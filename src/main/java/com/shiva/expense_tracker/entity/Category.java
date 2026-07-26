package com.shiva.expense_tracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(
        name="categories",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"name","user_id"}
                )
        }
)
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @NotBlank(message = "Category name required")
    private String name;



    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;




    public Category(){

    }



    public int getId(){
        return id;
    }


    public void setId(int id){
        this.id=id;
    }


    public String getName(){
        return name;
    }


    public void setName(String name){
        this.name=name;
    }


    public User getUser(){
        return user;
    }


    public void setUser(User user){
        this.user=user;
    }

}