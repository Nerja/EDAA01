package mountain;

public class Side {
	private Point p1, p2, m;
	public Side(Point p1, Point p2, Point m) {
		this.p1 = p1;
		this.p2 = p2;
		this.m = m;
	}
	
	public Point getMiddle() {
		return m;
	}
	
	@Override
	public boolean equals(Object x) {
		if(x instanceof Side) {
			Side xP = (Side)x;
			return p1.equals(xP.p1) && p2.equals(xP.p2) || p1.equals(xP.p2) && p2.equals(xP.p1);
		} else {
			return false;
		}
	}
}
