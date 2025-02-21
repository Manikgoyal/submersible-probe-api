package com.example.probe.commands;

import com.example.probe.models.Grid;
import com.example.probe.models.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles invalid or unknown commands.
 */
public class NoOpCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(NoOpCommand.class);

    @Override
    public void execute(Probe probe, Grid grid) {
        logger.warn("Invalid command received. No operation performed.");
    }
}
