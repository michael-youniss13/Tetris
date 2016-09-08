package Tetris;


import java.awt.Color;
import java.awt.Graphics;


public class Box extends GameObjects{

	private int pos_x;
	private int pos_y;
	
	public Box(int x, int y) {
		 super(x,y);
		 pos_x = x;
		 pos_y = y; 
		 shape();
	}
	
	@Override
	public void shape(){
		super.square1 = new Square(pos_x, pos_y);
		super.square2 = new Square(pos_x, pos_y + Square.square_Length);
		super.square3 = new Square(pos_x + Square.square_Length, pos_y);
		super.square4 = new Square(pos_x + Square.square_Length, pos_y + Square.square_Length);
		
	}
	@Override
	public void rotate(){}
	
	@Override
	public void updateGameArray(){
		TetrisFrame.gameField[super.square1.pos_x/20][super.square1.pos_y/20] = 2;
		TetrisFrame.gameField[super.square2.pos_x/20][super.square2.pos_y/20] = 2;
		TetrisFrame.gameField[super.square3.pos_x/20][super.square3.pos_y/20] = 2;
		TetrisFrame.gameField[super.square4.pos_x/20][super.square4.pos_y/20] = 2;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		super.square1.draw(g);
		g.setColor(Color.GREEN);
		super.square2.draw(g);
		g.setColor(Color.GREEN);
		super.square3.draw(g);
		g.setColor(Color.GREEN);
		super.square4.draw(g);
		g.setColor(Color.BLACK);		
	}
	
}
