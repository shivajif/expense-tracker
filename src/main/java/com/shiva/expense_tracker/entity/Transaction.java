package com.shiva.expense_tracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shiva.expense_tracker.enums.TransactionType;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;


@Entity
@Table(name="transactions")
public class Transaction {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @NotBlank(message="Title required")
    private String title;




    @Min(value = 1,message="Amount must be greater than zero")
    private double amount;





    @NotNull(message="Transaction type required")
    @Enumerated(EnumType.STRING)
    private TransactionType type;




    private String description;



    @NotNull(message="Transaction date required")
    private LocalDate transactionDate;





    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;




    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;





    public Transaction(){

    }



    public Integer getId(){
        return id;
    }


    public void setId(Integer id){
        this.id=id;
    }



    public String getTitle(){
        return title;
    }


    public void setTitle(String title){
        this.title=title;
    }



    public double getAmount(){
        return amount;
    }


    public void setAmount(double amount){
        this.amount=amount;
    }



    public TransactionType getType(){
        return type;
    }


    public void setType(TransactionType type){
        this.type=type;
    }



    public String getDescription(){
        return description;
    }


    public void setDescription(String description){
        this.description=description;
    }



    public LocalDate getTransactionDate(){
        return transactionDate;
    }


    public void setTransactionDate(LocalDate transactionDate){
        this.transactionDate=transactionDate;
    }



    public Category getCategory(){
        return category;
    }


    public void setCategory(Category category){
        this.category=category;
    }



    public User getUser(){
        return user;
    }


    public void setUser(User user){
        this.user=user;
    }

}