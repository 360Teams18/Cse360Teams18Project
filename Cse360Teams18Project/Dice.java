package Game;
import java.util.Random;

public class Dice {
	int faceValue;
	Random generator;
	
	public Dice()
	{
		faceValue = 1;
		generator = new Random();
	}
	
	public int getFaceValue()
	{
		return faceValue;
	}
	
	public void roll()
	{
		faceValue = generator.nextInt(20) + 1;
	}
}