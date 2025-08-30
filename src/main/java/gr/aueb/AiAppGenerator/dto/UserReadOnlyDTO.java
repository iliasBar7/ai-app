package gr.aueb.AiAppGenerator.dto;


import gr.aueb.AiAppGenerator.core.enums.Role;

public record UserReadOnlyDTO(
        Long id,
        String username,
        String email,
        Role role
        ) {}
