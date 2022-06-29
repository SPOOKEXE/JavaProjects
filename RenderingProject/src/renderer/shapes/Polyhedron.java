package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;

import renderer.point.MyVector;

public class Polyhedron {

	private MyPolygon[] polygons;
	private Color color;
	
	public Polyhedron( MyPolygon... polygons) {
		this.polygons = polygons;
		this.sortPolygons();
	}
	
	public Polyhedron( Color color, boolean decayColor, MyPolygon... polygons) {
		this.color = color;
		this.polygons = polygons;
		if (decayColor) {
			this.setDecayingPolygonColor();
		} else {
			this.setPolygonColor( color );
		}
		this.sortPolygons();
	}
	
	public void render( Graphics g ) {
		for (MyPolygon poly : this.polygons) {
			poly.render( g );
		}
		this.sortPolygons();
	}
	
	public void shift() {
		for (MyPolygon poly : this.polygons) {
			poly.shift( );
		}
	}

	public void sortPolygons() {
		MyPolygon.sortPolygons( this.polygons );
	}
	
	public void rotate( boolean clockWise, double xDegrees, double yDegrees, double zDegrees, MyVector lightVector ) {
		for ( MyPolygon p : this.polygons ) {
			p.rotate(clockWise, xDegrees, yDegrees, zDegrees, lightVector);
		}
		this.sortPolygons();
	}
	
	public void translate( double x, double y, double z ) {
		for ( MyPolygon p : this.polygons ) {
			p.translate(x, y, z);
		}
		this.sortPolygons();
	}
	
	public MyPolygon[] getPolygons() {
		return this.polygons;
	}
	
	public void setPolygonColor( Color color ) {
		this.color = color;
		for (MyPolygon poly : this.polygons) {
			poly.setColor( color );
		}
	}
	
	public void setDecayingPolygonColor() {
		double decayFactor = 0.99;
		for (MyPolygon poly : this.polygons) {
			poly.setColor( this.color );
			int r = (int) (this.color.getRed() * decayFactor);
			int g = (int) (this.color.getGreen() * decayFactor);
			int b = (int) (this.color.getBlue() * decayFactor);
			this.color = new Color(r, g, b);
		}
	}
	
	public void setLighting( MyVector lightVector ) {
		for (MyPolygon poly : this.polygons) {
			poly.setLighting( lightVector );
		}
	}
	
}
