package Tetris;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends GameObjects {

	private int pos_x;
	private int pos_y;
	private int rotate = 0;


	public Line(int x, int y) {
		super(x, y);
		pos_x = x;
		pos_y = y;
		shape();
	}

	@Override
	public void shape() {
		super.square1 = new Square(pos_x, pos_y);
		super.square2 = new Square(pos_x + Square.square_Length, pos_y);
		super.square3 = new Square(pos_x - Square.square_Length, pos_y);
		super.square4 = new Square(pos_x + 2 * Square.square_Length, pos_y);

	}

	public boolean canRotate() {
		if (rotate == 0) {
			if (canDrop()) {
				Square a = new Square(super.square1.pos_x, super.square1.pos_y
						+ Square.square_Length);
				if (a.canDrop()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} else if (rotate == 1) {
			if (canMoveRight() && canMoveLeft() && canDrop()) {
				Square a = new Square(super.square1.pos_x
						+ Square.square_Length, super.square1.pos_y);
				if (a.canMoveRight()) {
					return true;
				} else {
					return false;
				}
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
				super.square2.translatePosition(super.square2.pos_x
						- Square.square_Length, super.square2.pos_y
						+ Square.square_Length);
				super.square3.translatePosition(super.square3.pos_x
						+ Square.square_Length, super.square3.pos_y
						- Square.square_Length);
				super.square4.translatePosition(super.square4.pos_x - 2
						* Square.square_Length, super.square4.pos_y + 2
						* Square.square_Length);
				rotate = 1;
			} else {
				super.square2.translatePosition(super.square2.pos_x
						+ Square.square_Length, super.square2.pos_y
						- Square.square_Length);
				super.square3.translatePosition(super.square3.pos_x
						- Square.square_Length, super.square3.pos_y
						+ Square.square_Length);
				super.square4.translatePosition(super.square4.pos_x + 2
						* Square.square_Length, super.square4.pos_y - 2
						* Square.square_Length);
				rotate = 0;
			}
		}
	}

	@Override
	public void updateGameArray() {
		TetrisFrame.gameField[super.square1.pos_x / 20][super.square1.pos_y / 20] = 3;
		TetrisFrame.gameField[super.square2.pos_x / 20][super.square2.pos_y / 20] = 3;
		TetrisFrame.gameField[super.square3.pos_x / 20][super.square3.pos_y / 20] = 3;
		TetrisFrame.gameField[super.square4.pos_x / 20][super.square4.pos_y / 20] = 3;
	}

	@Override
	public void draw(Graphics g) {
			g.setColor(Color.ORANGE);
			super.square1.draw(g);
			g.setColor(Color.ORANGE);
			super.square2.draw(g);
			g.setColor(Color.ORANGE);
			super.square3.draw(g);
			g.setColor(Color.ORANGE);
			super.square4.draw(g);
			g.setColor(Color.BLACK);
	}

}
