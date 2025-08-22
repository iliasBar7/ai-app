package gr.aueb.AiAppGenerator.controller;


import gr.aueb.AiAppGenerator.core.exceptions.BusinessException;
import gr.aueb.AiAppGenerator.dto.UserInsertDTO;
import gr.aueb.AiAppGenerator.dto.UserReadOnlyDTO;
import gr.aueb.AiAppGenerator.model.User;
import gr.aueb.AiAppGenerator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<UserReadOnlyDTO> getUserByUsername(@PathVariable String username) throws BusinessException {
            return userService.getUserByUsername(username)
                    .map(ResponseEntity::ok) // if present , status 200 ok else proceed to orElseThrow
                    .orElseThrow(()->new BusinessException(1010,"user not found with" + username));

    }
    // Endpoint User Registration
    @PostMapping("/register")
    public ResponseEntity<UserReadOnlyDTO> registerUser(@AuthenticationPrincipal User user, @RequestBody UserInsertDTO userInsertDTO) throws BusinessException {


        UserReadOnlyDTO userReadOnlyDTO = userService.createUser(userInsertDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(userReadOnlyDTO);



    }
}
