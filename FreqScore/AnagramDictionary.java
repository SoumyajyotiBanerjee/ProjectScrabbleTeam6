package FreqScore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AnagramDictionary {

		private Map<String, String> map = new HashMap<String, String>();

		public String sortAlphabets(String originalWord) {
			char[] chars = originalWord.toCharArray();
			Arrays.sort(chars);
			String sortedWord = new String(chars);
			return sortedWord;
		}

		public void addToHash(String word) {
			String sortedWord = sortAlphabets(word);
			if (map.containsKey(sortedWord)) {
				map.put(sortedWord, map.get(sortedWord) + " " + word);
			} else {
				map.put(sortedWord, word);
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


}
