package com.example.demo.repository;

import com.example.demo.model.Expense;
import com.example.demo.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCarId(Long carId);

    List<Expense> findByType(ExpenseType type);

    List<Expense> findByCarIdAndType(Long carId, ExpenseType type);
}