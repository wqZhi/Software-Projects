#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>

char *path[100];

char error_message[30] = "An error has occurred\n";

void print_error_msg() {
    write(STDERR_FILENO, error_message, strlen(error_message));
}


int slice_string(char *string, char *tokens[]) {
    int i = 0;
//    strtok(string, "\n");
    while( (tokens[i] = strsep(&string," ")) != NULL ){
        i++;
    }
    tokens[i+1] = NULL;

    return i;
}

void change_directory(char * token[]) {
    if(chdir(token[0]) == -1){
//        printf("not changed");
        exit(1);
    }
}

void update_path(char * tokens[]) {
    int i = 0;
    while(1) {
        if(tokens[i] == NULL) { //i+1 need modify
            path[i] = NULL;
            break;
        }
        path[i] = (char *) malloc(strlen(tokens[i]) +1);
        strcpy(path[i], tokens[i]);
        i++;
    }

}


char *trimwhitespace(char *str){
    char *end;

    // Trim leading space
    while(isspace((unsigned char)*str)) str++;

    if(*str == 0)  // All spaces?
        return str;

    // Trim trailing space
    end = str + strlen(str) - 1;
    while(end > str && isspace((unsigned char)*end)) end--;

    // Write new null terminator character
    end[1] = '\0';

    return str;
}

