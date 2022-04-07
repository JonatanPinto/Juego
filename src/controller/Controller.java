package controller;

/**
 * @author Andrés Felipe Flórez, Jonatan Pinto - Programación III
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import SUI.SoundBack;
import SUI.Sounds;
import model.Maze;
import persistence.TextFile;
import view.Window;
public class Controller implements ActionListener, KeyListener {

	private static Controller controller;
	private TextFile file;
	private Window window;
	private Maze maze;
	private Sounds sounds;
	private SoundBack soundBack;
	private JOptionPane jOptionPane;
	private model.Time time;
	private static String ip;

	public String getIP() {
		return ip;
	}
	
	public void setIP() {
		ip = JOptionPane.showInputDialog("Escriba la direccion ip");
	}
	private boolean pause;

	private Controller() {
	}

	public static Controller getController() {
		if (controller == null)
			controller = new Controller();
		return controller;
	}

	public void initComponents() {
		setIP();
		readFileLab();
		try {
			maze = new Maze(file.readFile(), 4, 4, 500, 1,ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jOptionPane = new JOptionPane();
		window = new Window(maze);
		window.setVisible(true);
		time = new model.Time(maze.level);
		sounds = new Sounds("");
		soundBack = new SoundBack("sounds/soundBackLevel" + maze.level + ".mp3");
		start();
	}

	public void readFileLab() {
		try {
			file = new TextFile(System.getProperty("user.dir") + "/level0.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				while (true) {
					window.repaintAll(maze.getHero(), time.getChron());
					Thread.sleep(10);
					if (maze.getHero().getLifes() == 0) {
						lose();
						showJOptionPaneMessage("Perdiste todas tus vidas :(", "You Lost", "gameOver.jpg");
					} else if (time.isZero()) {
						lose();
						showJOptionPaneMessage("El tiempo ha terminado, Has perdido", "The time is Over",
								"timeIsOver.jpg");
					} else if (maze.getHero().getPoints() == maze.numberMeals + 1) {
						nextLevel();
					}
				}
			}
		};
		swingWorker.run();

	}

	public void lose() {
		maze.stopEnemies();
		sounds.setSoundPath("youLose.wav");
		sounds.playSound();
		reinitLevel();
	}

	public void nextLevel() {
		soundBack.pauseSoundBack();
		sounds.setSoundPath("tada.wav");
		sounds.playSound();
		maze.pauseEnemies();
		showJOptionPaneMessage("Sube de Nivel", "UP Level", "bravo.jpg");
		upLevel();
		showJOptionPaneMessage("Nivel : " + maze.level, "New Level", "lestsGo.jpg");
		maze.resumeEnemies();
		soundBack.resumeSoundBack();
	}

	public void showJOptionPaneMessage(String title, String message, String iconPath) {
		Icon icon = new ImageIcon(getClass().getResource("/icons/" + iconPath));
		jOptionPane.showMessageDialog(null, title, message, JOptionPane.PLAIN_MESSAGE, icon);
	}

	public void reinitLevel() {
		maze.stopEnemies();
		readFileLab();
		try {
			maze = new Maze(file.readFile(), maze.numberEnemies, maze.numberMeals, maze.difficult, maze.level,ip);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
		time = new model.Time(maze.level);
		window.reInit(maze);
		window.repaintAll(maze.getHero(), time.getChron());
	}

	public void upLevel() {
		maze.stopEnemies();
		readFileLab();
		try {
			maze.upLevel(file.readFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		soundBack.setSong("sounds/soundBackLevel" + maze.level + ".mp3");
		window.reInit(maze);
		time = new model.Time(maze.level);
		window.repaintAll(maze.getHero(), time.getChron());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "viewControls":
			showJOptionPaneMessage("", "Controles", "Controles.jpg");
			break;

		case "pauseGame":
			pauseGame();
			break;

		case "resumeGame":
			resumeGame();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_UP:
			moveUp();
			break;
		case KeyEvent.VK_DOWN:
			moveDown();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		case KeyEvent.VK_SPACE:
			shootHero();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	public void pauseGame() {
		maze.pauseEnemies();
		window.pauseGame();
		time.pauseTime();
		pause = true;
	}

	public void resumeGame() {
		maze.resumeEnemies();
		window.resumeGame();
		time.resumeTime();
		pause = false;
	}

	public void moveUp() {
		maze.moveHeroUP();
		playSoundMove();
	}

	public void moveDown() {
		maze.moveHeroDOWN();
		playSoundMove();
	}

	public void moveRight() {
		maze.moveHeroRIGHT();
		playSoundMove();
	}

	public void moveLeft() {
		maze.moveHeroLEFT();
		playSoundMove();
	}

	public void playSoundMove() {
		sounds.setSoundPath("movee.wav");
		sounds.playSound();
	}

	public void shootHero() {
		sounds.setSoundPath("shootS.wav");
		sounds.playSound();
		maze.getHero().shoot();
	}
}
