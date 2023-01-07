#include <stdlib.h>
#include <printf.h>

int main() {
    int N = 3;
    int b = 16;
    int x = 0;
    for (int i = 0; i < N; i++)
        x++;

    b = b >> x;
    printf("%d", b);
    return 0;
}