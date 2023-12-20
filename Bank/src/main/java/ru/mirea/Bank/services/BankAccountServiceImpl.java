package ru.mirea.Bank.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.Bank.dao.BankAccountDAO;
import ru.mirea.Bank.dao.UserDAO;
import ru.mirea.Bank.dto.Request.AddMoneyOnBankAccountRequest;
import ru.mirea.Bank.dto.Request.BankAccountRequest;
import ru.mirea.Bank.dto.Response.BankAccountResponse;
import ru.mirea.Bank.dto.UserModel;
import ru.mirea.Bank.entities.BankAccountEntity;
import ru.mirea.Bank.entities.UserEntity;
import ru.mirea.Bank.repositories.BankAccountRepository;
import ru.mirea.Bank.repositories.UserRepository;
import ru.mirea.Bank.security.jwt.JwtService;
@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountDAO bankAccountDAO;
    private final UserDAO userDAO;
    private final JwtService jwtService;

    @Override
    public void createBankAccount(String jwt) {
        UserModel userModel=jwtService.parseToken(jwt);

        BankAccountEntity accountEntity=BankAccountEntity.builder()
                .accountUser(userDAO.findByEmail(userModel.getEmail()))
                .balance(0)
                .build();
        bankAccountDAO.save(accountEntity);
    }

    @Override
    public void addMoney(String jwt, AddMoneyOnBankAccountRequest request) {
        String email=jwtService.parseToken(jwt).getEmail();
        int id=userDAO.findByEmail(email).getId();
        bankAccountDAO.addMoney(request.getSum(), id);
    }

    @Override
    public BankAccountResponse getAccount(String jwt) {
        String email=jwtService.parseToken(jwt).getEmail();
        int id=userDAO.findByEmail(email).getId();
        return new BankAccountResponse(bankAccountDAO.findById(id).getBalance());
    }


}
