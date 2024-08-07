package sky.pro.calculator_2_12.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FieldValueExceptionParam1 extends RuntimeException {

}
