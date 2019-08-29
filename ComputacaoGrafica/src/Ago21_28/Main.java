package Ago21_28;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {

	public static void main(String[] args) {
		int x1, y1, x2, y2;
		boolean test = true;
		if (!test) {
			x1 = Integer.parseInt(JOptionPane.showInputDialog("X1: "));
			y1 = Integer.parseInt(JOptionPane.showInputDialog("Y1: "));
			x2 = Integer.parseInt(JOptionPane.showInputDialog("X2: "));
			y2 = Integer.parseInt(JOptionPane.showInputDialog("Y2: "));
		} else {
			x1 = 10;
			y1 = 15;
			x2 = 520;
			y2 = 220;
		}

		double m =  (double) (y2 - y1) / (x2 - x1);
		double b = y1 - m * x1;
		System.out.println(m);
		System.out.println("P1 ("+x1+","+y1+"), P2 ("+x2+","+y2+"), m: "+m+", b: "+b);

		Main main = new Main();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(100);
		} catch (Exception e) {

		}

		main.drawLineDeclive(x1, y1, x2, y2, m, b);

		main.drawLineDDA(x1+55, y1, x2+55, y2, m, b);
//		main.print();
//		JOptionPane.showMessageDialog(null, "m: "+ m);
//		JOptionPane.showMessageDialog(null, "b: "+ b);
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
			plot(x, y);
		}
	}
	
	// DDA 28Ago
	public void drawLineDDA(int x1, int y1, int x2, int y2, double m, double b) {
		double y = y1, x = x1;
		
		if (m >= 1) {
			for (x = x1; x <= x2; x++) {
				y += m;
				plot((int)x, (int)y);
			}
		}else {
			for (y = y1; y <= y2; y++) {
				x += 1/m;
				plot((int)x, (int)y);
			}
		}
	}

	public void plot(int x, int y) {
		Graphics g = getGraphics();
		g.setColor(new Color(200, 60, 23));
		g.fillOval(x, y, 2,2);
	}

}
