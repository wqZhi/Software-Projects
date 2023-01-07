/******************************************************************************
 * @file: functions.c
 *
 * WISC NETID wzhi3
 * CANVAS USERNAME WEIQIAN ZHI
 * WISC ID NUMBER 9082593279
 * OTHER COMMENTS FOR THE GRADER (OPTIONAL)
 *
 * @creator: Weiqian Zhi (wzhi3@wisc.edu)
 * @modified: 10/17/21 Weiqian Zhi (wzhi3@wisc.edu)
 *****************************************************************************/
#include <stdio.h>
#include "functions.h"

// Some macros that may be useful to you
#define MAX_USERNAME_LEN    32
#define MAX_EMAIL_LEN       32
#define MAX_DOMAIN_LEN      64
#define MIN_PASSWORD_LEN    8
#define MAX_PASSWORD_LEN    16
#define NEW_LINE_CHAR       10

// Set this to 1 to enable dbgprintf statements, make sure to set it back to 0
// before submitting!
#define DEBUG               0
#define dbgprintf(...)      if (DEBUG) { printf(__VA_ARGS__); }

/******************************************************************************
 * Helper functions
 *****************************************************************************/

// ADD ANY HELPER FUNCTIONS YOU MIGHT WRITE HERE
// Examples: IsLetter, IsDigit, Length, Find...

/*
 * Check if the array starts with a letter
 *
 * @param arr : The string
 * @return 1 if valid, 0 if not
 */
int isBeginLetter(char arr[]) {
    if (!('A' <= arr[0] && arr[0] <= 'Z') && !('a' <= arr[0] && arr[0] <= 'z')) {
        return 0;//Not begin with letter
    }

    return 1;//Begin with letter
}

/*
 * Check if the array contains with letter or digits
 *
 * @param arr : The string
 * @param len : Size of the input buffer
 * @return 1 if valid, 0 if not
 */
int isOnlyLetterDigits(char arr[], size_t len) {
    for (int i = 0; i < len; i++) {
        if (arr[i] == '\0') {
            break;
        }

        if (!('0' <= arr[i] && arr[i] <= '9')
            && !('A' <= arr[i] && arr[i] <= 'Z')
            && !('a' <= arr[i] && arr[i] <= 'z')) {
            return 0;
        }
    }

    return 1;//contain only letters and digits
}

/*
 * Check if the array size is reach to Maximum or not
 *
 * @param arr : The string
 * @param len : Size of the input buffer
 * @param MaxSize : Required Maximum size of the input
 * @return 1 if valid, 0 if not
 */
int isReachMaxSize(char arr[], size_t len, int MaxSize) {
    int size = 0;
    for (int i = 0; i < len; i++) {
        if (arr[i] == '\0') {
            if (size > MaxSize) {
                return 0;
            } else {
                break;
            }
        }
        size++;
    }
    return 1;
}


/******************************************************************************
 * Verification functions
 *****************************************************************************/

/*
 * A username must begin with a letter [A-Z, a-z], contain 32 characters
 * or less, and  may only consist of letters, underscores, or
 * digits [A-Z, a-z, _, 0-9]. An error message is displayed if any of
 * these conditions are not met. Only print the first applicable error
 * message.
 *
 * @param user : The username string
 * @param len : Size of the username input buffer
 * @return 1 if valid, 0 if not
 */
int Verify_Username(char user[], size_t len) {
    /* BEGIN MODIFYING CODE HERE */
    dbgprintf("This line only prints if DEBUG is set to 1\n");

    //Check invalid username starting character
    if (isBeginLetter(user) == 0) {
        printf(ERROR_01_USER_START_INVALID);
        return 0;
    }

    //Check Max 32 charcters
    if (isReachMaxSize(user, len, MAX_USERNAME_LEN) == 0) {
        printf(ERROR_02_USER_LEN_INVALID);
        return 0;
    }

    //Check Invalid character in username
    for (int i = 0; i < len; i++) {
        if (user[i] == '\0') {
            break;
        }

        if (!('0' <= user[i] && user[i] <= '9')
            && !('A' <= user[i] && user[i] <= 'Z')
            && !('a' <= user[i] && user[i] <= 'z')
            && user[i] != 95) {
            printf(ERROR_03_USER_CHARS_INVALID);
            return 0;
        }
    }

    printf(SUCCESS_1_USER);
    return 1;
}

