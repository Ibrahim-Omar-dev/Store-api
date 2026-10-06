package com.Store_api.store.controllers;
import java.util.Map;

import com.Store_api.store.Services.UserService;
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
    private final UserService userService;
    @GetMapping
    public Iterable<UserDto> getAll(
            @RequestParam(required = false, name = "sort") String sortBy) {
        return userService.getAll(sortBy);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable Long id)
    {
        var userDto=userService.getOne(id);
        return  ResponseEntity.ok(userDto);
    }
    @PostMapping
    public ResponseEntity<?> create( @Valid @RequestBody RegisterUserRequest request
           , UriComponentsBuilder  builder)
    {
        var userDto=userService.create(request);
        var uri = builder.path("/user/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
    @PatchMapping
    public ResponseEntity<UserDto> update(@RequestBody UpdateUserDto request, @RequestParam Long id)
    {
        var userDto=userService.update(request,id);
        return ResponseEntity.ok(userDto);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long id)
    {
        userService.delete(id);
        return ResponseEntity.noContent().build();

    }
    @PostMapping("/{id}/changing-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody ChangeUserPasswordDto request) {
        userService.changePassword(id, request);
        return ResponseEntity.noContent().build();
    }
}
