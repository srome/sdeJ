package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * This class contains data points (x,y) for a function f(x)=y and can evaluate f(t) for any t in (a,b).
 * The evaluation of f(t) is calculated via the data points and linear interpolation.
 * @author Scott Rome
 */
public class InterpolatedFunction implements Function<Double, Double> {
    final List<Double> x, y;

    protected InterpolatedFunction(final List<Double> xPoints, final List<Double> yPoints){
        this.x = xPoints;
        this.y = yPoints;
    }

    /**
     * Let the interpolated function be denoted as f. This calculates f(t) for t=aDouble and returns the value.
     * Recommended method to use for proper exception handling.
     * @param aDouble The t value to compute f(t)
     * @return f(t)
     * @throws SdeJException
     */
    public Double evaluate(final Double aDouble) throws SdeJException {
        return interpolate(aDouble);
    }

    @Override
    public Double apply(final Double aDouble) {
        try {
            return interpolate(aDouble);
        } catch (SdeJException e) {
            return Double.NaN;
        }
    }

    private int findLowerIndex(Double input) throws SdeJException {
        //perform a binary search to find the index k such that x(k) <= input
        int min = 0;
        int max = this.x.size()-1;

        if (!isInDomain(input)) {
            throw new SdeJException("Input is outside of the domain of the function");
        }

        while ( min != max-1 ) {
            int mid = (int) Math.floor( (max+min) / 2d);
            if ( input < x.get(mid)) {
                max = mid;
                if (input > x.get(min+1)) {
                    min++;
                }
            } else {
                min = mid;
                if (input < x.get(max-1)) {
                    max--;
                }
            }
        }

        return min;
    }

    /**
     * Determines whether the input is in the domain of the function.
     * @param input Value that one wants to check is in the domain
     * @return true if the input is in the domain, false otherwise
     */
    public boolean isInDomain(final Double input) {
        return (input <= x.get(x.size()-1)) && (input >= x.get(0));
    }

    /**
     * Returns the x values used in the interpolation.
     * @return An unmodifiable list of type double containing the x values of the data points.
     */
    public List<Double> getX() {
        return Collections.unmodifiableList(x);
    }

    /**
     * Returns the y values used in the interpolation.
     * @return A unmodifiable list of type double cotnaining the function values of the data points.
     */
    public List<Double> getY() {
        return Collections.unmodifiableList(y);
    }

    private double interpolate(final Double input) throws SdeJException {
        /**
         * (y-f(a)) = ((f(b)-f(a))/(b-a))(input -a)
         */
        final int indexOfA = findLowerIndex(input);
        final int indexOfB = indexOfA + 1;
        final double fa = y.get(indexOfA);
        final double fb = y.get(indexOfB);
        final double a = x.get(indexOfA);
        final double b = x.get(indexOfB);


        return fa + ((fb-fa)/(b-a))*(input - a);
    }

}
