package FocusGame;

import java.awt.Color;

public class Human extends Player{
	
	private Boolean colorBlind;
	private int textSize;
	
	public Human(String name, Color color, Boolean colorblind, int textsize) {
		super(name, color);
		colorBlind = colorblind;
		textSize = textsize;
	}
	
	// Accessor for colorBlind
	public Boolean isColorBlind() {
		return(colorBlind);
	}
	
	// Accessor for textSize
	public int getTextSize() {
		return(textSize);
	}

}
