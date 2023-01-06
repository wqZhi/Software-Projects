#include "heapfile.h"
#include "error.h"

// routine to create a heapfile
const Status createHeapFile(const string fileName)
{
    File* 		file;
    Status 		status;
    FileHdrPage*	hdrPage;
    int			hdrPageNo;
    int			newPageNo;
    Page*		newPage;

    // try to open the file. This should return an error
    status = db.openFile(fileName, file);
    if (status != OK)
    {
		// file doesn't exist. First create it and allocate
		// an empty header page and data page.
		
		// TODO:
        // This function creates an empty (well, almost empty) heap file.
        // To do this create a db level file by calling db->createfile().
        status = db.createFile(fileName);
        if (status) return status;

        status = db.openFile(fileName, file);
        if (status) return status;

        // Then, allocate an empty page by invoking bm->allocPage() appropriately. 
        // As you know allocPage() will return a pointer to an empty page in the buffer pool along with the page number of the page. 
        status = bufMgr->allocPage(file, newPageNo, newPage);
        if (status) return status;

        // Take the Page* pointer returned from allocPage() and cast it to a FileHdrPage*. 
        FileHdrPage* hdrPage = (FileHdrPage*)newPage;
        
        // Using this pointer initialize the values in the header page.
        strncpy(hdrPage->fileName, fileName.data(), MAXNAMESIZE-1); // ????
        hdrPage->recCnt = 0;
        
        // Then make a second call to bm->allocPage(). 
        // This page will be the first data page of the file. 
        int newDataPageNo;
        Page* newDataPage;
        status = bufMgr->allocPage(file, newDataPageNo, newDataPage);
        if (status) return status;

        // Using the Page* pointer returned, invoke its init() method to initialize the page contents. 
        newDataPage->init(newDataPageNo);
        
        // Finally, store the page number of the data page in firstPage and lastPage attributes of the FileHdrPage.
        hdrPage->firstPage = newDataPageNo;
        hdrPage->lastPage = newDataPageNo;
        hdrPage->pageCnt = 1;

        //When you have done all this unpin both pages and mark them as dirty.
        status = bufMgr->unPinPage(file, newPageNo, true);
        if (status) return status;
        status = bufMgr->unPinPage(file, newDataPageNo, true);
        if (status) return status;

        db.closeFile(file);

        return OK;
    }
    else return (FILEEXISTS);
}

// routine to destroy a heapfile
const Status destroyHeapFile(const string fileName)
{
	return (db.destroyFile (fileName));
}

// constructor opens the underlying file
HeapFile::HeapFile(const string & fileName, Status& returnStatus)
{
    Status 	status;
    Page*	pagePtr;

    cout << "opening file " << fileName << endl;

	//This method first opens the appropriate file by calling db->openFile() (do not forget to save the File* returned in the filePtr data member). 
    // open the file and read in the header page and the first data page
    if ((status = db.openFile(fileName, filePtr)) == OK)
    {
        // TODO:
        // Next, it reads and pins the header page for the file in the buffer pool, initializing the private data members headerPage, headerPageNo, and hdrDirtyFlag. 
        // You might be wondering how you get the page number of the header page. 
        // This is what file->getFirstPage() is used for (see description of the I/O layer)! 
        returnStatus = filePtr->getFirstPage(headerPageNo);
        if (returnStatus) return;
        returnStatus = bufMgr->readPage(filePtr, headerPageNo, pagePtr);
        if (returnStatus) return;
        headerPage = (FileHdrPage*)pagePtr;


        // Finally, read and pin the first page of the file into the buffer pool, 
        Page* firstDataPage;
        int firstDataPageNo = headerPage->firstPage;
        returnStatus = bufMgr->readPage(filePtr, firstDataPageNo, firstDataPage);
        if (returnStatus) return;

        //initializing the values of curPage, curPageNo, and curDirtyFlag appropriately. 
        curPage = firstDataPage;
        curPageNo = firstDataPageNo;
        curDirtyFlag = false; // ????

        // Set curRec to NULLRID.
        curRec = NULLRID;
		
    }
    else
    {
    	cerr << "open of heap file failed\n";
		returnStatus = status;
		return;
    }
}

