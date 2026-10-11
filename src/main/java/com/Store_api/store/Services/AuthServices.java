package com.Store_api.store.Services;

import com.Store_api.store.Exception.EmailNotFoundException;
import com.Store_api.store.Exception.InvalidPasswordException;
import com.Store_api.store.Exception.UnAuthorizationException;
import com.Store_api.store.Exception.UserNotFoundException;
import com.Store_api.store.dto.Auth.JwtResponse;
import com.Store_api.store.dto.Auth.LoginRequset;
import com.Store_api.store.dto.User.UserDto;
import com.Store_api.store.mapper.UserMapper;
import com.Store_api.store.repositories.AuthRepository;
import com.Store_api.store.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServices {
    private final AuthenticationManager authenticationManager;
    private final JwtServices jwtServices;
    private  final UserRepository userRepository;
    private final UserMapper userMapper;
    public String Login(LoginRequset loginRequset, HttpServletResponse response)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequset.getEmail(),
                        loginRequset.getPassword()
                )
        );
        var user= userRepository.findByEmail(loginRequset.getEmail()).orElse(null);
        if (user==null)
            throw  new UserNotFoundException();

        var accessToken=jwtServices.generateAccessToken(user);
        var refreshToken=jwtServices.generateRefreshToken(user);

        var cookie=new Cookie("refreshToken",refreshToken.toString());
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(7*24*60*60);
        cookie.setSecure(false);

        response.addCookie(cookie);

        return accessToken.toString();
    }
    public UserDto getUser()
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId=(Long)authentication.getPrincipal();
        var user=userRepository.findById(userId).orElse(null);
        if(user==null)
        {
            throw new EmailNotFoundException();
        }
        return userMapper.toDto(user);
    }
    public String generateNewToken(String token) {
        var jwt = jwtServices.parseToken(token);

        if (jwt == null || jwt.isExpired()) {
            throw new UnAuthorizationException();
        }

        var user = userRepository.findById(jwt.getUserId())
                .orElseThrow(() -> new UnAuthorizationException());

        var accessToken = jwtServices.generateAccessToken(user);

        return accessToken.toString();
    }
}
