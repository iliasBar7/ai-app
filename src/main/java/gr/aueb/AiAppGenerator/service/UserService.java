package gr.aueb.AiAppGenerator.service;

import gr.aueb.AiAppGenerator.core.exceptions.BusinessException;
import gr.aueb.AiAppGenerator.dto.UserInsertDTO;
import gr.aueb.AiAppGenerator.dto.UserReadOnlyDTO;
import gr.aueb.AiAppGenerator.mapper.UserMapper;
import gr.aueb.AiAppGenerator.model.User;
import gr.aueb.AiAppGenerator.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);



    @Transactional
    public UserReadOnlyDTO createUser(UserInsertDTO dto) throws BusinessException {
        try{
            if(userRepository.existsByUsername(dto.username())){
                throw new BusinessException(1001,"Username already exists");
            }
            User user = userMapper.mapToUser(dto);
            User savedUser = userRepository.save(user);
            return userMapper.mapToReadOnlyDTO(savedUser);

        } catch (BusinessException e) {
            throw new BusinessException(1002,"Failed to create user: " + e.getMessage());
        }
    }

    // Retrieve a user and map to read-only DTO.
    public Optional<UserReadOnlyDTO> getUserByUsername(String username) throws BusinessException {
        try{
            return userRepository.findByUsername(username)
                    .map(userMapper::mapToReadOnlyDTO);


        } catch (Exception e) {
            logger.error("Failed to retrieve user with {} : {}",username,e.getMessage());
            throw new BusinessException(1003,"Failed to retrieve user: " + e.getMessage());
        }
    }





    }



