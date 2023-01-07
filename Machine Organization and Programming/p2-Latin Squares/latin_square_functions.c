/******************************************************************************
 * @file: latin_square_functions.c
 *
 * WISC NETID wzhi3
 * CANVAS USERNAME 
 * WISC ID NUMBER 9082593279
 * OTHER COMMENTS FOR THE GRADER (OPTIONAL)
 *
 * @creator: YOUR PREFERED NAME (YOUR WISC EMAIL) wzhi3@wisc.edu
 * @modified: SUBMISSION DATE 20/11/2021
 *****************************************************************************/

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "latin_square_functions.h"

// Set this to 1 to enable dbgprintf statements, make sure to set it back to 0
// before submitting!
#define DEBUG               0
#define dbgprintf(...)      if (DEBUG) { printf(__VA_ARGS__); }
#define dbgprint_latin_square(n, LS) if (DEBUG) { Print_Latin_Square(n, LS); }

/******************************************************************************
 * Data Types and Structures
 *****************************************************************************/

// ADD ANY ADDITIONAL DATA TYPES OR STRUCTSvHERE

/******************************************************************************
 * Globals
 *****************************************************************************/
extern void Print_Latin_Square(const size_t n, char **latin_square);

/******************************************************************************
 * Helper functions
 *****************************************************************************/

// ADD ANY HELPER FUNCTIONS YOU MIGHT WRITE HERE

/******************************************************************************
 * Verificaiton functions
 *****************************************************************************/

/*
 * This function takes the name of the file containing the latin square
 * and reads in the data to the the latin_square parameter.
 *
 * There are many approaches that will work to read in the latin square data.
 * In any approach you choose, you will need to do at least the following:
 *     1) open the file
 *     2) read in the text from the file
 *     3) figure out the dimensions of the latin square (n)
 *     4) reserve memory for the latin_square. This requires 2 steps
 *         4a) reserve an array of pointers to the rows
 *         4b) reserve an array of characters for each row
 *     5) fill in the latin_square data structure
 *     6) close the file
 *
 * @param filename The name of the file to read in
 * @param latin_square_in A pointer to the latin square variable in main
 * @param n The value of both dimensions of the latin square (i.e. nxn)
 */
void Read_Latin_Square_File(const char *filename,
                            char ***latin_square_in,
                            size_t *n) {
    /* BEGIN MODIFYING CODE HERE */
    FILE *fptr = fopen(filename, "r");

    char *str = malloc(1001 * sizeof (char));
    fgets(str, 1001, fptr);
    for(int i = 0; i < 1000; i++) {
        if(*(str + i) < 33 || *(str + i)>126) {
            break;
        }
        (*n)++;
    }

    *latin_square_in = malloc((*n) * sizeof(char*));
    for(int i = 0; i < *n; i++){
        *(*latin_square_in + i) = malloc((*n) * sizeof(char));
    }

    for(int i = 0; i < *n; i++){
        for(int j = 0; j < *n; j++){
            *(*(*latin_square_in + i)+j) = *(str + j);
        }

        if (*n == 1000) {
            fgets(str, 1001, fptr);
        }

        fgets(str, 1001, fptr);
    }

    free(str);
    fclose(fptr);
    /* END MODIFYING CODE HERE */
}

/*
 * This function checks to see that exactly n symbols are used and that
 * each symbol is used exactly n times.
 *
 * @param n The value of both dimensions of the latin square (i.e. nxn)
 * @param latin_square_in A pointer to the latin square variable in main
 * @return 1 if valid, 0 if not
 */
int Verify_Alphabet(const size_t n, char **latin_square) {
    if (latin_square == NULL) {
        printf("Verify_Alphabet - latin_square is NULL\n");
        return 0;
    }

    /* BEGIN MODIFYING CODE HERE */
    int count;
    char tempChar;
    int row;
    int col;
//    char *visited = malloc(n * sizeof(char ));

    for(int i = 0; i < n; i++){
        count = 0;
        row = 0;
        col = 0;
        tempChar = *(*latin_square + i);

        for(int j = 0; j < n*n; j++) {
            if ( tempChar == *( *(latin_square + row) + col) ){
                count++;
            }

            col++;
            if (col == n ) {
                col = 0;
                row++;
            }
        }

        if (count != n){
            return 0;
        }
    }

    /* END MODIFYING CODE HERE */
    return 1;
}

/*
 * This function verifies that no symbol is used twice in a row or column.
 * It prints an error message alerting the user which rows or columns have
 * duplicate symbols. This means that you will have at most n row error prints
 * and n column error prints.
 *
 * Note: Test all rows first then test all columns.
 *
 * Error messages have been included for you. Do not change the format out the
 * print statements, as our grading scripts will use exact string matching. You
 * change the variable passed as the formatting argument to printf.
 *
 * @param n The value of both dimensions of the latin square (i.e. nxn)
 * @param latin_square_in A pointer to the latin square variable in main
 * @return 1 if valid, 0 if not
 */
int Verify_Rows_and_Columns(const size_t n, char **latin_square){
    if (latin_square == NULL) {
        printf("Verify_Rows_and_Columns - latin_square is NULL\n");
        return 0;
    }

    /* BEGIN MODIFYING CODE HERE */
    int error = 0;//if is 1, then means has error.
    int result = 1;

    //Check row duplicate or not
    char tempValue;
    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            tempValue = *(*(latin_square + row) + col);

            for (int i = col+1; i < n; i++){
                if (tempValue == *( *(latin_square + row) + i) ) {
                    error = 1;
                    break;
                }
            }

            if (error == 1) {
                error = 0;
                result = 0;
                printf("Error in row %d\n", row);
                break;
            }
        }
    }

    //Check col duplicate or not
    error = 0;
    for (int col = 0; col < n; col++) {
        for (int row = 0; row < n; row++) {
            tempValue = *(*(latin_square + row) + col);

            for (int i = 0; i < n; i++) {
                if (tempValue == *( *(latin_square + i) + col)) {
                    error += 1;
                }
            }

            if (error != 1) {
                result = 0;
                error = 0;
                printf("Error in column %d\n", col);
                break;
            }
            error = 0;
        }
    }

    /* END MODIFYING CODE HERE */
    return result;
}

/*
 * This function calls free to allow all memory used by the latin_square
 * verification program to be reclaimed.
 *
 * Note: you will have n+1 calls to free
 *
 * @param n The value of both dimensions of the latin square (i.e. nxn)
 * @param latin_square_in A pointer to the latin square variable in main
 */
void Free_Memory(const size_t n, char **latin_square) {
    /* BEGIN MODIFYING CODE HERE */
    for (int i = 0; i < n; i++){
        free(*(latin_square + i));
    }

    free(latin_square);
    /* END MODIFYING CODE HERE */
}


