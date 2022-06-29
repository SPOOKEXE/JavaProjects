package renderer.point;

import java.awt.Point;

import renderer.Display;

public class PointConverter {

	private static double scale = 4;
	private static final int SPECIAL_NUM = 1400;
	private static final double ZOOM_FACTOR = 1.2;
	
	public static void zoomIn() {
		scale *= ZOOM_FACTOR;
	}
	
	public static void zoomOut() {
		scale /= ZOOM_FACTOR;
	}
	
	public static Point convertPoint( MyPoint point3D ) {
		
		double x3d = point3D.getAdjustedY() * scale;
		double y3d = point3D.getAdjustedZ() * scale;
		double depth = point3D.getAdjustedX() * scale;
		double[] newVal = scale(x3d, y3d, depth);
		
		int x2D = (int) (Display.WIDTH / 2 + newVal[0]);
		int y2D = (int) (Display.HEIGHT / 2 - newVal[1]); // is inverted so it must be flipped over the axis
		
		Point point2D = new Point( x2D, y2D );
		return point2D;
	}
	
	public static double[] scale(double x3d, double y3d, double depth) {
		double dist = Math.sqrt( x3d * x3d + y3d * y3d );
		double theta = Math.atan2( y3d ,  x3d ); // Angle of vector
		double depth2 = 15 - depth; // Offset from origin along X axis (depth axis)
		double localScale = Math.abs( SPECIAL_NUM / (depth2 + SPECIAL_NUM) );
		dist *= localScale; // rescale
		double[] newVal = new double[2];
		newVal[0] = dist * Math.cos( theta ); // rotate
		newVal[1] = dist * Math.sin( theta ); // rotate
		return newVal;
		
	}
	
	public static void rotateAxisX( MyPoint p, boolean ClockWise, double degrees ) {
		double radius = Math.sqrt( p.y * p.y + p.z * p.z ); // get the length of the line from origin to point
		double theta = Math.atan2( p.z , p.y ); // get the current angle from the normal (horizontal)
		theta += 2 * (Math.PI / 360) * degrees * (ClockWise ? -1 : 1); // amount of offset to add to theta in RADIANS ; rotated theta value
		p.y = radius * Math.cos( theta ); // new rotated coordinate point
		p.z = radius * Math.sin( theta ); // new rotated coordinate point
	}
	
	public static void rotateAxisY( MyPoint p, boolean ClockWise, double degrees ) {
		double radius = Math.sqrt( p.x * p.x + p.z * p.z ); // get the length of the line from origin to point
		double theta = Math.atan2( p.x , p.z ); // get the current angle from the normal (horizontal)
		theta += 2 * (Math.PI / 360) * degrees * (ClockWise ? -1 : 1); // amount of offset to add to theta in RADIANS ; rotated theta value
		p.x = radius * Math.sin( theta ); // new rotated coordinate point
		p.z = radius * Math.cos( theta ); // new rotated coordinate point
	}
	
	public static void rotateAxisZ( MyPoint p, boolean ClockWise, double degrees ) {
		double radius = Math.sqrt( p.y * p.y + p.x * p.x ); // get the length of the line from origin to point
		double theta = Math.atan2( p.y , p.x ); // get the current angle from the normal (horizontal)
		theta += 2 * (Math.PI / 360) * degrees * (ClockWise ? 1 : -1); // amount of offset to add to theta in RADIANS ; rotated theta value
		p.y = radius * Math.sin( theta ); // new rotated coordinate point
		p.x = radius * Math.cos( theta ); // new rotated coordinate point
	}
	
	
}
