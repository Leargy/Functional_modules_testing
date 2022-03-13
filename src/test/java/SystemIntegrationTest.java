import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import functions.CosFunction;
import functions.DifferentlyBasedLogorithm;
import functions.TanFunction;
import functions.basic.NaturalLogarithmFunction;
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

public class SystemIntegrationTest {
    private static CosFunction cos = mock(CosFunction.class);
    private static SinFunction sin = mock(SinFunction.class);
    private static TanFunction tan = mock(TanFunction.class);
    private static NaturalLogarithmFunction ln = mock(NaturalLogarithmFunction.class);
    private static DifferentlyBasedLogorithm log2 = mock(DifferentlyBasedLogorithm.class);
    private static DifferentlyBasedLogorithm log5 = mock(DifferentlyBasedLogorithm.class);
    private static DifferentlyBasedLogorithm log10 = mock(DifferentlyBasedLogorithm.class);
    private static SystemRunner fs = null;


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
        fillMock(cos, "src/test/cos_negative_fs.csv");
        fillMock(sin, "src/test/sin_negative_fs.csv");
        fillMock(tan, "src/test/tan_negative_fs.csv");
        fillMock(ln, "src/test/ln_fs.csv");
        fillMock(log2, "src/test/ln_2_fs.csv");
        fillMock(log5, "src/test/ln_5_fs.csv");
        fillMock(log10, "src/test/ln_10_fs.csv");

    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/negative_fs.csv"})
    public void negativeFsTestFirstStage(double x, double expectedY) {
        final double delta = 0.0;

        fs = new SystemRunner(tan, null,null,null);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/negative_fs.csv"})
    public void negativeFsTestSecondStage(double x, double expectedY) {
        final double delta = 0.8;

        fs = new SystemRunner(new TanFunction(sin,cos), null,null,null);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/negative_fs.csv"})
    public void negativeFsTestThirdStage(double x, double expectedY) {
        final double delta = 0.8;

        fs = new SystemRunner(new TanFunction(new SinFunction(20),cos), null,null,null);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/negative_fs.csv"})
    public void negativeFsTestFourthStage(double x, double expectedY) {
        final double delta = 0.8;
        SinFunction sin_a = new SinFunction(20);
        CosFunction cos_a = new CosFunction(sin);

        fs = new SystemRunner(new TanFunction(sin_a,cos_a), null,null,null);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/negative_fs.csv"})
    public void negativeFsTestFifthStage(double x, double expectedY) {
        final double delta = 0.8;
        SinFunction sin_a = new SinFunction(20);
        CosFunction cos_a = new CosFunction(sin_a);

        fs = new SystemRunner(new TanFunction(sin_a,cos_a), null,null,null);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/positive_fs.csv"})
    public void positiveFsTestFirstStage(double x, double expectedY) {
        final double delta = 0.001;

        fs = new SystemRunner(null, log2,log5,log10);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/positive_fs.csv"})
    public void positiveFsTestSecondStage(double x, double expectedY) {
        final double delta = 0.001;

        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(20,2,ln);
        fs = new SystemRunner(null, log2_a,log5,log10);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/positive_fs.csv"})
    public void positiveFsTestThirdStage(double x, double expectedY) {
        final double delta = 0.001;

        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(20,2,ln);
        DifferentlyBasedLogorithm log5_a = new DifferentlyBasedLogorithm(20,5,ln);
        DifferentlyBasedLogorithm log10_a = new DifferentlyBasedLogorithm(20,10,ln);

        fs = new SystemRunner(null, log2_a,log5_a,log10_a);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/positive_fs.csv"})
    public void positiveFsTestFourthStage(double x, double expectedY) {
        final double delta = 0.8;
        NaturalLogarithmFunction ln_a = new NaturalLogarithmFunction(150,6);

        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(150,2,ln_a);
        DifferentlyBasedLogorithm log5_a = new DifferentlyBasedLogorithm(100,5,ln);
        DifferentlyBasedLogorithm log10_a = new DifferentlyBasedLogorithm(100,10,ln);

        fs = new SystemRunner(null, log2_a,log5_a,log10_a);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/positive_fs.csv"})
    public void positiveFsTestFifthStage(double x, double expectedY) {
        final double delta = 0.8;
        NaturalLogarithmFunction ln_a = new NaturalLogarithmFunction(150,6);

        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(150,2,ln_a);
        DifferentlyBasedLogorithm log5_a = new DifferentlyBasedLogorithm(150,5,ln_a);
        DifferentlyBasedLogorithm log10_a = new DifferentlyBasedLogorithm(100,10,ln);

        fs = new SystemRunner(null, log2_a,log5_a,log10_a);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(files={"src/test/positive_fs.csv"})
    public void positiveFsTestSixthStage(double x, double expectedY) {
        final double delta = 0.8;
        NaturalLogarithmFunction ln_a = new NaturalLogarithmFunction(200,6);

        DifferentlyBasedLogorithm log2_a = new DifferentlyBasedLogorithm(200,2,ln_a);
        DifferentlyBasedLogorithm log5_a = new DifferentlyBasedLogorithm(200,5,ln_a);
        DifferentlyBasedLogorithm log10_a = new DifferentlyBasedLogorithm(200,10,ln_a);

        fs = new SystemRunner(null, log2_a,log5_a,log10_a);
        assertEquals(expectedY, fs.calculateSysytem(x), delta);
    }
}