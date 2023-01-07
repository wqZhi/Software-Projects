// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef UNTITLED_DIAMOND_H
#define UNTITLED_DIAMOND_H

class Diamond : public Rock{

public:
    Diamond(float _mass) : Rock(_mass) {
        this->mass = _mass;
        this->classification = "Diamond";
    }

    bool Inspect() {
        if (this->mass >= 3){
            return true;
        }
        return false;
    }

};

#endif //UNTITLED_DIAMOND_H
