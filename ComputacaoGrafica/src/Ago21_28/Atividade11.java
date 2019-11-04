package Ago21_28;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Atividade11 extends JFrame {
	
	// Pedro Gallon Alves - 816124368
	// Jhonnanthn Carlos William Balsas - 816119078

	Graphics g;

	public static void main(String[] args) {

		int[] x, y;

		boolean test = false;
		if (!test) {
			int qtd = Integer.parseInt(JOptionPane.showInputDialog("Qtd de pontos: "));
			x = new int[qtd];
			y = new int[qtd];
			for (int i = 0; i < qtd; i++) {
				x[i] = Integer.parseInt(JOptionPane.showInputDialog("P" + (i + 1) + "; X: "));
				y[i] = Integer.parseInt(JOptionPane.showInputDialog("P" + (i + 1) + "; Y: "));
			}

		} else {
			x = new int[5];
			x[0] = 300;
			x[1] = 400;
			x[2] = 150;
			x[3] = 250;
			x[4] = 450;

			y = new int[5];
			y[0] = 300;
			y[1] = 200;
			y[2] = 100;
			y[3] = 450;
			y[4] = 180;

		}
	
		Atividade11 main = new Atividade11();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}
		for (int i = 0; i < x.length - 1; i++) {
				main.drawLineDDA(x[i], y[i], x[i+1], y[i+1]);
	
		}

	}

	public Atividade11() {
		setSize(800, 800);
		setTitle("ExemploPrintReta");
		setVisible(true);

	}

	public void drawLineDDA(int x1, int y1, int x2, int y2) {
		double y = y1, x = x1;
		double m = (double) (y2 - y1) / (x2 - x1);
		System.out.println("m:" + m);

		if (m >= 1) {
			if(x1<x2) {
				for (x = x1; x <= x2; x++) {
					y += m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			}else {
				y = y2;
				for (x = x2; x <= x1; x++) {
					y += m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			}
			
		} else {
//			System.out.println("plot("+x1+","+y1+") - ("+x2+", "+y2+")");
			if(y1<y2) {
				for (y = y1; y <= y2; y++) {
					x += 1 / m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			}else {
				x = x2;
				for (y = y2; y <= y1; y++) {
					x += 1 / m;
					plot((int) x, (int) y, new Color(0, 60, 203));
				}
			}
			
		}
	}

	public void plot(int x, int y, Color color) {
		if (g == null) {
			g = getGraphics();
		}

		g.setColor(color);

		g.fillOval(x, y, 2, 2);
	}

}
