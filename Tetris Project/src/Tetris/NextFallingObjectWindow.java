package Tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NextFallingObjectWindow extends JPanel {

	private GameObjects next = new GameObjects(60, 60);
	private String levelLabel;
	private boolean levelComplete = false;
	private Timer timer;

	public NextFallingObjectWindow() {
		timer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
	}

	void tick() {
		if (TetrisFrame.piecesItr == TetrisFrame.pieces.length - 1
				&& !TetrisFrame.getLost()) {
			levelLabel = ("Level " + (TetrisFrame.level + 1) + " Complete!!");
			levelComplete = true;
		} else if (!TetrisFrame.getLost()) {
			next = TetrisFrame.pieces[TetrisFrame.piecesItr + 1];
			levelComplete = false;
		}
		repaint();

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(140, 140);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		Graphics2D graphics = (Graphics2D) g;
		Stroke s = graphics.getStroke();
		graphics.setStroke(new BasicStroke(4));
		graphics.drawRect(0, 0, 140, 140);
		graphics.setStroke(s);
		if (!levelComplete && !TetrisFrame.getLost()) {
			try {
				g.translate(-20, 50);
				next.draw(g);
			} catch (NullPointerException e) {
			}
		} else if (!TetrisFrame.getLost()) {
			g.setFont(new Font("Calibri", Font.BOLD, 16));
			g.drawString(levelLabel, 5, 70);
		} else {
			if (TetrisFrame.getLost()) {
				g.setFont(new Font("Calibri", Font.BOLD, 20));
				g.drawString("Game Over", 22, 70);
				timer.stop();
			}
		}
	}

}