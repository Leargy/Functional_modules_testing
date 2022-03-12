package utils;

import java.io.IOException;
import java.util.function.Function;

public class FunctionAutomate {
    private Function<Double, Double> myFunctionToAutomate;

    public void perfomeMultipleCalculation(double initialX, double finalX, double step) throws IOException {
        double x = initialX;
        double resX = 0;
        while ( x < finalX + step ) {
            resX = myFunctionToAutomate.apply(x);
            if(resX == Double.NaN) {
                throw new IOException("Some problems happened due to CSVWriter.\n Writing file was deleted or not exists");
            }
            x += step;
        }
    }

    public void setMyFunctionToAutomate(Function<Double, Double> myFunctionToAutomate) {
        this.myFunctionToAutomate = myFunctionToAutomate;
    }
}
