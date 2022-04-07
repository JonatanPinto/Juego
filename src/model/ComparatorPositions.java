package model;

import java.util.Comparator;

public class ComparatorPositions implements Comparator<Position>{
	
	public ComparatorPositions() {
	}

	public int compare(Position position, Position position2) {
		if(position.getX() == position2.getX() && position.getY() == position2.getY()) {
			return 0;
		}
		if(position.getX() < position2.getX() && position.getY() < position2.getY()) {
			return -1;
		}
		if(position.getX() > position2.getX() && position.getY() > position2.getY()) {
			return 1;
		}
		return 1;
	};
}
