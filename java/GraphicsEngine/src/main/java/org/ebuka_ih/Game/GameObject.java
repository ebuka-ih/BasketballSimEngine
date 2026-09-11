package org.ebuka_ih.Game;

import org.ebuka_ih.Court.Coordinate;
import org.ebuka_ih.Court.GridNode;

import java.awt.*;

public abstract class GameObject {

    private Coordinate position;

    private Color color;
    private boolean collidable;
    private Match match;

    protected GameObject() {
    }
    protected GameObject(Match match) {
        this.match = match;
        match.getGrid().addObject(this);
    }

    public Coordinate getCoordinate() {
        return this.position;
    }
    public void setCoordinate(Coordinate coordinate) {
        GridNode node = match.getGrid().getNode(coordinate);
        if(!match.getGrid().hasObject(node)) {
            this.position = coordinate;
        } else {
            int searchRadius = 1;
            Coordinate c = null;
            while (c == null) {
                for(int dx = -searchRadius; dx <= searchRadius && c == null; dx++) {
                    for(int dy = -searchRadius; dy <= searchRadius && c == null; dy++) {
                        if (dx == 0 && dy == 0) {
                            continue;
                        }
                        Coordinate v = new Coordinate(coordinate.getX() + dx, coordinate.getY() + dy);
                        GridNode n = match.getGrid().getNode(v);
                        if(!match.getGrid().hasObject(n)) {
                            c = v;
                        }
                    }
                }
                searchRadius++;
            }
            this.position = c;
        }
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setColor(String hex) {
        this.color = Color.decode(hex);
    }
    public Color getColor() {
        return this.color;
    }
    public boolean isCollidable() {
        return this.collidable;
    }
    public void setMatch(Match match) {
        this.match = match;
        match.getGrid().addObject(this);
    }
    public Match getMatch() {
        return this.match;
    }
}