/***************************************************************************************************
 * Group Work Assignment 1 - A1-Group 28
 * File: IOScanner.java
 * Members: Ionel Pop, Joel Mattson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

 import java.util.Scanner;

 public class IOScanner 
 {
     static Scanner input;
     
     public void initializeScanner()
     {
         input = new Scanner(System.in);
     }
 
     public void closeScanner() 
     {
         input.close();
     }
     
     public void cleanBuffer()
     {
         input.nextLine();
     }
     
     /** 
      * @param message message to be displayed to the user
      * @return int
      */
     public int readInt(String message) 
     {
         System.out.print(message);
         return input.nextInt();        
     }
     
     /**
      * @param fullStr boolean value to determine if the user wants to enter a full string or not
      * @param message message to be displayed to the user
      * @return String
      */
     public String readStr(boolean fullStr, String message) 
     {
         System.out.print(message);
         if (fullStr) 
         {
             return input.nextLine();
         }
         return input.next();
     }
     
     /**
      * @param separator the separator to be used to split the string
      * @param message message to be displayed to the user
      * @return String[]
      */
     public String[] readFullStrSplit(String separator, String message)
     {
         System.out.print(message);
         return input.nextLine().split(separator);
     }
 }
 