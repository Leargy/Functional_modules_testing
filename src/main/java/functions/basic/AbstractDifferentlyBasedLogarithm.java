package functions.basic;

public abstract class AbstractDifferentlyBasedLogarithm extends TaylorSeries{
    protected final double BASE;

    public AbstractDifferentlyBasedLogarithm(long termsNum, double base) {
        super(termsNum);
        BASE = base;
    }
}
