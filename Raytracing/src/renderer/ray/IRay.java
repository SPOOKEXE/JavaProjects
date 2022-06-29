package renderer.ray;

import java.awt.Polygon;

import renderer.entity.Entity;
import renderer.point.Point3D;
import renderer.point.Vector3;
import renderer.world.IWorld;

public interface IRay {
	RayResult getWorldIntersection( Point3D position, Vector3 direction, IWorld world );
	
	boolean intersectsPolygon( Polygon poly ) throws Exception;
	boolean intersectsEntity( Entity entity ) throws Exception;
}
