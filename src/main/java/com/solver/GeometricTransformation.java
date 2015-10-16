package com.solver;

import com.domain.Solution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeometricTransformation {

    public Solution transformToGeometric(final Solution solution, final double initialPosition) {
        List<Double> yValues = solution.getY();
        List<Double> newY = new ArrayList<>();
        yValues.forEach(y -> newY.add(initialPosition * Math.exp(y)));

        return new Solution(solution.getX(), newY);
    }
}
