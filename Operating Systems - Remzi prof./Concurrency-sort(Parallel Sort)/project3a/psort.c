#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h> //Library for mmap
#include <sys/stat.h> //Library for struct stat
#include <unistd.h>
#include <sys/sysinfo.h>
#include <string.h>
#include <pthread.h>

typedef unsigned int uint;
int MAX_THREAD = 0;
int USED_THREAD = 0;

typedef struct {
  int key;
  char *record;
} key_record;

struct {
    int fd;
    char *map;
    char *fn;
} rec, sort;

typedef struct {
  key_record *keyRecord;
  int size;
} merge_param;

typedef struct {
  key_record * keyRecord;
  int pos1;
  int pos2;
  key_record * temp;
} sort_param;


void mergeSort_conquer(key_record *key_record_map, uint left, uint mid, uint right, key_record * temp) {
    uint i = left;
    uint j = mid + 1;
    int index = 0;
    while (i <= mid && j <= right) {
        if (key_record_map[i].key < key_record_map[j].key) {
            temp[index++] = key_record_map[i++];
        } else {
            temp[index++] = key_record_map[j++];
        }
    }
    
    while (i <= mid) {
        temp[index++] = key_record_map[i++];
    }
    while (j <= right) {
        temp[index++] = key_record_map[j++];
    }
    
    index = 0;
    while (left <= right) {
        key_record_map[left++] = temp[index++];
    }
}


void *mergeSort_divide(void * arg) {
  sort_param *param_divide = (sort_param*) arg;

  //key_record *key_record_map, int left, uint right, key_record * temp
    key_record *key_record_map = param_divide->keyRecord;
    int left = param_divide->pos1;
    int right = param_divide->pos2;
    key_record *temp = param_divide->temp;

    int mid = left + (right - left) / 2;

    if (left < right) {
        pthread_t tid1;
        pthread_t tid2;

        // left side sort
        sort_param param_left = {key_record_map, left, mid, temp};
        if (USED_THREAD < MAX_THREAD) {
          USED_THREAD++;
          pthread_create(&tid1, NULL, mergeSort_divide, &param_left);
          pthread_join(tid1, NULL);
          USED_THREAD--;
        }
        else {
          mergeSort_divide(&param_left);
        }

        // right side sort
        sort_param param_right = {key_record_map, mid + 1, right, temp};
        if (USED_THREAD < MAX_THREAD) {
          USED_THREAD++;
          pthread_create(&tid2, NULL, mergeSort_divide, &param_right);
          pthread_join(tid2, NULL);
          USED_THREAD--;
        }
        else {
          mergeSort_divide(&param_right);
        }

        mergeSort_conquer(key_record_map, left, mid, right, temp);
    }

    return NULL;
}

void* mergeSort(void *arg) {
    // key_record *key_record_map, uint size
    merge_param *param = (merge_param*) arg;

    key_record *key_record_map = param->keyRecord;
    int size = param->size;
    
    key_record *temp = (key_record *)malloc(param->size * sizeof(key_record));

    sort_param paramdivide = {key_record_map, 0, size - 1, temp};
    mergeSort_divide(&paramdivide);


    free(temp);
    return NULL;
}


//print error msg
void print_error_msg(){
  fprintf(stderr, "An error has occurred\n");
}

int main(int argc, char* argv[]) {
  if (argc != 3) {
    print_error_msg();
    exit(0);
  }

  //open file or create file
  rec.fn = argv[1];
  sort.fn = argv[2];
  
  if ((rec.fd = open(rec.fn, O_RDONLY)) == -1 || 
      (sort.fd = open(sort.fn, O_RDWR | O_CREAT, (mode_t)0600)) == -1 ||
      fgetc(fopen(rec.fn, "r")) == EOF) {
    print_error_msg();
    exit(0);
  }

  struct stat st;
  stat(rec.fn, &st);
  uint recsize = st.st_size;

  //using mmap for read and write
  if ((rec.map = mmap(0, recsize, PROT_READ, MAP_SHARED, rec.fd, 0)) == MAP_FAILED) {
    close(rec.fd);
    exit(0);
  }

  int temp = ftruncate(sort.fd, (int) recsize);
  temp++;
  if ( (sort.map = mmap(0, recsize, PROT_READ | PROT_WRITE, MAP_SHARED, sort.fd, 0)) == MAP_FAILED ) {
    close(sort.fd);
    exit(0);
  }


  uint size = recsize;
  size = size / 100;
  key_record *key_record_map = (key_record *)malloc(size * sizeof(key_record));

  //reading key and record
  key_record *c = key_record_map;

  for (char *r = rec.map; r < rec.map + size * 100; r += 100) {
    c->key = *(int *)r;
    c->record = r;
    c++;
  }


  //parallel merge sort
  MAX_THREAD = get_nprocs();
  USED_THREAD = 1;
  pthread_t threads;

  merge_param param = {key_record_map, size};
  pthread_create(&threads, NULL, mergeSort, &param);
  pthread_join(threads, NULL);
  // mergeSort(key_record_map, size);



  //write output
  char * ptr_temp = sort.map;
  for (uint i = 0; i < size; i++) {
    memcpy(ptr_temp, key_record_map[i].record, 100);
    ptr_temp += 100;
  }


  //end program
  free(key_record_map);
  if (munmap(rec.map, size) == -1 || munmap(sort.map, size) == -1) {
    exit(0);
  }
  close(sort.fd);
  close(rec.fd);
  exit(0);
}