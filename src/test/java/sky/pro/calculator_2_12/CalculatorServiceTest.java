package sky.pro.calculator_2_12;


import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import sky.pro.calculator_2_12.exception.DivisionByZeroException;
import sky.pro.calculator_2_12.service.CalculatorService;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorServiceTest {

    private CalculatorService out = new CalculatorService();
    private static final Random random = new Random();

    @Test
    public void plusTest() {
        float num1, num2;
        num1 = random.nextFloat(0, 10);  //Эксперимент по вебинару от Санёчка 2.12 (в Г.-Х этого нет)
        num2 = random.nextFloat(0, 10);  //Эксперимент по вебинару от Санёчка 2.12 (в Г.-Х этого нет)
        Number expected = num1 + num2;
        Number actual = out.plus(num1, num2);

        Assertions.assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(out.plus((float) 0, (float) 0)).isEqualTo(0);
    }

    @Test
//    @RepeatedTest(2) //Почему проходит, но выдаёт красное предупреждение?
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
        Assertions.assertThatExceptionOfType(DivisionByZeroException.class)  //Вариант 1
                .isThrownBy(() -> out.divide((float) 4, (float) 0));
        Assertions.assertThatExceptionOfType(DivisionByZeroException.class)
                .isThrownBy(() -> out.divide((float) 0, (float) 0));
        org.junit.jupiter.api.Assertions.assertThrows(DivisionByZeroException.class, () -> out.divide((float) 0, (float) 0)); //Вариант 2
    }
}
