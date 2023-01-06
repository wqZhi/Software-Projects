#include "catalog.h"
#include "query.h"
#include "stdio.h"
#include "stdlib.h"


// forward declaration
const Status ScanSelect(const string & result, 
			const int projCnt, 
			const AttrDesc projNames[],
			const AttrDesc *attrDesc, 
			const Operator op, 
			const char *filter,
			const int reclen);

/*
 * Selects records from the specified relation.
 *
 * Returns:
 * 	OK on success
 * 	an error code otherwise
 */

const Status QU_Select(const string & result, 
		       const int projCnt, 
		       const attrInfo projNames[],
		       const attrInfo *attr, 
		       const Operator op, 
		       const char *attrValue)
{
	Status status = OK;
   // Qu_Select sets up things and then calls ScanSelect to do the actual work
    cout << "Doing QU_Select " << endl;

	if (projCnt < 0) {
		return ATTRTYPEMISMATCH; // an error 
	}

	AttrDesc descProjNames[projCnt];
	for (int i = 0; i < projCnt; i++) {
		status = attrCat->getInfo(projNames[i].relName, projNames[i].attrName, descProjNames[i]);
		if (status) return status;
	}

	int reclen = 0;
	for (int i = 0; i < projCnt; i++) {
		reclen += descProjNames[i].attrLen;
	}

	if (attr) {
		AttrDesc descAttr;
		status = attrCat->getInfo(attr->relName, attr->attrName, descAttr);

		const char *filter;
		int iVal;
		float fVal;
		if (descAttr.attrType == INTEGER) {
			iVal = atoi(attrValue);
			filter = (char*)&iVal;
		}
		else if (descAttr.attrType == FLOAT) {
			fVal = atof(attrValue);
			filter = (char*)&fVal;
		}
		else {
			filter = attrValue;
		}

		return ScanSelect(result, projCnt, descProjNames, &descAttr, op, filter, reclen);
	}
	else {
		return ScanSelect(result, projCnt, descProjNames, NULL, op, NULL, reclen);
	}

}
// A selection is implemented using a filtered HeapFileScan.  
// The result of the selection is stored in the result relation called result 
// (a  heapfile with this name will be created by the parser before QU_Select() is called).  
// The project list is defined by the parameters projCnt and projNames.  
// Projection should be done on the fly as each result tuple is being appended to the result table.  
// A final note: the search value is always supplied as the character string attrValue.  
// You should convert it to the proper type based on the type of attr. 
// You can use the atoi() function to convert a char* to an integer and atof() to convert it to a float.   
// If attr is NULL, an unconditional scan of the input table should be performed.

const Status ScanSelect(const string & result, 
			const int projCnt, 
			const AttrDesc projNames[],
			const AttrDesc *attrDesc, 
			const Operator op, 
			const char *filter,
			const int reclen)
{
	Status status = OK;
    cout << "Doing HeapFileScan Selection using ScanSelect()" << endl;

	char outputData[reclen];
	Record outputRec;
	outputRec.data = outputData;
	outputRec.length = reclen;

	HeapFileScan scan(string(projNames[0].relName), status);
	if (status) return status;
	if (filter) {
		status = scan.startScan(attrDesc->attrOffset, attrDesc->attrLen, (Datatype)attrDesc->attrType, filter, op);
	}
	else {
		status = scan.startScan(0, 0, STRING, NULL, op);
	}
    InsertFileScan resultRel(result, status);
    if (status) return status;

	RID rid;
	while(scan.scanNext(rid) == OK) {
		Record rec;
		status = scan.getRecord(rec);
		if (status) return status;

		int outputOffset = 0;
		for (int i = 0; i < projCnt; i++) {
			memcpy(
				outputData + outputOffset, 
				(char*)rec.data + projNames[i].attrOffset,
				projNames[i].attrLen
			);
			outputOffset += projNames[i].attrLen;
		}

		RID outRID;
		status = resultRel.insertRecord(outputRec, outRID);
		if (status) return status;
	}

	return OK;
}
