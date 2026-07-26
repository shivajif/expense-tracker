package com.shiva.expense_tracker.controller;


import com.shiva.expense_tracker.dto.CategoryReportResponse;
import com.shiva.expense_tracker.dto.MonthlyReportResponse;
import com.shiva.expense_tracker.dto.YearlyReportResponse;
import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.repository.UserRepository;
import com.shiva.expense_tracker.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reports")
public class ReportController {


    @Autowired
    private ReportService reportService;


    @Autowired
    private UserRepository userRepository;




    // Monthly Report

    @GetMapping("/monthly")
    public MonthlyReportResponse getMonthlyReport(
            @RequestParam int month,
            @RequestParam int year,
            Authentication authentication
    ){


        User user =
                userRepository.findByEmail(
                        authentication.getName()
                );


        return reportService.getMonthlyReport(
                user.getId(),
                month,
                year
        );

    }




    // Yearly Report

    @GetMapping("/yearly")
    public YearlyReportResponse getYearlyReport(
            @RequestParam int year,
            Authentication authentication
    ){


        User user =
                userRepository.findByEmail(
                        authentication.getName()
                );


        return reportService.getYearlyReport(
                user.getId(),
                year
        );

    }




    // Category Report

    @GetMapping("/category")
    public CategoryReportResponse getCategoryReport(
            @RequestParam int month,
            @RequestParam int year,
            Authentication authentication
    ){


        User user =
                userRepository.findByEmail(
                        authentication.getName()
                );


        return reportService.getCategoryReport(
                user.getId(),
                month,
                year
        );

    }

}