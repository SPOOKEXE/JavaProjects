
public class Vertex {
	
	double x, y, z;
	
	Vertex(double x, double y , double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void resize(double scale) {
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
	}
	
}
