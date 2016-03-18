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
        final JFreeChart chart = createChart(dataset);
        this.chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(510, 282));
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private XYDataset createDataset(Scenario[] scenarios, 
            int quantity, 
            int size, 
            ScenarioType type, 
            int variableOption) {

        final XYSeries series1 = new XYSeries("Sequential");
        final XYSeries series2 = new XYSeries("Parallel");

        for (Scenario scenario : scenarios) {
            switch (variableOption) {
                case 0:
//                    System.out.println("Quantity is variable");
                    if (scenario.getSize() == size
                            && scenario.getType() == type) {
                        for (int timeStamp : scenario.getSequentialTimes()) {
                            series1.add(scenario.getQuantity(), timeStamp);
                        }
                        
                        for (int timeStamp : scenario.getParallelTimes()) {
                            series2.add(scenario.getQuantity(), timeStamp);
                        }
                    }   break;
                case 1:
//                    System.out.println("Size is variable");
                    if (scenario.getType() == type
                            && scenario.getQuantity() == quantity) {
                        for (int timeStamp : scenario.getSequentialTimes()) {
                            series1.add(scenario.getSize(), timeStamp);
                        }
                        
                        for (int timeStamp : scenario.getParallelTimes()) {
                            series2.add(scenario.getSize(), timeStamp);
                        }
                    }   break;
                default:
//                    System.out.println("Else is variable");
                    for (int timeStamp : scenario.getSequentialTimes()) {
                        series1.add(0, timeStamp);
                    }   for (int timeStamp : scenario.getParallelTimes()) {
                        series2.add(1, timeStamp);
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
    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Demo 6", // chart title
                "X", // x axis label
                "Y", // y axis label
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
