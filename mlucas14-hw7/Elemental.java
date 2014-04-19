/************************************************************************
 * Program to represent Elementals in the Homework 7 driver class. 
 * Java 600.107
 * @author Morgan Lucas
 * @since 2014-04-11
 *********************************************************************/
 


/******************************************************************************
 * Following code imported from prof. Ben Mitchell's solution to Assignment 5
 * (with changes)
 *****************************************************************************/
 
 
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;

public class Elemental {

  private static Random rng = new Random(); 
  private static DecimalFormat df = new DecimalFormat("#.##");

  private String name;
  private int attack = rng.nextInt(10)+1;
  private int defense = rng.nextInt(10)+1;
  private int level = rng.nextInt(20)+1;
  private int health = level * 5;
  private double base = rng.nextDouble() * 10.0;
  private double base2 = Double.parseDouble(df.format(base));
  private String[] typearray = {"Fire", "Air", "Earth", "Water"};
  private int typenumber = rng.nextInt(typearray.length);
  private String type = (typearray[typenumber]);
  


  /****************************
   * Constructs an Elemental with the given statistics.&nbsp;You 
   * can assume that all the parameters are valid for this assignment.
   * @param name The name of the Elemental
   * @param level The level of the Elemental
   * @param health The initial health (HP)
   * @param attack The attack stat
   * @param defense The defense stat
   * @param type The Elemental type (Earth, Air, Fire, Water)
   ***************************/
   
  public Elemental(String name, int level, int health, int attack, int defense, String type) {
    this.name = name;
    this.name = Search.capitalize(this.name);
    this.level = level;
    this.health = health;
    this.attack = attack;
    this.defense = defense;
    this.type = type;
  }

  /****************************
   * Constructs an Elemental with just a name, level, and type; the
   * remaining values should be set to a default value of five (5).
   * @param name The name of the Elemental
   * @param level The level of the Elemetal 
   * @param type The Elemental type (Earth, Air, Fire, Water)
   ***************************/
  
   public Elemental(String name, int level, String type) {
    this(name, level, 5, 5, 5, type);
   }
   
    
   
     /****************************
   * Constructs an Elemental with the given statistics.&nbsp;You 
   * can assume that all the parameters are valid for this assignment.
   * @param name The name of the Elemental
   * @param level The level of the Elemental multiplied by 5
   * @param health The initial health (HP)
   * @param attack The attack stat
   * @param defense The defense stat
   * @param type The Elemental type (Earth, Air, Fire, Water)
   ***************************/ 
   
   public Elemental(String name, int level, int attack, int defense, String type) {
    this.name = name;
    this.name = Search.capitalize(this.name);
    this.level = level;
    this.health = level*5;
    this.attack = attack;
    this.defense = defense;
    this.type = type;
  }
  
  /****************************
   * Returns a string representation of the Elemental, which should
   * include only the name and level.
   * @return a string of the form "name, level)"
   ***************************/
  public String toString() {
    return  Search.capitalize(name + ", level " + level);
  }
 
  /****************************
   * Returns an ASCII-art picture of the Elemental's type. 
   * Use the Elemental's type to decide which picture to return.
   * @return a multi-line string encoding an ascii-art icon
   ***************************/
  public String iconString() {
    
    if (this.type.equalsIgnoreCase("fire"))
   {
   
   System.out.println("    (");  
   System.out.println("  .) )");
   System.out.println(" '(,' (,");
   System.out.println(" ). (, ('");
   System.out.println("( ) ; ' )");
   System.out.println("')_,_)_('");
  }
   
   
   // WATER
   else if (this.type.equalsIgnoreCase("water"))
   {
   System.out.println("\n\n");
   System.out.println(" \\ | /");
   System.out.println("  \\|/ ");
   System.out.println("---+---");
   System.out.println("  /|\\  ");
   System.out.println(" / | \\ ");
   }
  
   // AIR
   else if (this.type.equalsIgnoreCase("air"))
   {
   System.out.println("\n\n");
   System.out.println("   _   _");
   System.out.println(" _/_\\_/_\\_/");
   System.out.println(" _/_\\_/_\\_/");
   System.out.println(" _/_\\_/_\\_/  ");
   System.out.println(" _/ \\_/ \\_/  ");
   }
   
   // EARTH
   else
   {
   System.out.println("\n\n");
   System.out.println("  ___");
   System.out.println(" /   \\");
   System.out.println("/     \\");
   System.out.println("|     |");
   System.out.println("\\     /");
   System.out.println(" \\___/");
   }
   
   return "Yay!";
  }
  
