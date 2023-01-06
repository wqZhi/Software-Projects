#include "catalog.h"
#include "query.h"


/*
 * Inserts a record into the specified relation.
 *
 * Returns:
 * 	OK on success
 * 	an error code otherwise
 */

const Status QU_Insert(const string & relation, 
	const int attrCnt, 
	const attrInfo attrList[])
{
	Status status = OK;
	// part 6

	InsertFileScan insertScan(relation, status);
	if (status) return status;

	AttrDesc descAttr[attrCnt];
	for (int i = 0; i < attrCnt; i++) {
		status = attrCat->getInfo(attrList[i].relName, attrList[i].attrName, descAttr[i]);
		if (status) return status;
	}	

	int reclen = 0;
	for (int i = 0; i < attrCnt; i++) {
		reclen += descAttr[i].attrLen;
	}

	char outputData[reclen];
	Record outputRec;
	outputRec.data = outputData;
	outputRec.length = reclen;

	for (int i = 0; i < attrCnt; i++) {
		if (attrList[i].attrValue == NULL) {
			return ATTRTYPEMISMATCH; // TODO: use an error for reject
		}

		void* pVal;
		int iVal;
		float fVal;
		if (attrList[i].attrType == INTEGER) {
			iVal = atoi((const char*)attrList[i].attrValue);
			pVal = &iVal;
		}
		else if (attrList[i].attrType == FLOAT) {
			fVal = atof((const char*)attrList[i].attrValue);
			pVal = &fVal;
		}
		else {
			pVal = attrList[i].attrValue;
		}
		memcpy(
			outputData + descAttr[i].attrOffset,
			pVal,
			descAttr[i].attrLen
		);
	}

	RID outRID;
	status = insertScan.insertRecord(outputRec, outRID);
	if (status) return status;

	return OK;
}

