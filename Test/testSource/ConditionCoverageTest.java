package testSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

/**
 * 条件覆盖
 * */
@RunWith(value = Parameterized.class)
class ConditionCoverageTest {

    Coverage coverage = new Coverage();

    public static Stream<Arguments> getTestParams() {
        return Stream.of(Arguments.of(2, 4, 10, 16),//F1F2T3T4
                Arguments.of(4, 0, 14, 18));//T1T2F3F4
    }

    @ParameterizedTest
    @MethodSource("getTestParams")
    void funcCoverage(int x, int y, int z, int except) {
        System.out.println("Run condition coverage test");
        int actual = coverage.funcCoverage(x, y, z);
        Assertions.assertEquals(except, actual);
    }
}