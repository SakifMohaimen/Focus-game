package FocusGame;

import java.awt.event.*;
import javax.swing.*;

/**
 * Any events fired creates a new EventHandler object, which then performs the requested event
 * @author Nathan French
 * @version 2021-10-28
 *
 */

public class EventHandler implements ActionListener{

	private GUI userInterface;
	private String command;
	
	public EventHandler(GUI UI,String command) {
		userInterface = UI;
		this.command = command;
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object target = evt.getSource();
		
		if (target instanceof JButton) {

			if (command.equals("StartGame")) {
				userInterface.showStartPanel();
			} else if (command.equals("TitleScreen")) {
				userInterface.showTitlePanel();
			} else if (command.equals("LoadGame")) {
				userInterface.showLoadPanel();
			} else if (command.equals("DisplaySettings")) {
				userInterface.showSettingsPanel();
			}
			
		} else if (target instanceof JComboBox) {
			
			if (command.equals("ChangeBoardColor")) {
				userInterface.changeBoardColor((String) ((JComboBox) target).getSelectedItem());
			} else if (command.equals("ChangeFontSize")) {
				userInterface.changeFont((String) ((JComboBox) target).getSelectedItem());
			}
		}
		
	}
}
