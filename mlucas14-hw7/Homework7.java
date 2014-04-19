/***********************************************************************
 * Driver class for Homework 7; implements a menu system in which the user 
 * can add elementals and summoners, and have a battle between them.  
 * Java 600.107
 * @author Morgan Lucas
 * @since 2014-04-11
 ************************************************************************/
 
 

/******************************************************************************
 * Following code imported from prof. Ben Mitchell's solution to Assignment 5
 * (with changes)
 *****************************************************************************/ 

 
import java.text.DecimalFormat;
import java.util.*;


public class Homework7 {


  private static int summonerarraycounter = 0;//  number generator; fixed seed used for testing
  private static Random rand = new Random(1337);

  // constants to use for initializing elementals
  private static final String FIRE_NAME = "Flamey";
  private static final int LEVEL = 8; 
  private static final int FIRE_ATTACK = 6, FIRE_DEFENSE = 3, FIRE_HEALTH = 5;
  private static final String EARTH_NAME = "Dirtbag";
  private static final int DEFAULT = 5; // default value for short constructor
  private static int error;
  
  
 /******************************************************************************
 * main function for driver class; works with menu system in which the user can
 * choose to add summoners and elementals and have a battle between them.
 *****************************************************************************/

  public static void main(String[] args) {
    Scanner stdIn = new Scanner(System.in);
    String menu;
    String[] summonername = new String[1000];
    Summoner[] summoner = new Summoner[1000];
    int summonercounter = 0; // for summoner object array

    int summonerindex;
    int summonerindex2;
    

    boolean invalid;
    boolean search;
    int menuChoice = 0;
    String tempname = "";
    String promptString = "";
    String errorString = "";
    int index = 0;
    
    
    while (menuChoice != 6) {  // loop until user quits
    
      System.out.println(
          "\nMain Menu: \n" + 
          "1) Add a summoner\n" +
          "2) Add an Elemental\n" +
          "3) Display list of summoners\n" + 
          "4) Display a summoner's Elementals\n" +
          "5) Battle two elementals.\n" +
          "6) Exit the program");

      System.out.print("Enter choice: ");
      try {
    	  menuChoice = stdIn.nextInt();
    
    	  if (menuChoice > 0 && menuChoice < 7) {
    		  System.out.println();  
    		  summonerindex = 0;
      
    		  switch (menuChoice) {
    		  	case 1:  // add a summoner
         
    		  		search = true;
            
    		  		promptString = "Please enter name for Summoner: ";
    		  		errorString = "Sorry! This Summoner already exists.";
    		  		search = Search.search(promptString, errorString,
                        summonercounter, summoner, search);
    		  		error = Search.getError();
    		  		tempname = Search.getInput();
    		  		tempname = Search.capitalize(tempname);
            
    		  		if (search && (error == 0)) {
    		  			summoner[summonercounter] = new Summoner(tempname);
    		  			summonercounter++;
    		  			break;
    		  		} else
    		  			break;

    		  	case 2: // add an elemental
          
    		  		tempname = "";
            
    		  		search = false;
    		  		promptString = "Please enter the Summoner name: ";
    		  		errorString = "Sorry! No Summoners exist by this name!";
    		  		search = Search.search(promptString, errorString,
                        summonercounter, summoner, search);
    		  		error = Search.getError();
    		  		index = Search.getIndex();
         
    		  		if (search && (error == 0)) {             
    		  			summoner[index].elementalSearchAdd();
                
    		  		} 
    		  		break;
         
         
    		  		//display list of summoners
    		  	case 3:
    		  		String[][] printarray = new String[summonercounter][3];
          
    		  		for (int k = 0; k < summonercounter; k++) {
    		  			printarray[k][0] = summoner[k].getSummonerName();
    		  			printarray[k][0] = (printarray[k][0]).toUpperCase();
    		  			printarray[k][0] = (printarray[k][0].charAt(0)) + (printarray[k][0]).toLowerCase().substring(1);
               
    		  			printarray[k][1] = Integer.toString(summoner[k].getElementalCounter());
    		  			printarray[k][2] = Integer.toString(summoner[k].getMaxLevel());
    		  		}
          
    		  		Arrays.sort(printarray, new Comparator<String[]>(){
    		  			@Override
    		  			public int compare(String[] first, String[] second) {
    		  				return first[0].compareTo(second[0]);
    		  			}
    		  		});
          
            
    		  		for (int k = 0; k < summonercounter; k++){
         
    		  			System.out.println(printarray[k][0] + " has " + printarray[k][1] + " Elementals "  
    		  					+ "with highest Level at " + printarray[k][2]);
    		  		}
    		  		break;
          
    		  		
    		  		//display a summoner's elementals  
    		  	case 4:
    		  		tempname = "";
              
    		  		search = false;
            
    		  		promptString = "Please enter a summoner's name: ";
    		  		errorString = "Summoner does not exist!";
    		  		search = Search.search(promptString, errorString,
                        summonercounter, summoner, search);
    		  		error = Search.getError();
    		  		tempname = Search.getInput();
    		  		index = Search.getIndex();
    		  		int elementalcounter = summoner[index].getElementalCounter();
                        
               
    		  		if (search && (error == 0)) {
    		  			if (elementalcounter > 1) {
    		  				System.out.println("\n" + tempname + " has " + elementalcounter + " Elemental(s) named: \n"); 
    		  			} else {
    		  				System.out.print(tempname + " has " + elementalcounter + " Elemental(s) named ");
    		  			}
    		  			summoner[index].printElementNameHealth();          
    		  		} // end of if(search)
    		  		break;    
               

    		  	case 5:   // battleground!
    		  		String input;
    		  		String[][] capitalizedNames = new String[2][2];
    		  		
    		  		//list of summoners
    		  		System.out.println("Do you need to see a list of Summoners to help you choose? (Y/N)");
    		  		input = stdIn.next();
    		  		if (input.equalsIgnoreCase("y")
    		  				|| input.equalsIgnoreCase("yes")) {

    		  			System.out.println("Printing a list of Summoners. Please choose one:\n");
    		  			for(int i = 0; i < summonercounter; i++) {
    		  				System.out.println(summoner[i].getSummonerName());
    		  			}
    		  			System.out.println();
    		  		} // end if
            
    		  		//summoner 1
    		  		search = false;
    		  		promptString = "Please enter the first Summoner's name: ";
    		  		errorString = "Sorry! No Summoners exist by this name!";
    		  		boolean search1 = Search.search(promptString, errorString,
    		  				summonercounter, summoner, search);        
    		  		String summoner1 = Search.getInput();
    		  		summoner1 = Search.capitalize(summoner1);
    		  		int index1 = Search.getIndex();
            
            
            
    		  		//list of summoner 1's elementals
    		  		System.out.println("Do you need to see a list of " 
    		  				+ summoner1 + "'s Elementals to help you choose? (Y/N)");
    		  		input = stdIn.next();
    		  		if (input.equalsIgnoreCase("y")
    		  				|| input.equalsIgnoreCase("yes")) {

    		  			System.out.println("Printing a list of " + summoner1 + "'s Elementals.\n"
    		  					+ "Please choose one:\n");
    		  			for(int i = 0; i < (summoner[index1].getElementalCounter()); i++) {
    		  				System.out.println((summoner[index1].getElementalObjectArray())[i]);//
    		  			} // end for
    		  			System.out.println();
    		  		} // end if
            
    		  		//elemental 1
    		  		search = false;
    		  		promptString = "Please enter " + summoner1 + "'s Elemental's name: ";
    		  		errorString = "Sorry! No Elementals exist by this name!";
    		  		boolean search2 = Search.ElementalSearch(promptString, errorString,
    		  				(summoner[index1].getElementalCounter()), (summoner[index1].getElementalObjectArray()), search);
    		  		String elemental1 = Search.getInput();
    		  		elemental1 = Search.capitalize(elemental1);
    		  		int eindex1 = Search.getIndex();
            
            
            
    		  		//list of summoners
    		  		System.out.println("Do you need to see a list of Summoners to help you choose? (Y/N)");
    		  		input = stdIn.next();
    		  		if (input.equalsIgnoreCase("y")
    		  				|| input.equalsIgnoreCase("yes")) {
    		  			System.out.println("Printing a list of Summoners. Please choose one:\n");
    		  			for(int i = 0; i < summonercounter; i++) {
    		  				System.out.println(summoner[i].getSummonerName());
    		  			}
    		  			System.out.println();
    		  		}
            
    		  		// summoner 2
    		  		search = false;
    		  		promptString = "Please enter the second Summoner's name: ";
    		  		errorString = "Sorry! No Summoners exist by this name!";
    		  		boolean search3 = Search.search(promptString, errorString,
    		  				summonercounter, summoner, search);
    		  		String summoner2 = Search.getInput();
    		  		summoner2 = Search.capitalize(summoner2);
    		  		int index2 = Search.getIndex();
            
    		  		// list of summoner 2's elementals
    		  		System.out.println("Do you need to see a list of " 
    		  				+ summoner2 + "'s Elementals to help you choose? (Y/N)");
    		  		input = stdIn.next();
    		  		if (input.equalsIgnoreCase("y")
    		  				|| input.equalsIgnoreCase("yes")) {
                 
    		  			System.out.println("Printing a list of " + summoner2 + "'s Elementals.\n"
    		  					+ "Please choose one:\n");
    		  			for(int i = 0; i < (summoner[index2].getElementalCounter()); i++) {
    		  				System.out.println((summoner[index2].getElementalObjectArray())[i]);
    		  			} // end for
    		  			System.out.println();
    		  		} // end if

    		  		//elemental 2    
    		  		search = false;
    		  		promptString = "Please enter " + summoner2 + "'s Elemental's name: ";
    		  		errorString = "Sorry! No Elementals exist by this name!";
    		  		boolean search4 = Search.ElementalSearch(promptString, errorString,
    		  				(summoner[index2].getElementalCounter()), (summoner[index2].getElementalObjectArray()), search);
    		  		String elemental2 = Search.getInput();
    		  		elemental2 = Search.capitalize(elemental2);
    		  		int eindex2 = Search.getIndex();
            
    		  		System.out.println();System.out.println();
            
    		  		
    		  		
    		  		// BATTLE
            
    		  		if (search1 && search2 && search3 && search4) {
               
    		  			battle(summoner[index1], summoner[index2], 
    		  					(summoner[index1].getElementalObjectArray())[eindex1],
    		  					(summoner[index2].getElementalObjectArray())[eindex2]);
                     
    		  		}
    		  		break;
    	
    		  		
    		  	case 6:
    		  		System.out.println("Thank you! Program exiting now...");
    		  		System.exit(0);
    		  		break;

    		  	default:
    		  		System.out.println("Invalid menu choice, try again.");
    		  		break;
    		  } // end of switch
      
      
    	  } // end of if (menuChoice)
    	  else {
    		  System.out.println("\n\nPlease input an integer from 1 to 6 to make a selection. \n"
    				  + "You can also input '6' to QUIT immediately.");
         
    		  continue;
    	  } // end of else
         
      }// end of try
       
      catch (InputMismatchException e) {
    	  System.out.println("\n\nPlease enter a valid input. \n"
                     + "The menu options range from integers 1 to 6. \n"
                     + "Please try again.");
    	  stdIn.next();
    	  continue;
      }
      
      
    } // while loop

    System.out.println("Goodbye!");
  
  }//end of main
  
  
  
