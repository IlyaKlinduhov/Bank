package ru.mirea.Bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
    private String source;
    private String destination;
    private int sum;

}
