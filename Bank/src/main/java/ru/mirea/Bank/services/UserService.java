package ru.mirea.Bank.services;

import ru.mirea.Bank.dto.Request.LoginRequest;
import ru.mirea.Bank.dto.Request.RegisterRequest;
import ru.mirea.Bank.dto.Response.LoginResponse;
import ru.mirea.Bank.dto.Response.RegisterResponse;
import ru.mirea.Bank.entities.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity getUserByEmail(String email);

    RegisterResponse Register(RegisterRequest registerRequest);

    LoginResponse Login(LoginRequest loginRequest);
}
