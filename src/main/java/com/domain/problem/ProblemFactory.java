package com.domain.problem;

import com.domain.SdeJException;
import com.utils.FunctionUtils;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class ProblemFactory {

    public Problem getBrownianMotionProblem(final double scale,
                                            final double drift,
                                            final double timeStep,
                                            final double initialValue,
                                            final int numberOfSteps,
                                            final double initialTime) throws SdeJException {
        BiFunction<Double,Double,Double> f = FunctionUtils.affine(0d, 0d, drift);
        BiFunction<Double,Double,Double> g = FunctionUtils.affine(0d, 0d, scale);
        return new Problem(f,g,initialValue, initialTime, numberOfSteps,timeStep);
    }

    public Problem getGeometricBrownianMotionProblem(final double scale,
                                                     final double drift,
                                                     final double timeStep,
                                                     final double initialValue,
                                                     final int numberOfSteps,
                                                     final double initialTime) throws SdeJException {
        BiFunction<Double,Double,Double> f = FunctionUtils.affine(0d, drift, 0d);
        BiFunction<Double,Double,Double> g = FunctionUtils.affine(0d, scale, 0d);
        return new Problem(f,g,initialValue, initialTime, numberOfSteps,timeStep);
    }
}
