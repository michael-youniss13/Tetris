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

public class ScoreField extends JPanel {
	
	private int score;
	private int lines;
	private int level;
	
	public ScoreField(){
		this.score = 0;
		this.lines = 0;
		this.level = 0;
		
		Timer updater = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		updater.start();
	}

	void tick(){
		updateScoreField();
		repaint();
	}

	public void updateScoreField(){
		if(TetrisFrame.score != this.score){
			this.score = TetrisFrame.score;
		}
		if(TetrisFrame.rows != this.lines){
			this.lines= TetrisFrame.rows;
		}
		if(TetrisFrame.level != this.level){
			this.level = TetrisFrame.level;
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		Graphics2D graphics = (Graphics2D) g;
		Stroke s = graphics.getStroke();
		graphics.setStroke(new BasicStroke(4));
		graphics.drawRect(0, 0, 140, 140);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		String scoreString = String.format("Score: %d", this.score);
		String lineString = String.format("Lines: %d", this.lines);
		String levelString = String.format("Level: %d", this.level);
		g.drawString(scoreString, 10, 40);
		g.drawString(lineString, 10, 70);
		g.drawString(levelString, 10, 100);
		graphics.setStroke(s);
	}
	

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(140, 140);
	}
	
	
	

}
