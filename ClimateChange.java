/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package climate.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author tiegancozzie
 */
public class ClimateChange {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File seaFile=new File("sea_level.csv"); // reads in sea_level file
        File co2File=new File("co2.csv"); // reads in co2 file
        File tempFile=new File("temperature_anomaly.csv"); // reads in temperature_anomaly file
        FileWriter fWriter=new FileWriter("OutPut.txt"); // makes the output file and allows us to write to it
        
        Scanner seaIn=new Scanner(seaFile); // scanner for seaLevel file
        Scanner co2In=new Scanner(co2File); // scanner for co2 file
        Scanner tempIn=new Scanner(tempFile); // scanner for temperature file
        
        
        RedBlackTree<SeaLevel,Date> seaLevelTree=new RedBlackTree(); // RBT for seaLevel data with sea level rise being the key
        RedBlackTree<Date,SeaLevel> seaLevelTreeDate=new RedBlackTree(); // RBT for seaLevel data with date being the key
        RedBlackTree<co2Level,Date> co2Tree=new RedBlackTree(); // RBT for co2 with co2 concentration being the key
        RedBlackTree<Date,co2Level> co2TreeDate=new RedBlackTree(); // RBT for co2 with date being the key
        RedBlackTree<Temp,Date> tempTree=new RedBlackTree(); // RBT for temperature with temperature anomalies being the key
        RedBlackTree<Date,Temp> tempTreeDate=new RedBlackTree(); // RBT for temperature with date being the key
        
        String[] Months=new String[] {"Jan","Feb","March","April", "May","Jun","July","Aug","Sep","Oct","Nov","Dec"}; // Months of the year

        String[] seperate; // for splitting up the lines in each file
       
        seaIn.nextLine();
        
        while(seaIn.hasNext()){ // reads every line in the Sea Level file
            seperate=seaIn.nextLine().split(","); // takes out the "," in the lines and puts it into an array
            if(seperate[2].contains("-")){ // getting date into a uniform format
                String[] temp=seperate[2].split("-"); 
                String month=temp[1];
                String day=temp[2];
                String year=temp[0];
                seperate[2]=month+"/"+day+"/"+year; // putting the date in proper form
            }
            Date date=new Date(seperate[2],Double.parseDouble(seperate[3])); // Storing all the data for that line (DATE)
            SeaLevel sl=new SeaLevel(Double.parseDouble(seperate[3]),date); // Storing all the data for that line
            seaLevelTree.put(sl,sl.info()); // Inputting into the RBT with Sea Level being the key
            seaLevelTreeDate.put(date,sl); // Inputting into the RBT with Date being the key
        }
        
        
        
        seperate=null; // clearing all data in the line seperator
        
        co2In.nextLine();
        
        while(co2In.hasNext()){ // reads every line in the Co2 file
            seperate=co2In.nextLine().split(","); // takes out the "," in the lines and puts it into an array
            seperate[2]=seperate[2].replace("-","/"); // gets the date into a uniform format
            Date date=new Date(seperate[2],Double.parseDouble(seperate[3])); // Storing all the data for that line (DATE)
            co2Level co2=new co2Level(Double.parseDouble(seperate[3]),date); //Storing all the data for that line
            co2Tree.put(co2,date); // Inputting into the RBT with Co2 concentration being the key
            co2TreeDate.put(date,co2); // Inputting into the RBT with the Date being the key
        }
        
        
        seperate=null; // clearing all data in the line seperator
        
        tempIn.nextLine();
        
