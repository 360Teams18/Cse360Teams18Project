package Game;
import javax.swing.JFrame;

public class AirplaneGame
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Airplane Race");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      AirplanePanel panel = new AirplanePanel();
      frame.getContentPane().add(panel);
      
      frame.pack();
      frame.setVisible(true);
   }
}