package gruppe63_dungeon_crawler;


import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Thread thread;
	
	// Game klasse?
	//private static Game game; // Wieso game auf static?
	private Game game;
	@SuppressWarnings("unused")
	private Enemyquest qenemy;
	
	private JPanel GameoverPanel;
	private JPanel shopPanel;
	private JPanel storyPanel;
	private JPanel questPanel;
	@SuppressWarnings("unused")
	private JPanel gameStopPanel;
	private JPanel LevelUpPanel;
	//private JPanel quit;
	//boolean quit = false;
	private Container cp;
	int difficulty;
	public Controller controller;
	private Player player = null;
	private int storysite;
	private int points;
	
	private int xFrame=610;
	private int yFrame=689;
	

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
	JButton bLoadSavegame;
	
	JButton bServer;
	JButton bClient2;
	
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
	GridBagConstraints bLoadSavegamec = new GridBagConstraints();
	
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
	
	GridBagConstraints bServerc = new GridBagConstraints();
	GridBagConstraints bClient2c = new GridBagConstraints();
	GridBagConstraints lWinnerPoints1c = new GridBagConstraints();
	GridBagConstraints lWinnerPoints2c = new GridBagConstraints();
	
	// NPCstory-elements
	JLabel lStory;
	JButton bNext;
	GridBagConstraints lStoryc = new GridBagConstraints();
	GridBagConstraints bNextc = new GridBagConstraints();
	
	// Playerlevelup-elements
	JLabel lLevelup;
	JLabel lPoints;
	JButton bLevelupMaxHealth;
	JButton bLevelupMaxMana;
	JButton bLevelupFireres;
	JButton bLevelupMagicDmg;
	GridBagConstraints lLevelupc = new GridBagConstraints();
	GridBagConstraints lPointsc = new GridBagConstraints();
	GridBagConstraints bLevelupMaxHealthc = new GridBagConstraints();
	GridBagConstraints bLevelupMaxManac = new GridBagConstraints();
	GridBagConstraints bLevelupFireresc = new GridBagConstraints();
	GridBagConstraints bLevelupMagicDmgc = new GridBagConstraints();
	
	//Quest-elements
	JLabel aufgabe;
	JButton ok;
	JButton oke;
	JButton no;
	JButton nop;
	GridBagConstraints aufgabes = new GridBagConstraints();
	GridBagConstraints oks = new GridBagConstraints();
	GridBagConstraints okes = new GridBagConstraints();
	GridBagConstraints nos = new GridBagConstraints();
	GridBagConstraints nops = new GridBagConstraints();
	
	//Stopgame
	JLabel pause;
	GridBagConstraints pauses = new GridBagConstraints();
	
	


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
		bLoadSavegame = new JButton("Load last Savegame");
		bLoadSavegame.addActionListener(this);
		
		bServer = new JButton("Server und 1. Client starten");
		bServer.addActionListener(this);
		bClient2 = new JButton("2. Client starten");
		bClient2.addActionListener(this);

		
		
		
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
		
		// Initialize LoadSavegamebutton
		bLoadSavegamec.gridx = 0;
		bLoadSavegamec.gridy = 5;
		bLoadSavegamec.fill = GridBagConstraints.HORIZONTAL;
		bLoadSavegamec.weightx = 1.0;
		
		// Initialize Serverbutton
		bServerc.gridx = 0;
		bServerc.gridy = 6;
		bServerc.fill = GridBagConstraints.HORIZONTAL;
		bServerc.weightx = 2.0;
		
