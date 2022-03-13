package utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    @ParameterizedTest
    @CsvSource( value={
            "0, 1.0",
            "1, 1.0",
            "2, 2.0",
            "3, 6.0",
            "4, 24.0",
            "5, 120.0",
            "6, 720.0",
            "8, 40320.0",
            "9, 362880.0"
    } )
    public void testCommonValues(long n, double fact) {
        assertEquals(fact, Factorial.fact(n), 0);
    }
}
