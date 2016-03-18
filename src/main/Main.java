/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.PlotterController;
import model.Reader;
import view.PlotterView;

/**
 *
 * @author Lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PlotterView view = new PlotterView();
        Reader reader = new Reader("");
        
        PlotterController controller = new PlotterController(view, reader);
        
        
        view.setVisible(true);
    }
    
}
