package main.math;

public class MathLibrary {

	public static int[] getCircleXY( double radius, double steppedDelta ) {
		int x = (int) (radius * Math.cos( steppedDelta * Math.PI * 2 ));
		int y = (int) (radius * Math.sin( steppedDelta * Math.PI * 2 ));
		return new int[] {x, y};
	}
	
}
