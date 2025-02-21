package com.example.probe.commands;

import com.example.probe.models.Grid;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Moves the probe forward based on its current direction.
 */
public class MoveForwardCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(MoveForwardCommand.class);

    public void execute(Probe probe, Grid grid) {
        Position newPosition = probe.getDirection().moveForward(probe.getX(), probe.getY());
        if (grid.isValidPosition(newPosition)) {
            logger.info("Moving forward to position: ({}, {})", newPosition.getX(), newPosition.getY());
            probe.setX(newPosition.getX());
            probe.setY(newPosition.getY());
        } else {
            logger.warn("Cannot move forward: Obstacle or boundary detected.");
        }
    }
}
