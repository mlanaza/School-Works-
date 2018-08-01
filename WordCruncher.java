

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCruncher {
	private String word;
	char[] vowels = {'a','e','i','o','u'};

	
	
	

	
	// constructor that sets instance variable
	// into "default"
	public WordCruncher(){
		word = "default";
	}
	
	
	
	
	
	
	// a parameterized constructor that tests whether
	// GivenWord a has whitespace or punctuation marks.
	// If the GivenWord tests true for it, it will 
	// result to setting the word into "default"
	public WordCruncher(String GivenWord){
		if (isNotValidWord(GivenWord)){
			word = "default";
		} else {
			word = GivenWord;
		}	
	}
	
	
	
	
	
	
	// checks the word for punctuation marks and
	// whitespace using the matcher
	private boolean isNotValidWord(String checkWord){
		Pattern marks = Pattern.compile("[!,.?/:;']");
		Matcher marksMatcher = marks.matcher(checkWord);
			
		Pattern whitespace = Pattern.compile("\\s");		
		Matcher spaceMatcher = whitespace.matcher(checkWord);
	
		return spaceMatcher.find() || marksMatcher.find();
		
	}
	
	
	
	
	
	
	// returns the instance variable word
	public String toString(){
		return word;
	}
	
	

	
	
	
	// counts for letters in a word using
	// character's pre-defined method isLetter
	public int numLetters(){
		int countLetters = 0;
		for (int i = 0; i < word.length(); i++){
			char testchar = word.charAt(i);
			if(Character.isLetter(testchar)){
				countLetters++;
			}
		} 
		return countLetters;
	}
	
	
	
	
	

	// counts how many vowels in a word
	// by testing one character at a time
	// with an array of vowels
	public int numVowels() {
		int countVowel = 0;
		String tempWord = word.toLowerCase();
		
		for (char vowel : vowels){
			for (int i = 0; i < tempWord.length(); i++){
				char testChar = tempWord.charAt(i);
				if (testChar == vowel){
					countVowel++;
				}
			}
		}	
		return countVowel;
	}
	
	

	

	
	// a boolean method that tests whether a
	// word starts with a vowel using if-statements
	public boolean beginsWithVowel(){
		String tempWord = word.toLowerCase();
		
		if (tempWord.startsWith("a")){
			return true;
		}
		if (tempWord.startsWith("e")){
			return true;
		}
		if (tempWord.startsWith("i")){
			return true;
		}
		if (tempWord.startsWith("o")){
			return true;
		}
		return (tempWord.startsWith("u"));
	}
	
	
	
	
	
	
	// sets the word into a StringBuilder reverseWord
	// which will be use to reverse a word and return it
	public StringBuilder reverse() {
		StringBuilder reverseWord = new StringBuilder(word);
		
		reverseWord = reverseWord.reverse();
		return reverseWord;
	}
	
	
	
	
	
	
	// This method uses the StringBuilder in order
	// to change the word into a Pig Latin by adding
	// "way" or "ay" depending if it starts with a
	// consonant or a vowel
	public StringBuilder toPigLatin(){
		StringBuilder pigWord = new StringBuilder(word);
		char firstLetter = word.charAt(0);
		
		if(beginsWithVowel()){
			pigWord.append("way");
		} else {
			pigWord.deleteCharAt(0);
			pigWord.append(firstLetter);
			pigWord.append("ay");
		} 
		
		return pigWord;
	}
	
	
	
	
	
	
	// This method uses the StringBuilder in order
	// to change the word into Gibberish by adding "ithag"
	// at the start or after the first letter
	public StringBuilder toGibberish(){
		StringBuilder gibTalk = new StringBuilder(word);
		
		if(beginsWithVowel()){
			gibTalk.insert(0, "ithag");
		} else {
			gibTalk.insert(1, "ithag");
		}
		return gibTalk;
	} 
	
	
	
	
	
	
	// Counts how many times the char checkChar appeared
	// in the variable word. Then return the count to its
	// caller
	public int numCharOccurrences(char checkChar){
		int count = 0;
		for (int i = 0; i < word.length(); i++){
			Character testChar = word.charAt(i);
			if (testChar.equals(checkChar)){
				count++;
			}
		} return count;
	}

}
