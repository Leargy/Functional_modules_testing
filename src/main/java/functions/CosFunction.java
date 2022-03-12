package functions;

import functions.basic.SinFunction;

import java.util.function.Function;

public class CosFunction implements Function<Double, Double> {
    private final SinFunction sin;

    public CosFunction(SinFunction sinFunction) {
        this.sin = sinFunction;
    }

    @Override
    public Double apply(Double x) {
        double n_x = normalize(x);

        double one = -1;
        if (n_x < Math.PI/2 && n_x > -Math.PI/2) one = 1;

        double sinRes = sin.apply(n_x);

        return one * Math.sqrt(1 - sinRes * sinRes);
    }

    private Double normalize(Double x) {
        double res = x % ( -2 * Math.PI );

        if ( res > 0 ) res -= 2 * Math.PI;

        return res;
    }
}
