/* check that address space size is updated in threads */
#include "types.h"
#include "user.h"


#undef NULL
#define NULL ((void*)0)
#define PGSIZE (4096)

int ppid;
int global = 0;
unsigned int size = 0;
lock_t lock, lock2;
int num_threads = 1;


#define assert(x) if (x) {} else { \
   printf(1, "%s: %d ", __FILE__, __LINE__); \
   printf(1, "assert failed (%s)\n", # x); \
   printf(1, "TEST FAILED\n"); \
   kill(ppid); \
   exit(); \
}

void worker(void *arg1, void *arg2);

int main(int argc, char *argv[])
{
   ppid = getpid();

   int arg1 = 11, arg2 = 22;

   size = (unsigned int)sbrk(0);

   int thread_pid = thread_create(worker, &arg1, &arg2);
   assert(thread_pid > 0);
   
   int join_pid = thread_join();
   assert(join_pid > 0);
   

   printf(1, "TEST PASSED\n");
   exit();
}

void worker2(void *arg1, void *arg2)
{
   lock_acquire(&lock);
   assert((unsigned int)sbrk(0) == size);
   global++;
   lock_release(&lock);

   
   lock_acquire(&lock2);
   assert((unsigned int)sbrk(0) == size);
   global++;
   lock_release(&lock2);

   
   exit();
}


void worker(void *arg1, void *arg2) {
   lock_init(&lock);
   lock_init(&lock2);
   lock_acquire(&lock);
   lock_acquire(&lock2);

   int nested_thread_id = thread_create(worker2, 0, 0);
    assert(nested_thread_id > 0);
   size = (unsigned int)sbrk(0);

   while (global < num_threads) {
      lock_release(&lock);
      sleep(100);
      lock_acquire(&lock);
   }

   global = 0;
   sbrk(10000);
   size = (unsigned int)sbrk(0);
   lock_release(&lock);

   while (global < num_threads) {
      lock_release(&lock2);
      sleep(100);
      lock_acquire(&lock2);
   }
   lock_release(&lock2);

   int nested_join_pid = thread_join();
   assert(nested_join_pid > 0);




   exit();
}

