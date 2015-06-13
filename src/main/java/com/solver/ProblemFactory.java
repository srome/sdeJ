package com.solver;

import com.domain.Problem;
import com.domain.SdeJException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.utils.FunctionUtils;

import java.util.function.BiFunction;

@Service
public class ProblemFactory {

    public Problem getBrownianMotionProblem(final @RequestParam(value = "scale", required = false, defaultValue = "1") double scale, final @RequestParam(value = "drift", required = false, defaultValue = "0") double drift, final @RequestParam(value = "timeStep", required = false, defaultValue = ".01") double timeStep, final @RequestParam(value = "initialValue", required = false, defaultValue = "1") double initialValue, final @RequestParam(value = "numberOfSteps", required = false, defaultValue = "100") int numberOfSteps, final @RequestParam(value = "initialTime", required = false, defaultValue = "0") double initialTime) throws SdeJException {
        BiFunction<Double,Double,Double> f = FunctionUtils.affine(0d, 0d, drift);
        BiFunction<Double,Double,Double> g = FunctionUtils.affine(0d, 0d, scale);
        return new Problem(f,g,initialValue, initialTime, numberOfSteps,timeStep);
    }

    public Problem getGeometricBrownianMotionProblem(final @RequestParam(value = "scale", required = false, defaultValue = "1") double scale, final @RequestParam(value = "drift", required = false, defaultValue = "0") double drift, final @RequestParam(value = "timeStep", required = false, defaultValue = ".01") double timeStep, final @RequestParam(value = "initialValue", required = false, defaultValue = "1") double initialValue, final @RequestParam(value = "numberOfSteps", required = false, defaultValue = "100") int numberOfSteps, final @RequestParam(value = "initialTime", required = false, defaultValue = "0") double initialTime) throws SdeJException {
        BiFunction<Double,Double,Double> f = FunctionUtils.affine(0d, drift, 0d);
        BiFunction<Double,Double,Double> g = FunctionUtils.affine(0d, scale, 0d);
        return new Problem(f,g,initialValue, initialTime, numberOfSteps,timeStep);
    }
}
