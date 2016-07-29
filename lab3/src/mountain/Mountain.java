package mountain;

import java.util.LinkedList;
import java.util.List;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

	private Point a, b, c;
	private double dev;
	private List<Side> sides;
	public Mountain(Point a, Point b, Point c, double dev) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		sides = new LinkedList<Side>();
	}
	
	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		drawRect(g, order, a, b, c, dev);
	}
	
	private void drawRect(TurtleGraphics g, int order, Point a, Point b, Point c, double dev) {
		if(order == 0) {
			g.moveTo(a.getX(), a.getY());
			g.forwardTo(b.getX(), b.getY());
			g.forwardTo(c.getX(), c.getY());
			g.forwardTo(a.getX(), a.getY());
		} else {
			Point m_ab = Point.getMiddle(a, b, dev, sides);
			Point m_bc = Point.getMiddle(b, c, dev, sides);
			Point m_ca = Point.getMiddle(c, a, dev, sides);
			drawRect(g, order-1, a, m_ab, m_ca, dev/2);
			drawRect(g, order-1, m_ab, b, m_bc, dev/2);
			drawRect(g, order-1, m_bc, c, m_ca, dev/2);
			drawRect(g, order-1, m_ab, m_bc, m_ca, dev/2);
		}
	}

}
