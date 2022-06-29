package renderer.world;

public class Camera {

	private double x, y, z;
	private double roll, pitch, yaw;

	public Camera(double x, double y, double z, double roll, double pitch, double yaw) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public Camera(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.roll = this.pitch = this.yaw = 0;
	}

	public Camera() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.roll = this.pitch = this.yaw = 0;
	}

	public void translate(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void rotate(double roll, double pitch, double yaw) {
		this.roll += roll;
		this.pitch += pitch;
		this.yaw += yaw;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	public double getRoll() {
		return this.roll;
	}

	public double getPitch() {
		return this.pitch;
	}

	public double getYaw() {
		return this.yaw;
	}

}
