package Tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlayField extends JPanel {

	private JButton reset;
	private JButton quit;
	private JButton instructions;
	private TetrisFrame tetrisRectangle;
	private JFrame instructionsFrame;
	
	public GamePlayField() {
		JPanel jm = new JPanel();
		try {
			newInstructionsButton();
		} catch (IOException e) {
		}
		quitButton();
		resetButton();
		
		jm.add(instructions);
		jm.add(reset);
		jm.add(quit);
		jm.setSize(100, 20);
		jm.setVisible(true);
		add(jm, BorderLayout.NORTH);
		
		tetrisRectangle = new TetrisFrame();
		tetrisRectangle.setFocusable(true);
		add(tetrisRectangle, BorderLayout.WEST);

		JPanel scoreAndNext = new JPanel();
		ScoreField sField = new ScoreField();
		NextFallingObjectWindow nFalling = new NextFallingObjectWindow();
		scoreAndNext.setLayout(new BoxLayout(scoreAndNext, BoxLayout.PAGE_AXIS));
		scoreAndNext.add(nFalling);
		scoreAndNext.add(sField);
		add(scoreAndNext, BorderLayout.EAST);

		Timer timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
		
		tetrisRectangle.setLost(false);
	}

	void tick() {
		if (!instructionsFrame.isShowing() && !tetrisRectangle.isFocusOwner() && !tetrisRectangle.getLost() ) {
			tetrisRectangle.requestFocus();
			TetrisFrame.setTimerOn();
		}
	}
	
	public void resetButton() {
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.disposeOfFrame();
				reset();
				JFrame gamePlayFieldFrame = new JFrame();
				gamePlayFieldFrame.setResizable(false);
				Color bkgd = new Color(73, 170, 245);
				gamePlayFieldFrame.setBackground(bkgd);
				GamePlayField tField = new GamePlayField();
				gamePlayFieldFrame.setTitle("Tetris");
				gamePlayFieldFrame.add(tField);
				gamePlayFieldFrame.pack();
				gamePlayFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gamePlayFieldFrame.setVisible(true);
			}
		});
	}
	
	
	public void quitButton() {
		quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tetrisRectangle.setEnabled(false);
				tetrisRectangle.removeAll();
				System.exit(0);
			}
		});
	}

	public void newInstructionsButton() throws FileNotFoundException {
		instructions = new JButton("Instructions");
		instructions.setContentAreaFilled(false);
		instructionsFrame = new JFrame();
		InstructionsPage iPage = new InstructionsPage();
		instructionsFrame.add(iPage);
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructionsFrame.setTitle("Instructions");
				instructionsFrame.setSize(622, 358);
				instructionsFrame.setVisible(true);
				TetrisFrame.setTimerOff();
			}
		});
	}
	
	public void reset(){
		TetrisFrame.setTimerOff();
		TetrisFrame.score = 0;
		TetrisFrame.rows = 0;
		TetrisFrame.level = 0;
		TetrisFrame.pieces = new GameObjects[10];
		TetrisFrame.setIterator(0);
		TetrisFrame.gameField = new int[10][20];
	}
	

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 500);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
