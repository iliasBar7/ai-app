package gr.aueb.AiAppGenerator.controller;


import gr.aueb.AiAppGenerator.core.exceptions.BusinessException;
import gr.aueb.AiAppGenerator.dto.UserInsertDTO;
import gr.aueb.AiAppGenerator.dto.UserReadOnlyDTO;
import gr.aueb.AiAppGenerator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/register")
    public ResponseEntity<UserReadOnlyDTO> registerUser(@RequestBody UserInsertDTO userInsertDTO) {
        return userService.
    }
}
