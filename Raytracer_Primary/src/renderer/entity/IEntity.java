package renderer.entity;

import renderer.shapes.MyPolygon;

public interface IEntity {

	void init();
	void update();
	
	String toString();
	MyPolygon[] getPolygons();

	boolean isVisible();
	void setVisible( boolean visible );
}
