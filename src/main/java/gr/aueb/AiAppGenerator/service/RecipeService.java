package gr.aueb.AiAppGenerator.service;

import gr.aueb.AiAppGenerator.core.exceptions.BusinessException;
import gr.aueb.AiAppGenerator.dto.RecipeRequestDTO;
import gr.aueb.AiAppGenerator.dto.RecipeResponseDTO;
import gr.aueb.AiAppGenerator.mapper.RecipeMapper;
import gr.aueb.AiAppGenerator.model.Recipe;
import gr.aueb.AiAppGenerator.model.User;
import gr.aueb.AiAppGenerator.repository.RecipeRepository;
import gr.aueb.AiAppGenerator.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository repository;
    private final UserRepository userRepository;
    private final RecipeMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(RecipeService.class);


    @Transactional
    public RecipeResponseDTO create(RecipeRequestDTO dto) throws BusinessException {
        try {
            log.info("Creating recipe with title: {}", dto.title());

           User user = userRepository.findByUsername(dto.username())
                    .orElseThrow(()->  new BusinessException(2011,"User not found with username: " + dto.username()));

            Recipe recipe = mapper.mapToEntity(dto, user);
            Recipe saved = repository.save(recipe);
            log.debug("Recipe saved with id: {}", saved.getId());
            return mapper.mapToDto(saved);
        } catch (Exception e) {
            log.error("Error creating recipe", e);
            throw new BusinessException(2001, "Failed to create recipe");
        }
    }

    @Transactional
    public List<RecipeResponseDTO> getAll() throws BusinessException {
        try {
            log.info("Fetching all recipes");
            return repository.findAll()
                    .stream()
                    .map(mapper::mapToDto)
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching all recipes", e);
            throw new BusinessException(2002, "Failed to fetch recipes");
        }
    }

    public RecipeResponseDTO getById(Long id) throws BusinessException {
        try {
            log.info("Fetching recipe with id: {}", id);
            return repository.findById(id)
                    .map(mapper::mapToDto)
                    .orElseThrow(() -> {
                        log.warn("Recipe not found with id: {}", id);
                        return new BusinessException(2003, "Recipe not found");
                    });

        } catch (Exception e) {
            log.error("Error fetching recipe by id {}", id, e);
            throw new BusinessException(2004, "Unexpected error fetching recipe");
        }
    }

    @Transactional
    public RecipeResponseDTO update(Long id, RecipeRequestDTO dto) throws BusinessException {
        try {
            log.info("Updating recipe with id: {}", id);
            Recipe recipe = repository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("Recipe not found for update, id: {}", id);
                        return new BusinessException(1005, "Recipe not found for update");
                    });

            Recipe updated = mapper.updateEntity(recipe, dto);
            Recipe saved = repository.save(updated);
            log.debug("Recipe updated with id: {}", saved.getId());
            return mapper.mapToDto(saved);

        } catch (Exception e) {
            log.error("Error updating recipe with id {}", id, e);
            throw new BusinessException(2006, "Unexpected error updating recipe");
        }
    }

    public void delete(Long id) throws BusinessException {
        try {
            log.info("Deleting recipe with id: {}", id);
            if (!repository.existsById(id)) {
                log.warn("Recipe not found for delete, id: {}", id);
                throw new BusinessException(2007, "Recipe not found for delete");
            }
            repository.deleteById(id);
            log.debug("Recipe deleted with id: {}", id);

        } catch (Exception e) {
            log.error("Error deleting recipe with id {}", id, e);
            throw new BusinessException(2008, "Unexpected error deleting recipe");
        }
    }

    public List<RecipeResponseDTO> getByAuthor(User author) throws BusinessException {
        try{
            log.info("Fetching recipes for author: {}",author.getUsername());;
            return repository.findByAuthor(author)
                    .stream()
                    .map(mapper::mapToDto)
                    .toList();
        }catch (Exception e) {
            log.error("Error fetching recipes for author{}",author.getId(),e);
            throw new BusinessException(2009,"Failed to fetch recipes for author");
        }
    }
}
