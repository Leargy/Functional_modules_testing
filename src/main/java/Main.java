import functions.basic.NaturalLogarithmFunction;
import functions.basic.SinFunction;
import functions.CosFunction;
import functions.DifferentlyBasedLogorithm;
import functions.TanFunction;

public class Main {

    public static void main(String[] args) {
        //accuracy
        long terms = 25;

        NaturalLogarithmFunction naturalLogarithmFunction = new NaturalLogarithmFunction(terms,6);
        DifferentlyBasedLogorithm log_2 = new DifferentlyBasedLogorithm(terms, 2, naturalLogarithmFunction);
        DifferentlyBasedLogorithm log_5 = new DifferentlyBasedLogorithm(terms, 5, naturalLogarithmFunction);
        DifferentlyBasedLogorithm log_10 = new DifferentlyBasedLogorithm(terms, 10, naturalLogarithmFunction);
        SinFunction sinFunction = new SinFunction(terms);
        CosFunction cosFunction = new CosFunction(sinFunction);
        TanFunction tanFunction = new TanFunction(sinFunction, cosFunction);

        SystemRunner systemRunner = new SystemRunner(tanFunction, log_2, log_5, log_10);



        Double[] n = new Double[]{
                -0.7168146928204138,
                        -0.04681469282041384,
                        -5.0,
                        -4.74,
                        -4.15,
                        -3.15,
                        -2.3,
                        -1.56,
                        -1.11,
                0.0,
                0.7168146928204138,
                0.04681469282041384,
                5.0,
                4.74,
                4.15,
                3.15,
                2.3,
                1.56,
                1.11,
        };

        for (double x:n) {
            System.out.println(Math.sin(x));
        }

    }
}
