package com.Store_api.store.Services;

import com.Store_api.store.Exception.EmailAlreadyExistsException;
import com.Store_api.store.Exception.InvalidPasswordException;
import com.Store_api.store.Exception.UserNotFoundException;
import com.Store_api.store.dto.User.ChangeUserPasswordDto;
import com.Store_api.store.dto.User.RegisterUserRequest;
import com.Store_api.store.dto.User.UpdateUserDto;
import com.Store_api.store.dto.User.UserDto;
import com.Store_api.store.mapper.UserMapper;
import com.Store_api.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper  userMapper;
    private final PasswordEncoder passwordEncoder;
    public Iterable<UserDto> getAll(String sortBy)
    {
        if (sortBy == null || !Set.of("email", "name").contains(sortBy)) {
            sortBy = "name";
        }
        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    public UserDto getOne( Long id){
        var user=userRepository.findById(id).orElse(null);
        if(user == null)
            throw new UserNotFoundException();
        return userMapper.toDto(user);
    }
    public UserDto create(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }
    public UserDto update( UpdateUserDto request,  Long id) {
        var user=userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException();
        userMapper.update(request,user);
        userRepository.save(user);
        return  userMapper.toDto(user);
    }
    public void delete( Long id)
    {
        var user=userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException();
        userRepository.delete(user);
    }
    public void changePassword(Long id, ChangeUserPasswordDto request) {
        var user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new InvalidPasswordException();
        }

        user.setPassword(request.getNewPassword());
    }
}
