package base;

public class Vector2c {

	// Variables //
	public int x;
	public int y;
	
	// Constructors //
	public Vector2c() { }
	public Vector2c(int x) { this.x = x; }
	public Vector2c(int x, int y) { this.x = x; this.y = y; }
	
	// Methods //
	public Vector2c add( Vector2c other ) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2c sub( Vector2c other ) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2c mult( Integer mult ) {
		this.x *= mult;
		this.y *= mult;
		return this;
	}
	
	public Vector2c div( Integer divider ) {
		this.x /= divider;
		this.y /= divider;
		return this;
	}
	
}
