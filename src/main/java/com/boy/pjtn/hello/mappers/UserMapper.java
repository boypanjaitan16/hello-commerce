package com.boy.pjtn.hello.mappers;

import org.mapstruct.Mapper;
import com.boy.pjtn.hello.dtos.auth.UserResponse;
import com.boy.pjtn.hello.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponse toUserResponse(User user);
}
