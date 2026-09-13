cmake_minimum_required(VERSION 4.0)
project(SimulationEngine)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

add_library(simulation SHARED src/Simulation.cpp
        src/Pathfinder/Pathfinder.cpp
        src/grid/Coordinate.cpp
        src/Player/Player.cpp
        src/Player/Stats/Stats.cpp
        src/grid/Route.cpp
        src/grid/Route.h
        src/game/Ball.cpp
        src/game/Ball.h
        src/game/GameObject.cpp
        src/game/GameObject.h
        src/game/Match.cpp
        src/game/Match.h
        src/enums/game/Quarter.h
        src/enums/game/PosessionState.h
        src/enums/game/GameSituation.h
        src/enums/game/ScoreSituation.h
        src/enums/pathfinding/PathType.h
        src/enums/offense/DriveType.h
        src/enums/offense/ShotType.h
        src/enums/offense/OffensiveSpots.h
        src/Util/Util.cpp
        src/Util/Util.h
        src/game/GameResult.h)




