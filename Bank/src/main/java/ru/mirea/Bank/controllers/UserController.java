package ru.mirea.Bank.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Bank.dto.Request.LoginRequest;
import ru.mirea.Bank.dto.Request.RegisterRequest;
import ru.mirea.Bank.dto.Response.LoginResponse;
import ru.mirea.Bank.dto.Response.RegisterResponse;
import ru.mirea.Bank.dto.UserModel;
import ru.mirea.Bank.security.jwt.JwtService;
import ru.mirea.Bank.services.UserService;
import ru.mirea.Bank.services.UserServiceImpl;
@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/getUser")
    public UserModel getUser(@RequestParam String email){
        return new UserModel(userService.getUserByEmail(email).getEmail(),userService.getUserByEmail(email).getPassword());
    }

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest){
        return userService.Register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse){
        LoginResponse loginResponse=userService.Login(loginRequest);
        Cookie cookie=new Cookie("jwt",loginResponse.getJwt());
        cookie.setPath("/v1");
        servletResponse.addCookie(cookie);
        return loginResponse;
    }
}
