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
        String delims = "[-=;#&]+";
        String[] tokens = rawScenario.split(delims);
        
        // TODO if all fields are not provided, throw an error
        
        this.parallelTimes = new int[]{Integer.parseInt(tokens[10]),
            Integer.parseInt(tokens[11]),
            Integer.parseInt(tokens[12]),
            Integer.parseInt(tokens[13]),
            Integer.parseInt(tokens[14])};
        this.sequentialTimes = new int[]{Integer.parseInt(tokens[4]),
            Integer.parseInt(tokens[5]),
            Integer.parseInt(tokens[6]),
            Integer.parseInt(tokens[7]),
            Integer.parseInt(tokens[8])};
        this.parallelAverageTime = Integer.parseInt(tokens[15]);
        this.sequentialAverageTime = Integer.parseInt(tokens[9]);

        this.size = Integer.parseInt(tokens[1]);
        this.quantity = Integer.parseInt(tokens[0]);
//        this.type =s

        this.machine = tokens[3];
    }
    
    // Getters
    public String getMachine() {
        return this.machine;
    }
}
