import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class AnagramsAndScore
{
	String listofAnagram;
	int score;
	
	AnagramsAndScore()
	{
		listofAnagram=null;
		score = -1;
	}
}
class Anagrams {

	private Map<String, String> map = new HashMap<String, String>();

	public String sortString(String original) {
		char[] chars = original.toCharArray();
		Arrays.sort(chars);
		String sorted = new String(chars);
		return sorted;
	}

	public void addToHash(String word) {
		String sortedWord = sortString(word);
		if (map.containsKey(sortedWord)) {
			map.put(sortedWord, map.get(sortedWord) + " " + word);
		} else {
			map.put(sortedWord, word);
		}
	}

	public void createMapFromFile() {

		readFile("dictionary.txt");
		//System.out.println(map);

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
	


	public AnagramsAndScore getAnagramsOf(String key) {
		
		key = sortString(key);
		String wordsAndScore = map.get(key);
		AnagramsAndScore entity = new AnagramsAndScore();
		if(wordsAndScore!=null)
		{
			entity = new AnagramsAndScore();
			wordScorer obj = new wordScorer();
			entity.score = obj.getWordScore(key);
			entity.listofAnagram=wordsAndScore;
		}
		
		return entity;
	}

}

class recommendWords
{
	
	public Map<Integer, String> recommendation(String wordInput)
	{
		Anagrams anagramObject = new Anagrams();
		anagramObject.createMapFromFile();
		AllCombinations allCoombination = new AllCombinations();
		String [] allWords = allCoombination.possibleCombinations(wordInput);
		Map<Integer,String> MapWords = new HashMap<Integer,String>();
		AnagramsAndScore result;
		int maxScore=0;
		String highestScoringWords=null;
		
		String keyString="";
		
		for (String words: allWords) {

			if(!keyString.contains(anagramObject.sortString(words)))
			{
				result=anagramObject.getAnagramsOf(words);
				if(result.listofAnagram!=null)
				{
					if(MapWords.containsKey(result.score))
					{
						MapWords.put(result.score, result.listofAnagram+" "+MapWords.get(result.score));
					}
					else
					{
						MapWords.put(result.score, result.listofAnagram);
					}
					
				}
			}
			
		}
		
		return MapWords;
		
	}
	
}
public class Scrabble {

	public static void main(String args[]) {

		Scanner input = new Scanner(System.in);
		
		System.out.print("GIVE INPUT (7 Letters at Max");
		String wordInput = input.next();
		recommendWords getWords = new recommendWords();
		Map<Integer, String> SortedWords = new TreeMap<Integer, String>(getWords.recommendation(wordInput));
		
		for (Map.Entry<Integer, String> entry : SortedWords.entrySet()) {
		
			System.out.println(">> "+entry.getValue()+" "+entry.getKey());
		}
		
		
	}
}
