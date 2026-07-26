package com.shiva.expense_tracker.dto;

import java.util.Map;

public class CategoryReportResponse {

    private Map<String, Double> categoryTotals;

    public CategoryReportResponse() {
    }

    public CategoryReportResponse(Map<String, Double> categoryTotals) {
        this.categoryTotals = categoryTotals;
    }

    public Map<String, Double> getCategoryTotals() {
        return categoryTotals;
    }

    public void setCategoryTotals(Map<String, Double> categoryTotals) {
        this.categoryTotals = categoryTotals;
    }
}