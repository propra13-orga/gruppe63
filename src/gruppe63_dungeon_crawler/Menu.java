package gruppe63_dungeon_crawler;


import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Thread thread;
	
	// Game klasse?
	private Game game;
	
	private JPanel GameoverPanel;
	private Container cp;
	int difficulty;
	public Controller controller;
	
	private int xFrame=600;
	private int yFrame=700;
	

	public Menu() {

		super("Menu");
		

	}
	
	JLabel lTitle;
	JLabel lDifficulty;
	JRadioButton rbEasy;
	JRadioButton rbHard;
	ButtonGroup diffGroup;
	JButton bStart;
	JButton bSettings;
	JButton bExit;
	JButton bSave;
	JButton bClose;
	JButton bRestart;
	GridBagConstraints lTitlec = new GridBagConstraints();
	GridBagConstraints lDifficultyc = new GridBagConstraints();
	GridBagConstraints bStartc = new GridBagConstraints();
	GridBagConstraints bSettingsc = new GridBagConstraints();
	GridBagConstraints rbEasyc = new GridBagConstraints();
	GridBagConstraints rbHardc = new GridBagConstraints();
	GridBagConstraints bSavec = new GridBagConstraints();
	GridBagConstraints bClosec = new GridBagConstraints();
	GridBagConstraints bExitc = new GridBagConstraints();
	GridBagConstraints bExitc2 = new GridBagConstraints();
	GridBagConstraints bRestartc = new GridBagConstraints();
	GridBagConstraints lEndec = new GridBagConstraints();

	public void initialize() {
		
		cp = this.getContentPane();
		
		/* Prefixes:
		 * Declarate Elements
		 * 
		 * b = Button
		 * rb = RadioButton
		 * l = Label
		 * 
		 * Initialize Elements, structure with Gridbagdesign
		 * 
		 */
		
		

		bSettings = new JButton("Settings");
		bSettings.addActionListener(this);
		lTitle = new JLabel("Dungeon Crawlers", JLabel.CENTER);
		lDifficulty = new JLabel("Difficulty", JLabel.CENTER);
		bStart = new JButton("Start game");
		bStart.addActionListener(this);
		bExit = new JButton("Exit");
		bExit.addActionListener(this);
		bClose = new JButton("Exit without saving");
		bClose.addActionListener(this);
		bSave = new JButton("Save and exit");
		bSave.addActionListener(this);
		
		
		
		// Initialize Titlelabel
		lTitle.setFont(new Font("Serif", Font.PLAIN, 25));
		lTitlec.gridx = 0;
		lTitlec.gridy = 1;
		lTitlec.fill = GridBagConstraints.BOTH;

		// Initialize Difficultylabel
		lDifficultyc.gridx = 0;
		lDifficultyc.gridy = 0;
		lDifficultyc.gridwidth = 2;
		lDifficultyc.fill = GridBagConstraints.BOTH;
		
		// Initialize Startbutton
		// Shortcut: Alt+S
		bStart.setMnemonic(KeyEvent.VK_S);

		bStartc.gridx = 0;
		bStartc.gridy = 2;
		bStartc.gridwidth = 2;
		bStartc.fill = GridBagConstraints.HORIZONTAL;
		bStartc.weightx = 1.0;
		
		// Initialize Settingsbutton
		bSettingsc.gridx = 0;
		bSettingsc.gridy = 3;
		bSettingsc.fill = GridBagConstraints.HORIZONTAL;
		bSettingsc.weightx = 2.0;
		
		// Initialize Radiobuttons
		rbEasy = new JRadioButton("easy");
		rbEasyc.gridx = 0;
		rbEasyc.gridy = 1;
		
		rbHard = new JRadioButton("hard");
		rbHardc.gridx = 1;
		rbHardc.gridy = 1;
		
		// Buttongroup
		diffGroup = new ButtonGroup();
		diffGroup.add(rbEasy);
		diffGroup.add(rbHard);
		
		// Initialize Savebutton
		bSavec.gridx = 0;
		bSavec.gridy = 2;
		bSavec.fill = GridBagConstraints.HORIZONTAL;
		bSavec.weightx = 1.0;
		
		// Initialize Closebutton
		bClosec.gridx = 1;
		bClosec.gridy = 2;
		bClosec.fill = GridBagConstraints.HORIZONTAL;
		bClosec.weightx = 1.0;
		
		// Initialize Exitbutton
		bExitc.gridx = 0;
		bExitc.gridy = 4;
		bExitc.fill = GridBagConstraints.HORIZONTAL;
		bExitc.weightx = 1.0;

		cp.setLayout(new GridBagLayout());

		// Add Elements to ContentPane
		cp.add(lTitle, lTitlec);
		cp.add(bStart, bStartc);
		cp.add(bSettings, bSettingsc);
		cp.add(bExit, bExitc);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setSize(xFrame, yFrame);
//		this.setResizable(false);
		
		this.setVisible(true);

	}

	public static void main(String[] args) {

		final Menu f = new Menu();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				f.initialize();
			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==this.bStart)
		{
			this.startGame();
			
		}
		if(e.getSource()==this.bExit)
		{
			System.exit(0);
		}
		if(e.getSource()==this.bSettings)
		{
			// open Settings
			setVisible(false);
			cp.removeAll();
			cp.add(lDifficulty, lDifficultyc);
			cp.add(rbEasy, rbEasyc);
			cp.add(rbHard, rbHardc);
			cp.add(bSave, bSavec);
			cp.add(bClose, bClosec);
			setVisible(true);
		}
		if(e.getSource()==this.bSave)
		{
			if(rbEasy.isSelected())
				difficulty = 0;
			else if(rbHard.isSelected())
				difficulty = 1;
			setVisible(false);
			cp.removeAll();
			cp.add(lTitle, lTitlec);
			cp.add(bStart, bStartc);
			cp.add(bSettings, bSettingsc);
			cp.add(bExit, bExitc);
			setVisible(true);
		}
		if(e.getSource()==this.bClose)
		{
			setVisible(false);
			cp.removeAll();
			cp.add(lTitle, lTitlec);
			cp.add(bStart, bStartc);
			cp.add(bSettings, bSettingsc);
			cp.add(bExit, bExitc);
			setVisible(true);
		}
		if(e.getSource()==this.bRestart)
		{
			this.remove(GameoverPanel);
			this.initialize();
		}
		
	}
	
	public void startGame()
	{
		// Start game, load dungeon
		String diffString = null;
		if (difficulty == 0)
				diffString = "Easy";
		else if (difficulty == 1)
				diffString = "Hard";
		System.out.println("Loading game...");
		System.out.println("Difficulty: " + diffString);
		
		//Controller
		controller = new Controller();
		this.setFocusable(true);
		this.addKeyListener(controller);

		//Neues Layout, Map (ueber Game-stat)
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

		//Erzeugt neues Spiel und startet es
		game = new Game(this.cp, this);
		thread = new Thread(game);
		thread.start();
	}
	
	public void win(boolean win) {
		// Destroy myGame
		game = null;

		// Weiter-Menu
		GameoverPanel = new JPanel();
		GameoverPanel.setBounds(0, 0, 600, 600);
		GameoverPanel.setLayout(new GridBagLayout());
		JLabel lEnde = new JLabel("", JLabel.CENTER);
		lEnde.setFont(new Font("Serif", Font.PLAIN, 25));
		if(win){
		lEnde.setText("You win!");
		} else{
		lEnde.setText("You lose!");
		}
		bRestart = new JButton("Hauptmenu");
		bRestart.addActionListener(this);
		bRestart.setDefaultCapable(true);
		bRestartc.gridx = 0;
		bRestartc.gridy = 1;
		bRestartc.fill = GridBagConstraints.HORIZONTAL;
		
		lEndec.gridx = 0;
		lEndec.gridy = 0;
		lEndec.fill = GridBagConstraints.HORIZONTAL;
		
		bExitc2.gridx = 0;
		bExitc2.gridy = 2;
		bExitc2.fill = GridBagConstraints.HORIZONTAL;
		bExitc2.weightx = 1.0;
		
		GameoverPanel.add(lEnde, lEndec);
		GameoverPanel.add(bRestart, bRestartc);
		GameoverPanel.add(bExit, bExitc2);

		GameoverPanel.setVisible(true);


		cp.add(GameoverPanel);


		this.pack();
		this.setSize(xFrame, yFrame);



		}

}
