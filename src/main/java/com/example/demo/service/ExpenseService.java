package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.model.ExpenseType;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public List<Expense> getFilteredExpenses(Long carId, ExpenseType type) {
        if (carId == null && type == null) {
            return expenseRepository.findAll();
        } else if (carId != null && type == null) {
            return expenseRepository.findByCarId(carId);
        } else if (carId == null && type != null) {
            return expenseRepository.findByType(type);
        } else {
            return expenseRepository.findByCarIdAndType(carId, type);
        }
    }
}