  /****************************
   * Tests whether two Elementals are equal based solely on their names
   * (case insensitive) and their levels.
   * @param other, The Elemental to compare to
   * @return True if the names and levels are the same, false otherwise
   ***************************/
  public boolean equals(Elemental other) {
    return this.name.equalsIgnoreCase(other.name) &&
           this.level == other.level;
  }
  
  
  /****************************
   * Set the name of the elemental.
   * @return void 
   ***************************/
  public void setName(String newname) {
    this.name = newname;
    this.name = Search.capitalize(this.name);
  }
  
  /****************************
   * Set the current health of the elemental.
   * @return void
   ***************************/
  public void setHealth(int health) {
    this.health = health;
  }

  /****************************
   * Get the attack statistic of this elemental
   * @return The attack value
   ***************************/
  public int getAttack() {
    return attack;
  }

  /****************************
   * Get the defense statistic of this elemental
   * @return The defense value
   ***************************/
  public int getDefense() {
    return defense;
  }

  /****************************
   * Get the current health of this elemental
   * @return The health value
   ***************************/
  public int getHealth() {
    return health;
  }

  /****************************
   * Get the level of this elemental
   * @return The level value
   ***************************/
  public int getLevel() {
    return level;
  }

  /****************************
   * Get the type of this elemental
   * @return The type 
   ***************************/
  public String getType() {
    return type;
  }

  /****************************
   * Get the name of this elemental
   * @return The name string
   ***************************/
  public String getName() {
    return  Search.capitalize(name);
  }
  
   
/**
   Method to generate values of 1.0 occuring with 80% probability and 2.0 
   occuring with 20% probability. 
   @return the value generated, 1.0 or 2.0
*/
   public double randCritical()
   {
      Random ran = new Random();
         double num = ran.nextDouble();
         if(num < 0.2) 
         return 1;  
         else 
         return 2; 

   }

  /****************************
   * Method to perform a battle between two Elementals.  The
   * attack is specified by its base damage, as well as the critical, type, and
   * random damage modifiers. The other necessary stats (attack, defense,
   * level) are instance fields of the attacking Elemental.
   * @param target The defending Elemental
   * @param base The base damage of the attack [1,10]
   * @param critical The critical-damage modifier (1.0 for non-critical, 2.0 for critical)
   * @param typeModifier The type damage modifier, based on the relative strength of the Elemental types of the attacker and defender. [0.5-2.0]
   * @param random The random damage modifier [0.75,1.50]
   * @return The damage done (as a rounded integer)
   ***************************/ 
  public int attack(Elemental target, double base, double critical, double typeModifier, double random) {
    double modifier = typeModifier * critical * random;
    double damageDouble = (((level + 5) / 10.0) * ((double)attack / target.getDefense())) * base * modifier;
    int damage = (int) Math.round(damageDouble);

    System.out.print(this.getName() + " attacks " + target.getName() + " for " + damage + " damage.");
    
    if (critical == 2.0) {
      System.out.print(" Critical hit!");
    }
    System.out.println();
    System.out.println();

    target.hurt(damage);
    
    if (target.isAlive()) {
      System.out.println(target.getName() + " has " + target.getHealth() + " health remaining.");
    } else {
      System.out.println(target.getName() + " is defeated, and has been dispersed back into its component elements!");
    }

    return damage;
  }


  /****************************
   * Applies damage to the Elemental, decreasing its current health points.
   * @param damage The amount of damage to be applied
   ***************************/
  public void hurt(int damage) {
    health -= damage;
  }

  /****************************
   * Figure out if this Elemental is alive or not.
   * @return True if current health is greater than 0, false otherwise.
   ***************************/
  public boolean isAlive() {
    return health > 0;
  }


  
}