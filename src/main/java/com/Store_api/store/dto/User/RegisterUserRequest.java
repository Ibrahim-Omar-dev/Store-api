package com.Store_api.store.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name is require")
    private String name;
    @Email(message = "Email Not Valid")
    @NotBlank(message = "Email is require")
    private String email;
    @NotBlank(message = "Password is require")
    @Size(min = 6,max = 25,message = "password must between 6 and 25")
    private String password;
}
