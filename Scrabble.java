import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Scrabble {
		
	public static boolean containsWord(String sameWeightWords, String comparionWord){
		
		boolean result=false;
		
		if(sameWeightWords!=null)
		{
			String [] words = sameWeightWords.split(" ");
			
			for(String word: words) {
				if (comparionWord.trim().equals(word.trim())) {
					return true;
				}
			}
		}
		return result;
		
	}
	
	public static int updatedScore(String word, String rackAlphabets) {
		char [] presentLetters = rackAlphabets.toCharArray();
		int [] alphabetWeights = new WordScore().getAlphabetWeights();
		int score = 0;
		for (int i=0; i< presentLetters.length; i++) {
			if (presentLetters[i]!=' ' && word.contains(Character.toString(presentLetters[i]))) {
				score += alphabetWeights[presentLetters[i]-'a'];
			}
		}
		return score;
	}
	
	public static void writeToFile(String fileName, Map<Integer, String> sortedWords) {

		try {
			BufferedWriter recommendation_writer = new BufferedWriter(new FileWriter(new File(fileName)));
			
			for (Map.Entry<Integer, String> entry : sortedWords.entrySet()) {
				System.out.println(">> "+entry.getKey()+" "+entry.getValue());
				recommendation_writer.write(entry.getKey()+" - "+entry.getValue()+"\n");
			}
			recommendation_writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	public static void main(String args[]) {
		
		Scanner input = new Scanner(System.in);		
		System.out.print("GIVE INPUT (7 Letters at Max) \n");
		String rackWords = input.nextLine();
		System.out.print("GIVE Output File Name\n");
		String outputFile = input.nextLine();
		Map<Integer, String> updatedWordScore = new HashMap<Integer, String>();
		WordRecommender wordRecommender = new WordRecommender();
		Map<Integer, String> sortedWords = new TreeMap<Integer, String>(wordRecommender.Recommendation(rackWords));
		
		if(rackWords.contains(" ")) {
			
			for(String line: sortedWords.values()) {
				String [] words = line.split(" ");
				
				for(String word: words) {
					
					int finalScore = updatedScore(word.trim(), rackWords.trim());
					
					if (updatedWordScore.containsKey(finalScore)) {
						if(!containsWord(updatedWordScore.get(finalScore),word))
							updatedWordScore.put(finalScore, (updatedWordScore.get(finalScore) +" "+ word));
						
					} else {
						updatedWordScore.put(finalScore, word);
						
					}
					
				}
			}
			sortedWords = new TreeMap<Integer, String>(updatedWordScore);
		}
		
		writeToFile(outputFile, sortedWords);
	}
}
