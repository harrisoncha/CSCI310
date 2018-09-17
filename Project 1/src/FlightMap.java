import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class FlightMap {
	public static Queue<Queue<String>> initiateSearch(HashMap<String,Integer> map, StringBuilder routeFrom){
		Queue<Queue<String>> possibleRoutes = new LinkedList<Queue<String>>();
		char initialDestination = routeFrom.charAt(0);
		
		//find all the places that start at the root destination.
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			
			//Keeps track of the unseen routes, ONLY serves to exclude initalDestination routes. i.e if initialDestination = 'P', "PW" is eliminated when found
			HashMap<String, Integer> notSeenYet = new HashMap<>();
			
			//keep tracks of possible routes from this initialDestination
			Queue<String> flightList = new LinkedList<String>();
		
			//if you find a possible route from the initial destination, add it to the flight list, and check all possibilities from there.
			if(entry.getKey().charAt(0) == initialDestination) {
				String costString = Integer.toString(entry.getValue());
				flightList.add(entry.getKey()+costString);
				for(Map.Entry<String, Integer> entry2 : map.entrySet()) {
					if(!entry2.getKey().equals(entry.getKey())){
						notSeenYet.put(entry2.getKey(), map.get(entry2.getKey()));
					}
				}	
				flightList = searchFlights(flightList, notSeenYet, entry.getKey(), entry.getValue(), initialDestination);
			}
			// possible routes found from a particular initial destination route is added
			possibleRoutes.add(flightList);
		}			
		return possibleRoutes;
	}
	
	public static Queue<String> searchFlights(Queue<String> flightList, HashMap<String,Integer> hashmap, String key, int prevCost, char initialDestination) {
		char leavingFrom = key.charAt(1);
		
		for(Map.Entry<String, Integer> entry : hashmap.entrySet()) {			
			char arrivalTo = entry.getKey().charAt(0);	
			if(leavingFrom == arrivalTo &&  arrivalTo != initialDestination) {
				//get the total cost of the ticket
				int totalCost = prevCost + entry.getValue();
				
				//add to and from location, plus flight ticket for route to hashmap -> first two entries are letters related to destinations and the rest of the string is related to ticket price
				flightList.add(entry.getKey()+totalCost);
				
				//create a hashmap to keep track of places you have not visited, updated every time you 'travel' somewhere new
				HashMap<String, Integer> notSeenYetInLoop = new HashMap<>();
				for(Map.Entry<String, Integer> entry2 : hashmap.entrySet()) {
					if(!entry2.getKey().equals(entry.getKey())){
						notSeenYetInLoop.put(entry2.getKey(), hashmap.get(entry2.getKey()));
					}
				}
				//look for the next possible arrival destination, if available
				searchFlights(flightList, notSeenYetInLoop, entry.getKey(), totalCost, initialDestination);	
			}
		}
		return flightList;
	}
}
