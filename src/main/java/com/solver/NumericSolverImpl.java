package com.solver;

import com.domain.Problem;
import com.domain.Solution;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.BiFunction;


/**
 * Generic Stochastic Differential Equation simulator
 *     dS(t) = f(t) * S(t) * dt + g(t) * S(t) * dW(t)
 *     where dW = sqrt(dt) * randomGaussian
 * @author Scott Rome
 */
@Service
public class NumericSolverImpl implements NumericSolver {

	public double getNextValue(final double S, final BiFunction<Double,Double,Double> f, final BiFunction<Double,Double,Double> g, final double t, final double dt, final Random randomNumberGenerator) {
		return S + dS(S,f,g,t,dt, randomNumberGenerator);
	}

    @Override
    public double dS(final double S, final BiFunction<Double,Double,Double> f, final BiFunction<Double,Double,Double> g, final double t, final double dt, final Random randomNumberGenerator) {
    // Price + d(Price)
        double dS = f.apply(t, S)*dt + g.apply(t, S)* Math.sqrt(dt) * randomNumberGenerator.nextGaussian();
        return dS;
    }

    public Solution solve(final Problem problem) {

        final Random randomNumberGenerator = new Random();
        randomNumberGenerator.setSeed(System.currentTimeMillis());

        final Solution solution = new Solution();
        double value = problem.getInitialValue();
        double time = problem.getInitialTime();
        final double dt = problem.getTimeStep();
        final BiFunction<Double,Double,Double> f = problem.getF();
        final BiFunction<Double,Double,Double> g = problem.getG();

        //add first point
        solution.addPair(time, value);

        //simulate differential equation
        for (int k = 0 ; k < problem.getNumberOfSteps() ; k++) {
            time += dt;
            value = getNextValue(value, f, g, time, dt, randomNumberGenerator);
            solution.addPair(time,value);
        }

        return solution;
    }
}
