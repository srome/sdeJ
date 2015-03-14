package test;


import domain.BivariateFunction;
import domain.FunctionUtils;
import domain.Problem;
import domain.Solution;
import junit.framework.Assert;
import org.junit.Test;
import test.graphing.TestPlotter;


public class AffineTest {

    @Test
    public void test() throws Exception {

        BivariateFunction f = FunctionUtils.affine(0d, 0d, 1d);
        BivariateFunction g = FunctionUtils.affine(0d, 0d, 0d);
        double initialTime = 0d;
        double endTime = 5d;
        double initialValue = 1d;
        int numberOfSteps = 20000;
        Problem problem = new Problem(f,g,initialValue, initialTime, endTime, numberOfSteps);
        problem.setEquationText("Linear", "y=x");

        //create solution
        double tempTime = initialTime;
        Solution x = new Solution();
        x.addPair(tempTime, 1d);
        for (int k = 0; k < problem.getNumberOfSteps(); k++) {
            tempTime += problem.getTimeStep();
            x.addPair(tempTime, tempTime + 1d);
        }

        Assert.assertTrue(problem.getSolution().equals(x));
        Assert.assertTrue(new TestPlotter().plotSolution(problem));


    }
}
