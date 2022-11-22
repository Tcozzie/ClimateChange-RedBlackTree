/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climate.change;

/**
 *
 * @author tiegancozzie
 */
public class co2Level implements Comparable<co2Level> {
    private double Co2;
    private Date Date;
    
    public co2Level(double co2,Date date){
        this.Co2=co2; // assigns a value
        this.Date=date; // assigns a value
    }
    
    public double key(){ // allows you to get a key from a set
        return this.Co2;
    }
    
    public Date info(){ // allows you to get a key from a set
        return this.Date;
    }

    @Override
    public int compareTo(co2Level o) {
        return Double.compare(this.Co2,o.Co2);
    }
}
