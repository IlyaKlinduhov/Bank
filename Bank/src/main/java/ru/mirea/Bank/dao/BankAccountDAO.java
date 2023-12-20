package ru.mirea.Bank.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.Bank.entities.BankAccountEntity;
import ru.mirea.Bank.entities.UserEntity;
import ru.mirea.Bank.repositories.BankAccountRepository;

@Service
@RequiredArgsConstructor
public class BankAccountDAO {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountEntity save(BankAccountEntity bankAccountEntity){
        return bankAccountRepository.save(bankAccountEntity);
    }
    public BankAccountEntity findById(Integer bankAccountId){
        return bankAccountRepository.findById(bankAccountId).orElse(null);
    }
    public BankAccountEntity getBankAccount(UserEntity user){
        return bankAccountRepository.findBankAccountEntityByAccountUser(user);
    }
    public void deleteById(Integer id){
        bankAccountRepository.deleteById(id);
    }
    public void addMoney(int amount, Integer id){
        bankAccountRepository.addMoney(amount,id);
    }

    public void deleteMoney(int amount, Integer id){
        bankAccountRepository.deleteMoney(amount,id);
    }
}
