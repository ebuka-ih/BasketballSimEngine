package org.ebuka_ih.Player;

public class Stats {

    private int finishing;
    private int midrange;
    private int threePoint;
    private int fourPoint;
    private int ballHandling;
    private int passing;

    private int physicalDefense;
    private int perimeterDefense;
    private int stealing;
    private int blocking;
    private int rebounding;

    private int speed;
    private int stamina;

    private int clutch;
    private int consistency;
    private int confidence;

    public Stats() {

    }
    public Stats(
            int finishing,
            int midrange,
            int threePoint,
            int fourPoint,
            int ballHandling,
            int passing,
            int physicalDefense,
            int perimeterDefense,
            int stealing,
            int blocking,
            int rebounding,
            int speed,
            int stamina,
            int clutch,
            int consistency,
            int confidence) {

        this.finishing = finishing;
        this.midrange = midrange;
        this.threePoint = threePoint;
        this.fourPoint = fourPoint;
        this.ballHandling = ballHandling;
        this.passing = passing;
        this.physicalDefense = physicalDefense;
        this.perimeterDefense = perimeterDefense;
        this.stealing = stealing;
        this.blocking = blocking;
        this.rebounding = rebounding;
        this.speed = speed;
        this.stamina = stamina;
        this.clutch = clutch;
        this.consistency = consistency;
        this.confidence = confidence;
    }

    public int getFinishing() {
        return this.finishing;
    }
    public void setFinishing(int finishing) {
        this.finishing = finishing;
    }
    public int getMidrange() {
        return this.midrange;
    }
    public void setMidrange(int midrange) {
        this.midrange = midrange;
    }
    public int getThreePoint() {
        return this.threePoint;
    }
    public void setThreePoint(int threePoint) {
        this.threePoint = threePoint;
    }
    public int getFourPoint() {
        return this.fourPoint;
    }
    public void setFourPoint(int fourPoint) {
        this.fourPoint = fourPoint;
    }
    public int getBallHandling() {
        return this.ballHandling;
    }
    public void setBallHandling(int ballHandling) {
        this.ballHandling = ballHandling;
    }
    public int getPassing() {
        return this.passing;
    }
    public void setPassing(int passing) {
        this.passing = passing;
    }
    public int getPhysicalDefense() {
        return this.physicalDefense;
    }
    public void setPhysicalDefense(int defense) {
        this.physicalDefense = defense;
    }
    public int getPerimeterDefense() {
        return this.perimeterDefense;
    }
    public void setPerimeterDefense(int perimeterDefense) {
        this.perimeterDefense = perimeterDefense;
    }
    public int getStealing() {
        return this.stealing;
    }
    public void setStealing(int stealing) {
        this.stealing = stealing;
    }
    public int getBlocking() {
        return this.blocking;
    }
    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }
    public int getRebounding() {
        return this.rebounding;
    }
    public void setRebounding(int rebounding) {
        this.rebounding = rebounding;
    }
    public int getSpeed() {
        return this.speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getStamina() {
        return this.stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public int getClutch() {
        return this.clutch;
    }
    public void setClutch(int clutch) {
        this.clutch = clutch;
    }
    public int getConsistency() {
        return this.consistency;
    }
    public void setConsistency(int consistency) {
        this.consistency = consistency;
    }
    public int getConfidence() {
        return this.confidence;
    }
    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public int[] ship() {
        return new int[]{
                finishing, midrange, threePoint,
                fourPoint, ballHandling, passing,
                physicalDefense, perimeterDefense,
                stealing, blocking, rebounding,
                speed, stamina, clutch, consistency, confidence};

    }
}

