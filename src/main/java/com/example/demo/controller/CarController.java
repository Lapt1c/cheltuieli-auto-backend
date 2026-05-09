package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public String showCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("newCar", new Car());
        return "cars";
    }

    @PostMapping("/cars")
    public String addCar(@ModelAttribute("newCar") Car car) {
        carService.saveCar(car);
        return "redirect:/cars";
    }


}