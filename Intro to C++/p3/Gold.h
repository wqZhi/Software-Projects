// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef UNTITLED_GOLD_H
#define UNTITLED_GOLD_H

class Gold : public Rock{

public:
    Gold(float _mass) : Rock(_mass) {
        this->mass = _mass;
        this->classification = "Gold";
    }

    bool Inspect() {
        if (this->mass >= 1) {
            return true;
        }
        return false;
    }

};

#endif //UNTITLED_GOLD_H
