//
// Created by william on 9/10/2026.
//

#include "PathEvaluator.h"

#include "../enums/offense/OffensiveSpots.h"
#include "../enums/pathfinding/Direction.h"
#include "../Player/Player.h"



double PathEvaluator::evaluateNode(Player* player, GridNode* node, GridNode* endPoint) {

}
double PathEvaluator::evaluateDirection(Direction direction, Direction actual) {

}
double PathEvaluator::evaluateSpacing(Player* player, GridNode* node) {

}
double PathEvaluator::evaluateDistance(GridNode& node, GridNode& endPoint) {
    Coordinate* startPos = node->getCoordinate();
    Coordinate* endPos = endPoint->getCoordinate();

}


GridNode* getBestNeighbor(GridNode* node) {
    return nullptr;
}

Route constructRoute(GridNode* start, GridNode* goal) {
    return null;
}



