package sky.pro.calculator_2_12.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(DivisionByZeroException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void handleDivisionByZeroException(DivisionByZeroException e) {
        System.out.println("Деление на ноль не допустимо!");
    }
    @ExceptionHandler(FieldValueExceptionParam1.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleFieldValueException(FieldValueExceptionParam1 e) {
        System.out.println("Не введено первое значение входного параметра!");
    }
    @ExceptionHandler(FieldValueExceptionParam2.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleFieldValueException2(FieldValueExceptionParam2 e) {
        System.out.println("Не введено второе значение входного параметра!");
    }
}
