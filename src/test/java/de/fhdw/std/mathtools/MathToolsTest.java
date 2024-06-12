package de.fhdw.std.mathtools;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MathToolsTest {
    private static int fibonacciOracle(int n) {
        if (n == 1 || n == 2) return 1;
        return fibonacciOracle(n - 1) + fibonacciOracle(n - 2);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 10, 20, 35})
    public void testFibonacci(int n) {
        assertThat(MathTools.fibonacci(n)).isEqualTo(fibonacciOracle(n));
    }


    private static int facultyOracle(int n) {
        if (n == 0) return 1;
        return n * facultyOracle(n - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 10, 23, 42})
    public void testFaculty(int n) {
        assertThat(MathTools.faculty(n)).isEqualTo(facultyOracle(n));
    }


    private static BigInteger facultyOracle(BigInteger n) {
        if (BigInteger.ZERO.equals(n)) return BigInteger.ONE;
        return n.multiply(facultyOracle(n.subtract(BigInteger.ONE)));
    }

    /*
        upper end > 200 shows how recursive implementation fails
        (might be higher on different VM configurations - e.g., see JVM option -Xss)
     */
    public static Stream<Arguments> generateIntStream() {
        return IntStream.range(0, 100).mapToObj(n -> Arguments.of(BigInteger.valueOf(n * 97)));
    }

    @ParameterizedTest
    @MethodSource("generateIntStream")
    public void testBigIntFaculty(BigInteger n) {
        assertThat(MathTools.faculty(n)).isEqualTo(facultyOracle(n));
    }

}
