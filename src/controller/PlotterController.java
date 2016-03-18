/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    
    public PlotterController(PlotterView view, Reader reader) {
        this.view = view;
        this.reader = reader;
        
        this.view.addComboBoxListener(new ComboBoxListener());
        ArrayList<String> rawSenarios = this.reader.startReading();
        this.scenarios = new Scenario[rawSenarios.size()];
        
        for (String rawScenario : rawSenarios) {
            Scenario scenario = new Scenario(rawScenario);
            this.scenarios[rawSenarios.indexOf(rawScenario)] = scenario;
        }
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
}
