package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AirplanePanel extends JPanel
{
   private static final int BASE_SPEED = 40;
   private JButton oneButton;
   private JButton twoButton;
   private Polygon playOne;
   private Polygon playTwo;
   private int[] xPlane = {30, 30, 45, 40, 55, 60, 120, 130, 120, 60, 55, 40, 45};
   private int[] yPlaneOne = {80, 95, 95, 130, 130, 95, 95, 87, 80, 80, 45, 45, 80};
   private int[] yPlaneTwo = {230, 245, 245, 280, 280, 245, 245, 237, 230, 230, 195, 195, 230};
   private Player playerOne;
   private Player playerTwo;
   private JLabel turn;
   private JLabel rollResult;
   private boolean winner;
   
   public AirplanePanel()
   {  
      oneButton = new JButton("Player one roll");
      twoButton = new JButton("Player two roll");
      turn = new JLabel("Player one's turn.");
      rollResult = new JLabel("You rolled: " + 0);
      
      ButtonListener tool = new ButtonListener();
      
      oneButton.addActionListener(tool);
      twoButton.addActionListener(tool);
      
      add(turn);
      add(oneButton);
      add(twoButton);
      add(rollResult);
      
      playOne = new Polygon(xPlane, yPlaneOne, 13);
      playTwo = new Polygon(xPlane, yPlaneTwo, 13);
      
      playerOne = new Player();
      playerTwo = new Player();
      
      
      setBackground(Color.white);
      setPreferredSize(new Dimension(800, 400));
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         int rollVal = 0;
         int newVal = 0;
         
         if (playerOne.hasWon() || playerTwo.hasWon())
            System.exit(0);
            
         
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
         else
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

         repaint();
      }
   }
   
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
   }
}