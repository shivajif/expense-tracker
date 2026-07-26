package com.shiva.expense_tracker.controller;


import com.shiva.expense_tracker.dto.DashboardResponse;
import com.shiva.expense_tracker.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {


    @Autowired
    private DashboardService dashboardService;



    @GetMapping
    public DashboardResponse getDashboard(
            Authentication authentication
    ){

        return dashboardService.getDashboard(
                authentication.getName()
        );

    }

}