// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef UNTITLED_ROCKPILE_H
#define UNTITLED_ROCKPILE_H
#include <string>
#include "Rock.h"
using namespace std;

class RockPile {
    string name;
    int count, size;
    Rock **pile;

public:
    RockPile(string _name);
    ~RockPile();
    void Add(Rock* _rock);
    Rock *Remove();
    int GetSize();
    int GetCount();
    void Print();

private:
    void Grow();
    void Shrink();
};
#endif //UNTITLED_ROCKPILE_H
