package com.shiva.expense_tracker.repository;


import com.shiva.expense_tracker.entity.Transaction;
import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.enums.TransactionType;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction,Integer> {



    // ==========================
    // BASIC TRANSACTION
    // ==========================


    List<Transaction> findByUser(User user);



    Optional<Transaction> findByIdAndUser(
            Integer id,
            User user
    );




    // ==========================
    // TYPE FILTER
    // ==========================


    List<Transaction> findByUserAndType(
            User user,
            TransactionType type
    );





    // ==========================
    // REPORTS
    // ==========================


    List<Transaction> findByUserAndTransactionDateBetween(
            User user,
            LocalDate startDate,
            LocalDate endDate
    );



    List<Transaction> findByUserAndTypeAndTransactionDateBetween(
            User user,
            TransactionType type,
            LocalDate startDate,
            LocalDate endDate
    );





    // ==========================
    // CATEGORY REPORT
    // ==========================


    List<Transaction> findByUserAndCategoryId(
            User user,
            Integer categoryId
    );






    // ==========================
    // RECENT TRANSACTIONS
    // ==========================


    List<Transaction> findTop5ByUserOrderByIdDesc(
            User user
    );






    // ==========================
    // SEARCH
    // ==========================


    List<Transaction> findByUserAndTitleContainingIgnoreCase(
            User user,
            String title
    );







    // ==========================
    // PAGINATION
    // ==========================


    Page<Transaction> findByUser(
            User user,
            Pageable pageable
    );







    // ==========================
    // SORTING
    // ==========================


    Page<Transaction> findByUserOrderByAmountDesc(
            User user,
            Pageable pageable
    );


}