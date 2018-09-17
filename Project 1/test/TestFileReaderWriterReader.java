package test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import src.FileReaderWriter;

public class TestFileReaderWriterReader {

	@Test
	public void test() {
		StringBuilder initialDestination = new StringBuilder();
		HashMap<String, Integer> map = new HashMap<>();
		map = FileReaderWriter.ReadFile("test.txt", initialDestination);
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			assertEquals("PR",entry.getKey());//only one entry in entire map
		}
	}

}