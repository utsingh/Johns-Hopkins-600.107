import java.util.Scanner;
import java.util.Arrays;

public class Search {

   private static int i = 0;
   private static String tempname = "";
   private static int error = 0;
   
   
 /****************************
  * Method to search if a summoner name exists in an array
  * @param promptString, prompting the user to input aummoner name
  * @param errorString, the error statement that summonername already exists 
  * @param counter, to go through array
  * @param summoner, the summoner array
  * @param search, true or false
  * @return search
  ***************************/

   public static boolean search(String promptString, String errorString,
                        int counter, Summoner[] summoner, boolean search) {
      
      Scanner stdIn = new Scanner(System.in);
      i = 0;
      String tempsummoner = "";
      
            do {
              error = 0;
              System.out.println(promptString);
              
              tempname = stdIn.nextLine();
              tempname = capitalize(tempname); 
              
              while (i < counter) {
              
                  //error = 0;
              
                  tempsummoner = summoner[i].getSummonerName();
                  tempsummoner = capitalize(tempsummoner); 
                
                
                  if (tempname.equals("")) {    // inadvertent return key
                    System.out.println("Invalid input: Blank Line entered!");
                    
                  }
                  else if((tempname.compareToIgnoreCase(tempsummoner)) == 0) {
                     search = (!search);
                     if (search == false) {
                       
                        System.out.println(errorString);
                        error++;
                     }
                     else {
                        return search;
                     }
                     
                  }
                  else {
                     if (search == false && i > (counter - 2)) {
                        System.out.println(errorString);
                        error++;
                        }
                     i++;
                  }
               } // end of while
                  
         } while (tempname.equals("")); // end of do-while
               
      return search;
   }
   
   
 
 /****************************
  * Method to search if a elemental name exists in an array
  * @param promptString, prompting the user to input elemental name
  * @param errorString, the error statement that the elemental name already exists 
  * @param counter, to go through array
  * @param elemental, the elemental array
  * @param search, true or false
  * @return search
  ***************************/
   public static boolean ElementalSearch(String promptString, String errorString,
                        int counter, Elemental[] elemental, boolean search) {
      
      Scanner stdIn = new Scanner(System.in);
      i = 0;
      String tempelemental = "";
      do {
              System.out.println(promptString);
              tempname = stdIn.nextLine(); 
              
              tempname = capitalize(tempname);
              
              while (i < counter) {
              
                  tempelemental = elemental[i].getName();
                  tempelemental = capitalize(tempelemental);
                  
                  
                  
                  if (tempname.equals("")) {    // inadvertent return key
                    System.out.println("Invalid input: Blank Line entered!");
                    
                  }
                        //search result matches
                  else if((tempname.compareToIgnoreCase(tempelemental)) == 0) {//tempname.equalsIgnoreCase(tempelemental)) {
                     search = (!search);
                     if (search == false) {
                       System.out.println(errorString);
                       error++;
                     }
                     else {
                       return search;
                     }
                     
                  }
                  else {
                         if (search == false && i > (counter - 2)) {
                         System.out.println(errorString);
                         error++;
                         }
                    i++;
                  }
               } // end of while
                  
         } while (tempname.equals("")); // end of do-while
               
      return search;
   }
   
 /**********************************
  * Method to get index of array  
  * @return index
  *************************************/
   public static int getIndex() {
      return i;
   }
   
 /**********************************
  * Method to get input from user  
  * @return user input
  *************************************/
   public static String getInput() {
      return Search.capitalize(tempname);
   }
   
   
 /*************************************
  * Method to capitalize tempname  
  * @return tempname
  *************************************/
   public static String capitalize(String tempname) {
      //capitalization
       tempname = tempname.toUpperCase();
       tempname = (tempname.charAt(0)) + (tempname.toLowerCase()).substring(1);
       return tempname;
   }
   
      
 /***************************************
  * Method to get error after search
  * @return error
  *************************************/
   public static int getError() {
      return error;
   }
}  
