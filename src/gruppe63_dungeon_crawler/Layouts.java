package gruppe63_dungeon_crawler;

import java.awt.CardLayout;
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

public class Layouts {
	static Menu menu = new Menu();
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		CardLayout cardlayout = new CardLayout();
		cardlayout.addLayoutComponent("hallo", menu);
	}

}
