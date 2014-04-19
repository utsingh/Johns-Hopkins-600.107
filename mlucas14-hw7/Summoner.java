
/************************************************************
 * Program to represent Summoners in the Homwework 7 driver class. 
 * Java 600.107
 * @author Morgan Lucas
 * @since 2014-04-11
 ****************************************************************/
 

import java.text.DecimalFormat;
import java.util.*;


public class Summoner {

   Scanner stdIn = new Scanner(System.in);
   private String name; // elemental name
   private String summonername;
   private String[] elementalname = new String[1000];
   
   private int elementalcounter = 0;
   private int[] elementalhealth = new int[1000];
   Elemental[] elemental = new Elemental[1000];
   private int maxlevel = 0;
   private String maxlevelname = "";
   private int error;
 

 /****************************
  * Constructs summoner with name generated from user input.
  * @param name The name of the summoner
  ***************************/ 
   
public Summoner(String summonername)
{
   summonername = Search.capitalize(summonername);
   this.summonername = summonername;
}

 /****************************
  * Method to copy summoner's name 
  * @return copy of summoner's name
  ***************************/
public void copy(Summoner object){
   this.summonername = object.summonername;
}

 /****************************
  * Method to set summoner's name from user input 
  * @return summoner's name 
  ***************************/
public void setSummonerName(String newname) {
   newname = Search.capitalize(newname);
   this.summonername = newname;
}

 /****************************
  * Method to get elemental's name 
  * @return elemental's name 
  ***************************/
public String getName()
{
   return Search.capitalize(this.name);
}


 /****************************
  * Method to get summoner's name 
  * @return summoner's name 
  ***************************/
public String getSummonerName()
{
   return Search.capitalize(this.summonername);
}

 /****************************
  * Method to get elemental object array
  * @return elemental 
  ***************************/
public Elemental[] getElementalObjectArray() {
   return this.elemental;
}

 /****************************
  * Method to get elemental counter used for array
  * @return elemental counter 
  ***************************/
public int getElementalCounter (){
   return this.elementalcounter;
}

 /****************************
  * Method to get the summoner's maximum level of elementals
  * @return the maximum level
  ***************************/
public int getMaxLevel() {
   return this.maxlevel;
}

 /****************************
  * Method to input user's elementals if valid input and if elemental name exists. 
  * @return void
  ***************************/
public void elementalSearchAdd(){
  
   boolean invalid = false;
   
   boolean search = true;
   String promptString = "Enter elemental name: ";
   String errorString = "Sorry! Elemental already exists!";
   search = Search.ElementalSearch(promptString, errorString,
                        this.elementalcounter, elemental, search);
   this.name = Search.getInput();
   this.error = Search.getError();
             if (search && (this.error == 0)) {
              
               invalid = false;
               int count = 4;
                
              do {
                count = 4;
                System.out.println("Enter Elemental stats as \"type, level, attack, defense\": ");
                String type = stdIn.next();
                type = Search.capitalize(type);
                int level = stdIn.nextInt();
                int attack = stdIn.nextInt();
                int defense = stdIn.nextInt();              
            
               
                String fixedType = "";
                fixedType += Character.toUpperCase(type.charAt(0));
                fixedType += type.toLowerCase().substring(1);  
      
                if (!fixedType.equals("Fire") && 
                 !fixedType.equals("Water") && 
                 !fixedType.equals("Air") &&
                 !fixedType.equals("Earth"))
                {
                     System.out.println("Invalid elemental type! ");
                     invalid = true;
                     count--;
                }

                /* verify numbers are in legal ranges */
                if (level < 1 || level > 20) {
                    System.out.println("Invalid level! ");
                    invalid = true;
                    count--;
                }

                if (attack < 1 || attack > 10) {
                     System.out.println("Invalid attack! ");
                     invalid = true;
                     count--;
                }

                if (defense < 1 || defense > 10) {
                     System.out.println("Invalid defense! ");
                     invalid = true;
                     count--;
                }
            
            
            
                if (!invalid) {  // if this one is good
                  elemental[this.elementalcounter] = new Elemental(this.name, level, (level*5), attack, defense, type);
                  this.elementalcounter++;
                  if(level > this.maxlevel){
                     this.maxlevel = level;
                     this.maxlevelname = this.name;
                     this.maxlevelname = Search.capitalize(this.maxlevelname);
                   }
                }             
              } while(count != 4); //end of do-while
               
            
            } // end of if (search
                   
}


 /****************************
  * Method to get elemental's name from array 
  * @return elemental name
  ***************************/
public String[] getElementalName(){
   return this.elementalname;
}


 /****************************
  * Method to print elemental's name and health points 
  * @return void
  ***************************/
public void printElementNameHealth() {
   String[][] elementarray = new String[this.elementalcounter][2];
   
   for (int i = 0; i < this.elementalcounter; i++) {
      elementarray[i][0] = elemental[i].getName();
      elementarray[i][0] = Search.capitalize(elementarray[i][0]);
          
      elementarray[i][1] = Integer.toString(elemental[i].getHealth());
   }
   
   Arrays.sort(elementarray, new Comparator<String[]>(){
               @Override
               public int compare(String[] first, String[] second) {
                  return Double.valueOf(second[1]).compareTo(Double.valueOf(first[1]));
               }
   });
          
            
   for (int k = 0; k < this.elementalcounter; k++){
            elementarray[k][0] = Search.capitalize(elementarray[k][0]);
            System.out.println("\'" + elementarray[k][0] + "\' with " + elementarray[k][1] + " Current Health Points. ");
   }

}
}