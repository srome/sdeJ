package com.solver;

import com.domain.Problem;
import com.domain.Solution;

import java.util.Random;
import java.util.function.BiFunction;

public interface NumericSolver {

    public double getNextValue(final double S, final BiFunction<Double,Double,Double> f, final BiFunction<Double,Double,Double> g, final double t, final double dt, final Random randomNumberGenerator);
    public double dS(final double S, final BiFunction<Double,Double,Double> f, final BiFunction<Double,Double,Double> g, final double t, final double dt, final Random randomNumberGenerator) ;
    public Solution solve(Problem problem);
}

