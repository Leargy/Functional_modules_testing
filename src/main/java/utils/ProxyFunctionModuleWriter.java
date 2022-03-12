package utils;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

public class ProxyFunctionModuleWriter implements Function<Double, Double> {
    private final Function<Double, Double> FUNCTION;
    private final String FILE_PATH;

    public ProxyFunctionModuleWriter(Function<Double, Double> function, String filePath) {
        this.FUNCTION = function;
        this.FILE_PATH = filePath;
    }

    @Override
    public Double apply(Double x) {
        double result = FUNCTION.apply(x);

        try ( CSVWriter csvWriter = new CSVWriter( new FileWriter( FILE_PATH, true ), ',', '\0', '\\', "\n" ) ) {
            String[] record = { Double.toString( x ), Double.toString( result ) };
            csvWriter.writeNext( record );
        } catch ( IOException io ) {
            return Double.NaN;
        }
        return result;
    }
}
