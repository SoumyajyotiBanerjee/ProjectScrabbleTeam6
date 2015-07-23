
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

public class WordScore {

	private Map<Integer, String> wordScorerMap = new HashMap<Integer, String>();
	public int alphabetWeights[] = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	
	public int[] getAlphabetWeights() {
		return alphabetWeights;
	}
	
	public int getWordScore(String word) {
		word.toLowerCase();

		int score = 0;
		for (int i = 0; i < word.length(); i++) {
			score += alphabetWeights[word.charAt(i) - 'a'];
		}

		return score;
	}

	public void readFile(String fileName) {
		BufferedReader word_reader = null;

		try {
			word_reader = new BufferedReader(new FileReader(fileName));

			String sCurrentLine;

			while ((sCurrentLine = word_reader.readLine()) != null) {
				addToMap(sCurrentLine);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void decsendingSort() {
		
		TreeMap<Integer, String> key_sorted_words = new TreeMap<Integer, String>(wordScorerMap);
		NavigableMap<Integer, String> descendingOrderMap = key_sorted_words.descendingMap();
		writeToFile(descendingOrderMap);
	
	}

	public void writeToFile(NavigableMap<Integer, String> descendingOrderMap) {
		
		try {
			BufferedWriter wordWriter = new BufferedWriter(new FileWriter(
					new File("output_words.txt")));

			for (int key : descendingOrderMap.keySet()) {
				String equalWeightWords[] = descendingOrderMap.get(key).split(" ");
				
				Arrays.sort(equalWeightWords);

				for (String w : equalWeightWords) {
					wordWriter.write(w + "\t" + key + "\n");
				}
			}
			wordWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addToMap(String word) {
		int wordScorer = getWordScore(word);
		if (wordScorerMap.containsKey(wordScorer)
				&& !wordScorerMap.get(wordScorer).contains(word)) {

			wordScorerMap.put(wordScorer, wordScorerMap.get(wordScorer) + " " + word);
		} else if (!wordScorerMap.containsKey(wordScorer)) {
			wordScorerMap.put(wordScorer, word);
		}
	}

	public static void main(String args[]) {
		WordScore wordScore = new WordScore();
		Scanner input = new Scanner(System.in);

		System.out.println("Enter FileName :");
		String fileName = input.nextLine();

		wordScore.readFile(fileName);
		wordScore.decsendingSort();
	}

}

class WordScorePair
{
	String listofAnagram;
	int score;
	
	WordScorePair()
	{
		listofAnagram=null;
		score = -1;
	}
}
