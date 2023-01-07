// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <iostream>
#include <iomanip>
#include "Customer.h"
#include "Drink.h"

using namespace std;

void printMainMenu() {
    cout << "Welcome to Downtown Dairy Bar!" << endl;
    cout << "[1] Order a Drink" << endl;
    cout << "[2] Pay Your Tab" << endl;
    cout << "[3] Exit program" << endl;
    cout << "Please enter a menu item:" << endl;
}

void printDrinkMenu() {
    cout << "[1] Whole Milk" << endl;
    cout << "[2] 2% Milk" << endl;
    cout << "[3] Heavy Cream" << endl;
    cout << "[4] Milkshake" << endl;
    cout << "[5] Malt" << endl;
    cout << "[6] Skim Milk(Water)" << endl;
    cout << "[7] Return to main menu" << endl;
    cout << "Please enter a menu item:" << endl;
}

void printServeMenu() {
    cout << "[1] Straight" << endl;
    cout << "[2] On the Rocks" << endl;
    cout << "[3] Double" << endl;
    cout << "[4] Tall" << endl;
    cout << "Please enter a menu item:" << endl;
}

int main() {
    int userInput;
    float tip;
    Customer user = Customer();

    do {
        printMainMenu();
        cin >> userInput;
        cin.clear();
        cin.ignore(1024, '\n');
        while (!cin || userInput != 1 && userInput != 2 && userInput != 3) {
            cout << "Invalid choice, please input a valid menu item." << endl;
            cin >> userInput;
            cin.clear();
            cin.ignore(1024, '\n');
        }

        if (userInput == 1) {
            cout << "Which Drink Would You Like to Order?" << endl;
            printDrinkMenu();

            cin >> userInput;
            cin.clear();
            cin.ignore(1024, '\n');
            while (!cin || userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4
            && userInput != 5 && userInput != 6 && userInput != 7) {
                cout << "Invalid choice, please input a valid menu item." << endl;
                cin >> userInput;
                cin.clear();
                cin.ignore(1024, '\n');
            }

            Drink user_drink;
            switch (userInput) {
                case 1: //[1] Whole Milk
                    user_drink = Drink("Whole Milk", 2.50);

                    cout << "How Would You Like Your Drink Served?" << endl;
                    printServeMenu();
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    while (!cin || userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4) {
                        cout << "Invalid choice, please input a valid menu item." << endl;
                        cin >> userInput;
                        cin.clear();
                        cin.ignore(1024, '\n');
                    }

                    if (userInput == 1) {
                        user.Serve(user_drink, NEAT);
                    } else if (userInput == 2) {
                        user.Serve(user_drink, ROCKS);
                    } else if (userInput == 3) {
                        user.Serve(user_drink, DOUBLE);
                    } else if (userInput == 4) {
                        user.Serve(user_drink, TALL);
                    }
                    break;

                case 2: //[2] 2% Milk
                    user_drink = Drink("2% Milk", 2.00);

                    cout << "How Would You Like Your Drink Served?" << endl;
                    printServeMenu();
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    while (!cin || userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4) {
                        cout << "Invalid choice, please input a valid menu item." << endl;
                        cin >> userInput;
                        cin.clear();
                        cin.ignore(1024, '\n');
                    }

                    if (userInput == 1) {
                        user.Serve(user_drink, NEAT);
                    } else if (userInput == 2) {
                        user.Serve(user_drink, ROCKS);
                    } else if (userInput == 3) {
                        user.Serve(user_drink, DOUBLE);
                    } else if (userInput == 4) {
                        user.Serve(user_drink, TALL);
                    }
                    break;

                case 3: //[3] Heavy Cream
                    user_drink = Drink("Heavy Cream", 3.50);

                    cout << "How Would You Like Your Drink Served?" << endl;
                    printServeMenu();
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    while (!cin || userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4) {
                        cout << "Invalid choice, please input a valid menu item." << endl;
                        cin >> userInput;
                        cin.clear();
                        cin.ignore(1024, '\n');
                    }

                    if (userInput == 1) {
                        user.Serve(user_drink, NEAT);
                    } else if (userInput == 2) {
                        user.Serve(user_drink, ROCKS);
                    } else if (userInput == 3) {
                        user.Serve(user_drink, DOUBLE);
                    } else if (userInput == 4) {
                        user.Serve(user_drink, TALL);
                    }
                    break;

                case 4: //[4] Milkshake
                    user_drink = Drink("Milkshake", 5.00);

                    cout << "How Would You Like Your Drink Served?" << endl;
                    printServeMenu();
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    while (!cin || userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4) {
                        cout << "Invalid choice, please input a valid menu item." << endl;
                        cin >> userInput;
                        cin.clear();
                        cin.ignore(1024, '\n');
                    }

                    if (userInput == 1) {
                        user.Serve(user_drink, NEAT);
                    } else if (userInput == 2) {
                        user.Serve(user_drink, ROCKS);
                    } else if (userInput == 3) {
                        user.Serve(user_drink, DOUBLE);
                    } else if (userInput == 4) {
                        user.Serve(user_drink, TALL);
                    }
                    break;

                case 5: //[5] Malt
                    user_drink = Drink("Malt", 6.00);

                    cout << "How Would You Like Your Drink Served?" << endl;
                    printServeMenu();
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    while (!cin || userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4) {
                        cout << "Invalid choice, please input a valid menu item." << endl;
                        cin >> userInput;
                        cin.clear();
                        cin.ignore(1024, '\n');
                    }

                    if (userInput == 1) {
                        user.Serve(user_drink, NEAT);
                    } else if (userInput == 2) {
                        user.Serve(user_drink, ROCKS);
                    } else if (userInput == 3) {
                        user.Serve(user_drink, DOUBLE);
                    } else if (userInput == 4) {
                        user.Serve(user_drink, TALL);
                    }
                    break;

                case 6: //[6] Skim Milk
                    user_drink = Drink("Skim Milk", 1.00);

                    cout << "How Would You Like Your Drink Served?" << endl;
                    printServeMenu();
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    while (!cin || userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4) {
                        cout << "Invalid choice, please input a valid menu item." << endl;
                        cin >> userInput;
                        cin.clear();
                        cin.ignore(1024, '\n');
                    }

                    if (userInput == 1) {
                        user.Serve(user_drink, NEAT);
                    } else if (userInput == 2) {
                        user.Serve(user_drink, ROCKS);
                    } else if (userInput == 3) {
                        user.Serve(user_drink, DOUBLE);
                    } else if (userInput == 4) {
                        user.Serve(user_drink, TALL);
                    }
                    break;
            }
        }
        else if (userInput == 2) {// Pay your tab
            cout << "How much they would like to tip?" << endl;
            cin >> tip;
            while (!cin || tip < 0) {
                cin.clear();
                cin.ignore(1024, '\n');
                cout << "Invalid choice, please input a valid menu item." << endl;
                cin >> tip;
            }
            user.Print(tip);
            user = Customer();//resets customer

            cout << "Enter any number to return to menu" << endl;
            cin >> userInput;
            while (!cin) {
                cin.clear();
                cin.ignore(1024, '\n');
                cout << "Invalid choice, please input a valid number to return to menu." << endl;
                cin >> userInput;
            }
        }
        else if (userInput == 3) {// Exit Program
            break;
        }

    } while (true);

    return 0;
}



