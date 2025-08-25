package gr.aueb.AiAppGenerator.dto;

//return recipe to frontend
public record RecipeResponseDTO(
        Long id,
        String title,
        String description,
        String ingredients,
        String instructions
) {}
