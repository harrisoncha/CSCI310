package src;
import java.util.HashMap;
import java.util.Queue;

public class SearchMap {
	
	public static void main(String [] args) {
		//retrieves your initial destination
		StringBuilder initialDestination = new StringBuilder ();
		//will store all possible routes and associated costs
		HashMap<String, Integer> map = new HashMap<>(); 
		
		//populate hashmap with data of flight routes
		map = FileReaderWriter.ReadFile(args[0], initialDestination);
		System.out.println(args[0]);
		//start the search and retrieve results
		Queue<Queue<String>> possibleRoutes = FlightMap.initiateSearch(map, initialDestination);
		
		//output results to output file
		FileReaderWriter.WriteFile(possibleRoutes, initialDestination, args[1]);
	}
	
}