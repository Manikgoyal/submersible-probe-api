package com.example.probe.commands;

import com.example.probe.models.Grid;
import com.example.probe.models.Probe;

/**
 * Interface defining the command pattern for executing probe commands.
 */
public interface Command {
    void execute(Probe probe, Grid grid);
}
