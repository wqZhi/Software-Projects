// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef CS368_A6_NORTHAMERICANMEMBER_H
#define CS368_A6_NORTHAMERICANMEMBER_H

#include <vector>
#include "Punches.h"

using namespace std;

class NorthAmericanMember {
    string name;
    int strength;
    bool rockschool, tiger;
    vector<char> strategy;

public:
    NorthAmericanMember() {}

    NorthAmericanMember(string _name, int _strength, vector<char> _strategy, bool _rockschool, bool _tiger) {
        this->name = _name;
        this->strength = _strength;
        this->strategy = _strategy;
        this->rockschool = _rockschool;
        this->tiger = _tiger;
    }

    bool operator<(const NorthAmericanMember &defender) {
        if ((this->rockschool && defender.tiger) || (this->tiger && defender.rockschool)) {
            throw new Punches(this->strength, defender.strength);
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

                if (p1_win == 3){return false;}
                if (p2_win == 3){return true;}
            }

            if (p1_win < p2_win) {return true;}
            else {return false;}

        }
    }

    friend ostream &operator<<(ostream &out, const NorthAmericanMember &member) {
        out << member.name;

        if (member.rockschool == true) {out << " (School of Rock)" << endl;}
        else {out << " (Paper of Tigers)" << endl;}

        return out;
    }

};


#endif //CS368_A6_NORTHAMERICANMEMBER_H
