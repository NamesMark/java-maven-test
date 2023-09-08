package com.marktest;

/**
 * Represents a rover that can navigate on a plateau.
 * <p>
 * The rover has a position represented by a {@link Coordinate} and an orientation represented by the {@link Orientation} enum.
 * The rover can perform three types of actions: turn left, turn right, and move forward in the direction of its current orientation.
 * </p>
 * <p>
 * The default constructor initializes the rover at the origin point (0,0) facing north.
 * </p>
 * 
 * @author NamesMark
 * @version 1.0
 */
public class Rover {
    private Plateau plateau;
    private Coordinate position;
    private Orientation orientation;

    Rover(Coordinate position, Orientation orientation, Plateau plateau) {
        this.plateau = plateau;
        this.position = position;
        this.orientation = orientation;
    }

    Rover(Plateau plateau) {
        this.plateau = plateau;
        this.position = new Coordinate(0,0);
        this.orientation = Orientation.NORTH;
    }

    public void turnLeft() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.WEST;
                break;
            case EAST:
                orientation = Orientation.NORTH;
                break;
        
            case SOUTH:
                orientation = Orientation.EAST;
                break;
            case WEST:
                orientation = Orientation.SOUTH;
                break;
        }
    }

    public void turnRight() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    public void moveForward() {
        Coordinate nextPosition = position.copy();
    
        switch (orientation) {
            case NORTH:
                nextPosition.moveNorth();
                break;
            case EAST:
                nextPosition.moveEast();
                break;
            case SOUTH:
                nextPosition.moveSouth();
                break;
            case WEST:
                nextPosition.moveWest();
                break;
        }
    
        if (plateau.isValidPosition(nextPosition)) {
            position = nextPosition;
        } else {
            throw new IllegalArgumentException("Invalid move. Rover would go out of plateau boundaries.");
        }
    }

    public Coordinate getPosition() {
        return this.position;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

}