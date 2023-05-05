package FocusGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveStorage {
	
	private String gamePath = System.getProperty("user.dir");
	private String path;
	
	public SaveStorage() {
		path = gamePath + "\\Saves";
	}
	
	public void saveGameData() {

	}
	
	public ArrayList<String> loadGameData(String fileName) {
		
		ArrayList<String> gameData = new ArrayList<>();
		try {
			File saveFile = new File(path + "\\" + fileName);
			Scanner reader = new Scanner(saveFile);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				gameData.add(data);
			}	
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return(gameData);
		
	}
	
	public String[] getSaves() {
		File saveFiles = new File(path);
		return(saveFiles.list());	
	}
	
	public Boolean checkData(String fileName) {
		try {
			File saveFile = new File(path + "\\" + fileName);
			Scanner reader = new Scanner(saveFile);
			reader.close();
		} catch (FileNotFoundException e) {
			return(false);
		}
		return(true);
	}
}
