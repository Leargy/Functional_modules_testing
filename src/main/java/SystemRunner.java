import functions.DifferentlyBasedLogorithm;
import functions.TanFunction;

public class SystemRunner {

    private TanFunction tg;
    private DifferentlyBasedLogorithm log_2;
    private DifferentlyBasedLogorithm log_5;
    private DifferentlyBasedLogorithm log_10;

    public SystemRunner(TanFunction tg, DifferentlyBasedLogorithm log_2, DifferentlyBasedLogorithm log_5, DifferentlyBasedLogorithm log_10) {
        this.tg = tg;
        this.log_2 = log_2;
        this.log_5 = log_5;
        this.log_10 = log_10;
    }

    public double calculateSysytem(double x) {
        Double res;
        if(x <= 0) {
            res = tg.apply(x) * tg.apply(x);
        }else {
            double log_2_v = log_2.apply(x);
            double log_5_v = log_5.apply(x);
            double log_10_v = log_10.apply(x);


            res = (((((log_5_v * log_2_v) - log_2_v) * log_10_v) * log_2_v) - log_5_v);
        }
        return res;
    }
}
