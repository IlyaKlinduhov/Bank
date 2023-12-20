package ru.mirea.Bank.dao;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import ru.mirea.Bank.entities.UserEntity;
import ru.mirea.Bank.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDAO {
    private final UserRepository userRepository;

    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    public UserEntity findById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }


}
