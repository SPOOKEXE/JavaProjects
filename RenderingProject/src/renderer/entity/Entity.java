package renderer.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.point.MyVector;
import renderer.shapes.MyPolygon;
import renderer.shapes.Polyhedron;

public class Entity implements IEntity {

	private List<Polyhedron> polyhedrons;
	private MyPolygon[] polygons;
	
	public Entity( List<Polyhedron> polyhedrons ) {
		this.polyhedrons = polyhedrons;
		List<MyPolygon> tempList = new ArrayList<MyPolygon>();
		for (Polyhedron tetra : this.polyhedrons) {
			tempList.addAll( Arrays.asList( tetra.getPolygons() ) );
		}
		this.polygons = new MyPolygon[tempList.size()];
		this.polygons = tempList.toArray(this.polygons);
		this.sortPolygons();
	}
	
	@Override
	public void render(Graphics g) {
		for (MyPolygon poly : this.polygons) {
			poly.render( g );
		}
	}

	@Override
	public void translate(double x, double y, double z) {
		for (Polyhedron polyhedron : this.polyhedrons) {
			polyhedron.translate( x, y, z );
		}
		this.sortPolygons();
	}
	
	@Override
	public void rotate(boolean clockWise, double xDegrees, double yDegrees, double zDegrees, MyVector lightVector) {
		for (Polyhedron polyhedron : this.polyhedrons) {
			polyhedron.rotate( clockWise, xDegrees, yDegrees, zDegrees, lightVector );
		}
		this.sortPolygons();
	}
	
	@Override
	public void setLighting( MyVector lightVector ) {
		for (Polyhedron polyhedron : this.polyhedrons) {
			polyhedron.setLighting( lightVector );
		}
	}
	
	@Override
	public void shift() {
		for (Polyhedron poly : this.polyhedrons) {
			poly.shift();
		}
	}
	
	private void sortPolygons() {
		MyPolygon.sortPolygons(this.polygons);
	}

}
