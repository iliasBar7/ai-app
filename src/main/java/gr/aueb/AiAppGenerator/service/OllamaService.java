package gr.aueb.AiAppGenerator.service;
import gr.aueb.AiAppGenerator.dto.OllamaRequestDTO;
import gr.aueb.AiAppGenerator.dto.OllamaResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:11434").build();
    }

    public Mono<String> askLlama(String prompt) {
        OllamaRequestDTO requestDTO = new OllamaRequestDTO(
                "llama3",
                         prompt,
                false
        );

        return webClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(OllamaResponseDTO.class)
                .map(OllamaResponseDTO::response)
                .defaultIfEmpty("No response from model.")
                .onErrorReturn("Failed to reach Ollama.");
    }

}