void start_redirection(char *dest){
    int fd = open(dest, O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
    dup2(fd, 1);
    dup2(fd, 2);
    close(fd);
}

int detect_redirection(char *p){
    int count=0;
    while(*p != '\0'){
        if (*p == '>'){
            count++;
        }
        p++;
    }

    if (count >= 1) {
        return 1;
    }
    return -1;
}

int check_redir_duplicate_symbols (char *p){
    int count=0;
    while(*p != '\0'){
        if (*p == '>'){
            count++;
        }

        if (count > 1) {
            return -1;
        }
        p++;
    }

    return 1;
}

int is_has_value(char *ptr[]){
    int index = 0;
    while(1) {
        if (ptr[index] == NULL){
            break;
        }
        index++;
    }

    if (index >= 1){
        return 1;
    }
    return -1;
}

int perprocess(int userInput_len, char *userInput_tokens[], int redirection_mode, char *redirection_file) {
    if (strcmp(userInput_tokens[0], "cd") == 0) {
        if (userInput_len == 1 || userInput_len > 2) { // illegal format
            print_error_msg();
            return -1;
        }

        change_directory(userInput_tokens + 1);
        return -1;

    } else if (strcmp(userInput_tokens[0], "exit") == 0) {
        if (userInput_len > 1) {
            print_error_msg();
            return -1;
        }
        exit(0);
    } else if (strcmp(userInput_tokens[0], "path") == 0) {
        update_path(userInput_tokens + 1);
        return -1;
    } else {
        int rc = fork();

        if (rc < 0) { // fork failed; exit
            print_error_msg();
            exit(1);
        } else if (rc == 0) {
            int index = 0;
            while (1) {
                if (path[index] == NULL) { break; }

                char temp_path[20];
                strcpy(temp_path, path[index]);
                strcat(temp_path, "/");
                strcat(temp_path, userInput_tokens[0]);

                index++;
                if (access(temp_path, X_OK) != 0) {
                    return -1;
                }

                if (redirection_mode) {
                    start_redirection(redirection_file);

                }

                char *myargs[3];
                myargs[0] = strdup(temp_path);

                if (execv(myargs[0], userInput_tokens) == -1) {
                    print_error_msg();
                    return -1;
                }
            }
            print_error_msg();
            exit(0);
        }
        int pid;
        int wc = wait(&pid);
        int ret_code = WEXITSTATUS(pid);
        return ret_code;
    }

    return -1;
}

int main(int argc, char *argv[]) {
    FILE *file;
    size_t bufSize = 256;
    char *userInput = malloc(sizeof(char) * bufSize);
    char *userInput_tokens[100];
    path[0] = strdup("/bin");
    path[1] = NULL;
    int retVal;
    int userInput_len;
    int redirection_mode = 0;
    char *redirection_file;

    if (argc == 1) {
        file = stdin;
    } else if (argc == 2) {
        file = fopen(argv[1], "r");

        if (file == NULL) {
            print_error_msg();
            exit(1);
        }
    } else {
        print_error_msg();
        exit(1);
    }

    while (1) {
        if (argc == 1) {
            printf("wish> ");
            // If successful, getline() returns the number of characters that are read, including the newline character, but not including the terminating null byte (‘\0’).
            retVal = getline(&userInput, &bufSize, stdin);
        } else {
            retVal = getline(&userInput, &bufSize, file);
        }

        if (retVal == -1) {
            exit(0);  // We received an EOF
        } else if (retVal == 1) { // The newline character "\t"
            continue;
        }

        userInput = strsep(&userInput, "\n");
        userInput = trimwhitespace(userInput);
        if (userInput[0] == '\0' || strlen(userInput) == 0) {
            continue;
        }

        //-------------(if statement) start
        int alert_fault_if_statement = 0;
        char *ptr_line = malloc(sizeof (char) * bufSize);
        strcpy(ptr_line, userInput);
        userInput_len = slice_string(ptr_line, userInput_tokens);

        //iterate through the loop
        if (strcmp(userInput_tokens[0], "if") == 0){
            //check format(head, tail)
            if (strcmp(userInput_tokens[userInput_len-1], "fi") != 0) {
                print_error_msg();
                continue;
            }

            char *cmd[10];
            char *op[10];
            int constant;

            int i=0;
            int if_num=0;
            int fi_num=0;
            while(1) {
                if (userInput_tokens[i]==NULL){
                    break;
                }

                //get cmd and op and constant
                int index =0;

                int j;
                for (j = i; j < userInput_len; j++) {
                    cmd[index] = (char *) malloc(strlen(userInput_tokens[j+1]) +1);
                    strcpy(cmd[index], userInput_tokens[j+1]);
                    index++;
                    cmd[index] = NULL;

                    if (strcmp(userInput_tokens[j+2], "==") == 0 || strcmp(userInput_tokens[j+2], "!=") == 0){
                        //store op
                        op[0] = (char *) malloc(strlen(userInput_tokens[j+2]) +1);
                        strcpy(op[0], userInput_tokens[j+2]);
                        op[1] = NULL;

                        constant = (*userInput_tokens[j+3] - '0');

                        break;
                    }
                }

                //check cmd, op, constant are not null, then call execv(cmd) -> op constant
                int success = 0;
                if (is_has_value(cmd) && is_has_value(op)) {
                    if (strcmp(op[0], "==") == 0) {
//                        printf("Here\n");
//                        print_token(cmd);
//                        exit(0);
                        int retNum = perprocess(userInput_len, cmd, redirection_mode, redirection_file);
                        if (retNum == constant) {
                            success = 1;
                        }
//                        printf("%d\n", retNum);
//                        perprocess(userInput_len, userInput_tokens, redirection_mode, redirection_file);
                    }

                }

                if (success){
                    i = j + 2+ 1;// become then
                    if (strcmp(userInput_tokens[i], "then") == 0 && strcmp(userInput_tokens[i+1], "hello") == 0){
                        perprocess(userInput_len, cmd, redirection_mode, redirection_file);
                        break;
                    }
                    else if (strcmp(userInput_tokens[i+1], "if") == 0 || strcmp(userInput_tokens[i+1], "cd") == 0){
                        i++;
                        continue;
                    }

                }
                else{
                    break;
                }


            }

            /**
             * if r23 == 23 then hello
             * if r23 == 23 then if ret_arg_0 10 != 10 then hello fi fi
             *
             * loop 0 to n
             *    next is  cmd and op, check cmd evecv(cmd), check op store op, -> check with constant store it  is True or not
             *    check then or not -> check hello or if ,  case 1: if
             *                                              case 2: hello, if all true and #if==#fi. print hello.
             */
        }



        //-------------(redirection) start
        char *p = userInput;
        char *arg_token;
        if (detect_redirection(p) == 1) {
            p = userInput;
            if (check_redir_duplicate_symbols(p) == 1) {
                arg_token = strsep(&userInput, ">");
                arg_token = trimwhitespace(arg_token);
                redirection_file = strsep(&userInput, ">");
                redirection_file = trimwhitespace(redirection_file);
//            printf("first (%s)", arg_token);
//            printf("second (%s)", redirection_file);
//            exit(0);
                if (*redirection_file == '\0') {
                    print_error_msg();
                    continue;
                }
//            printf("%s\n", redirection_file);
                //exit(0);
                int redir_mulit_file_alert = 0;
                char *look_redir_right = redirection_file;
                for (int i = 0; i < strlen(look_redir_right); i++) {
                    if (look_redir_right[i] == ' ') {
                        redir_mulit_file_alert++;
                    }
                }

                int redir_no_arg_alert = 0;
                char *look_redir_left = arg_token;
                if (look_redir_left[0] == '\0' || strlen(look_redir_left) == 0) {
                    redir_no_arg_alert = 1;
                }

                if (redir_mulit_file_alert || redir_no_arg_alert) {
//                printf("here");
//                exit(0);
                    print_error_msg();
                    continue;
                }

                redirection_mode = 1;
            } else {
                print_error_msg();
                continue;
            }
        }
        //-------------(redirection) end

        if (redirection_mode) {
            userInput = arg_token;
        }

        userInput_len = slice_string(userInput, userInput_tokens);


        int condition = perprocess(userInput_len, userInput_tokens, redirection_mode, redirection_file);
        if (condition == -1) {
            continue;
        }

    }
    fclose(file);

    return 0;
}