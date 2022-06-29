package renderer.rendering;

import renderer.camera.ICamera;
import renderer.display.IDisplay;
import renderer.entity.IEntity;
import renderer.point.Point3D;
import renderer.point.PointManipulator;
import renderer.point.Vector2;
import renderer.point.Vector3;
import renderer.shapes.MyPolygon;
import renderer.world.IWorld;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class RenderManager {

	private static Random random = new Random();

	private static int[] randomColor() {
        int[] rgb = { random.nextInt(255), random.nextInt(255), random.nextInt(255) };
		return rgb;
    }

	// https://github.com/JOML-CI/JOML

	public static ArrayList<MyPolygon> sortPolygons( ArrayList<MyPolygon> polygons, Point3D origin ) {

		// Order them one front of the other
		Collections.sort(polygons, new Comparator<MyPolygon>() {
			@Override
			public int compare(MyPolygon p1, MyPolygon p2) {
				Point3D p1Average = p1.getAveragePoint();
				Point3D p2Average = p2.getAveragePoint();
				double p1Dist = Point3D.dist( p1Average, origin );
				double p2Dist = Point3D.dist( p2Average, origin );
				double diff = p1Dist - p2Dist;
				if (diff == 0) {
					return 0;
				}
				return diff < 0 ? -1 : 1;
			}
		});

		// Remove any that are fully hidden
		// for (MyPolygon polygon : polygons) { }

		return polygons;

	}

	public static ArrayList<MyPolygon> getVisiblePolygons(IWorld world, ICamera camera ) {

		Point3D camera_location = camera.getPosition();
		Vector3 camera_direction = camera.getDirection();

		double camera_fieldOfView = camera.getFOV();
		double cosFOV = Math.cos( camera_fieldOfView );

		ArrayList<MyPolygon> polygons = new ArrayList<MyPolygon>();

		for (IEntity entity : world.getEntities()) {
			// Ignore any invisible entity
			if (!entity.isVisible()) {
				continue;
			}
			// Get all entity polygons
			for (MyPolygon polygon : entity.getPolygons()) {
				Vector3 deltaPosition = Point3D.diff(camera_location, polygon.getAveragePoint() );
				double withinFOVAngle = Vector3.dot( camera_direction, deltaPosition );
				boolean withinFOV = withinFOVAngle > cosFOV;
				if (withinFOV) {
					polygons.add( polygon );
				}
			}
		}

		return polygons;
	}

	public static void drawVisiblePolygonsOnImage( BufferedImage image, IWorld world, ICamera camera ) {
		ArrayList<MyPolygon> visiblePolygons = getVisiblePolygons(world, camera);
		visiblePolygons = sortPolygons(visiblePolygons, camera.getPosition());
		// System.out.println(visiblePolygons);
		Graphics g = image.getGraphics();
		for (MyPolygon visiblePolygon : visiblePolygons) {
			Polygon poly2D = new Polygon();
			for (Point3D point3D : visiblePolygon.getPoints()) {
				Point p = PointManipulator.convertPoint( point3D, camera.getPosition(), camera.getResolution() );
				poly2D.addPoint(p.x, p.y);
			}
			g.drawPolygon( poly2D );
			g.setColor( visiblePolygon.getColor() );
			// g.fillPolygon( poly2D );
		}
		g.dispose();
	}
	
	public static BufferedImage generateRandomImage( IDisplay widget, IWorld world, ICamera camera ) {
		Vector2 imageResolution = camera.getResolution();
		BufferedImage newImage = new BufferedImage(imageResolution.x, imageResolution.y, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				int[] rgb = randomColor();
				newImage.setRGB(x, y, new Color(rgb[0], rgb[1], rgb[2]).getRGB() );
			}
		}
		return newImage;
	}
	
	public static BufferedImage generateImage( IDisplay widget, IWorld world, ICamera camera ) {
		Vector2 imageResolution = camera.getResolution();
		BufferedImage newImage = new BufferedImage(imageResolution.x, imageResolution.y, BufferedImage.TYPE_INT_ARGB);
		drawVisiblePolygonsOnImage( newImage, world, camera );
		/*
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				Color color = new Color(0, 0, 0);
				newImage.setRGB(x, y, color.getRGB());
			}
		}*/

		return newImage;
	}
	
}
