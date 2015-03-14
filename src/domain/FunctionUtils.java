package domain;


import domain.BivariateFunction;

public class FunctionUtils {

    public static BivariateFunction multiply(final BivariateFunction f, final BivariateFunction g) {
        return new BivariateFunction() {
            @Override
            public Double apply(Double t, Double y) {
                return f.apply(t,y)*g.apply(t,y);
            }
        };
    }

    public static BivariateFunction affine(final double a, final double b, final double c) {
        //ax + by + c
        return new BivariateFunction() {
            @Override
            public Double apply(Double t, Double y) {
                return a*t + b* y + c;
            }
        };
    }

    public static BivariateFunction exponential(final double a, final double b, final double c, final double d, final double e) {
        //a*exp(bt) + c*exp(dy) + e
        return new BivariateFunction() {
            @Override
            public Double apply(Double t, Double y) {
                return a*Math.exp(b*t)+ c*Math.exp(d*y)+e;
            }
        };
    }

}
