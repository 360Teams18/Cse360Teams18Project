package Game;

/**
 * The purpose of this class is to represent the stats of a Player in 
 * the game.
 * */

public class Player {

	Dice myDice;
	int currentPos;
	int speed;
 int prevRoll;
 
 private final int FINISH_LINE = 750;
 
 	/**
 	 * This is the default constructor for a player object.*/
	public Player()
	{
		myDice = new Dice();
		currentPos = 0;
		speed = 0 ;
		prevRoll = 0;
	}

	/**
	 * This method provides the functionality of a dice roll.
	 * @return the face value of the dice roll.*/
	public int rollDice()
	{
		prevRoll = myDice.getFaceValue();
		myDice.roll();
		return myDice.getFaceValue();
	}
	
	/**
	 * This method returns the current speed of the player's airplane.
	 * @return speed the player's current speed*/
	public int getSpeed(){
		return speed;
	}
 
	/**
	 * returns the value of the previous dice roll if needed.
	 * @return prevRoll the value of the previous dice roll*/
	public int getPrev(){
		return prevRoll;
	}
	
	/**
	 * This method updates the current speed of a player.
	 * @param var the current speed update*/
	public void updateSpeed(int var){
		
		 speed = var ;
	}
	
	/**
	 * This method returns the current position of the player's plane.
	 * @return the current position of the player's plane*/
	public int getCurrentPos()
	{
		return currentPos;
	}
	
	/**
	 * Updates the current position of the player's airplane.
	 * @param position the current postion of the player's plane*/
	public void updateCurrentPos(int position)
	{
		currentPos+= position ;
	}
 
	/**
	 * Returns true if the player has won the game.
	 * @return result true if the player has won the game*/
 public boolean hasWon()
 {
    boolean result = false;
    
    if (currentPos >= FINISH_LINE)
       result = true;
       
    return result;
 }
 
 /**
  * Returns true if the player's plane can move.
  * @return result true if the player can move*/
 public boolean canMove()
 {
    boolean result = true;
    
    if (speed == 0)
       result = false;
       
    return result;
 }
}