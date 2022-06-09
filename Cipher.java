import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Cipher {
	
	// Private class variables initialised once, to be used for encoding and decoding
	// Two hashmaps are kept in memory to act as a bidirectional hashmap for
	// converting between characters and their indices.
	private static Scanner sc = new Scanner(System.in);
	private final static String chartable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
	private static Map<Character,Integer> charToInt = new HashMap<Character,Integer>();
	private static Map<Integer,Character> intToChar = new HashMap<Integer,Character>();
	
	
	// Main encoding function.
	public static String encode(String plainText) {
		System.out.println("Enter offset character for encoding:");
		String output = sc.nextLine();
		int offset = charToInt.get(output.charAt(0));
		char currChar;
		char shiftedChar;
		
		for (int i = 0; i < plainText.length(); i++) {
			currChar = plainText.charAt(i);
			
			// If character is a not in table, don't shift it.
			if (!charToInt.containsKey(currChar)) {
				shiftedChar = currChar;
				}
			// Else, apply shift accordingly
			else {
				shiftedChar = intToChar.get(Math.floorMod(charToInt.get(currChar) - offset, 44));
				}
			// And add to output string
			output = output + shiftedChar;
		}
		return output;
	}
	
	
	// Main decoding function
	public static String decode(String encodedText) {
		String output = "";
		int offset = charToInt.get(encodedText.charAt(0));
		char currChar;
		char shiftedChar;
		
		for (int i = 1; i < encodedText.length(); i++) {
			currChar = encodedText.charAt(i);
			
			// If character is not in table, don't shift it. 
			if (!charToInt.containsKey(currChar)) {
				shiftedChar = currChar;
				}
			// Else, apply shift accordingly
			else {
				shiftedChar = intToChar.get(Math.floorMod(charToInt.get(currChar) + offset, 44));
				}
			// And add to output string
			output = output + shiftedChar;
		}
		return output;
	}
	
	
	// Helper function to initialise mapping of characters to indices.
	private static void createMapping() {
		for (int i = 0; i < chartable.length(); i++) {
			charToInt.put(chartable.charAt(i), i);
			intToChar.put(i, chartable.charAt(i));
		}
		return;
	}
	
	
	public static void main(String[] args) {
		// Initialise private variables and prepare for user input
		String command = "";
		createMapping();
		
		// Main while loop to catch user input
		while (!command.equals("exit")) {
			System.out.println("Available commands:\n"
					 + "1. encode\n"
					 + "2. decode\n"
					 + "3. exit\n"
					 + "Your command:");
			command = sc.nextLine();
			
			// Switch case for encoding, decoding, or exiting code
			switch (command) {
			case "encode":
				System.out.println("Enter input string for encoding:");
				System.out.println(encode(sc.nextLine()));
				break;
				
			case "decode":
				System.out.println("Enter input string for decoding:");
				System.out.println(decode(sc.nextLine()));
				break;
				
			case "exit":
				break;
				
			default:
				System.out.println("Invalid command");
			}
			
		}
		System.out.println("Exited");
		
	}

}
