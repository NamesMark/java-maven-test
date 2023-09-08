package com.marktest;

/**
 * Factory class responsible for creating instances of the Rover class.
 * <p>
 * This class abstracts the instantiation logic of the Rover class, allowing for flexibility
 * in creating rover objects with different initial states.
 * </p>
 * 
 * @author NamesMark
 * @version 1.0
 */
public class RoverFactory {

    public Rover createRover(Coordinate position, Orientation orientation, Plateau plateau) {
        return new Rover(position, orientation, plateau);
    }
}