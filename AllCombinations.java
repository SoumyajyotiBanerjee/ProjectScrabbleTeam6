import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AllCombinations {
    private static ArrayList<String> allPossibleCombinations = new ArrayList<String>();
    
    public static void generateLetterCombinations(String instr, StringBuffer outstr, int index)
    {
        for (int i = index; i < instr.length(); i++)
        {
            outstr.append(instr.charAt(i));
            allPossibleCombinations.add(outstr.toString());
            generateLetterCombinations(instr, outstr, i + 1);
            outstr.deleteCharAt(outstr.length() - 1);
        }
    } 
    
    
    public static int NumberOfSpace(String word)
    {
    	int count=0;    	
    	while (word.contains(" "))
    	{
    		word = word.replaceFirst(" ","");
    		count++;
    	}
    	
    	return count;
    }
    
    public static String [] possibleCombinations(String word) {
    	
        generateLetterCombinations(word, new StringBuffer(), 0);
               
        ArrayList<String> updatedList = new ArrayList<String>();
        
        int numberOfSpace = NumberOfSpace(word);
        for(String entry: allPossibleCombinations) {
    		updatedList.add(entry);
        }
        if(numberOfSpace==1)
        {        	
        	for(String entry: updatedList) {
        	     
            	if(entry.contains(" ")) {
            		String entryNew = entry;
            		allPossibleCombinations.remove(entry);
            		for(int i= 'a'; i<= 'z'; i++) {
            			allPossibleCombinations.add(entryNew.replace(' ', (char)i));
            		}
            	}
            }
        }
        else if(numberOfSpace==2)
        {
        	for(String entry: updatedList) {
        	     
            	if(entry.contains(" ")) {
            		String entryNew = entry;
            		allPossibleCombinations.remove(entry);            		
            		for(int i= 'a'; i<= 'z'; i++) {
            			String spaceremove = entryNew.replaceFirst(" ",Character.toString((char)i));
            			
            			for(int j='a';j<='z';j++)
            			{
            				allPossibleCombinations.add(spaceremove.replaceFirst(" ",Character.toString((char)j)));
            			}
            		}
            	}
            }
        		
        }
        
        Set<String> distictWords = new HashSet<String>();
        for (String comabination: allPossibleCombinations) {
        	distictWords.add(comabination);
        }

        return distictWords.toArray(new String[distictWords.size()]);
    }
}