package functions;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import functions.basic.NaturalLogarithmFunction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogarithmsTest {
    private static NaturalLogarithmFunction ln = mock(NaturalLogarithmFunction.class);
    private static DifferentlyBasedLogorithm log2 = mock(DifferentlyBasedLogorithm.class);
    private static DifferentlyBasedLogorithm log5 = mock(DifferentlyBasedLogorithm.class);
    private static DifferentlyBasedLogorithm log10 = mock(DifferentlyBasedLogorithm.class);

    private static void fillMock(Function<Double, Double> fn, String tableName) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();

            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(fn.apply(x)).thenReturn(y);
            }

        } catch (IOException | CsvException exp) {
            throw exp;
        }
    }

    @BeforeAll
    public static void setup() throws IOException, CsvException {
        fillMock(ln, "src/test/ln_test.csv");
        fillMock(log2, "src/test/ln_2_test.csv");
        fillMock(log5, "src/test/ln_5_test.csv");
        fillMock(log10, "src/test/ln_110_test.csv");
    }


    @ParameterizedTest
    @CsvFileSource(files={"src/test/ln_test.csv"})
    public void lnTest(double x, double expectedY) {
        final double delta = 0.8;

        NaturalLogarithmFunction ln_a = new NaturalLogarithmFunction(100,6);
        assertEquals(expectedY, ln_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/ln_2_test.csv"})
    public void log2TestFirstStage(double x, double expectedY) {
        final double delta = 0.8;
        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(100,2,ln);
        assertEquals(expectedY, log2_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/ln_2_test.csv"})
    public void log2TestSecondStage(double x, double expectedY) {
        final double delta = 0.8;
        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(100,2,new NaturalLogarithmFunction(100,6));
        assertEquals(expectedY, log2_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/ln_5_test.csv"})
    public void log5TestFirstStage(double x, double expectedY) {
        final double delta = 0.8;
        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(100,5,ln);
        assertEquals(expectedY, log2_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/ln_5_test.csv"})
    public void log5TestSecondStage(double x, double expectedY) {
        final double delta = 0.8;
        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(100,5,new NaturalLogarithmFunction(100,6));
        assertEquals(expectedY, log2_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/ln_110_test.csv"})
    public void log10TestFirstStage(double x, double expectedY) {
        final double delta = 0.8;
        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(100,10,ln);
        assertEquals(expectedY, log2_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/ln_110_test.csv"})
    public void log10TestSecondStage(double x, double expectedY) {
        final double delta = 0.8;
        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(100,10,new NaturalLogarithmFunction(100,6));
        assertEquals(expectedY, log2_a.apply(x), delta);
    }

}
