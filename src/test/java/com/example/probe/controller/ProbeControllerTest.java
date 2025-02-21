package com.example.probe.controller;

import com.example.probe.dto.CommandRequest;
import com.example.probe.models.Position;
import com.example.probe.models.Probe;
import com.example.probe.service.IProbeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProbeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IProbeService probeService;

    @InjectMocks
    private ProbeController probeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(probeController).build();
    }

    @Test
    void testExecuteCommands() throws Exception {
        CommandRequest request = new CommandRequest("FFL");
        Probe probe = new Probe();
        when(probeService.executeCommands(any(CommandRequest.class))).thenReturn(probe);

        mockMvc.perform(post("/probe/execute")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"commands\":\"FFL\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetVisitedPositions() throws Exception {
        when(probeService.getVisitedPositions()).thenReturn(List.of(new Position(0, 1), new Position(1, 2)));

        mockMvc.perform(get("/probe/visited"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}