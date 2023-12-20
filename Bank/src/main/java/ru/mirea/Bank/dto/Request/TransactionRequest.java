package ru.mirea.Bank.dto.Request;

import lombok.Data;

@Data
public class TransactionRequest {
    private String destination;
    private int amount;
}
