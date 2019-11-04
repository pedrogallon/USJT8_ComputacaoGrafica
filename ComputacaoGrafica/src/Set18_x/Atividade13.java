package Set18_x;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Professor: Pier
 * Pedro Gallon Alves - 816124368
 * Jhonnanthn Carlos William Balsas - 816119078
 */

public class Atividade13 extends JFrame implements KeyListener {

	Graphics g;

	// pontos em float, para menor perda de informação após cálculos;
	// cast para int para o plot() com bresenham
	static float[] x, y;
	// centroide
	static int Cx, Cy;
	// evento pressionado (t, r ou s)
	static char event = ' ';

	public static void main(String[] args) {


		int qtd = Integer.parseInt(JOptionPane.showInputDialog("Qtd de vertices: "));
		x = new float[qtd];
		y = new float[qtd];

		//se houver 3 ou mais pontos, o usuario insere os pontos. Se não, é carregado um default
		if (qtd > 2) {

			for (int i = 0; i < qtd; i++) {
				x[i] = Integer.parseInt(JOptionPane.showInputDialog("P" + (i + 1) + "; X: "));
				y[i] = Integer.parseInt(JOptionPane.showInputDialog("P" + (i + 1) + "; Y: "));
			}
		} else {
			JOptionPane.showMessageDialog(null, "Qtd de pontos < 3; Carregando poligono default");
			qtd = 4;
			x = new float[qtd];
			x[0] = 200;
			x[1] = 300;
			x[2] = 300;
			x[3] = 150;

			y = new float[qtd];
			y[0] = 200;
			y[1] = 200;
			y[2] = 300;
			y[3] = 300;
		}

		Atividade13 main = new Atividade13();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}
		
		// desenha poligono original
		main.plotPolig();
		
		System.out.println("Instruções; Aperte as seguintes teclas:");
		System.out.println("T: Transladar;	Setas: Direção;");
		System.out.println("S: Escalar; 	Setas: Cima = Aumentar, Baixo = Diminuir;");
		System.out.println("R: Rotacionar;	Setas: Esquerda = Antihorario; Direita = Horario");

	}

	// percorre os vetores para desenhar poligono
	private void plotPolig() {
		for (int i = 0; i < x.length - 1; i++) {
			drawLine((int) x[i], (int) y[i], (int) x[i + 1], (int) y[i + 1]);
		}
		drawLine((int) x[0], (int) y[0], (int) x[x.length - 1], (int) y[y.length - 1]);
	}

	public Atividade13() {
		setSize(600, 600);
		setTitle("At13");
		setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}
	
	
	//bresenham
	public void drawLine(int x1, int y1, int x2, int y2) {
		int dx = Math.abs(x2 - x1);
		int dy = -Math.abs(y2 - y1);
		int sx = x1 < x2 ? 1 : -1;
		int sy = y1 < y2 ? 1 : -1;
		int e1 = dx + dy;
		int e2;

		for (;;) {
			plot(x1, y1);
			e2 = 2 * e1;
			if (e2 >= dy) {
				if (x1 == x2)
					break;
				e1 += dy;
				x1 += sx;
			}
			if (e2 <= dx) {
				if (y1 == y2)
					break;
				e1 += dx;
				y1 += sy;
			}
		}
	}

	public void plot(int x, int y) {
		if (g == null) {
			g = getGraphics();
			g.setColor(new Color(70, 70, 70));
		}
		g.fillOval(x, y, 2, 2);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// listener para as letras
		if (e.getKeyCode() == 84) {
			System.out.println("Translate...");
			event = 't';
		}
		if (e.getKeyCode() == 82) {
			System.out.println("Rotate...");
			event = 'r';
		}
		if (e.getKeyCode() == 83) {
			System.out.println("Scale...");
			event = 's';
		}

		// listener para as setas; só acessado se uma letra tiver sido selecionada
		// esquerda =37 cima=38 direita=39 baixo=40
		if (e.getKeyCode() > 36 && e.getKeyCode() < 41) {
			int dir = e.getKeyCode(); // direction
			if (event != ' ') {

				switch (event) {
				case 't':
					//translate
					translate(dir);
					plotPolig();
					break;
				case 'r':
					// translate para 0,0; rotaciona; translate para centroide
					if (dir == 37 || dir == 39) {
						getCentroid();
						translateToOrigin();
						rotate(dir);
						translateToCentroid();
						plotPolig();
					} else {
						System.out.println("Canceledo (use esquerda ou direita)");
					}
					break;
				case 's':
					// translate para 0,0; escala; translate para centroide
					if (dir == 38 || dir == 40) {
						getCentroid();
						translateToOrigin();
						scale(dir);
						translateToCentroid();
						plotPolig();
					} else {
						System.out.println("Canceledo (use cima ou baixo)");
					}

					break;
				}
				// limpa evento
				event = ' ';

			}
		}

	}

	// move o centroide e pontos para 0,0
	public void translateToOrigin() {
		for (int i = 0; i < x.length; i++) {
			x[i] -= Cx;
			y[i] -= Cy;
		}
	}

	// retorna pontos ao centroide
	public void translateToCentroid() {
		for (int i = 0; i < x.length; i++) {
			x[i] += Cx;
			y[i] += Cy;
		}

	}

	
	public void translate(int dir) {
		int ty = 0;
		int tx = 0;

		// verifica a tireção para definir ty ou tx
		// incremento de 20
		// esquerda =37 cima=38 direita=39 baixo=40
		switch (dir) {
		case 37:
			System.out.println("Left");
			tx = -20;
			break;
		case 38:
			System.out.println("Up");
			ty = -20;
			break;
		case 39:
			System.out.println("Right");
			tx = 20;
			break;
		case 40:
			System.out.println("Down");
			ty = 20;
			break;
		}

		for (int i = 0; i < x.length; i++) {
			x[i] += tx;
			y[i] += ty;
		}

	}

	public void scale(int dir) {
		float s = 0;

		
		// verifica direção para definir escala
		// aumenta ou diminui em 0.1
		switch (dir) {

		case 38:
			System.out.println("Up");
			s = 1.1f;
			break;
		case 40:
			System.out.println("Down");
			s = 0.9f;
			break;
		}

		for (int i = 0; i < x.length; i++) {
			x[i] *= s;
			y[i] *= s;
		}

	}

	public void rotate(int dir) {

		int a = 0;
		
		// verifica direção para definir sentido de rotação
		// rotaciona em 5 graus
		switch (dir) {

		case 37:
			System.out.println("counter");
			a = -5;
			break;
		case 39:
			System.out.println("clockwise");
			a = 5;
			break;
		}

		for (int i = 0; i < x.length; i++) {
			x[i] = (float) (x[i] * Math.cos(Math.toRadians(a)) - y[i] * Math.sin(Math.toRadians(a)));
			y[i] = (float) (x[i] * Math.sin(Math.toRadians(a)) + y[i] * Math.cos(Math.toRadians(a)));
		}

	}

	//pega centroide de acordo com posição atual dos pontos
	private void getCentroid() {
		Cx = 0;
		Cy = 0;
		for (int i = 0; i < x.length; i++) {
			Cx += x[i];
			Cy += y[i];
		}
		Cx = Cx / x.length;
		Cy = Cy / x.length;

	}

}
