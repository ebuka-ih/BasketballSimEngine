//
// Created by william on 9/11/2026.
//

#include "Direction.h"

Direction::Direction(const int dx, const int dy, const int angle)
    : dx(dx), dy(dy), angle(angle) {
}

int Direction::getAngle() const {
    return this -> angle;
}
int Direction::getDx() const {
    return this -> dx;
}
int Direction::getDy() const {
    return this -> dy;
}


const Direction  Direction::NORTH(0, -1, 0);
const Direction  Direction::NORTH_EAST(1, -1, 45);
const Direction  Direction::EAST(1, 0, 90);
const Direction  Direction::SOUTH_EAST(1, 1, 135);
const Direction  Direction::SOUTH(0, 1, 180);
const Direction  Direction::SOUTH_WEST(-1, 1, 225);
const Direction  Direction::WEST(-1, 0, 270);
const Direction  Direction::NORTH_WEST(-1, -1, 315);