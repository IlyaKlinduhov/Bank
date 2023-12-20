package ru.mirea.Bank.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Bank.dto.BankAccountModel;
import ru.mirea.Bank.dto.Request.AddMoneyOnBankAccountRequest;
import ru.mirea.Bank.dto.Request.BankAccountRequest;
import ru.mirea.Bank.dto.Response.BankAccountResponse;
import ru.mirea.Bank.security.jwt.JwtService;
import ru.mirea.Bank.services.BankAccountService;
@Slf4j
@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final JwtService jwtService;

    @GetMapping("/get")
    public BankAccountResponse getBankAccount(@CookieValue(name = "jwt") String jwt){
        return bankAccountService.getAccount(jwt);
    }

    @PostMapping("/open")
    public void createBankAccount(@CookieValue(name = "jwt") String jwt){
        log.info("bibmba");
        bankAccountService.createBankAccount(jwt);
    }

    @PostMapping("/add")
    public void addMoneyOnBankAccount(@CookieValue(name = "jwt") String jwt,@RequestBody AddMoneyOnBankAccountRequest accountRequest){
        bankAccountService.addMoney(jwt, accountRequest);
    }


}
