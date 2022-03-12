import functions.basic.NaturalLogarithmFunction;
import functions.basic.SinFunction;
import functions.CosFunction;
import functions.DifferentlyBasedLogorithm;
import functions.TanFunction;

public class Main {

    public static void main(String[] args) {
        //accuracy
        long terms = 25;

        NaturalLogarithmFunction naturalLogarithmFunction = new NaturalLogarithmFunction(terms, 6);
        DifferentlyBasedLogorithm log_2 = new DifferentlyBasedLogorithm(terms, 2, naturalLogarithmFunction);
        DifferentlyBasedLogorithm log_5 = new DifferentlyBasedLogorithm(terms, 5, naturalLogarithmFunction);
        DifferentlyBasedLogorithm log_10 = new DifferentlyBasedLogorithm(terms, 10, naturalLogarithmFunction);
        SinFunction sinFunction = new SinFunction(terms);
        CosFunction cosFunction = new CosFunction(sinFunction);
        TanFunction tanFunction = new TanFunction(sinFunction, cosFunction);

        SystemRunner systemRunner = new SystemRunner(tanFunction, log_2, log_5, log_10);

        double res = systemRunner.calculateSysytem(12);
        System.out.println(res);
    }
}
