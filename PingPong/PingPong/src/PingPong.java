import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class PingPong {
	private static PingPongPanel pingPongPanel;
	private static JFrame frame;
	private static boolean isRunning = false;

	public static void main(String[] args) throws InterruptedException {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pingPongPanel = new PingPongPanel();
		frame.add(pingPongPanel);
		frame.setVisible(true);
		frame.addKeyListener(new PingPongListener());
		
		@SuppressWarnings("unused")
		MainMenu menu = new MainMenu();

		while (true) {
			Thread.sleep(3);
			pingPongPanel.moveBall();
			pingPongPanel.moveStick();
			pingPongPanel.repaint();
			if (MainMenu.singlePlayer)
				pingPongPanel.moveAIPaddle();
		}
	}

	private static class PingPongListener implements KeyListener {
		boolean isPaused = false;	// Dani
		
		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_S || 
					e.getKeyCode() == KeyEvent.VK_W) {
				pingPongPanel.setStickStep(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP ||
					e.getKeyCode() == KeyEvent.VK_DOWN) {
				pingPongPanel.setStickStepTwo(0);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (!isPaused && isRunning && e.getKeyCode() == KeyEvent.VK_UP) {
				pingPongPanel.setStickStepTwo(-1);
			} else if (!isPaused && isRunning && e.getKeyCode() == KeyEvent.VK_DOWN) {
				pingPongPanel.setStickStepTwo(1);
			} else if (!isPaused && isRunning && e.getKeyCode() == KeyEvent.VK_S) {
				pingPongPanel.setStickStep(1);
			} else if (!isPaused && isRunning && e.getKeyCode() == KeyEvent.VK_W) {
				pingPongPanel.setStickStep(-1);
			} else if (!isRunning && !isPaused && e.getKeyCode() == KeyEvent.VK_ENTER) {
				isRunning = true;
				pingPongPanel.centeringStickAndBall();
			} else if (isRunning && e.getKeyCode() == KeyEvent.VK_SPACE) {
				// pause Dani
				pingPongPanel.setStickStep(0);
				pingPongPanel.setStickStepTwo(0);
				pingPongPanel.setPaused(!pingPongPanel.getPaused());
				isPaused = !isPaused;
			}
			else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				frame.dispose(); // exit frame
			}
		}
	}
	
	public static boolean getRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		PingPong.isRunning = isRunning;
	}
}
