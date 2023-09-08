package com.marktest;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the plateau on which rovers navigate.
 * <p>
 * This class provides methods to manage rovers on the plateau and check if a given position is valid.
 * The plateau is initialized with an upper right coordinate, with the lower left coordinate being (0,0).
 * </p>
 * <p>
 * The Plateau class is implemented as a singleton to ensure only one instance of the plateau exists.
 * </p>
 * 
 * @author NamesMark
 * @version 1.0
 */
public class Plateau 
{
    private Coordinate upperRightCoordinate;
    private List<Rover> rovers;

    private static Plateau instance;

    public Plateau(Coordinate coordinate) {
        this.upperRightCoordinate = coordinate;
        this.rovers = new ArrayList<Rover>();
    }

    public static Plateau getInstance(Coordinate coordinate) {
        if (instance == null) {
            instance = new Plateau(coordinate);
        }
        return instance;
    }

    public void addRover(Rover rover) {
        rovers.add(rover);
    }

    public boolean isValidPosition(Coordinate position) {
        return position.getX() >= 0 && position.getX() <= upperRightCoordinate.getX()
            && position.getY() >= 0 && position.getY() <= upperRightCoordinate.getY();
    }
}
