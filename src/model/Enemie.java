package model;

import SUI.Sounds;

public class Enemie extends Thread {
	private Position position;
	private Move move;
	private Maze maze;
	private String map[][];
	private boolean stop;
	private boolean pause;
	private long time;
	private int live;

	public Enemie(Move move, Position position, long time, Maze maze) {
		this.move = move;
		this.maze = maze;
		this.position = position;
		this.time = time;
		this.map = maze.getMaze();
		live = maze.level;
		this.start();
	}

	public void moveEnemie() {
		switch (move) {
		case DOWN:
			if (position.getY() < map.length - 2) {
				setPositionEnemie(new Position(position.getX(), position.getY() + 1));
			} else {
				setMoveRandom();
			}
			break;
		case UP:
			if (position.getY() > 0) {
				setPositionEnemie(new Position(position.getX(), position.getY() - 1));
			} else {
				setMoveRandom();
			}

			break;
		case RIGHT:
			if (position.getX() < map.length - 2) {
				setPositionEnemie(new Position(position.getX() + 1, position.getY()));
			} else {
				setMoveRandom();
			}
			break;
		case LEFT:
			if (position.getX() > 0) {
				setPositionEnemie(new Position(position.getX() - 1, position.getY()));
			} else {
				setMoveRandom();
			}
			break;
		default:
			break;
		}
	}

	public synchronized void setPositionEnemie(Position newPosition) {
		if (!map[newPosition.getY()][newPosition.getX()].equals("1") && !map[newPosition.getY()][newPosition.getX()].equals("3")) {
			if (map[newPosition.getY()][newPosition.getX()].equals("2")) {
				maze.removeEnemie(new Position(newPosition.getX(), newPosition.getY()));
				maze.getHero().setInjure(true);
				maze.getHero().setLifes(maze.getHero().getLifes() - 1);
				map[position.getY()][position.getX()] = "0";
				Sounds sounds = new Sounds("cry.wav");
				sounds.playSound();
				stop = true;
			} else {
				map[position.getY()][position.getX()] = "0";
				map[newPosition.getY()][newPosition.getX()] = "4";
				setPosition(newPosition);
			}
		} else {
			setMoveRandom();
		}
	}

	public void setMoveRandom() {
		setMove(Move.values()[(int) (Math.random() * 4)]);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public void execute() {
		moveEnemie();
	}

	public synchronized void startEnemie() {
		this.start();
	}

	public synchronized void pauseEnemie() {
		pause = true;
	}

	public synchronized void stopEnemie() {
		pause = false;
		stop = true;
		notify();
	}

	synchronized public void resumeEnemie() {
		pause = false;
		notify();
	}

	@Override
	public void run() {
		while (!stop) {
			execute();
			synchronized (this) {
				while (pause) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (stop) {
					break;
				}
			}
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
}
