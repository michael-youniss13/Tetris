package Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsPage extends JPanel {

	private JLabel ins;
	public BufferedImage ir;
	
	public InstructionsPage(){
		setBackground(Color.WHITE); 
		ins = new JLabel("<html> Tetris: "
		 		+ "<br>"
				+ "<br> Tetris is a simple game. The goal is to move and rotate the dropping"
				+ "<br> shapes so that they form full rows which will then vanish. See how many"
				+ "<br> points you can get before the game ends when the blocks stack too high/"
				+ "<br> If you just can't get enough tetris try hitting the reset button when your"
				+ "<br> game ends and keep on playing."
				+ "<br>"
				+ "<br> The controls are simple"
				+ "<br> -UP arrow key will rotate the object"
				+ "<br> -LEFT arrow key moves the object to the left"
				+ "<br> -RIGHT arrow key moves the object to the right"
				+ "<br> -DOWN arrow key makes the object descend faster"
				+ "<br> -SPACE BAR hard drops the object"
				+ "<br>"
				+ "<br>	Good Luck!</html>");
		
		 add(ins);
	}
	
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(622, 358);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
