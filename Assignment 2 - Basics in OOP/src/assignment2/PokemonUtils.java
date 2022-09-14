/***************************************************************************************************
 * Group Work Assignment 2 - A2-Group 29
 * File: PokemonUtils.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

 package assignment2;

 import java.lang.Math;
 
 public class PokemonUtils
 {
     /* Matrix Representation of the strengths and weaknesses of every specified Pokemon Type 
      * The 8-th row and column represent the 'Normal' type, which is not weak or strong against any other type */
     
     public static final float[][] DAMAGE_MULTIPLIER_MATRIX = {
         { 1,    1,      1,      .5f,    2,      1,      1,      1 },
         { 1,    2,      1,      1,      1,      1,      1,      1 },
         { 1,    .5f,    .5f,    1,      .5f,    1,      2,      1 },
         { 2,    .5f,    1,      .5f,    2,      2,      .5f,    1 },
         { .5f,  .5f,    1,      .5f,    .5f,    1,      2,      1 },
         { 1,    2,      1,      .5f,    2,      .5f,    .5f,    1 },
         { 1,    .5f,    1,      2,      .5f,    1,      .5f,    1 },
         { 1,    1,      1,      1,      1,      1,      1,      1 },
     };
 
     /* These are the possible additional messages for effective/ineffective attacks */
 
     public static final String[] ADDITIONAL_ATTACK_MESSAGE = {
         " It is not very effective...",
         " It is super effective!"
     };
 
     /* make public for reusability in other Classes */
     
     public final static String END_OF_LINE = System.lineSeparator();
     
     /** Return the correct multiplier for the attack
      * @param attackerType
      * @param targetType
      * @return float
      */
     public static float getEffectiveValue(int attackerIndex, int targetIndex)
     {
         return DAMAGE_MULTIPLIER_MATRIX[attackerIndex][targetIndex];
     }
 
     /** 
      * @param value
      * @param decimalPlaces
      * @return double
      */
     public static double truncateDouble(double value, int decimalPlaces) 
     {
         double powerOfTen = Math.pow(10, decimalPlaces);
         return Math.floor(value * powerOfTen) / powerOfTen;
     }
 
     /** 
      * @param value
      * @param min
      * @param max
      * @return boolean
      */
     public static boolean isWithinBounds(int value, int min, int max) 
     {
         return (value >= min && value <= max);
     }
     
     /** 
      * @param addedValue
      * @param boundary
      * @return boolean
      */
     public static boolean exceedingBound(int addedValue, int boundary)
     {
         return addedValue > boundary;
     }
 
     /**
      * @param targetPokemon
      * @return String
      */
     public static String remainingHP(Pokemon targetPokemon) {
         return String.format("%s%s has %d HP left.",
                 END_OF_LINE,
                 targetPokemon.getName(),
                 targetPokemon.getCurrentHP());
     }
     
     /**
      * @param name
      * @return String
      */
     public static String assignFaint(String name) {
         return String.format(" %s faints.", name);
     }
 
     /**
      * @param targetPokemon
      * @return float
      */
     public static float computeEffectiveness(String attacker, String target) {
         PokemonTypes attackerType = PokemonTypes.valueOf(attacker.toUpperCase());
         PokemonTypes targetType = PokemonTypes.valueOf(target.toUpperCase());
 
         return PokemonUtils.getEffectiveValue(attackerType.mapValue, targetType.mapValue);
     }
 
     /**
      * @param effectiveness
      * @return int
      */
     public static int calculateAttackDamage(int skillAttackPower, float effectiveness) {
         int damage = (int) (skillAttackPower * effectiveness);
 
         return damage;
     }
 }