	.file	"find_max_template.c"
	.text
	.section	.rodata
	.align 8
.LC0:
	.string	"The length of the array is %d\n"
	.text
	.globl	Find_Max
	.type	Find_Max, @function
Find_Max:

.LFB0:
// prologue - write code for the prologue here
     endbr64                   # funciton start

     pushq   %rbp              # set rbp

     movq    %rsp, %rbp        # store rsp to rbp

// reserve space for local variables
     subq    $32, %rsp         # reserve space

     movl    %edi, -12(%rbp)   # save first argument n

     movq    %rsi, -28(%rbp)   # save second argument *a

     movl    $0, -4(%rbp)      # set i = 1

     movl    $0, -8(%rbp)      # set max_index = 0

// printf("The length of the array is %d\n", n);
	 movl	-12(%rbp), %eax    # store first arguement in %eax

	 movl	%eax, %esi         # store %eax value in %esi

	 leaq	.LC0(%rip), %rdi   # get string

	 movl	$0, %eax           # move 0 to %eax

	 call	printf@PLT         # pritnt the length of array

     jmp    LOOP_CONDITION     # goto (test i)

// reserve space for local variables
/* make a table here the variables from 
 * your c code and the addresses used in assembly 
 * -12(%rbp)  - address of n
 * -28(%rbp) - address of *a
 * -4(%rbp) - address of i
 * -8(%rbp) - address of max_index
 *
*/

// this code calls Print_One_Number to 
// print the number found in register %eax - use this code to debug 
// replace $999 with a register or memory address you wish to print
TOP_OF_LOOP:                    #loop:
    movq    -28(%rbp), %rax     #   get pointer *a, store in temp_place

    movl    -4(%rbp), %edx      #   get i

    movl    (%rax,%rdx,4), %eax #   get a[i]

	movl	%eax, %edi          #   store %eax into %edi for print

	call	Print_One_Number@PLT#   call print

// write your code to find the index of the maximum value here
    //GET a[max_index]
    movq    -28(%rbp), %rax     #   get pointer *a, store in a register

    movl    -8(%rbp), %edx      #   get max_index value

    movl    (%rax,%rdx,4), %ecx #   store a[max_index] in a register

    //GET a[i]
    movq    -28(%rbp), %rax     #   get pointer *a, store in a register

    movl    -4(%rbp), %edx      #   get i value

    movl    (%rax,%rdx,4), %edx #   store a[i] in a register

    //IF_CONDITIONAL_START
    cmpl    %ecx, %edx          #   Compare a[i] : a[max_index]

    jle     IF_FALSE_BLOCK      #   If <= , go to (increase i)

    movl    -4(%rbp), %edx      #   else, store i in %edx register

    movl    %edx, -8(%rbp)      #   and, let i's value store in max_index
    //IF_CONDITIONAL_END

IF_FALSE_BLOCK:                 #increase i:
    movl    -4(%rbp), %ecx      #   get i

    addl    $1, %ecx            #   Increase i

    movl    %ecx, -4(%rbp)      #   store changed i back to i's address

LOOP_CONDITION:                 #test i:
    movl    -4(%rbp), %ecx      #   get i

    movl    -12(%rbp), %edx     #   get n

    cmpl    %edx, %ecx          #   Compare i : n

    jl      TOP_OF_LOOP         #   If <, goto (loop)
    
// prepare the return value
// the template returns -1, you will need to update this
END_OF_FUNCTION:
	movl	-8(%rbp), %eax      #   Prepare the Return value

// epilogue - complete the epilogue below
    leave
    ret

.LFE0:
	.size	Find_Max, .-Find_Max
	.ident	"GCC: (Ubuntu 9.3.0-17ubuntu1~20.04) 9.3.0"
	.section	.note.GNU-stack,"",@progbits
	.section	.note.gnu.property,"a"
	.align 8
	.long	 1f - 0f
	.long	 4f - 1f
	.long	 5
0:
	.string	 "GNU"
1:
	.align 8
	.long	 0xc0000002
	.long	 3f - 2f
2:
	.long	 0x3
3:
	.align 8
4:
