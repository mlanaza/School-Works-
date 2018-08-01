import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class PhysiciansHelper
{
	// symptom to illnesses map 
	private Map<String, List<String>> symptomChecker;


	/* Constructor symptomChecker map using TreeMap */
	public PhysiciansHelper()
	{ 
		// use TreeMap, i.e. sorted order keys
		symptomChecker = new TreeMap<String,List<String>>();
	} // end default constructor


	/* Reads a text file of illnesses and their symptoms.
	   Each line in the file has the form
		Illness: Symptom, Symptom, Symptom, ...  
	   Store data into symptomChecker map */

	public void processDatafile()
	{
		Scanner input = new Scanner(System.in);
		String filename = null;
		Scanner scFile = null;
		String[] symptoms;
		String[] token;
		
		System.out.print("Enter data filename: ");
		filename = input.next();
		
		
		try {
			scFile = new Scanner( new File (filename));
		} catch (FileNotFoundException e){
			System.out.println(e.getMessage());
			
		}
		if (scFile != null) {
			
			while(scFile.hasNext()) {
				String oneLine = scFile.nextLine();
				token = oneLine.toLowerCase().split(":");
				String disease = token[0].trim();
				symptoms = token[1].trim().split(",");
			
				for(String s: symptoms) {
					s = s.trim();
					List<String> value = symptomChecker.get(s);
					
					if(value != null) {
						value.add(disease);
						
					} else {
						value = new ArrayList<>();
						value.add(disease);
						symptomChecker.put(s, value);
						
					}
				}
			}
			
			//displays symptomChecker map
			for(Entry<String, List<String>> entries: symptomChecker.entrySet()) {
				System.out.println(entries.getKey()+ "=" + entries.getValue());
			}
			System.out.println();
		}
		// Step 1: read in a data filename from keybaord
		//         create a scanner for the file

		// Step 2: process data lines in file scanner
		// 2.1 for each line, split the line into a disease and symptoms
		//     make sure to trim() spaces and use toLowercase()
		// 2.2 for symptoms, split into individual symptom
		//     create a scanner for symptoms 
		//     useDelimeter(",") to split into individual symptoms 
		//     make sure to trim() spaces and use toLowercase()
		//     for each symptom
		//        if it is already in the map, insert disease into related list
		//        if it is not already in the map, create a new list with the disease
		// Step 3: display symptomChecker map

		// implement here.....

	} // end processDatafile



	/*  Read patient's symptoms in a line and adds them to the list.
		Input format: Symptom, Symptom, Symptom,...
	    Shows diseases that match a given number of the symptoms. */

	public void processSymptoms()
	{
		Map<String, Integer> matchedSymptoms = new TreeMap<String, Integer>();
		Scanner input = new Scanner(System.in);
		String patientInput;
		List<String> patientSymptoms = new ArrayList<>();
		String[] checkSymptoms;
		
		
		System.out.println("The possible symptoms are: ");
		for(Entry<String, List<String>> k: symptomChecker.entrySet()) {	
			System.out.println(k.getKey());
		}
		System.out.println("");
		System.out.print("Enter symptoms: ");
		patientInput = input.nextLine();
		checkSymptoms = patientInput.toLowerCase().trim().split(",");
		
		//checks if valid symptom
		for(String c: checkSymptoms) {
			c = c.trim();
			for(Entry<String, List<String>> entries: symptomChecker.entrySet()) {	
					String k = entries.getKey();
				
				if(c.equals(k)) {
					
					if(patientSymptoms.contains(c)) { 
						System.out.println("=>duplicate symptom: " + c);
					} else {
						patientSymptoms.add(c);
						break; 
					}
					
				} 
			} if(!patientSymptoms.contains(c)) {
				System.out.println("=>invalid symptom: " + c);
			}
		}
		
		System.out.printf("\nPatientSymptoms list:");
		
		System.out.println(Arrays.toString(patientSymptoms.toArray()));
		
		//determines what disease(s) matches the symptom(s)
		for(String p: patientSymptoms) {
			List<String> illness = symptomChecker.get(p);
			for(String i: illness){
				if(!matchedSymptoms.containsKey(i)) {
					matchedSymptoms.put(i, new Integer(1));
				} else {
					Integer frequency = matchedSymptoms.get(i) + 1;
					matchedSymptoms.put(i, frequency);
				}
			}
			
		}
		
		for(int i = 1; i <= matchedSymptoms.size(); i++) {
			int count = 1;
			for(Entry<String, Integer> entries: matchedSymptoms.entrySet()) {
				Integer freq = entries.getValue();
				String disease = entries.getKey();
				if(i == freq){
					if(count == 1) {
						System.out.println("==> Disease(s) match " + i + " symptom(s)");
						System.out.println(disease);
						count++;
					} else {
						System.out.println(disease);	
					}
					
				}
			}	
		}
		// Step 1: get all possible symptoms from symptomChecker map
		//         and display them
		// Step 2: process patient symptoms, add to patientSymptoms list 
		//         read patient's symptoms in a line, separated by ','
		//         create a scanner for symptoms 
		//         UseDelimeter(",") to split into individual symptoms 
		//         make sure to trim() spaces and use toLowercase()
		//         display invalid/duplicate symptoms
		//         add valid symptoms to patientSymptoms list
		// Step 3: display patientSymptoms list
   	        // Step 4: process illnesses to frequency count
		//         create a map of disease name and frequency count
		//         for each symptom in patientSymptoms list
		//              get list of illnesses from symptomChecker map
		//              for each illness in the list
		// 	            if it is already in the map, increase counter by 1
	        //	            if it is not already in the map, create a new counter 1
		//         ** need to keep track of maximum counter numbers
		// Step 5: display result
		//         for count i = 1 to maximum counter number
		//             display illness that has count i
		 

		// implement here.....

	} // end processSymptoms 


	public static void main(String[] args)
	{

		PhysiciansHelper lookup = new PhysiciansHelper();
		lookup.processDatafile();
		lookup.processSymptoms();
	} // end main
	
	
	//private methods
	
	
} // end PhysiciansHelper
