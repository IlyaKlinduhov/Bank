package ru.mirea.Bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.Bank.dao.BankAccountDAO;
import ru.mirea.Bank.dao.TransactionDAO;
import ru.mirea.Bank.dao.UserDAO;
import ru.mirea.Bank.dto.Request.TransactionRequest;
import ru.mirea.Bank.entities.TransactionEntity;
import ru.mirea.Bank.repositories.BankAccountRepository;
import ru.mirea.Bank.repositories.TransactionRepositiry;
import ru.mirea.Bank.repositories.UserRepository;
import ru.mirea.Bank.security.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionDAO transactionDAO;
    private final UserDAO userDAO;
    private final BankAccountDAO bankAccountDAO;
    private final JwtService jwtService;

    @Override
    public void moveMoney(String jwt, TransactionRequest transactionRequest) {
        String destinationEmail=transactionRequest.getDestination();
        String sourceEmail=jwtService.parseToken(jwt).getEmail();
        int destinationId=userDAO.findByEmail(destinationEmail).getId();
        int sourceId=userDAO.findByEmail(sourceEmail).getId();
        bankAccountDAO.deleteMoney(transactionRequest.getAmount(), bankAccountDAO.findById(sourceId).getId());
        bankAccountDAO.addMoney(transactionRequest.getAmount(),bankAccountDAO.findById(destinationId).getId());
        TransactionEntity transactionEntity=TransactionEntity.builder()
                .source(bankAccountDAO.findById(sourceId))
                .destination(bankAccountDAO.findById(destinationId))
                .amountOfMoney(transactionRequest.getAmount())
                .build();

        transactionDAO.save(transactionEntity);
    }
}
