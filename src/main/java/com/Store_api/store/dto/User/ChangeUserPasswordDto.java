package com.Store_api.store.dto.User;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class ChangeUserPasswordDto {
    private String oldPassword;
    private String newPassword;
}
