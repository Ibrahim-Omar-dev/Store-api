package com.Store_api.store.controllers;

import com.Store_api.store.Services.AuthServices;
import com.Store_api.store.Services.JwtServices;
import com.Store_api.store.dto.Auth.JwtResponse;
import com.Store_api.store.dto.Auth.LoginRequset;
import com.Store_api.store.dto.User.UserDto;
import com.Store_api.store.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthentationController {
    private final AuthServices  authServices;
    private final JwtServices jwtServices;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> Login(@Valid @RequestBody LoginRequset loginUserDto, HttpServletResponse response)
    {
       var token= authServices.Login(loginUserDto,response);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refresh(@CookieValue(value = "refreshToken") String token)
    {
        var accessToken=authServices.generateNewToken(token);
        return ResponseEntity.ok(new JwtResponse(accessToken));
    }
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        var userDto=authServices.getUser();
        return ResponseEntity.ok(userDto);
    }
}
