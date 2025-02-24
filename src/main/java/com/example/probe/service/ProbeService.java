package com.example.probe.service;

import com.example.probe.dto.CommandRequest;
import com.example.probe.exception.CommandExecutionException;
import com.example.probe.models.Direction;
import com.example.probe.models.Grid;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Service class responsible for executing commands on the probe.
 * Implements IProbeService and provides functionality to move the probe
 * within a predefined grid while avoiding obstacles.
 */
@Service
public class ProbeService implements IProbeService {

    private static final Logger logger = LoggerFactory.getLogger(ProbeService.class);
    private static final Set<Character> VALID_COMMANDS = Set.of('F', 'B', 'L', 'R');
    private final Grid grid;
    private final Probe probe;

    /**
     * Default constructor initializing a 10x10 grid with predefined obstacles.
     * Also initializes the probe at the starting position (0,0) facing NORTH.
     */
    public ProbeService() {
        this.grid = new Grid(10, 10, new HashSet<>(List.of(
                new Position(2, 2)
        )));
        this.probe = new Probe(0, 0, Direction.NORTH, grid.getObstacles());
    }

    /**
     * Constructor for dependency injection, allowing the grid and probe
     * to be injected externally for testing or alternative configurations.
     *
     * @param grid  The grid defining the movement boundaries and obstacles.
     * @param probe The probe that executes movement commands.
     */
    public ProbeService(Grid grid, Probe probe) {
        this.grid = grid;
        this.probe = probe;
    }

    /**
     * Executes a series of movement commands on the probe.
     * Commands are filtered to allow only valid movements (F, B, L, R).
     *
     * @param request The request containing movement commands as a string.
     * @return The updated probe after executing the commands.
     */
    @Override
    public Probe executeCommands(CommandRequest request) {
        request.getCommands()
                .chars()
                .mapToObj(c -> (char) c)
                .forEach(command -> {
                    logger.info("Executing command: {}", command);
                    try {
                        probe.getCommandExecutor().executeCommand(probe, command, grid);
                        probe.getVisitedPositions().add(new Position(probe.getX(), probe.getY()));
                    } catch (CommandExecutionException e) {
                        logger.error("Command execution failed: {}", e.getMessage(), e);
                    }
                });
        return probe;
    }

    /**
     * Retrieves the list of positions visited by the probe during movement.
     *
     * @return A list of positions the probe has traveled through.
     */
    @Override
    public List<Position> getVisitedPositions() {
        return probe.getVisitedPositions();
    }
}
