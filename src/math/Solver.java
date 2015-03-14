package math;

import domain.BivariateFunction;
import domain.Problem;
import domain.Solution;

public interface Solver {

    public double getNextValue(final double S, final BivariateFunction f, final BivariateFunction g, final double t, final double dt);
    public double dS(final double S, final BivariateFunction f, final BivariateFunction g, final double t, final double dt) ;
    public Solution solve(Problem problem);
}

