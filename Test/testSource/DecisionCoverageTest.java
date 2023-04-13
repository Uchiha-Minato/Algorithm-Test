package testSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

/**
 * 判定覆盖测试
 * */
@RunWith(value = Parameterized.class)
class DecisionCoverageTest {

    Coverage coverage = new Coverage();

    public static Stream<Arguments> getTestParams() {
        return Stream.of(Arguments.of(4, 0, 11, 17),//Y Y
                Arguments.of(5, 2, 13, 20),//Y N
                Arguments.of(2, 4, 11, 17),//N Y
                Arguments.of(2, 0, 14, 14));//N N
    }

    @ParameterizedTest
    @MethodSource("getTestParams")
    void funcCoverage(int x, int y, int z, int except) {
        System.out.println("Run decision coverage test");
        int actual = coverage.funcCoverage(x, y, z);
        Assertions.assertEquals(except, actual);
    }
}