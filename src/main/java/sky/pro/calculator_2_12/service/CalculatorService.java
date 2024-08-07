package sky.pro.calculator_2_12.service;

import org.springframework.stereotype.Service;
import sky.pro.calculator_2_12.exception.DivisionByZeroException;

@Service

public class CalculatorService {
    public float plus(Float a, Float b) {
        return a + b;
    }
    public float minus(Float a, Float b) {
        return a - b;
    }
    public float multiply(Float a, Float b) {
        return a * b;
    }
    public float divide(Float a, Float b) {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a / b;
    }
}
