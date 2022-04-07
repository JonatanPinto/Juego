package model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Random;

import SUI.Sounds;

public class Maze {
	private String[][] maze;
	private Hero hero;
	private ArrayList<Enemie> enemies;
	private int rows;
	private int cols;
	public int numberEnemies;
	public int numberMeals;
	public long difficult;
	public int level;
	private String ip;

	public Maze(ArrayList<String> arrayList, int numberEnemies, int numberMeals, long difficult, int level, String ip) {
		initData(arrayList);
		enemies = new ArrayList<Enemie>();
		this.numberEnemies = numberEnemies;
		this.numberMeals = numberMeals;
		this.difficult = difficult;
		this.level = level;
		this.ip = ip;
		hero = new Hero(this, 4, 0, Move.RIGHT,ip);
		putFood(0);
		putEnemies(0);
		verify();
	}

	private void initData(ArrayList<String> list) {
		ArrayList<String> temp = list;
		maze = new String[temp.size()][temp.get(0).split(";").length];
		rows = temp.size();
		cols = temp.get(0).split(";").length;
		for (int i = 0; i < rows; i++) {
			String[] vector = temp.get(i).split(";");
			for (int j = 0; j < vector.length; j++) {
				if (vector[j].equals("0")) {
					maze[i][j] = vector[j];
				} else if (vector[j].equals("2")) {
					maze[i][j] = vector[j];
				} else {
					maze[i][j] = "1";
				}
			}
		}
		verify();
	}

	public void reinitLevel(ArrayList<String> arrayList) {
		initData(arrayList);
		putEnemies(0);
		putFood(0);
	}

	public void upLevel(ArrayList<String> arrayList) {
		level++;
		initData(arrayList);
		numberMeals = (int) (numberMeals * 1.5);
		numberEnemies = (int) (numberEnemies + 2);
		difficult = difficult - 50;
		hero = new Hero(this, 4, 0, Move.RIGHT,ip);
		putFood(0);
		putEnemies(0);
	}

	private void verify() {
		for (int i = 0; i < rows - 1; i++) {
			for (int j = 0; j < cols - 1; j++) {
				if (maze[i][j] == null)
					maze[i][j] = "1";
			}
		}
	}

	public Position findHero() {
		for (int i = 0; i < rows - 1; i++) {
			for (int j = 0; j < cols - 1; j++) {
				if (maze[i][j].equals("2"))
					return new Position(j, i);
			}
		}
		return null;
	}

	public void moveHeroRIGHT() {
		hero.moveRIGHT();
	}

	public void moveHeroLEFT() {
		hero.moveLEFT();
	}

	public void moveHeroDOWN() {
		hero.moveDOWN();
	}

	public void moveHeroUP() {
		hero.moveUP();
	}

	public void putFood(int count) {
		int countPrizes = count;
		for (int i = 0; i < cols - 1; i++) {
			for (int j = 0; j < rows - 1; j++) {
				int random = (int) (Math.random() * 100);
				if (maze[i][j].equals("0") && random < 2 && countPrizes <= numberMeals) {
					maze[i][j] = "3";
					countPrizes++;
				}
			}
		}
		if (countPrizes <= numberMeals) 
			putFood(countPrizes);
	}

	public void putEnemies(int count) {
		int countEnemies = count;
		for (int i = 0; i < cols - 1; i++) {
			for (int j = 0; j < rows - 1; j++) {
				int random = (int) (Math.random() * 100);
				if (maze[i][j].equals("0") && random == 1 && countEnemies <= numberEnemies) {
					maze[i][j] = "4";
					countEnemies++;
					int aleatorio = (int) (Math.random() * 4);
					enemies.add(new Enemie(Move.values()[aleatorio], new Position(j, i), difficult, this));
				}
			}
		}
		if (countEnemies < numberEnemies) {
			putEnemies(countEnemies);
		}
	}

	public void putEnemies2() {
		for (int i = 0; i < rows - 1; i++) {
			for (int j = 0; j < cols - 1; j++) {
				if (maze[i][j].equals("4")) {
					System.out.println("Agrego enemigo");
					int aleatorio = (int) (Math.random() * 4);
					enemies.add(new Enemie(Move.values()[aleatorio], new Position(j, i), difficult, this));
				}
			}
		}
	}

	public void pauseEnemies() {
		for (Enemie enemie : enemies) {
			enemie.setPause(true);
		}
	}

	public void resumeEnemies() {
		for (Enemie enemie : enemies) {
			enemie.resumeEnemie();
		}
	}

	public void stopEnemies() {
		for (Enemie enemie : enemies) {
			enemie.setStop(true);
		}
	}

	public Enemie findEnemiePosition(Position position) {
		ComparatorPositions comparatorPositions = new ComparatorPositions();
		for (Enemie enemie : enemies) {
			if (comparatorPositions.compare(enemie.getPosition(), position) == 0) {
				return enemie;
			}
		}
		return null;
	}

	public void takeAwayLifes(Position position) {
		Enemie enemieToLow = findEnemiePosition(position);
		if (enemieToLow.getLive() == 1) {
			enemieToLow.stopEnemie();
			maze[enemieToLow.getPosition().getY()][enemieToLow.getPosition().getX()] = "0";
			removeEnemie(position);
		} else {
			enemieToLow.setLive(enemieToLow.getLive() - 1);
		}
		Sounds sounds = new Sounds("injured.wav");
		sounds.playSound();
	}

	public void removeEnemie(Position position) {
		maze[position.getY()][position.getX()] = "0";
		enemies.remove(findEnemiePosition(position));
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public String[][] getMaze() {
		return maze;
	}

	public void setMaze(String[][] maze) {
		this.maze = maze;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
}
