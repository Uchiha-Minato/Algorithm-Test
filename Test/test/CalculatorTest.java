package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testSource.Calculator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(value = Parameterized.class)
class CalculatorTest {

    Calculator cal = new Calculator();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @MethodSource("getTestParas")
    void testDivide(double num1, double num2, double except) {
        System.out.println("run testDivide");
        double actual = cal.divide(num1, num2);
        Assertions.assertEquals(except, actual);
    }

    public static Stream<Arguments> getTestParas(){
        return Stream.of(Arguments.of(1.0, 2.0, 0.5)/*,
                Arguments.of(2.0, 3.0, 0.6666666),
                Arguments.of(3.0, 0.0, 0.0)*/);
    }
}