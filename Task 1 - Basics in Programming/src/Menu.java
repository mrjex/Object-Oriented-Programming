/***************************************************************************************************
 * Group Work Assignment 1 - A1-Group 28
 * File: Menu.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

 import java.util.Locale;
 import java.util.Arrays;
 
 public class Menu 
 {
     static IOScanner input = new IOScanner();
     static int[] studentScores = new int[7];
 
     // constant values
     static final int NUMBER_OF_SCORES           = 7;
     static final int[] INTERVAL                 = { 0, 100 };
     static final String[] ORDINAL_INDICATORS    = { "st", "nd", "rd", "th" };
 
     public static void main(String[] args)
     {
         Locale.setDefault(Locale.ENGLISH);  // a decimal place is indicated with a dot
         input.initializeScanner();
         
         // initial read of the scores
         readScores();                       
         System.out.println("Thank you for your input. Your entered scores are:\n" 
                             + joinedStudentScores());
         
         int option;
         do {
             displayMenu();
             option = input.readInt("Type your option: ");
             
             switch (option) 
             {
                 case 1: 
                 {
                     readScores(); 
                     System.out.println("Thank you for your input. Your entered scores are:\n" 
                                         + joinedStudentScores());
                     break;
                 }
                 case 2: 
                 {                    
                     System.out.printf("The mean of the numbers is %.02f\n", calculateMean());
                     break;
                 } 
                 case 3: 
                 {
                     int[][] extremaScores = twoExtremaOfScores();
                     printExtrema(extremaScores);
                     break;
                 } 
                 case 4: 
                 {
                     int[] highestScoreWithIndex = getHighestScoreWithIndex();
                     printHighestScoreWithIndex(highestScoreWithIndex);
                     break;
                 }
                 case 5: 
                 {
                     collectHashtags();
                     break;
                 }
 
                 case 6: 
                 {
                     System.out.println("Thank you for using our grading system. Have a nice day!");
                     break;
                 }
                 
                 default: 
                 {
                     System.out.println("Error - Invalid value. Please type between 1 and 6");
                     break;
                 }
             }
 
         } while (option != 6);
         
         input.closeScanner();
     }
     
     // -----------------------------------------Subroutines-------------------------------------------//
 
     public static void displayMenu() {
         System.out.println("Welcome to the menu. Choose one of the options below: ");
         System.out.println("1. Register new scores for students.");
         System.out.println("2. Calculate the mean of the entered scores.");
         System.out.println("3. Find the two highest and two lowest scores.");
         System.out.println("4. Find the highest score and its position.");
         System.out.println("5. Collect hashtags from a post.");
         System.out.println("6. To exit.");
     }
 
     //-----------------------------------------Task 1-------------------------------------------//
 
     /* Analysis:
      * We use the static array studentScores[] to store the scores entered by the user.
      * Then we enter a loop until all the scores are entered.
      * We use a ternary operator to determine the correct ordinal indicator and
      * we make sure that the scores are within a valid interval.
      * Last accessed: 09-13-2022 */
 
     public static void readScores() 
     {     
         int tempScore;                              // temporary variable to store the user input
         String currentOrdinalIndicator;             // store the current ordinal indicator
 
         int studentCounter = 0;                     // variable to keep track of the number of students (default value is 0)
         while (studentCounter < NUMBER_OF_SCORES) 
         {
             currentOrdinalIndicator = (studentCounter < 3) ? ORDINAL_INDICATORS[studentCounter] : ORDINAL_INDICATORS[3];
 
             tempScore = input.readInt("Enter the score for the " + (studentCounter + 1) + currentOrdinalIndicator + " student ");
 
             if (isWithinInterval(tempScore)) 
             {
                 studentScores[studentCounter++] = tempScore;
 
             } else 
             {
                 // warn the user with a message
                 System.out.printf("Error - Input out of bound. Score can only be between %d  and %d.\n", 
                                     INTERVAL[0], INTERVAL[1]);
             }
         }
     }
     
     /** 
      * @param value value input by the user
      * @return boolean value to determine whether the value belongs to the interval or not
      */
     public static boolean isWithinInterval(int value)  
     {
         if (value >= INTERVAL[0] && value <= INTERVAL[1]) return true; 
         return false;
     }
  
     /** 
      * We use the String join() method to concatenate the scores
      * and print them with the correct format (per the instructions).
      * @return String
      */
     public static String joinedStudentScores() 
     {
         String[] tempArray = new String[studentScores.length];
         for (int i = 0; i < studentScores.length; i++) 
         {
           tempArray[i] = String.valueOf(studentScores[i]);
         }
         return String.join(", ", tempArray);
     }
     
     //-----------------------------------------Task 2-------------------------------------------//
     
     /** 
      * Iterate over the studentScores array to get the sum of them.
      * Then we divide them by the number of students to calculate the mean.
      * @return double
      */
     public static double calculateMean()
     {   
         int scoreSum = 0;
         for (int i = 0; i < studentScores.length; i++) 
         { 
             scoreSum += studentScores[i];
         }
         return (double) scoreSum / NUMBER_OF_SCORES;
     } 
 
     //-----------------------------------------Task 3-------------------------------------------//    
     
     /** 
      * Here, we obtain the two highest and two lowest scores of the studentScores array.
      * We initialize 4 variables to store the values of the extrema. The default values
      * are the bounds of the interval. Then, we iterate over the array and update the
      * variables accordingly: if the current value is higher than the first highest value,
      * we swap the second highest value with the first highest value and then we update
      * the first highest value. Otherwise, we check if the current value is higher than the
      * second highest value and update it accordingly. The same logic applies to the lowest values.
      * Lastly, we return a two-dimensional array with the extrema values per the format:
      * {{highest1, highest2}, {lowest1, lowest2}}.
      * @return int[][]
      */
     public static int[][] twoExtremaOfScores() 
     {
         int highestScore = INTERVAL[0], secondHighestScore = INTERVAL[0];
         int lowestScore = INTERVAL[1], secondLowestScore = INTERVAL[1];
 
         for (int i = 0; i < studentScores.length; i++)
         {
             if (studentScores[i] > highestScore)
             {
                 secondHighestScore = highestScore;
                 highestScore = studentScores[i];                
             }
             else if (studentScores[i] > secondHighestScore)
             {
                 secondHighestScore = studentScores[i];
             }
 
             if (studentScores[i] < lowestScore)
             {
                 secondLowestScore = lowestScore;
                 lowestScore = studentScores[i];
             }
             else if (studentScores[i] < secondLowestScore)
             {
                 secondLowestScore = studentScores[i];
             }
         }
 
         return new int[][] { { highestScore, secondHighestScore }, { lowestScore, secondLowestScore } };
     }
 
     /** 
      * We accept a two-dimensional array as a parameter and print the extrema values
      * per the instructions.
      * @param extremaScores
      */
     public static void printExtrema(int[][] extremaScores) 
     {
         int[] highestScores = extremaScores[0];
         int[] lowestScores = extremaScores[1];
 
         System.out.println("The two lowest scores provided are " 
                         + lowestScores[0] + ", and " + lowestScores[1]);
 
         System.out.println("The two highest scores provided are " 
                         + highestScores[0] + ", and " + highestScores[1]);
     }
         
     //-----------------------------------------Task 4-------------------------------------------//
         
     /** 
      * In this method, we determine the highest score and its index. For that, we use an integer array,
      * that simultaneously stores the index and the value of the highest score. We initialize the array
      * with the first value of the studentScores array. Then, we iterate over the array and update the
      * array accordingly: if the current value is higher than the highest value, we update the array
      * with the current value and its index.
      * @return int[] 
      */
     public static int[] getHighestScoreWithIndex()
     {        
         int[] highestValueStruct = { 0, studentScores[0] }; // [value, index]
 
         for (int i = 1; i < NUMBER_OF_SCORES; i++)
         {
             if (studentScores[i] > highestValueStruct[1]) 
             {
                 highestValueStruct[0] = i;
                 highestValueStruct[1] = studentScores[i];
             }
         }
         return highestValueStruct;
     }
     
     /** 
      * We use a ternary operator to determine the correct ordinal indicator
      * based on the values stored in the highestValueStruct. Print the student's
      * number (with the valid ordinal indicator) and the highest score per the instructions.
      * @param highestValueStruct
      */
     public static void printHighestScoreWithIndex(int[] highestValueStruct)
     {
         int highestIdx = highestValueStruct[0];
 
         String ordinalIndicator = (highestIdx < 3) ? ORDINAL_INDICATORS[highestIdx] : ORDINAL_INDICATORS[3];
         
         System.out.printf("The highest score is %d and belongs to the %d%s student",
                             highestValueStruct[1], highestIdx + 1, ordinalIndicator);
         System.out.println(); // add new line
     }
 
     //-----------------------------------------Task 5-------------------------------------------//
 
     public static void collectHashtags()
     {
         input.cleanBuffer();
         String[] post = input.readFullStrSplit(" ", "Type your post: ");
 
         /* Analysis:
          * If we suppose a post of size n, then there may be at most n hashtags;
          * Then, we initialize an array of size n, i.e, the length of the post.
          * The initial value assigned to each cell of the array is a type of null. 
          * Last accessed: 09-09-2022 */
 
         int hashtagCount = 0;
         String[] foundHashtags = new String[post.length];
 
         /* In real life scenario, a hashtag which is only the '#' character is not valid.
          * Therefore, we check if the first character of the string is a '#'. If it is,
          * we check if the string is longer than 1 character. If it is, we add the 
          * string to the foundHashtags array. Otherwise, we ignore it. 
          * if (post[i].startsWith("#") && post[i].length() > 1) {...} 
          * Last accessed: 15-09-2022 */
 
         for (int i = 0; i < post.length; i++) {
             if (post[i].startsWith("#")) {          // check if the string begins with the '#' character
                 foundHashtags[hashtagCount++] = post[i];    // this way, we also preserve the order of the hashtag
             }
         }
 
         /* Analysis:
          * For the Arrays.copyOfRange() method, documentation from oracle.com:
          * https://docs.oracle.com/javase/6/docs/api/java/util/Arrays.html
          * By doing so, we remove all the null values from the array, i.e., create a
          * subset of the array.
          * Last accessed: 09-09-2022 */
 
         String[] foundHashtagsSubset = Arrays.copyOfRange(foundHashtags, 0, hashtagCount);
 
         // check for the case when no hashtags are found, otherwise join all the hashtags per the instructions
         if (hashtagCount == 0) {
             System.out.println("No hashtags were typed.");
         } else {
             System.out.println("Hashtags found: " + String.join(" ", foundHashtagsSubset));
         }
     }
 
     // -----------------------------------------Subroutines-------------------------------------------//
 }
 