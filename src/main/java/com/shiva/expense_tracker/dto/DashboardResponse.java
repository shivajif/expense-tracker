package com.shiva.expense_tracker.dto;


import java.util.Map;



public class DashboardResponse {


    private double totalIncome;


    private double totalExpense;


    private double balance;


    private Map<String,Double> categorySpending;




    public DashboardResponse(){

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



    public Map<String, Double> getCategorySpending() {
        return categorySpending;
    }


    public void setCategorySpending(
            Map<String, Double> categorySpending
    ) {
        this.categorySpending = categorySpending;
    }

}