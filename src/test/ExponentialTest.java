package test;


import domain.BivariateFunction;
import domain.FunctionUtils;
import domain.Problem;
import domain.Solution;
import junit.framework.Assert;
import org.junit.Test;
import test.graphing.TestPlotter;


public class ExponentialTest {

    @Test
    public void test() throws Exception {

        BivariateFunction f = FunctionUtils.affine(0d, 1d, 0d);
        BivariateFunction g = FunctionUtils.affine(0d, 0d, 0d);
        double initialTime = 0d;
        double endTime = 2d;
        double initialValue = 1d;
        int numberOfSteps = 20000;
        Problem problem = new Problem(f,g,initialValue, initialTime, endTime, numberOfSteps);
        problem.setEquationText("Exponential","y=exp(x)");

        //create solution
        double tempTime = initialTime;
        Solution exp = new Solution();
        exp.addPair(tempTime, Math.exp(tempTime));
        for (int k = 0; k < problem.getNumberOfSteps(); k++) {
            tempTime += problem.getTimeStep();
            exp.addPair(tempTime, Math.exp(tempTime));
        }


        Assert.assertTrue(problem.getSolution().equals(exp));
        Assert.assertTrue(new TestPlotter().plotSolution(problem));

    }
}
