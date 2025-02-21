package com.example.probe.models;

import com.example.probe.commands.CommandExecutor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Model representing the probe, which moves within the grid.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Probe {
    private int x;
    private int y;
    private Direction direction;
    private List<Position> visitedPositions;

    @JsonIgnore
    private CommandExecutor commandExecutor;

    public Probe(int x, int y, Direction direction, Set<Position> obstacles) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.visitedPositions = new ArrayList<>();
        this.commandExecutor = new CommandExecutor(obstacles); // ✅ Initialize CommandExecutor
    }
}

