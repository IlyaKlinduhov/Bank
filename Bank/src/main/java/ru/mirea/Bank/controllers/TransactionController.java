package ru.mirea.Bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Bank.dto.Request.TransactionRequest;
import ru.mirea.Bank.services.TransactionService;

@RestController
@RequestMapping("/v1/tr")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/maketr")
    public void doTransaction(@CookieValue(name = "jwt") String jwt, TransactionRequest request){
        transactionService.moveMoney(jwt, request);
    }


}
