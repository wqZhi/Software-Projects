// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef UNTITLED_ROCK_H
#define UNTITLED_ROCK_H
#include <string>
#include <iostream>
using namespace std;

class Rock {

protected:
    string classification;
    float mass;

public:
    Rock(float  _mass){
        this->mass = _mass;
        this->classification = "Rock";
    }

    void Print(){
        cout << classification << ",\tweighing mass: " << mass << endl;
    }

    virtual bool Inspect() = 0;

};

#endif //UNTITLED_ROCK_H
