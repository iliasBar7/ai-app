package gr.aueb.AiAppGenerator.mapper;

import gr.aueb.AiAppGenerator.dto.UserInsertDTO;
import gr.aueb.AiAppGenerator.dto.UserReadOnlyDTO;
import gr.aueb.AiAppGenerator.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {


   private final PasswordEncoder passwordEncoder;

    // Map User entity -> UserReadOnlyDTO
    public UserReadOnlyDTO toReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }


    public UserReadOnlyDTO mapToUserEntity(User user) {
        return toReadOnlyDTO(user);
    }

    // Map UserInsertDTO -> User entity (with encoded password)
    public  User fromInsertDTO(UserInsertDTO dto) {
        return User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .email(dto.email())
                .build();
    }



}
