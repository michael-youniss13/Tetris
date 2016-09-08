package Tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class TetrisFrame extends JPanel {

	public static int MAX_HEIGHT = 400;
	public static int MAX_WIDTH = 200;
	public static GameObjects[] pieces;
	public static int piecesItr = 0;
	public static int score;
	public static int rows;
	public static int level = 0;
	public static int[][] gameField = new int[10][20];
	private static Timer timer;
	private int timeInterval = 250;
	private static boolean lost = false;
	private int slideIterator = 0;

	public TetrisFrame() {
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					pieces[piecesItr].rotate();
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					pieces[piecesItr].drop();
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					pieces[piecesItr].moveLeft();
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					pieces[piecesItr].hardDrop();
					pieces[piecesItr].updateGameArray();
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					pieces[piecesItr].moveRight();
					repaint();
				}

			}
		});
		pieces = createNewGameObjectsArray(10);
		setBackground(Color.RED);
		timer = new Timer(timeInterval, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
	}

	void tick() {
		if (pieces[piecesItr].canDrop()) {
			pieces[piecesItr].drop();
		} else if (canSlide()) {
		} else {
			piecesItr++;
			score += 4;
			if (piecesItr == pieces.length) {
				level += 1;
				timeInterval -= 10;
				pieces = createNewGameObjectsArray(level * 50 + 10);
			}
			if (checkIfLost()) {
				timer.stop();
				lost = true;
			}
		}
		detectFullRows();
		repaint();
	}

	public boolean canSlide() {
		if (pieces[piecesItr].canMoveLeft() || pieces[piecesItr].canMoveRight()) {
			if (slideIterator < 1) {
				slideIterator++;
				repaint();
				return true;
			} else {
				pieces[piecesItr].updateGameArray();
				return false;
			}
		} else {
			pieces[piecesItr].updateGameArray();
			return false;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		Graphics2D graphics = (Graphics2D) g;
		Stroke orig = graphics.getStroke();
		graphics.setStroke(new BasicStroke(4));
		graphics.drawRect(0, 0, MAX_WIDTH, MAX_HEIGHT);
		graphics.setStroke(orig);
		pieces[piecesItr].draw(g);
		updateBoardDrawing(g);
	}

	public void updateBoardDrawing(Graphics g) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if (gameField[i][j] >= 1) {
					switch (gameField[i][j]) {
					case 1:
						g.setColor(Color.YELLOW);
						break;
					case 2:
						g.setColor(Color.GREEN);
						break;
					case 3:
						g.setColor(Color.ORANGE);
						break;
					case 4:
						g.setColor(Color.BLUE);
						break;
					case 5:
						g.setColor(Color.WHITE);
						break;
					case 6:
						g.setColor(Color.GRAY);
						break;
					case 7:
						g.setColor(Color.CYAN);
						break;
					}
					Graphics2D graphics = (Graphics2D) g;
					graphics.fillRect(i * Square.square_Length, j
							* Square.square_Length, Square.square_Length,
							Square.square_Length);
					graphics.setColor(Color.BLACK);
					Stroke s = graphics.getStroke();
					graphics.setStroke(new BasicStroke(2));
					graphics.drawRect(i * Square.square_Length, j
							* Square.square_Length, Square.square_Length,
							Square.square_Length);
					graphics.setStroke(s);

				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(MAX_WIDTH, MAX_HEIGHT);
	}

	public void drawGrid(Graphics g) {
		g.setColor(Color.BLUE);
		for (int i = 0; i < 400; i += 20) {
			g.drawLine(0, i, 400, i);
			g.drawLine(i, 0, i, 400);
		}

	}

	public void detectFullRows() {
		int countBoxesInRow = 0;
		for (int y = 0; y < 20; y++) {
			countBoxesInRow = 0;
			for (int x = 0; x < 10; x++) {
				if (gameField[x][y] >= 1) {
					countBoxesInRow += 1;
				}
				if (countBoxesInRow == 10) {
					deletFullRow(y);
					score += 10;
				}
			}
		}
	}

	public void deletFullRow(int rowNum) {
		rows += 1;
		for (int x = 0; x < 10; x++) {
			gameField[x][rowNum] = 0;
		}
		for (int y = rowNum - 1; y > 0; y--) {
			for (int x = 0; x < 10; x++) {
				gameField[x][y + 1] = gameField[x][y];
			}
		}
	}

	public boolean checkIfLost() {
		if (pieces[piecesItr].canDrop()) {
			return false;
		}
		return true;
	}

	public GameObjects[] createNewGameObjectsArray(int length) {
		int lastInt = 0;
		GameObjects[] go = new GameObjects[length];
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				int a = (int) (Math.random() * 7);
				if (a == lastInt && a < 7) {
					a += 1;
				} else if (a == lastInt) {
					a = 0;
				}
				if (a == 1) {
					go[i] = new Lr(80, 0);
				} else if (a == 2) {
					go[i] = new Ll(80, 0);
				} else if (a == 3) {
					go[i] = new Line(80, 0);
				} else if (a == 4) {
					go[i] = new S(80, 0);
				} else if (a == 5) {
					go[i] = new Z(80, 0);
				} else if (a == 6) {
					go[i] = new Box(80, 0);
				} else {
					go[i] = new T(80, 0);
				}
				lastInt = a;
			}
		}
		return go;
	}

	public static boolean getLost() {
		return lost;
	}

	public static void setIterator(int x) {
		piecesItr = x;
	}

	public boolean isTimerOn() {
		return (timer.isRunning());
	}

	public static void setTimerOn() {
		timer.start();
	}

	public static void setTimerOff() {
		timer.stop();
	}
	
	public void setLost(boolean a){
		lost = a;
	}

}
