package com.utils;

import java.util.function.BiFunction;

public class FunctionUtils {

    public static BiFunction<Double,Double,Double> multiply(final BiFunction<Double,Double,Double> f, final BiFunction<Double,Double,Double> g) {
        return (t,y) -> f.apply(t,y)*g.apply(t,y);
    }

    public static BiFunction<Double,Double,Double> affine(final double a, final double b, final double c) {
        //ax + by + c
        return (t,y) -> a*t + b*y + c;
    }

    public static BiFunction<Double,Double,Double> exponential(final double a, final double b, final double c, final double d, final double e) {
        //a*exp(bt) + c*exp(dy) + e
        return (t,y) -> a*Math.exp(b*t)+ c*Math.exp(d*y)+e;
    }

}
