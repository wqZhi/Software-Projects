//
// Created by w on 10/31/21.
//
#ifndef UNTITLED_RUBY_H
#define UNTITLED_RUBY_H
class Ruby : public Rock{

public:
    Ruby(float _mass) : Rock(_mass) {
        this->mass = _mass;
    }

    bool Inspect() {
        this->classification = "Ruby";
        if (this->mass >= 5){
            return true;
        }
        return false;
    }

};
#endif //UNTITLED_RUBY_H
