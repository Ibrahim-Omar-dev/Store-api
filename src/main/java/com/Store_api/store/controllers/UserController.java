package com.Store_api.store.controllers;
import java.util.Map;
import com.Store_api.store.dto.User.ChangeUserPasswordDto;
import com.Store_api.store.dto.User.RegisterUserRequest;
import com.Store_api.store.dto.User.UpdateUserDto;
import com.Store_api.store.dto.User.UserDto;
import com.Store_api.store.mapper.UserMapper;
import com.Store_api.store.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @GetMapping
    public Iterable<UserDto> getAll(
            @RequestParam(required = false, name = "sort") String sortBy) {
        if (sortBy == null || !Set.of("email", "name").contains(sortBy)) {
            sortBy = "name";
        }

        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(user -> userMapper.toDto(user))
                .toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable Long id)
    {
        var user=userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(userMapper.toDto(user));
    }
    @PostMapping
    public ResponseEntity<?> create( @Valid @RequestBody RegisterUserRequest request
           , UriComponentsBuilder  builder)
    {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("email", "Email already exists"));
        }
        var user= userMapper.toEntity(request);
        userRepository.save(user);
        var userDto=userMapper.toDto(user);
        var uri = builder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
    @PatchMapping
    public ResponseEntity<UserDto> update(@RequestBody UpdateUserDto request, @RequestParam Long id)
    {
        var user=userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        userMapper.update(request,user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id)
    {
        var user=userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        userRepository.delete(user);
        return ResponseEntity.noContent().build();

    }
    @PostMapping("/{id}/changing-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id,
                                             @RequestBody ChangeUserPasswordDto request)
    {
        var user=userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        if (!user.getPassword().equals(request.getOldPassword()))
            return ResponseEntity.badRequest().build();
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

}