        while(tempIn.hasNext()){ // reads every line in the Temperature Anomalies file
            seperate=tempIn.nextLine().split(","); // takes out the "," in the lines and puts it into an array
            if(seperate[0].equals("World")){ // makes sure we are only reading "World" data within the file
                if(seperate[2].contains("-")){ // getting date into a uniform format
                String[] temp=seperate[2].split("-");
                String month=temp[1];
                String day=temp[2];
                String year=temp[0];
                seperate[2]=month+"/"+day+"/"+year; // putting the date in proper form
            }
            double conversion=Double.parseDouble(seperate[3]); // making the temperature into an integer
            conversion*=1.8; // converting from (C) to (F)
            conversion=Math.round(conversion*100); // rounding off the long decimal
            conversion/=100; 
            Date date=new Date(seperate[2],conversion); // Storing all the data for that line (DATE)
            Temp t=new Temp(conversion,date); // Storing all the data from that line
            tempTree.put(t,date); // Inputting into the RBT with temperature anomalies being the key
            tempTreeDate.put(date,t); // Inputting into the RBT with the Date being the key
            }
        }
        String[] Date=tempTree.minKeyValue().key().split("/"); // getting the individual numbers from the date
        //Writes to the output file. Gets the lowest Key and writes the date in proper format
        fWriter.write("Lowest Temperature anomaly (F): "+tempTree.min().key()+" on "+Months[Integer.parseInt(Date[0])-1]+" "+Date[1]+", "+Date[2]);
        fWriter.write("\n");
        if(co2TreeDate.get(tempTree.minKeyValue())!=null){ // checks to see if any data was taken on the same date as the lowest temperature anomaly
            //Writes to the output file. Gets the average Co2 concentration for the same date
            fWriter.write("On that same date, the Average Co2 concentration was "+co2TreeDate.get(tempTree.minKeyValue()).key());
            fWriter.write("\n");
        }
        
        if(seaLevelTreeDate.get(tempTree.minKeyValue())!=null){ // checks to see if any data was taken on the same date as the lowest temperature anomaly
            //Writes to the output file. Gets the Average Sea Level Rise for the same date
            fWriter.write("On that same date, the Average Sea Level Rise was "+seaLevelTreeDate.get(tempTree.minKeyValue()).key());
            fWriter.write("\n");
        }
        
        fWriter.write("\n");
        
        Date=tempTree.maxKeyValue().key().split("/"); // getting the individual numbers from the date
        //Writes to the output file. Gets the largest Key and writes the date in proper format
        fWriter.write("Highest Temperature anomaly (F): "+tempTree.max().key()+" on "+Months[Integer.parseInt(Date[0])-1]+" "+Date[1]+", "+Date[2]);
        fWriter.write("\n");
        if(co2TreeDate.get(tempTree.maxKeyValue())!=null){ // checks to see if any data was taken on the same date as the highest temperature anomaly
            //Writes to the output file. Gets the average Co2 concentration for the same date
            fWriter.write("On that same date, the Average Co2 concentration was "+co2TreeDate.get(tempTree.maxKeyValue()).key());
            fWriter.write("\n");
        }
        
        if(seaLevelTreeDate.get(tempTree.maxKeyValue())!=null){ // checks to see if any data was taken on the same date as the highest temperature anomaly
             //Writes to the output file. Gets the Average Sea Level Rise for the same date
            fWriter.write("On that same date, the Average Sea Level Rise was "+seaLevelTreeDate.get(tempTree.maxKeyValue()).key());
            fWriter.write("\n");
        }
        
        fWriter.write("\n");
        
        Date=seaLevelTree.minKeyValue().key().split("/"); // getting the individual numbers from the date
        //Writes to the output file. Gets the lowest key and writes the date in proper format
        fWriter.write("Lowest Sea Level Rise: "+seaLevelTree.min().key()+" on "+Months[Integer.parseInt(Date[0])-1]+" "+Date[1]+", "+Date[2]);
        fWriter.write("\n");
        if(tempTreeDate.get(seaLevelTree.minKeyValue())!=null){ // checks to see if any data was taken on the same date as the lowest sea level rise
            //Writes to the output file. Gets the temperature anomaly for the same date
            fWriter.write("On that same date, the Temperature Anomaly (F) was "+tempTreeDate.get(seaLevelTree.minKeyValue()).key());
            fWriter.write("\n");
        }
        
