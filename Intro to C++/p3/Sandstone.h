// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef UNTITLED_SANDSTONE_H
#define UNTITLED_SANDSTONE_H

class Sandstone : public Rock{

public:
    Sandstone(float _mass) : Rock(_mass) {
        this->mass = _mass;
        this->classification = "Sandstone";
    }

    bool Inspect() {
        return false;
    }


};




#endif //UNTITLED_SANDSTONE_H
