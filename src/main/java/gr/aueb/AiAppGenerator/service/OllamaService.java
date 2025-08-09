package gr.aueb.AiAppGenerator.service;
import gr.aueb.AiAppGenerator.dto.OllamaResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:11434").build();
    }

    public String askLlama(String prompt) {
        OllamaResponseDTO ollamaResponseDTO = webClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                        "model", "llama3",
                        "prompt", prompt,
                        "stream", false
                ))
                .retrieve()
                .bodyToMono(OllamaResponseDTO.class)
                .block();

        return ollamaResponseDTO != null ? ollamaResponseDTO.response() : "No response from model.";
    }
}
