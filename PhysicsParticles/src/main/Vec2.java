package main;


public class Vec2 {

	// Variables & Properties //
	public int x;
	public int y;
	
	// Constructors //
	public Vec2() { this.x = 0; this.y = 0; }
	public Vec2(int x, int y) { this.x = x; this.y = y; }
	
	// Functions //
	public Vec2 sub(Vec2 other) {
	    this.x -= other.x;
	    this.y -= other.y;
	    return this;
	  }
	  
	  public Vec2 add(Vec2 other) {
	    this.x += other.x;
	    this.y += other.y;
	    return this;
	  }
	  
	  public Vec2 mult(double mult) {
	    this.x = (int) (this.x * mult);
	    this.y = (int) (this.y * mult);
	    return this;
	  }
	  
	  public Vec2 copy() {
	    return new Vec2(this.x, this.y);
	  }
	  
	  public double magDoub() {
	    if (this.x == 0 && this.y == 0) { return 0.0f; }
	    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	  }
	  
	  public float magFloat() {
		 return (float) this.magDoub();
	  }
	  
	  public Vec2 norm() {
		  // V = (x, y, z)
		  // |V| = Math.sqrt(x*x + y*y + z*z)
		  // V/|V| = (x/|V|, y/|V|, z/|V|)
		  float magF = Math.abs(this.magFloat());
		  this.x = (int) (this.x / magF);
		  this.y = (int) (this.y / magF);
		  return this;
	  }
	  
	  public boolean equals( Vec2 other ) {
	    return (this.x == other.x) && (this.y == other.y); 
	  }
	  
	  public String toString() {
	    return "(" + this.x + ", " + this.y + ")";
	  }
	  
	  // Methods //
	
}
