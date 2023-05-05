package FocusGame;

import java.awt.Color;
import java.util.Map;

import FocusUI.Board;
import FocusUI.Space;
import java.util.ArrayList;

public class Game {
	public static final Color[] colors = {new Color(0, 145, 255),new Color(251, 255, 0),new Color(0, 255, 68),new Color(255, 0, 0)};
	
	private Player[] players;
	private int turn;
	private Object[][] space;
	private Board board;
	private SaveStorage saveStore;
	
	public Game(Board board) {
		players = new Player[4];
		space = new Object[8][8];
		this.board = board;
		turn = 1;
		saveStore = new SaveStorage();
	}
	
	private void clearData() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if (col < players.length) {
					players[col] = null;
				}
				space[row][col] = null;
			}
		}
	}
	
	public void setupGame(Object[][] playerData) {
		for (int i = 0; i < 4; i ++) {
			String name = (String) playerData[i][0];
			int difficulty = (int) playerData[i][1];
			if (difficulty == 0) {
				players[i] = new Human(name,colors[i],false,0);
			} else {
				players[i] = new CPU(name,colors[i],difficulty);
			}
		}
		int[][] startPattern = {{0,0,0,0,3,1,3,1},{1,1,1,1,3,1,3,1},{0,0,0,0,3,1,3,1},{1,1,1,1,3,1,3,1},
								{2,0,2,0,2,2,2,2},{2,0,2,0,3,3,3,3},{2,0,2,0,2,2,2,2},{2,0,2,0,3,3,3,3}};
		
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if (!board.checkIfNonSpace(row,col)) {
					int pawnColor = startPattern[row][col];
					space[row][col] = new Pawn(colors[pawnColor]);
				}
			}
		}
		
		board.setBoard(space);	
	}
	
	public String[] getSaves() {
		return(saveStore.getSaves());
	}
	
	public Boolean checkFile(String fileName) {
		return(saveStore.checkData(fileName));
	}
	
	public Object[][] sendPlayerData() {
		Object[][] playerData = new Object[4][2];
		for (int i=0; i< players.length; i++) {
			playerData[i][0] = players[i].getPlayerName();
			if (players[i] instanceof CPU) {
				playerData[i][1] = ((CPU) players[i]).getDifficulty();
			} else {
				playerData[i][1] = 0;
			}
		}
		return(playerData);
	}
	
	public Object[][] loadGame(String fileName) {
		ArrayList<String> data = saveStore.loadGameData(fileName);
		int row = 0;
		int col = 0;
		
		clearData();
		
		for (int i = 0; i < data.size(); i++) {
			if (i < 4) {
				int difficulty = Integer.parseInt(data.get(i+4));
				if (difficulty == 0) {
					players[i] = new Human(data.get(i),colors[i],false,0);
				} else {
					players[i] = new CPU(data.get(i),colors[i],difficulty);
				}
			} else if (i == 4) {
				i += 4;
				turn = Integer.parseInt(data.get(i));
			} else {
				if (!board.checkIfNonSpace(row,col) && !data.get(i).equals("")) {
					int pawnColor = Integer.parseInt(data.get(i));
					space[row][col] = new Pawn(colors[pawnColor]);
				}
				col ++;
				if (col > 7) {
					col = 0;
					row ++;
				}
			}
		}
		board.setBoard(space);
		return(sendPlayerData());
	}
	
}
