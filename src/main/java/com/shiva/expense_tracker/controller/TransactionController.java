package com.shiva.expense_tracker.controller;


import com.shiva.expense_tracker.entity.Transaction;
import com.shiva.expense_tracker.service.TransactionService;


import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController
@RequestMapping("/transactions")
public class TransactionController {



    @Autowired
    private TransactionService transactionService;







    // CREATE TRANSACTION

    @PostMapping
    public Transaction saveTransaction(
            @Valid @RequestBody Transaction transaction,
            Authentication authentication
    ){


        return transactionService.saveTransaction(
                transaction,
                authentication.getName()
        );

    }







    // GET ALL TRANSACTIONS

    @GetMapping
    public List<Transaction> getAllTransactions(
            Authentication authentication
    ){


        return transactionService.getAllTransactions(
                authentication.getName()
        );

    }







    // GET TRANSACTION BY ID

    @GetMapping("/{id}")
    public Transaction getTransactionById(
            @PathVariable int id,
            Authentication authentication
    ){


        return transactionService.getTransactionById(
                id,
                authentication.getName()
        );

    }







    // UPDATE TRANSACTION

    @PutMapping("/{id}")
    public Transaction updateTransaction(
            @PathVariable int id,
            @Valid @RequestBody Transaction transaction,
            Authentication authentication
    ){


        transaction.setId(id);


        return transactionService.updateTransaction(
                transaction,
                authentication.getName()
        );

    }







    // DELETE TRANSACTION

    @DeleteMapping("/{id}")
    public String deleteTransaction(
            @PathVariable int id,
            Authentication authentication
    ){


        transactionService.deleteTransaction(
                id,
                authentication.getName()
        );


        return "Transaction deleted successfully";

    }







    // FILTER BY TYPE

    @GetMapping("/type/{type}")
    public List<Transaction> getTransactionsByType(
            @PathVariable String type,
            Authentication authentication
    ){


        return transactionService.getTransactionsByUserAndType(
                authentication.getName(),
                Enum.valueOf(
                        com.shiva.expense_tracker.enums.TransactionType.class,
                        type.toUpperCase()
                )
        );

    }







    // RECENT TRANSACTIONS

    @GetMapping("/recent")
    public List<Transaction> getRecentTransactions(
            Authentication authentication
    ){


        return transactionService.getRecentTransactions(
                authentication.getName()
        );

    }







    // SEARCH TRANSACTIONS

    @GetMapping("/search")
    public List<Transaction> searchTransactions(
            @RequestParam String keyword,
            Authentication authentication
    ){


        return transactionService.searchTransactions(
                authentication.getName(),
                keyword
        );

    }







    // PAGINATION

    @GetMapping("/page")
    public Page<Transaction> pagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Authentication authentication
    ){


        return transactionService.getTransactionsPagination(
                authentication.getName(),
                page,
                size
        );

    }







    // SORTING

    @GetMapping("/sort")
    public Page<Transaction> sorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Authentication authentication
    ){


        return transactionService.getSortedTransactions(
                authentication.getName(),
                page,
                size
        );

    }


}