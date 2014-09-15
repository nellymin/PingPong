import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class PingPong {
	private static PingPongPanel pingPongPanel;
	private static JFrame frame;

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
		}
	}

	private static class PingPongListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				pingPongPanel.setstickStep(-1);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				pingPongPanel.setstickStep(1);
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				pingPongPanel.setStickStepTwo(1);
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				pingPongPanel.setStickStepTwo(-1);
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				//pingPongPanel.randomBallMovement();
				pingPongPanel.setStepX(1); 
				pingPongPanel.setStepY(1);
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				// pause
				pingPongPanel.setStepX(0);
				pingPongPanel.setStepY(0);
				//спиране на движението на стиковете
				//подноваване на играта с предходната посока на движение на топчето
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				frame.dispose(); // exit frame
			}
		}
	}
}
