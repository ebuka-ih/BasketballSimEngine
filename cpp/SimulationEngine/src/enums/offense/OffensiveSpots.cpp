//
// Created by william on 9/10/2026.
//

#include "OffensiveSpots.h"
const Coordinate OffensiveSpots::BASKET(0, 37, 0, 5);
const Coordinate OffensiveSpots::MID_RANGE(0, 37, 5, 25);
const Coordinate OffensiveSpots::LONG_RANGE(0, 37, 25, 29);
const Coordinate OffensiveSpots::THREE_POINT_TOP(31, 37, 0, 4);
const Coordinate OffensiveSpots::THREE_POINT_LEFT(15, 64, 0, 4);
const Coordinate OffensiveSpots::THREE_POINT_RIGHT(15,11, 0, 4);
const Coordinate OffensiveSpots::THREE_POINT_TOP_CORNER(0, 5, 0, 4);
const Coordinate OffensiveSpots::THREE_POINT_BOTTOM_CORNER(0, 68, 0, 4);
const Coordinate OffensiveSpots::FOUR_POINT_TOP(37, 37, 0, 4);
const Coordinate OffensiveSpots::FOUR_POINT_LEFT(31, 58, 0, 4);
const Coordinate OffensiveSpots::FOUR_POINT_RIGHT(31,15, 0, 4);
const Coordinate OffensiveSpots::FOUR_POINT_TOP_CORNER(0, 2, 0, 4);
const Coordinate OffensiveSpots::FOUR_POINT_BOTTOM_CORNER(0, 73, 0, 4);
const Coordinate OffensiveSpots::FOUR_POINT_DEEP(45, 37, 4, 10);
const Coordinate OffensiveSpots::POST_LEFT(5, 40, 0, 5);
const Coordinate OffensiveSpots::POST_RIGHT(5, 34, 0, 5);

Coordinate getPosition(const Coordinate &coordinate, const bool home) {
    if (home) {
        return coordinate;
    }
    return {150-coordinate.getX(), coordinate.getY()};
}
