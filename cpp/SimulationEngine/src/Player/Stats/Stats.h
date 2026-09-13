//
// Created by william on 9/10/2026.
//

#ifndef SIMULATIONENGINE_STATS_H
#define SIMULATIONENGINE_STATS_H


class Stats {
    int finishing;
    int midrange;
    int threePoint;
    int fourPoint;
    int ballHandling;
    int passing;

    int physicalDefense;
    int perimeterDefense;
    int stealing;
    int blocking;
    int rebounding;

    int speed;
    int stamina;

    int clutch;
    int consistency;
    int confidence;

    public:
    explicit Stats(const int (&stats)[16]);
    [[nodiscard("finishing ignored")]]int getFinishing() const;
    [[nodiscard("midrange ignored")]]int getMidrange() const;
    [[nodiscard("three point ignored")]]int getThreePoint() const;
    [[nodiscard("four point ignored")]]int getFourPoint() const;
    [[nodiscard("ball handling ignored")]]int getBallHandling() const;
    [[nodiscard("passing ignored")]]int getPassing() const;
    [[nodiscard("physical defense ignored")]]int getPhysicalDefense() const;
    [[nodiscard("perimeter defense ignored")]]int getPerimeterDefense() const;
    [[nodiscard("stealing ignored")]]int getStealing() const;
    [[nodiscard("blocking ignored")]]int getBlocking() const;
    [[nodiscard("rebounding ignored")]]int getRebounding() const;
    [[nodiscard("speed ignored")]]int getSpeed() const;
    [[nodiscard("stamina ignored")]]int getStamina() const;
    [[nodiscard("clutch ignored")]]int getClutch() const;
    [[nodiscard("consistency ignored")]]int getConsistency() const;
    [[nodiscard("confidence ignored")]]int getConfidence() const;
};


#endif //SIMULATIONENGINE_STATS_H