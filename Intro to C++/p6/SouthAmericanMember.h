// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef CS368_A6_SOUTHAMERICANMEMBER_H
#define CS368_A6_SOUTHAMERICANMEMBER_H

#include <vector>
#include "Punches.h"

using namespace std;

class SouthAmericanMember {
    string name;
    int strength, speed;
    bool rockschool, tiger;
    vector<char> strategy;

public:
    SouthAmericanMember() {}

    SouthAmericanMember(string _name, int _strength, int _speed, vector<char> _strategy, bool _rockschool, bool _tiger) {
        this->name = _name;
        this->strength = _strength;
        this->speed = _speed;
        this->strategy = _strategy;
        this->rockschool = _rockschool;
        this->tiger = _tiger;
    }

    bool operator<(const SouthAmericanMember &defender) {
        if ((this->rockschool && defender.tiger) || (this->tiger && defender.rockschool)) {
            throw new Punches(this->strength, defender.strength, this->speed, defender.speed);
        }
        else {
            int p1_win = 0;
            int p2_win = 0;
            for (int i = 0; i < this->strategy.size(); i++) {
                if (this->strategy.at(i) == defender.strategy.at(i)) {
                    p1_win++;
                    p2_win++;
                }
                else if (this->strategy.at(i) == 'r') {
                    if (defender.strategy.at(i) == 's') {p1_win++;}
                    else if (defender.strategy.at(i) == 'p') {p2_win++;}
                }
                else if (this->strategy.at(i) == 's') {
                    if (defender.strategy.at(i) == 'p') {p1_win++;}
                    else if (defender.strategy.at(i) == 'r') {p2_win++;}
                }
                else if (this->strategy.at(i) == 'p') {
                    if (defender.strategy.at(i) == 'r') {p1_win++;}
                    else if (defender.strategy.at(i) == 's') {p2_win++;}
                }

                if (p1_win == 4){return false;}
                if (p2_win == 4) {return true;}
            }

            if (p1_win < p2_win) {return true;}
            else {return false;}

        }
    }

    friend ostream &operator<<(ostream &out, const SouthAmericanMember &member) {
        cout << member.name;

        if (member.rockschool == true) {cout << " (School of Rock)" << endl;}
        else {cout << " (Paper of Tigers)" << endl;}

        return out;
    }
};


#endif //CS368_A6_SOUTHAMERICANMEMBER_H
