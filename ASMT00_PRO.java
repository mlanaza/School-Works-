/****************************************************************
*
* File: ASMT00_PRO.java
* By: Anne Lanaza
* Date: 6/11/18
*
* Description: This program is similar to the the standard version
* 	except that it can accept two keywords. The first being the 
* 	word to be defined while the second keyword is the meaning by 
* 	its part of speech. If the user only types one keyword, it will
* 	list out all the definitions it has stored. If two keywords were
* 	entered but the second keyword doesn't exist for that word then 
* 	it just prints out "not found" similar to when a word isn't found.
*
****************************************************************/

import java.util.*;
import java.util.Map.Entry;

class DictionaryPro {
	
	private static Map<String, String> grammarMap;
	private static Map<DataPro, Map<String, String>> DataProMap;
	
	//constructor for the two maps
	DictionaryPro(){
		DataProMap = new HashMap<DataPro, Map<String, String>>();
		grammarMap = new LinkedHashMap<String, String>();
	}
	
	//an enum Data type where the raw data are stored
	public enum DataPro {
	CSC210 ("CSC210","verb", "To learn Java.",  
			"noun", "Intro to Java.",
			"adjective", "Comfortable with Objects and Classes., Ready for CSC 220."),
	CSC220 ("CSC220", "verb", "To create data structures.",  
			"adjective", "Ready to create complex data structures.", 
			"noun", "Data Structures."),
	CSC340 ("CSC340", "noun", "A CS upper division course., Many hours outside of class.",
			"adjective", "C++ version of CSC210 + CSC220 + more"),
	BOOK ("Book", "verb", "To arrange something on a particular date.", 
			"noun", "A set of pages." ),
	BOOKABLE ("Bookable", "adjective", "Can be ordered"),
	BOOKBINDER ("Bookbinder", "noun", "A person who fastens the pages of books."),
	BOOKCASE ("Bookcase", "noun", "A piece of furniture with shelves.");
		
		private String title;
		private List<String> definitions;
		
		//constructor that has first parameter as the title and the ff. data is stored into a list
		DataPro(String title, String ...definitions){
			this.title = title;
			this.definitions = Arrays.asList(definitions);
		}
		
		//getter for the title
		public String getTitle() {
			return this.title;
		}
	
		//getter for the definitions
		public List<String> getDefinitions(){
			return this.definitions;
		}
	}
	
	//initializes the linkedhashmap within a hashmap
	public void initializeMapping() {
		DataProMap = new HashMap<DataPro, Map<String, String>>();
		
		for(DataPro d: DataPro.values()) {
			List<String> arr = d.getDefinitions();
			grammarMap = new LinkedHashMap<String, String>();
			for(int i = 0; i < arr.size(); i+=2) {
				grammarMap.put(arr.get(i), arr.get(i+1));
			}
			DataProMap.put(d, grammarMap);
		}
	}

	public void searchData(String key1, String key2) {
		Scanner input = new Scanner(System.in);
		boolean found = false;
		
		if(key2.equals("question mark")) { //tests if 2nd keyword is a question mark
			System.out.println("\t|\r\t <2nd argument must be a part of speech>\r\t|");
		} else {
			for(Entry<DataPro, Map<String, String>> entry : DataProMap.entrySet()) {
				if(entry.getKey().toString().equals(key1)) { //checks if the 1st keyword exists in the data
					found = true;
					grammarMap = entry.getValue();
					if(grammarMap.containsKey(key2)) {
						for(Entry<String, String> entry2: grammarMap.entrySet()) {
							if(entry2.getKey().toString().equals(key2)) { 	//checks if the 2nd keyword exists in the data
								
								System.out.println("\t|\r\t " +entry.getKey().getTitle() + " [" + 
								entry2.getKey() + "] : " + entry2.getValue() + "\r\t|");
								break;
							}
						}
					} else if(key2 == "" || key2.isEmpty()) { 	//this happens when there's no 2nd keyword
						System.out.println("\t|");
						for(Entry<String, String> entry2: grammarMap.entrySet()) {
							System.out.println("\t " + entry.getKey().getTitle() + " [" + 
						entry2.getKey() + "] : " + entry2.getValue());
						} System.out.println("\t|");
					} else { 	//happens when the 1st keyword doesn't exist
						System.out.println("\t|\r\t <Not found>\r\t|");
					}
				}
			} if (found == false) System.out.println("\t|\r\t <Not found>\r\t|");
		}	
	}
	
}
public class ASMT00_PRO {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		DictionaryPro lookup = new DictionaryPro();
		lookup.initializeMapping();
		
		System.out.println("-----DICTIONARY 340 PRO-----\n");
		System.out.print("Search: ");
		String keyword1 = input.next();
		String keyword2 = input.nextLine();

		while(!keyword1.equals("!Q")) {
			
			lookup.searchData(keyword1.toUpperCase().trim(), keyword2.toLowerCase().trim());
			
			System.out.println();
			System.out.print("Search: ");
			keyword1 = input.next();
			keyword2 = input.nextLine();
		}	
		System.out.println("\r-----THANK YOU-----");
	}
}
