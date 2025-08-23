package gr.aueb.AiAppGenerator.dto;

import gr.aueb.AiAppGenerator.core.enums.Role;
import jakarta.validation.constraints.*;
import org.aspectj.weaver.ast.Not;

public record UserInsertDTO(
        @NotNull(message = "username is required")
         String username,
         @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\\\d)(?=.*?[@#$!%&*]).{8,}$",
         message = "Invalid Password")
         String password,
         @Email
         String email
) {}
