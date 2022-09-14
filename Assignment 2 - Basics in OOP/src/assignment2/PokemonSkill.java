/***************************************************************************************************
 * Group Work Assignment 2 - A2-Group 29
 * File: Pokemon.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

 package assignment2;

 public class PokemonSkill
 {
     private final String name;
     private final int attackPower;
     private final int energyCost;
 
     public PokemonSkill(String name, int AP, int EC)
     {
         this.name = name;
         this.attackPower = AP;
         this.energyCost = EC;
     }
 
     // *** getters ***
     
     /** 
      * @return String
      */
     public String getName() 
     {
         return this.name;
     }
 
     /** 
      * @return int
      */
     public int getAttackPower() 
     {
         return this.attackPower;
     }
 
     /** 
      * @return int
      */
     public int getEnergyCost() 
     {
         return this.energyCost;
     }
     
     /** Compare if two Pokemon Skills are the same:
      * Two skills are equal if they have the same names, APs and energy costs.
      * @param anotherObject
      * @return boolean
      */
     public boolean equals(Object anotherObject) 
     {    
         if (anotherObject == null) 
         {
             return false;
         }
         if (anotherObject instanceof PokemonSkill) 
         {
             // cast the Object to the desired type
             PokemonSkill anotherPokemon = (PokemonSkill) anotherObject;
 
             boolean checkName = this.name.equals(anotherPokemon.name);
             boolean checkAttackPower = this.attackPower == anotherPokemon.attackPower;
             boolean checkEnergyCost = this.energyCost == anotherPokemon.energyCost;
 
             return checkName && checkAttackPower && checkEnergyCost;
         }
 
         return false;
     }
     
     /** Output specified in the instructions
      * @return String
      */
     public String toString() 
     {
         return String.format("%s - AP: %d EC: %d", 
                             name, attackPower, energyCost);
     }
 }