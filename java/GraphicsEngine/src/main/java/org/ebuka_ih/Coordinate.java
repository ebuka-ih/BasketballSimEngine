package org.ebuka_ih;

public class Coordinate {

    private long nativeHandle;

    public Coordinate(int x, int y) {
        nativeHandle = create(x, y);
    }

    private static native long create(int x, int y);
    public native int getX();
    public native int getY();
}
