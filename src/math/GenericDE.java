package math;

import domain.BivariateFunction;
import domain.Problem;
import domain.Solution;

import java.util.Random;


/**
 * Generic Stochastic Differential Equation simulator
 *     dS(t) = f(t) * S(t) * dt + g(t) * S(t) * dW(t)
 *     where dW = sqrt(dt) * randomGaussian
 * @author Scott Rome
 */
public class GenericDE implements Solver{

    final private Random randomNumberGenerator;
	
	public GenericDE() {
		randomNumberGenerator = new Random();
		randomNumberGenerator.setSeed(System.currentTimeMillis());
	}
	
	public double getNextValue(final double S, final BivariateFunction f, final BivariateFunction g, final double t, final double dt) {
		return S + dS(S,f,g,t,dt);
	}
	
	public double dS(final double S, final BivariateFunction f, final BivariateFunction g,final double t, final double dt) {
		// Price + d(Price)
		double dS = f.evaluate(t, S)*dt + g.evaluate(t,S)* Math.sqrt(dt) * randomNumberGenerator.nextGaussian();
		return dS;	
	}

    public Solution solve(Problem problem) {
        final Solution solution = new Solution();
        double value = problem.getInitialValue();
        double time = problem.getInitialTime();
        final double dt = problem.getTimeStep();
        final BivariateFunction f = problem.getF();
        final BivariateFunction g = problem.getG();

        //add first point
        solution.addPair(time, value);

        //simulate differential equation
        for (int k = 0 ; k < problem.getNumberOfSteps() ; k++) {
            time += dt;
            value = getNextValue(value, f, g, time, dt);
            solution.addPair(time,value);
        }

        return solution;
    }
}
