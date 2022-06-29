package renderer.entity;

import renderer.shapes.MyPolygon;
import renderer.shapes.MyPolyhedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entity implements IEntity {

	private List<MyPolyhedron> polyhedrons;
	private MyPolygon[] polygons;
	
	private Color color;
	private boolean visible;
	
	public Entity( List<MyPolyhedron> polyhedrons ) {
		this.polyhedrons = polyhedrons;
		this.visible = true;
		List<MyPolygon> tempList = new ArrayList<MyPolygon>();
		for (MyPolyhedron tetra : this.polyhedrons) {
			tempList.addAll( Arrays.asList( tetra.getPolygons() ) );
		}
		this.polygons = new MyPolygon[tempList.size()];
		this.polygons = tempList.toArray(this.polygons);
	}
	public void setColor( Color color ) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}

	public boolean isVisible() { return this.visible; }
	public void setVisible( boolean visible ) { this.visible = visible; }

	public MyPolygon[] getPolygons() {
		return this.polygons;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public String toString() {
		String baseString = "Entity | %s | Polyhedron Count: %s | Polygon Count: %s";
		return String.format(
			baseString,
			Integer.toHexString(this.hashCode()),
			this.polyhedrons.toArray().length,
			this.polygons.length
		);
	}
	
}
