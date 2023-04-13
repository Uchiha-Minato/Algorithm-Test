package testSource;

import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

/**
 * 判定-条件覆盖
 * */
@RunWith(value = Parameterized.class)
class DCCoverageTest {

    Coverage coverage = new Coverage();

    public static Stream<Arguments> getTestParams() {
        return Stream.of(Arguments.of(4, 4, 14, 24),//T1T2T3T4, M=T,N=T
                Arguments.of(2, 3, 14, 17));//F1F2F3F4, M=F,N=F
    }

    @ParameterizedTest
    @MethodSource("getTestParams")
    void funcCoverage(int x, int y, int z, int except) {
        System.out.println("Run decision-condition coverage");
        int actual = coverage.funcCoverage(x, y, z);
        Assertions.assertEquals(except, actual);
    }
}