package domain;

import java.util.function.BinaryOperator;

/**
 * Class to be instantiated when defining coefficient functions in SDE. See test for examples.
 * The variable t denotes time and y is the slot used for the function itself. See examples in the
 * test suite and FunctionUtils.
 * @author Scott Rome
 */
public class BivariateFunction implements BinaryOperator<Double> {

    @Override
    public Double apply(Double t, Double y) {
        return null;
    }


    public Double evaluate(final Double t, final Double y) {
        return apply(t, y);
    }

    public Double evaluate(final Double t) {
        return evaluate(t, 0d);
    }
}
