package com.solver;

import com.domain.Solution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeometricTransformation {

    public Solution transformToGeometric(final Solution solution, final double initialPosition) {
        List<Double> y = solution.getY();
        List<Double> newY = new ArrayList<>();
        for (double value : y) {
            newY.add(initialPosition * Math.exp(value));
        }

        return new Solution(solution.getX(), newY);
    }
}
