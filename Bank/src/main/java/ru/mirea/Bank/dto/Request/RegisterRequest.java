package ru.mirea.Bank.dto.Request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
