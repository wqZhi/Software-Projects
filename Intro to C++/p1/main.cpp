// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <iostream>
#include <iomanip>
#include "functions.h"
using namespace std;

void printMainMenu() {
    cout << "Welcome to Geometry Helper" << endl;
    cout << "[1] Calculate a 2D Area" << endl;
    cout << "[2] Calculate a 2D Area" << endl;
    cout << "[3] Exit program" << endl;
    cout << "Please enter a menu item:" << endl;
}

void print2DMenu() {
    cout << "1. 2D Menu" << endl;
    cout << "Choose an Area to Compute" << endl;
    cout << "[1] Circle" << endl;
    cout << "[2] Triangle" << endl;
    cout << "[3] Rectangle" << endl;
    cout << "[4] Return to main menu" << endl;
    cout << "Please enter a menu item:" << endl;
}

void print3DMenu() {
    cout << "2. 3D Menu" << endl;
    cout << "[1] Sphere" << endl;
    cout << "[2] Cone" << endl;
    cout << "[3] Box" << endl;
    cout << "[4] Return to main menu" << endl;
    cout << "Please enter a menu item:" << endl;
}

float checkValueValid(float value){
    while(value < 0) {
        cout << "Invalid value, please input a valid value." << endl;
        cin >> value;
        cin.clear();
        cin.ignore(1024, '\n');
    }

    return value;
}

int main() {
    int userInput;
    float radius, width, height, length;

    do{
        printMainMenu();
        cin >> userInput;
        cin.clear();
        cin.ignore(1024, '\n');

        while (userInput != 1 && userInput != 2 && userInput != 3){
            cout << "Invalid choice, please input a valid menu item." << endl;
            cin >> userInput;
            cin.clear();
            cin.ignore(1024, '\n');
        }

        switch (userInput){
            case 1: //Calculate a 2D Area
                print2DMenu();

                cin >> userInput;
                cin.clear();
                cin.ignore(1024, '\n');

                while (userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4){
                    cout << "Invalid choice, please input a valid menu item." << endl;
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                }

                if (userInput == 1) {
                    //Circle
                    cout << std::fixed;
                    cout << std::setprecision(2);

                    cout << "Please enter the radius of the circle: " << endl;
                    cin >> radius;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    radius = checkValueValid(radius);

                    cout << "The area of the circle is: " << AreaOfCircle(radius) << endl;
                }
                else if (userInput == 2){
                    //Triangle
                    cout << std::fixed;
                    cout << std::setprecision(2);

                    cout << "Please enter the width of the triangle: " << endl;
                    cin >> width;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    width = checkValueValid(width);
                    cout << "Please enter the height of the triangle: " << endl;
                    cin >> height;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    height = checkValueValid(height);

                    cout << "The area of the triangle is: " << AreaOfTriangle(width, height) << endl;
                }
                else if (userInput == 3){
                    // Rectangle
                    cout << std::fixed;
                    cout << std::setprecision(2);

                    cout << "Please enter the width of the rectangle: " << endl;
                    cin >> width;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    width = checkValueValid(width);
                    cout << "Please enter the height of the rectangle: " << endl;
                    cin >> height;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    height = checkValueValid(height);

                    cout << "The area of the rectangle is: " << AreaOfRectangle(width, height) << endl;
                }

                if (userInput == 4){
                    userInput = 1;
                    continue;
                }

                cout << "Enter any number to return to menu" << endl;
                cin >> userInput;
                cin.clear();
                cin.ignore(1024, '\n');
                userInput = 1;
                continue;

            case 2://Calculate a 3D Area
                print3DMenu();

                cin >> userInput;
                cin.clear();
                cin.ignore(1024, '\n');

                while (userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4){
                    cout << "Invalid choice, please input a valid menu item." << endl;
                    cin >> userInput;
                    cin.clear();
                    cin.ignore(1024, '\n');
                }

                if (userInput == 1) {
                    //Sphere
                    cout << std::fixed;
                    cout << std::setprecision(2);

                    cout << "Please enter the radius of the sphere: " << endl;
                    cin >> radius;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    radius = checkValueValid(radius);

                    cout << "The area of the sphere is: " << VolumeOfSphere(radius) << endl;
                }
                else if (userInput == 2){
                    //Cone
                    cout << std::fixed;
                    cout << std::setprecision(2);

                    cout << "Please enter the radius of the cone: " << endl;
                    cin >> radius;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    radius = checkValueValid(radius);
                    cout << "Please enter the height of the cone: " << endl;
                    cin >> height;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    height = checkValueValid(height);

                    cout << "The area of the cone is: " << VolumeOfCone(radius, height) << endl;
                }
                else if (userInput == 3){
                    // Rectangle
                    cout << std::fixed;
                    cout << std::setprecision(2);

                    cout << "Please enter the width of the box: " << endl;
                    cin >> width;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    width = checkValueValid(width);
                    cout << "Please enter the height of the box: " << endl;
                    cin >> height;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    height = checkValueValid(height);
                    cout << "Please enter the length of the box: " << endl;
                    cin >> length;
                    cin.clear();
                    cin.ignore(1024, '\n');
                    length = checkValueValid(length);

                    cout << "The area of the box is: " << VolumeOfBox(width, height, length) << endl;
                }

                if (userInput == 4){
                    userInput = 1;
                    continue;
                }

                cout << "Enter any number to return to menu" << endl;
                cin >> userInput;
                cin.clear();
                cin.ignore(1024, '\n');
                userInput = 1;
                continue;
        }

    } while (userInput == 1 || userInput == 2);

    return 0;
}



