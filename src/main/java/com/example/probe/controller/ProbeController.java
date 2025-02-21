package com.example.probe.controller;

import com.example.probe.dto.CommandRequest;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import com.example.probe.service.IProbeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller for managing the probe API endpoints.
 */
@RestController
@RequestMapping("/probe")
@AllArgsConstructor
public class ProbeController {

    private final IProbeService probeService;

    /**
     * Executes movement commands on the probe.
     *
     * @param request The command request containing movement instructions.
     * @return The updated state of the probe after executing commands.
     */
    @PostMapping("/execute")
    public ResponseEntity<Probe> executeCommands(@RequestBody CommandRequest request) {
        Probe probe = probeService.executeCommands(request);
        return ResponseEntity.ok(probe);
    }

    /**
     * Retrieves the list of positions visited by the probe.
     *
     * @return A list of visited positions.
     */
    @GetMapping("/visited")
    public ResponseEntity<List<Position>> getVisitedPositions() {
        List<Position> visitedPositions = probeService.getVisitedPositions();
        return ResponseEntity.ok(visitedPositions);
    }
}
