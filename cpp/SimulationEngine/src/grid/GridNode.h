//
// Created by william on 9/10/2026.
//

#ifndef SIMULATIONENGINE_GRIDNODE_H
#define SIMULATIONENGINE_GRIDNODE_H
#include "Coordinate.h"


class GridNode {

    Coordinate coordinate;
    GridNode* parent;

    public:
    explicit GridNode(Coordinate coordinate);
    void setParent(GridNode* node);
    Coordinate& getCoordinate();
    GridNode* getParent();
};


#endif //SIMULATIONENGINE_GRIDNODE_H