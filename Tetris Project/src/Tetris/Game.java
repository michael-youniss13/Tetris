package Tetris;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {

	private static JFrame gamePlayFieldFrame = new JFrame();
	private JFrame mainMenuFrame = new JFrame();
	private static JFrame instructionsFrame;
	private JButton newGame;
	private JButton quit;
	private JButton instructions;
	private JButton mainMenuButton;
	private Color buttonCol = new Color(168, 59, 57);
	
	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		mainMenuFrame.setBackground(Color.GREEN);
		mainMenuFrame.setTitle("Tetris");
		quitButton();
		newGameButton();
		try {
			newInstructionsButton();
		} catch (FileNotFoundException e) {
		}

		JLabel label = new JLabel("TETRIS");
		Font f = new Font("Times New Roman", Font.BOLD, 50);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(f);
		label.setAlignmentX(200);

		JPanel jpan = new JPanel();
		jpan.setBackground(Color.RED);
		jpan.add(newGame);
		jpan.add(instructions);
		jpan.add(quit);
		jpan.setVisible(true);

		mainMenuFrame.setLayout(new GridLayout(2, 1));
		mainMenuFrame.add(label);
		mainMenuFrame.add(jpan);
		mainMenuFrame.pack();
		mainMenuFrame.setSize(700, 600);
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.setVisible(true);
	
	}

	public void newGameButton() {
		newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePlayFieldFrame.setResizable(false);
				Color bkgd = new Color(73, 170, 245);
				gamePlayFieldFrame.setBackground(bkgd);
				GamePlayField tField = new GamePlayField();
				gamePlayFieldFrame.setTitle("Tetris");
				gamePlayFieldFrame.add(tField);
				gamePlayFieldFrame.pack();
				gamePlayFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gamePlayFieldFrame.setVisible(true);
				mainMenuFrame.setVisible(false);
			}
		});
	}

	public void quitButton() {
		quit = new JButton("Quit");
		quit.setBackground(buttonCol);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuFrame.dispose();
				System.exit(0);
			}
		});
	}

	public void newMainMenuButton() {
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setBackground(buttonCol);
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game mm = new Game();
				instructionsFrame.setVisible(false);
			}
		});
	}

	public void newInstructionsButton() throws FileNotFoundException {
		instructions = new JButton("Instructions");
		instructions.setBackground(buttonCol);
		instructions.setContentAreaFilled(false);
		instructionsFrame = new JFrame();			
		InstructionsPage iPage = new InstructionsPage();
		instructionsFrame.add(iPage);
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructionsFrame.setTitle("Instructions");
				instructionsFrame.setSize(622, 358);
				instructionsFrame.setVisible(true);

			}
		});

	}
	
	
	public static void disposeOfFrame(){
		gamePlayFieldFrame.dispose();
	}

}
