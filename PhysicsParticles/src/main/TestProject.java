package main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Classes.Simulation;
import Classes.WindowViewport;
import SimObjects.Particle;

public class TestProject implements Runnable {
	private static final double nansecond = 10e5;
	private static final int windowPadding = 40;
	
	private static ArrayList<Simulation> simulationClasses = new ArrayList<Simulation>();
	private static ArrayList<WindowViewport> viewportWindows = new ArrayList<WindowViewport>();
	private static int RENDERS_PER_SEC = 2;
	private static int PHYSICS_PER_SEC = 2;
	
	public void init() {
		// Create a new window viewport
		WindowViewport window = new WindowViewport();
		viewportWindows.add(window);
		Dimension windowSize = window.widgetFrame.getSize();
		
		// Create a new simulation
		Simulation newSimulation = new Simulation();
		simulationClasses.add(newSimulation);
		
		// Create random particles in the simulation
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			Vec2 randomPosition = new Vec2( rand.nextInt(windowPadding, windowSize.width - windowPadding), rand.nextInt(windowPadding, windowSize.height - windowPadding) );
			Particle newParticle = new Particle(randomPosition);
			newSimulation.appendParticles(newParticle);
		}
		
		// Assign the window viewport the particles for rendering
		window.setSimulation( newSimulation );
	}
	
	@Override
	public void run() {
		System.out.println("Main - Start Update & Render Thread");
		
		// Vars
		float lastPhysicsUpdate = System.nanoTime();
		float lastRenderUpdate = System.nanoTime();
		final float physicsInterval = 1000f * (1f / RENDERS_PER_SEC);
		final float renderInterval = 1000f * (1f / PHYSICS_PER_SEC);
		
		while (true) {
			float newUpdate = System.nanoTime();
			
			float deltaPhysics = (float) ((newUpdate - lastPhysicsUpdate) / nansecond);
			float deltaRender = (float) ((newUpdate - lastRenderUpdate) / nansecond);
			
			// Physics Updates
			if (deltaPhysics > physicsInterval) {
				lastPhysicsUpdate = newUpdate;
				for (Simulation sim : simulationClasses) {
					// run with 10 smaller iteration steps
					for (int i = 0; i < 10; i++) {
						sim.update(deltaRender * 0.001 * 1/10);
					}
				}
			}

			// Render Updates
			if (deltaRender > renderInterval) {
				lastRenderUpdate = newUpdate;
				for (WindowViewport viewWindow : viewportWindows) {
					viewWindow.render();
				}
			}
			
			// Global Update Interval
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
