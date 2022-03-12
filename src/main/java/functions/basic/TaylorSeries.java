package functions.basic;

import java.util.function.Function;

public abstract class TaylorSeries implements Function<Double, Double> {
    protected final long TERMS_NUM;

    public TaylorSeries(long termsNum) {
        if(termsNum <= 0) TERMS_NUM = 1;
        else TERMS_NUM = termsNum;
    }
}
