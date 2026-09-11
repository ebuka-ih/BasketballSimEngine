package org.ebuka_ih.Movement;


import org.ebuka_ih.Court.Coordinate;
import org.ebuka_ih.Player.Player;
import org.ebuka_ih.Player.PlayerAction;
import org.ebuka_ih.en.DecisionMaking.offense.BallDecision;
import org.ebuka_ih.en.PathType;

import java.util.List;

public class Route {

    private List<Coordinate> points;
    private PathType type;
    private int current;

    public Route() {
    }
    public Route(List<Coordinate> points, PathType type, BallDecision ballDecision) {
        this.points = points;
        this.type = type;
        this.current = 0;
    }
    public boolean isFinished() {
        return current >= points.size();
    }
    public void advance() {
        if (!isFinished()) {
            if (current < points.size()) {
                current++;
            }
        }
    }
    public void runEndAction(Player player) {
        switch (type) {
            case SHOT -> PlayerAction.shoot(player);
            case DRIVE -> PlayerAction.drive(player);
        }
    }
    public void setPoints(List<Coordinate> points) {
        this.points = points;
    }
    public void setType(PathType type) {
        this.type = type;
    }
    public void terminate() {
        if (points != null) {
            points.clear();
        }

        current = 0;
    }
    public int currentIndex() {
        return this.current;
    }
    public List<Coordinate> getPoints() {
        return points;
    }

    public PathType getType() {
        return type;
    }
}