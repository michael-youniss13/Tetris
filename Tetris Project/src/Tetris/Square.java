package Tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Square {

	public static final int square_Length = 20;
	public int pos_x;
	public int pos_y;

	public Square(int x, int y) {
		pos_x = x;
		pos_y = y;

	}

	public void translatePosition(int x, int y) {
		this.pos_x = x;
		this.pos_y = y;
	}

	public boolean canDrop() {
		if (pos_y + square_Length <= TetrisFrame.MAX_HEIGHT - square_Length
				&& TetrisFrame.gameField[(pos_x) / 20][(pos_y + square_Length) / 20] == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int highestFreeBoxInColumn(int a) {
		int i = 19;
		while (i >= 0) {
			if (TetrisFrame.gameField[a][i] >= 1) {
				i--;
			} else {
				return i;
			}
		}
		return 0;
	}

	public boolean canMoveRight() {
		boolean status = false;
		if (pos_x + (Square.square_Length) <= TetrisFrame.MAX_WIDTH
				- Square.square_Length) {
			if (pos_x < TetrisFrame.MAX_WIDTH - square_Length
					&& TetrisFrame.gameField[(pos_x / 20) + 1][pos_y / 20] == 0) {
				status = true;
			} else {
				status = false;
			}
		} else {
			status = false;
		}
		return status;
	}

	public boolean canMoveLeft() {
		boolean status = false;
		if (pos_x - (Square.square_Length) >= 0) {
			if (pos_x > 0 && TetrisFrame.gameField[(pos_x / 20) - 1][pos_y / 20] == 0) {
				status = true;
			} else {
				status = false;
			}
		} else {
			status = false;
		}
		return status;
	}

	
	
	public void drop() {
		if (canDrop()) {
			pos_y += square_Length;
		} else {
			//updateGameArray();
		}
	}

	public void moveRight() {
		if (canMoveRight()) {
			pos_x += Square.square_Length;
		}
	}

	public void moveLeft() {
		if (canMoveLeft()) {
				pos_x -= Square.square_Length;
		}
	}

	public void hardDrop() {
		pos_y = (highestFreeBoxInColumn(pos_x / 20)) * square_Length;
	}

	public void draw(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		g.fillRect(pos_x, pos_y, square_Length, square_Length);
		Stroke s = graphics.getStroke();
		graphics.setStroke(new BasicStroke(2));
		graphics.setColor(Color.BLACK);
		graphics.drawRect(pos_x, pos_y, square_Length, square_Length);
		graphics.setStroke(s);
	}

	public void updateGameArray() {
		int x = pos_x;
		int y = pos_y;
		TetrisFrame.gameField[x / 20][y / 20] = 1;
	}

}
