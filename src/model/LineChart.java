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

    public LineChart(String string, Scenario[] scenarios, int quantity,
            int size, ScenarioType type, int variableOption, String machine) {
        final XYDataset dataset = createDataset(scenarios,
                quantity,
                size,
                type,
                variableOption,
                machine);
        final JFreeChart chart = createChart(dataset, variableOption, machine);
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
            final int variableOption,
            final String machine) {

        final XYSeries series1 = new XYSeries("Sequential");
        final XYSeries series7 = new XYSeries("");
        final XYSeries series2 = new XYSeries("Parallel");
        final XYSeries series3 = new XYSeries("Sequential Dilts");
        final XYSeries series4 = new XYSeries("Parallel Dilts");
        final XYSeries series5 = new XYSeries("Sequential Jonathan");
        final XYSeries series6 = new XYSeries("Parallel Jonathan");
        
        System.out.println("Scenarios size: " + scenarios.length);

        for (Scenario scenario : scenarios) {
            
            switch (variableOption) {
                case 0:
                    if (scenario.getMachine().equals(machine)) {
                        if (scenario.getSize() == size
                                && scenario.getType() == type) {
                            System.out.println(scenario.getQuantity() + " " + scenario.getSize() + " " + scenario.getType() + " " + scenario.getSequentialAverageTime());
                            series1.add(scenario.getQuantity(), scenario.getSequentialAverageTime());
                            series2.add(scenario.getQuantity(), scenario.getParallelAverageTime());

                        }
                    }   break;
                case 1:
                    if (scenario.getMachine().equals(machine)) {
                        if (scenario.getQuantity() == quantity 
                                && scenario.getType() == type) {
                            System.out.println(scenario.getQuantity() + " " + scenario.getSize() + " " + scenario.getType() + " " + scenario.getSequentialAverageTime());
                            series1.add(scenario.getSize(), scenario.getSequentialAverageTime());
                            series2.add(scenario.getSize(), scenario.getParallelAverageTime());

                        }
                    } break;
                case 2:
                    if (scenario.getMachine().equals(machine)) {
                        if (scenario.getQuantity() == quantity 
                                && scenario.getSize() == size) {
                            System.out.println(scenario.getQuantity() + " " + scenario.getSize() + " " + scenario.getType() + " " + scenario.getSequentialAverageTime());
                            series1.add(0, scenario.getSequentialAverageTime());
                            series2.add(1, scenario.getParallelAverageTime());
                        }
                    } break;
                default:
                    if (scenario.getQuantity() == quantity 
                            && scenario.getSize() == size
                            && scenario.getType() == type) {
                        System.out.println("qtd: " + scenario.getQuantity() + " size: " + scenario.getSize() + " type: " + scenario.getType() + " seq: " + scenario.getSequentialAverageTime() + " par: " + scenario.getParallelAverageTime() + " machine: " + scenario.getMachine());
                        if ("caio".equals(scenario.getMachine())) {
                            series1.add(100, scenario.getSequentialAverageTime());
                            series2.add(100, scenario.getParallelAverageTime());
                        } else if ("dilts".equals(scenario.getMachine())) {
                            series3.add(200, scenario.getSequentialAverageTime());
                            series4.add(200, scenario.getParallelAverageTime());
                        } else if ("Jonathan".equals(scenario.getMachine())) {
                            series5.add(300, scenario.getSequentialAverageTime());
                            series6.add(300, scenario.getParallelAverageTime());
                        }  
                    } break; 

            }
            
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series7);
        dataset.addSeries(series2);
        
        if (variableOption == 3) {
            dataset.addSeries(series3);
            dataset.addSeries(series4);
            dataset.addSeries(series5);
            dataset.addSeries(series6);
        }

        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset,
            final int variableOption,
            final String machine) {
        
        String title = "";
        switch (variableOption) {
            case 0: title = "Quantity"; break;
            case 1: title = "Size"; break;
            default: title = "Type"; break;
        }

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title + " Line Chart: " + machine, // chart title
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
