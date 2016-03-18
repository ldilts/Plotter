/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Reader {
    private String filePath = "";
    private BufferedReader bufferedReader;
    
    private ArrayList<String> lines;
    
    public Reader(String filePath) {
        this.filePath = filePath;
        this.lines = new ArrayList<String>();
    }
    
    public ArrayList<String> startReading() {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.filePath));
            
            String line = null;
            
            try {
                while(bufferedReader.ready()) {
                    line = bufferedReader.readLine();
                    lines.add(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.lines;
    }
}
