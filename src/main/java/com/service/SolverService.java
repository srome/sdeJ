package com.service;

import com.domain.problem.Problem;
import com.domain.SdeJException;
import com.domain.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.solver.GeometricTransformation;
import com.solver.NumericSolver;
import com.domain.problem.ProblemFactory;

@Component
public class SolverService {

    @Autowired
    private ProblemFactory problemBuilderService;
    @Autowired
    private NumericSolver numericSolver;
    @Autowired
    private GeometricTransformation geometricTransformation;

    public Solution getBrownianMotionProblem(final double scale, final double drift, final double timeStep, final double initialValue, final int numberOfSteps, final double initialTime) throws SdeJException {
        Problem problem = problemBuilderService.getBrownianMotionProblem(scale, drift, timeStep, initialValue, numberOfSteps, initialTime);
        return numericSolver.solve(problem);
    }

    public Solution getGeometricBrownianMotionProblem(final double scale, final double drift, final double timeStep, final double initialValue, final int numberOfSteps, final double initialTime) throws SdeJException {
        Problem problem = problemBuilderService.getBrownianMotionProblem(scale, (drift-(scale*scale)/2d), timeStep, 0d, numberOfSteps, initialTime);
        Solution solution = numericSolver.solve(problem);
        return geometricTransformation.transformToGeometric(solution, initialValue);
    }
}
