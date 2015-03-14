package test;


import domain.BivariateFunction;
import domain.FunctionUtils;
import domain.Problem;
import domain.Solution;
import junit.framework.Assert;
import org.junit.Test;
import test.graphing.TestPlotter;


public class XSquaredTest {

    @Test
    public void test() throws Exception {

        BivariateFunction f = FunctionUtils.affine(2d, 0d, 0d);
        BivariateFunction g = FunctionUtils.affine(0d, 0d, 0d);
        double initialTime = 1d;
        double endTime = 3d;
        double initialValue = 1d;
        int numberOfSteps = 20000;
        Problem problem = new Problem(f,g,initialValue, initialTime, endTime, numberOfSteps);
        problem.setEquationText("X_Squared", "y=x^2");

        //create solution
        double tempTime = initialTime;
        Solution x2 = new Solution();
        x2.addPair(tempTime, tempTime * tempTime);
        for (int k = 0; k < problem.getNumberOfSteps(); k++) {
            tempTime += problem.getTimeStep();
            x2.addPair(tempTime, tempTime * tempTime);
        }

        Assert.assertTrue(problem.getSolution().equals(x2));
        Assert.assertTrue(new TestPlotter().plotSolution(problem));
    }
}
