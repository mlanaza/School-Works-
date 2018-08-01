/****************************************************************
*
* File: ASMT00_Standard.java
* By: Anne Lanaza
* Date: 6/11/18
*
* Description: This program is a dictionary type that accepts 
* 	keywords and searches it in the data stored in the map.
* 	It prints out the meaning of the keyword but if no such
*	keyword exists in the data it prints out "not found."
*
****************************************************************/

import java.util.*;
import java.util.Map.Entry;


class Dictionary {
	
	//map for the dictionary
	private static Map<Data, List<String>> dataMap;
	
	//constructor for the map using hashmap
	Dictionary(){
		dataMap = new HashMap<Data, List<String>>();
	}
	
	//an enum Data type where the raw data are stored
	public enum Data{
		CSC210 ("CSC210", "Intro to Java.", "To learn Java.", "Comfortable with Objects and Classes.", 
				"Ready for CSC 220."),
		CSC220 ("CSC220", "Data Structures.", "Ready to create complex data structures.", 
				"To create data structures."),
		CSC340 ("CSC340", "= C++ version of CSC210 + CSC220 + more", "A CS upper division course.", 
				"Many hours outside of class."),
		BOOK ("Book", "A set of pages.", "To arrange something on a particular date."),
		BOOKABLE ("Bookable", "Can be ordered."),
		BOOKBINDER ("Bookbinder", "A person who fastens the pages of books."),
		BOOKCASE ("Bookcase", "A piece of furniture with shelves.");
		
		private Data datas;
		private String title;
		private List<String> definitions;
		
		//constructor that has first parameter as the title and the ff. data is stored into a list
		Data(String title, String ...words){
			this.title = title;
			this.definitions = Arrays.asList(words);
		}
		
		//getter for definitions
		public List<String> getDefinitions(){
			return this.definitions;
		}
		
		//getter for the title
		public String getTitle() {
			return this.title;
		}
		
		//getter for the data or the title
		public Data getData() {
			return this.datas;
		}
		
	}
	
	//initializes the data_Map with the enum Data
	public void initializeMap(){
		dataMap = new HashMap<Data, List<String>>();
		for(Data d: Data.values()) {
			dataMap.put(d, d.definitions);
		}
	}
	
	//method for searching the keyword
	public void searchData(String keyWord){
		Scanner input = new Scanner(System.in);
		boolean found = false;
		for(Entry<Data, List<String>> entries: dataMap.entrySet()) {
			String key = entries.getKey().toString();
			if(keyWord.toUpperCase().equals(key)) { 	//tests out if the keyword exists in the data
				found = true;
				System.out.println("\t|");
				for(String values: entries.getValue()) {
					System.out.println("\t " + entries.getKey().getTitle() + ": " + values);
				}
				System.out.println("\t|");
				break;
			} 	
		}
		if (found == false) System.out.println("\t|\r\t <Not found>\r\t|");	//this is printed out when keyword doesn't exist
	}
}

public class ASMT00_Standard {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Dictionary lookup = new Dictionary();
		String word;
		
		lookup.initializeMap();
		
		System.out.println("-----DICTIONARY 340 Standard-----\n");
		System.out.print("Search: ");
		word = input.nextLine();
		
		while(!word.equals("!q")) {
			
			lookup.searchData(word);
			
			System.out.println();
			System.out.print("Search: ");
			word = input.nextLine();
		}		
		System.out.println("\r-----THANK YOU-----");
	}
}
