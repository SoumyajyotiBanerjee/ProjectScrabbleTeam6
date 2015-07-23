package FreqScore;
import java.util.ArrayList;
import java.util.HashMap;


class Odometer
{
	ArrayList<String> Sequence;
	int NumberOfdigits;
	
	
	Odometer(int NumberOfdigits)
	{
		this.NumberOfdigits = NumberOfdigits;
		Sequence = new ArrayList<String>();
		this.generateSequence();
	}
	
	private String arrayToString(int number []) {
		StringBuilder numString = new StringBuilder();
		for( int num: number) {
			numString.append(num);
		}
		return numString.toString();
		
	}
	
	
	private void generateSequence()
	{
		int Number[] = new int[NumberOfdigits];
		generateSequenceRec(0, Number);
	}
	
	private void generateSequenceRec(int index, int Number[]) {
		
		if (index == NumberOfdigits) {
			Sequence.add(arrayToString(Number));
			return;
		}
		
		int start = (index==0)? 1:Number[index-1]+1;
		int end = (10-NumberOfdigits)+index;
		
		for(int i=start; i<=end; i++)
		{
			Number[index] = i;
			generateSequenceRec(index+1,Number);
		}
	}
	
	String getNextNumber(String number) {
		int index = Sequence.indexOf(number);
		return Sequence.get( (index+1) % Sequence.size());
	}
	
	String getPrevNumber(String number) {
		int index = Sequence.indexOf(number);
		return Sequence.get( (index-1) % Sequence.size());
	}
	
	int getDistance(String number1,String number2) {
		int index1 = Sequence.indexOf(number1);
		int index2 = Sequence.indexOf(number2);
		return (index1-index2)>0?(index1-index2):(Sequence.size()-(index1-index2));
	}
	
	
}
public class OdometerMain {

	private static final int NO_OF_DIGITS = 3;

	public static void main(String args[])
	{
		Odometer Oro_Object = new Odometer(NO_OF_DIGITS);
		
		String NextNumber = Oro_Object.getNextNumber("234");
		String PrevNumber = Oro_Object.getPrevNumber("456");
		int distance = Oro_Object.getDistance("234","237");
		
		System.out.println("Next >> "+NextNumber+" Prev >>"+PrevNumber+" Distance between 234 and 237 >> "+distance);
	}
}
