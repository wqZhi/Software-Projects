#include <string>
// if you need any additional includes, add them here.

#ifndef DRINK_HEADER
#define DRINK_HEADER
enum STYLE {NEAT, ROCKS, DOUBLE, TALL};

using namespace std;

struct Drink {
    // data and functions should be added here

    //default constructor
    Drink();

    Drink(string _name, float _price, STYLE _style = NEAT);
    Drink(const Drink& copy_from);
    void Prepare(const STYLE serving_style);
    float TotalPrice();
    void Print();

    // you can add a private section for data if desired.
private:
    string name;
    float price;
    STYLE style;
};
#endif

