package com.example.probe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandRequest {
    @NotBlank(message = "Commands cannot be empty or null.")
    private String commands;
}