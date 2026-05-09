package com.example.demo.api;

import com.example.demo.model.Expense;
import com.example.demo.model.ExpenseType;
import com.example.demo.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseRestController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getExpenses(@RequestParam(required = false) Long carId,
                                     @RequestParam(required = false) ExpenseType type) {
        return expenseService.getFilteredExpenses(carId, type);
    }

    @PostMapping
    public void addExpense(@RequestBody Expense expense) {
        expenseService.saveExpense(expense);
    }
}