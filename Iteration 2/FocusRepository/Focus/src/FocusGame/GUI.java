package FocusGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main UI class of the project, intereacts directly with user
 * @author Nathan French
 * @version 2021-10-28
 *
 */

public class GUI extends JFrame{
	
	private JPanel menuPanel;
	private JPanel titlePanel;
	private JPanel startPanel;
	private JPanel loadPanel;
	private JPanel settingsPanel;
	private Board boardPanel;
	public static int fontSize;
	
	CardLayout cl = new CardLayout();
	
	public GUI() {
		setTitle("Focus");
		fontSize = 18;
		
		boardPanel = new Board();
		
		menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(200,700));
		menuPanel.setLayout(cl);
		titlePanel = createTitlePanel();
		startPanel = createStartPanel();
		loadPanel = createLoadPanel();
		settingsPanel = createSettingsPanel();
		menuPanel.add(titlePanel,"1");
		menuPanel.add(startPanel,"2");
		menuPanel.add(loadPanel,"3");
		menuPanel.add(settingsPanel,"4");
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(boardPanel,BorderLayout.CENTER);
		getContentPane().add(menuPanel,BorderLayout.WEST);
		pack();

		setSize(1000,700);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);
	}
	
	private GamePanel createTitlePanel() {
		GamePanel titlePanel = new GamePanel("Focus");
		
		JButton startButton = new JButton("Start Game");
		startButton.setMaximumSize(new Dimension(200,50));
		startButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		startButton.addActionListener(new EventHandler(this,"StartGame"));
		
		JButton loadButton = new JButton("Load Game");
		loadButton.setMaximumSize(new Dimension(200,50));
		loadButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		loadButton.addActionListener(new EventHandler(this,"LoadGame"));
		
		JButton displayButton = new JButton("Display Settings");
		displayButton.setMaximumSize(new Dimension(200,50));
		displayButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		displayButton.addActionListener(new EventHandler(this,"DisplaySettings"));
		
		titlePanel.add(startButton);
		titlePanel.add(loadButton);
		titlePanel.add(displayButton);
		
		return(titlePanel);
	}
	
	private GamePanel createStartPanel() {
		GamePanel startPanel = new GamePanel("Start");
		
		JButton backButton = new JButton("Back to Menu");
		backButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		backButton.setMaximumSize(new Dimension(200,50));
		backButton.addActionListener(new EventHandler(this,"TitleScreen"));

		startPanel.add(backButton);

		return(startPanel);
	}
	
	private GamePanel createLoadPanel() {
		GamePanel loadPanel = new GamePanel("Load");
		
		JButton backButton = new JButton("Back to Menu");
		backButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		backButton.setMaximumSize(new Dimension(200,50));
		backButton.addActionListener(new EventHandler(this,"TitleScreen"));

		loadPanel.add(backButton);

		return(loadPanel);
	}
	
	private GamePanel createSettingsPanel() {
		GamePanel settingsPanel = new GamePanel("Settings");
		
		JLabel boardColorText = new JLabel("Board Colour");
		boardColorText.setFont(new Font("lol",Font.PLAIN,fontSize));
		boardColorText.setMaximumSize(new Dimension(200,50));
		
		String[] colors = { "Gray", "White", "Red", "Green", "Blue", "Yellow", "Orange", "Purple", "Pink" };
		JComboBox boardColorSetting = new JComboBox(colors);
		boardColorSetting.setFont(new Font("lol",Font.PLAIN,fontSize));
		boardColorSetting.setMaximumSize(new Dimension(300,50));
		boardColorSetting.addActionListener(new EventHandler(this,"ChangeBoardColor"));
		
		JLabel fontSizeText = new JLabel("Font Size");
		fontSizeText.setFont(new Font("lol",Font.PLAIN,fontSize));
		fontSizeText.setMaximumSize(new Dimension(200,50));
		
		String[] sizes = { "Small", "Medium", "Large" };
		JComboBox fontSizeSetting = new JComboBox(sizes);
		fontSizeSetting.setFont(new Font("lol",Font.PLAIN,fontSize));
		fontSizeSetting.setMaximumSize(new Dimension(300,50));
		fontSizeSetting.addActionListener(new EventHandler(this,"ChangeFontSize"));
		
		JButton backButton = new JButton("Back to Menu");
		backButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		backButton.setMaximumSize(new Dimension(200,50));
		backButton.addActionListener(new EventHandler(this,"TitleScreen"));

		settingsPanel.add(boardColorText);
		settingsPanel.add(boardColorSetting);
		settingsPanel.add(fontSizeText);
		settingsPanel.add(fontSizeSetting);
		settingsPanel.add(backButton);

		return(settingsPanel);
	} 
	
	public void showTitlePanel() {
		cl.show(menuPanel,"1");
	}
	
	public void showStartPanel() {
		cl.show(menuPanel,"2");
	}
	
	public void showLoadPanel() {
		cl.show(menuPanel,"3");
	}
	
	public void showSettingsPanel() {
		cl.show(menuPanel,"4");
	}
	
	public void changeBoardColor(String color) {
		boardPanel.changeColor(color);
	}

	public void changeFont(String size) {
		if (size.equals("Small")) {
			fontSize = 14;
		} else if (size.equals("Medium")) {
			fontSize = 18;
		} else if (size.equals("Large")) {
			fontSize = 23;
		}
		
		Component[] menuChildren = menuPanel.getComponents();
		for (int i = 0; i < menuChildren.length ; i++) {
			Component[] panelChildren = ((GamePanel) menuChildren[i]).getComponents();
			for (int v = 0; v < panelChildren.length ; v++) {
				if (panelChildren[v] instanceof Component && v != 0) {
					panelChildren[v].setFont(new Font("lol",Font.PLAIN,fontSize));
				}
			}
		}
		
	}
}
