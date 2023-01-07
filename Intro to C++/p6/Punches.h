#ifndef CS368_A6_PUNCHES_H
#define CS368_A6_PUNCHES_H
#include <exception>
#ifndef PUNCHES_HEADER
#define PUNCHES_HEADER

class Punches : public std::exception {
public:
    int challenger, defender;

    Punches(int ch_strength, int df_strength, int ch_speed=1, int df_speed=1) : std::exception() {
        challenger = ch_strength * ch_speed;
        defender = df_strength * df_speed;
    }

    virtual const char* what() const throw() {
        return "A fight broke out between two members!";
    }
};

#endif
#endif //CS368_A6_PUNCHES_H
