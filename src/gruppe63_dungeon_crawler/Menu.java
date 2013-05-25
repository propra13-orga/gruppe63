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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Menu extends Controller{

	private static final long serialVersionUID = 1L;
	private Thread thread;
	
	// Game klasse?
	private Panel game;
	
	private JPanel nextPanel;
	private Container cp;
	int difficulty;
	

	public Menu() {

		super("Menu");
		

	}

	public void initialize() {

		//settings.initialize();
		//settings.setVisible(false);
		
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
		final JLabel lTitle;
		final JLabel lDifficulty;
		final JRadioButton rbEasy;
		final JRadioButton rbHard;
		final ButtonGroup diffGroup;
		final JButton bStart;
		final JButton bSettings;
		final JButton bExit;
		final JButton bSave;
		final JButton bClose;
		final GridBagConstraints lTitlec = new GridBagConstraints();
		final GridBagConstraints lDifficultyc = new GridBagConstraints();
		final GridBagConstraints bStartc = new GridBagConstraints();
		final GridBagConstraints bSettingsc = new GridBagConstraints();
		final GridBagConstraints rbEasyc = new GridBagConstraints();
		final GridBagConstraints rbHardc = new GridBagConstraints();
		final GridBagConstraints bSavec = new GridBagConstraints();
		final GridBagConstraints bClosec = new GridBagConstraints();
		final GridBagConstraints bExitc = new GridBagConstraints();
		

		bSettings = new JButton("Settings");
		lTitle = new JLabel("Dungeon Crawlers", JLabel.CENTER);
		lDifficulty = new JLabel("Difficulty", JLabel.CENTER);
		bStart = new JButton("Start game");
		bExit = new JButton("Exit");
		
		
		
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
		bStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// Start game, load dungeon
				String diffString = null;
				if (difficulty == 0)
						diffString = "Easy";
				else if (difficulty == 1)
						diffString = "Hard";
				System.out.println("Loading game...");
				System.out.println("Difficulty: " + diffString);
				
				game = new Panel();
				//System.out.println("" + a.geta().getPosX());
				//System.out.println("" + a.geta().posX);
				setVisible(false); // Hide Menu

			}
		});

		// Shortcut: Alt+S
		bStart.setMnemonic(KeyEvent.VK_S);

		bStartc.gridx = 0;
		bStartc.gridy = 2;
		bStartc.gridwidth = 2;
		bStartc.fill = GridBagConstraints.HORIZONTAL;
		bStartc.weightx = 1.0;
		

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
		
		// Initialize Savebutton
		bSave = new JButton("Save and exit");

		bSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		});
		
		bSavec.gridx = 0;
		bSavec.gridy = 2;
		bSavec.fill = GridBagConstraints.HORIZONTAL;
		bSavec.weightx = 1.0;
		
		
		// Initialize Closebutton
		bClose = new JButton("Exit without saving");

		bClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				cp.removeAll();
				cp.add(lTitle, lTitlec);
				cp.add(bStart, bStartc);
				cp.add(bSettings, bSettingsc);
				cp.add(bExit, bExitc);
				setVisible(true);

			}
		});

		bClosec.gridx = 1;
		bClosec.gridy = 2;
		bClosec.fill = GridBagConstraints.HORIZONTAL;
		bClosec.weightx = 1.0;
		

		// Initialize Exitbutton

		bExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});

		bExitc.gridx = 0;
		bExitc.gridy = 4;
		bExitc.fill = GridBagConstraints.HORIZONTAL;
		bExitc.weightx = 1.0;
		
		// Initialize Settingsbutton

		bSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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
		});

		cp.setLayout(new GridBagLayout());

		// Add Elements to ContentPane
		cp.add(lTitle, lTitlec);
		cp.add(bStart, bStartc);
		cp.add(bSettings, bSettingsc);
		cp.add(bExit, bExitc);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setSize(400, 300);
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

}
