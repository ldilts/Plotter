/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import model.LineChart;
import model.Reader;
import model.Scenario;
import view.PlotterView;

/**
 *
 * @author Lucas
 */
public class PlotterController {
    private final PlotterView view;
    private Reader reader;
    
    private Scenario[] scenarios;
    
    public PlotterController(PlotterView view) {
        this.view = view;
        
        this.view.addComboBoxListener(new ComboBoxListener());
        this.view.addButtonListener(new ButtonListener());
    }
    
    class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (view.getComboBoxIndex()) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    }
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
//            String filePath = view.getTextFieldText();
//            reader = new Reader(filePath);
//            
//            try {
//                ArrayList<String> rawSenarios = reader.startReading();
//                
//                scenarios = new Scenario[rawSenarios.size()];
//        
//                for (String rawScenario : rawSenarios) {
//                    Scenario scenario = new Scenario(rawScenario);
//                    scenarios[rawSenarios.indexOf(rawScenario)] = scenario;
//                    System.out.println(scenario.getMachine());
//                }
//            } catch (IOException ex) {
//                // TODO show error
//            }
            LineChart lineChart = new LineChart("Title");
            view.setChartPanel(lineChart.getChartPanel());
        }
    }
}
