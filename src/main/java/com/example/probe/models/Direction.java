package com.example.probe.models;

/**
 * Enum representing the four possible directions the probe can face.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Moves forward based on the current direction.
     */
    public Position moveForward(int x, int y) {
        return switch (this) {
            case NORTH -> new Position(x, y + 1);
            case EAST -> new Position(x + 1, y);
            case SOUTH -> new Position(x, y - 1);
            case WEST -> new Position(x - 1, y);
        };
    }

    /**
     * Moves backward based on the current direction.
     */
    public Position moveBackward(int x, int y) {
        return switch (this) {
            case NORTH -> new Position(x, y - 1);
            case EAST -> new Position(x - 1, y);
            case SOUTH -> new Position(x, y + 1);
            case WEST -> new Position(x + 1, y);
        };
    }

    /**
     * Turns left from the current direction.
     */
    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }

    /**
     * Turns right from the current direction.
     */
    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
}
