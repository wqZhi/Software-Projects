#include "catalog.h"
#include "query.h"


/*
 * Deletes records from a specified relation.
 *
 * Returns:
 * 	OK on success
 * 	an error code otherwise
 */

const Status QU_Delete(const string & relation, 
		       const string & attrName, 
		       const Operator op,
		       const Datatype type, 
		       const char *attrValue)
{
// part 6
	Status status = OK;

	// if no attrName or value delete all tuples
	if (attrName.empty() || attrValue == NULL) {
		HeapFileScan scan(relation, status);
		if (status) return status;

		status = scan.startScan(0, 0, type, NULL, op);
		if (status) return status;

		RID rid;
		while(scan.scanNext(rid) == OK) {
			status = scan.deleteRecord();
			if (status) return status;
		}

		return OK;
	}

	AttrDesc attr;
	status = attrCat->getInfo(relation, attrName, attr);
	if (status) return status;

	const char* filter;
	float fVal;
	int iVal;
	if (type == INTEGER) {
		iVal = atoi(attrValue);
		filter = (const char*)&iVal;
	}
	else if (type == FLOAT) {
		fVal = atof(attrValue);
		filter = (const char*)&fVal;
	}
	else {
		filter = attrValue;
	}

	HeapFileScan scan(relation, status);
	if (status) return status;

	status = scan.startScan(attr.attrOffset, attr.attrLen, (Datatype)type, filter, op);
	if (status) return status;

	RID rid;
	while(scan.scanNext(rid) == OK) {
		status = scan.deleteRecord();
		if (status) return status;
	}

	return OK;
}


