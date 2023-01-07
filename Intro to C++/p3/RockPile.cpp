// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <iostream>
#include "Rock.h"
#include "RockPile.h"

RockPile::RockPile(string _name){
    this->name = _name;
    this->count = 0;
    this->size = 5;
    pile = new Rock*[this->size];
}


RockPile::~RockPile() {
    for (int i = 0; i < size; ++i){
        delete pile[i];
    }

    delete []pile;

}


void RockPile::Add(Rock *_rock) {
    pile[count] = _rock;
    this->count++;
    if (count == size){
        Grow();
    }
}


Rock *RockPile::Remove() {
    Rock *lastRock = pile[count-1];
    pile[count-1] = nullptr;
    this->count--;

    if (count < (size/4.0)){
        Shrink();
    }

    return lastRock;
}


void RockPile::Print() {
    cout << "Name: " << this->name << endl;
    cout << "Size: " << GetSize() << "; Count: " << GetCount() << endl;

    for (int i = 0; i < this->count; ++i){
        pile[i]->Print();
    }
}


void RockPile::Grow() {
    this->size *= 2;

    Rock **newPile = new Rock*[size];
    for (int i = 0; i < count; ++i){
        newPile[i] = &(*pile[i]);
    }

    delete []pile;
    pile = newPile;
}


void RockPile::Shrink() {
    this->size /= 2;
    Rock **newPile = new Rock*[size];
    for (int i = 0; i < count; ++i){
        newPile[i] = &(*pile[i]);
    }

    delete []pile;
    pile = newPile;
}


int RockPile::GetSize() {
    return this->size;
}


int RockPile::GetCount() {
    return this->count;
}


