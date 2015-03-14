package test;


import domain.BivariateFunction;
import domain.FunctionUtils;
import domain.Problem;
import junit.framework.Assert;
import org.junit.Test;
import test.graphing.TestPlotter;


public class MultiplicationODETest {

    @Test
    public void test() throws Exception {

        BivariateFunction f = FunctionUtils.affine(0d, 1d, 0d);
        BivariateFunction g = FunctionUtils.exponential(1d, -1d, 0d, 0d, 0d);
        BivariateFunction h = FunctionUtils.multiply(f, g);
        BivariateFunction j = FunctionUtils.affine(0d, 0d, 0d);

        double initialTime = 0d;
        double endTime = 2d;
        double initialValue = 1d;
        int numberOfSteps = 20000;
        Problem problem = new Problem(h,j,initialValue, initialTime, endTime, numberOfSteps);
        problem.setEquationText("First Order ODE With Multiplication", "dy=t*exp(-t)dt");

        Assert.assertTrue(new TestPlotter().plotSolution(problem));

    }
}
