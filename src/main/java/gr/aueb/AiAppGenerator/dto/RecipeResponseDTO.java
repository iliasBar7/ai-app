package gr.aueb.AiAppGenerator.dto;

import gr.aueb.AiAppGenerator.model.User;

//return recipe to frontend
public record RecipeResponseDTO(
        Long id,
        String title,
        String description,
        String ingredients,
        String instructions

) {}