// the destructor closes the file
HeapFile::~HeapFile()
{
    Status status;
    cout << "invoking heapfile destructor on file " << headerPage->fileName << endl;

    // see if there is a pinned data page. If so, unpin it 
    if (curPage != NULL)
    {
    	status = bufMgr->unPinPage(filePtr, curPageNo, curDirtyFlag);
		curPage = NULL;
		curPageNo = 0;
		curDirtyFlag = false;
		if (status != OK) cerr << "error in unpin of date page\n";
    }
	
	 // unpin the header page
    status = bufMgr->unPinPage(filePtr, headerPageNo, hdrDirtyFlag);
    if (status != OK) cerr << "error in unpin of header page\n";
	
	// status = bufMgr->flushFile(filePtr);  // make sure all pages of the file are flushed to disk
	// if (status != OK) cerr << "error in flushFile call\n";
	// before close the file
	status = db.closeFile(filePtr);
    if (status != OK)
    {
		cerr << "error in closefile call\n";
		Error e;
		e.print (status);
    }
}

// Return number of records in heap file

const int HeapFile::getRecCnt() const
{
  return headerPage->recCnt;
}

// retrieve an arbitrary record from a file.
// if record is not on the currently pinned page, the current page
// is unpinned and the required page is read into the buffer pool
// and pinned.  returns a pointer to the record via the rec parameter

const Status HeapFile::getRecord(const RID & rid, Record & rec)
{
    Status status;

    // cout<< "getRecord. record (" << rid.pageNo << "." << rid.slotNo << ")" << endl;
   
    // TODO:
    // This method returns a record (via the rec structure) given the RID of the record. 
    // The private data members curPage and curPageNo should be used to keep track of the current data page pinned in the buffer pool. 
    // If the desired record is on the currently pinned page, simply invoke curPage->getRecord(rid, rec) to get the record.  
    if (rid.pageNo == curPageNo && curPage) {
        status = curPage->getRecord(rid, rec);
        if (status) return status;
    }
    // Otherwise, you need to unpin the currently pinned page (assuming a page is pinned) 
    else {
            if (curPage) {
            status = bufMgr->unPinPage(filePtr, curPageNo, curDirtyFlag);
            if (status) return status;
        }
        // and use the pageNo field of the RID to read the page into the buffer pool.
        curPageNo = rid.pageNo;
        curDirtyFlag = false;
        status = bufMgr->readPage(filePtr, curPageNo, curPage);
        if (status) return status;

        status = curPage->getRecord(rid, rec);
        if (status) return status;
    }

    curRec = rid;

    return OK;
}

HeapFileScan::HeapFileScan(const string & name,
			   Status & status) : HeapFile(name, status)
{
    filter = NULL;
}

const Status HeapFileScan::startScan(const int offset_,
				     const int length_,
				     const Datatype type_, 
				     const char* filter_,
				     const Operator op_)
{
    if (!filter_) {                        // no filtering requested
        filter = NULL;
        return OK;
    }
    
    if ((offset_ < 0 || length_ < 1) ||
        (type_ != STRING && type_ != INTEGER && type_ != FLOAT) ||
        (type_ == INTEGER && length_ != sizeof(int)
         || type_ == FLOAT && length_ != sizeof(float)) ||
        (op_ != LT && op_ != LTE && op_ != EQ && op_ != GTE && op_ != GT && op_ != NE))
    {
        return BADSCANPARM;
    }

    offset = offset_;
    length = length_;
    type = type_;
    filter = filter_;
    op = op_;

    return OK;
}


