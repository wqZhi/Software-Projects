#Basic Solving Steps
  3 Firstly, I use [./memcached -p 13468] and [telnet 127.0.0.1 13468] to initially test my memcahed file with exam    ple that given by instructor in order to make sure the file is correct with incr, decr and get-firstname.
  4 Then, I use the code [grep \"incr\" * ] to find the logic code for incr function to find out how increment achi    eved.
  5 Meanwhile, I also find the method that reads user's input in proto.text.c which is called process.command.ascii    , so I add mult and div in this method which enable their input detection. After that, trace to process.arithme    tic.command method, I changed the parameter const bool incr to a const int variable which allows the add.delta     method able to identify the specificy operation and handled it. Using grep to trace and search for the name of     the method being called in these methods. Then I found out in memcached.c there is a method called (enum delta.    result.type do.add.delta) does the mathematics calculation like incr and decr. So I also added div and mult in     this if-else statement and later on by testing, I continue add a return Error to notify the divisor can't be ze    ro or negative.
  6 
  7 #OTHER Modifications:
  8 trace.h: adding #define MEMCACHED COMMAND MULT(arg0, arg1, arg2, arg3) define MEMCACHED COMMAND MULT ENABLED()     (0) / simmilar with DIV
  9 
 10 Changing enum in some file .c .h for the Error message(can't be zero or neg. number) I created.
 11 
 12 Adding new switch Case Error message in some file .c .h  like: proto bin.c
 13 
 14 Changing the parameter in some flle .c .h since I change the const bool to const int
