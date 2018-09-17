package test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import src.FlightMap;

public class TestFlightMapInitiateSearch {

	@Test
	public void test() {
		Queue<Queue<String>> possibleRoutes = new LinkedList<Queue<String>>();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("PR", 300);
		map.put("RX", 200);
		map.put("XY", 100);
		StringBuilder routeFrom = new StringBuilder();
		routeFrom.append("P");
		possibleRoutes = FlightMap.initiateSearch(map, routeFrom);
		assertEquals(3, possibleRoutes.size());
		for(Queue<String> listOfSets : possibleRoutes) {
			for(String valuesList : listOfSets){
				if(String.valueOf(valuesList.charAt(0)).equals('R')){
					assertEquals("RX500",valuesList);
				}
				if(String.valueOf(valuesList.charAt(0)).equals('X')){
					assertEquals("XY600",valuesList);
				}
				if(String.valueOf(valuesList.charAt(0)).equals('P')){
					assertEquals("PR300",valuesList);
				}
			}
		}
	}

}