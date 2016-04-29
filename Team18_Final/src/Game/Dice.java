package Game;

/**
 * The purpose of this class is to represent the functionality of a dice.
 * */

import java.util.Random;

public class Dice {
	int faceValue;
	Random generator;
	
	/**
	 * This is the default constructor of a dice object.*/
	public Dice()
	{
		faceValue = 1;
		generator = new Random();
	}
	
	/**
	 * Returns the current face value of a dice object.
	 * @return faceValue the current face value of a dice*/
	public int getFaceValue()
	{
		return faceValue;
	}
	
	/**
	 * This method simulates a dice roll with a random number.*/
	public void roll()
	{
		faceValue = generator.nextInt(20) + 1;
	}
}