package gr.aueb.AiAppGenerator.dto;

import jakarta.validation.constraints.NotNull;

public record AuthenticationRequestDTO(
        @NotNull
        String username,
        @NotNull
        String password) { }
