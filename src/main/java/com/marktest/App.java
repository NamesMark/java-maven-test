package com.marktest;

import java.util.Scanner;

/**
 * 
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner (System.in);
        int x, y = -1;
        while (true) {
            x = -1;
            y = -1;
            System.out.print("Enter Graph Upper Right Coordinate: ");
            String[] plateauCoordinates = scanner.nextLine().split(" ");
            try {
                x = Integer.parseInt(plateauCoordinates[0]);
                y = Integer.parseInt(plateauCoordinates[1]);
            } catch (NumberFormatException e) {
                System.out.print("Bad coordinates. Try again.");
            }
            if (x != -1 && y != -1) {
                break;
            }
            System.out.print("Bad coordinates. Try again.");
        }
        
        Plateau plateau = new Plateau(new Coordinate(x, y));
        RoverFactory roverFactory = new RoverFactory();

        int roverCount = 1;

        while (true) {
            // Rover starting postion:
            System.out.print("Rover " + roverCount + " Starting Position: ");
            String[] startingPosition = scanner.nextLine().split(" ");
            if (startingPosition.length != 3) {
                System.out.println("Bad starting position input. Try again.");
                continue;
            }
            int roverX, roverY = -1; 
            try {
                roverX = Integer.parseInt(startingPosition[0]);
                roverY = Integer.parseInt(startingPosition[1]);
            } catch (NumberFormatException e) {
                System.out.print("Bad rover coordinates. Try again.");
                continue;
            }

            Orientation roverOrientation = Orientation.fromShortForm(startingPosition[2].charAt(0));

            Rover rover = roverFactory.createRover(new Coordinate(roverX, roverY), roverOrientation, plateau);

            // Movement Plan input and resolution:
            System.out.print("Rover " + roverCount + " Movement Plan: ");
            String movementPlan = scanner.nextLine();
            for (char commandChar : movementPlan.toCharArray()) {
                Command command;
                switch (Character.toUpperCase(commandChar)) {
                    case 'L':
                        command = new TurnLeftCommand(rover);
                        break;
                    case 'R':
                        command = new TurnRightCommand(rover);
                        break;
                    case 'M':
                        command = new MoveForwardCommand(rover);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid command: " + commandChar);
                }
                command.execute();
            }
            
            Coordinate finalPosition = rover.getPosition();
            Orientation finalOrientation = rover.getOrientation();
            System.out.println("Rover " + roverCount + " Output: " + finalPosition.getX() + " " + finalPosition.getY() + " " + finalOrientation);
            
            // Exit condition:
            System.out.print("Do you want to input another rover? (yes/no): ");
            String continueInput = scanner.nextLine().trim().toLowerCase();
        
            if ("no".equals(continueInput) || "exit".equals(continueInput)) {
                break;  
            }
        
            roverCount++;
        }

        scanner.close();
    }
}
