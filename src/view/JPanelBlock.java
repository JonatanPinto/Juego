package view;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Enemie;
import model.Maze;
import model.Move;
import model.Position;

public class JPanelBlock extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private String string;
	private Move move;
	private int level;
	
	public JPanelBlock(String string, int level) {
		this.string = string;
		move = Move.RIGHT;
		this.level = level;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawComponent(g);
	}

	public void drawComponent(Graphics graphics) {
		if (!string.equals("1")) {
			if (level % 2 == 0)
				drawComponent(graphics, "street.jpg");
			else
				drawComponent(graphics, "grass.png");
		}
		if (string.equals("2")) {
			drawComponent(graphics, move.name().toLowerCase() + "1.fw.png");
		} else if (string.equals("3")) {
			drawComponent(graphics, "oro.png");
		} else if (string.equals("4")) {
			drawComponent(graphics, move.name().toLowerCase() + getMonster()  + ".monster.png");
		} else if (string.equals("5")) {
			drawComponent(graphics, "bullet" + move.name().charAt(0) + ".png");
		}

		if (string.equals("1")) {
			if (level % 2 == 0)
				drawComponent(graphics, "wall3.png");
			else
				drawComponent(graphics, "wood.jpg");
		}
	}

	public int getMonster() {
		int aux = 0;
		if(level == 1 || level == 5 || level == 9) {
			aux = 1;
		}else if(level == 2 || level == 6 || level == 10) {
			aux = 2;
		}else if(level == 3 || level == 7 || level == 11) {
			aux = 3;
		}else if(level == 4 || level == 8 || level == 12) {
			aux = 4;
		}
		return aux;
	}
	
	public void drawComponent(Graphics graphics, String name) {
		graphics.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/" + name)), 0, 0,
				getWidth(), getHeight(), this);
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
		repaint();
	}
}
