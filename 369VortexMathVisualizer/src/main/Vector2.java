package main;

public class Vector2 {

	int x, y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 sub( Vector2 other ) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2 add( Vector2 other ) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2 mult( double scale ) {
		this.x = (int) (this.x * scale);
		this.y = (int) (this.y * scale);
		return this;
	}
	
	public Vector2 Unit() {
		int max = Math.max( Math.abs( this.x ), Math.abs( this.y) );
		if (max == 0) {
			this.x = 0;
			this.y = 0;
		} else {
			if (this.x != 0) this.x = (int) (this.x / max);
			if (this.y != 0) this.y = (int) (this.y / max);
		}
		return this;
	}
	
	public Vector2 copy() {
		return new Vector2( this.x, this.y );
	}
	
}
