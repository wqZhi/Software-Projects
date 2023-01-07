// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <iostream>
#include "Customer.h"
#include "Drink.h"
#include <fstream>

using namespace std;

enum MAIN_MENU {
    ORDER = 1, PAY = 2, SAVETAB = 3, LOADTAB = 4, EXIT = 5
};
enum DRINK_MENU {
    WHOLE = 1, TWO = 2, HEAVY = 3, MILKSHAKE = 4, MALT = 5, SKIM = 6, RETURN = 7
};

void PrintMain();

void PrintDrinks(Drink menu[], int count);

void PrintStyles();

int main() {
    int input = 0;
    int drink_choice = 0;
    int style_choice = 0;
    float tip;
    string customerName;

    ifstream file;
    file.open("menu.txt");
    int menu_numDrinks;
    file >> menu_numDrinks;
    Drink *menu = new Drink[menu_numDrinks];
    string style;
    for (int i = 0; i < menu_numDrinks; i++) {
        file >> menu[i].name >> menu[i].base_price >> style;
    }
    file.close();

    Drink next_drink;

    cout << "Please enter a you name: ";
    cin >> customerName;
    while (!cin) {
        cin.clear(); cin.ignore(100, '\n');
        cout << "Please enter a valid string: ";
        cin >> customerName;
    }
    Customer customer = Customer(customerName);

    do {
        PrintMain();
        cin >> input;
        while (!cin || input < 1 || input > 5) {
            if (!cin) {cin.clear();cin.ignore(100, '\n');}
            cout << "Please enter a valid menu item: ";
            cin >> input;
        }
        // now we have good input, handle the cases:
        switch (input) {
            case ORDER:
                if (customer.ReachedLimit()) {
                    cout << "You've reached your limit!" << endl;
                } else {
                    PrintDrinks(menu, menu_numDrinks);

                    cin >> drink_choice;
                    while (!cin || drink_choice < 1 || drink_choice > menu_numDrinks+1) {
                        if (!cin) {
                            cin.clear();
                            cin.ignore(100, '\n');
                        }
                        cout << "Please enter a valid menu item: ";
                        cin >> drink_choice;
                    }

                    if (drink_choice == menu_numDrinks+1){
                        break;
                    }

                    PrintStyles();

                    cin >> style_choice;
                    while (!cin || style_choice < 1 || style_choice > 4) {
                        if (!cin) {
                            cin.clear();
                            cin.ignore(100, '\n');
                        }
                        cout << "Please enter a valid menu item: ";
                        cin >> style_choice;
                    }

                    next_drink = Drink(menu[drink_choice - 1]); // copy a drink from the menu.
                    switch (style_choice) {
                        case NEAT:
                            customer.Serve(next_drink, NEAT);
                            break;
                        case ROCKS:
                            customer.Serve(next_drink, ROCKS);
                            break;
                        case DOUBLE:
                            customer.Serve(next_drink, DOUBLE);
                            break;
                        case TALL:
                            customer.Serve(next_drink, TALL);
                            break;
                    }
                }
                break;

            case PAY:
                cout << "Please enter your tip percent (%): ";
                cin >> tip;
                while (!cin || tip < 0.0) {
                    if (!cin) {cin.clear();cin.ignore(100, '\n');}
                    cout << "Please enter a valid tip percent (%): ";
                    cin >> tip;
                }
                customer.Print(tip); // divide tip by 100 to go from percent to proportion
                customer = Customer(); // reset after paying tab.
                break;

            case SAVETAB: {
                ifstream f;
                ofstream file;
                string tempTxt = customerName + ".txt";
                f.open(tempTxt);
                if (!f) {
                    file.open(tempTxt, fstream::in | fstream::out | fstream::trunc);
                    file << customer;
                }
                else {
                    file.open(tempTxt);
                    file << customer;
                }

                f.close();
                file.close();

                string currentName;
                cout << "Please enter a you name: ";
                cin >> currentName;
                while (!cin) {
                    cin.clear(); cin.ignore(100, '\n');
                    cout << "Please enter a valid string: ";
                    cin >> currentName;
                }

                if (currentName != customerName) {
                    customerName = currentName;
                }

                customer = Customer(customerName);

                break;
            }

            case LOADTAB: {
                ifstream fToLoad;
                string tempTXT = customerName + ".txt";
                fToLoad.open(tempTXT);
                if (!fToLoad) {
                    cout << "There is no such file. Cannot Load" << endl;
                } else {
                    ifstream cFile;
                    cFile.open(tempTXT);
                    cFile >> customer;
                    cFile.close();
                }

                fToLoad.close();
                break;
            }

            case EXIT:
                break;
        }

    } while (input != EXIT);

    return 0;
}

void PrintMain() {
    cout << "[" << ORDER << "] Order a Drink" << endl
         << "[" << PAY << "] Pay Your Tab" << endl
         << "[" << SAVETAB << "] Save Your Tab" << endl
         << "[" << LOADTAB << "] Load Your Tab" << endl
         << "[" << EXIT << "] Exit" << endl
         << "Please enter a menu item: ";
}

void PrintDrinks(Drink menu[], int count) {
    for (int i = 0; i < count; ++i) {
        cout << "[" << i+1 << "] " << menu[i].name << endl;
    }
    cout << "[" << count + 1 << "] Return to main menu" << endl
         << "Please enter a menu item: ";
}

void PrintStyles() {
    cout << "[" << NEAT << "] Neat" << endl
         << "[" << ROCKS << "] On the Rocks" << endl
         << "[" << DOUBLE << "] Double" << endl
         << "[" << TALL << "] Tall" << endl
         << "Please enter a menu item: ";
}
