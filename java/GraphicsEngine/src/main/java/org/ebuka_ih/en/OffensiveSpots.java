package org.ebuka_ih.en;

import org.ebuka_ih.Court.Coordinate;
import org.ebuka_ih.Util.Util;


public enum OffensiveSpots {
    BASKET(0, 37, 0, 5),
    MID_RANGE(0, 37, 5, 25),
    LONG_RANGE(0, 37, 25, 29),

    THREE_POINT_TOP(31, 37, 0, 4),
    THREE_POINT_LEFT(15, 64, 0, 4),
    THREE_POINT_RIGHT(15, 11, 0, 4),
    THREE_POINT_TOP_CORNER(0, 5, 0, 4),
    THREE_POINT_BOTTOM_CORNER(0, 68, 0, 4),

    FOUR_POINT_TOP(37, 37, 0, 4),
    FOUR_POINT_LEFT(31, 58, 0, 4),
    FOUR_POINT_RIGHT(31, 15, 0, 4),
    FOUR_POINT_TOP_CORNER(0, 2, 0, 4),
    FOUR_POINT_BOTTOM_CORNER(0, 73, 0, 4),
    FOUR_POINT_DEEP(45, 37, 4, 10),

    POST_LEFT(5, 40, 1, 5),
    POST_RIGHT(5, 34, 1, 5);
    private final int x;
    private final int y;
    private final int min;
    private final int max;

    OffensiveSpots(int x, int y, int min, int max) {
        this.x = x;
        this.y = y;
        this.min = min;
        this.max = max;
    }
    public Coordinate getHomePosition() {
        int offset = Util.RANDOM.nextInt(max - min + 1) + min;
        Coordinate center = new Coordinate(x, y);
        return center.getRandom(offset);
    }
    public Coordinate getAwayPosition() {
        int offset = Util.RANDOM.nextInt(max - min + 1) + min;
        Coordinate center = new Coordinate(150-x, y);
        return center.getRandom(offset);
    }
}
