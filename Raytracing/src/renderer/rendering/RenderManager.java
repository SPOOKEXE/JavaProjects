package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import renderer.camera.ICamera;
import renderer.display.IDisplay;
import renderer.point.Vector2;
import renderer.world.IWorld;

public class RenderManager {

	private static Random random = new Random();

	private static int[] randomColor() {
        int[] rgb = { random.nextInt(255), random.nextInt(255), random.nextInt(255) };
		return rgb;
    }
	
	public static Color getWorldPixelFromCamera( Vector2 cameraOffset, IWorld world, ICamera camera ) {
		Color color = new Color(0,0,0);
		
		return color;
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
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				Color color = getWorldPixelFromCamera( new Vector2(x, y), world, camera );
				newImage.setRGB(x, y, color.getRGB());
			}
		}
		return newImage;
	}
	
}
