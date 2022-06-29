package renderer.intersect;

import renderer.point.Point3D;
import renderer.point.Vector2;
import renderer.point.Vector3;
import renderer.shapes.MyPolygon;

import java.util.ArrayList;

public class IntersectManager {

	// https://en.wikipedia.org/wiki/Intersection_(Euclidean_geometry)#Two_line_segments
	// https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/#:~:text=1)%20Draw%20a%20horizontal%20line,true%2C%20then%20point%20lies%20outside.

	// https://stackoverflow.com/questions/217578/how-can-i-determine-whether-a-2d-point-is-within-a-polygon

	public static boolean isPointWithin2DPolygonBoundingBox( Vector2 point, Vector2[] polygonPoints ) {
		Vector2 firstPoint = polygonPoints[0];
		int minX = firstPoint.x, maxX = firstPoint.x;
		int minY = firstPoint.y, maxY = firstPoint.y;
		for (Vector2 polyPoint : polygonPoints) {
			if (firstPoint == polyPoint) {
				continue;
			}
			if (polyPoint.x < minX) {
				minX = polyPoint.x;
			} else if (polyPoint.x > maxX) {
				maxX = polyPoint.x;
			}
			if (polyPoint.y < minY) {
				minY = polyPoint.y;
			} else if (polyPoint.y > maxY) {
				maxY = polyPoint.y;
			}
		}
		return (point.x < minX || point.x > maxX || point.y < minY || point.y < maxY);
	}

	public static boolean doLineSegmentsIntersect( Vector2 pointA, Vector2 pointB, Vector2 pointC, Vector2 pointD ) {
		if (pointA.x == pointB.x) {
			return !(pointC.x == pointD.x && pointA.x != pointC.x);
		} else if (pointC.x == pointD.x) {
			return true;
		} else {
			// Both lines are not parallel to the y-axis
			double m1 = (pointA.y-pointB.y) / (pointA.x-pointB.x);
			double m2 = (pointC.y-pointD.y) / (pointC.x-pointD.x);
			return m1 != m2;
		}
	}

	public static boolean is2DPointWithin2DPolygon( Vector2 point, Vector2[] polygonPoints ) {
		if ( polygonPoints.length < 3 || !isPointWithin2DPolygonBoundingBox(point, polygonPoints)) {
			return false;
		}
		System.out.println("Point is within polygon's bounding box");
		int intersections = 0;
		for (int i = 0; i < polygonPoints.length; i++) {
			Vector2 thisPoint = polygonPoints[i];
			Vector2 otherPoint = polygonPoints[ (i + 1) % polygonPoints.length];
			if ( doLineSegmentsIntersect( thisPoint, otherPoint, point, new Vector2(1000, point.y) ) ) {
				intersections += 1;
			}
		}
		System.out.println((intersections % 2) == 1 ? "Within Polygon." : "Not within polygon.");
		return (intersections % 2) == 1;
	}

	/*
		public static Point2D transformPointTo2D(  ) {
			return null;
		}
		public static Point2D[] transformPolygonTo2D( MyPolygon polygon ) {
			Point2D[] points = new Point2D[polygon.getPoints().length];
			return points;
		}
	*/

	public static MyPolygon getOrderedPolygonIntersection(Point3D position, Vector3 direction, ArrayList<MyPolygon> orderedPolygons ) {
		/*
		for ( MyPolygon polygon : orderedPolygons ) {
			if ( is2DPointWithin2DPolygon( IntersectManager.transformPolygonTo2D( polygon ) ) ) {
				return polygon;
			}
		}*/
		return null;
	}

}
