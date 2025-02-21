package com.example.probe.service;

import com.example.probe.dto.CommandRequest;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;

import java.util.List;

public interface IProbeService {
    Probe executeCommands(CommandRequest request);

    List<Position> getVisitedPositions();
}
