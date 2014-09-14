import java.awt.Graphics;

import javax.swing.JPanel;

public class PingPongPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BALL_SIZE = 25;
	private static final int STICK_SIZE = 100;
	private int ballStartX = 380; // Lyudmil
	private int ballStartY = 250; // Lyudmil
	private int ballStepX = 0;
	private int ballStepY = 0;
	private int stickOneStart = 200; // Lyudmil
	private int stickTwoStart = 200; // Lyudmil
	private int stickStep = 0;
	private int firstPlayerResult = 0; // Lyudmil
	private int secondPlayerResult = 0; // Lyudmil

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(ballStartX, ballStartY, BALL_SIZE, BALL_SIZE);
		g.fillRect(1, stickOneStart, 5, STICK_SIZE);
		g.fillRect((int) getSize().getWidth() - 6, stickTwoStart, 5, STICK_SIZE);
		g.drawString("Player 1: " + firstPlayerResult, 5, 10); // Lyudmil
		g.drawString("Player 2: " + secondPlayerResult, getWidth() - 60, 10); // Lyudmil
	}

	public void moveBall() {
		ballStartX += this.ballStepX;
		ballStartY += this.ballStepY;
		if (ballStartX + BALL_SIZE > getSize().getWidth()) {
			ballStepX = -1;
		}

		if (ballStartX < 0) {
			ballStepX = 1;
		}

		if (ballStartY + BALL_SIZE > getSize().getHeight()) {
			ballStepY = -1;
		}

		if (ballStartY < 0) {
			ballStepY = 1;
		}
	}

	public void moveStick() {

		this.stickOneStart += this.stickStep;

	}

	public void setStepX(int stepX) {
		this.ballStepX = stepX;
	}

	public void setStepY(int stepY) {
		this.ballStepY = stepY;
	}

	public void setStickMovement(int step) {
		this.stickStep = step;
		if (stickOneStart > getSize().getHeight()) { // Lyudmil
			stickOneStart = (int) getSize().getHeight() - 100; // Lyudmil
			stickStep = 0; // Lyudmil
		}
		if (stickOneStart < 0) { // Lyudmil
			stickOneStart = 0; // Lyudmil
			stickStep = 0; // Lyudmil
		}
	}
}
