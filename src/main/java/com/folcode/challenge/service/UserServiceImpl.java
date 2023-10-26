package com.folcode.challenge.service;

import com.folcode.challenge.dto.UserDTO;
import com.folcode.challenge.dto.UserParamDTO;
import com.folcode.challenge.dto.UserUpdateDTO;
import com.folcode.challenge.entity.User;
import com.folcode.challenge.mapper.UserMapper;
import com.folcode.challenge.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Creates a new user entity in the database based on the provided UserDTO object.
     * This method uses MapStruct for mapping between the UserDTO and the User entity,
     * eliminating manual mapping boilerplate.
     *
     * @param userParamDTO The data transfer object containing the details of the user to be created.
     */
    @Override
    public void createUser(UserParamDTO userParamDTO) {
        User userEntity = UserMapper.INSTANCE.userParamDTOtoUser(userParamDTO);
        this.save(userEntity);
    }

    /**
     * Retrieves a user from the database based on the provided user ID
     * and converts the user entity to a UserDTO object, if found.
     *
     * @param userId The ID of the user to be retrieved.
     * @return An Optional containing the UserDTO object if the user is found, or an empty Optional if not.
     */
    @Override
    public Optional<UserDTO> findById(Long userId) {
        return userRepository.findById(userId).map(UserMapper.INSTANCE::userToUserDTO);
    }

    /**
     * Retrieves all users from the database and converts them to a list of UserDTO objects.
     * @return A list of UserDTO objects representing all the users in the database.
     */
    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Updates specific details of a user in the database based on the provided user ID
     * and the parameters specified in the UserUpdateDTO object.
     * Only the non-null fields from the UserUpdateDTO are updated on the user entity.
     * This ensures that only specified attributes get modified, preserving the existing data
     * for attributes not intended to be changed.
     *
     * @param userId The ID of the user to be updated.
     * @param userUpdateDTO The data transfer object containing the updated details for the user.
     * @throws ResponseStatusException If the user with the provided ID is not found in the database.
     */
    @Override
    public void updateById(Long userId, UserUpdateDTO userUpdateDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //User user = UserMapper.INSTANCE.userUpdateDTOToUser(userUpdateDTO);
        User userEntity = user.get();
        if (userUpdateDTO.getRequirementsMet() != null) userEntity.setRequirementsMet(userUpdateDTO.getRequirementsMet());
        if (userUpdateDTO.getName() != null) userEntity.setName(userUpdateDTO.getName());
        if (userUpdateDTO.getDni() != null) userEntity.setDni(userUpdateDTO.getDni());
        if (userUpdateDTO.getEmail() != null) userEntity.setEmail(userUpdateDTO.getEmail());
        if (userUpdateDTO.getNationality() != null) userEntity.setNationality(userUpdateDTO.getNationality());
        if (userUpdateDTO.getPhone() != null) userEntity.setPhone(userUpdateDTO.getPhone());
        if (userUpdateDTO.getLastName() != null) userEntity.setLastName(userUpdateDTO.getLastName());
        if (userUpdateDTO.getBirthDate() != null) userEntity.setBirthDate(userUpdateDTO.getBirthDate());

        this.save(userEntity);
    }
}
