package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.math.MathLibrary;
 
/**
 * This program demonstrates how to draw lines using Graphics2D object.
 * @author www.codejava.net
 *
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 1200;
	
	private static final int MULTIPLIER = 48;
	private static final int MODULUS = 400;
	
	public Main() {
        super("Lines Drawing Demo");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    void drawLines(Graphics g) {
    	
    	int small_sphere_radius = 10;
    	
    	double stepDelta = (1 / (double) MODULUS);
    	int radius = (int) (Math.min( WIDTH, HEIGHT ) / 3);
    	Vector2 midPoint = new Vector2( WIDTH / 2, HEIGHT / 2 );
    	HashMap <Integer, int[]> values = new HashMap<Integer, int[]>();
    	
    	g.drawOval( midPoint.x - radius, midPoint.y - radius, radius * 2, radius * 2);
    	g.setColor( Color.BLACK );
    	for (int i = 0; i < MODULUS; i++) {
    		int[] xy = MathLibrary.getCircleXY(radius, (stepDelta * i));
    		int posX = midPoint.x + xy[0];
    		int posY = midPoint.y + xy[1];
    		values.put(i, xy);
    		Vector2 offset = new Vector2(posX, posY);
    		offset.sub( midPoint );
    		offset.Unit();
    		offset.mult(50);
    		g.fillOval( posX - (small_sphere_radius / 2), posY - (small_sphere_radius / 2), small_sphere_radius, small_sphere_radius);
    		//g.drawString( Integer.toString(i), posX + offset.x / 2, posY + offset.y / 2);
    	}
    	
    	g.setColor( Color.ORANGE );
    	for (int i = MODULUS - 1; i > 0; i--) {
    		int remainder = (i * MULTIPLIER) % MODULUS;
    		int[] xy = values.get(i);
    		int[] other_xy = values.get(remainder);
    		g.drawLine( midPoint.x + xy[0],  midPoint.x + xy[1],  midPoint.x + other_xy[0],  midPoint.x + other_xy[1]);
    	}
    	
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
