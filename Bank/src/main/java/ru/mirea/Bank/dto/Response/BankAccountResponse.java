package ru.mirea.Bank.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccountResponse {
    private int balance;
}
