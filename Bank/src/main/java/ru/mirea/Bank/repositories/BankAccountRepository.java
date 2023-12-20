package ru.mirea.Bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.Bank.entities.BankAccountEntity;
import ru.mirea.Bank.entities.UserEntity;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {
    BankAccountEntity findBankAccountEntityByAccountUser(UserEntity user);
    @Transactional
    @Modifying
    @Query("UPDATE BankAccountEntity e SET e.balance=e.balance+ :amount WHERE e.id=:entityID")
    void addMoney(@Param("amount") int amount, @Param("entityID") int id);

    @Transactional
    @Modifying
    @Query("UPDATE BankAccountEntity e SET e.balance=e.balance- :amount WHERE e.id=:entityID")
    void deleteMoney(@Param("amount") int amount, @Param("entityID") int id);

}
