package com.domain;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final List<Double> x; //x values of solution

    private final List<Double> y; //y values of solution

    public Solution() {
        x = new ArrayList<>();
        y = new ArrayList<>();
    }

    public Solution(List<Double> x, List<Double> y) {
        this.x = x;
        this.y = y;
    }

    public void addPair(Double x, Double y) {
        this.x.add(x);
        this.y.add(y);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null || !(other instanceof Solution)) {
            return false;
        }

        final Solution solution = (Solution) other;
        if (!this.dataPoints().equals(solution.dataPoints())) {
            return false;
        }

        double tolerance = .001;
        List<Double> oX = solution.getX();
        List<Double> oY = solution.getY();
        for (int k = 0; k < this.dataPoints(); k++) {
            if ( Math.abs(x.get(k) - oX.get(k)) > tolerance || Math.abs(y.get(k) - oY.get(k)) > tolerance ) {
                return false;
            }
        }
        return true;
    }

    public Integer dataPoints() {
        return x.size();
    }

    public List<Double> getY() {
        return y;
    }

    public List<Double> getX() {
        return x;
    }
}