/*
 * An email address has four parts:
 *      name
 *          exists
 *          must start with letter
 *          max 32 characters
 *          may contain only letters and digits
 *      @ symbol
 *          exists
 *      domain name
 *          exists
 *          max of 64 characters
 *          composed of one or more subdomains separated by .
 *          subdomain must begin with a letter
 *          subdomains may contain only letters and digits
 *      top-level domain
 *          must be [.edu, .com, .net]
 *
 * If the email address contains one or more errors print only the first
 * applicable error from the list.
 *
 * Note this task is based on a real world problem and may not be the best
 * order to approach writing the code.
 *
 * @param email : The email string
 * @param len : Size of the email input buffer
 * @return 1 if valid, 0 if not
 */
int Verify_Email(char email[], size_t len) {
    //Declare an at_pointer variable and search the string for the @ symbol.
    char *pAt = email;
    if (email[0] != '\0') {
        for (int i = 0; i < len; i++) {
            if (email[i] == '\0') {
                break;
            }

            if (email[i] == '@') {
                pAt = &email[i];
                break;
            }
        }
    }

    //Declare an end_pointer.
    char *pEnd = NULL;
    if (email[0] != '\0') {
        for (int i = 0; i < len; i++) {
            if (email[i] == '\0') {
                pEnd = &email[i - 1];
                break;
            }

            if (i == (len - 1)) {
                pEnd = &email[i];
                break;
            }
        }
    }

    //Check has top domain or not
    int hasAT = -1;// hasAt = -1 when there don't have @; otherwise, hasAt = 1.
    if (*pAt == '@') {
        hasAT = 1;
    }
    int hasTopDomain = -1;// hasDomain = -1 when there don't have (.edu or .com or .net)); otherwise, hasDomain = 1.
    char *pDot_TopDomain = NULL;
    if (pEnd != NULL && (*(pEnd - 4) == '.' || *(pEnd - 3) == '.')) {
        if (*(pEnd) == '\0') {
            if ((*(pEnd - 3) == 'e' && *(pEnd - 2) == 'd' && *(pEnd - 1) == 'u')
                || (*(pEnd - 3) == 'c' && *(pEnd - 2) == 'o' && *(pEnd - 1) == 'm')
                || (*(pEnd - 3) == 'n' && *(pEnd - 2) == 'e' && *(pEnd - 1) == 't')) {

                pDot_TopDomain = (pEnd - 4);
                hasTopDomain = 1;
            }
        } else {
            if ((*(pEnd - 2) == 'e' && *(pEnd - 1) == 'd' && *(pEnd) == 'u')
                || (*(pEnd - 2) == 'c' && *(pEnd - 1) == 'o' && *(pEnd) == 'm')
                || (*(pEnd - 2) == 'n' && *(pEnd - 1) == 'e' && *(pEnd) == 't')) {

                pDot_TopDomain = (pEnd - 3);
                hasTopDomain = 1;
            }
        }
    }

    if ((hasAT == -1 && hasTopDomain == 1) || (hasAT == -1 && hasTopDomain == -1)) {
        char name_email[len];
        int i;
        if (hasTopDomain == 1) {
            for (i = 0; i < (pDot_TopDomain - email); i++) {
                name_email[i] = email[i];
            }
            name_email[i] = '\0';
        } else {
            for (i = 0; i < (pEnd + 1 - email); i++) {
                name_email[i] = email[i];
            }
            name_email[i] = '\0';
        }

        //Check name missing.
        if (name_email[0] == '\0') {
            printf(ERROR_04_EMAIL_MISSING_NAME);
            return 0;
        }

        //Check Name must begin with letter.
        if (isBeginLetter(name_email) == 0) {
            printf(ERROR_05_EMAIL_START_INVALID);
            return 0;
        }

        //Check name must have fewer than 32 characters
        if (isReachMaxSize(name_email, len, MAX_EMAIL_LEN) == 0) {
            printf(ERROR_06_EMAIL_NAME_LEN_INVALID);
            return 0;
        }

        //Check invalid character in name (want contains only letters and digits)
        if (isOnlyLetterDigits(name_email, len) == 0) {
            printf(ERROR_07_EMAIL_NAME_CHARS_INVALID);
            return 0;
        }

        printf(ERROR_08_EMAIL_MISSING_SYMBOL); // example mike.wisc.edu
        return 0;

    }
    else if ((hasAT == 1 && hasTopDomain == 1) || (hasAT == 1 && hasTopDomain == -1)) {
        char name_email[len];
        int size_name = 0;
        for (int i = 0; i < (pAt - email); i++) {
            name_email[i] = email[i];
            size_name++;
        }
        name_email[size_name] = '\0';

        //Check name missing.
        if (name_email[0] == '\0') {
            printf(ERROR_04_EMAIL_MISSING_NAME);
            return 0;
        }

        //Check Name must begin with letter.
        if (isBeginLetter(name_email) == 0) {
            printf(ERROR_05_EMAIL_START_INVALID);
            return 0;
        }

        //Check name must have fewer than 32 characters
        if (isReachMaxSize(name_email, len, MAX_EMAIL_LEN) == 0) {
            printf(ERROR_06_EMAIL_NAME_LEN_INVALID);
            return 0;
        }

        //Check invalid character in name (want contains only letters and digits)
        if (isOnlyLetterDigits(name_email, len) == 0) {
            printf(ERROR_07_EMAIL_NAME_CHARS_INVALID);
            return 0;
        }

        int size_email = 0;
        for (int i = 0; i < len; i++) {
            if (email[i] == '\0') {
                break;
            }
            size_email++;
        }

        char *pDomainStart = pAt + 1;
        char name_domain[len];
        if (hasTopDomain == 1) {
            int index = 0;
            for (char *p_iterator = pAt + 1; p_iterator < (pEnd - 3); p_iterator++) {
                name_domain[index] = *p_iterator;
                index++;
            }
            name_domain[index] = '\0';
        } else {
            int index = 0;
            for (char *p_iterator = pAt + 1; p_iterator <= pEnd; p_iterator++) {
                name_domain[index] = *p_iterator;
                index++;
            }
            name_domain[index] = '\0';
        }

        if (isReachMaxSize(name_domain, len, 0) != 0) {
            printf(ERROR_09_EMAIL_MISSING_DOMAIN);
            return 0;
        }

        //Check Maximum of 64 characters in domain
        if (isReachMaxSize(name_domain, len, MAX_DOMAIN_LEN) == 0) {
            printf(ERROR_10_EMAIL_DOMAIN_LEN_INVALID);
            return 0;
        }

        //Check Domain begin with letter
        if (isBeginLetter(pDomainStart) == 0) {
            printf(ERROR_11_EMAIL_DOMAIN_START_INVALID);
            return 0;
        }
        else {
            char tempArray[MAX_DOMAIN_LEN];
            char *pDStart = pAt + 1;
            char *pDEnd = pAt + 2;

            for (int i = 0; i < size_email; i++) {
                tempArray[0] = *pDStart;
                if (tempArray[0] == '\0'){
                    break;
                }
                if (*pDEnd != '.') {
                    for (int j = 1; j < size_email; j++) {
                        tempArray[j] = *(pDEnd);
                        pDEnd = pDEnd + 1;
                        if (*pDEnd == '.' || pDEnd ==pEnd+1) {
                            break;
                        }
                    }

                    if (isBeginLetter(tempArray) == 1) {//Begin with a letter
                        if (isOnlyLetterDigits(tempArray, size_email) == 1) {//contain only letters and digits
                            if (pDEnd == pEnd+1) {
                                break;
                            } else if (hasTopDomain == 1 && pDEnd == (pEnd - 3)) {
                                break;
                            }
                            else {
                                for (int k = 0; k < 64; k++) {
                                    tempArray[k] = 0;
                                }

                                pDStart = pDEnd + 1;
                                pDEnd = pDStart + 1;
                                continue;
                            }
                        } else {
                            printf(ERROR_12_EMAIL_DOMAIN_CHARS_INVALID);
                            return 0;
                        }
                    } else {
                        printf(ERROR_11_EMAIL_DOMAIN_START_INVALID);
                        return 0;
                    }

                } else {
                    if (pDEnd == (pEnd - 3)) {
                        if (isBeginLetter(tempArray) == 1) {
                            break;
                        } else {
                            printf(ERROR_11_EMAIL_DOMAIN_START_INVALID);
                            return 0;
                        }
                    } else {
                        if (isBeginLetter(tempArray) != 1) {
                            printf(ERROR_11_EMAIL_DOMAIN_START_INVALID);
                            return 0;
                        }

                        for (int k = 0; k < 64; k++) {
                            tempArray[k] = 0;
                        }
                        pDStart = pDEnd + 1;
                        pDEnd = pDStart + 1;
                        continue;
                    }
                }

            }
        }
    }

    if (hasTopDomain == -1) {
        printf(ERROR_13_TOP_LEVEL_DOMAIN_INVALID);
        return 0;
    }

    printf(SUCCESS_2_EMAIL);
    return 1;
}

