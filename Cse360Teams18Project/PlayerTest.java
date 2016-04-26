package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player playOne = new Player();
		
		assertNotNull(playOne);
	}
	
	@Test
	public void testRollDice()
	{
		Player tool = new Player();
		assertNotEquals(1, tool.rollDice());
	}
	
	@Test
	public void testGetSpeed()
	{
		Player tool = new Player();
		assertEquals(0, tool.getSpeed());
	}
	
	@Test
	public void testGetPrev()
	{
		Player tool = new Player();
		assertEquals(0, tool.getPrev());
	}
	
	@Test
	public void testUpdateSpeed()
	{
		Player plane = new Player();
		plane.updateSpeed(10);
		assertEquals(10, plane.getSpeed());
	}
	
	@Test
	public void testGetCurrentPos()
	{
		Player tool = new Player();
		assertEquals(0, tool.getCurrentPos());
	}
	
	@Test
	public void testUpdateCurrentPos()
	{
		Player tool = new Player();
		tool.updateCurrentPos(50);
		assertEquals(50, tool.getCurrentPos());
	}
	
	@Test
	public void testHasWon()
	{
		Player tester = new Player();
		assertEquals(false, tester.hasWon());
		tester.updateCurrentPos(800);
		assertEquals(true, tester.hasWon());
	}
	
	@Test
	public void testCanMove()
	{
		Player tool = new Player();
		assertEquals(false, tool.canMove());
		tool.updateSpeed(1);
		assertEquals(true, tool.canMove());
	}
}
