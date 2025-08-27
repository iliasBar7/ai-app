package gr.aueb.AiAppGenerator.dto;

import jakarta.validation.constraints.NotBlank;

//for create/update a recipe
public record RecipeRequestDTO(
        @NotBlank(message = "Title required")
        String title,
        String description,
        @NotBlank(message = "Ingredients required")
        String ingredients,
        @NotBlank(message = "instructions required")
        String instructions,
        @NotBlank
        String username

) {}
