package functions;

import functions.basic.AbstractDifferentlyBasedLogarithm;
import functions.basic.NaturalLogarithmFunction;

public class DifferentlyBasedLogorithm extends AbstractDifferentlyBasedLogarithm {
    private final NaturalLogarithmFunction naturalLogarithm;

    public DifferentlyBasedLogorithm(long termsNum, double base, NaturalLogarithmFunction naturalLogarithmFunction) {
        super(termsNum, base);
        this.naturalLogarithm = naturalLogarithmFunction;
    }

    @Override
    public Double apply(Double x) {
        double xres = naturalLogarithm.apply(x);
        double bres = naturalLogarithm.apply(BASE);
        return xres / bres;
    }
}
