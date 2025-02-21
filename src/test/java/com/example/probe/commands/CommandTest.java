package com.example.probe.commands;

import static org.junit.jupiter.api.Assertions.*;

import com.example.probe.models.Grid;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import com.example.probe.models.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for command execution logic.
 */
class CommandTest {

    private Probe probe;
    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(10, 10, new HashSet<>());
        probe = new Probe(0, 0, Direction.NORTH, grid.getObstacles());
    }

    @Test
    void testMoveForwardCommand() {
        // Move the probe forward
        Command command = new MoveForwardCommand();
        command.execute(probe, grid);

        // Verify position update
        assertEquals(0, probe.getX(), "X-coordinate should remain unchanged");
        assertEquals(1, probe.getY(), "Y-coordinate should increase by 1");
    }

    @Test
    void testMoveBackwardCommand() {
        // Move the probe backward
        Command command = new MoveBackwardCommand();
        command.execute(probe, grid);

        // Verify position update
        assertEquals(0, probe.getX(), "X-coordinate should remain unchanged");
        assertEquals(0, probe.getY(), "Y-coordinate should decrease by 1");
    }

    @Test
    void testTurnLeftCommand() {
        // Turn the probe left
        Command command = new TurnLeftCommand();
        command.execute(probe, grid);

        // Verify direction update
        assertEquals(Direction.WEST, probe.getDirection(), "Probe should face WEST");
    }

    @Test
    void testTurnRightCommand() {
        // Turn the probe right
        Command command = new TurnRightCommand();
        command.execute(probe, grid);

        // Verify direction update
        assertEquals(Direction.EAST, probe.getDirection(), "Probe should face EAST");
    }

    @Test
    void testMoveIntoObstacle() {
        // Place an obstacle at (0,1)
        grid.getObstacles().add(new Position(0, 1));

        // Attempt to move forward into the obstacle
        Command command = new MoveForwardCommand();
        command.execute(probe, grid);

        // Verify that the probe does not move
        assertEquals(0, probe.getX(), "X-coordinate should remain unchanged");
        assertEquals(0, probe.getY(), "Y-coordinate should remain unchanged");
    }

    @Test
    void testMoveOutOfBounds() {
        // Move probe to the grid boundary
        probe.setY(9);

        // Attempt to move forward beyond the grid
        Command command = new MoveForwardCommand();
        command.execute(probe, grid);

        // Verify that the probe does not move out of bounds
        assertEquals(0, probe.getX(), "X-coordinate should remain unchanged");
        assertEquals(9, probe.getY(), "Y-coordinate should remain at the boundary");
    }

    @Test
    void testNoOpCommand() {
        // Execute a no-operation command
        Command command = new NoOpCommand();
        command.execute(probe, grid);

        // Verify that the probe's state remains unchanged
        assertEquals(0, probe.getX(), "X-coordinate should remain unchanged");
        assertEquals(0, probe.getY(), "Y-coordinate should remain unchanged");
        assertEquals(Direction.NORTH, probe.getDirection(), "Direction should remain NORTH");
    }
}

