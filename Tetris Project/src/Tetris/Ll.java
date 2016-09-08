package Tetris;

import java.awt.Color;
import java.awt.Graphics;

public class Ll extends GameObjects {

	private int pos_x;
	private int pos_y;
	private int rotate;


	public Ll(int x, int y) {
		super(x, y);
		pos_x = x;
		pos_y = y;
		shape();
	}

	@Override
	public void shape() {
		super.square1 = new Square(pos_x, pos_y);
		super.square2 = new Square(pos_x - Square.square_Length, pos_y
				+ Square.square_Length);
		super.square3 = new Square(pos_x - Square.square_Length, pos_y);
		super.square4 = new Square(pos_x + Square.square_Length, pos_y);

	}

	public boolean canRotate() {
		if (rotate == 0) {
			if (canDrop()) {
				return true;
			} else {
				return false;
			}
		} else if (rotate == 1) {
			if (canMoveRight()) {
				return true;
			} else {
				return false;
			}
		} else if (rotate == 2) {
			if (canDrop()) {
				return true;
			} else {
				return false;
			}
		} else if (rotate == 3) {
			if (canMoveLeft()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void rotate() {
		if (canRotate()) {
			if (rotate == 0) {
				super.square2.translatePosition(super.square2.pos_x,
						super.square2.pos_y - 2 * Square.square_Length);
				super.square3.translatePosition(super.square3.pos_x
						+ Square.square_Length, super.square3.pos_y
						- Square.square_Length);
				super.square4.translatePosition(super.square4.pos_x
						- Square.square_Length, super.square4.pos_y
						+ Square.square_Length);
				rotate = 1;
			} else if (rotate == 1) {
				super.square2.translatePosition(super.square2.pos_x + 2
						* Square.square_Length, super.square2.pos_y);
				super.square3.translatePosition(super.square3.pos_x
						+ Square.square_Length, super.square3.pos_y
						+ Square.square_Length);
				super.square4.translatePosition(super.square4.pos_x
						- Square.square_Length, super.square4.pos_y
						- Square.square_Length);
				rotate = 2;
			} else if (rotate == 2) {
				super.square2.translatePosition(super.square2.pos_x,
						super.square2.pos_y + 2 * Square.square_Length);
				super.square3.translatePosition(super.square3.pos_x
						- Square.square_Length, super.square3.pos_y
						+ Square.square_Length);
				super.square4.translatePosition(super.square4.pos_x
						+ Square.square_Length, super.square4.pos_y
						- Square.square_Length);
				rotate = 3;
			} else {
				super.square2.translatePosition(super.square2.pos_x - 2
						* Square.square_Length, super.square2.pos_y);
				super.square3.translatePosition(super.square3.pos_x
						- Square.square_Length, super.square3.pos_y
						- Square.square_Length);
				super.square4.translatePosition(super.square4.pos_x
						+ Square.square_Length, super.square4.pos_y
						+ Square.square_Length);
				rotate = 0;
			}
		}
	}

	@Override
	public void updateGameArray() {
		TetrisFrame.gameField[super.square1.pos_x / 20][super.square1.pos_y / 20] = 4;
		TetrisFrame.gameField[super.square2.pos_x / 20][super.square2.pos_y / 20] = 4;
		TetrisFrame.gameField[super.square3.pos_x / 20][super.square3.pos_y / 20] = 4;
		TetrisFrame.gameField[super.square4.pos_x / 20][super.square4.pos_y / 20] = 4;
	}

	@Override
	public void draw(Graphics g) {
			g.setColor(Color.BLUE);
			super.square1.draw(g);
			g.setColor(Color.BLUE);
			super.square2.draw(g);
			g.setColor(Color.BLUE);
			super.square3.draw(g);
			g.setColor(Color.BLUE);
			super.square4.draw(g);
			g.setColor(Color.BLACK);
	}

}
