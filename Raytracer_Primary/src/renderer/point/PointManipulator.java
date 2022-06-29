package renderer.point;

import java.awt.*;

public class PointManipulator {

    private static double scale = 8;
    private static final int SPECIAL_NUM = 1400;

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

    public static Point convertPoint( Point3D point3D, Point3D cameraPosition, Vector2 cameraResolution ) {

        double x3d = point3D.y * scale;
        double y3d = point3D.z * scale;
        double depth = point3D.x * scale;
        double[] newVal = scale(x3d, y3d, depth);
        int x2D = (int) ( (cameraResolution.x / 2) + newVal[0]);
        int y2D = (int) ( (cameraResolution.y / 2) - newVal[1]); // is inverted so it must be flipped over the axis

        return new Point( x2D, y2D );
    }

}
