// name: Weiqian Zhi
// Wisc ID: wzhi3

#include "Drink.h"
#include <iostream>
#include <iomanip>
using namespace std;

Drink::Drink() {
}

Drink::Drink(string _name, float _price, STYLE _style) {
    this ->name = _name;
    this ->price = _price;
    this ->style = _style;
}

Drink::Drink(const Drink& copy_from){
    this ->name = copy_from.name;
    this ->price = copy_from.price;
    this ->style = copy_from.style;
}

void Drink::Prepare(const STYLE serving_style) {
    this ->style = serving_style;
}

float Drink::TotalPrice(){
    STYLE drink = this->style;
    switch (drink) {
        case NEAT:
            break;
        case ROCKS:
            this->price += 0.25;
            break;
        case DOUBLE:
            this->price = price * 2.0;
            break;
        case TALL:
            this->price += 1;
            break;
    }

    return this->price;
}

void Drink::Print(){
    cout << std::fixed;

    string style;
    STYLE drink = this->style;
    switch (drink) {
        case NEAT:
            style = "straight";
            break;
        case ROCKS:
            style = "on the rocks";
            break;
        case DOUBLE:
            style = "double";
            break;
        case TALL:
            style = "tall";
            break;
    }

    cout << this->name << " (" << style << "):" << "\t $" << std::setprecision(2) << this->price << endl;
}


