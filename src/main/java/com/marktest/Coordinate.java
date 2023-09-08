package com.marktest;

/**
 * Representation of a 2D coordinate point in a grid.
 * <p>
 * The default constructor initializes the coordinate to the origin point (0,0).
 * </p>
 * 
 * @author NamesMark
 * @version 1.0
 */
public class Coordinate 
{
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        this.x = 0;
        this.y = 0;
    }

    public void moveNorth() {
        this.y += 1;
    }

    public void moveSouth() {
        this.y -= 1;
    }

    public void moveEast() {
        this.x += 1;
    }

    public void moveWest() {
        this.x -= 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate copy() {
        return new Coordinate(this.x, this.y);
    }
}
