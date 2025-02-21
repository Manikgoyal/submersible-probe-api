package com.example.probe.commands;

import com.example.probe.models.Grid;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Moves the probe backward based on its current direction.
 */
public class MoveBackwardCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(MoveBackwardCommand.class);

    public void execute(Probe probe, Grid grid) {
        Position newPosition = probe.getDirection().moveBackward(probe.getX(), probe.getY());
        if (grid.isValidPosition(newPosition)) {
            probe.setX(newPosition.getX());
            probe.setY(newPosition.getY());
        } else {
            System.out.println("Cannot move backward: Obstacle or boundary detected.");
        }
    }
}
