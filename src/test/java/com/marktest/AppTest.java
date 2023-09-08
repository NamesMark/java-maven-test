package com.marktest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mockito;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void sanityCheck()
    {
        assertTrue( true );
    }

    @Test
    public void roverBuildCheck()
    {
        Plateau plateau = new Plateau();
        RoverFactory roverFactory = new RoverFactory();
        Rover rover1 = roverFactory.createRover(plateau);
        Rover rover2 = roverFactory.createRover(new Coordinate(2, 2), Orientation.SOUTH, plateau);

        assertEquals(0, rover1.getPosition().getX());
        assertEquals(0, rover1.getPosition().getY());
        assertEquals(Orientation.NORTH, rover1.getOrientation());

        assertEquals(2, rover2.getPosition().getX());
        assertEquals(2, rover2.getPosition().getY());
        assertEquals(Orientation.SOUTH, rover2.getOrientation());

    }

    @Test
    public void testValidMovementPlan() {
        assertTrue(App.isValidMovementPlan("LMLMLMLMM"));
        assertTrue(App.isValidMovementPlan("MMRMMRMRRM"));
        assertFalse(App.isValidMovementPlan("LMXLMLMLMM"));
        assertFalse(App.isValidMovementPlan("MMRMMRMRRMX"));
    }

    @Test
    public void testRoverMovement() {
        Plateau plateau = new Plateau(new Coordinate(5, 5));
        Rover rover = new Rover(new Coordinate(1, 2), Orientation.NORTH, plateau);
        
        rover.turnLeft();
        rover.moveForward();
        rover.turnLeft();
        rover.moveForward();

        assertEquals(0, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
        assertEquals(Orientation.SOUTH, rover.getOrientation());
    }

    @Test
    public void testRoverMoveWithMockPlateau() {
        Plateau mockPlateau = Mockito.mock(Plateau.class);
        Mockito.when(mockPlateau.isValidPosition(Mockito.any(Coordinate.class))).thenReturn(true);

        Rover rover = new Rover(new Coordinate(1, 2), Orientation.NORTH, mockPlateau);
        rover.moveForward();

        assertEquals(1, rover.getPosition().getX());
        assertEquals(3, rover.getPosition().getY());
    }
}
