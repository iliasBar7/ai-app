package gr.aueb.AiAppGenerator.controller;
import gr.aueb.AiAppGenerator.service.OllamaService;
import gr.aueb.AiAppGenerator.service.RecipeOllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class OllamaController {

    private final OllamaService ollamaService;
    private final RecipeOllamaService recipeOllamaService;

    @GetMapping("/ai-ask")
    public Mono<String> askAi(@RequestParam String prompt){
        return ollamaService.askLlama(prompt);
    }

    @GetMapping("/generate-recipe")
    public Mono<String> generateRecipe(
            @RequestParam String ingredients,
            @RequestParam String cuisine,
            @RequestParam String dietaryRestrictions
    ){
        return recipeOllamaService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

}
