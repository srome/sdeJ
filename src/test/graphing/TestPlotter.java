package test.graphing;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.progra.charting.ChartEncoder;
import de.progra.charting.DefaultChart;
import de.progra.charting.model.DefaultChartDataModel;
import de.progra.charting.render.LineChartRenderer;
import domain.Problem;

/**
 * Class which generates graphs for the tests.
 */
public class TestPlotter {
    //Adapted from a tutorial for jopenchart

    //allows for multiple functions to be plotted on same x axis.
    //functions must have the same x-values.
    public List<List<Double>> yValues = new ArrayList<List<Double>>();
    public List<String> yTitles = new ArrayList<String>();
    public List<Double> xAxis = new ArrayList<Double>();

    public void addXAxis(List<Double> values) {
        xAxis.addAll(values);
    }

    public void addFunctionValues(String title, List<Double> yValues) {
        this.yValues.add(yValues);
        yTitles.add(title);
    }

    public boolean build(String title) {
        int numberOfDataPoints = yValues.iterator().next().size();
        int numberOfFunctions = yValues.size();
        double[][] yPoints = new double[numberOfFunctions][numberOfDataPoints]; // Create data array
        double[] xPoints = new double[numberOfDataPoints];

        //add data to model
        for (int j = 0; j < numberOfDataPoints; j++) {
            for (int k = 0; k < numberOfFunctions; k++) {
                yPoints[k][j] = yValues.get(k).get(j);   //k rows by j columns
            }
            xPoints[j] = xAxis.get(j);
        }

        //add titles of functions
        String[] titlesOfFunctions = new String[numberOfFunctions];
        yTitles.toArray(titlesOfFunctions);

        int width = 640; // Image size
        int height = 480;
        // Create data model

        DefaultChartDataModel data = new DefaultChartDataModel(yPoints, xPoints, titlesOfFunctions);
        // Create chart with default coordinate system
        DefaultChart c = new DefaultChart(data, title,
                DefaultChart.LINEAR_X_LINEAR_Y);

        // Add a line chart renderer
        c.addChartRenderer(new LineChartRenderer(c.getCoordSystem(), data), 1);
        // Set the chart size
        c.setBounds(new Rectangle(0, 0, width, height));
        // Export the chart as a PNG image

        File directory = new File(System.getProperty("user.dir") + "/output");

        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            ChartEncoder.createEncodedImage(
                    new FileOutputStream(System.getProperty("user.dir")
                            + "/output/" + title + ".png"), c,
                    "png");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public TestPlotter addProblemToPlot(Problem problem) throws Exception {
        List<Double> x = problem.getSolution().getX();
        if (xAxis.isEmpty()) {
            this.addXAxis(x);
        } else if (!this.xAxis.equals(x)) {
            throw new Exception("X axis are not equal");
        }
        this.addFunctionValues(problem.getEquation(), problem.getSolution().getY());
        return this;
    }

    public Boolean plotSolution(Problem problem) throws Exception {
        return addProblemToPlot(problem).plot(problem);
    }

    private Boolean addToPlot(Problem problem) throws Exception {
        addProblemToPlot(problem);
        return true;
    }

    public boolean plot(Problem problem) {
        return build(problem.getEquationName());
    }


    public boolean plot(String title) {
        return build(title);
    }
}


