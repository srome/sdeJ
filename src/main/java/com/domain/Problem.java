package com.domain;


import com.solver.NumericSolver;
import com.solver.NumericSolverImpl;

import java.util.function.BiFunction;

/**
 * This class is represents the first order first order SDE dy=f(t,y)dt + g(t,y)dW. When g=0, this problem will be an ODE.
 * The methods on this class allow one to solve and return an interpolated solution as a InterpolatedFunction.
 * @author Scott Rome
 */
public class Problem {
    final private BiFunction<Double,Double,Double> f, g;
    final private NumericSolver solver;
    private String equationName;
    private String equation;
    private Solution solution;
    private Double initialValue = null;
    private Double initialTime = null;
    private Double endTime = null;
    private Double timeStep;
    private Integer numberOfSteps = null;

    /**
     * Initializes a first order SDE dy=f(t,y)dt + g(t,y)dW on (a,b) where the number of time steps is given.
     * @param f The function multiplied against dt.
     * @param g The function multiplied against dW.
     * @param a Initial time.
     * @param b The end time of the simulation.
     * @param numberOfSteps The number of steps between a and b.
     * @throws SdeJException
     */
    public Problem(final BiFunction<Double,Double,Double> f, final BiFunction<Double,Double,Double> g, final double initialValue, final double a, final double b, final int numberOfSteps) throws SdeJException {
        this.f = f;
        this.g = g;
        this.solver = new NumericSolverImpl();
        this.initialValue = initialValue;
        setInterval(a,b);
        this.numberOfSteps = numberOfSteps;
        calculateTimeStep();
    }

    /**
     * Initializes a first order SDE dy=f(t,y)dt + g(t,y)dW initial value problem on an unspecified interval starting at time a.
     * @param f The function multiplied against dt.
     * @param g The function multiplied against dW.
     * @param initialValue y(initialTime)
     * @param initialTime Initial time
     * @param numberOfSteps The number of time steps to use in the simulation.
     * @param timeStep The number of time steps.
     * @throws SdeJException
     */
    public Problem(final BiFunction<Double,Double,Double> f, final BiFunction<Double,Double,Double> g, final double initialValue, final double initialTime, final int numberOfSteps, final double timeStep) throws SdeJException {
        this.f = f;
        this.g = g;
        this.solver = new NumericSolverImpl();
        this.initialTime = initialTime;
        this.initialValue = initialValue;
        this.numberOfSteps = numberOfSteps;
        this.timeStep = timeStep;
    }

    /**
     * Sets the time interval that the problem should be solved on (i.e. (0,100))
     * @param a start of the interval
     * @param b end of the interval
     * @return the current instance of Problem
     */
    public Problem setInterval(final Double a, final Double b) {
        this.initialTime = a;
        this.endTime = b;
        return this;
    }

    /**
     * Sets the time step (i.e. \delta t, dt) using (a,b) and the number of time steps
     * @return the current instance of Problem
     * @throws SdeJException
     */
    private Problem calculateTimeStep() throws SdeJException {
        if (initialTime == null || endTime == null) {
            throw new SdeJException("Interval not specified.");
        } else if (numberOfSteps == null) {
            throw new SdeJException("Number of time steps not set.");
        }
        this.timeStep = (endTime - initialTime)/ numberOfSteps.doubleValue();
        return this;
    }

    /**
     * Used in test methods to title the plot.
     * @param equationName Title of the plot.
     * @param equation Equation which shows in the legend.
     * @return The current instance of Problem.
     */
    public Problem setEquationText(final String equationName, final String equation) {
        this.equationName = equationName;
        this.equation = equation;
        return this;
    }

    private Solution solve() {
        this.solution = solver.solve(this);
        return solution;
    }

    public Double getInitialValue() {
        return initialValue;
    }

    public Solution getSolution() {
        if (solution == null) {
            solve();
        }
        return solution;
    }

    public Double getTimeStep() {
        return timeStep;
    }

    public Double getInitialTime() {
        return initialTime;
    }

    public BiFunction<Double,Double,Double> getF() {
        return f;
    }

    public BiFunction<Double,Double,Double> getG() {
        return g;
    }

    public String getEquation() {
        return equation;
    }

    public String getEquationName() {
        return equationName;
    }

    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }
}
