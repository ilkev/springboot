package ru.skypro.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.calculator.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @GetMapping
    public String hello() {
        return "Добро пожаловать в калькулятор";
    }


    @GetMapping("/plus")
    public String sum(@RequestParam int num1, @RequestParam int num2) {
        int result = calculatorService.sum(num1, num2);
        return buildResponse(num1, num2, result, '+');
    }

    @GetMapping("/minus")
    public String subtract(@RequestParam int num1, @RequestParam int num2) {
        int result = calculatorService.subtract(num1, num2);
        return buildResponse(num1, num2, result, '-');
    }


    @GetMapping("/multiply")
    public String multiply(@RequestParam int num1, @RequestParam int num2) {
        int result = calculatorService.multiply(num1, num2);
        return buildResponse(num1, num2, result, '*');
    }


    @GetMapping("/divide")
    public String divide(@RequestParam int num1, @RequestParam int num2) {
        if (num2 == 0) {
            return "Второй аргумент 0 на 0 делить нельзя";
        }

        int result = calculatorService.divide(num1, num2);
        return buildResponse(num1, num2, result, '/');
    }


    private String buildResponse(int num1, int num2, int result, char action) {
        return String.format("%d %c %d = %d", num1, action, num2, result);
    }
}
