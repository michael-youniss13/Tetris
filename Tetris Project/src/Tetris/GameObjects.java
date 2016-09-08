package Tetris;

import java.awt.Graphics;

public class GameObjects {

	public Square square1;
	public Square square2;
	public Square square3;
	public Square square4;

	public GameObjects(int x, int y) {
	}

	
	public boolean canDrop() {
		if (square1.canDrop() && square2.canDrop() && square3.canDrop()
				&& square4.canDrop()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canMoveRight() {
		if (square1.canMoveRight() && square2.canMoveRight()
				&& square3.canMoveRight() && square4.canMoveRight()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canMoveLeft() {
		if (square1.canMoveLeft() && square2.canMoveLeft()
				&& square3.canMoveLeft() && square4.canMoveLeft()) {
			return true;
		} else {
			return false;
		}
	}

	public void drop() {
		if (canDrop()) {
			square1.drop();
			square2.drop();
			square3.drop();
			square4.drop();
		}
	}


	public void moveLeft() {
		if (canMoveLeft()) {
			square1.moveLeft();
			square2.moveLeft();
			square3.moveLeft();
			square4.moveLeft();
		}
	}

	public void moveRight() {
		if (canMoveRight()) {
			square1.moveRight();
			square2.moveRight();
			square3.moveRight();
			square4.moveRight();
		}
	}
	
	public void hardDrop(){
		while(canDrop()){
			drop();
		}
	}
	
	public void shape() {
	//override
	}
	public void rotate() {
	//override
	}
	public void updateGameArray() {
	//override
	}
	
	public void setPosition(int x, int y){
		square1.translatePosition(x,y);
		square2.translatePosition(x,y);
		square3.translatePosition(x,y);
		square4.translatePosition(x,y);
	}
	
	
	public void draw(Graphics g) {
		square1.draw(g);
		square2.draw(g);
		square3.draw(g);
		square4.draw(g);
	}
}

