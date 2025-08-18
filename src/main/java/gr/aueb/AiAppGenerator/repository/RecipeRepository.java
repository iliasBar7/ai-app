package gr.aueb.AiAppGenerator.repository;

import gr.aueb.AiAppGenerator.model.Recipe;
import gr.aueb.AiAppGenerator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findByAuthor(User author);
}
