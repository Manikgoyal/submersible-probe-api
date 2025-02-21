package com.example.probe.commands;

import com.example.probe.models.Grid;
import com.example.probe.models.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Turns the probe right.
 */
public class TurnRightCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(TurnRightCommand.class);

    @Override
    public void execute(Probe probe, Grid grid) {
        probe.setDirection(probe.getDirection().turnRight());
        logger.info("Turning right. New direction: {}", probe.getDirection());
    }
}
