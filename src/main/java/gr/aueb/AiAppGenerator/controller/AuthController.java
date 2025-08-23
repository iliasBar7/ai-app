package gr.aueb.AiAppGenerator.controller;

import gr.aueb.AiAppGenerator.authentication.AuthenticationService;
import gr.aueb.AiAppGenerator.dto.AuthenticationRequestDTO;
import gr.aueb.AiAppGenerator.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        LOGGER.info("Received authentication request for user: {}", authenticationRequestDTO.username());
       AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticate(authenticationRequestDTO);
        LOGGER.info("Authentication successful for user: {}", authenticationRequestDTO.username());
       return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
    }

}
