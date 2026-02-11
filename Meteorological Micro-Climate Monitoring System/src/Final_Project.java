/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.final_project;

import java.util.Scanner;

/**
 *
 * @author syaza_szoqrgp
 */
public class Final_Project {
    //Group 7
    //Members:
    //SYAZA FATHIYAH BINTI FARDIN (311237)
    //NUR ANIS FARISYA BINTI MOHD FAIZOL (310794)
    //NUR SABRINA BINTI MOHD ARIFFIN (310648)
    
    //Amount of data that can be entered
    static int count;
    
    //Store data - Array
    static int[] stationID = new int[100];
    static double[] temperature = new double[100];
    static double [] humidity = new double[100];
    static double[] windSpeed = new double[100];
    static double[] rainFall = new double[100];
    static String[] location = new String[100];
    
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        
        
        int choice;
        do{
            printMainMenu();
            choice = input.nextInt();
            
            switch (choice){
                case 1:
                    addRecord(input);
                    break;
                case 2:
                    editRecord(input);
                    break;
                case 3:
                    deleteRecord(input);
                    break;
                case 4:
                    searchRecord(input);
                    break;
                case 5:
                    viewRecord(input);
                    break;
                case 6:
                    System.out.println ("Goodbye! Hope to see you again.");
                    break;
                default:
                    System.out.println ("Invalid input! Enter the number between 1 - 6");      
            }
        }while (choice != 6);
            
