package main.search.model;

import java.util.HashMap;
import java.util.Map;

import main.search.constant.Global;

public class FileBean {
	
	private String id;
	String[] title, body;
	private Map<String, Integer> terms;
	
	public FileBean(String id, String title, String body){
		this.id = id;
		this.title = parseText(title);
		this.body = parseText(body);
		terms = new HashMap<String, Integer>();
		//System.out.println("File indexing started: " + id);
		index();
		//System.out.println("File indexing finished: " + id);
	}
	
	private String[] parseText(String text){
		String[] words;
		if (text != null || "".equals(text)) {
			words = text.split(Global.TEXT_DELIMITER);
		} else {
			words = new String[]{""};
		}
		return words;
	}
	
	public String getId(){ return id; }
	
	public int getFrequency(String term){
		Integer frequency = terms.get(term.toLowerCase());
		if (frequency == null) {
			return 0;
		} else {
			return frequency;
		}
	}
	
	
	private void index() {
		indexContents(title);
		indexContents(body);
	}
	
	public boolean contains(String term) {
		return terms.containsKey(term.toLowerCase());
	}
	
	private void indexContents(String[] contents) {
		for (int i = 0; i < contents.length; i++) {
			String word = contents[i].toLowerCase();
			if (!terms.containsKey(word)) {
				//term doesn't exist yet
				terms.put(word, 1);
				//System.out.println("Adding new word [" + word + "]:[1]");
			} else {
				//add to existing term count
				int value = terms.get(word);
				terms.put(word, value + 1);
				//System.out.println("Adding word [" + word + "]:[" + value + 1 + "]");
			}
		}
	}
}