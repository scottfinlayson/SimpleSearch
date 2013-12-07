package test.search.processor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import main.search.processor.Search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SearchTest {
	
	Search s1;
	ByteArrayOutputStream outContent;
	
	@Before
	public void setup() throws IOException {
		s1 = new Search("exercise_data.csv", new String[]{"BT"});
		//s2 = new Search("", new String[]{"BT"});
		
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void testCreateSearch() throws IOException {
		s1 = new Search("exercise_data.csv", new String[]{"BT"});
		assertNotNull(s1);
	}
	
	@Test (expected=IOException.class)
	public void testSearchFailsWithNoFile() throws IOException {
		new Search("", new String[]{"BT"});
		fail("Should have thrown IO exception");
	}
	
	@Test
	public void testMainSearchWithNoSearchTerms() throws IOException {
		Search.main(new String[]{"Filename"});
		assertTrue(outContent.toString().contains("You have not entered enough arguments."));
	}
	
	@Test
	public void testMainSearchWithSearchTermsThatDoesntExist() throws IOException {
		Search s = new Search("exercise_data.csv", new String[]{"CIRCUS"});
		s.printResults();
		assertTrue(outContent.toString().contains("File [\"34e29d6a1e1c49ab213700aa0b7807311350a1ff\"] achieved a rating of: 0"));
	}
}
