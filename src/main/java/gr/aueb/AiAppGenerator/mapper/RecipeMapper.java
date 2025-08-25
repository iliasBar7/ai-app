package gr.aueb.AiAppGenerator.mapper;

import gr.aueb.AiAppGenerator.dto.RecipeRequestDTO;
import gr.aueb.AiAppGenerator.dto.RecipeResponseDTO;
import gr.aueb.AiAppGenerator.model.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

    public Recipe MapToEntity(RecipeRequestDTO dto) {
        return Recipe.builder()
                .title(dto.title())
                .description(dto.description())
                .ingredients(dto.ingredients())
                .instructions(dto.instructions())
                .build();
    }

    public RecipeResponseDTO MapToDto(Recipe entity) {
        return new RecipeResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getIngredients(),
                entity.getInstructions()
        );
    }

    public Recipe updateEntity(Recipe entity, RecipeRequestDTO dto) {
        // Create a new Recipe object by copying the existing one and updating fields from the DTO.
        return entity.toBuilder()
                .title(dto.title())
                .description(dto.description())
                .ingredients(dto.ingredients())
                .instructions(dto.instructions())
                .build();
    }

}
