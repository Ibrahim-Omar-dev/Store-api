package com.Store_api.store.mapper;

import com.Store_api.store.dto.User.RegisterUserRequest;
import com.Store_api.store.dto.User.UpdateUserDto;
import com.Store_api.store.dto.User.UserDto;
import com.Store_api.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring" ,
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);
    void update(UpdateUserDto  updateUserDto, @MappingTarget User user);
}
