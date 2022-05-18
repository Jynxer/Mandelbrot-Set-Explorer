import javax.swing.*;
import java.awt.*;

public class MandelbrotExplorer extends JPanel {
	
	static JPanel panel = new JPanel();
	static JPanel panel2 = new JPanel();
	static int size = 1000;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mandelbrot Explorer");
		frame.getContentPane().add(BorderLayout.NORTH, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel2);
	    frame.getContentPane().add(new Display());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(size, size);
	    frame.setVisible(true);
		
	}

}
