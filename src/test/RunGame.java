package test;

import controller.Controller;

public class RunGame {	
	public static void main(String[] args) {
			Controller controller = Controller.getController();
			controller.initComponents();
	}
}