package Classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class WindowViewport {

	// Variables & Properties
	public JFrame widgetFrame;
	public Simulation activeSimulation;
	
	// Constructors //
	public WindowViewport() {
		this.widgetFrame = new JFrame();
		this.widgetFrame.setTitle("Simulation Display");
		this.widgetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.widgetFrame.pack();
		this.widgetFrame.setVisible(true);
		this.widgetFrame.setLocationRelativeTo(null);
		this.widgetFrame.setResizable(false);
		this.widgetFrame.setSize(400, 400);
	}
	
	// Functions //
	
	// Methods //
	public void setSimulation( Simulation simulation ) {
		System.out.printf("Set simulation; ", simulation);
		activeSimulation = simulation;
	}
	
	public void render() {
		
		// Get the frame's buffer for drawing.
		BufferStrategy bs = this.widgetFrame.getBufferStrategy();
		if (bs == null) {
			this.widgetFrame.createBufferStrategy(3); // triple buffering
			return;
		}
		
		// Draw the graphics
		Dimension size = this.widgetFrame.getSize();
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size.width, size.height);
		// Render the simulation by passing the graphics through
		if (this.activeSimulation != null) {
			this.activeSimulation.render(g);
		} else {
			g.drawString("No Simulation Set", (size.width / 2), (size.height / 2));
		}
		g.dispose();
		bs.show();
	}
	
}
