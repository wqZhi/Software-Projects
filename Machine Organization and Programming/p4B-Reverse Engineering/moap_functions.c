// for each of the following functions
// correct the prototype
// write function body
// replace return with the correct return statement
//
// you may write as many helper functions here as you wish

int algorithm_1() {
    int x = 8000;
    int y = 63;
    int sum = x + y;

    return sum;
}

int algorithm_2(int x, int y) {
    if (y <= x){
        return x/y;
    }
    else{
        int sum = x+x+x;
        return sum + y;
    }

}

int helper_3(int val){
    int result = val - 1;
    return result;
}

int algorithm_3(int x, int y) {
    int result = 0;
    int toCompare = y;

    while(0 < toCompare){
        result += x;
        toCompare = helper_3(toCompare);
    }

    return result;
}

int algorithm_4(int x, int *result) {
    int sum = x+x+x;

    *result = sum;

    return *result;
}



