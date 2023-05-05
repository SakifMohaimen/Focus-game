package FocusUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Custom Made JPanel designed to hold menu information
 * @author Nathan French
 * @version 2021-10-28
 *
 */

public class GamePanel extends JPanel{
	
	public GamePanel(String menuTitle) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.gray);
		
		int titleSize = (85 - 5*(menuTitle.length()));
		JLabel title = new JLabel(menuTitle);
		title.setAlignmentX(LEFT_ALIGNMENT);
		title.setFont(new Font("lol",Font.PLAIN,titleSize));
		title.setPreferredSize(new Dimension(50,50));
		add(title);
		
		setPreferredSize(new Dimension(200,700));
	}
	
	public GamePanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.gray);
		
		setPreferredSize(new Dimension(200,700));
	}
	
}
