package com.Store_api.store.mapper;

import com.Store_api.store.dto.RegisterUserRequest;
import com.Store_api.store.dto.UserDto;
import com.Store_api.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" ,
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);
}
