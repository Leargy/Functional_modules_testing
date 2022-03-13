package functions.base;

import functions.basic.SinFunction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinBaseFunctionTest {
    private final SinFunction sin = new SinFunction(26);
    private final double delta = 0.08;
    @ParameterizedTest
    @CsvFileSource(files={"src/test/sin_test.csv"})
    public void sinCSVbaseTest(double x, double expectedY) {
        assertEquals(expectedY,sin.apply(x),delta);
    }

}
