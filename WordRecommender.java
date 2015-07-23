import java.util.HashMap;
import java.util.Map;


public class WordRecommender {
		
		public Map<Integer, String> Recommendation(String wordInput)
		{
			AnagramDictionary anagramDictionary = new AnagramDictionary();
			anagramDictionary.createMapFromFile();
			String [] allWords = AllCombinations.possibleCombinations(wordInput);
			Map<Integer,String> mapWords = new HashMap<Integer,String>();
			WordScorePair result;
			String keyString = "";
			
			for (String word: allWords) {
				
				result = anagramDictionary.getAnagrams(word);
				if (!keyString.contains(anagramDictionary.sortString(word)) && result.listofAnagram!=null)
				{
					if (mapWords.containsKey(result.score))
					{
						mapWords.put(result.score, result.listofAnagram+ " " +mapWords.get(result.score));
					}
					else
					{
						mapWords.put(result.score, result.listofAnagram);
					}
						
				}
				
			}
			
			return mapWords;
			
		}
		
	
}
