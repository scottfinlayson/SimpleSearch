package test.search.util;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import main.search.model.FileBean;
import main.search.util.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {
	
	Set<FileBean> files;
	String validFile, fileDoesntExist, emptyFile; 
	
	@Before
	public void setup(){
		files = new HashSet<FileBean>();
		validFile = "exercise_data.csv";
		fileDoesntExist = "doesnt_exist.csv";
		emptyFile = "empty.csv";
	}
	
	@Test
	public void testFileReadSuccesfully(){
		try{
			files = FileUtils.parseCSVFile(validFile);
		} catch (IOException e) {
			fail("File not found or cannot be read");
		}
		assertNotSame(0, files.size());
	}
	
	@Test (expected=IOException.class)
	public void testFileCannotBeFoundException() throws IOException {
		FileUtils.parseCSVFile(fileDoesntExist);
		fail("Should have cause exception");
	}
	
	@Test (expected=IOException.class)
	public void testEmptyFile() throws IOException {
		FileUtils.parseCSVFile(fileDoesntExist);
		fail("Should have cause exception");
	}
	
}