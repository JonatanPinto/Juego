package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Maze;
import model.Position;

public class PanelGame extends JPanel {
	private JPanelBlock[][] jPanels;
	private Maze maze;

	public PanelGame(Maze maze) {
		this.maze = maze;
		init();
	}

	private void init() {
		setLayout(new GridLayout(maze.getMaze().length, maze.getMaze().length));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		jPanels = new JPanelBlock[maze.getRows()][maze.getCols()];
		initMat();
		repaintBlocks();
	}

	public void repaintBlocks() {
		removeAll();
		setBlocks();
		updateUI();
		repaint();
	}

	public void setBlocks() {
		String matriz[][] = maze.getMaze();
		for (int i = 0; i < maze.getRows() - 1; i++) {
			for (int j = 0; j < maze.getCols() - 1; j++) {
				jPanels[i][j] = new JPanelBlock("0", maze.level);
				jPanels[i][j].setMove(maze.getHero().getMove());
				jPanels[i][j].setBorder(BorderFactory.createEmptyBorder());
				if (matriz[i][j].equals("1")) {
					jPanels[i][j].setString("1");
				} else if (matriz[i][j].equals("2")) {
					jPanels[i][j].setString("2");
				} else if (matriz[i][j].equals("3")) {
					jPanels[i][j].setString("3");
				} else if (matriz[i][j].equals("4")) {
					jPanels[i][j].setMove(maze.findEnemiePosition(new Position(j, i)).getMove());
					jPanels[i][j].setString("4");
				} else if (matriz[i][j].equals("5")) {
					jPanels[i][j].setMove(maze.getHero().findShoot(new Position(j, i)).getMove());
					jPanels[i][j].setString("5");
				} else {
					jPanels[i][j].setString("0");
				}
				add(jPanels[i][j]);
			}
		}
	}

	public void initMat() {
		for (int i = 0; i < maze.getRows(); i++) {
			for (int j = 0; j < maze.getCols(); j++) {
				jPanels[i][j] = new JPanelBlock("0", maze.level);
				jPanels[i][j].repaint();
			}
		}
	}
}
