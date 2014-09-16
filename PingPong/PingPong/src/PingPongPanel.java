import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PingPongPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int BALL_SIZE = 25;
	private static final int STICK_SIZE = 100;
	private static final int STEPS = 7;
	private int ballX = 380; // Lyudmil
	private int ballY = 250; // Lyudmil
	private int ballStepX = 0;
	private int ballStepY = 0;
	private int stickOneY = 200; // Lyudmil
	private int stickTwoY = 200; // Lyudmil
	private int stickOneStep = 0;
	private int stickTwoStep = 0;
	private int firstPlayerResult = 0; // Lyudmil
	private int secondPlayerResult = 0; // Lyudmil
	private boolean isPaused = false; // Dani

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
		g.fillRect(1, stickOneY, 5, STICK_SIZE);
		g.fillRect((int) getSize().getWidth() - 6, stickTwoY, 5, STICK_SIZE);
		g.drawString("Player 1: " + firstPlayerResult, 5, 10); // Lyudmil
		g.drawString("Player 2: " + secondPlayerResult, getWidth() - 60, 10); // Lyudmil

		if (isPaused) {
			Font f = new Font("Dialog", Font.ITALIC, 20); // Dani
			g.setFont(f);
			g.drawString("PAUSED", getWidth() / 2 - 50, getHeight() / 2);
		}
	}

	public void moveBall() {
		if (!isPaused) {
			if (ballX + BALL_SIZE > getSize().getWidth() - 5) {
				if (ballY + BALL_SIZE / 2 < stickTwoY || ballY + BALL_SIZE / 2 > stickTwoY + STICK_SIZE) {
					firstPlayerResult++;
					win(1);
				} else {
					SoundEffect.PAD_BOUNCE.play();
					setBallStepXY();
				}
				// мини задача: за по- добър визуален ефект топчето да излиза
				// извън
				// очертанието на полето.
			}

			if (ballX < 5) {
				if (ballY + BALL_SIZE / 2 < stickOneY || ballY + BALL_SIZE / 2 > stickOneY + STICK_SIZE) {
					secondPlayerResult++;
					win(2);
				} else {
					SoundEffect.PAD_BOUNCE.play();
					setBallStepXY();
				}
			}

			if (ballY + BALL_SIZE > getSize().getHeight() || ballY < 0) {
				ballStepY *= -1;
				SoundEffect.WALL_BOUNCE.play();
			}

			ballX += this.ballStepX;
			ballY += this.ballStepY;
		}
	}

	public void moveStick() {
		if (stickOneY < 1) {
			stickOneY = 1;
			this.stickOneStep = 0;
		} 
		if (stickOneY + STICK_SIZE >= getHeight()) {
			stickOneY = getHeight() - (STICK_SIZE + 1);
			this.stickOneStep = 0;
		}
		this.stickOneY += this.stickOneStep;
		if (stickTwoY < 1) {
			stickTwoY = 1;
			this.stickTwoStep = 0;
		} 
		if (stickTwoY + STICK_SIZE >= getHeight()) {
			stickTwoY = getHeight() - (STICK_SIZE + 1);
			this.stickTwoStep = 0;
		}
		this.stickTwoY += this.stickTwoStep;
	}

	public void setStepX(int stepX) {
		this.ballStepX = stepX;
	}

	public void setStepY(int stepY) {
		this.ballStepY = stepY;
	}

	public void setStickStep(int step) {
		this.stickOneStep = step;
	}

	public void setStickStepTwo(int step) {
		this.stickTwoStep = step;
	}

	public boolean getPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void randomBallMovement() { // метод, който ще стартира топчето оfт
										// центъра с random посока
		int[] side = {1, -1};
		Random rand = new Random();
		setStepX(side[rand.nextInt(2)]);
		setStepY(side[rand.nextInt(2)]);
	}

	private void win(int player) { // Niki 15
		SoundEffect.SCORE.play();
		PingPong.setRunning(!PingPong.getRunning()); // Dani
		centeringStickAndBall(); // Dani
		setStepX(0);
		setStepY(0);
		String message = "Player " + player + " WIN!  \nplayer 1  | "
				+ firstPlayerResult + " - " + secondPlayerResult
				+ " |  player 2";
		JOptionPane.showMessageDialog(null, message, "PiNg PoNg reSult",
				JOptionPane.WARNING_MESSAGE);
	}

	private void setBallStepXY() {
		int[] stickSectionCoord = new int[STEPS];
		int[] ySteps = new int[STEPS];
		for (int i = 0; i < STEPS; i++) {
			ySteps[i] = i <= STEPS / 2 ? (STEPS / 2 - i) * -1 : i - STEPS / 2;
		}
		int sectionSize = STICK_SIZE / stickSectionCoord.length;

		if (ballStepX == 1) {
			// stickTwoY
			for (int i = 0; i < STEPS; i++) {
				// initialize array with the coordinates of stick sectors
				stickSectionCoord[i] = i * sectionSize + stickTwoY;
			}
		} else {
			// initialize array with the coordinates of stick sectors
			for (int i = 0; i < STEPS; i++) {
				stickSectionCoord[i] = i * sectionSize + stickOneY;
			}
		}
		for (int i = 0; i < STEPS - 1; i++) {
			if (ballY + (BALL_SIZE / 2) > stickSectionCoord[i]
					&& ballY + (BALL_SIZE / 2) < stickSectionCoord[i + 1]) {
				ballStepY = ySteps[i];
				ballStepX *= -1;
				break;
			}
		}
	}

	private int AISpeed = 0;
	public void moveAIPaddle() {
		AISpeed++;
		if (AISpeed > 40) {
			if (ballY < stickTwoY) {
				stickTwoStep = -2;
			} else if (ballY > stickTwoY + STICK_SIZE / 2) {
				stickTwoStep = 2;
			} else if (ballY == stickTwoY + STICK_SIZE / 2) {
				stickTwoStep = 0;
			}
		}
	}

	void centeringStickAndBall() { // Dani
		ballX = 380;
		ballY = 250;
		randomBallMovement();
		setStickStep(0);
		setStickStepTwo(0);
		this.stickOneY = getHeight() / 2 - 80; // setting the sticks to start
												// from the center
		this.stickTwoY = getHeight() / 2 - 80;
	}
}
