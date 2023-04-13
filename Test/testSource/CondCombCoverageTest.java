package testSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

/**
 * 条件组合覆盖
 * */
@RunWith(value = Parameterized.class)
class CondCombCoverageTest {

    Coverage coverage = new Coverage();

    public static Stream<Arguments> getTestParams() {
        return Stream.of(Arguments.of(4, 0, 11, 17),//x>3 && z<=10
                Arguments.of(4, 0, 11, 17),//x>3 && z>10
                Arguments.of(3, 0, 11, 14),//x<=3 && z>10
                Arguments.of(3, 0, 10, 13),//x<=3 && z<=10
                Arguments.of(5, 4, 9, 18),//y=4 or z<12
                Arguments.of(5, 4, 14, 25),//y=4 or z>=12
                Arguments.of(5, 0, 11, 18),//y!=4 or z<12
                Arguments.of(5, 0, 12, 17));//y!=4 or z>=12
    }

    @ParameterizedTest
    @MethodSource("getTestParams")
    void funcCoverage(int x, int y, int z, int except) {
        System.out.println("Run condition combination coverage test");
        int actual = coverage.funcCoverage(x, y, z);
        Assertions.assertEquals(except, actual);
    }
}