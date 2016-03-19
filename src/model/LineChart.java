/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Lucas
 */
public class LineChart {

    private ChartPanel chartPanel;

    public LineChart(String string, Scenario[] scenarios, int quantity, int size, ScenarioType type, int variableOption) {
        final XYDataset dataset = createDataset(scenarios,
                quantity,
                size,
                type,
                variableOption);
        final JFreeChart chart = createChart(dataset, variableOption);
        this.chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(510, 282));
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private XYDataset createDataset(final Scenario[] scenarios, 
            final int quantity, 
            final int size, 
            final ScenarioType type, 
            final int variableOption) {

        final XYSeries series1 = new XYSeries("Sequential");
        final XYSeries series2 = new XYSeries("Parallel");
        
        System.out.println("Scenarios size: " + scenarios.length);

        for (Scenario scenario : scenarios) {
            switch (variableOption) {
                case 0:
                    if (scenario.getSize() == size
                            && scenario.getType() == type) {
                        System.out.println(scenario.getQuantity() + " " + scenario.getSize() + " " + scenario.getType() + " " + scenario.getSequentialAverageTime());
                        series1.add(scenario.getQuantity(), scenario.getSequentialAverageTime());
                        series2.add(scenario.getQuantity(), scenario.getParallelAverageTime());
                        
                    }   break;
                case 1:
                    if (scenario.getQuantity() == quantity 
                            && scenario.getType() == type) {
                        System.out.println(scenario.getQuantity() + " " + scenario.getSize() + " " + scenario.getType() + " " + scenario.getSequentialAverageTime());
                        series1.add(scenario.getSize(), scenario.getSequentialAverageTime());
                        series2.add(scenario.getSize(), scenario.getParallelAverageTime());

                    }   break;
                default:
                    if (scenario.getQuantity() == quantity 
                            && scenario.getSize() == size) {
                        System.out.println(scenario.getQuantity() + " " + scenario.getSize() + " " + scenario.getType() + " " + scenario.getSequentialAverageTime());
                        series1.add(0, scenario.getSequentialAverageTime());
                        series2.add(1, scenario.getParallelAverageTime());
                    }   break;
            }
            
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset, final int variableOption) {
        
        String title = "";
        switch (variableOption) {
            case 0: title = "Quantity"; break;
            case 1: title = "Size"; break;
            default: title = "Type"; break;
        }

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title + " Line Chart", // chart title
                title, // x axis label
                "Time", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

    public ChartPanel getChartPanel() {
        return this.chartPanel;
    }
}
