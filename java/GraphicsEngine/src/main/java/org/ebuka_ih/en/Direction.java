package org.ebuka_ih.en;

public enum Direction {

    NORTH(0, -1, 0),
    NORTHEAST(1, -1, 45),
    EAST(1, 0, 90),
    SOUTHEAST(1, 1, 135),
    SOUTH(0, 1, 180),
    SOUTHWEST(-1, 1, 225),
    WEST(-1, 0, 270),
    NORTHWEST(-1, -1, 315);


    private final int dx;
    private final int dy;
    private final int angle;

    Direction(int dx, int dy, int angle) {
        this.dx = dx;
        this.dy = dy;
        this.angle = angle;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getAngle() {
        return this.angle;
    }
}