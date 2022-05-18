import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Display extends JPanel {
	
	static double minReal = -2.0;
	static double maxReal = 0.7;
	static double minImaginary = -1.25;
	static double maxImaginary = 1.25;
	static int maxIterations = 100;
	static int xyResolution = 1000;
	static double radiusSquared = 4.0;
	static String magText = "Magnification = " + ExploreFunctions.roundedMagnificationValue;
	static boolean show = false;
	static String maxIterationsInfo = "Current Max Iterations = " + maxIterations;
	static boolean showColour = false;
	
	MandelbrotCalculator mandelCalc = new MandelbrotCalculator();
	int[][] mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	int colour;
	Color currColour;

	public Display() {
		
	    JButton zoom = new JButton("Zoom In");
	    MandelbrotExplorer.panel.add(zoom);
	    JButton zoomOut = new JButton("Zoom Out");
	    MandelbrotExplorer.panel.add(zoomOut);
	    JButton up = new JButton("Up");
	    MandelbrotExplorer.panel.add(up);
	    JButton down = new JButton("Down");
	    MandelbrotExplorer.panel.add(down);
	    JButton left = new JButton("Left");
	    MandelbrotExplorer.panel.add(left);
	    JButton right = new JButton("Right");
	    MandelbrotExplorer.panel.add(right);
	    JButton undo = new JButton("Undo");
	    MandelbrotExplorer.panel.add(undo);
	    JButton reset = new JButton("Reset");
	    MandelbrotExplorer.panel.add(reset);
	    JLabel magnification = new JLabel();
	    magnification.setText("Magnification: ");
	    MandelbrotExplorer.panel.add(magnification);
	    JCheckBox magOn = new JCheckBox("");
	    MandelbrotExplorer.panel.add(magOn);
	    JLabel showColor = new JLabel();
	    showColor.setText("Colour: ");
	    MandelbrotExplorer.panel.add(showColor);
	    JCheckBox colourOn = new JCheckBox("");
	    MandelbrotExplorer.panel.add(colourOn);
	    
	    JButton save = new JButton("Save");
	    MandelbrotExplorer.panel2.add(save);
	    JButton load = new JButton("Load");
	    MandelbrotExplorer.panel2.add(load);
	    JButton plusMaxIteraions = new JButton("Increase Max Iterations");
	    MandelbrotExplorer.panel2.add(plusMaxIteraions);
	    JButton minusMaxIterations = new JButton("Decrease Max Iterations");
	    MandelbrotExplorer.panel2.add(minusMaxIterations);
	    JLabel currentMaxIterations = new JLabel();
	    currentMaxIterations.setText(maxIterationsInfo);
	    MandelbrotExplorer.panel2.add(currentMaxIterations);
	    
	    zoom.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.zoom();
	    		magText = ("Magnification = " + ExploreFunctions.roundedMagnificationValue);
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    zoomOut.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.zoomOut();
	    		magText = ("Magnification = " + ExploreFunctions.roundedMagnificationValue);
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    reset.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.reset();
	    		magText = ("Magnification = " + ExploreFunctions.roundedMagnificationValue);
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    up.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.up();
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });

	    down.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.down();
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    left.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.left();
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    right.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.right();
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    undo.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.undo();
	    		currentMaxIterations.setText(maxIterationsInfo);
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    plusMaxIteraions.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.plusMaxIterations();
	    		currentMaxIterations.setText(maxIterationsInfo);
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    minusMaxIterations.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		ExploreFunctions.minusMaxIterations();
	    		currentMaxIterations.setText(maxIterationsInfo);
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    magOn.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (magOn.isSelected()) {
	    			
	    			show = true;
	    			
	    		} else {
	    			
	    			show = false;
	    			
	    		}
	    		
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    colourOn.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (colourOn.isSelected()) {
	    			
	    			showColour = true;
	    			
	    		} else {
	    			
	    			showColour = false;
	    			
	    		}
	    		
	    		repaint();
	    		
	    	}
	    	
	    });
	    
	    save.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		try {
	    			
					ExploreFunctions.save();
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
	    		
	    	}
	    	
	    });
	    
	    load.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		try {
	    			
					ExploreFunctions.load();
					
				} catch (IOException e2) {

					e2.printStackTrace();
					
				}
	    		
	    		mandelbrotData = mandelCalc.calcMandelbrotSet(xyResolution, xyResolution, minReal, maxReal, minImaginary, maxImaginary, maxIterations, radiusSquared);
	    		repaint();
	    		
	    	}
	    	
	    });
		
	}
	
	public void paint(Graphics g) {
		
		for (int a = 0; a < mandelbrotData.length; a++) {
			
			for (int b = 0; b < mandelbrotData[a].length; b++) {
				
				colour = Color.HSBtoRGB((float) mandelbrotData[a][b] / maxIterations, 0.5f, 1);
				currColour = new Color(colour);
				
				if (mandelbrotData[a][b] == maxIterations) {
					
					g.setColor(Color.black);
					
				} else {
					
					if (showColour) {
						
						g.setColor(currColour);
						
					} else {
						
						g.setColor(Color.white);
						
					}
					
				}
				
				g.drawLine(b,a,b,a);
				
			}
			
		}

		if (show) {
			
			g.setColor(Color.black);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString(magText, 50, 50);
			
		}
	    
	  }
	
}
