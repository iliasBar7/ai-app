package gr.aueb.AiAppGenerator.dto;
import jakarta.validation.constraints.*;

public record UserInsertDTO(
        @NotNull(message = "username is required")
         String username,
         @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\\\d)(?=.*?[@#$!%&*]).{8,}$",
         message = "Invalid Password")
         String password,
         @Email
         String email
) {}
