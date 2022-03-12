package utils;

import java.util.stream.LongStream;

public class Factorial {
    public static double fact(long n) {
        double res = ( double ) LongStream.rangeClosed( 1, n )
                .reduce(1, ( long x, long y ) -> x * y );
        return res;
    }
}
