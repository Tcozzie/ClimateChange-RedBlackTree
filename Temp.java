/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climate.change;

/**
 *
 * @author tiegancozzie
 */
public class Temp implements Comparable<Temp>{
    private double Temp;
    private Date Date;
    
    public Temp(double temp,Date date){
        this.Temp=temp; // assigns a value
        this.Date=date; // assigns a value
    }
    
    public double key(){ // allows you to get a key from a set
        return this.Temp;
    }
    
    public String info(){ // allows you to get a value from a set
        return this.Date.key();
    }

    @Override
    public int compareTo(Temp o) {
        return Double.compare(this.Temp,o.Temp);
    }
}
