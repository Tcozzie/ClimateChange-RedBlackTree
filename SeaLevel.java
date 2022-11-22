/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climate.change;

/**
 *
 * @author tiegancozzie
 */
public class SeaLevel implements Comparable<SeaLevel> {
    private double seaLevel;
    private Date Date;
    
    public SeaLevel(double sealevel,Date date){
        this.seaLevel=sealevel; // assigns a value
        this.Date=date; // assigns a value
    }
    
    public double key(){ // allows you to get a key of a set
        return this.seaLevel;
    }
    
    public Date info(){ // allows you to get a value from a set
        return this.Date;
    }

    @Override
    public int compareTo(SeaLevel o) {
        return Double.compare(this.seaLevel,o.seaLevel);
    }
}
