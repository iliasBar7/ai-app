package gr.aueb.AiAppGenerator.authentication;

import gr.aueb.AiAppGenerator.dto.AuthenticationRequestDTO;
import gr.aueb.AiAppGenerator.dto.AuthenticationResponseDTO;
import gr.aueb.AiAppGenerator.model.User;
import gr.aueb.AiAppGenerator.repository.UserRepository;
import gr.aueb.AiAppGenerator.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto) {
        //Create an authentication token from username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(),dto.password())
        );

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not authorized "));

        // if authentication was successfull , generate JWT token

        String token = jwtService.generateToken(authentication.getName(),user.getRole().name());
        return new AuthenticationResponseDTO(token, user.getUsername());
    }
}
