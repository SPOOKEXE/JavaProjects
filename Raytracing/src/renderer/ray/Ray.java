package renderer.ray;

import java.awt.Polygon;

import renderer.entity.Entity;
import renderer.point.Point3D;
import renderer.point.Vector3;
import renderer.world.IWorld;

// https://github.com/jMonkeyEngine/jmonkeyengine/blob/master/jme3-core/src/main/java/com/jme3/math/Ray.java

public class Ray implements IRay {

	@Override
	public RayResult getWorldIntersection(Point3D position, Vector3 direction, IWorld world) {
		RayResult newResult = new RayResult();
		return newResult;
	}
	
	@Override
	public boolean intersectsPolygon( Polygon poly ) throws Exception {
		throw new Exception("Unimplemented");
	}

	@Override
	public boolean intersectsEntity(Entity entity) throws Exception {
		throw new Exception("Unimplemented");
	}

}
