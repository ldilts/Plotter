/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lucas
 */
public class Scenario {
    private int[] parallelTimes;
    private int[] sequentialTimes;
    private int parallelAverageTime;
    private int sequentialAverageTime;
    
    private int size;
    private int quantity;
    private ScenarioType type;
    
    private String machine;
    
    public Scenario(String rawScenario) {
        this.inflateScenarioWith(rawScenario);
    }
    
    // Helper Methods
    
    private void inflateScenarioWith(String rawScenario) {
//        this.parallelTimes = 
//        this.sequentialTimes =
//        this.parallelAverageTime =
//        this.sequentialAverageTime =

//        this.size =
//        this.quantity =
//        this.type =

//        this.machine =
    }
}
