package gr.aueb.AiAppGenerator.service;

import gr.aueb.AiAppGenerator.dto.OllamaRequestDTO;
import gr.aueb.AiAppGenerator.dto.OllamaResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class RecipeService {

    private final WebClient webClient;


    public RecipeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:11434").build();
    }

    public Mono<String> createRecipe(String ingredients,
                                     String cuisine,
                                     String dietaryRestrictions) {

       String prompt = String.format( """
                I want to create a recipe using the following ingredients: %s.
                The cuisine type I prefer is %s.
                Please consider the following dietary restrictions: %s.
                Please provide me with a detailed recipe including title, list of ingredients, and cooking instructions
                """,ingredients, cuisine, dietaryRestrictions);



        OllamaRequestDTO requestDTO = new OllamaRequestDTO("llama3",prompt,false);

        return webClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(OllamaResponseDTO.class)
                .map(OllamaResponseDTO::response)
                .onErrorReturn("Failed to generate recipe from Ollama.");


    }
}
