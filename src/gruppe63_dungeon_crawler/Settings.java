package gruppe63_dungeon_crawler;


import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

public class Settings extends Controller {
	
	private static final long serialVersionUID = 1L;
	private int difficulty = 0;
	//Menu menu = new Menu();
	
	
	public Settings()
	{
		super("Einstellungen");
	}
	
	public void initialize() {
		
		//menu.initialize();
		//menu.setVisible(false);

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
		
		JLabel lDifficulty = new JLabel("Difficulty", JLabel.CENTER);
		
		GridBagConstraints lDifficultyc = new GridBagConstraints();
		lDifficultyc.gridx = 0;
		lDifficultyc.gridy = 0;
		lDifficultyc.gridwidth = 2;
		lDifficultyc.fill = GridBagConstraints.BOTH;

		final JRadioButton rbEasy = new JRadioButton("easy");
		
		GridBagConstraints rbEasyc = new GridBagConstraints();
		rbEasyc.gridx = 0;
		rbEasyc.gridy = 1;
		
		final JRadioButton rbHard = new JRadioButton("hard");
		
		GridBagConstraints rbHardc = new GridBagConstraints();
		rbHardc.gridx = 1;
		rbHardc.gridy = 1;
		
		// Max. 1 Button can be active, so we have to put the Radiobuttons in a Buttongroup
		ButtonGroup diffGroup = new ButtonGroup();
		diffGroup.add(rbEasy);
		diffGroup.add(rbHard);

		JButton bSave = new JButton("Save and exit");

		bSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(rbEasy.isSelected())
					difficulty = 0;
				else if(rbHard.isSelected())
					difficulty = 1;
				setVisible(false);
				//menu.setVisible(true);

			}
		});

		GridBagConstraints bSavec = new GridBagConstraints();
		bSavec.gridx = 0;
		bSavec.gridy = 2;
		bSavec.fill = GridBagConstraints.HORIZONTAL;
		bSavec.weightx = 1.0;

		JButton bClose = new JButton("Exit without saving");

		bClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				//menu.setVisible(true);

			}
		});

		GridBagConstraints bClosec = new GridBagConstraints();
		bClosec.gridx = 1;
		bClosec.gridy = 2;
		bClosec.fill = GridBagConstraints.HORIZONTAL;
		bClosec.weightx = 1.0;

		cp.setLayout(new GridBagLayout());

		//cp.add(panel, panelc);
		cp.add(lDifficulty, lDifficultyc);
		cp.add(rbEasy, rbEasyc);
		cp.add(rbHard, rbHardc);
		cp.add(bSave, bSavec);
		cp.add(bClose, bClosec);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setSize(400, 300);
//		this.setResizable(false);
		
		this.setVisible(true);

	}
	
	public int getDifficulty()
	{
		return difficulty;
	}
	
	public void setDifficulty(int diffnew)
	{
		difficulty = diffnew;
	}

}
