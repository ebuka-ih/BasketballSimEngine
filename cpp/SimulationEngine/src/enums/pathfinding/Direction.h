//
// Created by william on 9/11/2026.
//

#ifndef SIMULATIONENGINE_DIRECTION_H
#define SIMULATIONENGINE_DIRECTION_H


class Direction {

    Direction(int dx, int dy, int angle);
    int dx;
    int dy;
    int angle = 0;

    public:
    static const Direction NORTH;
    static const Direction NORTH_EAST;
    static const Direction NORTH_WEST;
    static const Direction SOUTH;
    static const Direction SOUTH_EAST;
    static const Direction SOUTH_WEST;
    static const Direction EAST;
    static const Direction WEST;

    int getDx() const;
    int getDy() const;
    int getAngle() const;


};


#endif //SIMULATIONENGINE_DIRECTION_H