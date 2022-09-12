/***************************************************************************************************
 * Group Work Assignment 2 - A2-Group 29
 * File: ItemBagUtils.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

 package assignment2;

 import java.util.ArrayList;
 
 public class ItemBagUtils {
 
     /** This method uses Binary search to find the index of the item in the list.
      * Therefore, the time complexity is O(log(n)), therefore being more efficient than
      * a standard linear search, O(n).
      * 
      * @param item
      * @return int
      */
     public static int getIndexFrom(Item item, ArrayList<Item> items)
     {
         /* low, high represent the indices of the first and the last elements in the list;
          * this is the initial state of the binary search algorithm. */
 
         int low = 0; 
         int high = items.size() - 1;
 
         int foundIndex = 0;
         boolean foundItem = false; // this is the flag that indicates if the item was found or not.
 
         while (low <= high && !foundItem) 
         {
             int mid = (low + high) / 2; 
             
             // if the middle element is the item we are looking for
             if (items.get(mid).getWeight() == item.getWeight()) 
             {
                 foundIndex = mid;
                 foundItem = true;
             } 
 
             /* if the item is greater (in weight) than the middle item; 
              * in the else-if and else statements, we partition the list 
              * per the binary search algorithm */
 
             else if (item.getWeight() > items.get(mid).getWeight()) 
             {
                 high = mid - 1;
                 foundIndex = mid;
 
             } else {
                 low = mid + 1;
                 foundIndex = mid + 1;
             }
         }
 
         return foundIndex;
     }
 
     /* space for new methods to be implemented that are specific to the ItemBag class ... */
 }