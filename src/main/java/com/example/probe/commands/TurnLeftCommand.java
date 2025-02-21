package com.example.probe.commands;

import com.example.probe.models.Grid;
import com.example.probe.models.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Turns the probe left.
 */
public class TurnLeftCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(TurnLeftCommand.class);

    @Override
    public void execute(Probe probe, Grid grid) {
        probe.setDirection(probe.getDirection().turnLeft());
        logger.info("Turning left. New direction: {}", probe.getDirection());
    }
}
