#ifndef UNTITLED_ROCKMAKER_H
#define UNTITLED_ROCKMAKER_H

#include <cstdlib>
#include "Rock.h"
#include "Ruby.h"
#include "Gold.h"
#include "Diamond.h"
#include "Sandstone.h"
#ifndef ROCKMAKER_HEADER
#define ROCKMAKER_HEADER

Rock* PanForRock() {
    int kind = rand() % 8;
    float mass = ((rand() % 100000) + 1) / 8333.0;
    switch (kind) {
        case 0:
            return new Ruby(mass);
        case 1:
            return new Gold(mass);
        case 2:
            return new Diamond(mass);
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
            return new Sandstone(mass);
        default:
            return nullptr;
    }
}
#endif

#endif //UNTITLED_ROCKMAKER_H
