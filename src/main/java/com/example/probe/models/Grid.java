package com.example.probe.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Model representing the grid where the probe moves.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grid {
    private int width;
    private int height;
    private Set<Position> obstacles;

    public boolean isValidPosition(Position position) {
        return !obstacles.contains(position) &&
                position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }
}

