package FocusGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
	int[][] nonSpaces = {{0,0},{0,1},{1,0},{6,0},{7,0},{7,1},
            {0,6},{0,7},{1,7},{6,7},{7,7},{7,6}};
	private JLabel[][] space;
	
	private Map<String, Color> colorMap = Map.of("Red",new Color(255, 115, 115),"Blue",new Color(115, 148, 255),
			"Yellow",new Color(255, 250, 115),"Green",new Color(115, 255, 115),
			"Purple",new Color(183, 115, 255),"Pink",new Color(255, 115, 218),
			"Orange",new Color(255, 152, 115),"Gray",new Color(69, 69, 69),"White",new Color(235, 235, 235));
	
	private Color boardColor = colorMap.get("Gray");
	private Color spaceColor = boardColor.darker();
	
	public Board() {
		setLayout(new GridLayout(x,y));
		space = new JLabel[x][y];
		
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				space[row][col] = new JLabel();
				space[row][col].setBackground(spaceColor);	
				space[row][col].setBorder(BorderFactory.createLineBorder(boardColor));
				if (!checkIfNonSpace(row,col)) {
					space[row][col].setOpaque(true);
				}
				add(space[row][col]);
			}
		}
		
		setBackground(boardColor);
		
	}
	
	private Boolean checkIfNonSpace(int x,int y) {
		for (int i = 0; i < nonSpaces.length;  i++) {
			if (nonSpaces[i][0] == x && nonSpaces[i][1] == y) return(true);
		}
		return(false);
	}
	
	public void wipeBoard() {
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				space[row][col].setBackground(spaceColor);	
				if (!checkIfNonSpace(row,col)) {
					space[row][col].setOpaque(true);
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
				space[row][col].setBackground(spaceColor);
				space[row][col].setBorder(BorderFactory.createLineBorder(boardColor));	
				if (!checkIfNonSpace(row,col)) {
					space[row][col].setOpaque(true);
				}
			}
		}
		
	}
}
