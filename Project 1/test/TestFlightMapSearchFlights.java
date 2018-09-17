package test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import src.FlightMap;

public class TestFlightMapSearchFlights {

	@Test
	public void test() {
		Queue<String> flightList = new LinkedList<String>();
		HashMap<String, Integer> hashmap = new HashMap<>();
		hashmap.put("RX", 200);
		hashmap.put("XY", 100);
		String key = "PR";
		int prevCost = 300;
		char initialDestination = 'P';
		flightList = FlightMap.searchFlights(flightList, hashmap, key, prevCost, initialDestination);
		for(String valuesList : flightList){ //only two entries possible
			if(String.valueOf(valuesList.charAt(0)).equals('R')){
				assertEquals("RX500",valuesList);
			}
			if(String.valueOf(valuesList.charAt(0)).equals('X')){
				assertEquals("XY600",valuesList);
			}
		}
	}
	//RX is a possible destination to PR and therefore should be added to the queue by the searchFlights function
}	//indicates that RX and XY have been added into the queue since all destinations can be reached from PR 

