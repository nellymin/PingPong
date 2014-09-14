import java.awt.Graphics;
import javax.swing.JPanel;

public class PingPongPanel extends JPanel {
	private static final int BALL_SIZE = 25;
	private static final int STICK_SIZE = 100;
	private int ballStartX = 100;  
	private int ballStartY = 100; 
	private int ballStepX = 0;
	private int ballStepY = 0;
	private int stickStart = 100;
	private int stickTwoStart = 100;
	private int stickStep = 0;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(ballStartX, ballStartY, BALL_SIZE, BALL_SIZE);
		g.fillRect(1, stickStart, 5, STICK_SIZE);
		g.fillRect((int)getSize().getWidth()-6, stickTwoStart, 5, STICK_SIZE);
	}
			
	public void moveBall() {
		ballStartX += this.ballStepX;
		ballStartY += this.ballStepY;
		if(ballStartX + BALL_SIZE > getSize().getWidth()) {
			ballStepX = -1;
		}
		
		if(ballStartX < 0) {
			ballStepX = 1;
		}
		
		if(ballStartY + BALL_SIZE > getSize().getHeight()) {
			ballStepY = -1;
		}
		
		if(ballStartY < 0) {
			ballStepY = 1;
		}
	}
	
	public void moveStick() {

		this.stickStart += this.stickStep;
		
	}
	
	public void setStepX(int stepX) {
		this.ballStepX = stepX;
	}
	
	public void setStepY(int stepY) {
		this.ballStepY = stepY;
	}
	
	public void setStickMovement(int step){
		this.stickStep = step;
	}
}
