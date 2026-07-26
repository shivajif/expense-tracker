package com.shiva.expense_tracker.service;

import com.shiva.expense_tracker.dto.CategoryReportResponse;
import com.shiva.expense_tracker.dto.MonthlyReportResponse;
import com.shiva.expense_tracker.dto.YearlyReportResponse;
import com.shiva.expense_tracker.entity.Transaction;
import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.enums.TransactionType;
import com.shiva.expense_tracker.repository.TransactionRepository;
import com.shiva.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    // =========================
    // MONTHLY REPORT
    // =========================

    public MonthlyReportResponse getMonthlyReport(int userId,
                                                  int month,
                                                  int year) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Transaction> incomes =
                transactionRepository.findByUserAndTypeAndTransactionDateBetween(
                        user,
                        TransactionType.INCOME,
                        startDate,
                        endDate);

        List<Transaction> expenses =
                transactionRepository.findByUserAndTypeAndTransactionDateBetween(
                        user,
                        TransactionType.EXPENSE,
                        startDate,
                        endDate);

        double totalIncome = incomes.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpense = expenses.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalIncome - totalExpense;

        return new MonthlyReportResponse(
                totalIncome,
                totalExpense,
                balance,
                startDate.getMonth().toString(),
                year
        );
    }

    // =========================
    // YEARLY REPORT
    // =========================

    public YearlyReportResponse getYearlyReport(int userId,
                                                int year) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        List<Transaction> incomes =
                transactionRepository.findByUserAndTypeAndTransactionDateBetween(
                        user,
                        TransactionType.INCOME,
                        startDate,
                        endDate);

        List<Transaction> expenses =
                transactionRepository.findByUserAndTypeAndTransactionDateBetween(
                        user,
                        TransactionType.EXPENSE,
                        startDate,
                        endDate);

        double totalIncome = incomes.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpense = expenses.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalIncome - totalExpense;

        return new YearlyReportResponse(
                totalIncome,
                totalExpense,
                balance,
                year
        );
    }

    // =========================
    // CATEGORY REPORT
    // =========================

    public CategoryReportResponse getCategoryReport(int userId,
                                                    int month,
                                                    int year) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Transaction> expenses =
                transactionRepository.findByUserAndTypeAndTransactionDateBetween(
                        user,
                        TransactionType.EXPENSE,
                        startDate,
                        endDate);

        Map<String, Double> categoryTotals = new HashMap<>();

        for (Transaction transaction : expenses) {

            String categoryName = transaction.getCategory().getName();

            categoryTotals.put(
                    categoryName,
                    categoryTotals.getOrDefault(categoryName, 0.0)
                            + transaction.getAmount()
            );
        }

        return new CategoryReportResponse(categoryTotals);
    }

}