/*
 * The following password requirements must be verified:
 *  - May use any character except spaces (i.e., "my password" is invalid)
 *  - Must contain at least 8 characters (i.e., "Password" has 8 characters
 *    and is valid)
 *  - May have at most 16 characters (i.e., "1234567890Abcdef" has 16
 *    characters and is valid)
 *  - Must contain at least one upper case character [A-Z]
 *  - Must contain at least one lower case character [a-z]
 *
 * @param pwd : The original password string
 * @param len : Size of the original password input buffer
 * @return 1 if valid, 0 if not
 */
int Verify_Password(char pwd[], size_t len) {
    //Check Password may not contain spaces
    for (int i = 0; i < len; i++) {
        if (pwd[i] == ' ') {
            printf(ERROR_14_PWD_SPACES_INVALID);
            return 0;
        }

        if ((pwd[i] == '\0') || (i == (len - 1))) {
            break;
        }
    }

    //Compute Size
    int pwdSize = 0;
    for (int i = 0; i < len; i++) {
        if (pwd[i] == '\0') {
            pwdSize = i;
            break;
        } else if (i == (len - 1)) {
            pwdSize = i + 1;
        }
    }

    //Check Password must have at least 8 characters
    if (pwdSize < MIN_PASSWORD_LEN) {
        printf(ERROR_15_PWD_MIN_LEN_INVALID);
        return 0;
    }

    //Check Password may have at most 16 character
    if (pwdSize > MAX_PASSWORD_LEN) {
        printf(ERROR_16_PWD_MAX_LEN_INVALID);
        return 0;
    }

    //Check Password must contain at least one upper case character
    int upperCount = 0;
    for (int i = 0; i < pwdSize; i++) {
        if (('A' <= pwd[i] && pwd[i] <= 'Z')) {
            upperCount++;
            break;
        }
    }
    if (upperCount == 0) {
        printf(ERROR_17_PWD_MIN_UPPER_INVALID);
        return 0;
    }

    //Check Password must contain at least one lower case character
    int lowerCount = 0;
    for (int i = 0; i < pwdSize; i++) {
        if (('a' <= pwd[i] && pwd[i] <= 'z')) {
            lowerCount++;
            break;
        }
    }
    if (lowerCount == 0) {
        printf(ERROR_18_PWD_MIN_LOWER_INVALID);
        return 0;
    }

    return 1;
}

