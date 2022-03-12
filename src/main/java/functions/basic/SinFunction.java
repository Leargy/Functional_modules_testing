package functions.basic;

import utils.Factorial;

public strictfp class SinFunction extends TaylorSeries {

    public SinFunction(long termsNum) {
        super(termsNum);
    }

    @Override
    public Double apply(Double x) {
        double res = 0;
        for(long i = 1; i < TERMS_NUM / 2; i++) {
            res += (Math.pow(-1, i - 1) * Math.pow(x, 2L * i - 1)) / Factorial.fact(2L * i - 1);
        }
        return res;
    }
}
