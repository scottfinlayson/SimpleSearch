package main.search.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.search.constant.Global;
import main.search.model.FileBean;

public class FileUtils {
	public static Set<FileBean> parseCSVFile(String filepath) throws IOException {
		FileReader reader;
		Set<FileBean> files = new HashSet<FileBean>();
		
		reader = new FileReader(filepath);
		BufferedReader in = new BufferedReader(reader);
		
		String line = in.readLine();
		while(line != null){
			String[] cells = line.split(Global.CSV_FILE_DELIMITER);
			int length = cells.length;
			
			if(length >= 3) {
				String body = "";
				
				for(int i = 2; i < length; i++) {
					body = combine(body, cells[i]);
				}
				
				FileBean fbean = new FileBean(cells[0], cells[1], body);
				files.add(fbean);
				line = in.readLine();
			}
		}
		in.close();
	
		return files;
	}
	
	private static String combine(String s1, String s2) {
		if (!s1.isEmpty()) {
			return s1 + "," + s2;
		} else {
			return s2;
		}
	}
}



















