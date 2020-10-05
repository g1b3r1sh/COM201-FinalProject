package finalProject;

public class Util {
	// Returns smallest angle between a1 to a2
	// If clockwise, returns positive. If counterclockwise, returns negative
	// Algorithm by M. Usman Khan from https://stackoverflow.com/questions/7570808/how-do-i-calculate-the-difference-of-two-angle-measures
	public static int subtractAngles(int a, int b)
	{
		int d = Math.abs(a - b) % 360; 
		int r = d > 180 ? 360 - d : d;

		//calculate sign 
		int sign = (a - b >= 0 && a - b <= 180) || (a - b <=-180 && a- b>= -360) ? 1 : -1; 
		r *= sign;
		return r;
	}
}
