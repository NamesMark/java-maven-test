package com.marktest;

/**
 * Represents the orientations for a rover.
 * <p>
 * Four possible orientations: NORTH, SOUTH, EAST, and WEST.
 * Determines the direction in which a rover is facing on the plateau.
 * </p>
 * 
 * @author NamesMark
 * @version 1.0
 */
public enum Orientation {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    private final char shortForm;

    Orientation(char shortForm) {
        this.shortForm = shortForm;
    }

    public char getShortForm() {
        return shortForm;
    }

    public static Orientation fromShortForm(char shortForm) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.getShortForm() == shortForm) {
                return orientation;
            }
        }
        throw new IllegalArgumentException("Invalid orientation: " + shortForm);
    }
}