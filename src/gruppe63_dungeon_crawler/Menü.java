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

public class Menü extends JFrame {

	private static final long serialVersionUID = 1L;
	Einstellungen einstellungen = new Einstellungen();

	public Menü() {

		super("Menü");

	}

	public void initialize() {

		Container cp = this.getContentPane();

		final JPanel panel = new JPanel() {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				super.paintComponent(g);

			}
		};

		/*GridBagConstraints panelc = new GridBagConstraints();
		panelc.gridx = 0;
		panelc.gridy = 0;
		panelc.gridwidth = 2;
		panelc.fill = GridBagConstraints.BOTH;
		panelc.weightx = 1.0;
		panelc.weighty = 1.0;*/
		
		JLabel lTitle = new JLabel("Dungeon Crawlers", JLabel.CENTER);
		lTitle.setFont(new Font("Serif", Font.PLAIN, 25));
		
		GridBagConstraints lTitlec = new GridBagConstraints();
		lTitlec.gridx = 0;
		lTitlec.gridy = 1;
		lTitlec.fill = GridBagConstraints.BOTH;

		JButton bStart = new JButton("Spiel starten");

		bStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// Spiel starten, dungeon laden mit gespeicherten einstellungen
				System.out.println("Noch nicht implementiert!");
				einstellungen.getDifficulty();
				System.out.println("Schwierigkeitsgrad: " + einstellungen.getDifficulty());
				

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

		JButton bSettings = new JButton("Einstellungen");

		bSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Einstellungen öffnen
				einstellungen.initialize();

			}
		});

		GridBagConstraints bSettingsc = new GridBagConstraints();
		bSettingsc.gridx = 0;
		bSettingsc.gridy = 3;
		bSettingsc.fill = GridBagConstraints.HORIZONTAL;
		bSettingsc.weightx = 2.0;

		JButton bExit = new JButton("Beenden");

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

		//cp.add(panel, panelc);
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

		final Menü f = new Menü();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				f.initialize();
			}

		});

	}

}
