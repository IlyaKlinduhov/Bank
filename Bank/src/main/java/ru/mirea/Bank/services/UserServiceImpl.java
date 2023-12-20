package ru.mirea.Bank.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.Bank.dao.UserDAO;
import ru.mirea.Bank.dto.Request.LoginRequest;
import ru.mirea.Bank.dto.Request.RegisterRequest;
import ru.mirea.Bank.dto.Response.LoginResponse;
import ru.mirea.Bank.dto.Response.RegisterResponse;
import ru.mirea.Bank.dto.UserModel;
import ru.mirea.Bank.entities.UserEntity;
import ru.mirea.Bank.repositories.UserRepository;
import ru.mirea.Bank.security.jwt.JwtService;

import javax.security.auth.login.CredentialException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public RegisterResponse Register(RegisterRequest registerRequest) {
        UserEntity userEntity = UserEntity.builder().email(registerRequest.getEmail()).password(passwordEncoder.encode(registerRequest.getPassword())).build();
        userDAO.save(userEntity);

        UserModel userModel = UserModel.builder().email(registerRequest.getEmail()).build();

        return new RegisterResponse(jwtService.generateToken(userModel));
    }

    @Override
    @SneakyThrows
    public LoginResponse Login(LoginRequest loginRequest) {
        String email=loginRequest.getEmail();
        String password= loginRequest.getPassword();
        /*if(userRepository.findByEmail(email).){
            throw new UsernameNotFoundException("User not found");
        }*/

        UserModel userModel=UserModel.builder().email(email).password(password).build();

        if(!passwordEncoder.matches(password, userDAO.findByEmail(email).getPassword())){
            throw new CredentialException("Wrong username or password");
        }

        String jwt= jwtService.generateToken(userModel);

        return new LoginResponse(userModel.getEmail(),jwt);
    }
}
