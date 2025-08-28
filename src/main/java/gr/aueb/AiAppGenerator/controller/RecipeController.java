package gr.aueb.AiAppGenerator.controller;

import gr.aueb.AiAppGenerator.core.exceptions.BusinessException;
import gr.aueb.AiAppGenerator.dto.RecipeRequestDTO;
import gr.aueb.AiAppGenerator.dto.RecipeResponseDTO;
import gr.aueb.AiAppGenerator.repository.UserRepository;
import gr.aueb.AiAppGenerator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final UserRepository userRepository; // DI for findByAuthor


    @PostMapping()
    public ResponseEntity<RecipeResponseDTO> createRecipe(@RequestBody @Valid RecipeRequestDTO requestDTO) throws BusinessException {
        RecipeResponseDTO response = recipeService.create(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO>getById(@PathVariable Long id) throws BusinessException {
        RecipeResponseDTO response = recipeService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    ResponseEntity<List<RecipeResponseDTO>> getAll() throws BusinessException {
        return ResponseEntity.ok(recipeService.getAll());

    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> update(
            @PathVariable Long id,
            @RequestBody RecipeRequestDTO requestDTO) throws BusinessException {
        RecipeResponseDTO response = recipeService.update(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> delete(@PathVariable Long id) throws BusinessException {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<RecipeResponseDTO>> getByAuthor(@PathVariable Long authorId) throws BusinessException {
        var author = userRepository.findById(authorId)
                .orElseThrow(()-> new BusinessException(2010,"Author not found"));

        return ResponseEntity.ok(recipeService.getByAuthor(author));
    }

}
