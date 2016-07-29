package mountain;

import java.util.List;
import java.util.ListIterator;

public class Point {
	private double x, y;

	/** Constructs and initializes a point at the specified (x,y) location. */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/** 
	 * Returns the x coordinate. 
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}

	/** 
	 * Returns the y coordinate. 
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/** Indicates whether some other object is "equal to" this one.
	 * @param  obj the reference object with which to compare
	 * @return  true if this object is the same as the obj argument; false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return x == p.x && y == p.y;
		} else {
			return false;
		}
	}

	/** Moves this point to the specified (x,y) location.
	 * post: the point is moved to the location (x,y)
	 * @param  x the x coordinate of the new location
	 * @param  y the y coordinate of the new location
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Point getMiddle(Point p1, Point p2, double dev, List<Side> sides) {
		ListIterator<Side> itr = sides.listIterator();
		Side target = new Side(p1, p2, null);
		while(itr.hasNext()) {
			Side s = itr.next();
			if(s.equals(target)) {
				itr.remove();
				return s.getMiddle();
			}
		}
		int middX = (int)Math.round((p1.x+p2.x)/2.0);
		int middY = (int)Math.round((p1.y+p2.y)/2.0+RandomUtilities.randFunc(dev));
		Point middle = new Point(middX, middY);
		target = new Side(p1, p2, middle);
		sides.add(target);
		return middle;
	}
}
