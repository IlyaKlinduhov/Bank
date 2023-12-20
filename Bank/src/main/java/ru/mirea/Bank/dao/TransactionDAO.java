package ru.mirea.Bank.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.Bank.entities.BankAccountEntity;
import ru.mirea.Bank.entities.TransactionEntity;
import ru.mirea.Bank.repositories.TransactionRepositiry;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDAO {
    private final TransactionRepositiry transactionRepositiry;

    public TransactionEntity save(TransactionEntity transactionEntity){
        return transactionRepositiry.save(transactionEntity);
    }

    public TransactionEntity findById(Integer id){
        return transactionRepositiry.findById(id).orElse(null);
    }

    public List<TransactionEntity> findTransactionsBySource(BankAccountEntity bankAccountEntity){
        return transactionRepositiry.findTransactionsBySource(bankAccountEntity);
    }
    public List<TransactionEntity> findTransactionsByDestination(BankAccountEntity bankAccountEntity){
        return transactionRepositiry.findTransactionsByDestination(bankAccountEntity);
    }

    public void deleteById(int id){
        transactionRepositiry.deleteById(id);
    }


}
