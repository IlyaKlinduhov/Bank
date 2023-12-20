package ru.mirea.Bank.services;

import ru.mirea.Bank.dto.Request.TransactionRequest;
import ru.mirea.Bank.entities.TransactionEntity;

public interface TransactionService {
    void moveMoney(String jwt, TransactionRequest request);
}
