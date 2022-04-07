package model.socket;

import java.io.IOException;

import connection.Client;
import model.Hero;

public class Player {

	private Hero hero;
	private Client client;
	
	public Player() {
		
	}
	
	/**
	 * Enviar una accion y el tipo de accion que es
	 * Move
	 * @param typeMessage
	 * @param action
	 */
	public void sendAction(TypeMessage typeMessage,String action) {
		try {
			client.write(typeMessage+"/"+action);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
