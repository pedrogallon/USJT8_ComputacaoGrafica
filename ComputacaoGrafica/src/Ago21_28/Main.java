package Ago21_28;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {

	public static void main(String[] args) {
		int x1, y1, x2, y2, r1, r2;
		boolean test = true;
		if (!test) {
			x1 = Integer.parseInt(JOptionPane.showInputDialog("X1: "));
			y1 = Integer.parseInt(JOptionPane.showInputDialog("Y1: "));
			x2 = Integer.parseInt(JOptionPane.showInputDialog("X2: "));
			y2 = Integer.parseInt(JOptionPane.showInputDialog("Y2: "));
			
			r1 = Integer.parseInt(JOptionPane.showInputDialog("Raio 1: "));
			r2 = Integer.parseInt(JOptionPane.showInputDialog("Raio 2: "));
			
		} else {
			x1 = 200;
			y1 = 15;
			x2 = 520;
			y2 = 600;
			r1 = 300;
			r2 = 500;
		}

		double m = (double) (y2 - y1) / (x2 - x1);
		double b = y1 - m * x1;
		System.out.println(m);
		System.out.println("P1 (" + x1 + "," + y1 + "), P2 (" + x2 + "," + y2 + "), m: " + m + ", b: " + b);

		Main main = new Main();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(100);
		} catch (Exception e) {

		}
		// Set 1 - Lines e Circles
		main.drawLineDeclive(x1, y1, x2, y2, m, b);
		main.drawLineDDA(x1 + 55, y1, x2 + 55, y2, m, b);
		main.drawCircle(r1);
		main.drawCircleCosSen(r2);

	}

	public Main() {
		setSize(800, 600);
		setTitle("ExemploPrintReta");
		setVisible(true);

	}
	
	
	// Declive
	public void drawLineDeclive(int x1, int y1, int x2, int y2, double m, double b) {

		for (int x = x1; x <= x2; x++) {
			int y = (int) (m * x + b);
			plot(x, y, new Color(200, 0, 203));
		}
	}

	// DDA 28Ago
	public void drawLineDDA(int x1, int y1, int x2, int y2, double m, double b) {
		double y = y1, x = x1;

		if (m >= 1) {
			for (x = x1; x <= x2; x++) {
				y += m;
				plot((int) x, (int) y, new Color(0, 60, 203));
			}
		} else {
			for (y = y1; y <= y2; y++) {
				x += 1 / m;
				plot((int) x, (int) y, new Color(0, 60, 203));
			}
		}
	}

	public void drawCircle(int r) {
		float x, y;
		for (x = 0; x <= r; x++) {
			y = (int) Math.sqrt(r * r - x * x);
			plot((int) x, (int) y, new Color(100, 200, 23));
		}

	}

	public void drawCircleCosSen(float r) {
		float x, y;

		for (float a = 0; a <= 90; a = a + 0.2f) {
			x = (float) (r * Math.cos(Math.toRadians(a)));
			y = (float) (r * Math.sin(Math.toRadians(a)));
			plot((int) x, (int) y, new Color(200, 60, 23));
		}

	}
	
	public void plot(int x, int y, Color color) {
		Graphics g = getGraphics();
		g.setColor(color);
		g.fillOval(x, y, 2, 2);
	}

}
