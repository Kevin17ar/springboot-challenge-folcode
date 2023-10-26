package com.folcode.challenge.mapper;

import com.folcode.challenge.dto.UserDTO;
import com.folcode.challenge.dto.UserParamDTO;
import com.folcode.challenge.dto.UserUpdateDTO;
import com.folcode.challenge.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userParamDTOtoUser(UserParamDTO userParamDTO);

    UserDTO userToUserDTO(User user);

    User userUpdateDTOToUser(UserUpdateDTO userUpdateDTO);
}
