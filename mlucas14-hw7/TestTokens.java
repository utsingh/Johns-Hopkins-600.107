/**
 * 
 */
import java.util.Scanner;
/**
 * @author UT
 *
 */
public class TestTokens {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter string:");
		String input = scan.nextLine();
		char[] charArray = tokens(input);
		
		for (int i=0; i < charArray.length; i++) {

			System.out.println("index " + i + "\t" + (int) charArray[i]);
		}
		
		scan.close();
		
	}
	
	public static char[] tokens(String input){
		char[] charArray = input.toCharArray();
	return charArray;
	}

}
