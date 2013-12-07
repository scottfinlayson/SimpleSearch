package main.search.processor;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.search.model.FileBean;
import main.search.util.FileUtils;

public class Search {
	
	private Set<FileBean> files;
	private Map<String, Double> idfs, ratings;
	
	public Search(String filename, String[] searchTerms) throws IOException {
		files = new HashSet<FileBean>();
		idfs = new HashMap<String, Double>();
		ratings = new HashMap<String, Double>();
		
		readFile(filename);
		calculateIDF(searchTerms);
		setRatings();
	}
	
	private void readFile(String filename) throws IOException {
		System.out.println("Reading file: " + filename);
		files = FileUtils.parseCSVFile(filename);
		System.out.println("Contents of file have been loaded");
	}
	
	private void calculateIDF(String[] searchTerms) {
		for (String term : searchTerms) {
			int count = 0;
			for (FileBean file : files) {
				if (file.contains(term)) {
					count++;
				}
			}
			int numberOfDocs = files.size();
			double log = (double) Math.log(numberOfDocs/(count+1) ) + 1;
			idfs.put(term, log);
		}
	}
	
	public void printResults(){
		System.out.println("Search Results");
		System.out.println("============================================");
		
		Set<String> files = ratings.keySet();
		for (String fileId : files) {
			double rating = ratings.get(fileId);
			System.out.println("File [" + fileId + "] achieved a rating of: " + rating);
		}
		
		
		System.out.println("============================================");
	}
	
	private void setRatings() {
		Set<String> terms = idfs.keySet();
		for (String term : terms) {
			setRating(term);
		}
	}
	
	private void setRating(String term) {
		double idf = idfs.get(term);
		for(FileBean file: files) {
			int frequency = file.getFrequency(term);
			double rating = frequency * idf;
			
			Double score = ratings.get(file.getId());
			if (score == null) {
				ratings.put(file.getId(), rating);
			} else {
				ratings.put(file.getId(), rating + score);
			}
		}
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		int length = args.length;
		
		if (length < 2){
			System.out.println("You have not entered enough arguments.");
			return;
		}
		
		Search search = new Search(args[0], Arrays.copyOfRange(args, 1, length));
		search.printResults();
	}
}
