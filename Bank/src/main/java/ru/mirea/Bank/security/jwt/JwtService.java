package ru.mirea.Bank.security.jwt;

import ru.mirea.Bank.dto.UserModel;

public interface JwtService {

    String generateToken(UserModel userModel);

    UserModel parseToken(String jwt);
}
