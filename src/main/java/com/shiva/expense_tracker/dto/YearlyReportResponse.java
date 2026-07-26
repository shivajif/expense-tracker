package com.shiva.expense_tracker.dto;

public class YearlyReportResponse {

    private double totalIncome;
    private double totalExpense;
    private double balance;
    private int year;

    public YearlyReportResponse() {
    }

    public YearlyReportResponse(double totalIncome, double totalExpense,
                                double balance, int year) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
        this.year = year;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}