            input.close();
        }
    
    // ***** MAIN MENU ******
    public static void printMainMenu(){
        System.out.println("Welcome to Micro-Climate Monitoring System!");
        System.out.println ("1. ADD A RECORD");
        System.out.println ("2. EDIT A RECORD");
        System.out.println ("3. DELETE A RECORD");
        System.out.println ("4. SEARCH A RECORD");
        System.out.println ("5. VIEW A RECORD");
        System.out.println ("6. EXIT");
        System.out.print ("Choose an option: ");
    }
    
    // ***** ADD RECORD *****
    public static void addRecord(Scanner input){
   
        System.out.println ("MENU - ADD A RECORD");
        
        //If the element has exceed the amount it will return
        if (count >= 100){
            System.out.println ("WARNING! STORAGE FULL!");
            return;
        }
        
        System.out.print ("\nENTER THE STATION ID: ");
        int id = input.nextInt();
        input.nextLine();
        
        System.out.print ("\nENTER THE LOCATION NAME: ");
        String loc = input.nextLine();
        
        System.out.print ("\nENTER THE TEMPERATURE (°C): ");
        double temp = input.nextDouble();
        
        System.out.print ("\nENTER THE HUMIDITY (%): ");
        double humid = input.nextDouble();
        
        System.out.print ("\nENTER THE WIND SPEED (KM/H): ");
        double windSpd = input.nextDouble();
        
        System.out.print ("\nENTER THE RAINFALL (MM): ");
        double rainF = input.nextDouble();
        
        //Location of where the elements will be stored
        stationID[count] = id;
        location[count] = loc;
        temperature[count] = temp;
        humidity[count] = humid;
        windSpeed[count] = windSpd;
        rainFall[count] = rainF;
        
        count++;
        
        System.out.println ("Record has been added! Total record: " + (count) +".");
    }
    
    //***** EDIT RECORD *****
    public static void editRecord(Scanner input){
        System.out.println ("MENU - EDIT A RECORD");
        System.out.print ("\nEnter Station ID to edit: ");
        int idToEdit = input.nextInt();
        
        boolean edit = false;
        int idx = -1;
        
        
        //Finding matching station ID
        for(int i = 0; i < count; i++){
            if (stationID[i] == idToEdit){
                idx = i;
                edit = true;
                break;
            }
        }
        
        if (edit == false){
            System.out.println ("The Station ID " + idToEdit + " is not in the list.");
            return;
       }
        
        printRecord(idx);
        
        System.out.print ("Do you want to edit this record? [Y/N]: ");
        char ans = input.next().charAt(0);
        if (ans != 'y' && ans != 'Y'){
            System.out.println ("EDIT CANCELLED!");
            return;
        }
        
        System.out.println ("Which field do you want to edit?");
        System.out.println ("1. Station ID");
        System.out.println ("2. Location Name");
        System.out.println ("3. Temperature");
        System.out.println ("4. Humidity");
        System.out.println ("5. Wind Speed");
        System.out.println ("6. Rainfall");
        System.out.print ("Choose an option: ");
        int editChoice = input.nextInt();
        
        switch (editChoice){
            case 1:{
                System.out.print ("New Station ID: ");
                int newID = input.nextInt();
                
                //Check for similar ID
                boolean conflict = false;
                for (int i=0; i < count; i++){
                    if(stationID[i] == newID && i != idx){
                        conflict = true;
                        break;
                    }
                }
                if (conflict){
                    System.out.println (" WARNING! Station ID " + newID + " already exists.");
                } else{
                    stationID[idx] = newID;
                    System.out.println ("Station ID updated.");
                }
                break;
            }
        case 2: {
            System.out.println ("New Location Name: ");
            input.nextLine();
            String newLoc = input.nextLine();
            location[idx] = newLoc;
            System.out.println ("Location updated.");
            break;
        }
        case 3:{
            System.out.println ("New Temperature (°C): ");
            double newTemp = input.nextDouble();
            temperature[idx] = newTemp;
            System.out.println ("Temperature updated.");
            break;
        }
        case 4:{
            System.out.print ("New Humidity (%): ");
            double newHumid = input.nextDouble();
            humidity[idx] = newHumid;
            System.out.println ("Humidity updated.");
            break;
        }
        case 5:{
            System.out.print ("New Wind Speed (km/h): ");
            double newWind = input.nextDouble();
            windSpeed[idx] = newWind;
            System.out.println ("Wind Speed updated.");
            break;
        }
        case 6:{
            System.out.print ("New Rainfall (mm): ");
            double newRain = input.nextDouble();
            rainFall[idx] = newRain;
            System.out.println ("Rainfall updated.");
            break;
        }
        default:
            System.out.println ("Invalid field!");
    }
        printRecord(idx); //show updated record
    }        
            
    //**DELETE A RECORD**
    public static void deleteRecord(Scanner input){
        
        System.out.println("MENU - DELETE A RECORD");
        System.out.println("Enter Station ID to delete: ");
        int idToDelete = input.nextInt();
        
        int idx = -1;
        //FIND STATION ID INDEX
        for (int i = 0; i < count; i++) {
            if (stationID[i] == idToDelete) {
                idx = i;
                break;
            }
        }
        
        //IF NOT FOUND
        if (idx == -1) {
            System.out.println("Station ID " + idToDelete + "not found.");
            return;
        }
        
        //DISPLAY THE RECORD BEFORE DELETE
        printRecord(idx);
        System.out.print("Are you sure you want to delete this record? (Y/N): ");
        char confirm = input.next().charAt(0);
        
        if (confirm != 'Y' && confirm != 'y') {
            System.out.println("DELETE CANCELLED!");
            return;
        }
        
        for (int i = idx; i < count - 1; i++) {
            stationID[i] = stationID[i + 1];
            location[i] = location[i + 1];
            temperature[i] = temperature[i + 1];
            humidity[i] = humidity[i + 1];
            windSpeed[i] = windSpeed[i + 1];
            rainFall[i] = rainFall[i + 1];
        }
        
        count--;
        
        System.out.println("Record deleted successfully!");
            
        }
    
    public static void viewRecord(Scanner input){
        if (count == 0){
            System.out.println ("No records to view!");
            return;
        }
        
        int i = count - 1;
        System.out.println ("***********************************");
        System.out.println("Station ID: " + stationID[i]);
        System.out.println ("Location: " + location[i]);
        System.out.println ("Temperature: " + temperature[i] + "°C");
        System.out.println ("Humidity: " + humidity[i] + "%");
        System.out.println ("Wind Speed: " + windSpeed[i] + "km/h");
        System.out.println ("Rainfall: " + rainFall[i] + "mm");
        System.out.println ("***********************************");
        
        double heatIndex = (temperature[i] + (0.33 * humidity[i]) - 0.70);
        System.out.printf ("Heat Index: %.2f \n", heatIndex);
        
    }
    
    public static void searchRecord (Scanner input){
         System.out.print("Enter a Station Id to search for: ");
         int stationIDtarget = input.nextInt();
         boolean isFound = false;
         
         for (int i= 0; i < stationID.length; i++)
         
             if (stationIDtarget == stationID[i])
             {
                 System.out.println("You search for " + stationID[i] + ", it is in index " + i);
                 printRecord(i); 
                 isFound = true;
                 break;
             }
         if (!isFound)
             System.out.println("Station ID not found");
         }
    public static void printRecord(int i){
        System.out.println ("***************************************");
        System.out.println("Station ID: " + stationID[i]);
        System.out.println ("Location: " + location[i]);
        System.out.println ("Temperature: " + temperature[i] + "°C");
        System.out.println ("Humidity: " + humidity[i] + "%");
        System.out.println ("Wind Speed: " + windSpeed[i] + "km/h");
        System.out.println ("Rainfall: " + rainFall[i] + "mm");
        System.out.println ("***************************************");
    }

}
