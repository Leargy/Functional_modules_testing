package functions;

import functions.basic.SinFunction;

import java.util.function.Function;

public class TanFunction implements Function<Double, Double> {
    private final SinFunction sin;
    private final CosFunction cos;

    public TanFunction(SinFunction sinFunction, CosFunction cosFunction) {
        this.sin = sinFunction;
        this.cos = cosFunction;
    }

    @Override
    public Double apply(Double x) {
        return sin.apply(x)/ cos.apply(x);
    }
}

