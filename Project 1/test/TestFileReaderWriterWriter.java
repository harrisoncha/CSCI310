package test;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import src.FileReaderWriter;

public class TestFileReaderWriterWriter {

	@Test
	public void test() {
		Queue<Queue<String>> possibleRoutes = new LinkedList<Queue<String>>();
		Queue<String> flightList = new LinkedList <String>();
		flightList.add("PR300");
		flightList.add("RX500");
		flightList.add("XY600");
		possibleRoutes.add(flightList);
		String outputFilename = "output.txt";
		StringBuilder initialDestination = new StringBuilder();
		initialDestination.append("P");
		FileReaderWriter.WriteFile(possibleRoutes, initialDestination, outputFilename);
	}
}
