package com.example.probe.commands;


import com.example.probe.exception.CommandExecutionException;
import com.example.probe.models.Grid;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Handles execution of movement commands for the probe.
 */
public class CommandExecutor {
    private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);
    private final Map<Character, Command> commandMap;
    private final Set<Position> obstacles;

    public CommandExecutor(Set<Position> obstacles) {
        this.commandMap = new HashMap<>();
        this.obstacles = obstacles;
        commandMap.put('F', new MoveForwardCommand());
        commandMap.put('B', new MoveBackwardCommand());
        commandMap.put('L', new TurnLeftCommand());
        commandMap.put('R', new TurnRightCommand());
    }

    public void executeCommand(Probe probe, char command, Grid grid) {
        Command commandToExecute = commandMap.getOrDefault(command, new NoOpCommand());
        Position newPosition = getNextPosition(probe, command);

        if (grid.isValidPosition(newPosition) && !obstacles.contains(newPosition)) {
            logger.info("Executing command: {}", command);
            try {
                commandToExecute.execute(probe, grid);
            } catch (Exception e) {
                throw new CommandExecutionException("Failed to execute command: " + command, e);
            }
        } else {
            logger.warn("Obstacle detected or out of bounds. Probe stays at: ({}, {})", probe.getX(), probe.getY());
        }
    }

    private Position getNextPosition(Probe probe, char command) {
        return switch (command) {
            case 'F' -> probe.getDirection().moveForward(probe.getX(), probe.getY());
            case 'B' ->
                    probe.getDirection().moveBackward(probe.getX(), probe.getY()); // Turning does not change position
            default -> new Position(probe.getX(), probe.getY()); // Stay at the same position
        };
    }
}

