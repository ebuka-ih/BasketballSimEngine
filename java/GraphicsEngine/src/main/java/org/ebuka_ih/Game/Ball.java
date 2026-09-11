package org.ebuka_ih.Game;

import org.ebuka_ih.Court.Coordinate;
import org.ebuka_ih.Player.Player;

public class Ball extends GameObject {

    double velocityX;
    double velocityY;

    boolean loose;
    private Player holder;

    public Ball(Match match, Coordinate position) {
        super(match);
        this.holder = null;
        this.loose = true;
        super.setCoordinate(position);
    }
    public Ball(Match match, Player holder) {
        super(match);
        this.holder = holder;
        this.loose = false;
    }
    @Override
    public void setCoordinate(Coordinate position) {
        super.setCoordinate(position);
        this.loose = true;
    }
    public double getXVelocity() {
        return this.velocityX;
    }
    public double getVelocityY() {
        return this.velocityY;
    }
    public boolean isLoose() {
        return this.loose;
    }
    public void release() {
        this.holder = null;
        this.loose = false;
    }
    public void setHolder(Player player) {
        this.holder = player;
        this.loose = false;
    }
    public Player getHolder() {
        return this.holder;
    }
}