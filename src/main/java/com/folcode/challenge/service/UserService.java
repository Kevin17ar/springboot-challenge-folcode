package com.folcode.challenge.service;

import com.folcode.challenge.dto.UserDTO;
import com.folcode.challenge.dto.UserParamDTO;
import com.folcode.challenge.dto.UserUpdateDTO;
import com.folcode.challenge.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    void createUser(UserParamDTO userParamDTO);

    Optional<UserDTO> findById(Long userId);

    List<UserDTO> findAll();

    void deleteById(Long userId);

    void updateById(Long userId, UserUpdateDTO userUpdateDTO);
}
