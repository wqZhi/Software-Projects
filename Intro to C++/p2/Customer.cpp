// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <iostream>
#include <iomanip>
#include "Customer.h"
using namespace std;

Customer::Customer() {
    count_drink = 0;
}

void Customer::Serve(const Drink &drink, const STYLE style) {
    if (this->count_drink < drinkLimit) {
        drinkList[count_drink] = drink;
        drinkList[count_drink].Prepare(style);
        this->count_drink++;
    }
    else{
        return;
    }
}

float Customer::TotalTab(const float tip){
    float tax = 0.05;
    float total_amount = 0.0;
    float user_tip = (tip / 100.00) + 1;

    for (int i = 0; i < this->count_drink; i++) {
        total_amount += drinkList[i].TotalPrice();
    }

    total_amount = total_amount * tax + total_amount * user_tip;

    return total_amount;
}

void Customer::Print(const float tip){
    float total_amount = TotalTab(tip);

    for (int i = 0; i < this->count_drink; i++) {
         drinkList[i].Print();
    }

    cout << std::fixed;
    cout << "\t***" << endl;
    cout << "Tax:\t\t\t  5.0%" << endl;
    cout << "Tip:\t\t\t  "<< std::setprecision(1) << tip << "%" << endl;
    cout << "Total:\t\t\t  $"<< std::setprecision(2) << total_amount << endl;
}
