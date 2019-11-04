package Set18_x;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Main extends JFrame {

	Graphics g;

	public static void main(String[] args) {
		int x1 = 150, y1 = 150, 
				x2 = 100, y2 = 400, 
				x3 = 200, y3 = 200;
		Main main = new Main();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(100);
		} catch (Exception e) {

		}
		
		main.plotTriangulo(x1,y1,x2,y2,x3,y3);
		
		int tx = 20, ty = 150;
		int angulo1 = -25;
		
		int angulo2 = -180;

		float sx = 3.3f, sy = 0.8f;
		main.translate(x1, y1, x2, y2, x3, y3, tx, ty);
		main.scale(x1, y1, x2, y2, x3, y3, sx, sy);
		main.rotate(x1, y1, x2, y2, x3, y3, angulo1);
		main.rotateStatic(x1, y1, x2, y2, x3, y3, angulo2);
	}

	public Main() {

		setSize(800, 600);
		setTitle("ExemploPrintReta");
		setVisible(true);

	}

	public void drawLineDDA(int x1, int y1, int x2, int y2) {
		double y = y1, x = x1;
		double m = (double) (y2 - y1) / (x2 - x1);
		System.out.println("m:" + m);

		if (m >= 1) {
			if (x1 < x2) {
				for (x = x1; x <= x2; x++) {
					y += m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			} else {
				y = y2;
				for (x = x2; x <= x1; x++) {
					y += m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			}

		} else {
//			System.out.println("plot("+x1+","+y1+") - ("+x2+", "+y2+")");
			if (y1 < y2) {
				for (y = y1; y <= y2; y++) {
					x += 1 / m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			} else {
				x = x2;
				for (y = y2; y <= y1; y++) {
					x += 1 / m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			}

		}
	}

	public void drawLineDeclive(int x1, int y1, int x2, int y2) {

		double m = (double) (y2 - y1) / (x2 - x1);
		double b = y1 - m * x1;

		for (int x = x1; x <= x2; x++) {
			int y = (int) (m * x + b);
			plot(x, y, new Color(200, 0, 203));
		}
	}

	public void translate(int x1, int y1, int x2, int y2, int x3, int y3, int tx, int ty) {
		x1 += tx;
		y1 += ty;
		x2 += tx;
		y2 += ty;
		x3 += tx;
		y3 += ty;
		
		this.plotTriangulo(x1,y1,x2,y2,x3,y3);
		
	}
	
	public void scale(int x1, int y1, int x2, int y2, int x3, int y3, float sx, float sy) {
		x1 *= sx;
		y1 *= sy;
		x2 *= sx;
		y2 *= sy;
		x3 *= sx;
		y3 *= sy;
	
		this.plotTriangulo(x1,y1,x2,y2,x3,y3);
		
	}
	
	public void rotate(int x1, int y1, int x2, int y2, int x3, int y3, int a) {
		x1 = (int)(x1 * Math.cos(Math.toRadians(a))-y1*Math.sin(Math.toRadians(a)));
		y1 = (int)(x1 * Math.sin(Math.toRadians(a))+y1*Math.cos(Math.toRadians(a)));
		x2 = (int)(x2 * Math.cos(Math.toRadians(a))-y2*Math.sin(Math.toRadians(a)));
		y2 = (int)(x2 * Math.sin(Math.toRadians(a))+y2*Math.cos(Math.toRadians(a)));
		x3 = (int)(x3 * Math.cos(Math.toRadians(a))-y3*Math.sin(Math.toRadians(a)));
		y3 = (int)(x3 * Math.sin(Math.toRadians(a))+y3*Math.cos(Math.toRadians(a)));
	
		this.plotTriangulo(x1,y1,x2,y2,x3,y3);
		
	}
	
	public void rotateStatic(int x1, int y1, int x2, int y2, int x3, int y3, int a) {
		float cx = (x1+x2+x3)/3;
		float cy = (y1+y2+y3)/3;		
		
		x1 = (int)(x1 * Math.cos(Math.toRadians(a))-y1*Math.sin(Math.toRadians(a)) 
			+ cx - cx * Math.cos(Math.toRadians(a)) + cy * Math.sin(Math.toRadians(a)));
		y1 = (int)(x1 * Math.sin(Math.toRadians(a))+y1*Math.cos(Math.toRadians(a)) 
			+ cy - cx * Math.sin(Math.toRadians(a)) - cy * Math.cos(Math.toRadians(a)));
		x2 = (int)(x2 * Math.cos(Math.toRadians(a))-y2*Math.sin(Math.toRadians(a)) 
			+ cx - cx * Math.cos(Math.toRadians(a)) + cy * Math.sin(Math.toRadians(a)));
		y2 = (int)(x2 * Math.sin(Math.toRadians(a))+y2*Math.cos(Math.toRadians(a)) 
			+ cy - cx * Math.sin(Math.toRadians(a)) - cy * Math.cos(Math.toRadians(a)));
		x3 = (int)(x3 * Math.cos(Math.toRadians(a))-y3*Math.sin(Math.toRadians(a)) 
			+ cx - cx * Math.cos(Math.toRadians(a)) + cy * Math.sin(Math.toRadians(a)));
		y3 = (int)(x3 * Math.sin(Math.toRadians(a))+y3*Math.cos(Math.toRadians(a)) 
			+ cy - cx * Math.sin(Math.toRadians(a)) - cy * Math.cos(Math.toRadians(a)));
	
		this.plotTriangulo(x1,y1,x2,y2,x3,y3);
		
	}
	
	public void plotTriangulo(int x1, int y1, int x2, int y2, int x3, int y3) {

		this.drawLineDDA(x1, y1, x2, y2);
		this.drawLineDDA(x3, y3, x2, y2);
		this.drawLineDDA(x1, y1, x3, y3);

	}

	public void plot(int x, int y, Color color) {
		if (g == null) {
			g = getGraphics();
		}

		g.setColor(color);

		g.fillOval(x, y, 2, 2);
	}
}
