package com.shiva.expense_tracker.service;


import com.shiva.expense_tracker.entity.Category;
import com.shiva.expense_tracker.entity.Transaction;
import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.enums.TransactionType;

import com.shiva.expense_tracker.repository.CategoryRepository;
import com.shiva.expense_tracker.repository.TransactionRepository;
import com.shiva.expense_tracker.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class TransactionService {



    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CategoryRepository categoryRepository;





    // ==========================
    // CREATE
    // ==========================

    public Transaction saveTransaction(
            Transaction transaction,
            String email
    ){

        User user =
                userRepository.findByEmail(email);


        if(user == null){
            throw new RuntimeException("User not found");
        }


        Category category =
                categoryRepository.findByIdAndUser(
                                transaction.getCategory().getId(),
                                user
                        )
                        .orElseThrow(
                                () -> new RuntimeException("Category not found")
                        );


        transaction.setUser(user);

        transaction.setCategory(category);


        return transactionRepository.save(transaction);

    }







    // ==========================
    // GET ALL
    // ==========================


    public List<Transaction> getAllTransactions(
            String email
    ){

        User user =
                userRepository.findByEmail(email);


        if(user == null){
            throw new RuntimeException("User not found");
        }


        return transactionRepository.findByUser(user);

    }







    // ==========================
    // GET BY ID
    // ==========================


    public Transaction getTransactionById(
            int id,
            String email
    ){

        User user =
                userRepository.findByEmail(email);


        if(user == null){
            throw new RuntimeException("User not found");
        }



        return transactionRepository
                .findByIdAndUser(id,user)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Transaction not found"
                        )
                );

    }







    // ==========================
    // UPDATE
    // ==========================


    public Transaction updateTransaction(
            Transaction transaction,
            String email
    ){

        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException("User not found");
        }



        Transaction existing =
                transactionRepository
                        .findByIdAndUser(
                                transaction.getId(),
                                user
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Transaction not found"
                                )
                        );



        Category category =
                categoryRepository
                        .findByIdAndUser(
                                transaction.getCategory().getId(),
                                user
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Category not found"
                                )
                        );



        existing.setTitle(transaction.getTitle());

        existing.setAmount(transaction.getAmount());

        existing.setDescription(transaction.getDescription());

        existing.setType(transaction.getType());

        existing.setTransactionDate(
                transaction.getTransactionDate()
        );

        existing.setCategory(category);



        return transactionRepository.save(existing);

    }








    // ==========================
    // DELETE
    // ==========================


    public void deleteTransaction(
            int id,
            String email
    ){


        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException("User not found");
        }



        Transaction transaction =
                transactionRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Transaction not found"
                                )
                        );



        transactionRepository.delete(transaction);

    }







    // ==========================
    // FILTER TYPE
    // ==========================


    public List<Transaction> getTransactionsByUserAndType(
            String email,
            TransactionType type
    ){

        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException("User not found");
        }



        return transactionRepository
                .findByUserAndType(
                        user,
                        type
                );

    }








    // ==========================
    // RECENT
    // ==========================


    public List<Transaction> getRecentTransactions(
            String email
    ){

        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException("User not found");
        }



        return transactionRepository
                .findTop5ByUserOrderByIdDesc(user);

    }








    // ==========================
    // SEARCH
    // ==========================


    public List<Transaction> searchTransactions(
            String email,
            String keyword
    ){

        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException("User not found");
        }



        return transactionRepository
                .findByUserAndTitleContainingIgnoreCase(
                        user,
                        keyword
                );

    }








    // ==========================
    // PAGINATION
    // ==========================


    public Page<Transaction> getTransactionsPagination(
            String email,
            int page,
            int size
    ){

        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException("User not found");
        }



        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );



        return transactionRepository
                .findByUser(
                        user,
                        pageable
                );

    }








    // ==========================
    // SORTING
    // ==========================


    public Page<Transaction> getSortedTransactions(
            String email,
            int page,
            int size
    ){

        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException("User not found");
        }



        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );



        return transactionRepository
                .findByUserOrderByAmountDesc(
                        user,
                        pageable
                );

    }


}