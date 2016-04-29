package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class AirplanePanel extends JPanel
{
   private static final int BASE_SPEED = 40;
   private JButton oneButton;
   private JButton twoButton;
   private JButton statButton;
   private Polygon playOne;
   private Polygon playTwo;
   private int[] xPlane = {30, 30, 45, 40, 55, 60, 120, 130, 120, 60, 55, 40, 45};
   private int[] yPlaneOne = {80, 95, 95, 130, 130, 95, 95, 87, 80, 80, 45, 45, 80};
   private int[] yPlaneTwo = {230, 245, 245, 280, 280, 245, 245, 237, 230, 230, 195, 195, 230};
   private Player playerOne;
   private Player playerTwo;
   private JLabel turn;
   private JLabel rollResult;
   
   private String content;
   private JTextArea results;  
   private JFrame texter;
   private JFrame statEntry;
   private JTextField field;
   private JLabel record;
   private String nameEntry;
   private String currentChamp;
   
   private int winsSoFarOne;
   private int winsSoFarTwo;
   
   /**
    * This is the constructor for an AirplanePanel object.
    * @param stats the read in string from the text file
    * @param personName the name of the current champion
    * @param playerOneWins the number of wins by playerOne
    * @param playerTwoWins the number of wins by playerTwo*/
   public AirplanePanel(String stats, String personName, int playerOneWins, int playerTwoWins)
   {  
      oneButton = new JButton("Player one roll");
      twoButton = new JButton("Player two roll");
      statButton = new JButton("Record/View statistics");
      turn = new JLabel("Player one's turn.");
      rollResult = new JLabel("You rolled: " + 0);
      
      ButtonListener tool = new ButtonListener();
      StatListener pen = new StatListener();
      
      results = new JTextArea(20, 30);
      texter = new JFrame("Statistics");
      
      statEntry = new JFrame("Enter The Winner's Name and press enter.");
      field = new JTextField(20);
      record = new JLabel("Enter the winner's name! \n Press enter when done.");
      nameEntry = "";
      
      oneButton.addActionListener(tool);
      twoButton.addActionListener(tool);
      statButton.addActionListener(tool);
      field.addActionListener(pen);
      
      add(turn);
      add(oneButton);
      add(twoButton);
      add(statButton);
      add(rollResult);
      
      playOne = new Polygon(xPlane, yPlaneOne, 13);
      playTwo = new Polygon(xPlane, yPlaneTwo, 13);
      
      playerOne = new Player();
      playerTwo = new Player();
      
      
      setBackground(Color.white);
      setPreferredSize(new Dimension(800, 400));
      
      content = stats;
      currentChamp = personName;
      winsSoFarOne = playerOneWins;
      winsSoFarTwo = playerTwoWins;
   }
   
   /**
    * This class is an action listener for the buttons.*/
   private class ButtonListener implements ActionListener
   {
	   /**This method listens for when a button is hit during the game.
	    * */
      public void actionPerformed(ActionEvent event) 
      {
         int rollVal = 0;
         int newVal = 0;
            
         
         if (event.getSource() == oneButton)
         {
            rollVal = playerOne.rollDice();
            rollResult.setText("You rolled: " + rollVal);
            turn.setText("Player two's turn.");
            
            if (rollVal % 3 == 0 && !playerOne.canMove())   //Rule # 1
               playerOne.updateSpeed(1);
            else if (rollVal == playerOne.getPrev())  //Rule # 2
            {
               newVal = playerOne.getSpeed() * 2;
               playerOne.updateSpeed(newVal);
            }
            else if (rollVal == 1 || rollVal == 20)   //Rule # 3
               playerOne.updateSpeed(0);
            
            playerOne.updateCurrentPos(BASE_SPEED * playerOne.getSpeed());
            playOne.translate(BASE_SPEED * playerOne.getSpeed(), 0);
         }
         else if(event.getSource() == twoButton)
         {
            rollVal = playerTwo.rollDice();
            rollResult.setText("You rolled: " + rollVal);
            turn.setText("Player one's turn.");
            
            if (rollVal % 3 == 0 && !playerTwo.canMove())   //Rule # 1
               playerTwo.updateSpeed(1);
            else if (rollVal == playerTwo.getPrev())  //Rule # 2
            {
               newVal = playerTwo.getSpeed() * 2;
               playerTwo.updateSpeed(newVal);
            }
            else if (rollVal == 1 || rollVal == 20)   //Rule # 3
               playerTwo.updateSpeed(0);
            
            playerTwo.updateCurrentPos(BASE_SPEED * playerTwo.getSpeed());
            playTwo.translate(BASE_SPEED * playerTwo.getSpeed(), 0);
         }
         else if (event.getSource() == statButton)
         {
        	 //Text display screen.
        	   results.setText(content);
        	   texter.getContentPane().add(results);
        	   texter.pack();
        	   texter.setVisible(true);
        	   
            //Name Entry screen.
        	   statEntry.getContentPane().add(record);
        	   statEntry.getContentPane().add(field);
        	   statEntry.pack();
        	   statEntry.setVisible(true);
         }

         repaint();
      }
   }
   
   /**
    * This class is a listener for the text field box.*/
   private class StatListener implements ActionListener
   {
	   /**This method listens for when a name is entered into the text field.*/
      public void actionPerformed(ActionEvent event)
      {
    	  currentChamp = field.getText();
    	  PrintWriter output = null;
    	  
          if (playerOne.hasWon() || playerTwo.hasWon())
          {
    		  	try
    		  	{
    		  		output = new PrintWriter("stat.txt");
    		  	}
    		  	catch (FileNotFoundException error)
    		  	{
    		  		System.out.println("Error the file stat.txt was not found.");
    		  	}
    		  	
    		  	if(playerOne.hasWon())
    		  		winsSoFarOne++;
    		  	else if(playerTwo.hasWon())
    		  		winsSoFarTwo++;
    		  	
    		  	output.println("Player 1 wins " + winsSoFarOne);
    		  	output.println("Player 2 wins " + winsSoFarTwo);
    		  	output.print(currentChamp);
    		  	output.println(" is the current champion.");
    		  	
    		  	output.close();
          }
      }
   }
   
   /**
    * This method paints and repaints the airplanes when needed.*/
   public void paintComponent(Graphics page)
   {
      super.paintComponent(page);
      
      page.setColor(Color.blue);
      page.drawPolygon(playOne);
      
      page.setColor(Color.red);
      page.drawPolygon(playTwo);
      
      page.setColor(Color.yellow);
      page.drawPolygon(new int[]{750, 750}, new int[]{20, 380}, 2);
      
      page.setColor(Color.black);
      
      if (playerOne.hasWon())
         page.drawString("Player one wins!!!!!", 400, 200);
      else if (playerTwo.hasWon())
         page.drawString("Player two wins!!!!!", 400, 200);
      
      if(playerOne.hasWon() || playerTwo.hasWon())
      {
    	  page.drawString("Please enter the winner's name using the statistics button." , 400, 250);
    	  page.drawString("Be sure to hit enter when done.", 400, 300);
    	  page.drawString("Results reflected in next game.", 400, 350);
      }
   } 
}
