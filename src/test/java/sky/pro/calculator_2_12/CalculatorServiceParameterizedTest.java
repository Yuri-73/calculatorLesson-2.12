package sky.pro.calculator_2_12;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sky.pro.calculator_2_12.exception.DivisionByZeroException;
import sky.pro.calculator_2_12.service.CalculatorService;

import java.util.stream.Stream;

@DisplayName("Generalization of input data")
public class CalculatorServiceParameterizedTest {
    private final CalculatorService out = new CalculatorService();

    @DisplayName("Generalized parameters for testing all methods, including null for the second parameter")
    public static Stream<Arguments> provideParamsTests() {
        return Stream.of(
                Arguments.of(22f, 5f),
                Arguments.of(3f, 6f),
                Arguments.of(0f, 1f),
                Arguments.of(33f, 0f)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsTests")
    public void mustTestMathOperation(Float a, Float b) {
        Assertions.assertThat(out.plus(a, b)).isEqualTo(a + b);
        Assertions.assertThat(out.minus(a, b)).isEqualTo(a - b);
        Assertions.assertThat(out.multiply(a, b)).isEqualTo(a * b);
        if (b != 0) {
            Assertions.assertThat(out.divide(a, b)).isEqualTo(a / b);
        } else {
            Assertions.assertThatExceptionOfType(DivisionByZeroException.class)
                    .isThrownBy(() -> out.divide(a, b));  // Вариант 1
            org.junit.jupiter.api.Assertions.assertThrows(DivisionByZeroException.class, () -> out.divide(a, b)); // Вариант 2
        }
    }
}
