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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LineChart;
import model.Reader;
import model.Scenario;
import model.ScenarioType;
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
            
            String filePath = view.getTextFieldText();
            reader = new Reader("C:\\Users\\Lucas.Eurico\\Desktop\\arq-tempo-dilts.txt");
            
            try {
                ArrayList<String> rawSenarios = reader.startReading();
                
                scenarios = new Scenario[rawSenarios.size()];
        
                for (String rawScenario : rawSenarios) {
                    Scenario scenario = new Scenario(rawScenario);
                    scenarios[rawSenarios.indexOf(rawScenario)] = scenario;
                }
                
                int quantity = 0;
                int size = 0;
                ScenarioType type = ScenarioType.TEXTUAL;
                
                switch (view.getQuantityComboBoxIndex()) {
                    case 0: quantity = 10; break;
                    case 1: quantity = 50; break;
                    case 2: quantity = 100; break;
                }
                
                switch (view.getSizeComboBoxIndex()) {
                    case 0: size = 1; break;
                    case 1: size = 10; break;
                    case 2: size = 50; break;
                }
                
                switch (view.getTypeComboBoxIndex()) {
                    case 0: type = ScenarioType.BINARY; break;
                    case 1: type = ScenarioType.TEXTUAL; break;
                }
                
                System.out.println("Combobox option is: " + view.getComboBoxIndex());
                
                LineChart lineChart = new LineChart("",
                        scenarios,
                        quantity, 
                        size,
                        type,
                        view.getComboBoxIndex());
                view.setChartPanel(lineChart.getChartPanel());
            } catch (IOException ex) {
                 Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
