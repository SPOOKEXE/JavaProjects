package renderer.point;

public class MyVector {

	public double x, y, z;
	
	public MyVector( double x, double y, double z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public MyVector() {
		this.x = this.y = this.z = 0; // x, y, z = 0, starting from z backwards to x.
	}
	
	public MyVector( MyPoint p1, MyPoint p2 ) {
		this.x = p2.x - p1.x;
		this.y = p2.y - p1.y;
		this.z = p2.z - p1.z;
	}
	
	// Gives the scalar product of the two lines (multiplying two vectors together)
	// "are the product of the Euclidean magnitudes of the two vectors and the cosine of the angle between them"
	public static double dot( MyVector v1, MyVector v2 ) {
		return (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z); 
	}
	
	// get the right angle line out of the plane created by the two vectors (right angles to both vectors)
	// note: the area of the two vectors is ends up being the magnitude of the cross product parallel line
	// https://www.mathsisfun.com/algebra/vectors-cross-product.html
	public static MyVector cross( MyVector v1, MyVector v2 ) {
		return new MyVector( 
			(v1.y * v2.z) - (v1.z * v2.y),
			(v1.z * v2.x) - (v1.x - v2.z),
			(v1.x * v2.y) - (v1.y - v2.x)
		);
				
	}
	
	public static MyVector normalize( MyVector v ) {
		double magnitude = Math.sqrt( (v.x * v.x) + (v.y * v.y) + (v.z * v.z) );
		return new MyVector( (v.x / magnitude), (v.y / magnitude), (v.z / magnitude) );
	}
	
}
