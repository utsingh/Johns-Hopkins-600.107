/***********************************************************************
 * Program to verify whether a particular string inputted from user is a valid sentence 
 * in the Elemental language. Must be a "roar" by itself, a sequence of 
 * one or more "chirp"s, a sequence of one or more "click"s, a "click" sequence followed 
 * by a "hiss", a "trill" followed by a "chirp" sequence, or any valid sequence
 * followed by a "growl" followed by any valid sequence.
 * Java 600.107
 * @author Morgan Lucas
 * @since 2014-04-20
 ************************************************************************/
 
 import java.util.*;
 
 
 public class Homework8 {
 
   private static String input; //user input
   private static int error = 0;
 
   public static void main(String[] args) {
 
      Scanner stdIn = new Scanner(System.in);
      
    do {
           
      System.out.println("Please enter Elemental language (enter quit to exit):");
      input = stdIn.nextLine();
      
      
      boolean invalid = read(input);
      boolean invalidate = growl(input);
      
      if ((error == 0) && (invalidate == false)) {
         System.out.println (input + "\t VALID");
      }
      else if (!input.equalsIgnoreCase("quit")){
         System.out.println(input + "\t INVALID");
      }
    } while (!input.equalsIgnoreCase("quit"));
       
    System.out.println("Thank you! Goodbye");
    System.exit(0);
 
   }//end of main
   

 /******************************************************************************
 * Method to read string from user input, using recursion. 
 * @param String str, string from user input. 
 * @return false if valid, true if invalid. 
 *****************************************************************************/
   
   public static boolean read(String str){
      StringTokenizer token = new StringTokenizer (str);
      int count = token.countTokens();
      boolean invalid = true;
      boolean temp;
      
      if (count == 1) {    // stopping condition
         invalid = spelling(token.nextToken());
         if (invalid == true) {
            error++;
         }
         return invalid;
      }
      else {
         String tempstring  = "";
         temp = spelling(token.nextToken());
         if (temp == false) {
            invalid = temp;
         
            count--;
            for (int i = 0; i < count; i++) {
               tempstring += " " + token.nextToken();
            }
            boolean tempreturn = read(tempstring); // recursive statement
         }
         else {
            error++;
         }
         //System.out.println("read " + invalid);    
         return invalid;
            
      } // end else
   
   }
   
   
 /******************************************************************************
 * Method to check spelling of tokens from user input. Checks that all tokens are
 * spelled "roar", "chirp", "click", "hiss", "trill" and "growl" (case insensitive).
 * @param String str, string from user input.
 * @return false if valid, true if invalid
 *****************************************************************************/
   
   public static boolean spelling (String str){
      boolean invalid = true;
      if (str.equalsIgnoreCase("roar")){
         invalid = false;
         }  
      else if (str.equalsIgnoreCase("chirp")){
         invalid = false;
         }
      else if (str.equalsIgnoreCase("click")){
         invalid = false;
         }
      else if (str.equalsIgnoreCase("hiss")){
         invalid = false;
         }
      else if (str.equalsIgnoreCase("trill")){
         invalid = false;
         }
      else if (str.equalsIgnoreCase("growl")){
         invalid = false;
         }
         
      if (invalid == false) {
         return false;         
      }
      else {
         return true;
      }     
 
   }// end of spelling
   
   
   
 /******************************************************************************
 * Method to check conditions of a string with "growl". 
 * @param String str, string from user input.
 * @return false if valid, true if invalid
 *****************************************************************************/   
 
   public static boolean growl(String str) {
    try {
      str = str.toUpperCase();
      
      int index = str.indexOf("GROWL");
      
      if (index > -1) { // GROWL exists
         boolean invalidate1 = growl(str.substring(0, index-1));
         boolean invalidate2 = growl(str.substring(index+5));
         
         if (invalidate1 == false && invalidate2 == false)
            return false;
         else
            return true;
      }
      else { // growl doesn't exist
         return valid(str);
      }
    }
    
    catch (StringIndexOutOfBoundsException e){
         return true;
    }
   }
   
   

 /******************************************************************************
 * Method to determine if a string is valid. Must be a "roar" by itself, a sequence of 
 * one or more "chirp"s, a sequence of one or more "click"s, a "click" sequence followed 
 * by a "hiss" or a "trill" followed by a "chirp" sequence.
 * @param String str, string from user input. 
 * @return false if valid, true if invalid. 
 *****************************************************************************/   
  
   public static boolean valid(String str) {
      boolean invalid = true;
      str = str.toUpperCase();
      StringTokenizer tokenizer = new StringTokenizer(str);
      
      int tokencount = tokenizer.countTokens();
      
      if ((str.indexOf("ROAR") != -1) && (tokencount == 1)) {     //ROAR (no sequence)
         invalid = false;
      }
      else if (str.indexOf("CHIRP") != -1) {    //CHIRP
         if (str.indexOf("TRILL") != -1) {      //TRILL + CHIRP
            return valid(str.substring(str.indexOf("TRILL")+5));  //recursion for valid() 
         }
         else {      //ONLY CHIRP, NO TRILL
            boolean invalid2;
            for (int i = 0; i < tokencount; i++) {    //check matches for chirp on every token
               invalid2 = tokenizer.nextToken().equals("CHIRP");
               
               if (invalid2 == false)
                  error++;    //if any token != chirp, increment error
            }
            
            if (error == 0)
               invalid = false;
         }
      }
      else if (str.indexOf("CLICK") != -1) {
         if (str.indexOf("HISS") != -1) {      //CLICK + HISS
            return valid(str.substring(0, str.indexOf("HISS")-1));   //recursion for valid()
         }
         else {      //ONLY CHIRP, NO TRILL
            boolean invalid2;
            for (int i = 0; i < tokencount; i++) {
               invalid2 = tokenizer.nextToken().equals("CLICK");
               
               if (invalid2 == false)
                  error++;
            }
            
            if (error == 0)
               invalid = false;
         }
      }
      
      return invalid;
   } // end valid
}// end class
      
      

/********************************************************************
 Reflections:
 This assignment took approximately 5-6 hours of programming and debugging
 to complete. I found this assignment less time intensive than our previous 
 assignments, but it was nice after the last assignment. I think that the
 assignment covered what we have learned about recursion very well and helped
 me to practice the material. I relied on the course website example, the book
 and the internet to help me when I got stuck. I learned that its very important to 
 map out the solution first in order to stay on the right track and have a clear 
 understanding before going in the wrong direction. Overall, I felt that
 the assignment was very practical in practicing what we learned from our 
 lectures.
************************************************************************/