//
// Created by william on 9/10/2026.
//

#ifndef SIMULATIONENGINE_PATHEVALUATOR_H
#define SIMULATIONENGINE_PATHEVALUATOR_H
#include "../enums/pathfinding/Direction.h"
#include "../grid/GridNode.h"
#include "../grid/Route.h"


class Player;
class Direction;

class PathEvaluator {

    double evaluateNode(Player* evaluator, GridNode* node, GridNode* endPoint);
    double evaluateDirection(Direction direction, Direction actual);
    double evaluateSpacing(Player*, GridNode* node);
    double evaluateDistance(GridNode* node, GridNode* endPoint);
    bool isValid(GridNode* node);

    public:
    GridNode* getBestNeighbor(GridNode* node);
    Route constructRoute(GridNode* start, GridNode* goal);

};


#endif //SIMULATIONENGINE_PATHEVALUATOR_H