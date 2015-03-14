package domain;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final List<Double> x; //x values of solution

    private final List<Double> y; //y values of solution

    public Solution() {
        x = new ArrayList<Double>();
        y = new ArrayList<Double>();
    }

    public void addPair(Double x, Double y) {
        this.x.add(x);
        this.y.add(y);
    }

    public boolean equals(Solution solution) {
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
