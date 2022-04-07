package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFile {

	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	public TextFile(String pathname) throws FileNotFoundException {
		file = new File(pathname);
		fileReader = new FileReader(file);
	}
	
	public ArrayList<String> readFile() throws IOException{
		ArrayList<String> list = new ArrayList<>();
		bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null) {
			list.add(line);
			line = bufferedReader.readLine();
		}
		close();
		return list;
	}

	public void close() throws IOException {
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		if (fileReader != null) {
			fileReader.close();
		}
		
	}
}
