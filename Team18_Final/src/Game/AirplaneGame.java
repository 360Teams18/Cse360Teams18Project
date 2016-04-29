/** IMPORTANT NOTE: In order to run properly the text file 'stat.txt' must be included
 * in the proper location.
*/

package Game;

import javax.swing.JFrame;
import java.io.*;
import java.util.*;

/**
* How to play: 
* Player one hits their button first to roll the dice and see if they can move. 
* If they can their airplane moves.
* Player two then hits their button to do the same.
* The players alternate turns hitting their button until one player crosses the finishline.
* When done the winner enters their name using the record statistic button.
* Making sure to hit enter when done. The results will be reflected next game play.
*/

public class AirplaneGame
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Airplane Race");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Scanner obj = null;
      String hold = "";
      String dataBase = "";
      String personName = "";
      String[] tokens;
      int playerOne = 0;
      int playerTwo = 0;
      int lineNumber = 0;
      
      try
      {
         obj = new Scanner(new File("stat.txt"));
      }
      catch(FileNotFoundException error)
      {
         System.out.println("Error the file 'stat.txt' did not open");  //Instead here we can ensure that a new text file is created with the desired name.
      }
      
      while(obj.hasNext())
      {
         hold = obj.nextLine();
         dataBase = dataBase + hold + "\n";
         lineNumber++;
         
         if(!hold.equalsIgnoreCase("no statistics yet."))
         {
            tokens = hold.split(" ");
            
            if(lineNumber == 1)
               playerOne = Integer.parseInt(tokens[3]);
            else if(lineNumber == 2)
               playerTwo = Integer.parseInt(tokens[3]);
            else if(lineNumber == 3)
               personName = tokens[0];
         }
      }
      
      obj.close();
      
      AirplanePanel panel = new AirplanePanel(dataBase, personName, playerOne, playerTwo);
      frame.getContentPane().add(panel);
      
      frame.pack();
      frame.setVisible(true);
   }
}
