package sky.pro.calculator_2_12.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class DivisionByZeroException extends IllegalArgumentException {
    public DivisionByZeroException() {
    }
}
