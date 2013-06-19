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
	private JPanel shopPanel;
	private JPanel storyPanel;
	private Container cp;
	int difficulty;
	public Controller controller;
	private Player player = null;
	private int storysite;
	
	private int xFrame=600;
	private int yFrame=700;
	

	public Menu() {

		super("Dungeon Crawler 63");
		

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
	
	// Shop-elements
	JLabel lShop;
	JLabel lMoney;
	JButton bBuyHealthpotion;
	JButton bBuyManapotion;
	JButton bBackToGame;
	JButton bBuyArmor;
	GridBagConstraints lShopc = new GridBagConstraints();
	GridBagConstraints lMoneyc = new GridBagConstraints();
	GridBagConstraints bBuyHealthpotionc = new GridBagConstraints();
	GridBagConstraints bBuyManapotionc = new GridBagConstraints();
	GridBagConstraints bBackToGamec = new GridBagConstraints();
	GridBagConstraints bBuyArmorc = new GridBagConstraints();
	
	// NPCstory-elements
	JLabel lStory;
	JButton bNext;
	GridBagConstraints lStoryc = new GridBagConstraints();
	GridBagConstraints bNextc = new GridBagConstraints();

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
		if(e.getSource()==this.bBackToGame)
		{
			game.continueGame();
		}
		if(e.getSource()==this.bBuyHealthpotion)
		{
			if (player.getMoney()>=10)
			{
				player.setMoney(player.getMoney()-10);
				player.setHealthpotions(player.getHealthpotions()+1);
				lMoney.setText("Money: " + player.getMoney());
				lShop.setText("Du hast einen Heiltrank gekauft.");
			}
			else
			{
				lShop.setText("Du hast nicht genug Geld!");
			}
		}
		if(e.getSource()==this.bBuyManapotion)
		{
			if (player.getMoney()>=10)
			{
				player.setMoney(player.getMoney()-10);
				player.setManapotions(player.getManapotions()+1);
				lMoney.setText("Money: " + player.getMoney());
				lShop.setText("Du hast einen Manatrank gekauft.");
			}
			else
			{
				lShop.setText("Du hast nicht genug Geld!");
			}
		}
		if(e.getSource()==this.bBuyArmor)
		{
			if (player.getMoney()>=50)
			{
				
				if (player.getHatRuestung())
				{
					lShop.setText("Du hast bereits eine Ruestung!");
				}
				else
				{
					player.setMoney(player.getMoney()-50);
					player.setHatRuestung(true);
					lMoney.setText("Money: " + player.getMoney());
					lShop.setText("Du hast eine Ruestung gekauft.");
				}
			}
			else
			{
				lShop.setText("Du hast nicht genug Geld!");
			}
		}
		if(e.getSource()==this.bNext)
		{
			storysite++;
			if(storysite==2)
			{
				lStory.setText("<html><body>Berührst du Fallen (grau), verlierst du ein Leben und fängst erneut bei dem Raum an. <br>" +
						"Wenn du eine Qualle berührst, verlierst du Lebenspunkte. Du kannst dich mit einem Heiltrank heilen. <br>" +
						"Sowohl normale Gegner als auch Bossgegner haben Leben und fügen dir einen bestimmten Schaden zu.<br>" +
						"Du musst die Bossgegner töten, um in den nächsten Raum zu gelangen.</body></html>");
			}
			else if(storysite==3)
			{
				lStory.setText("<html><body>Dir stehen 3 Arten von Angriff zur Verfügung: <br>" +
						"Nahkampf: Drücke 1 (bzw. halte 1 fest).<br>" +
						"Bogen: Drücke shift.<br>" +
						"Feuerball (verbraucht Mana): Drücke enter. <br></body></html>");
			}
			else if(storysite==4)
			{
				lStory.setText("<html><body>Ebenfalls steht dir ein Shop zur Verfügung, in dem du Heil- und Manatränke <br>" +
						"sowie eine Rüstung kaufen kannst (Vermindert nur den Nahkampfschaden durch Gegner!).<br>" +
						"Heiltrank benutzen: Drücke 2 (heilt bis zu 20 Lebenspunkte)<br>" +
						"Manatrank benutzen: Drücke 3 (stellt bis zu 10 Mana wieder her).</body></html>");
				bNext.setText("Ok, back to game");
			}
			else if(storysite==5)
			{
				game.continueGame();
			}
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
		
		//initialize Controller
		controller = new Controller();
		this.setFocusable(true);
		this.addKeyListener(controller);

		//new layout
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

		//Start new game
		game = new Game(this.cp, this);
		thread = new Thread(game);
		thread.start();
	}
	
	public void win(boolean win) {
		game = null;

		// Zwischenmenu laden
		GameoverPanel = new JPanel();
		GameoverPanel.setBounds(0, 0, 600, 600);
		GameoverPanel.setLayout(new GridBagLayout());
		JLabel lEnde = new JLabel("", JLabel.CENTER);
		lEnde.setFont(new Font("Serif", Font.PLAIN, 25));
		if(win)
		{
			lEnde.setText("You win!");
		} 
		else
		{
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

	public void shop(Player player) 
	{
		this.player = player;
		shopPanel = new JPanel();
		shopPanel.setBounds(0, 0, 600, 600);
		shopPanel.setLayout(new GridBagLayout());
		
		
		lShop = new JLabel("Willkommen im Shop!");
		lShop.setFont(new Font("Serif", Font.PLAIN, 25));
		lShopc.gridx = 0;
		lShopc.gridy = 0;
		lShopc.fill = GridBagConstraints.BOTH;
		
		bBuyHealthpotion = new JButton("Buy Healthpotion [Preis 10]");
		bBuyHealthpotion.addActionListener(this);
		bBuyHealthpotionc.gridx = 0;
		bBuyHealthpotionc.gridy = 1;
		bBuyHealthpotionc.fill = GridBagConstraints.HORIZONTAL;
		
		bBuyManapotion = new JButton("Buy Manapotion [Preis 10]");
		bBuyManapotion.addActionListener(this);
		bBuyManapotionc.gridx = 0;
		bBuyManapotionc.gridy = 2;
		bBuyManapotionc.fill = GridBagConstraints.HORIZONTAL;
		
		bBuyArmor = new JButton("Buy Armor [Preis 50]");
		bBuyArmor.addActionListener(this);
		bBuyArmorc.gridx = 0;
		bBuyArmorc.gridy = 3;
		bBuyArmorc.fill = GridBagConstraints.HORIZONTAL;
		
		lMoney = new JLabel("Money: " + player.getMoney(), JLabel.CENTER);
		//lMoney = new JLabel("Money: " + 5, JLabel.CENTER);
		lMoneyc.gridx = 0;
		lMoneyc.gridy = 4;
		lMoneyc.fill = GridBagConstraints.BOTH;
		
		bBackToGame = new JButton("Back to game");
		bBackToGamec.gridx = 0;
		bBackToGamec.gridy = 5;
		bBackToGamec.fill = GridBagConstraints.HORIZONTAL;
		
		bBackToGame.addActionListener(this);
		bBackToGame.setDefaultCapable(true);
		
		shopPanel.add(lShop, lShopc);
		shopPanel.add(bBuyHealthpotion, bBuyHealthpotionc);
		shopPanel.add(bBuyManapotion, bBuyManapotionc);
		shopPanel.add(bBuyArmor, bBuyArmorc);
		shopPanel.add(lMoney, lMoneyc);
		shopPanel.add(bBackToGame, bBackToGamec);

		shopPanel.setVisible(true);
		
		cp.add(shopPanel);
		
		this.pack();
		this.setSize(xFrame, yFrame);
	}
	
	public void NPCstory()
	{
		storysite = 1;
		storyPanel = new JPanel();
		storyPanel.setBounds(0, 0, 600, 600);
		storyPanel.setLayout(new GridBagLayout());
		
		
		lStory = new JLabel("<html><body>Willkommen bei Dungeon Crawler.<br>" +
				"Der Held, die Qualle, wird mit den Pfeiltasten durch das Labyrinth gesteuert. <br>" +
				"Hindernisse sind schwarz, der Eingang grün und der Ausgang blau. <br>" +
				"Ziel des Spiels ist es, den Ausgang zu erreichen. Dabei triffst du auf unterschiedliche Gegner <br>" +
				"(Quallen) oder Bossgegner (ebenfalls Quallen).</body></html>");
		lStoryc.gridx = 0;
		lStoryc.gridy = 0;
		lStoryc.fill = GridBagConstraints.BOTH;
		
		bNext = new JButton("Next");
		bNext.addActionListener(this);
		bNextc.gridx = 0;
		bNextc.gridy = 1;
		bNextc.fill = GridBagConstraints.HORIZONTAL;
		
		storyPanel.add(lStory, lStoryc);
		storyPanel.add(bNext, bNextc);
		storyPanel.setVisible(true);
		
		cp.add(storyPanel);
		
		this.pack();
		this.setSize(xFrame, yFrame);
	}

}
