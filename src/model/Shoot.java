package model;


public class Shoot extends Thread {
	private Maze maze;
	private boolean pause;
	private boolean stop;
	private Move move;
	private String map[][];
	private Position position;

	public Shoot(Maze maze, Move move, Position positionHero) {
		this.maze = maze;
		getNewMove(move);
		this.map = maze.getMaze();
		this.position = nextPosition(positionHero);
		putInMapRun();
	}

	private void getNewMove(Move move) {
		if (move == Move.DOWN) {
			this.move = Move.DOWN;
		} else if (move == Move.RIGHT) {
			this.move = Move.RIGHT;
		} else if (move == Move.LEFT) {
			this.move = Move.LEFT;
		} else if (move == Move.UP) {
			this.move = Move.UP;
		}
	}

	public void putInMapRun() {
		if (position.getX() >= 0 && position.getY() < maze.getCols() && position.getY() >= 0
				&& position.getY() < maze.getRows()) {
			if (map[position.getY()][position.getX()].equals("0")) {
				this.map[position.getY()][position.getX()] = "5";
				start();
			}
		}
	}

	public Position nextPosition(Position position) {
		switch (move) {
		case DOWN:
			return new Position(position.getX(), position.getY() + 1);
		case UP:
			return new Position(position.getX(), position.getY() - 1);
		case LEFT:
			return new Position(position.getX() - 1, position.getY());
		case RIGHT:
			return new Position(position.getX() + 1, position.getY());
		default:
			return null;
		}
	}

	public void moveToPosition(Position newPosition) {
		if (map[newPosition.getY()][newPosition.getX()].equals("0")) {
			map[position.getY()][position.getX()] = "0";
			map[newPosition.getY()][newPosition.getX()] = "5";
			position = newPosition;
		}
		if (map[newPosition.getY()][newPosition.getX()].equals("4")) {
			removeShoot();
			maze.takeAwayLifes(newPosition);
		} else if (map[newPosition.getY()][newPosition.getX()].equals("1")
				|| map[newPosition.getY()][newPosition.getX()].equals("3")) {
			removeShoot();
		}
	}

	synchronized public void moveShoot() {
		switch (move) {
		case DOWN:
			if (position.getY() < maze.getRows() - 2) {
				moveToPosition(new Position(position.getX(), position.getY() + 1));
			} else {
				removeShoot();
			}
			break;
		case UP:
			if (position.getY() > 0) {
				moveToPosition(new Position(position.getX(), position.getY() - 1));
			} else {
				removeShoot();
			}
			break;

		case LEFT:
			if (position.getX() > 0) {
				moveToPosition(new Position(position.getX() - 1, position.getY()));
			} else {
				removeShoot();
			}
			break;

		case RIGHT:
			if (position.getX() < maze.getCols() - 2) {
				moveToPosition(new Position(position.getX() + 1, position.getY()));
			} else {
				removeShoot();
			}
			break;
		default:
			break;
		}

	}

	public void removeShoot() {
		stop = true;
		map[position.getY()][position.getX()] = "0";
	}

	synchronized public void stopShoot() {
		pause = false;
		stop = true;
		notify();
	}

	synchronized public void pauseShoot() {
		stop = false;
		pause = true;
		notify();
	}

	@Override
	public void run() {
		while (!stop) {
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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			execute();
		}
	}

	public void execute() {
		moveShoot();
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
}