// Initialize Clientbutton 2
		bClient2c.gridx = 0;
		bClient2c.gridy = 9;
		bClient2c.fill = GridBagConstraints.HORIZONTAL;
		bClient2c.weightx = 2.0;


		

		cp.setLayout(new GridBagLayout());

		// Add Elements to ContentPane
		cp.add(lTitle, lTitlec);
		cp.add(bStart, bStartc);
		cp.add(bSettings, bSettingsc);
		cp.add(bExit, bExitc);
		cp.add(bServer, bServerc);
		cp.add(bClient2, bClient2c);
		cp.add(bLoadSavegame, bLoadSavegamec);


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
			this.initSound();
			this.startGame(0, false);


					
		}
		if(e.getSource()==this.bExit)
		{
			System.exit(0);
		}
		
		if(e.getSource()==this.ok)
		{
			try {
				Sound.main2(null);
				} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}	
		    game.setQuest(true);
			game.continueGame();
						
		}
		if(e.getSource()==this.oke)
		{
			try {
				Sound.main2(null);
				} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}	
		    game.setQuest2(true);
			game.continueGame();
						
		}
		if(e.getSource()==this.no)
		{ 
			try {
				 Sound.main(null);
				 } catch (UnsupportedAudioFileException | IOException
				 | LineUnavailableException e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();
				 }
			
			game.continueGame();
						
			 Object[] options = {"OK"};
			
			 JOptionPane.showOptionDialog(null, "<html><body>!!!!!Spielverderber!!!!<br>"+ "Du willst mich nur aergern!<br>"+" Dabei habe ich mir so Muehe gegeben :(</body></html>","Die Nixe dreht sich beleidigt weg", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]); 
		}
		if(e.getSource()==this.nop)
		{ 
			try {
				 Sound.main(null);
				 } catch (UnsupportedAudioFileException | IOException
				 | LineUnavailableException e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();
				 }
			game.continueGame();
			 Object[] options = {"OK"};
			
			 JOptionPane.showOptionDialog(null, "<html><body>!!!!!Schade!!!!<br>"+ "Jetzt werde ich Tage brauchen bis ich Bubous gefunden habe und er ist schon wieder der Gewinner!<br>"+" Dabei habe ich mir so eine schoene Belohnung ausgedacht :(</body></html>","Info", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]); 
		}
		
		if(e.getSource()==this.bClient2)
		{
			
				this.startGame(2, false);
			
		}
		
		
		
		if(e.getSource()==this.bServer)
		{
			MultiServer server = new MultiServer();
			Thread t = new Thread(server);
			t.start();
			this.startGame(1, false);		
			
				
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
			this.initialize();
			setVisible(true);
		}
		if(e.getSource()==this.bClose)
		{
			setVisible(false);
			cp.removeAll();
			this.initialize();
			setVisible(true);
		}
		if(e.getSource()==this.bRestart)
		{
			this.remove(GameoverPanel);
			this.initialize();
		}
		if(e.getSource()==this.bBackToGame)
		{
			this.initSound();
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
			try {
	            Sound.main3(null);
	            }
			catch (UnsupportedAudioFileException | IOException
					| LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			storysite++;
			if(storysite==2)
			{
				lStory.setText("<html><body>Ziel ist es, sich durch die Gefahren der Tiefe zu schlaengeln<br>und das Endziel zu erreichen.<br> Dabei triffst du auf unterschiedliche Gegner und Meereserscheinungen. <br>" +
				        "Pass auf, wenn du auf Kraben triffst, musst du diese erledigen, sonst werden sie dich heimsuchen und du bleibst auf ewig in dem Labyrinth der Meere gefangen.Fangen dich die Quallen, erfaehrst du Schaden. <br>" +
						            "Sinken deine Gesundheitspunkte unter 100, verlierst du ein Leben und musst wieder zum Anfang des Raums. Jedoch kannst du dich mit einem Heiltrank heilen. <br>" +
						            "oder du sammelst Items die im Labyrinth verteilt sind.</body></html>");
			}
			else if(storysite==3)
			{
				lStory.setText("<html><body>Zum Angriff und Steuerung<br><br>Dir stehen 3 Arten von Angriff zur Verfuegung: <br>" +
			             "Nahkampf: Druecke 1 (bzw. halte 1 fest).<br>" +
			             "Bogen: Druecke shift.<br>" +
			             "Feuerball (verbraucht Mana): Druecke enter. <br></body></html>");
			}
			else if(storysite==4)
			{
				lStory.setText("<html><body>Ebenfalls kannst du den Unterwasser-Shop besuchen, in dem du Heil- und Manatraenke <br>" +
						"sowie eine Rüstung kaufen kannst (Vermindert nur den Nahkampfschaden durch Gegner!).<br>" +
						"Gebrauchsanweisung zur Benutzung der gekauften Artikel<br><br>Heiltrank benutzen: Druecke 2 (heilt bis zu 20 Lebenspunkte)<br>" +
						"Manatrank benutzen: Drücke 3 (stellt bis zu 10 Mana wieder her).</body></html>");
				bNext.setText("Ok, back to game");
			}
			else if(storysite==5)
			{
				game.continueGame();
			}			
		}
		if(e.getSource()==bLevelupMaxHealth)
		{
			if (points > 0)
			{
				player.setMaxHealth(player.getMaxHealth() + 10);
				points--;
				lPoints.setText("Punkte: " + points);
			}
			else
			{
				lLevelup.setText("Nicht genügend Punkte!");
			}
		}
		if(e.getSource()==bLevelupMaxMana)
		{
			if (points > 0)
			{
				player.setMaxMana(player.getMaxMana() + 10);
				points--;
				lPoints.setText("Punkte: " + points);
			}
			else
			{
				lLevelup.setText("Nicht genügend Punkte!");
			}
		}
		if(e.getSource() == bLevelupFireres)
		{
			if (points > 0)
			{
				if (player.getFireres() == false)
				{
					player.setFireres(true);
					points--;
					lPoints.setText("Punkte: " + points);
				}
				else
				{
					lLevelup.setText("Bereits erhöht!");
				}
			}
			else
			{
				lLevelup.setText("Nicht genügend Punkte!");
			}
		}
		if(e.getSource() == bLevelupMagicDmg)
		{
			if (points > 0)
			{
				if (player.getUpMagicdmg() == false)
				{
					player.setUpMagicdmg(true);
					points--;
					lPoints.setText("Punkte: " + points);
				}
				else
				{
					lLevelup.setText("Bereits erhöht!");
				}
			}
			else
			{
				lLevelup.setText("Nicht genügend Punkte!");
			}
		}
		if(e.getSource() == bLoadSavegame)
		{
			this.initSound();
			this.startGame(0, true);
		}
		
	}
	
	public void startGame(int n, boolean b)
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
		game = new Game(this.cp, this, n, b);
		thread = new Thread(game);
		thread.start();
	}
	
	public void win(boolean win, Player player) {
		int client=game.getClient();
		game = null;

		this.player = player;
		
		// Zwischenmenu laden
		GameoverPanel = new JPanel();
		GameoverPanel.setBounds(0, 0, 600, 600);
		GameoverPanel.setLayout(new GridBagLayout());
		JLabel lEnde = new JLabel("", JLabel.CENTER);
		lEnde.setFont(new Font("Serif", Font.PLAIN, 25));
		if(win)
		{
			lEnde.setText("You win!");
			Game.actualroom=1;
		} 
		else
		{
			lEnde.setText("You lose!");
			Game.actualroom=1;
		}
		
		JLabel lWinnerPoints1 = new JLabel("Your winnerpoints : " + player.getWinnerpoints(), JLabel.CENTER);
		lWinnerPoints1.setFont(new Font("Serif", Font.PLAIN, 25));
		JLabel lWinnerPoints2 = new JLabel("Your enemy's winnerpoints : " + player.getWinnerpoints2(), JLabel.CENTER);
		lWinnerPoints2.setFont(new Font("Serif", Font.PLAIN, 25));
		
		bRestart = new JButton("Hauptmenu");
		bRestart.addActionListener(this);
		bRestart.setDefaultCapable(true);
		bRestartc.gridx = 0;
		bRestartc.gridy = 1;
		bRestartc.fill = GridBagConstraints.HORIZONTAL;
		
		lEndec.gridx = 0;
		lEndec.gridy = 0;
		lEndec.fill = GridBagConstraints.HORIZONTAL;
		
		lWinnerPoints1c.gridx = 0;
		lWinnerPoints1c.gridy = 3;
		lWinnerPoints1c.fill = GridBagConstraints.HORIZONTAL;
		lWinnerPoints2c.gridx = 0;
		lWinnerPoints2c.gridy = 4;
		lWinnerPoints2c.fill = GridBagConstraints.HORIZONTAL;
		
		bExitc2.gridx = 0;
		bExitc2.gridy = 2;
		bExitc2.fill = GridBagConstraints.HORIZONTAL;
		bExitc2.weightx = 1.0;
		
		GameoverPanel.add(lEnde, lEndec);
		GameoverPanel.add(bRestart, bRestartc);
		GameoverPanel.add(bExit, bExitc2);
		if (client!=0) {
			GameoverPanel.add(lWinnerPoints1, lWinnerPoints1c);
			GameoverPanel.add(lWinnerPoints2, lWinnerPoints2c);
			}


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
		
		
		lStory = new JLabel("<html><body>Willkommen bei Dungeon Crawler der Meere.<br><br>" +
				        "Hier erhaelst du einige Anweisungen und erklaerungen zum Spiel.<br><br>DU, die Krake, kannst mit den Pfeiltasten durch das Labyrinth laufen. <br>Korallen sind deine Hindernisse, sie lassen dich nicht durch und tuermen sich auf wie Waende<br> Du kommst aus einer wunderschoenen Muschel.<br>Doch um weiter zu kommen musst du dich zur Glaskugel begeben. <br>Leicht zu erkennen durch das Ausgangssymbol " +
				       "</body></html>"); 
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

	public void playerLevelUp(Player p, int punkte)
	{
		this.player = p;
		points = punkte;
		LevelUpPanel = new JPanel();
		LevelUpPanel.setBounds(0, 0, 600, 600);
		LevelUpPanel.setLayout(new GridBagLayout());
		
		
		lLevelup = new JLabel("Du bist eine Stufe aufgestiegen!");
		lLevelup.setFont(new Font("Serif", Font.PLAIN, 25));
		lLevelupc.gridx = 0;
		lLevelupc.gridy = 0;
		lLevelupc.fill = GridBagConstraints.BOTH;
		
		bLevelupMaxHealth = new JButton("+10 Maximum Health");
		bLevelupMaxHealth.addActionListener(this);
		bLevelupMaxHealthc.gridx = 0;
		bLevelupMaxHealthc.gridy = 1;
		bLevelupMaxHealthc.fill = GridBagConstraints.HORIZONTAL;
		
		bLevelupMaxMana = new JButton("+10 Maximum Mana");
		bLevelupMaxMana.addActionListener(this);
		bLevelupMaxManac.gridx = 0;
		bLevelupMaxManac.gridy = 2;
		bLevelupMaxManac.fill = GridBagConstraints.HORIZONTAL;
		
		bLevelupFireres = new JButton("Einmalig: +10 Feuerrüstung (benötigt 5 Upgrades in Maxhealth)");
		bLevelupFireres.addActionListener(this);
		bLevelupFireresc.gridx = 0;
		bLevelupFireresc.gridy = 3;
		bLevelupFireresc.fill = GridBagConstraints.HORIZONTAL;
		
		bLevelupMagicDmg = new JButton("Einmalig: +10 Feuerballschaden (benötigt 5 Upgrades in Maxmana)");
		bLevelupMagicDmg.addActionListener(this);
		bLevelupMagicDmgc.gridx = 0;
		bLevelupMagicDmgc.gridy = 2;
		bLevelupMagicDmgc.fill = GridBagConstraints.HORIZONTAL;
		
		bBackToGame = new JButton("Back to game");
		bBackToGamec.gridx = 0;
		bBackToGamec.gridy = 5;
		bBackToGamec.fill = GridBagConstraints.HORIZONTAL;
		
		lPoints = new JLabel("Punkte: " + points);
		lPointsc.gridx = 0;
		lPointsc.gridy = 6;
		lPointsc.fill = GridBagConstraints.BOTH;
		
		bBackToGame.addActionListener(this);
		bBackToGame.setDefaultCapable(true);
		
		LevelUpPanel.add(lLevelup, lLevelupc);
		LevelUpPanel.add(bLevelupMaxHealth, bLevelupMaxHealthc);
		LevelUpPanel.add(bLevelupMaxMana, bLevelupMaxManac);
		LevelUpPanel.add(bLevelupFireres, bLevelupFireresc);
		LevelUpPanel.add(bLevelupMagicDmg, bLevelupMagicDmgc);
		LevelUpPanel.add(bBackToGame, bBackToGamec);
		LevelUpPanel.add(lPoints, lPointsc);

		LevelUpPanel.setVisible(true);
		
		cp.add(LevelUpPanel);
		
		this.pack();
		this.setSize(xFrame, yFrame);
	}

	public void NixeQuest() {
		
		questPanel = new JPanel();
		questPanel.setBounds(0, 0, 600, 600);
		questPanel.setLayout(new GridBagLayout());
		
		
		aufgabe = new JLabel("<html><body>la la la la Hallo !<br>" +
				"Ich bin die Nixe Melodie. <br>" +
				"Ich und eine boese rosa Qualle haben meinen Ring versteckt, <br>" +
				" wenn du ihn findest, dann erhaelst du 10 XP.<br>" +
				"Kannst du meinen Ring finden?....<br> traust du dir das zu ?? <br> Ha haha ha<br></body></html>");
		aufgabes.gridx = 0;
		aufgabes.gridy = 0;
		aufgabes.fill = GridBagConstraints.BOTH;
		
		ok = new JButton("<html><body>Ich neheme die Herausforderung an <br>"+"und finde den Ring</body></html>");
		ok.addActionListener(this);
		oks.gridx = 0;
		oks.gridy = 2;
		oks.fill = GridBagConstraints.HORIZONTAL;
		
		questPanel.add(aufgabe, aufgabes);
		questPanel.add(ok, oks);
		questPanel.setVisible(true);
		
		no = new JButton("<html><body>Tut mir leid,<br>"+ "ich bin zu erwachsen, um Versteckspiele zu spielen<br></body></html>");
		no.addActionListener(this);
		nos.gridx = 0;
		nos.gridy = 6;
		nos.fill = GridBagConstraints.HORIZONTAL;
		
		questPanel.add(aufgabe, aufgabes);
		questPanel.add(no, nos);
		questPanel.setVisible(true);
		
		cp.add(questPanel);
		
		this.pack();
		this.setSize(xFrame, yFrame);
		
	}

	public void FroschQuest() {
		
		questPanel = new JPanel();
		questPanel.setBounds(0, 0, 600, 600);
		questPanel.setLayout(new GridBagLayout());
		
		
		aufgabe = new JLabel("<html><body>Quack.... Hallo !...Quack<br>" +
				"Ich bin Feivel der Froschkewitz. <br>" +
				"Ich und mein Freund Bubous spielen verstecken.<br>Bubous hat sich versteckt, und ich kann ihn nicht finden. <br> Er ist ein Meister im verstecken.<br>Selbst der Buergermeister konnte ihn nicht finden.<br> " +
				"??...Koennt ihr mir helfen Bubous zu finden??.. <br>Wenn ihr es schaft ihn zu finden,dann erhaltet ihr eine Belohnung.<br>" +
				"???..Was die Behlohnung ist wollt ihr wissen ??... <br> Haha das bleibt mein Geheimnis<br>.<br>.<br></body></html>");
		aufgabes.gridx = 0;
		aufgabes.gridy = 0;
		aufgabes.fill = GridBagConstraints.BOTH;
		
		oke = new JButton("<html><body>Wir helfen dir <br>"+"und finden deinen Freund</body></html>");
		oke.addActionListener(this);
		okes.gridx = 0;
		okes.gridy = 2;
		okes.fill = GridBagConstraints.HORIZONTAL;
		
		questPanel.add(aufgabe, aufgabes);
		questPanel.add(oke, okes);
		questPanel.setVisible(true);
		
		nop = new JButton("<html><body>Tut mir leid,<br>"+ "Wir sind zu erwachsen, um Versteckspiele zu spielen<br></body></html>");
		nop.addActionListener(this);
		nops.gridx = 0;
		nops.gridy = 6;
		nops.fill = GridBagConstraints.HORIZONTAL;
		
		questPanel.add(aufgabe, aufgabes);
		questPanel.add(nop, nops);
		questPanel.setVisible(true);
		
		cp.add(questPanel);
		
		this.pack();
		this.setSize(xFrame, yFrame);
	}
	
	/*
	public void gameStop(){
		
		questPanel = new JPanel();
		questPanel.setBounds(0, 0, 600, 600);
		questPanel.setLayout(new GridBagLayout());
		
		
		aufgabe = new JLabel("<html><body>Quack.... Hallo !...Quack<br>" +
				"Ich bin Feivel der Froschkewitz. <br>" +
				"Ich und mein Freund Bubous spielen verstecken.<br>Bubous hat sich versteckt, und ich kann ihn nicht finden. <br> Er ist ein Meister im verstecken.<br>Selbst der Buergermeister konnte ihn nicht finden.<br> " +
				"??...Koennt ihr mir helfen Bubous zu finden??.. <br>Wenn ihr es schaft ihn zu finden,dann erhaltet ihr eine Belohnung.<br>" +
				"???..Was die Behlohnung ist wollt ihr wissen ??... <br> Haha das bleibt mein Geheimnis<br>.<br>.<br></body></html>");
		aufgabes.gridx = 0;
		aufgabes.gridy = 0;
		aufgabes.fill = GridBagConstraints.BOTH;
		
		ok = new JButton("<html><body>Wir helfen dir <br>"+"und finden deinen Freund</body></html>");
		ok.addActionListener(this);
		oks.gridx = 0;
		oks.gridy = 2;
		oks.fill = GridBagConstraints.HORIZONTAL;
		
		questPanel.add(aufgabe, aufgabes);
		questPanel.add(ok, oks);
		questPanel.setVisible(true);
		
		nop = new JButton("<html><body>Tut mir leid,<br>"+ "Wir sind zu erwachsen, um Versteckspiele zu spielen<br></body></html>");
		nop.addActionListener(this);
		nops.gridx = 0;
		nops.gridy = 6;
		nops.fill = GridBagConstraints.HORIZONTAL;
		
		questPanel.add(aufgabe, aufgabes);
		questPanel.add(nop, nops);
		questPanel.setVisible(true);
		
		cp.add(questPanel);
		
		this.pack();
		this.setSize(xFrame, yFrame);
		
		
		/*gameStopPanel = new JPanel();
		gameStopPanel.setBounds(0, 0, 600, 600);
		gameStopPanel.setLayout(new GridBagLayout());
		
		bBackToGame = new JButton("Back to game (Z)");
		bBackToGamec.gridx = 0;
		bBackToGamec.gridy = 1;
		bBackToGamec.fill = GridBagConstraints.HORIZONTAL;
		
		bBackToGame.addActionListener(this);
		bBackToGame.setDefaultCapable(true);
		
		gameStopPanel.add(bBackToGame, bBackToGamec);
		gameStopPanel.setVisible(true);
		
		cp.add(gameStopPanel);
		
		bRestart = new JButton("Hauptmenu (M)");
		bRestart.addActionListener(this);
		bRestart.setDefaultCapable(true);
		bRestartc.gridx = 0;
		bRestartc.gridy = 6;
		bRestartc.fill = GridBagConstraints.HORIZONTAL;
		
		gameStopPanel.add(bRestart, bRestartc);
		gameStopPanel.setVisible(true);
		
		cp.add(gameStopPanel);
		
		/*lEndec.gridx = 0;
		lEndec.gridy = 0;
		lEndec.fill = GridBagConstraints.HORIZONTAL;*/
		
		/*bExit = new JButton("Exit (Q)");
		bExit.addActionListener(this);
		bExit.setDefaultCapable(true);
		bExitc2.gridx = 0;
		bExitc2.gridy = 6;
		bExitc2.fill = GridBagConstraints.HORIZONTAL;
		//bExitc2 = new GridBagConstraints();
		
		gameStopPanel.add(bExit, bExitc2);
		gameStopPanel.setVisible(true);
		
		cp.add(gameStopPanel);
		
		this.pack();
		this.setSize(xFrame, yFrame);*/
	//}

	public void Taucher() {
		// TODO Auto-generated method stub
		questPanel = new JPanel();
		questPanel.setBounds(0, 0, 600, 600);
		questPanel.setLayout(new GridBagLayout());
		
		
		aufgabe = new JLabel("<html><body> Hallo !<br>" +
				"....  <br>" +
				"Nein, den Bubous habe ich nicht gesehen.<br> " +
				"<br>.<br>.<br></body></html>");
		aufgabes.gridx = 0;
		aufgabes.gridy = 0;
		aufgabes.fill = GridBagConstraints.BOTH;
		
		ok = new JButton("<html><body>Danke, wir suchen dann weiter.</body></html>");
		ok.addActionListener(this);
		oks.gridx = 0;
		oks.gridy = 2;
		oks.fill = GridBagConstraints.HORIZONTAL;
		
		questPanel.add(aufgabe, aufgabes);
		questPanel.add(ok, oks);
		questPanel.setVisible(true);
	}
	
	private void initSound()
	{
		try {
			Sound.main(null);
			} catch (UnsupportedAudioFileException | IOException
					| LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
	}

}
