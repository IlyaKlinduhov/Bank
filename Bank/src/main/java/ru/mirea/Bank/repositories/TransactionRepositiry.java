package ru.mirea.Bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.Bank.entities.BankAccountEntity;
import ru.mirea.Bank.entities.TransactionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TransactionRepositiry extends JpaRepository<TransactionEntity,Integer> {
    List<TransactionEntity> findTransactionsBySource(BankAccountEntity source);
    List<TransactionEntity> findTransactionsByDestination(BankAccountEntity destination);
}
