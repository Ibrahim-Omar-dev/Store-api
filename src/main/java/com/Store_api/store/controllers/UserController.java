package com.Store_api.store.controllers;

import com.Store_api.store.dto.RegisterUserRequest;
import com.Store_api.store.dto.UserDto;
import com.Store_api.store.entities.User;
import com.Store_api.store.mapper.UserMapper;
import com.Store_api.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
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
    public ResponseEntity<UserDto> create(@RequestBody RegisterUserRequest request
           , UriComponentsBuilder  builder)
    {
        var user= userMapper.toEntity(request);
        userRepository.save(user);
        var userDto=userMapper.toDto(user);
        var uri = builder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
}
