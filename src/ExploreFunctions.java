import java.io.*;
import java.text.DecimalFormat;
import java.util.Properties;

public class ExploreFunctions {
	
	private static DecimalFormat df = new DecimalFormat("#.#");
	
	static double multiplier = 100;
	static double realStep;
	static double imaginaryStep;
	static int lastOperation = 0; //Zoom = 1, Reset = 2, Up = 3, Down = 4, Left = 5, Right = 6, ZoomOut = 7, +maxIterations = 8, -maxIterations = 9.
	static double magnificationValue = 1.0;
	static String roundedMagnificationValue = df.format(magnificationValue);
	
	
	public static void updateSteps() {
		
		realStep = (Math.abs(Display.maxReal - Display.minReal) / Display.xyResolution) * multiplier;
		imaginaryStep = (Math.abs(Display.maxImaginary - Display.minImaginary) / Display.xyResolution) * multiplier;
		
	}
	
	public static void zoom() {
		
		updateSteps();
		
		Display.minReal += realStep;
		Display.maxReal -= realStep;
		Display.minImaginary += imaginaryStep;
		Display.maxImaginary -= imaginaryStep;
		System.out.println("Zoomed!");
		
		magnificationValue = (Math.abs(MandelbrotCalculator.INITIAL_MAX_REAL - MandelbrotCalculator.INITIAL_MIN_REAL) / Math.abs(Display.maxReal - Display.minReal));
		roundedMagnificationValue = df.format(magnificationValue);
		lastOperation = 1;
		
	}
	
	public static void reset() {
		
		updateSteps();
		
		Display.minReal = -2.0;
		Display.maxReal = 0.7;
		Display.minImaginary = -1.25;
		Display.maxImaginary = 1.25;
		Display.maxIterations = 100;
		Display.xyResolution = 1000;
		System.out.println("Reset!");
		
		magnificationValue = 1.0;
		roundedMagnificationValue = df.format(magnificationValue);
		lastOperation = 2;
		
	}
	
	public static void up() {
		
		updateSteps();
		
		Display.minImaginary -= imaginaryStep;
		Display.maxImaginary -= imaginaryStep;
		System.out.println("Moved up!");
		
		lastOperation = 3;
		
	}
	
	public static void down() {
		
		updateSteps();
		
		Display.minImaginary += imaginaryStep;
		Display.maxImaginary += imaginaryStep;
		System.out.println("Moved down!");
		
		lastOperation = 4;
		
	}
	
	public static void left() {
		
		updateSteps();
		
		Display.minReal -= realStep;
		Display.maxReal -= realStep;
		System.out.println("Moved left!");
		
		lastOperation = 5;
		
	}
	
	public static void right() {
		
		updateSteps();
		
		Display.minReal += realStep;
		Display.maxReal += realStep;
		System.out.println("Moved right!");
		
		lastOperation = 6;
		
	}
	
	public static void zoomOut() {
		
		updateSteps();
		
		Display.minReal -= realStep;
		Display.maxReal += realStep;
		Display.minImaginary -= imaginaryStep;
		Display.maxImaginary += imaginaryStep;
		System.out.println("Unzoomed!");
		
		magnificationValue = (Math.abs(MandelbrotCalculator.INITIAL_MAX_REAL - MandelbrotCalculator.INITIAL_MIN_REAL) / Math.abs(Display.maxReal - Display.minReal));
		roundedMagnificationValue = df.format(magnificationValue);
		lastOperation = 7;
		
	}
	
	public static void plusMaxIterations() {
		
		Display.maxIterations += 10;
		Display.maxIterationsInfo = "Current Max Iterations = " + Display.maxIterations;
		System.out.println("Increased Max Iterations!");
		ExploreFunctions.lastOperation = 8;
		
	}
	
	public static void minusMaxIterations() {
		
		Display.maxIterations -= 10;
		Display.maxIterationsInfo = "Current Max Iterations = " + Display.maxIterations;
		System.out.println("Decreased Max Iterations!");
		ExploreFunctions.lastOperation = 9;
		
	}
	
	public static void save() throws IOException {
		
		Properties p = new Properties();
		p.setProperty("minReal", ""+Display.minReal);
		p.setProperty("maxReal", ""+Display.maxReal);
		p.setProperty("minImaginary", ""+Display.minImaginary);
		p.setProperty("maxImaginary", ""+Display.maxImaginary);
		p.setProperty("maxIterations", ""+Display.maxIterations);
		
		FileOutputStream fos = new FileOutputStream("params.properties");
		
		p.store(fos, "Saved Params");
		
		fos.close();
		
		System.out.println("Saved!");
		
	}
	
	public static void load() throws IOException {
		
		FileInputStream fis = new FileInputStream("params.properties");
		
		Properties p2 = new Properties();
		p2.load(fis);
		
		String minRealString = p2.getProperty("minReal");
		String maxRealString = p2.getProperty("maxReal");
		String minImaginaryString = p2.getProperty("minImaginary");
		String maxImaginaryString = p2.getProperty("maxImaginary");
		String maxIterationsString = p2.getProperty("maxIterations");
		
		Display.minReal = Double.valueOf(minRealString);
		Display.maxReal = Double.valueOf(maxRealString);
		Display.minImaginary = Double.valueOf(minImaginaryString);
		Display.maxImaginary = Double.valueOf(maxImaginaryString);
		Display.maxIterations = Integer.valueOf(maxIterationsString);
		
		updateSteps();
		
		magnificationValue = (Math.abs(MandelbrotCalculator.INITIAL_MAX_REAL - MandelbrotCalculator.INITIAL_MIN_REAL) / Math.abs(Display.maxReal - Display.minReal));
		roundedMagnificationValue = df.format(magnificationValue);
		Display.maxIterationsInfo = "Current Max Iterations = " + Display.maxIterations;
		
		fis.close();
		
		System.out.println("Loaded!");
		
	}
	
	public static void undo() {
		
		switch (lastOperation) {
		  case 1: //Zoom
		    zoomOut();
		    break;
		  case 2: //Reset
		    System.out.println("Cannot undo a reset.");
		    break;
		  case 3: //Up
		    down();
		    break;
		  case 4: //Down
		    up();
		    break;
		  case 5: //Left
		    right();
		    break;
		  case 6: //Right
		    left();
		    break;
		  case 7: //Zoom Out
			  zoom();
			  break;
		  case 8: //+maxIterations
			  minusMaxIterations();
			  break;
		  case 9: //-maxIterations
			  plusMaxIterations();
			  break;
		}
		
	}
	
}
