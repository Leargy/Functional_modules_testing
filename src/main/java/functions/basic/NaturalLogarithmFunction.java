package functions.basic;

public strictfp class NaturalLogarithmFunction extends AbstractDifferentlyBasedLogarithm {

    public NaturalLogarithmFunction(long termsNum, double a) {
        super(termsNum, a);
    }

    @Override
    public Double apply(Double x) {
        double res = Math.log(BASE);
        for ( long n = 1; n < TERMS_NUM + 1; n++ ) {
            res += Math.pow(-1, n - 1) * Math.pow(x - BASE, n) / (n * Math.pow(BASE, n));
        }
        return res;
    }
}
