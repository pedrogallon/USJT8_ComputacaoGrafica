package Set18_x;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Atividade12 extends JFrame {

	// Pedro Gallon Alves - 816124368

	Graphics g;

	public static void main(String[] args) {

		Atividade12 main = new Atividade12();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}
		
		int width = 600;
		int height = 600;

		for (int i = 0; i < width; i+=20) {
			main.drawBresenhamQ4(0,i,i,width, width, height);
			main.drawBresenhamQ3(0,i,i,width, width, height);
			main.drawBresenhamQ2(0,i,i,width, width, height);
			main.drawBresenhamQ1(0,i,i,width, width, height);
		}
		
		main.drawBresenhamQ1(0, 0, 250, 250, width, height);
		main.drawBresenhamQ2(0, 0, 250, 250, width, height);
		main.drawBresenhamQ3(0, 0, 250, 250, width, height);
		main.drawBresenhamQ4(0, 0, 250, 250, width, height);

	}

	public Atividade12() {
		setSize(600, 600);
		setTitle("At12");
		setVisible(true);

	}
	
	

	public void drawBresenhamQ1(int x1, int y1, int x2, int y2, int w, int h) {

		int dx = Math.abs(x2 - x1);
		int dy = -Math.abs(y2 - y1);
		
		int sx = x1<x2 ? 1 : -1;
		int sy = y1<y2 ? 1 : -1;
		
		int e1 = dx+dy;
		int e2;
		

		for (;;) {
			plot(x1,600-y1);
			e2 = 2*e1;
			if (e2 >= dy) {
				if (x1 == x2) break;
				e1 += dy;
				x1 += sx;
			}if (e2 <= dx) {
				if (y1 == y2) break;
				e1 += dx;
				y1 += sy;
			}
		}

	}
	
	public void drawBresenhamQ2(int x1, int y1, int x2, int y2, int w, int h) {

		int dx = Math.abs(x2 - x1);
		int dy = -Math.abs(y2 - y1);
		
		int sx = x1<x2 ? 1 : -1;
		int sy = y1<y2 ? 1 : -1;
		
		int e1 = dx+dy;
		int e2;
		

		for (;;) {
			plot(600-x1,600-y1);
			e2 = 2*e1;
			if (e2 >= dy) {
				if (x1 == x2) break;
				e1 += dy;
				x1 += sx;
			}if (e2 <= dx) {
				if (y1 == y2) break;
				e1 += dx;
				y1 += sy;
			}
		}

	}
	
	public void drawBresenhamQ3(int x1, int y1, int x2, int y2, int w, int h) {

		int dx = Math.abs(x2 - x1);
		int dy = -Math.abs(y2 - y1);
		
		int sx = x1<x2 ? 1 : -1;
		int sy = y1<y2 ? 1 : -1;
		
		int e1 = dx+dy;
		int e2;
		

		for (;;) {
			plot(600-x1,y1);
			e2 = 2*e1;
			if (e2 >= dy) {
				if (x1 == x2) break;
				e1 += dy;
				x1 += sx;
			}if (e2 <= dx) {
				if (y1 == y2) break;
				e1 += dx;
				y1 += sy;
			}
		}

	}
	
	
	public void drawBresenhamQ4(int x1, int y1, int x2, int y2, int w, int h) {

		int dx = Math.abs(x2 - x1);
		int dy = -Math.abs(y2 - y1);
		
		int sx = x1<x2 ? 1 : -1;
		int sy = y1<y2 ? 1 : -1;
		
		int e1 = dx+dy;
		int e2;
		

		for (;;) {
			plot(x1,y1);
			e2 = 2*e1;
			if (e2 >= dy) {
				if (x1 == x2) break;
				e1 += dy;
				x1 += sx;
			}if (e2 <= dx) {
				if (y1 == y2) break;
				e1 += dx;
				y1 += sy;
			}
		}

	}

	public void plot(int x, int y) {
		if (g == null) {
			g = getGraphics();
			g.setColor(new Color(50, 50, 50));
		}
		g.fillOval(x, y, 2, 2);
	}

}
