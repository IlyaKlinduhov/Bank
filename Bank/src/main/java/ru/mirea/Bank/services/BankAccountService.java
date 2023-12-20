package ru.mirea.Bank.services;

import ru.mirea.Bank.dto.Request.AddMoneyOnBankAccountRequest;
import ru.mirea.Bank.dto.Request.BankAccountRequest;
import ru.mirea.Bank.dto.Response.BankAccountResponse;
import ru.mirea.Bank.entities.BankAccountEntity;

public interface BankAccountService {
    void createBankAccount(String jwt);

    void addMoney(String jwt, AddMoneyOnBankAccountRequest request);

    BankAccountResponse getAccount(String jwt);
}
