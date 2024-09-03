package sky.pro.calculator_2_12;

import io.micrometer.common.util.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import sky.pro.calculator_2_12.exception.DivisionByZeroException;
import sky.pro.calculator_2_12.service.CalculatorService;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса CalculatorService с помощью параметирического метода")
public class CalculatorServiceParameterizedTest {
    private final CalculatorService out = new CalculatorService();

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
    @DisplayName("Общий метод для тестирования всех методов, включая значение null для второго параметра.")
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

    //Эти методы для собственной тренировки по различным аннотациям параметризованных тестов из материала https://habr.com/ru/articles/591007/ этого урока 2.12:
    @ParameterizedTest
    @EmptySource
    ////Тестирование пустого источника
    void testMethodEmptySource(String argument) {
        assertTrue(StringUtils.isEmpty(argument));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование null-объекта")
    void testMethodNullSource(String argument) {
        assertTrue(argument == null);
    }

    @ParameterizedTest
    @DisplayName("Для null-объекта и пустого объекта одновременно")
    @NullAndEmptySource
    void testMethodNullAndEmptySource(String argument) {
        assertTrue(StringUtils.isEmpty(argument));  //isEmpty() включает в себя пустое значение и null.
        //Но assertTrue(argument == null) не пройдёт, потому что null не является пустым значением!!!
    }

    enum Direction {  //Тестирование перечисления
        EAST, WEST, NORTH, SOUTH
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    @DisplayName("Использование в параметрах класса Enum")
    void testWithEnumSource(Direction d) {
        assertNotNull(d);
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
        //Тестирование с помощью фабричного метода (он д.б. static)
    void testWithMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> argsProviderFactory() {
        return Stream.of("alex", "brian");
    }

    @ParameterizedTest
    @MethodSource
        //Т.к. в этой аннотации нет названия статического метода, то система ищет метод с тем же названием в том же блоке  (он д.б. тоже static)
    void testWithMethodSource2(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> testWithMethodSource2() {
        return Stream.of("alex", "brian");
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory2")
        //Тестирование с помощью фабрички для примитивных стримов
    void testWithMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    static IntStream argsProviderFactory2() {
        return IntStream.range(0, 9);
    }

    @ParameterizedTest
    @CsvSource(value = {  //Аргументы собраны уже в самой аннотации (каждый аргумент называется токином), с игнорированием начальных и конечных пробелов
            "  alex, 30   ",
            "brian, 35",
            "charles, 40"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testWithCsvSource(String name, int age) {
        assertNotNull(name);
        assertTrue(age > 0);
    }
}


