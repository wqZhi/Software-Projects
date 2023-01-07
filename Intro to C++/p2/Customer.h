#include <string>
#include "Drink.h"
// if you need any additional includes, add them here.

#ifndef CUSTOMER_HEADER
#define CUSTOMER_HEADER
const int drinkLimit = 10;

class Customer {
private:
    Drink drinkList[drinkLimit];
    int count_drink;
    // private data and functions should be added here
public:
    //default constructor
    Customer();
    void Serve(const Drink& drink, const STYLE style);
    float TotalTab(const float tip);
    void Print(const float tip);
    // public data and functions should be added here
};
#endif

