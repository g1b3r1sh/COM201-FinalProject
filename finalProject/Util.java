package finalProject;

/**
 * Utility class containing useful static methods for Program.
 */
public class Util {
	/**
	 * Returns angle between two angles. Taken from https://stackoverflow.com/questions/7570808/how-do-i-calculate-the-difference-of-two-angle-measures
	 * 
	 * @author M. Usman Khan
	 * @param a First angle.
	 * @param b Second angle.
	 * @return Smallest angle between a1 to a2. If clockwise, returns positive. If counterclockwise, returns negative.
	 */
	public static int subtractAngles(int a, int b)
	{
		int d = Math.abs(a - b) % 360; 
		int r = d > 180 ? 360 - d : d;

		//calculate sign 
		int sign = (a - b >= 0 && a - b <= 180) || (a - b <=-180 && a- b>= -360) ? -1 : 1; 
		r *= sign;
		return r;
	}
}
