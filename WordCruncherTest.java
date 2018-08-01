/* 	A10
	Mary Lanaza 
	CIS 254
	05-15-17
	Dave Harden 
	
	In this program, several string and character
	methods were utilized. First, the program asks
	for a word from the user. The input is validated if
	it doesn't contain any whitespace or punctuation marks.
	If it does, it sets the instance variable word in the
	WordCrucher class into "default." The program will run
	continuously until the user types quit. The word will then
	be tested on how many letters and vowels it has. Then it also
	turns the word into Pig Latin and Gibberish words. The 
	program also reverses the word and asks for a letter,
	if the word has the letter, it return the count of
	occurrences.*/

import java.util.Scanner;

public class WordCruncherTest {

	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		WordCruncher word = new WordCruncher();
		
		System.out.print("Please enter a word or enter quit to cancel: ");
		String tempWord = input.nextLine();
		word = new WordCruncher(tempWord);		
		
		while (!isQuit(tempWord)){
			
		int intLetters = word.numLetters();
		System.out.println("The number of letters in the word: " + intLetters);
		
		int intVowels = word.numVowels();
		System.out.println("The number of vowels in the word: " + intVowels);
		
		StringBuilder reverse = word.reverse();
		System.out.println("The word reversed: " + reverse);
		
		Object objectPig = word.toPigLatin();
		System.out.println("The word translated into Pig Latin: " + objectPig);
		
		Object objectGib = word.toGibberish();
		System.out.println("The word translated into Gibberish: " + objectGib);
		
		System.out.print("Please enter one letter: ");
		String letter = input.nextLine();
		char character = letter.charAt(0);
		System.out.println("The number of letter occurrences in the word: " 
		+ word.numCharOccurrences(character));
		
		System.out.println(" ");
		
		System.out.print("Please enter a word or enter quit to cancel: ");
		tempWord = input.nextLine();
		word = new WordCruncher(tempWord);
		
		
		}
		
		}
	
	
	
	public static boolean isQuit(String s1){
		String s2 = "quit";
		
		return s1.equalsIgnoreCase(s2);
		
	}
}

/*----------------paste of run from console window---------

Please enter a word or enter quit to cancel: HELLO
The number of letters in the word: 5
The number of vowels in the word: 2
The word reversed: OLLEH
The word translated into Pig Latin: ELLOHay
The word translated into Gibberish: HithagELLO
Please enter one letter: L
The number of letter occurrences in the word: 2
 
Please enter a word or enter quit to cancel: Mary Anne
The number of letters in the word: 7
The number of vowels in the word: 3
The word reversed: tluafed
The word translated into Pig Latin: efaultday
The word translated into Gibberish: dithagefault
Please enter one letter: m
The number of letter occurrences in the word: 0
 
Please enter a word or enter quit to cancel: oRangE
The number of letters in the word: 6
The number of vowels in the word: 3
The word reversed: EgnaRo
The word translated into Pig Latin: oRangEway
The word translated into Gibberish: ithagoRangE
Please enter one letter: e
The number of letter occurrences in the word: 0
 
Please enter a word or enter quit to cancel: Quit!
The number of letters in the word: 7
The number of vowels in the word: 3
The word reversed: tluafed
The word translated into Pig Latin: efaultday
The word translated into Gibberish: dithagefault
Please enter one letter: i
The number of letter occurrences in the word: 0
 
Please enter a word or enter quit to cancel: quit

-----------------------------------------------------------*/