  /******************************************************************************
 * Method to have a simple battle between two elementals.
 * @param summoner one, the first summoner chosen to battle
 * @param summoner two, the second summoner chosen to battle
 * @param elemental a, summoner one's elemental chosen to battle
 * @param elemental b, summoner two's elemental chosen to battle
 * @return the summoner with highest health points
 *****************************************************************************/
  
  
  public static void battle(Summoner one, Summoner two,
         Elemental a, Elemental b) {
  
      a.setHealth((a.getLevel())*5);
      b.setHealth((b.getLevel())*5);
   
      
      
      System.out.println("\n\n\n" + (one.getSummonerName()) + "'s elemental " 
            + "\'" + (a.getName()) + "\' stands with " + (a.getHealth()) + " Health Points "
            + " \n           VS           \n"
            + (two.getSummonerName()) + "'s elemental " 
            + "\'" + (b.getName()) + "\' stands with " + (b.getHealth()) + " Health Points ");
      System.out.println("\nThe battle begins...!\n\n\n");
      
      
       // have the Elementals battle until one is defeated
    while (a.isAlive() && b.isAlive()) {
      // set base to 1.0
      double base = 1.0;
      // critical occurs with 20% liklihood
      double critical = rand.nextInt() % 5 == 0 ? 2.0 : 1.0;
      double typeModifier = getModifier(a, b);
      double random = getRandom();

      a.attack(b, base, critical, typeModifier, random);

      // if b has taken enough damage to be dispersed, end the loop here
      if (!b.isAlive()) {
        break;
      }

      // calculate new values for second attack
      critical = rand.nextInt() % 5 == 0 ? 2.0 : 1.0;
      typeModifier = getModifier(b, a);
      random = getRandom();
      
      b.attack(a, base, critical, typeModifier, random);

    } // end while

    // re-test getHealth() and isAlive() now that one of the Elementals has been dispersed
    if (a.getHealth() > 0 && b.getHealth() > 0)
      System.out.println("getHealth() failed test (2)");

    if (a.isAlive() && b.isAlive()) {
      System.out.println("isAlive() failed test (2)");
    } else if (a.isAlive()) {  // a won
      System.out.println(a.getName() + " won!\n" + a.iconString());
    } else { // b won
      System.out.println(b.getName() + " won!\n" + b.iconString());
    }
  }
  
  
  /******************************************************************************
 * Get type-modifier for Elemental battle
 * @param elemental a, summoner one's elemental chosen to battle
 * @param elemental b, summoner two's elemental chosen to battle
 * @return value in the range [0.5,2.0] based on relative strength of Element types
 *****************************************************************************/
  public static double getModifier(Elemental a, Elemental b) {
    String attackType = a.getType();
    String defenseType = b.getType();
    double value = 1.0;
    
    if (attackType.equalsIgnoreCase("fire")) {
      if (defenseType.equalsIgnoreCase("fire")) {
        value = 0.75;
      } else if (defenseType.equalsIgnoreCase("water")) {
        value = 1.5;
      } else if (defenseType.equalsIgnoreCase("earth")) {
        value = 0.5;
      } else if (defenseType.equalsIgnoreCase("air")){ 
        value = 1.25;
      }
    } else if (attackType.equalsIgnoreCase("water")) {
      if (defenseType.equalsIgnoreCase("fire")) {
        value = 2.00;
      } else if (defenseType.equalsIgnoreCase("water")) {
        value = 0.5;
      } else if (defenseType.equalsIgnoreCase("earth")) {
        value = 1.5;
      } else if (defenseType.equalsIgnoreCase("earth")) { 
        value = 0.5;
      }
    } else if (attackType.equalsIgnoreCase("earth")) {
      if (defenseType.equalsIgnoreCase("fire")) {
        value = 1.00;
      } else if (defenseType.equalsIgnoreCase("water")) {
        value = 1.5;
      } else if (defenseType.equalsIgnoreCase("earth")) {
        value = 1.00;
      } else if (defenseType.equalsIgnoreCase("earth")){
        value = 0.5;
      }
    } else if (attackType.equalsIgnoreCase("air")){
      if (defenseType.equalsIgnoreCase("fire")) {
        value = 1.25;
      } else if (defenseType.equalsIgnoreCase("water")) {
        value = 1.25;
      } else if (defenseType.equalsIgnoreCase("earth")) {
        value = 0.75;
      } else if (defenseType.equalsIgnoreCase("air")) { 
        value = 0.75;
      }
    }
    
    return value;
  } // end getModifier
  
  

/******************************************************************************
 * Get random-modifier for Elemental battle
 * @return random value in the range [0.75, 1.50)
 *****************************************************************************/
  public static double getRandom() {
    return rand.nextDouble() * 0.75 + 0.75;
  } 


}

/*
 Reflections:
 This assigment was definitely the most challenging assignment that we have had so far 
 this semester. It took about a total of 20-24 hours to complete so I was glad that
 I started it early. At the end, I was proud of myself and found the assignment very
 rewarding and helpful in utilizing what we have learned in class. I find object 
 oriented programming very difficult so it was good to be able to practice it in this
 homework assignment. I relied mainly on the book, piazza and the internet in finding my 
 errors. I found it difficult to keep track of what I had done when coming back to 
 my work the next day. Overall, the assignment was very confusing but I learned a lot
 from it and I am very glad to have completed it. 
*/