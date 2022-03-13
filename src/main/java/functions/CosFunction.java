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

        if (n_x > 0){
            if (n_x < Math.PI/2 || n_x > 3*Math.PI/2) one = 1;
        }else{
            if (n_x > -Math.PI/2 || n_x < -3*Math.PI/2) one = 1;
        }

        double sinRes = sin.apply(n_x);

        return one * Math.sqrt(1 - sinRes * sinRes);
    }

    public Double normalize(Double x) {
        double res = x % ( 2* Math.PI );

        return res;
    }
}
