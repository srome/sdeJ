package test;


import domain.BivariateFunction;
import domain.FunctionUtils;
import domain.Problem;
import junit.framework.Assert;
import org.junit.Test;
import test.graphing.TestPlotter;


public class FirstOrderSDEDampeningTest {

    @Test
    public void test() throws Exception {

        BivariateFunction f = FunctionUtils.affine(0d, 1d, 0d);
        BivariateFunction g = FunctionUtils.exponential(1d, -10d, 0d, 0d, 0d);
        BivariateFunction h = FunctionUtils.multiply(f, g);
        BivariateFunction j = FunctionUtils.affine(0d, 0d, 0d);

        double initialTime = 0d;
        double endTime = 2d;
        double initialValue = 1d;
        int numberOfSteps = 100000;
        Problem problem = new Problem(f,g,initialValue, initialTime, endTime, numberOfSteps);
        problem.setEquationText("First Order SDE with Dampening", "dy=ydt + e^(-10t)dW");

        Assert.assertTrue(new TestPlotter().plotSolution(problem));

    }
}
