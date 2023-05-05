package FocusUI;

import javax.swing.*;

import FocusGame.Game;

import java.awt.*;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Main UI class of the project, intereacts directly with user
 * @author Nathan French
 * @version 2021-10-28
 *
 */

public class GUI extends JFrame{
	
	
	// Variables
	private JPanel menuPanel;
	private JPanel titlePanel;
	private JPanel startPanel;
	private JPanel loadPanel;
	private JPanel settingsPanel;
	private JPanel sessionPanel;
	private Board boardPanel;
	public static int fontSize;
	
	CardLayout cl = new CardLayout();
	
	private Game game;
	
	/**
	 * Constructor for main user interface, all ui is constructed here
	 */
	public GUI() {
		setTitle("Focus");
		fontSize = 18;
		
		boardPanel = new Board();
		
		menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(300,700));
		menuPanel.setLayout(cl);
		titlePanel = createTitlePanel();
		startPanel = createStartPanel();
		loadPanel = createLoadPanel();
		settingsPanel = createSettingsPanel();
		sessionPanel = createSessionPanel();
		menuPanel.add(titlePanel,"1");
		menuPanel.add(startPanel,"2");
		menuPanel.add(loadPanel,"3");
		menuPanel.add(settingsPanel,"4");
		menuPanel.add(sessionPanel,"5");
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(boardPanel,BorderLayout.CENTER);
		getContentPane().add(menuPanel,BorderLayout.WEST);
		pack();

