package Game;

public class Player {

	Dice myDice;
	int currentPos;
	int speed;
   int prevRoll;
   
   private final int FINISH_LINE = 750;
   
	public Player()
	{
		myDice = new Dice();
		currentPos = 0;
		speed = 0 ;
		prevRoll = 0;
	}

	public int rollDice()
	{
		prevRoll = myDice.getFaceValue();
		myDice.roll();
		return myDice.getFaceValue();
	}
	
	public int getSpeed(){
		return speed;
	}
   
   public int getPrev(){
      return prevRoll;
   }
	
	public void updateSpeed(int var){
		
		 speed = var ;
	}
	
	public int getCurrentPos()
	{
		return currentPos;
	}
	
	public void updateCurrentPos(int position)
	{
		currentPos+= position ;
	}
   
   public boolean hasWon()
   {
      boolean result = false;
      
      if (currentPos >= FINISH_LINE)
         result = true;
         
      return result;
   }
   
   public boolean canMove()
   {
      boolean result = true;
      
      if (speed == 0)
         result = false;
         
      return result;
   }
}