const Status HeapFileScan::endScan()
{
    Status status;
    // generally must unpin last page of the scan
    if (curPage != NULL)
    {
        status = bufMgr->unPinPage(filePtr, curPageNo, curDirtyFlag);
        curPage = NULL;
        curPageNo = 0;
		curDirtyFlag = false;
        return status;
    }
    return OK;
}

HeapFileScan::~HeapFileScan()
{
    endScan();
}

const Status HeapFileScan::markScan()
{
    // make a snapshot of the state of the scan
    markedPageNo = curPageNo;
    markedRec = curRec;
    return OK;
}

const Status HeapFileScan::resetScan()
{
    Status status;
    if (markedPageNo != curPageNo) 
    {
		if (curPage != NULL)
		{
			status = bufMgr->unPinPage(filePtr, curPageNo, curDirtyFlag);
			if (status != OK) return status;
		}
		// restore curPageNo and curRec values
		curPageNo = markedPageNo;
		curRec = markedRec;
		// then read the page
		status = bufMgr->readPage(filePtr, curPageNo, curPage);
		if (status != OK) return status;
		curDirtyFlag = false; // it will be clean
    }
    else curRec = markedRec;
    return OK;
}


const Status HeapFileScan::scanNext(RID& outRid)
{
    Status 	status = OK;
    RID		nextRid;
    RID		tmpRid;
    int 	nextPageNo;
    Record      rec;
	
    // TODO:
	// Returns (via the outRid parameter) the RID of the next record that satisfies the scan predicate. 
    // The basic idea is to scan the file one page at a time. 

    for (tmpRid = curRec; ; tmpRid = nextRid) {
        // For each page, use the firstRecord() and nextRecord() methods of the Page class to get the rids of all the records on the page. 
        if (curPage) { // if curPage != NULL
            status = curPage->nextRecord(tmpRid, nextRid);
        }
        else {
                // read the first page as curPage
                curPageNo = headerPage->firstPage;
                curDirtyFlag = false;
                status = bufMgr->readPage(filePtr, curPageNo, curPage);
                if (status) return status;

                status = curPage->firstRecord(nextRid);   
        }

        if (status == ENDOFPAGE || status == NORECORDS) {
            do {
                // Try to read next page
                if (curPageNo == headerPage->lastPage || curPageNo < 0) {
                    return FILEEOF; 
                }

                // fetch next page num
                status = curPage->getNextPage(nextPageNo);
                if (status) return status;

                // unpin current page
                status = bufMgr->unPinPage(filePtr, curPageNo, curDirtyFlag);
                if (status) return status;

                // read next page
                curPageNo = nextPageNo;
                // std::cout << nextPageNo << ' '; // delete this
                curDirtyFlag = false;
                status = bufMgr->readPage(filePtr, curPageNo, curPage);
                if (status) return status;

                // get the first record of the next page
                status = curPage->firstRecord(nextRid);            
            } while(status == NORECORDS);
            if (status) return status;
        }
        else if (status) return status;

        // Convert the rid to a pointer to the record data and invoke matchRec() to determine if record satisfies the filter associated with the scan. 
        status = curPage->getRecord(nextRid, rec);
        if (status) return status;

        if (matchRec(rec)) { // ????
            // If so, store the rid in curRec and return curRec. 
            curRec = nextRid;
            outRid = curRec;
            return OK;
            // To make things fast, keep the current page pinned until all the records on the page have been processed. 
        }
    }
    // IN THE if (status == ENDOFPAGE || status == NORECORDS) BLOCK:
    // Then continue with the next page in the file.  
    // Since the HeapFileScan class is derived from the HeapFile class it also has all the methods of the HeapFile class as well. 
    // Returns OK if no errors occurred. 
    // Otherwise, return the error code of the first error that occurred.
	
}


// returns pointer to the current record.  page is left pinned
// and the scan logic is required to unpin the page 

const Status HeapFileScan::getRecord(Record & rec)
{
    return curPage->getRecord(curRec, rec);
}

