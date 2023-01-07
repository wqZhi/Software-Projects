// name: Weiqian Zhi
// Wisc ID: wzhi3

#include <map>
#include "Stockholder.h"

using namespace std;

Stockholder::Stockholder() {}

Stockholder::Stockholder(string _name) {
    this->name = _name;
    this->cash = 100000;
    portfolio = map<string, pair<Stock *, int> >();
}

Stockholder::~Stockholder() {}

bool Stockholder::Purchase(Stock *stock, int count) {
    float payment_total = stock->new_value * count;

    if (payment_total <= this->cash) {
        if (this->portfolio.find(stock->short_name) == this->portfolio.end()) {
            this->portfolio[stock->short_name] = pair<Stock *, int>(stock, count);
        } else {
            this->portfolio[stock->short_name].second += count;
        }

        this->cash -= payment_total;
        return true;
    }

    return false;
}


bool Stockholder::Sell(string short_name, int count) {
    map<string, pair<Stock *, int> >::iterator lookup = portfolio.find(short_name);

    if (lookup == this->portfolio.end()) {
        return false;
    }

    int stockCount = this->portfolio[short_name].second;
    if (stockCount < count) {
        return false;
    }

    Stock *stock = this->portfolio[short_name].first;
    float earned = stock->new_value * count;
    this->cash += earned;
    this->portfolio[short_name].second -= count;
    if (this->portfolio[short_name].second == 0) {
        this->portfolio.erase(short_name);
    }

    return true;
}


float Stockholder::NetWorth() {
    PortfolioValue pv = PortfolioValue();
    pv = for_each(portfolio.begin(), portfolio.end(), pv);
    return pv.total_value + cash;
}


void Stockholder::Print() {
    cout << this->name << " $" << NetWorth() << endl;

    for (map<string, pair<Stock *, int> >::iterator it = this->portfolio.begin(); it != this->portfolio.end(); ++it) {
        it->second.first->Print();
        cout << "  " << it->second.second << endl;
    }

}