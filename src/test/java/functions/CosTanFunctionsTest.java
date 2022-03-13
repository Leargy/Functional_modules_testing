package functions;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import functions.basic.SinFunction;
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

public class CosTanFunctionsTest {
    private static SinFunction sin = mock(SinFunction.class);
    private static CosFunction cos = mock(CosFunction.class);

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
        fillMock(cos, "src/test/cos_mock.csv");
        fillMock(sin, "src/test/sin_mock.csv");
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/cos_test.csv"})
    public void cosIntegrationSinTestFirstStage(double x, double expectedY) {
        final double delta = 0.01;

        CosFunction cos_a = new CosFunction(sin);
        assertEquals(expectedY, cos_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/cos_test.csv"})
    public void cosIntegrationSinTestSecondStage(double x, double expectedY) {
        final double delta = 0.8;

        CosFunction cos_a = new CosFunction(new SinFunction(25));
        assertEquals(expectedY, cos_a.apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/tan_test.csv"})
    public void tanIntegrationSinCosTestFirstStage(double x, double expectedY) {
        final double delta = 0.01;
        assertEquals(expectedY, new TanFunction(sin,cos).apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/tan_test.csv"})
    public void tanIntegrationSinCosTestSecondStage(double x, double expectedY) {
        final double delta = 0.8;
        assertEquals(expectedY, new TanFunction(new SinFunction(25),cos).apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/tan_test.csv"})
    public void tanIntegrationSinCosTestThirdStage(double x, double expectedY) {
        final double delta = 0.8;
        assertEquals(expectedY, new TanFunction(new SinFunction(25),new CosFunction(sin)).apply(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/tan_test.csv"})
    public void tanIntegrationSinCosTestFourthStage(double x, double expectedY) {
        final double delta = 0.8;
        SinFunction sin_a = new SinFunction(25);
        assertEquals(expectedY, new TanFunction(sin_a,new CosFunction(sin_a)).apply(x), delta);
    }
}
