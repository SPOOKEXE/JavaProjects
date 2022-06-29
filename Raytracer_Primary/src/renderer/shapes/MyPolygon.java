package renderer.shapes;

import renderer.point.Point3D;

import java.awt.*;

public class MyPolygon {

	private Color color;
	private Point3D[] points;
	
	public MyPolygon(Point3D... points) {
		// this.color = Color.WHITE;
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D p = points[i];
			this.points[i] = new Point3D(p.x, p.y, p.z);
		}
	}
	
	public MyPolygon(Color color, Point3D... points) {
		this.color = color;
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D p = points[i];
			this.points[i] = new Point3D(p.x, p.y, p.z);
		}
	}

	public Point3D[] getPoints() {
		return this.points;
	}

	public Point3D getAveragePoint() {
		double x = 0;
		double y = 0;
		double z = 0;
		for (Point3D point : this.points) {
			x += point.x;
			y += point.y;
			z += point.z;
		}
		x /= this.points.length;
		y /= this.points.length;
		z /= this.points.length;
		return new Point3D(x, y, z);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor( Color color ) {
		this.color = color;
	}
	
}
