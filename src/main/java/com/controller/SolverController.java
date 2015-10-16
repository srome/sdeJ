package com.controller;

import com.domain.SdeJException;
import com.domain.Solution;
import com.service.SolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component
public class SolverController {

    @Autowired
    private SolverService solverService;

    @RequestMapping(value="/bm")
    public @ResponseBody
    Solution getBrownianMotion(@RequestParam(value="scale", defaultValue="1") final double scale,
                               @RequestParam(value="drift", defaultValue="0") final double drift,
                               @RequestParam(value="timeStep", defaultValue = ".01") final double timeStep,
                               @RequestParam(value="initialValue", defaultValue = "1") final double initialValue,
                               @RequestParam(value="numberOfSteps", defaultValue = "100") final int numberOfSteps,
                               @RequestParam(value="initialTime", defaultValue = "0") final double initialTime) throws SdeJException {
        return solverService.getBrownianMotionProblem(scale, drift, timeStep, initialValue, numberOfSteps, initialTime);
    }


    @RequestMapping(value="/gbm")
    public @ResponseBody
    Solution getGeometricBrownianMotion(@RequestParam(value="scale", defaultValue="1") final double scale,
                                        @RequestParam(value="drift", defaultValue="0") final double drift,
                                        @RequestParam(value="timeStep", defaultValue = ".01") final double timeStep,
                                        @RequestParam(value="initialValue", defaultValue = "1") final double initialValue,
                                        @RequestParam(value="numberOfSteps", defaultValue = "100") final int numberOfSteps,
                                        @RequestParam(value="initialTime", defaultValue = "0") final double initialTime) throws SdeJException {

        return solverService.getGeometricBrownianMotionProblem(scale, drift, timeStep, initialValue, numberOfSteps, initialTime);

    }

}