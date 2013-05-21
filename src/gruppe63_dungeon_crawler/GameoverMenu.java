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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameoverMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	Settings settings = new Settings();
	int lastGameEnd;
	

	public GameoverMenu(int gameEnd) {

		super("Game over");
		lastGameEnd = gameEnd; // 0 = Lose, 1 = Win

	}

	public void initialize() {

		settings.initialize();
		settings.setVisible(false);
		
		Container cp = this.getContentPane();

		final JPanel panel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				super.paintComponent(g);

			}
		};
		
		/* Prefixes:
		 * b = Button
		 * rb = RadioButton
		 * l = Label
		 */
		JLabel lTitle;
		if (lastGameEnd == 0)
		{
			lTitle = new JLabel("You lost!", JLabel.CENTER);
		}
		else
		{
			lTitle = new JLabel("You win!", JLabel.CENTER);
		}
		lTitle.setFont(new Font("Serif", Font.PLAIN, 25));
		
		GridBagConstraints lTitlec = new GridBagConstraints();
		lTitlec.gridx = 0;
		lTitlec.gridy = 1;
		lTitlec.fill = GridBagConstraints.BOTH;

		JButton bStart = new JButton("Start again");

		bStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// Start game, load dungeon
				int difficulty = settings.getDifficulty();
				String diffString = null;
				if (difficulty == 0)
						diffString = "Easy";
				else if (difficulty == 1)
						diffString = "Hard";
				System.out.println("Loading game...");
				System.out.println("Difficulty: " + diffString);
				
				Panel gamePanel = new Panel();
				setVisible(false); // Hide Menu
				

			}
		});

		// Shortcut: Alt+S
		bStart.setMnemonic(KeyEvent.VK_S);

		GridBagConstraints bStartc = new GridBagConstraints();
		bStartc.gridx = 0;
		bStartc.gridy = 2;
		bStartc.gridwidth = 2;
		bStartc.fill = GridBagConstraints.HORIZONTAL;
		bStartc.weightx = 1.0;

		JButton bSettings = new JButton("Settings");

		bSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// open Settings
				settings.setVisible(true);
		        //setVisible(false);

			}
		});

		GridBagConstraints bSettingsc = new GridBagConstraints();
		bSettingsc.gridx = 0;
		bSettingsc.gridy = 3;
		bSettingsc.fill = GridBagConstraints.HORIZONTAL;
		bSettingsc.weightx = 2.0;

		JButton bExit = new JButton("Exit");

		bExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});

		GridBagConstraints bExitc = new GridBagConstraints();
		bExitc.gridx = 0;
		bExitc.gridy = 4;
		bExitc.fill = GridBagConstraints.HORIZONTAL;
		bExitc.weightx = 1.0;

		cp.setLayout(new GridBagLayout());

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

		final GameoverMenu f = new GameoverMenu(0);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				f.initialize();
			}

		});

	}

}
