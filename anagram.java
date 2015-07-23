import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;


public class anagram {

	public static void main(String args[]) {
		BufferedReader word_reader = null;
		HashMap<String, String> word_dictionary = new HashMap<String, String>();
		 
		try {
			String sCurrentLine;
			word_reader = new BufferedReader(new FileReader("dictionary.txt"));
 
			while ((sCurrentLine = word_reader.readLine()) != null) {
				String key=sortString(sCurrentLine);
				String value = "";
				if (word_dictionary.containsKey(key)) {
					 value = word_dictionary.get(key);
					 value+=","+sCurrentLine;
				} else {
					value=sCurrentLine;
				}
				word_dictionary.put(key,value);
			}
			word_reader.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		try {
			BufferedWriter anagram_writer = new BufferedWriter(new FileWriter(new File("output.txt")));
			Map<String, String> key_sorted_dictionary = new TreeMap<String, String>(word_dictionary);
			for (Map.Entry<String, String> entry : key_sorted_dictionary.entrySet()) {
				String[] words = entry.getValue().split(",");
				if	(words.length>1) {
					anagram_writer.write(entry.getValue()+"\n");
				}
			}
			anagram_writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private static String sortString( String word )
	{
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
