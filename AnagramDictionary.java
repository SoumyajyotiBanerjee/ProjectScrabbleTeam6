import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AnagramDictionary {

		private Map<String, String> anagramMap = new HashMap<String, String>();

		public String sortString(String original) {
			char[] chars = original.toCharArray();
			Arrays.sort(chars);
			String sorted = new String(chars);
			return sorted;
		}

		public void addToHash(String word) {
			String sortedWord = sortString(word);
			if (anagramMap.containsKey(sortedWord)) {
				anagramMap.put(sortedWord, anagramMap.get(sortedWord) + " " + word);
			} else {
				anagramMap.put(sortedWord, word);
			}
		}

		public void createMapFromFile() {
			readFile("dictionary.txt");
		}

		public void readFile(String path) {
			BufferedReader br = null;
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader(path));
				while ((sCurrentLine = br.readLine()) != null) {
					this.addToHash(sCurrentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
	
		public WordScorePair getAnagrams(String key) {
			
			key = sortString(key);
			String wordsAndScore = anagramMap.get(key);
			WordScorePair wordScorePair = new WordScorePair();
			if(wordsAndScore!=null)
			{
				wordScorePair = new WordScorePair();
				WordScore obj = new WordScore();
				wordScorePair.score = obj.getWordScore(key);
				wordScorePair.listofAnagram=wordsAndScore;
			}
			
			return wordScorePair;
		}
}
