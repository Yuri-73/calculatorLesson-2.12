package sky.pro.calculator_2_12;


import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import sky.pro.calculator_2_12.exception.DivisionByZeroException;
import sky.pro.calculator_2_12.service.CalculatorService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorServiceTest {

    private CalculatorService out;

    @BeforeEach
    public void setUp() {
        out = new CalculatorService();
    }

    @Test
    public void plusTest() {
        Number actual = out.plus(3F, 5F);
        Number expected = 8F;

        Assertions.assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(out.plus((float) 0, (float) 0)).isEqualTo(0);
    }

    @Test
    public void minusTest() {
        Assertions.assertThat(out.minus((float) 4, (float) 3)).isEqualTo(1);
        Assertions.assertThat(out.minus((float) 2, (float) 4)).isEqualTo(-2);
    }

    @Test
    public void multiplyTest() {
        Assertions.assertThat(out.multiply((float) 4, (float) 3)).isEqualTo(12);
        Assertions.assertThat(out.multiply((float) 2, (float) 4)).isEqualTo(8);
    }

    @Test
    public void divideTest() {
        Assertions.assertThat(out.divide((float) 4, (float) 2)).isEqualTo(2);
        Assertions.assertThat(out.divide((float) 8, (float) 2)).isEqualTo(4);
    }
    @Test
    public void divideNegativeTest() {
        Assertions.assertThatExceptionOfType(DivisionByZeroException.class)
                .isThrownBy(() -> out.divide((float) 4, (float) 0));
        Assertions.assertThatExceptionOfType(DivisionByZeroException.class)
                .isThrownBy(() -> out.divide((float) 0, (float) 0));
    }
}
