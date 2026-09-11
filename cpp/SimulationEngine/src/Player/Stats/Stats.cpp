//
// Created by william on 9/10/2026.
//

#include "../Stats/Stats.h"

Stats::Stats(const int* stats) {
    finishing = stats[0];
    midrange = stats[1];
    threePoint = stats[2];
    fourPoint = stats[3];
    ballHandling = stats[4];
    passing = stats[5];


    physicalDefense = stats[6];
    perimeterDefense = stats[7];
    stealing = stats[8];
    blocking = stats[9];
    rebounding = stats[10];

    speed = stats[11];
    stamina = stats[12];

    clutch = stats[13];
    consistency = stats[14];
    confidence = stats[15];
}

int Stats::getFinishing() const {
    return finishing;
}
int Stats::getMidrange() const {
    return midrange;
}
int Stats::getThreePoint() const {
    return threePoint;
}
int Stats::getFourPoint() const {
    return fourPoint;
}
int Stats::getBallHandling() const {
    return ballHandling;
}
int Stats::getPassing() const {
    return passing;
}
int Stats::getPhysicalDefense() const {
    return physicalDefense;
}
int Stats::getPerimeterDefense() const {
    return perimeterDefense;
}
int Stats::getStealing() const {
    return stealing;
}
int Stats::getBlocking() const {
    return blocking;
}
int Stats::getRebounding() const {
    return rebounding;
}
int Stats::getSpeed() const {
    return speed;
}
int Stats::getStamina() const {
    return stamina;
}
int Stats::getClutch() const {
    return clutch;
}
int Stats::getConsistency() const {
    return consistency;
}
int Stats::getConfidence() const {
    return confidence;
}
