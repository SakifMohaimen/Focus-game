package FocusUI;

import javax.swing.*;

import FocusGame.Pawn;

import java.awt.*;
import java.util.Map;

/**
 * Custom Made JPanel designed to represent the game board
 * @author Nathan French
 * @version 2021-10-28
 *
 */

public class Board extends JPanel{
	
	private int x = 8;
	private int y = 8;
	private int[][] nonSpaces = {{0,0},{0,1},{1,0},{6,0},{7,0},{7,1},
            {0,6},{0,7},{1,7},{6,7},{7,7},{7,6}};
	private Space[][] space;
	
	private Map<String, Color> colorMap = Map.of("Red",new Color(255, 115, 115),"Blue",new Color(115, 148, 255),
			"Yellow",new Color(255, 250, 115),"Green",new Color(115, 255, 115),
			"Purple",new Color(183, 115, 255),"Pink",new Color(255, 115, 218),
			"Orange",new Color(255, 152, 115),"Gray",new Color(69, 69, 69),"White",new Color(235, 235, 235));
	
	private Color boardColor = colorMap.get("Gray");
	private Color spaceColor = boardColor.darker();
	
	public Board() {
		setLayout(new GridLayout(x,y));
		space = new Space[x][y];
		
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				space[row][col] = new Space();
				if (!checkIfNonSpace(row,col)) {
					space[row][col].setSpaceColor(spaceColor);
					space[row][col].setBorderColor(boardColor);
					space[row][col].setOpaque(true);
				}
				add(space[row][col]);
			}
		}
		
		setBackground(boardColor);
		
	}
	
	public Boolean checkIfNonSpace(int x,int y) {
		for (int i = 0; i < nonSpaces.length;  i++) {
			if (nonSpaces[i][0] == x && nonSpaces[i][1] == y) return(true);
		}
		return(false);
	}
	
	public void setBoard(Object[][] piece) {
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				if (!checkIfNonSpace(row,col)) {
					if (piece[row][col] instanceof Pawn) {
						space[row][col].setSpaceColor(((Pawn) piece[row][col]).getColor());
					}
				}
			}
		}
	}
	
	public void wipeBoard() {
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				if (!checkIfNonSpace(row,col)) {
					space[row][col].setSpaceColor(spaceColor);
				}
			}
		}	
	}

	public void changeColor(String color) {
		
		boardColor = colorMap.get(color);
		setBackground(boardColor);
		
		spaceColor = boardColor.darker();
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				if (!checkIfNonSpace(row,col)) {
					space[row][col].setSpaceColor(spaceColor);
					space[row][col].setBorderColor(boardColor);
				}
			}
		}
		
	}
}
