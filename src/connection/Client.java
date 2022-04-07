package connection;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Client extends Thread {
	private Socket socket;
	private ObjectOutputStream objOut;
	private ObjectInputStream objInp;
	private String ip;
	private int port;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void write(Object object) throws IOException {
		objOut.writeObject(object);
	}
	
	public Object read() throws ClassNotFoundException, IOException {
		return objInp.readObject();
	}

	@Override
	public void run() {
		try {
			socket = new Socket(ip, port);
			objOut = new ObjectOutputStream(socket.getOutputStream());
			objInp = new ObjectInputStream(socket.getInputStream());
			while (socket.isConnected()) {
				
			}
			System.out.println("Conectado");
			objInp.close();
			objOut.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
