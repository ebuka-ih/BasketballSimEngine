//
// Created by william on 9/10/2026.
//

#ifndef SIMULATIONENGINE_ROUTE_H
#define SIMULATIONENGINE_ROUTE_H
#include "GridNode.h"
#include <vector>


class Route {
    std::vector<GridNode*> nodes;

    public:
    void addNode(GridNode* node);
    const std::vector<GridNode*>& getNodes();
};


#endif //SIMULATIONENGINE_ROUTE_H