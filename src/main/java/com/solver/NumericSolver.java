package com.solver;

import com.domain.problem.Problem;
import com.domain.Solution;

import java.util.Random;
import java.util.function.BiFunction;

public interface NumericSolver {

    double getNextValue(final double S,
                        final BiFunction<Double, Double, Double> f,
                        final BiFunction<Double, Double, Double> g,
                        final double t,
                        final double dt);
    double dS(final double S,
              final BiFunction<Double, Double, Double> f,
              final BiFunction<Double, Double, Double> g,
              final double t,
              final double dt,
              final Random randomNumberGenerator) ;
    Solution solve(Problem problem);
}

