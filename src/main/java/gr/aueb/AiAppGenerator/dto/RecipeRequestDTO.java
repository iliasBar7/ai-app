package gr.aueb.AiAppGenerator.dto;

import jakarta.validation.constraints.NotNull;

//for create/update a recipe
public record RecipeRequestDTO(
        @NotNull(message = "Title required")
        String title,
        String description,
        @NotNull(message = "Ingredients required")
        String ingredients,
        @NotNull(message = "instructions required")
        String instructions
) {}
