package com.example.demo.controller;

import com.example.demo.model.Expense;
import com.example.demo.model.ExpenseType;
import com.example.demo.service.CarService;
import com.example.demo.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CarService carService;

    @GetMapping("/expenses")
    public String showExpenses(Model model,
                               @RequestParam(required = false) Long carId,
                               @RequestParam(required = false) ExpenseType type) {

        List<Expense> filteredExpenses = expenseService.getFilteredExpenses(carId, type);
        Double totalAmount = filteredExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        model.addAttribute("expenses", filteredExpenses);
        model.addAttribute("totalAmount", totalAmount);


        model.addAttribute("newExpense", new Expense());
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("expenseTypes", ExpenseType.values());
        model.addAttribute("selectedCarId", carId);
        model.addAttribute("selectedType", type);

        return "expenses";
    }

    @PostMapping("/expenses")
    public String addExpense(@ModelAttribute("newExpense") Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }
}