// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef CS368_A5_STOCKHOLDER_H
#define CS368_A5_STOCKHOLDER_H

#include <string>
#include "Stock.h"
#include <map>

struct PortfolioValue {
    float total_value;
    void operator() (pair<string, pair<Stock *, int> > stockInfo) {
        total_value += (stockInfo.second.first->new_value * stockInfo.second.second);
    }

};


class Stockholder {
private:
    string name;
    float cash;
    map<string, pair<Stock *, int> > portfolio;

public:
    Stockholder();

    Stockholder(string _name);

    ~Stockholder();

    bool Purchase(Stock *stock, int count);

    bool Sell(string short_name, int count);

    float NetWorth();

    void Print();

};


#endif //CS368_A5_STOCKHOLDER_H
