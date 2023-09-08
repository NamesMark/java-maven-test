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

        Plateau plateau = new Plateau(getPlateauCoordinates(scanner));
        RoverFactory roverFactory = new RoverFactory();

        int roverCount = 1;

        while (true) {
            // Rover starting postion:
            Rover rover = getRoverDetails(scanner, roverFactory, plateau, roverCount);

            // Movement Plan input and resolution:
            String movementPlan = getMovementPlan(scanner, rover, roverCount);
            executeMovementPlan(rover, movementPlan);
        
            // Output:
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

    private static Coordinate getPlateauCoordinates(Scanner scanner) {
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
                return new Coordinate(x, y);
            }
            System.out.print("Bad coordinates. Try again.");
        }
    }

    private static Rover getRoverDetails(Scanner scanner, RoverFactory roverFactory, Plateau plateau, int roverCount) {
        int roverX, roverY;
        Orientation roverOrientation;
        while (true) {
            System.out.print("Rover " + roverCount + " Starting Position: "); 
            String[] startingPosition = scanner.nextLine().split(" ");
            if (startingPosition.length != 3) {
                System.out.println("Bad starting position input. Try again.");
                continue;
            }
            roverX = -1;
            roverY = -1; 
            try {
                roverX = Integer.parseInt(startingPosition[0]);
                roverY = Integer.parseInt(startingPosition[1]);
            } catch (NumberFormatException e) {
                System.out.print("Bad rover coordinates. Try again.");
                continue;
            }

            try {
                roverOrientation = Orientation.fromShortForm(startingPosition[2].charAt(0));
                break;
            } catch (IllegalArgumentException e) {
                System.out.print("Bad rover orientation. Try again.");
                continue;
            }
        }

        return roverFactory.createRover(new Coordinate(roverX, roverY), roverOrientation, plateau);
    }

    private static String getMovementPlan(Scanner scanner, Rover rover, int roverCount) {
        System.out.print("Rover " + roverCount + " Movement Plan: ");
        String movementPlan = scanner.nextLine();
        return movementPlan;
    }

    private static void executeMovementPlan(Rover rover, String movementPlan) {
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
    }


}