// delete record from file. 
const Status HeapFileScan::deleteRecord()
{
    Status status;

    // delete the "current" record from the page
    status = curPage->deleteRecord(curRec);
    curDirtyFlag = true;

    // reduce count of number of records in the file
    headerPage->recCnt--;
    hdrDirtyFlag = true; 
    return status;
}


// mark current page of scan dirty
const Status HeapFileScan::markDirty()
{
    curDirtyFlag = true;
    return OK;
}

const bool HeapFileScan::matchRec(const Record & rec) const
{
    // no filtering requested
    if (!filter) return true;

    // see if offset + length is beyond end of record
    // maybe this should be an error???
    if ((offset + length -1 ) >= rec.length)
	return false;

    float diff = 0;                       // < 0 if attr < fltr
    switch(type) {

    case INTEGER:
        int iattr, ifltr;                 // word-alignment problem possible
        memcpy(&iattr,
               (char *)rec.data + offset,
               length);
        memcpy(&ifltr,
               filter,
               length);
        diff = iattr - ifltr;
        break;

    case FLOAT:
        float fattr, ffltr;               // word-alignment problem possible
        memcpy(&fattr,
               (char *)rec.data + offset,
               length);
        memcpy(&ffltr,
               filter,
               length);
        diff = fattr - ffltr;
        break;

    case STRING:
        diff = strncmp((char *)rec.data + offset,
                       filter,
                       length);
        break;
    }

    switch(op) {
    case LT:  if (diff < 0.0) return true; break;
    case LTE: if (diff <= 0.0) return true; break;
    case EQ:  if (diff == 0.0) return true; break;
    case GTE: if (diff >= 0.0) return true; break;
    case GT:  if (diff > 0.0) return true; break;
    case NE:  if (diff != 0.0) return true; break;
    }

    return false;
}

InsertFileScan::InsertFileScan(const string & name,
                               Status & status) : HeapFile(name, status)
{
  //Do nothing. Heapfile constructor will bread the header page and the first
  // data page of the file into the buffer pool
}

InsertFileScan::~InsertFileScan()
{
    Status status;
    // unpin last page of the scan
    if (curPage != NULL)
    {
        status = bufMgr->unPinPage(filePtr, curPageNo, true);
        curPage = NULL;
        curPageNo = 0;
        if (status != OK) cerr << "error in unpin of data page\n";
    }
}

// Insert a record into the file
const Status InsertFileScan::insertRecord(const Record & rec, RID& outRid)
{
    Page*	newPage;
    int		newPageNo;
    Status	status, unpinstatus;
    RID		rid;

    // check for very large records
    if ((unsigned int) rec.length > PAGESIZE-DPFIXED)
    {
        // will never fit on a page, so don't even bother looking
        return INVALIDRECLEN;
    }

    // TODO:
    // Inserts the record described by rec into the file returning the RID of the inserted record in outRid.

    // If curPage doesn't exist, use the last page.
    if (!curPage) {
            curPageNo = headerPage->lastPage;
            curDirtyFlag = false;
            status = bufMgr->readPage(filePtr, curPageNo, curPage);
            if (status) return status;
    }

    status = curPage->insertRecord(rec, rid);
    if (status == NOSPACE) {

        status = filePtr->allocatePage(newPageNo);
        if (status) return status;

        status = curPage->setNextPage(newPageNo);
        if (status) return status;

        status = bufMgr->unPinPage(filePtr, curPageNo, curDirtyFlag);
        if (status) return status;

        status = bufMgr->readPage(filePtr, newPageNo, newPage);
        if (status) return status;

        newPage->init(newPageNo);
        
        status = newPage->insertRecord(rec, rid);
        if (status) return status;

        headerPage->lastPage = newPageNo;
        headerPage->pageCnt += 1;
        hdrDirtyFlag = true;
        curPage = newPage;
        curPageNo = newPageNo;
    }
    else if (status) return status;

    outRid = rid;

    curDirtyFlag = true;
    headerPage->recCnt += 1;
    hdrDirtyFlag = true;

    return OK;
}


