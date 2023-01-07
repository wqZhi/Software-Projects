all:
	gcc -fPIC -g -c -Wall fscli.c -o libmfs
	gcc -shared -Wl,-soname,libmfs.so -o libmfs.so libmfs -lc
	gcc fsserv.c -o server
	rm -f test.img
	gcc mkfs.c -o mkfs
	./mkfs -f test.img