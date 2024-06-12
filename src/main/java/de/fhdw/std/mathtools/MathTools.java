package de.fhdw.std.mathtools;

import java.math.BigInteger;

public class MathTools {
    public static int fibonacci(int n) {
        int previous = 1;
        int current = 1;
        for (int i = 3; i <= n; i++) {
            int temp = previous + current;
            previous = current;
            current = temp;
        }
        return current;
    }

    public static int faculty(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) result *= i;
        return result;
    }

    public static BigInteger faculty(BigInteger n) {
        BigInteger result = BigInteger.ONE;
        BigInteger i = BigInteger.ONE;
        while (i.compareTo(n) <= 0) {
            result = result.multiply(i);
            i = i.add(BigInteger.ONE);
        }
        return result;
    }

}
