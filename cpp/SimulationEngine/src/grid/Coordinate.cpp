//
// Created by william on 9/9/2026.
//

#include "Coordinate.h"
#include "../Util/Util.h"
#include <cstdlib>
#include <random>

Coordinate::Coordinate(const int x, const int y)
    : x(x), y(y) {
}
Coordinate::Coordinate(const int x, const int y, const int min, const int max) {
    this -> x = Util::randomInt(min, max) + x;
    this -> y = Util::randomInt(min, max) + y;
}
int Coordinate::getX() const {
    return x;
}
int Coordinate::getY() const {
    return y;
}

extern "C" {

    __declspec(dllexport)
    Coordinate* coordinate_create(int x, int y) {
        return new Coordinate(x, y);
    }
}