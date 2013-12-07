package test.search.model;

import static org.junit.Assert.*;
import main.search.model.FileBean;
import org.junit.Before;
import org.junit.Test;

public class FileBeanTest {
	
	
	private FileBean fb1, fb2, fb3;
	private String id;
	private String title;
	private String body;
	
	
	
	@Before
	public void setup(){
		this.id = "93824ui3r23r2i3jdn2b4oi32dni";
		this.title = "BT Sport Deal";
		this.body = "BT Sport deal for the champions league has caught every by surprise and now BT has a serious offering that BSkyB will need to take seriously.";
		
		fb1 = new FileBean(id, title, body);
		
		String idNull = null, titleNull = null, bodyNull = null;
		fb2 = new FileBean(idNull, titleNull, bodyNull);
		
		String id = "", title = "", body = "";
		fb3 = new FileBean(id, title, body);
	}
	
	@Test
	public void testCreateFileBean(){
		assertNotNull(fb1);
	}

	@Test
	public void testCreateFileBeanWithNullValues(){
		assertNotNull(fb2);
	}
	
	@Test
	public void testGetId(){
		assertTrue(fb1.getId().equalsIgnoreCase(id));
	}
	
	@Test
	public void testIdValuesNotEqual(){
		assertFalse(fb1.getId().equalsIgnoreCase(fb2.getId()));
	}
	
	@Test
	public void testFileContainsTerm(){
		String term = "BT";
		assertTrue(fb1.contains(term));
	}
	
	@Test
	public void testFileDoesNotContainTerm(){
		String term = "Circus";
		assertFalse(fb1.contains(term));
	}
	
	@Test
	public void testNullFileDoesNotContainTerm(){
		String term = "Circus";
		assertFalse(fb2.contains(term));
	}
	
	@Test
	public void testEmptyFileDoesNotContainTerm(){
		String term = "Circus";
		assertFalse(fb3.contains(term));
	}
	
	@Test
	public void testFrequencyReportedCorrectlyForExistingWord(){
		assertEquals(3, fb1.getFrequency("BT"));
	}
	
	@Test
	public void testFrequencyReportedCorrectlyForNonExistingWord(){
		assertEquals(0, fb1.getFrequency("circus"));
	}
	
	@Test
	public void testEmptyFileParsing(){
		String id = "", title = "", body = "";
		FileBean fb4 = new FileBean(id, title, body);
	}
}
