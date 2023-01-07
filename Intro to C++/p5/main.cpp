// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <iostream>
#include "Stockholder.h"
#include <fstream>
#include <map>
#include <cstdlib>
#include <ctime>

using namespace std;

void PrintMain();

void PrintUser();


int main() {
    srand((unsigned int) time(NULL));
    int mainMenu_choice = 0;
    int userMenu_choice = 0;
    int stockNumToPurchase = 0;
    int isExist = 0;
    string userName, stockShortName;
//    Stockholder user;
    map<string, Stockholder> stockHolder_container = map<string, Stockholder>();

    ifstream file;
    file.open("stocks.txt");
    int numStocks;
    file >> numStocks;
    map<string, Stock> stock_container = map<string, Stock>();
    Stock stock;
    for (int i = 0; i < numStocks; i++) {
        stock = Stock();
        file >> stock;
        stock_container[stock.short_name] = stock;
    }
    file.close();

    do {
        PrintMain();
        cin >> mainMenu_choice;
        while (!cin || mainMenu_choice < 1 || mainMenu_choice > 4) {
            if (!cin) {
                cin.clear();
                cin.ignore(100, '\n');
            }
            cout << "Please enter a valid menu item: ";
            cin >> mainMenu_choice;
        }

        //Main
        switch (mainMenu_choice) {
            case 1:
                cout << "Please enter your name: ";
                cin >> userName;
                while (!cin) {
                    cin.clear();
                    cin.ignore(100, '\n');
                    cout << "Please enter a valid string: ";
                    cin >> userName;
                }

                isExist = stockHolder_container.count(userName);
                if (isExist == 0) {
                    stockHolder_container[userName] = Stockholder(userName);
                }

                Stockholder *user;
                for (map<string, Stockholder>::iterator it = stockHolder_container.begin();
                     it != stockHolder_container.end(); it++) {
                    if (it->first == userName) {
                        user = &(it->second);
                        it->second.Print();
                        break;
                    }
                }

                while (true) {
                    PrintUser();
                    cin >> userMenu_choice;
                    while (!cin || userMenu_choice < 1 || userMenu_choice > 5) {
                        if (!cin) {
                            cin.clear();
                            cin.ignore(100, '\n');
                        }
                        cout << "Please enter a valid menu item: ";
                        cin >> userMenu_choice;
                    }
                    cout << endl;

                    if (userMenu_choice == 5) {
                        break;
                    } else {
                        switch (userMenu_choice) {
                            case 1://List Available Stocks
                                for (map<string, Stock>::iterator it = stock_container.begin();
                                     it != stock_container.end(); ++it) {
                                    it->second.Print();
                                    cout << "\n";
                                }
                                break;
                            case 2://List My Stocks
                                user->Print();
                                break;
                            case 3://Purchase Stocks
                                cin.clear();
                                cin.ignore(100, '\n');
                                cout << "Please enter a Stock's short name: ";
                                cin >> stockShortName;
                                while (!cin) {
                                    cin.clear();
                                    cin.ignore(100, '\n');
                                    cout << "Please enter a valid stock short name: ";
                                    cin >> stockShortName;
                                }

                                if (stock_container.count(stockShortName) == 0) {
                                    cout<< "Warning: there is no such stock on the stock market that you are looking for.";
                                } else {//else key exists
                                    cin.clear();
                                    cin.ignore(100, '\n');
                                    cout << "Please enter a number of shares of that stock to purchase: ";
                                    cin >> stockNumToPurchase;
                                    bool isPurchase = user->Purchase(&stock_container[stockShortName],
                                                                     stockNumToPurchase);
                                    if (isPurchase) {
                                        cout << "Congratulations on purchasing "
                                             << stockNumToPurchase
                                             << " "
                                             << stock_container[stockShortName].long_name << endl;
                                    } else { cout << "Sorry, you can not afford this purchase."; }
                                }

                                break;

                            case 4://Sell Stocks
                                cin.clear();
                                cin.ignore(100, '\n');
                                cout << "Please enter a Stock's short name: ";
                                cin >> stockShortName;
                                while (!cin) {
                                    cin.clear();
                                    cin.ignore(100, '\n');
                                    cout << "Please enter a valid string: ";
                                    cin >> stockShortName;
                                }

                                if (stock_container.count(stockShortName) == 0) {
                                    cout << "Stock not found" << endl;
                                } else {
                                    cin.clear();
                                    cin.ignore(100, '\n');
                                    cout << "Please enter a number of shares of that stock to sell: ";
                                    cin >> stockNumToPurchase;
                                    bool isSell = user->Sell(stockShortName, stockNumToPurchase);
                                    if (isSell) {
                                        cout << "Congratulations on your sell";
                                    } else {
                                        cout << "Sorry, you don't have enough stocks to sell." << endl;
                                    }
                                }

                                break;
                        }

                        cout << endl;
                        cout << "Please enter a number to print the user menu again: ";
                        cin >> userMenu_choice;
                        while (!cin) {
                            cin.clear();
                            cin.ignore(100, '\n');
                            cout << "Please enter a valid number: ";
                            cin >> userMenu_choice;
                        }
                        cout << endl;

                    }
                }

                break;

            case 2://List Directory
                for (map<string, Stockholder>::iterator it = stockHolder_container.begin();
                     it != stockHolder_container.end(); ++it) {
                    cout << it->first << endl;
                }

                cout << "Please enter a number to print the main menu again: ";
                cin >> userMenu_choice;
                while (!cin) {
                    cin.clear();
                    cin.ignore(100, '\n');
                    cout << "Please enter a valid number: ";
                    cin >> userMenu_choice;
                }
                userMenu_choice = 0;
                cout << endl;
                break;

            case 3://Advance a Day
                for (map<string, Stock>::iterator it = stock_container.begin(); it != stock_container.end(); ++it) {
                    it->second.AdvanceDay();
                }
                break;
        }

    } while (mainMenu_choice != 4);


    return 0;
}


void PrintMain() {
    cout << "Welcome to the Stock Portfolio Manager!" << endl
         << "[1] Log In" << endl
         << "[2] List Directory" << endl
         << "[3] Advance a Day" << endl
         << "[4] Exit program" << endl
         << "Please enter a menu item: ";
}


void PrintUser() {
    cout << "What would you like to do?" << endl
         << "[1] List Available Stocks" << endl
         << "[2] List My Stocks" << endl
         << "[3] Purchase Stocks" << endl
         << "[4] Sell Stocks" << endl
         << "[5] Log Out (return to main menu)" << endl
         << "Please enter a menu item: ";
}
