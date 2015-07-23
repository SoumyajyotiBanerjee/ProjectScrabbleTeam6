import java.util.ArrayList;


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
    
    public static String [] possibleCombinations(String word) {
        generateLetterCombinations(word, new StringBuffer(), 0);
        return allPossibleCombinations.toArray(new String[allPossibleCombinations.size()]);
    }
}