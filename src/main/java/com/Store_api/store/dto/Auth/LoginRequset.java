package com.Store_api.store.dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequset {
    @NotBlank(message = "Email require")
    @Email(message = "Email invalid")
    private String email;
    @NotBlank(message = "Password require")
    private String password;
}
