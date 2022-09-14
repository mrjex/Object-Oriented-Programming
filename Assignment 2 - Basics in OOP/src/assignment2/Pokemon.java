/***************************************************************************************************
 * Group Work Assignment 2 - A2-Group 29
 * File: Pokemon.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

 package assignment2;

 public class Pokemon 
 {
     // class constants
     final byte MAX_ENERGY = 100;
     final byte ENERGY_RECOVER = 25;
     final byte HEALTH_RECOVER = 20;
 
     // class attributes
     private String name;
     private final int MAX_HP;
 
     private int currentHP;
     private int energyPoints;
     
     private final String type;
     private PokemonSkill skill; 
 
     // custom class attributes
     private boolean hasFainted;
 
     /* The name, MAX_HP and type must be specified. For all tasks below we 
      * can assume that the constructor receives only valid values */
 
     public Pokemon(String name, int maximumHP, String type)
     {
         this.name = name;
 
         this.MAX_HP = maximumHP;
         this.currentHP = this.MAX_HP;       // after creation, the current is the maximum
 
         this.energyPoints = MAX_ENERGY;     // we may assume that the energy is always max. 100
         
         this.skill = null;                  // the Pokemon doesn't know any skill yet
         this.type = type;
 
         this.hasFainted = false;
     }
  
     /** 
      * @return int
      */
     // getters
     public int getEnergy() 
     {
         return this.energyPoints;
     }
   
     /** 
      * @return int
      */
     public int getCurrentHP() 
     {
         return this.currentHP;
     }
   
     /** 
      * @return int
      */
     public int getMAX_HP() 
     {
         return this.MAX_HP;
     }
  
     /** 
      * @return String
      */
     public String getName() 
     {
         return this.name;
     }
  
     /** 
      * @return String
      */
     public String getType() 
     {
         return this.type;
     }
 
     /** 
      * @param newName
      */
     // Setters
 
     public void setName(String newName)
     {
         this.name = newName;
     }
 
     /** 
      * @param name
      * @param attackPower
      * @param energyCost
      */
     public void learnSkill(String name, int attackPower, int energyCost) 
     {
         this.skill = new PokemonSkill(name, attackPower, energyCost);
     }
 
     /** 
      * @return boolean
      */
     public boolean knowsSkill()
     {
         return (this.skill != null);
     }
 
     public void forgetSkill()
     {
         this.skill = null;
     }
 
     /** 
      * @param damage
      */
     public void receiveDamage(int damage)
     {
         this.currentHP -= damage;
 
         if (this.currentHP <= 0) 
         {
             this.currentHP = 0;
             if (!this.hasFainted) 
             {
                 faint();
             }
         }
     }
 
     public void rest()
     {
         if (!hasFainted) 
         {
             this.currentHP += this.HEALTH_RECOVER;
         }
 
         // if the current HP is greater than the max HP, set it to the max HP
         if (PokemonUtils.exceedingBound(this.currentHP, this.MAX_HP)) 
         {
             this.currentHP = this.MAX_HP;
         }
     }
 
     void faint() 
     {
         this.hasFainted = true;
     }
     
     /** 
      * @param energyCost
      */
     void useEnergy(int energyCost)
     {
         /* we use -1 here, so that we may use a skill if the energy is exactly the same as the cost */
         if (PokemonUtils.exceedingBound(this.energyPoints, energyCost - 1))
         {
             this.energyPoints -= energyCost;
         }     
     }
 
     public void recoverEnergy()
     {
         if (!hasFainted)
         {
             this.energyPoints += this.ENERGY_RECOVER;
         }
 
         // if the current energy is greater than the max energy, set it to the max energy
         if (PokemonUtils.exceedingBound(this.energyPoints, this.MAX_ENERGY))
         {
             this.energyPoints = this.MAX_ENERGY;
         }
     }
     
     /** 
      * @param item
      * @return String
      */
     public String useItem(Item item)
     {
         int amountOfHealthHealed;
 
         // 1st case - the HP is maximum
         if (this.currentHP == this.MAX_HP) 
         {
             return String.format("%s could not use %s. HP is already full.", 
                                 this.name, item.getName());
         }
 
         // 2nd case - the increase will exceed the maximum HP
         else if (PokemonUtils.exceedingBound(this.currentHP + item.getHealingPower(), this.MAX_HP))
         {
             this.currentHP = this.MAX_HP;
             amountOfHealthHealed = this.MAX_HP - item.getHealingPower();   
         }
 
         // 3rd case - the general increase of health
         else
         {
             // check if the Pokemon has fainted
             if (hasFainted) 
             {
                 hasFainted = false;
             }
 
             amountOfHealthHealed = item.getHealingPower();
             this.currentHP += amountOfHealthHealed;
         }
 
         return String.format("%s used %s. It healed %d HP.", 
                             this.name, item.getName(), amountOfHealthHealed);
     }
 
     /** 
      * @param targetPokemon
      * @return String
      */
     public String attack(Pokemon targetPokemon) 
     {
         // 1. test case
         if (this.skill == null)
         {
             return String.format("Attack failed. %s does not know a skill.", this.name);
         }
 
         // 2. test case
         else if (this.hasFainted)
         {
             return String.format("Attack failed. %s fainted.", this.name);
         }
 
         // 3. test case
         else if (targetPokemon.hasFainted)
         {
             return String.format("Attack failed. %s fainted.", targetPokemon.name);
         }
 
         // 4. test case
         else if (this.energyPoints < this.skill.getEnergyCost())
         {
             return String.format("Attack failed. %s lacks energy: %d/%d",
                                 this.name, this.energyPoints, this.skill.getEnergyCost());
         }
 
         // 5. detect a successful attack 
         else
         {
             // initial attack message (String)
             String attackMessage = String.format("%s uses %s on %s.", 
                                     this.name, this.skill.getName(), targetPokemon.getName());
 
             // compute the effectiveness of the attack and the caused damage (int)
             float effectiveness = PokemonUtils.computeEffectiveness(this.type, targetPokemon.getType());
             int damage = PokemonUtils.calculateAttackDamage(this.skill.getAttackPower(), effectiveness);
 
             // If it is not very effective
             if (effectiveness == 0.5)
             {
                 attackMessage += PokemonUtils.ADDITIONAL_ATTACK_MESSAGE[0];
             }
             // Else it's very effective
             else if (effectiveness == 2) 
             {
                 attackMessage += PokemonUtils.ADDITIONAL_ATTACK_MESSAGE[1];
             }
 
             // receive damage and let the attacker lose energy
             targetPokemon.receiveDamage(damage);
             this.useEnergy(this.skill.getEnergyCost());
 
             attackMessage += PokemonUtils.remainingHP(targetPokemon);
             
             // check if the target Pokemon has fainted
             if (targetPokemon.getCurrentHP() <= 0) 
             {
                 attackMessage += PokemonUtils.assignFaint(targetPokemon.getName());
             }
 
             return attackMessage;
         }
     }
 
     /** Compare if two Pokemons are the same:
      * Two pokemons are equal if they have the same name, type, skill, HP, MAX HP, EP
      * @param anotherObject
      * @return boolean
      */
     public boolean equals(Object anotherObject)
     { 
         if (anotherObject == null) 
         {
             return false;
         }
 
         if (anotherObject instanceof Pokemon) 
         {
             Pokemon anotherPokemon = (Pokemon) anotherObject;
 
             boolean equalName   = this.name.equals(anotherPokemon.name);
             boolean equalType   = this.type.equals(anotherPokemon.type);
             boolean equalHP     = this.currentHP == anotherPokemon.currentHP;
             boolean equalMAX_HP = this.MAX_HP == anotherPokemon.MAX_HP;
 
             boolean noSkillOrEqualSkill = (this.skill == null && anotherPokemon.skill == null) || 
                                           (this.skill != null && this.skill.equals(anotherPokemon.skill));
                                           
             boolean equalEnergy = this.energyPoints == anotherPokemon.energyPoints;
 
             return equalName && equalType && equalHP && equalMAX_HP && noSkillOrEqualSkill && equalEnergy;
         }
         
         return false;
     }
     
     /** Print the Pokemon object according to the specifications
      * @return String
      */
 
     public String toString()
     {
         // If skill is empty
         if (this.skill == null)
         {
             return String.format("%s (%s)", name, type);
         }
         else 
         {
             // If it has learned a skill
             return String.format("%s (%s). Knows %s - AP: %d EC: %d",
                 name, type, skill.getName(), this.skill.getAttackPower(), this.skill.getEnergyCost());
         }
     }
 }
 