package renderer.entity.builder;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.point.MyPoint;
import renderer.shapes.MyPolygon;
import renderer.shapes.Polyhedron;

public class ComplexEntityBuilder {

	public static IEntity createRubiksCube( double size, double centerX, double centerY, double centerZ ) {
		
		List<Polyhedron> tetras = new ArrayList<Polyhedron>();
		
		double cubeSpacing = 10;
		double halfSize = size / 2;
		
		for (int i = -1; i < 2; i++) { // -1, 0, 1
			double cubeCenterX = i * (size + cubeSpacing) + centerX;
			for (int j = -1; j < 2; j++) { // -1, 0, 1
				double cubeCenterY = j * (size + cubeSpacing) + centerY;
				for (int k = -1; k < 2; k++) { // -1, 0, 1
					double cubeCenterZ = k * (size + cubeSpacing) + centerZ;
					
					if (i == 0 && j == 0 && k == 0) continue; // ignore center cube
					
					MyPoint p1 = new MyPoint( cubeCenterX - halfSize, cubeCenterY - halfSize, cubeCenterZ - halfSize );
					MyPoint p2 = new MyPoint( cubeCenterX - halfSize, cubeCenterY - halfSize, cubeCenterZ + halfSize );
					MyPoint p3 = new MyPoint( cubeCenterX - halfSize, cubeCenterY + halfSize, cubeCenterZ - halfSize );
					MyPoint p4 = new MyPoint( cubeCenterX - halfSize, cubeCenterY + halfSize, cubeCenterZ + halfSize );
					MyPoint p5 = new MyPoint( cubeCenterX + halfSize, cubeCenterY - halfSize, cubeCenterZ - halfSize );
					MyPoint p6 = new MyPoint( cubeCenterX + halfSize, cubeCenterY - halfSize, cubeCenterZ + halfSize );
					MyPoint p7 = new MyPoint( cubeCenterX + halfSize, cubeCenterY + halfSize, cubeCenterZ - halfSize );
					MyPoint p8 = new MyPoint( cubeCenterX + halfSize, cubeCenterY + halfSize, cubeCenterZ + halfSize );
					
					MyPolygon polyRed = new MyPolygon( Color.RED, p5, p6, p8, p7 );
					MyPolygon polyWhite = new MyPolygon( Color.WHITE, p2, p4, p8, p6 );
					MyPolygon polyBlue = new MyPolygon( Color.BLUE, p3, p7, p8, p4 );
					MyPolygon polyGreen = new MyPolygon( Color.GREEN, p1, p2, p6, p5 );
					MyPolygon polyOrange = new MyPolygon( new Color(255, 120, 30), p1, p3, p4, p2 );
					MyPolygon polyYellow = new MyPolygon( Color.YELLOW, p1, p5, p7, p3 );
					
					Polyhedron tetra = new Polyhedron(polyRed, polyWhite, polyBlue, polyGreen, polyOrange, polyYellow);
					tetras.add(tetra);
					
				}
			}
		}
		
		return new Entity(tetras);
		
	}
	
}
