package com.github.wolfanyy.springusermanagement.mapper;

import com.github.wolfanyy.springusermanagement.dto.UserRequest;
import com.github.wolfanyy.springusermanagement.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface UserMapper {

    User toEntity(UserRequest request);
}
