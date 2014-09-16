import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PingPongPanel extends JPanel {

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
	private int stickStepTwo = 0;
	private int firstPlayerResult = 0; // Lyudmil
	private int secondPlayerResult = 0; // Lyudmil
	private boolean isPaused = false; // Dani

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(ballStartX, ballStartY, BALL_SIZE, BALL_SIZE);
		g.fillRect(1, stickOneStart, 5, STICK_SIZE);
		g.fillRect((int) getSize().getWidth() - 6, stickTwoStart, 5, STICK_SIZE);
		g.drawString("Player 1: " + firstPlayerResult, 5, 10); // Lyudmil
		g.drawString("Player 2: " + secondPlayerResult, getWidth() - 60, 10); // Lyudmil
		showingMessageOnPause(g);
	}

	private void showingMessageOnPause(Graphics g) {
		if (isPaused) {								
			Font f = new Font("Dialog", Font.ITALIC, 20);					//	Dani
			g.setFont(f);
			g.drawString("PAUSED", getWidth() / 2 - 50, getHeight() / 2);
		}
	}

	public void moveBall() {
		if(!isPaused){
		ballStartX += this.ballStepX;
		ballStartY += this.ballStepY;
		
		if (ballStartX + BALL_SIZE > getSize().getWidth()) {
			ballStepX = -1;
			if (ballStartY < stickTwoStart
					|| ballStartY > stickTwoStart + STICK_SIZE) {
				SoundEffect.SCORE.play();
				firstPlayerResult++;
				win(1);
				// Nelly 
				centeringStickAndBall(); // Dani
				// Nelly
			}
			else
				SoundEffect.PAD_BOUNCE.play();
			// мини задача: за по- добър визуален ефект топчето да излиза извън
			// очертанието на полето.
		}

		if (ballStartX < 0) {
			ballStepX = 1;
			if (ballStartY < stickOneStart
					|| ballStartY > stickOneStart + STICK_SIZE) {
				SoundEffect.SCORE.play();
				secondPlayerResult++;
				win(2);
				// Nelly
				ballStartX = 380;
				ballStartY = 250;
				ballStepX=0;
				ballStepY=0;
				//Nelly
			}
			else
				SoundEffect.PAD_BOUNCE.play();
		}

		if (ballStartY + BALL_SIZE > getSize().getHeight()) {
			ballStepY = -1;
			SoundEffect.WALL_BOUNCE.play();
		}

		if (ballStartY < 0) {
			ballStepY = 1;
			SoundEffect.WALL_BOUNCE.play();
		}
		}
	}

	public void moveStick() {
		this.stickOneStart += this.stickStep;
		if (stickOneStart <= 1
				|| stickOneStart >= getSize().getHeight() - STICK_SIZE) { // Niki
			// 14
			this.stickStep = 0; // Niki 14
		}
		this.stickTwoStart += this.stickStepTwo;
		if (stickTwoStart <= 1
				|| stickTwoStart >= getSize().getHeight() - STICK_SIZE) {
			this.stickStepTwo = 0;
		}
	}

	public void setStepX(int stepX) {
		this.ballStepX = stepX;
	}

	public void setStepY(int stepY) {
		this.ballStepY = stepY;
	}

	public void setStickStep(int step) {
		this.stickStep = step;
	}

	public void setStickStepTwo(int step) {
		this.stickStepTwo = step;
	}
	
	public boolean getPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void randomBallMovement() { // метод, който ще стартира топчето от
										// центъра с random посока
	}
	
	private void win(int player) { // Niki 15
		PingPong.setRunning(!PingPong.getRunning()); // Dani   
		centeringStickAndBall();  // Dani
		String message = "Player " + player + " WIN!  \nplayer 1  | "
				+ firstPlayerResult + " - " + secondPlayerResult
				+ " |  player 2";
		JOptionPane.showMessageDialog(null, message, "PiNg PoNg reSult",
				JOptionPane.WARNING_MESSAGE);
		// randomBallMovement();
	}

	private void centeringStickAndBall() { // Dani
		ballStartX = 380;
		ballStartY = 250;
		ballStepX=0;
		ballStepY=0;
		setStickStep(0);
		setStickStepTwo(0);
		this.stickOneStart = 200;  // setting the sticks to start from the center
		this.stickTwoStart = 200;  
	}
}
