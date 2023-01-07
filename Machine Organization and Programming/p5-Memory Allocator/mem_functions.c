#include "mem.h"
extern BLOCK_HEADER* first_header;

// return a pointer to the payload
// if a large enough free block isn't available, return NULL
void* Mem_Alloc(int size) {
    if (size == 0){return NULL;}

    int alloc_bit;
    int block_size;
    int payload;
    int padding;
    BLOCK_HEADER *current = first_header;

    while (1) {
        alloc_bit = current->size_alloc & 1;
        block_size = current->size_alloc & 0xFFFFFFFE;
        payload = current->payload;
        // padding = block_size - 8 - payload;
        current->size_alloc = block_size + alloc_bit;

        // check if current reaches the end
        if (current->size_alloc == 1) {
            return NULL;
        }

        // check if the current block is allocated
        if (alloc_bit == 1) {
            // the current block is allocated
            current = (BLOCK_HEADER *)((unsigned long)current + block_size);
            continue;
        }
        else {
            // current block is not allocated
            // check if the current block payload is large enough to hold the user requested size
            if (payload < size) {
                // payload is not big enough
                current = (BLOCK_HEADER *) ((unsigned long) current + block_size);
                continue;
            } else {
                // payload is big enough, prepare to return
                // check if a split is needed
                padding = payload - size;
                if (padding >= 16) {
                    // padding >= 16, a split is required
                    int newPadding;
                    newPadding = padding % 16;
                    BLOCK_HEADER *newHeader = NULL;
                    newHeader = (BLOCK_HEADER *) ((unsigned long) current + 8 + size + newPadding);
                    newHeader->payload = padding - newPadding - 8;
                    newHeader->size_alloc = padding - newPadding;
                    current->size_alloc = size + 8 + newPadding + 1;
                    current->payload = size;
                }
                else {
                    current->size_alloc = size + 8 + padding + 1;
                    current->payload = size;
                }
                return (BLOCK_HEADER *) ((unsigned long) current + 8);
            }
        }
    }

}


// return 0 on success
// return -1 if the input ptr was invalid
int Mem_Free(void *ptr) {
    // traverse the list and check all pointers to find the correct block
    // if you reach the end of the list without finding it return -1
    ptr-=8;
    int block_size;
    BLOCK_HEADER *pre = NULL;
    BLOCK_HEADER *next = NULL;
    BLOCK_HEADER *current = first_header;

    // loop till find the target header
    while(1){
        block_size = current->size_alloc & 0xFFFFFFFE;
        if (current->size_alloc == 1){
            return -1;
        }
        else if (current == ptr) {// free
            //wts free but not allocated
            if (current->size_alloc == 0){
                return -1;
            }

            // find next
            next = (BLOCK_HEADER *)((unsigned long)current + block_size);
            current->size_alloc -= 1;
            current->payload = current->size_alloc - 8;
            break;
        }
        else{
            pre = current;
            current = (BLOCK_HEADER *)((unsigned long)current + block_size);
        }
    }

//    BLOCK_HEADER *last_header = (BLOCK_HEADER *)((unsigned long)first_header + size);
    // coalesce adjacent free blocks

    // merge downwards
    if (next->size_alloc != 1) {
        if ((next->size_alloc & 1) == 0) {
            current->size_alloc += next->size_alloc;
            current->payload = current->size_alloc - 8;
        }
    }

    if (pre != NULL) {
        if ((pre->size_alloc & 1) == 0) {
            pre->size_alloc += current->size_alloc;
            pre->payload = pre->size_alloc-8;
            return 0;
        }
    }

    return -1;
}

