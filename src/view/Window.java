package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Controller;
import model.Hero;
import model.Maze;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelStats stats;
	private PanelGame game;
	private JMB jmb;
	private Maze maze;

	public Window(Maze maze) {
		this.maze = maze;
		jmb = new JMB();
		stats = new PanelStats();
		game = new PanelGame(maze);
		initComponents();
	}

	private void initComponents() {
		this.setTitle("Take all the gold");
		setIconImage(new ImageIcon(getClass().getResource("/icons/icon.png")).getImage());
		this.setJMenuBar(jmb);
		this.setLayout(new BorderLayout());
		this.add(stats, BorderLayout.NORTH);
		setExtendedState(MAXIMIZED_BOTH);
		setPreferredSize(new Dimension(1000, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		newLevel();
		addKeyListener(Controller.getController());
		this.setVisible(true);
	}

	public void newLevel() {
		game.setSize(this.getWidth(), this.getHeight());
		game.addKeyListener(Controller.getController());
		this.add(game, BorderLayout.CENTER);
		game.repaint();
	}

	public void repaintStats(Hero hero, String time) {
		stats.setLives("" + hero.getLifes());
		stats.setTime(time);
		stats.setPoints("" + hero.getPoints());
		stats.setLevel("" + maze.level);
	}

	public void repaintAll(Hero hero, String time) {
		repaintStats(hero, time);
		game.repaintBlocks();
	}

	public PanelStats getStats() {
		return stats;
	}

	public PanelGame getGame() {
		return game;
	}

	public void reInit(Maze maze) {
		game = new PanelGame(maze);
		stats.setLives("" + maze.getHero().getLifes());
		stats.setPoints("" + maze.getHero().getPoints());
		stats.setLevel("" + maze.level);
		add(stats, BorderLayout.NORTH);
		add(game, BorderLayout.CENTER);
	}
	
	public void pauseGame() {
		jmb.pause();
	}
	
	public void resumeGame() {
		jmb.resume();
	}
	
}
