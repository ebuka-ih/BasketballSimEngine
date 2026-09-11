//
// Created by william on 9/10/2026.
//

#ifndef SIMULATIONENGINE_MATCHGRID_H
#define SIMULATIONENGINE_MATCHGRID_H
#include <vector>

#include "GridNode.h"


class MatchGrid {
    //std::vector<GameObject*> objects;
    int rows;
    int cols;
    std::vector<std::vector<GridNode>> nodes;
    // Match match;
    Coordinate HOME_BASKET;
    Coordinate AWAY_BASKET;

    public:
    //Match getMatch();
    MatchGrid(const int rows, const int cols) {
        this -> rows = rows;
        this -> cols = cols;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {

            }
        }
    }
    int getRows() const;
    int getCols() const;
    GridNode* getNode(int row, int col);
    bool containsObject(GridNode* node);

};


#endif //SIMULATIONENGINE_MATCHGRID_H