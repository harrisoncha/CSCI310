package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Queue;

public class FileReaderWriter {
	public static HashMap<String, Integer>ReadFile(String filename, StringBuilder initialDestination){
		FileReader fr = null;
		BufferedReader br = null;
		HashMap<String, Integer> map = new HashMap<>();

		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			
			//retrieve where you are leaving from
			String line = br.readLine();
			initialDestination.append(line);
			
			//retrieve first line for destinations and costs
			line = br.readLine();	
			
			while(line != null){
				String[] values = line.split(" ");				
				String key = values[0] + values[1];
				Integer value = Integer.parseInt(values[2]);
				map.put(key, value);				
				line = br.readLine();
			}						
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			System.out.println("Most likely a problem with input values from input file");
		} finally {
			if( br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					System.out.println(ioe.getMessage());
				}
			}
			if( fr != null) {
				try {
					fr.close();
				} catch (IOException ioe) {
					System.out.println(ioe.getMessage());
				}
			}
		}
		return map;
	}
	
	public static void WriteFile(Queue<Queue<String>> possibleRoutes, StringBuilder initialDestination, String outputFilename){
		char prevDestination = ' ';
		int cost = 0;
		
		File file = new File(outputFilename);
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(file);
			pw = new PrintWriter(fw);
			pw.format("%-15s%-15s%-15s\n", "Destination", "From", "Price");
			for(Queue<String> listOfSets : possibleRoutes) {
				StringBuffer list = new StringBuffer("");
				for(String setValuesList : listOfSets){
					if(setValuesList.charAt(0) == initialDestination.charAt(0)) {
						list.append(Character.toString(setValuesList.charAt(0)) + "," + Character.toString(setValuesList.charAt(1))); 
						cost = Integer.parseInt(setValuesList.substring(2, setValuesList.length()));
						prevDestination = setValuesList.charAt(1);
						pw.format("%-15s%-15s%-15s\n", prevDestination, list, cost);
					}
					else if(prevDestination == setValuesList.charAt(0)){
						list.append("," + setValuesList.charAt(1));
						prevDestination = setValuesList.charAt(1);
						cost = Integer.parseInt(setValuesList.substring(2, setValuesList.length()));
						pw.format("%-15s%-15s%-15s\n", prevDestination, list, cost);
					}
					else {
						while(prevDestination != setValuesList.charAt(0)) {
							list.delete(list.length() - 2, list.length());
							prevDestination = list.charAt(list.length()-1);
						}
						list.append("," + setValuesList.charAt(1));
						prevDestination = setValuesList.charAt(1);
						cost = Integer.parseInt(setValuesList.substring(2, setValuesList.length()));
						pw.format("%-15s%-15s%-15s\n", prevDestination, list, cost);	
					}
				}
			}	
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} finally {
			if( pw != null) {
				try {
					pw.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if( fw != null) {
				try {
					fw.close();
				} catch (IOException ioe) {
					System.out.println(ioe.getMessage());
				}
			}
		}
	}
}