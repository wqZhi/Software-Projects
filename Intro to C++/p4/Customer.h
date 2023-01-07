// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef CS368A4_CUSTOMER_H
#define CS368A4_CUSTOMER_H

#include <string>
#include "Drink.h"
// if you need any additional includes, add them here.

#ifndef CUSTOMER_HEADER
#define CUSTOMER_HEADER
const int DRINK_LIMIT = 10;
const float TAX_PCT = 0.05;

class Customer {
private:
    Drink drinks[DRINK_LIMIT];
    int drink_count;
    string name;
public:
    Customer() { drink_count = 0; } // just writing the constructor inline, since it's a one-liner.
    Customer(string _name) {name = _name; drink_count = 0;};

    string GetName() const;
    void Serve(Drink& drink, const STYLE style);
    float TotalTab(const float tip);
    void Print(const float tip);

    friend ostream& operator << (ostream& output, Customer& c);
    friend istream& operator >> (istream& input, Customer& c);

    bool ReachedLimit(); // adding an extra function to let us print a message when we reach the limit, NOT required.
};
#endif

#endif //CS368A4_CUSTOMER_H
