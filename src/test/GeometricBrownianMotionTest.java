package test;

import domain.BivariateFunction;
import domain.FunctionUtils;
import domain.Problem;
import junit.framework.Assert;
import org.junit.Test;
import test.graphing.TestPlotter;

import java.lang.reflect.Method;

public class GeometricBrownianMotionTest {

    @Test
    public void test() throws Exception {

        TestPlotter image = new TestPlotter();

        for (int k = 0; k < 5; k++) {
            Problem problem = runSimulation();
            image.addProblemToPlot(problem);
        }
        Assert.assertTrue(image.plot("Geometric Brownian Motion"));

    }

    private Problem runSimulation() throws Exception {
        BivariateFunction f = FunctionUtils.affine(0d, .001d, 0d); //mu = 2
        BivariateFunction g = FunctionUtils.affine(0d, .005d, 0d); //sigma = .5
        double initialTime = 0d;
        double timeStep = .001d;
        double initialValue = 1d;
        int numberOfSteps = 20000;
        Problem problem = new Problem(f,g,initialValue, initialTime, numberOfSteps,timeStep);

        problem.setEquationText("Geometric Brownian Motion","dy=.001*y*dt + .005*y*dW");

        //reflections to test private methods
        Method solve = Problem.class.getDeclaredMethod("solve");
        solve.setAccessible(true);
        solve.invoke(problem);

        return problem;
    }

}
