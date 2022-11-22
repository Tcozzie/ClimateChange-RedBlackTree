/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climate.change;

/**
 *
 * @author tiegancozzie
 */
public class Date implements Comparable<Date> {
    private String Date;
    private Double Info;
    
    public Date(String date,Double info){ 
        this.Date=date; // assigns values
        this.Info=info; // assigns values
        
    }
    
    public String key(){ // allows you to get the key of a set
        return this.Date;
    }
    
    public double info(){ // allows you to get the value of a set
        return this.Info;
    }


    @Override
    public int compareTo(Date o) {
        String[] split1=this.Date.split("/"); //splits up first thing being compared into an array
        String[] split2=o.Date.split("/"); // splits up second thing being compared into an array
        int year1=Integer.parseInt(split1[2]); // gets the year
        int year2=Integer.parseInt(split2[2]); // gets the year
        int month1=Integer.parseInt(split1[0]); // gets the month
        int month2=Integer.parseInt(split2[0]); // gets the month
        int day1=Integer.parseInt(split1[1]); // gets the day
        int day2=Integer.parseInt(split2[1]); // gets the day
        if((Integer.compare(year1,year2)==0)){ // checks to see which year is smaller, if they are the same check the month
            if(Integer.compare(month1,month2)==0){ // checks to see which month is smaller, if ther are the same check the day
                if(Integer.compare(day1,day2)==0){ // checks to see which day is smaller
                    return 0;
                }else{
                    return Integer.compare(day1,day2); // returns the smaller day
                }
            }else{
                return Integer.compare(month1,month2); // returns the smaller month
            }
        }else{
            return Integer.compare(year1,year2); // returns the smaller year
        }
    }
}
