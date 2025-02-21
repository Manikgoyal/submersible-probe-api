package com.example.probe.service;

import com.example.probe.dto.CommandRequest;
import com.example.probe.models.Direction;
import com.example.probe.models.Grid;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProbeServiceTest {

    @InjectMocks
    private ProbeService probeService;

    @Mock
    private Grid grid;

    @Mock
    private Probe probe;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        grid = new Grid(10, 10, new HashSet<>(List.of(
                new Position(2, 2)
        )));
        probe = new Probe(0, 0, Direction.NORTH, grid.getObstacles());
        probeService = new ProbeService(grid, probe);
    }

    @Test
    void testMoveForwardTwice() {
        CommandRequest request = new CommandRequest();
        request.setCommands("FF");
        Probe probe = probeService.executeCommands(request);
        assertEquals(new Position(0, 2), new Position(probe.getX(), probe.getY()));
    }

    @Test
    void testMoveForwardAndBackward() {
        CommandRequest request = new CommandRequest();
        request.setCommands("FB");
        Probe probe = probeService.executeCommands(request);
        assertEquals(new Position(0, 0), new Position(probe.getX(), probe.getY()));
    }

    @Test
    void testMoveInDifferentDirections() {
        CommandRequest request = new CommandRequest();
        request.setCommands("FFLBB");
        Probe probe = probeService.executeCommands(request);
        assertEquals(new Position(1, 2), new Position(probe.getX(), probe.getY()));
    }

    @Test
    void testTurnLeftAndMove() {
        CommandRequest request = new CommandRequest();
        request.setCommands("LFFF");
        Probe probe = probeService.executeCommands(request);
        assertEquals(new Position(0, 0), new Position(probe.getX(), probe.getY()));
    }

    @Test
    void testTurnRightAndMove() {
        CommandRequest request = new CommandRequest();
        request.setCommands("RFFF");
        Probe probe = probeService.executeCommands(request);
        assertEquals(new Position(3, 0), new Position(probe.getX(), probe.getY()));
    }

}