/*
 * Original Password and the Reentered Password must match
 *
 * @param pwd1 : The original password string
 * @param len1 : Size of the original password input buffer
 * @param pwd2 : The reentered password string
 * @param len2 : Size of the renetered password input buffer
 * @return 1 if valid, 0 if not
 */
int Verify_Passwords_Match(char pwd1[], size_t len1, char pwd2[], size_t len2) {
    //Compute Size
    int pwd_1_Size = 0;
    for (int i = 0; i < len1; i++) {
        if (pwd1[i] == '\0') {
            pwd_1_Size = i;
            break;
        } else if (i == (len1 - 1)) {
            pwd_1_Size = i + 1;
        }
    }

    int pwd_2_Size = 0;
    for (int i = 0; i < len2; i++) {
        if (pwd2[i] == '\0') {
            pwd_2_Size = i;
            break;
        } else if (i == (len2 - 1)) {
            pwd_2_Size = i + 1;
        }
    }

    if (pwd_1_Size == pwd_2_Size) {
        for (int i = 0; i < pwd_1_Size; i++) {
            if (pwd1[i] != pwd2[i]) {
                printf(ERROR_19_PWD_MATCH_INVALID);
                return 0;
            }
        }
    } else {
        printf(ERROR_19_PWD_MATCH_INVALID);
        return 0;
    }

    printf(SUCCESS_3_PASSWORDS);
    return 1;
}

/******************************************************************************
 * I/O functions
 *****************************************************************************/

/*
 * Prompts user with an input and reads response from stdin
 *
 * @param message : Prompt displayed to the user
 * @param data : char array to hold user repsonse
 * @param MAX_LENGTH : Size of user response input buffer
 */
void Get_User_Data(char *message, char *data, const int MAX_LENGTH) {
    printf("%s", message);
    fgets(data, MAX_LENGTH, stdin);

    for (int i = 0; i < MAX_LENGTH; i++) {
        if (data[i] == '\n') {
            data[i] = '\0';
            break;
        }
    }

    return;
}
