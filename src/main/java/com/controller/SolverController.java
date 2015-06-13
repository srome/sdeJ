package com.controller;

import com.domain.SdeJException;
import com.domain.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.service.SolverService;

@Controller
@Component
public class SolverController {

    @Autowired
    SolverService solverService;

    @RequestMapping(value="/bm", method=RequestMethod.GET)
    public @ResponseBody
    Solution getBrownianMotion(@RequestParam(value="scale", required=false, defaultValue="1") final double scale,
                               @RequestParam(value="drift", required=false, defaultValue="0") final double drift,
                               @RequestParam(value="timeStep", required = false, defaultValue = ".01") final double timeStep,
                               @RequestParam(value="initialValue", required = false, defaultValue = "1") final double initialValue,
                               @RequestParam(value="numberOfSteps", required = false, defaultValue = "100") final int numberOfSteps,
                               @RequestParam(value="initialTime", required = false, defaultValue = "0") final double initialTime) throws SdeJException {
        return solverService.getBrownianMotionProblem(scale, drift, timeStep, initialValue, numberOfSteps, initialTime);
    }


    @RequestMapping(value="/gbm", method=RequestMethod.GET)
    public @ResponseBody
    Solution getGeometricBrownianMotion(@RequestParam(value="scale", required=false, defaultValue="1") final double scale,
                                        @RequestParam(value="drift", required=false, defaultValue="0") final double drift,
                                        @RequestParam(value="timeStep", required = false, defaultValue = ".01") final double timeStep,
                                        @RequestParam(value="initialValue", required = false, defaultValue = "1") final double initialValue,
                                        @RequestParam(value="numberOfSteps", required = false, defaultValue = "100") final int numberOfSteps,
                                        @RequestParam(value="initialTime", required = false, defaultValue = "0") final double initialTime) throws SdeJException {

        return solverService.getGeometricBrownianMotionProblem(scale, drift, timeStep, initialValue, numberOfSteps, initialTime);

    }

}