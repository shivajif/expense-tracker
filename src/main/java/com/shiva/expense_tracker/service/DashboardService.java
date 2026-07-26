package com.shiva.expense_tracker.service;


import com.shiva.expense_tracker.dto.DashboardResponse;
import com.shiva.expense_tracker.entity.Transaction;
import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.enums.TransactionType;
import com.shiva.expense_tracker.repository.TransactionRepository;
import com.shiva.expense_tracker.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class DashboardService {


    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private UserRepository userRepository;




    public DashboardResponse getDashboard(String email){


        User user = userRepository.findByEmail(email);



        if(user == null){

            throw new RuntimeException(
                    "User not found"
            );

        }



        List<Transaction> transactions =
                transactionRepository.findByUser(user);



        double totalIncome = 0;

        double totalExpense = 0;



        Map<String,Double> categorySpending =
                new HashMap<>();




        for(Transaction transaction : transactions){



            if(transaction.getType()
                    == TransactionType.INCOME){


                totalIncome += transaction.getAmount();


            }
            else if(transaction.getType()
                    == TransactionType.EXPENSE){



                totalExpense += transaction.getAmount();



                String categoryName =
                        transaction.getCategory().getName();



                categorySpending.put(
                        categoryName,
                        categorySpending.getOrDefault(
                                categoryName,
                                0.0
                        )
                                +
                                transaction.getAmount()
                );

            }

        }




        double balance =
                totalIncome - totalExpense;




        DashboardResponse response =
                new DashboardResponse();


        response.setTotalIncome(totalIncome);

        response.setTotalExpense(totalExpense);

        response.setBalance(balance);

        response.setCategorySpending(categorySpending);



        return response;

    }

}