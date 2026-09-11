package org.ebuka_ih.Court;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Coordinate {
    private Integer x;
    private Integer y;
    private Color color;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public Coordinate getRandom(int distance) {

        List<Coordinate> positions = new ArrayList<>();

        for (int dx = -distance; dx <= distance; dx++) {
            for (int dy = -distance; dy <= distance; dy++) {

                if (dx == 0 && dy == 0) {
                    int x = Math.clamp(getX() + dx, 0, 150);
                    int y = Math.clamp(getY() + dy, 0, 74);
                    positions.add(new Coordinate(x, y));
                }

                if (dx * dx + dy * dy == distance * distance) {
                    int x = Math.clamp(getX() + dx, 0, 150);
                    int y = Math.clamp(getY() + dy, 0, 74);
                    positions.add(new Coordinate(x, y));
                }
            }
        }
        Collections.shuffle(positions);
        return positions.getFirst();
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(!(object instanceof Coordinate coordinate)) {
            return false;
        }
        return coordinate.x.equals(x) && coordinate.y.equals(y);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}