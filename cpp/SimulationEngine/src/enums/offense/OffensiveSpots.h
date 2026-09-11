//
// Created by william on 9/10/2026.
//

#ifndef SIMULATIONENGINE_OFFENSIVESPOTS_H
#define SIMULATIONENGINE_OFFENSIVESPOTS_H
#include "../../grid/Coordinate.h"


class OffensiveSpots {

    public:
    static const Coordinate BASKET;
    static const Coordinate MID_RANGE;
    static const Coordinate LONG_RANGE;
    static const Coordinate THREE_POINT_TOP;
    static const Coordinate THREE_POINT_LEFT;
    static const Coordinate THREE_POINT_RIGHT;
    static const Coordinate THREE_POINT_TOP_CORNER;
    static const Coordinate THREE_POINT_BOTTOM_CORNER;
    static const Coordinate FOUR_POINT_TOP;
    static const Coordinate FOUR_POINT_LEFT;
    static const Coordinate FOUR_POINT_RIGHT;
    static const Coordinate FOUR_POINT_TOP_CORNER;
    static const Coordinate FOUR_POINT_BOTTOM_CORNER;
    static const Coordinate FOUR_POINT_DEEP;

    static const Coordinate POST_LEFT;
    static const Coordinate POST_RIGHT;

    static Coordinate getPosition(const Coordinate& coordinate, const bool home);
};


#endif //SIMULATIONENGINE_OFFENSIVESPOTS_H