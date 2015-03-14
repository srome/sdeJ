package test;

import domain.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;

public class InterpolatedFunctionTest {

    double initialTime = 0d;
    double endTime = 2d;
    double initialValue = 0d;
    int numberOfSteps = 20000;
    Random rand = new Random();

    @Test
    public void test() throws Exception {
        final InterpolatedFunction testFunction = createTestFunction();

        rand.setSeed(System.currentTimeMillis());
        double tempTime = initialTime;
        double timeStep = (endTime - initialTime) / ((double) numberOfSteps);
        for (int k = 0; k < numberOfSteps; k++) {
            double aDouble = initialTime + k * timeStep + timeStep/2d; //test the midpoints of the lines are close to the interpolated value
            if (aDouble > 2d) {
                aDouble = 2d;
            } else if ( aDouble < 0d) {
                aDouble = 0d;
            }
            Assert.assertTrue(Math.abs(testFunction.apply(aDouble) - Math.pow(aDouble,2)) < .001);
        }


        Assert.assertTrue(Math.abs(testFunction.apply(1.1d) - 1.1d*1.1d) < .001);
        Assert.assertTrue(Math.abs(testFunction.apply(.5d) - .5d*.5d) < .001);
        Assert.assertTrue(Math.abs(testFunction.apply(.1d) - .1d*.1d) < .001);
        Assert.assertTrue(Math.abs(testFunction.apply(.715d) - .715d*.715d) < .001);
        Assert.assertTrue(Math.abs(testFunction.apply(.23d) - .23d*.23d) < .001);
        Assert.assertTrue(testFunction.apply(100d).equals(Double.NaN));

    }

    public InterpolatedFunction createTestFunction() throws Exception {
        BivariateFunction f = FunctionUtils.affine(2d, 0d, 0d);
        BivariateFunction g = FunctionUtils.affine(0d, 0d, 0d);
        Problem problem = new Problem(f,g,initialValue, initialTime, endTime, numberOfSteps);

        return problem.getSolutionAsFunction();
    }
}
