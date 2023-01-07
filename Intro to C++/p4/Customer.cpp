// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <iostream>
#include <iomanip>
#include "Customer.h"
#include "Drink.h"

using namespace std;

string Customer::GetName() const {
    return this->name;
}

ostream& operator << (ostream& output, Customer& c) {
    output << c.name << " " << c.drink_count << endl;
    string style;

    for (int i = 0; i < c.drink_count; i++) {
        if (c.drinks[i].style == NEAT) {
            style = "NEAT";
        }else if(c.drinks[i].style == ROCKS) {
            style = "ROCKS";
        }else if(c.drinks[i].style == DOUBLE) {
            style = "DOUBLE";
        }else if(c.drinks[i].style == TALL) {
            style = "TALL";
        }

        output << c.drinks[i].name << " " << c.drinks[i].base_price << " " << style << endl;
    }

    return output;
}

istream& operator >> (istream& input, Customer& c){
    string cusName;
    int cusDrinkCount;
    input >> cusName >> cusDrinkCount;

    string drinkName, drinkStyle;
    float drinkPrice;

    for(int i = 0; i < cusDrinkCount; i++){
        input >> drinkName >> drinkPrice >> drinkStyle;

        c.drinks[i].name = drinkName;
        c.drinks[i].base_price = drinkPrice;

        if (drinkStyle == "NEAT") {
            c.drinks[i].style = NEAT;
        }else if(drinkStyle == "ROCKS") {
            c.drinks[i].style = ROCKS;
        }else if(drinkStyle == "DOUBLE") {
            c.drinks[i].style = DOUBLE;
        }else if(drinkStyle == "TALL") {
            c.drinks[i].style = TALL;
        }
    }

    c.drink_count = cusDrinkCount;

    return input;
}

void Customer::Serve(Drink& drink, const STYLE serving_style) {
    if (drink_count < DRINK_LIMIT){
        drink.Prepare(serving_style);
        drinks[drink_count++] = drink;
    }
}

float Customer::TotalTab(float tip_pct)
{
    float price = 0.0;
    for (int i = 0; i < drink_count; i++)
    {
        price += drinks[i].TotalPrice();
    }
    float tax_val = price*TAX_PCT;
    float tip_val = price*tip_pct/100.0;
    return price + tax_val + tip_val;
}

void Customer::Print(const float tip) {
    cout << "Your drinks are:" << endl;
    for (int i = 0; i < drink_count; i++){
        drinks[i].Print();
    }
    cout << "***" << endl << fixed << setprecision(2)
         << left << setw(22) << "Tax:"   << TAX_PCT * 100.0 << "%" << endl
         << left << setw(22) << "Tip:"   << tip             << "%" << endl
         << left << setw(22) << "Total:" << "$" << TotalTab(tip)   << endl;
}

bool Customer::ReachedLimit() {
    return drink_count >= DRINK_LIMIT;
}



