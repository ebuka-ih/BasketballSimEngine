//
// Created by william on 9/9/2026.
//

#ifndef SIMULATIONENGINE_COORDINATE_H
#define SIMULATIONENGINE_COORDINATE_H


class Coordinate {
public:
    Coordinate(int x, int y);
    Coordinate(int x, int y, int min, int max);

    int getX() const;
    int getY() const;

    Coordinate* coordinate_create(int x, int y);
private:
    int x;
    int y;

};

extern "C" {
    __declspec(dllexport)
    Coordinate* coordinate_create(int x, int y);
}

#endif //SIMULATIONENGINE_COORDINATE_H