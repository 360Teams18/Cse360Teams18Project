package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void testDice() {
		Dice obj = new Dice();
		assertNotNull(obj);
	}
	
	@Test
	public void testGetFaceValue()
	{
		Dice obj = new Dice();
		assertEquals(1, obj.getFaceValue());
	}
	
	@Test
	public void testRoll()
	{
		Dice obj = new Dice();
		obj.roll();
		assertNotEquals(1, obj.getFaceValue());
	}

}