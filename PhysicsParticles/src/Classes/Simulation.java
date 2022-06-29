package Classes;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import SimObjects.FieldBody;
import SimObjects.Particle;
import main.Vec2;

public class Simulation {
	
	public float AttractionConstant = 1;
	
	// Variables & Properties //
	ArrayList<Particle> simulatedParticles;
	ArrayList<FieldBody> simulatedFieldBodies;
	
	// Constructors //
	public Simulation() {
		this.simulatedParticles = new ArrayList<Particle>();
		this.simulatedFieldBodies = new ArrayList<FieldBody>();
	}
	
	// Functions //
	public Vec2 getForceOnParticle( Particle fromHere, Particle toHere ) {
		Vec2 direction = (toHere.position.copy().add(fromHere.position)).norm();
		float dist = toHere.position.copy().sub(fromHere.position).magFloat();
		return direction.mult( AttractionConstant * 1 / Math.pow(dist, 2) );
	}
	
	public boolean isColliding( Particle a, Particle b ) {
		return a.position.copy().sub(b.position).magFloat() <= (a.radius + b.radius);
	}
	
	// Methods //
	public void setConstants() {
		for (Particle p : this.simulatedParticles) {
			p.AttractionConstant = this.AttractionConstant;
		}
		for (FieldBody p : this.simulatedFieldBodies) {
			p.AttractionConstant = this.AttractionConstant;
		}
	}
	
	public void appendParticles( ArrayList<Particle> particles ) {
		simulatedParticles.addAll(particles);
		this.setConstants();
	}
	
	public void appendParticles( Particle... particles ) {
		for (Particle particle : particles) {
			simulatedParticles.add(particle);
		}
		this.setConstants();
	}
	
	public void render(Graphics g) {
		for (Particle particle : this.simulatedParticles) {
			g.setColor(Color.WHITE);
			g.fillOval(particle.position.x, particle.position.y, particle.radius, particle.radius);
			g.setColor(Color.GRAY);
			g.drawOval(particle.position.x, particle.position.y, particle.radius, particle.radius);
		}
	}
	
	public void update( double deltaTime ) {
		System.out.println("Simulation Update - unimplemented - " + deltaTime);
	}
	
}
