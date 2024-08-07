package sky.pro.calculator_2_12.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.calculator_2_12.exception.DivisionByZeroException;
import sky.pro.calculator_2_12.exception.FieldValueExceptionParam1;
import sky.pro.calculator_2_12.exception.FieldValueExceptionParam2;
import sky.pro.calculator_2_12.service.CalculatorService;

@RestController
@RequestMapping("/calculator")

public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String hello() {
        return "<h2 style=\"color: green; text-align: center\">Добро пожаловать в калькулятор</h2>";
    }
    @GetMapping( "/plus")
    public String plus (@RequestParam(value = "num1", required = false) Float a,
                        @RequestParam (value = "num2", required = false) Float b) {
        return buildView("+", a, b);
    }
    @GetMapping( "/minus")
    public String minus (@RequestParam(value = "num1", required = false) Float a,
                         @RequestParam (value = "num2", required = false) Float b) {
        return buildView("-", a, b);
    }
    @GetMapping( "/multiply")
    public String multiply (@RequestParam(value = "num1", required = false) Float a,
                            @RequestParam (value = "num2", required = false) Float b) {
        return buildView("*", a, b);
    }

    @GetMapping( "/divide")
    public String divide (@RequestParam(value = "num1", required = false) Float a,
                          @RequestParam (value = "num2", required = false) Float b) {
        return buildView("/", a, b);
    }

    private String buildView(String operation, Float operand1, Float operand2) {
        if (operand1 == null) {
            throw new FieldValueExceptionParam1();
        } else if (operand2 == null) {
            throw new FieldValueExceptionParam2();
        }
        if ("/".equals(operation) & operand2 == 0) {
            throw new DivisionByZeroException();
        }
        float result;

        result = switch (operation) {
            default:
            case "+":
                yield calculatorService.plus(operand1, operand2);
            case "-":
                yield calculatorService.minus(operand1, operand2);
            case "*":
                yield calculatorService.multiply(operand1, operand2);
            case "/":
                yield calculatorService.divide(operand1, operand2);
        };
        return operand1 + " " + operation + " " + operand2 + " = " + result;
    }
}