		setSize(1100,800);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);
		
		game = new Game(boardPanel);
		updateSaveList();
	}
	
	/**
	 * Creates the title panel for the game
	 * @return GamePanel TitlePanel
	 */
	private GamePanel createTitlePanel() {
		GamePanel titlePanel = new GamePanel("Focus");
		
		JButton startButton = new JButton("Start Game");
		startButton.setMaximumSize(new Dimension(200,50));
		startButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		startButton.addActionListener(new EventHandler(this,"ShowStartPanel"));
		
		JButton loadButton = new JButton("Load Game");
		loadButton.setMaximumSize(new Dimension(200,50));
		loadButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		loadButton.addActionListener(new EventHandler(this,"ShowLoadPanel"));
		
		JButton displayButton = new JButton("Display Settings");
		displayButton.setMaximumSize(new Dimension(200,50));
		displayButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		displayButton.addActionListener(new EventHandler(this,"ShowSettingsPanel"));
		
		JButton quitButton  = new JButton("Quit");
		quitButton.setMaximumSize(new Dimension(200,50));
		quitButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		quitButton.addActionListener(new EventHandler(this,"Quit"));
		
		titlePanel.add(startButton);
		titlePanel.add(loadButton);
		titlePanel.add(displayButton);
		titlePanel.add(quitButton);
		
		return(titlePanel);
	}
	
	/**
	 * Creates the start game panel for the game
	 * @return GamePanel StartPanel
	 */
	private GamePanel createStartPanel() {
		GamePanel startPanel = new GamePanel("Start");
		
		// Repeats 4 times to create each player panel
		for (int i = 1; i < 5; i ++) {
			JPanel playerPanel = new JPanel();
			playerPanel.setLayout(new FlowLayout());
			playerPanel.setMaximumSize(new Dimension(250,80));
			playerPanel.setOpaque(false);
			
			JLabel playerNumber = new JLabel("Player " + i);
			playerNumber.setFont(new Font("lol",Font.PLAIN,fontSize));
			
			JTextField playerName = new JTextField();
			playerName.setPreferredSize(new Dimension(70,30));
			
			JCheckBox cpuCheck = new JCheckBox("CPU");
			cpuCheck.setFont(new Font("lol",Font.PLAIN,fontSize));
			cpuCheck.addActionListener(new EventHandler(this,"SetDV:" + i));
			
			String[] difficulties = { "Easy", "Hard" };
			JComboBox<String> cpuDifficulty = new JComboBox<String>(difficulties);
			cpuDifficulty.setFont(new Font("lol",Font.PLAIN,fontSize));
			cpuDifficulty.setPreferredSize(new Dimension(100,30));
			cpuDifficulty.setVisible(false);
			
			playerPanel.add(playerNumber);
			playerPanel.add(playerName);
			playerPanel.add(cpuCheck);
			playerPanel.add(cpuDifficulty);
			
			startPanel.add(playerPanel);
		}
		
		JButton startButton = new JButton("Start Game");
		startButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		startButton.setMaximumSize(new Dimension(200,50));
		startButton.addActionListener(new EventHandler(this,"StartGame"));
		
		JButton backButton = new JButton("Back to Menu");
		backButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		backButton.setMaximumSize(new Dimension(200,50));
		backButton.addActionListener(new EventHandler(this,"TitleScreen"));

		startPanel.add(startButton);
		startPanel.add(backButton);

		return(startPanel);
	}
	
	/**
	 * Creates the load game panel for the game
	 * @return GamePanel LoadPanel
	 */
	private GamePanel createLoadPanel() {
		GamePanel loadPanel = new GamePanel("Load");
		
		JLabel saveLabel = new JLabel("Save List");
		saveLabel.setFont(new Font("lol",Font.PLAIN,fontSize));
		saveLabel.setMaximumSize(new Dimension(200,50));
		
		JComboBox<String> gameSaves = new JComboBox<String>();
		gameSaves.setFont(new Font("lol",Font.PLAIN,fontSize));
		gameSaves.setMaximumSize(new Dimension(300,50));
		
		JButton loadButton = new JButton("Load Game");
		loadButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		loadButton.setMaximumSize(new Dimension(200,50));
		loadButton.addActionListener(new EventHandler(this,"LoadGame"));
		
		JButton backButton = new JButton("Back to Menu");
		backButton.setFont(new Font("lol",Font.PLAIN,fontSize));
		backButton.setMaximumSize(new Dimension(200,50));
		backButton.addActionListener(new EventHandler(this,"TitleScreen"));
		
		JLabel saveInfoLabel = new JLabel("<html>" + "Remember to have 'Saves' folder in the same dictionary as the application!" + "</html>");
		saveInfoLabel.setFont(new Font("lol",Font.PLAIN,fontSize));
		saveInfoLabel.setMaximumSize(new Dimension(200,100));

		loadPanel.add(saveLabel);
		loadPanel.add(gameSaves);
		loadPanel.add(loadButton);
		loadPanel.add(backButton);
		loadPanel.add(saveInfoLabel);

		return(loadPanel);
	}
	
	/**
	 * Creates the display settings panel for the game
	 * @return GamePanel SettingsPanel
	 */
	private GamePanel createSettingsPanel() {
		GamePanel settingsPanel = new GamePanel("Settings");
		
		JLabel boardColorText = new JLabel("Board Colour");
		boardColorText.setFont(new Font("lol",Font.PLAIN,fontSize));
		boardColorText.setMaximumSize(new Dimension(200,50));
		
		String[] colors = { "Gray", "White", "Red", "Green", "Blue", "Yellow", "Orange", "Purple", "Pink" };
		JComboBox<String> boardColorSetting = new JComboBox<String>(colors);
		boardColorSetting.setFont(new Font("lol",Font.PLAIN,fontSize));
		boardColorSetting.setMaximumSize(new Dimension(300,50));
		boardColorSetting.addActionListener(new EventHandler(this,"ChangeBoardColor"));
		
		JLabel fontSizeText = new JLabel("Font Size");
		fontSizeText.setFont(new Font("lol",Font.PLAIN,fontSize));
		fontSizeText.setMaximumSize(new Dimension(200,50));
		
		String[] sizes = { "Small", "Medium", "Large" };
		JComboBox<String> fontSizeSetting = new JComboBox<String>(sizes);
		fontSizeSetting.setSelectedIndex(1);
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
	
	private GamePanel createSessionPanel() {
		GamePanel sessionPanel = new GamePanel();
		for (int i = 1; i < 5; i ++) {
			JPanel playerPanel = new JPanel();
			playerPanel.setLayout(new FlowLayout());
			playerPanel.setMaximumSize(new Dimension(250,40));
			playerPanel.setBackground(new Color(214,214,214));
			
			JLabel playerNumberAndName = new JLabel();
			playerNumberAndName.setFont(new Font("lol",Font.PLAIN,fontSize));
			
			JLabel cpuDifficulty = new JLabel();
			cpuDifficulty.setFont(new Font("lol",Font.PLAIN,fontSize));
			cpuDifficulty.setPreferredSize(new Dimension(100,30));
			
			playerPanel.add(playerNumberAndName);
			playerPanel.add(cpuDifficulty);
			
			sessionPanel.add(playerPanel);
		}
		return(sessionPanel);
	}
	
	/**
	 * Switchs to a different game panel depending on param
	 * @param panel
	 */
	public void showGamePanel(String panel) {
		if (panel == "3") updateSaveList();
		cl.show(menuPanel,panel);
	}
	
	/**
	 * Calls the board to change its color
	 * @param color
	 */
	public void changeBoardColor(String color) {
		boardPanel.changeColor(color);
	}

	/**
	 * Changes the global font variables, followed by changing the font size of all elements
	 * @param size
	 */
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
	
	/**
	 * Shows and hides difficulty select option depending if player is cpu or not
	 * @param player
	 * @param visible
	 */
	public void setDifficultyVisual(String player, Boolean visible) {
		JPanel playerPanel = (JPanel) startPanel.getComponent(Integer.parseInt(player));
		((JComponent) playerPanel.getComponent(3)).setVisible(visible);
	}
	
	@SuppressWarnings("unchecked")
	public void updateSaveList() {
		if (game != null) {
			String[] saves = game.getSaves();
			JComboBox<String> saveBox = ((JComboBox<String>) loadPanel.getComponent(2));
			saveBox.removeAllItems();
			if (saves != null) {
				for (String save: saves) {
					saveBox.addItem(save);	
				}
			}
		}
	}
	
	public void setPlayerInfo(Object[][] playerData) {
		String[] difficulties = {"","[Easy]","[Hard]"};
		for (int i = 0; i < 4; i++) {
			String name = (String) playerData[i][0];
			int difficulty = (int) playerData[i][1];
			JPanel playerPanel = (JPanel) sessionPanel.getComponent(i);
			((JLabel) playerPanel.getComponent(0)).setText((i+1) + ": " + name);
			((JLabel) playerPanel.getComponent(0)).setForeground(Game.colors[i].darker());
			((JLabel) playerPanel.getComponent(1)).setText(difficulties[difficulty]);
		}
	}
	
	/**
	 * Checks if players are valid before sending player data to the game class to start a new game
	 */
	public void setupGame() {
		Object[][] playerData = new Object[4][2];
		int incompleteData = 0;
		int botCount = 0;
		for (int i = 0; i < 4; i++) {
			
			JPanel playerPanel = (JPanel) startPanel.getComponent(i+1);
			String playerName = ((JTextField) playerPanel.getComponent(1)).getText();
			Boolean cpuCheck = ((JCheckBox) playerPanel.getComponent(2)).isSelected();
			String difficulty = (String) ((JComboBox<?>) playerPanel.getComponent(3)).getSelectedItem();
			
			if (playerName.length() < 19 && (playerName.isEmpty() != true || cpuCheck == true)) {
				
				if (cpuCheck) {
					botCount ++;
					if (difficulty == "Easy") {
						playerData[i][1] = 1;
					} else {
						playerData[i][1] = 2;
					}
					if (playerName.isEmpty()) {
						playerData[i][0] = "bot";
					} else {
						playerData[i][0] = playerName;	
					}
						
				} else {
					playerData[i][0] = playerName;
					playerData[i][1] = 0;	
				}
				
			} else {
				incompleteData = i+1;
				break;
			}
			
		}
		if (incompleteData == 0 && botCount < 4) {
			setPlayerInfo(playerData);
			game.setupGame(playerData);
			cl.show(menuPanel,"5");
		} else if (botCount < 4){
			showMessageDialog(null, "Invalid name for player " + incompleteData);
		}else {
			showMessageDialog(null, "No human players");	
		}
		
	}
	
	public void loadGame() {
		String fileName = (String) ((JComboBox<?>) loadPanel.getComponent(2)).getSelectedItem();
		setPlayerInfo(game.loadGame(fileName));
		cl.show(menuPanel,"5");
	}
	
}
