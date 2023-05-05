package FocusGame;

import java.awt.Color;

public class CPU extends Player{
	
	private int difficulty;
	
	public CPU(String name, Color color, int difficulty) {
		super(name,color);
		this.difficulty = difficulty;
	}
	
	// Accessor for difficulty
	public int getDifficulty() {
		return(difficulty);
	}

}
