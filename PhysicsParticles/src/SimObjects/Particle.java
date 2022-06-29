package SimObjects;

import main.Vec2;

public class Particle {

	public float AttractionConstant = 1;
	public int radius = 20;
	
	// Variables & Properties //
	public Vec2 position = new Vec2();
	public Vec2 velocity = new Vec2();
	public Vec2 acceleration = new Vec2();
	
	// Constructors //
	public Particle() { }
	public Particle(Vec2 pos) { this.position = pos; }
	public Particle(Vec2 pos, Vec2 vel) { this.position = pos; this.velocity = vel; }
	public Particle(Vec2 pos, Vec2 vel, Vec2 accel) { this.position = pos; this.velocity = vel; this.acceleration = accel; }
	
	// Functions //
	
	// Methods //
	
	
}
