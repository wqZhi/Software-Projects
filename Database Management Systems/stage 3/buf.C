#include <memory.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <iostream>
#include <stdio.h>
#include "page.h"
#include "buf.h"

#define ASSERT(c)  { if (!(c)) { \
		       cerr << "At line " << __LINE__ << ":" << endl << "  "; \
                       cerr << "This condition should hold: " #c << endl; \
                       exit(1); \
		     } \
                   }

//----------------------------------------
// Constructor of the class BufMgr
//----------------------------------------

BufMgr::BufMgr(const int bufs)
{
    numBufs = bufs;

    bufTable = new BufDesc[bufs];
    memset(bufTable, 0, bufs * sizeof(BufDesc));
    for (int i = 0; i < bufs; i++) 
    {
        bufTable[i].frameNo = i;
        bufTable[i].valid = false;
    }

    bufPool = new Page[bufs];
    memset(bufPool, 0, bufs * sizeof(Page));

    int htsize = ((((int) (bufs * 1.2))*2)/2)+1;
    hashTable = new BufHashTbl (htsize);  // allocate the buffer hash table

    clockHand = bufs - 1;
}


BufMgr::~BufMgr() {

    // flush out all unwritten pages
    for (int i = 0; i < numBufs; i++) 
    {
        BufDesc* tmpbuf = &bufTable[i];
        if (tmpbuf->valid == true && tmpbuf->dirty == true) {

#ifdef DEBUGBUF
            cout << "flushing page " << tmpbuf->pageNo
                 << " from frame " << i << endl;
#endif

            tmpbuf->file->writePage(tmpbuf->pageNo, &(bufPool[i]));
        }
    }

    delete [] bufTable;
    delete [] bufPool;
}


const Status BufMgr::allocBuf(int & frame) 
{
    Status status = OK;
    // pinnedFrameCnt is increment if current frame is pinned
    // It is reset to 0 if current frame is unpinned.
    // So if pinnedFrameCnt equals to numBufs means that 
    // the last numBufs numbers of frames are pinned.
    // So all the frames are pinned.
    unsigned int pinnedFrameCnt = 0;
    while(pinnedFrameCnt < numBufs) {
        advanceClock();
        BufDesc& bufDesc = bufTable[clockHand];
        if (bufDesc.valid) {
            if (bufDesc.pinCnt) {
                pinnedFrameCnt++;
                continue;
            }
            // Reset if a frame is unpinned.
            pinnedFrameCnt = 0;
            
            if (bufDesc.refbit) {
                bufDesc.refbit = false;
                continue;
            }
            if (bufDesc.dirty) {
                // Flush Page to Disk
                status = bufDesc.file->writePage(bufDesc.pageNo, &bufPool[clockHand]);
                if (status) return status;
                
            }
            // Remove entry from the hash table
            hashTable->remove(bufDesc.file, bufDesc.pageNo);

        }
        frame = clockHand;
        return OK;
    }

    return BUFFEREXCEEDED;
}

	
const Status BufMgr::readPage(File* file, const int pageNo, Page*& page)
{
    Status status = OK;
    int frameNo;
    status = hashTable->lookup(file, pageNo, frameNo);
    if (status && status != HASHNOTFOUND) return status;
    // Case 1
    if (status == HASHNOTFOUND) {
        status = allocBuf(frameNo);
        if (status) return status;
        // read the page form dist into the buffer pool frame
        status = file->readPage(pageNo, &bufPool[frameNo]);
        if (status) return status;
        // insert the page into the hashtable
        status = hashTable->insert(file, pageNo, frameNo);
        if (status) return status;
        // invoke Set on the frame to set it up properly
        bufTable[frameNo].Set(file, pageNo);
    }
    // Case 2
    else {
        bufTable[frameNo].refbit = true;
        bufTable[frameNo].pinCnt++;
    }

    page = &bufPool[frameNo];

    return OK;
}


const Status BufMgr::unPinPage(File* file, const int pageNo, 
			       const bool dirty) 
{
    Status status = OK;
    int frameNo;
    status = hashTable->lookup(file, pageNo, frameNo);
    if (status) return status;

    BufDesc& bufDesc = bufTable[frameNo];
    // Only decrement the pinCnt if pinCnt > 0.
    if (!bufDesc.pinCnt) return PAGENOTPINNED;
    bufDesc.pinCnt--;
    if (dirty) bufDesc.dirty = true;

    return OK;
}

const Status BufMgr::allocPage(File* file, int& pageNo, Page*& page) 
{
    Status status = OK;

    // allocate an empty page in the specified file
    status = file->allocatePage(pageNo);
    if (status) return status;
    // obtain a buffer pool frame
    int frameNo;
    status = allocBuf(frameNo);
    if (status) return status;
    // an entry is inserted into the hashtable 
    status = hashTable->insert(file, pageNo, frameNo);
    if (status) return status;
    // Set() is invoked on the frame to set it up propertly
    bufTable[frameNo].Set(file, pageNo);

    page = &bufPool[frameNo];

    return OK;
}

const Status BufMgr::disposePage(File* file, const int pageNo) 
{
    // see if it is in the buffer pool
    Status status = OK;
    int frameNo = 0;
    status = hashTable->lookup(file, pageNo, frameNo);
    if (status == OK)
    {
        // clear the page
        bufTable[frameNo].Clear();
    }
    status = hashTable->remove(file, pageNo);

    // deallocate it in the file
    return file->disposePage(pageNo);
}

const Status BufMgr::flushFile(const File* file) 
{
  Status status;

  for (int i = 0; i < numBufs; i++) {
    BufDesc* tmpbuf = &(bufTable[i]);
    if (tmpbuf->valid == true && tmpbuf->file == file) {

      if (tmpbuf->pinCnt > 0)
	  return PAGEPINNED;

      if (tmpbuf->dirty == true) {
#ifdef DEBUGBUF
	cout << "flushing page " << tmpbuf->pageNo
             << " from frame " << i << endl;
#endif
	if ((status = tmpbuf->file->writePage(tmpbuf->pageNo,
					      &(bufPool[i]))) != OK)
	  return status;

	tmpbuf->dirty = false;
      }

      hashTable->remove(file,tmpbuf->pageNo);

      tmpbuf->file = NULL;
      tmpbuf->pageNo = -1;
      tmpbuf->valid = false;
    }

    else if (tmpbuf->valid == false && tmpbuf->file == file)
      return BADBUFFER;
  }
  
  return OK;
}


void BufMgr::printSelf(void) 
{
    BufDesc* tmpbuf;
  
    cout << endl << "Print buffer...\n";
    for (int i=0; i<numBufs; i++) {
        tmpbuf = &(bufTable[i]);
        cout << i << "\t" << (char*)(&bufPool[i]) 
             << "\tpinCnt: " << tmpbuf->pinCnt;
    
        if (tmpbuf->valid == true)
            cout << "\tvalid\n";
        cout << endl;
    };
}


