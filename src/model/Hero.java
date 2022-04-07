package model;

import java.io.IOException;
import java.util.LinkedList;

import SUI.Sounds;
import connection.Client;

public class Hero extends Client{
	private int lifes;
	private int points;
	private Maze maze;
	private String map[][];
	private Move move;
	private Position position;
	public boolean injure;
	private LinkedList<Shoot> listShoot;

	public Hero(Maze maze, int lifes, int points, Move move,String ip) {
		super(ip, 12345);
		this.maze = maze;
		map = maze.getMaze();
		this.lifes = lifes;
		this.points = points;
		this.move = move;
		this.position = maze.findHero();
		listShoot = new LinkedList<>();
	}

	public void moveRIGHT() {
		if (position.getX() != maze.getCols() - 2) {
			if (!(map[position.getY()][position.getX() + 1].equals("1"))) {
				setMove(Move.RIGHT);
				check(position.getX() + 1, position.getY());
				map[position.getY()][position.getX()] = "0";
				map[position.getY()][position.getX() + 1] = "2";
				setPositionHero(position.getX() + 1, position.getY());
			}
		}
		try {
			this.write(Move.RIGHT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void moveLEFT() {
		if (position.getX() != 0) {
			if (!(map[position.getY()][position.getX() - 1].equals("1"))) {
				setMove(Move.LEFT);
				check(position.getX() - 1, position.getY());
				map[position.getY()][position.getX()] = "0";
				map[position.getY()][position.getX() - 1] = "2";
				setPositionHero(position.getX() - 1, position.getY());
			}
		}
		try {
			this.write(Move.LEFT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void moveDOWN() {
		if (position.getY() != maze.getRows() - 2) {
			if (!(map[position.getY() + 1][position.getX()].equals("1"))) {
				setMove(Move.DOWN);
				check(position.getX(), position.getY() + 1);
				map[position.getY()][position.getX()] = "0";
				map[position.getY() + 1][position.getX()] = "2";
				setPositionHero(position.getX(), position.getY() + 1);
			}
		}
		try {
			this.write(Move.DOWN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void moveUP() {
		if (position.getY() != 0) {
			if (!(map[position.getY() - 1][position.getX()].equals("1"))) {
				setMove(Move.UP);
				check(position.getX(), position.getY() - 1);
				map[position.getY()][position.getX()] = "0";
				map[position.getY() - 1][position.getX()] = "2";
				setPositionHero(position.getX(), position.getY() - 1);
			}
		}
		try {
			this.write(Move.UP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPositionHero(int x, int y) {
		setPosition(new Position(x, y));
	}

	public void check(int x, int y) {
		Sounds sounds = new Sounds("wahooo.wav");
		if (map[y][x].equals("3")) {
			sounds.playSound();
			setPoints(getPoints() + 1);
		}
		if (map[y][x].equals("4")) {
			sounds.setSoundPath("cry.wav");
			sounds.playSound();
			maze.findEnemiePosition(new Position(x, y)).setStop(true);
			setLifes(getLifes() - 1);
			maze.removeEnemie(new Position(x, y));
		}

	}

	public void shoot() {
		listShoot.add(new Shoot(maze, move, position));
	}

	public Shoot findShoot(Position position) {
		ComparatorPositions comparatorPositions = new ComparatorPositions();
		for (Shoot shoot : listShoot) {
			if(comparatorPositions.compare(shoot.getPosition(), position) == 0) {
				return shoot;
			}
		}
		return null;
	}

	public boolean isInjure() {
		return injure;
	}

	public void setInjure(boolean injure) {
		this.injure = injure;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
