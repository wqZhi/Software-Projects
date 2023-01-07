#include <iostream>
#include <vector>
#include "NorthAmericanMember.h"
#include "SouthAmericanMember.h"
#include "IRPSSHierarchy.h"
#include "Punches.h"

using namespace std;

int main() {
    vector<char> next_strategy;

    // add first member, Ares
    next_strategy = vector<char>{'r', 'r', 'p', 's', 'r', 's', 's', 'r', 'p', 's'};
    NorthAmericanMember next_member = NorthAmericanMember("Auspicious Ares", 5, next_strategy, false, false);
    IRPSSHierarchy<NorthAmericanMember> na_hierarchy = IRPSSHierarchy<NorthAmericanMember>(next_member);

    // Travis should defeat Ares and go to right.
    next_strategy = vector<char>{'p', 's', 's', 'r', 's', 'p', 's', 'r', 'p', 'r'};
    next_member = NorthAmericanMember("Tremendous Travis", 4, next_strategy, false, true);
    na_hierarchy.Insert(next_member);

    // Vlad should lose to Ares, and go to left.
    next_strategy = vector<char>{'s', 'r', 's', 'p', 'r', 'p', 'p', 's', 's', 'r'};
    next_member = NorthAmericanMember("Versed Vlad", 9, next_strategy, false, false);
    na_hierarchy.Insert(next_member);

    cout << "North American IRPSS chapter:" << endl;
    cout << na_hierarchy << endl; // should get Vlad, Ares, Travis, in that order.

    // Adam should beat Ares, then beat Travis
    next_strategy = vector<char>{'p', 'r', 's', 'p', 'r', 's', 'p', 'r', 's', 'p'};
    next_member = NorthAmericanMember("Atomic Adam", 7, next_strategy, false, true);
    na_hierarchy.Insert(next_member);

    // Elias beats Ares, then fights and kicks out Travis.
    next_strategy = vector<char>{'s', 'p', 's', 'p', 'p', 's', 'p', 'p', 'p', 's'};
    next_member = NorthAmericanMember("Enormous Elias", 10, next_strategy, true, false);
    na_hierarchy.Insert(next_member);

    cout << "North American IRPSS chapter:" << endl;
    cout << na_hierarchy << endl; // Should get Vlad, Ares, Elias, and Adam, in that order.


    // do another hierarchy, this time for the South American chapter of the society.
    // add first member, Andre
    next_strategy = vector<char>{'s', 's', 'p', 'r', 'p', 'r', 's', 'p', 'r', 'r'};
    SouthAmericanMember next_sa_member = SouthAmericanMember("Ambitious Andre", 4, 6, next_strategy, true, false);
    IRPSSHierarchy<SouthAmericanMember> sa_hierarchy = IRPSSHierarchy<SouthAmericanMember>(next_sa_member);

    // Eva should lose to Andre, then go to the left.
    next_strategy = vector<char>{'s', 'p', 'r', 'p', 'p', 's', 'r', 'p', 's', 'p'};
    next_sa_member = SouthAmericanMember("Electric Eva", 6, 3, next_strategy, false, false);
    sa_hierarchy.Insert(next_sa_member);

    // Maximilian should fight Andre, lose, and not be added in the hierarchy.
    next_strategy = vector<char>{'r', 'r', 'r', 'r', 'p', 's', 'r', 's', 'r', 'p'};
    next_sa_member = SouthAmericanMember("Maximilian", 7, 3, next_strategy, false, true);
    sa_hierarchy.Insert(next_sa_member);

    cout << "South American IRPSS chapter:" << endl;
    cout << sa_hierarchy << endl; // should have Eva, then Andre

    // Tamaya should tie with Andre, move to the left (since tie goes to the defender), then beat Eva.
    next_strategy = vector<char>{'r', 's', 'p', 's', 'p', 's', 'p', 's', 'p', 'r'};
    next_sa_member = SouthAmericanMember("Tranquil Tamaya", 7, 3, next_strategy, false, false);
    sa_hierarchy.Insert(next_sa_member);

    cout << "South American IRPSS chapter:" << endl;
    cout << sa_hierarchy << endl; // should have Eva, then Tamaya, then Andre

    return 0;
}