        if(co2TreeDate.get(seaLevelTree.minKeyValue())!=null){ // checks to see if any data was taken on the same date as the lowest sea level rise
            //Writes to the output file. Gets the average Co2 concentration for the same date
            fWriter.write("On that same date, the Average Co2 concentration was "+co2TreeDate.get(seaLevelTree.minKeyValue()).key());
            fWriter.write("\n");
        }
        
        fWriter.write("\n");
        
        Date=seaLevelTree.maxKeyValue().key().split("/"); // getting the individual numbers from the date
        //Writes to the output file. Gets the highest key and writes the date in proper format
        fWriter.write("Highest Sea Level Rise: "+seaLevelTree.max().key()+" on "+Months[Integer.parseInt(Date[0])-1]+" "+Date[1]+", "+Date[2]);
        fWriter.write("\n");
        if(tempTreeDate.get(seaLevelTree.maxKeyValue())!=null){ // checks to see if any data was taken on the same date as the highest sea level rise
            //Writes to the output file. Gets the temperature anomaly for the same date
            fWriter.write("On that same date, the Temperature Anomaly (F) was "+tempTreeDate.get(seaLevelTree.maxKeyValue()).key());
            fWriter.write("\n");
        }
        
        if(co2TreeDate.get(seaLevelTree.maxKeyValue())!=null){ // checks to see if any data was taken on the same date as the highest sea level rise
            //Writes to the output file. Gets the average Co2 concentration for the same date
            fWriter.write("On that same date, the Average Co2 concentration was "+co2TreeDate.get(seaLevelTree.maxKeyValue()).key());
            fWriter.write("\n");
        }
        
        fWriter.write("\n");
        
        Date=co2Tree.minKeyValue().key().split("/"); // getting the individual numbers from the date
        //Writes to the output file. Gets the lowest key and writes the date in proper format
        fWriter.write("Lowest Average Co2 concentration: "+co2Tree.min().key()+" on "+Months[Integer.parseInt(Date[0])-1]+" "+Date[1]+", "+Date[2]);
        fWriter.write("\n");
        if(tempTreeDate.get(co2Tree.minKeyValue())!=null){ // checks to see if any data was taken on the same date as the lowest co2 concentration
            //Writes to the output file. Gets the temperature anomaly for the same date
            fWriter.write("On that same date, the Temperature Anomaly (F) was "+tempTreeDate.get(co2Tree.minKeyValue()).key());
            fWriter.write("\n");
        }
        
        if(seaLevelTreeDate.get(co2Tree.minKeyValue())!=null){ // checks to see if any data was taken on the same date as the lowest co2 concentration
            //Writes to the output file. Gets the Average Sea Level Rise for the same date
            fWriter.write("On that same date, the Average Sea Level Rise was "+seaLevelTreeDate.get(co2Tree.minKeyValue()).key());
            fWriter.write("\n");
        }
        
        fWriter.write("\n");
        
        Date=co2Tree.maxKeyValue().key().split("/"); // getting the individual numbers from the date
        //Writes to the output file. Gets the largest key and writes the date in proper format
        fWriter.write("Highest Average Co2 concentration: "+co2Tree.max().key()+" on "+Months[Integer.parseInt(Date[0])-1]+" "+Date[1]+", "+Date[2]);
        fWriter.write("\n");
        if(tempTreeDate.get(co2Tree.maxKeyValue())!=null){ // checks to see if any data was taken on the same date as the highest co2 concentration
            //Writes to the output file. Gets the temperature anomaly for the same date
            fWriter.write("On that same date, the Temperature Anomaly (F) was "+tempTreeDate.get(co2Tree.maxKeyValue()).key());
            fWriter.write("\n");
        }
        
        if(seaLevelTreeDate.get(co2Tree.maxKeyValue())!=null){ // checks to see if any data was taken on the same date as the highest co2 concentration
            //Writes to the output file. Gets the Average Sea Level Rise for the same date
            fWriter.write("On that same date, the Average Sea Level Rise was "+seaLevelTreeDate.get(co2Tree.maxKeyValue()).key());
            fWriter.write("\n");
        }
        
        fWriter.write("\n");
        fWriter.close(); // closes the output file

    }
    
}
