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
		
		while (true) {
			Thread.sleep(3);
			pingPongPanel.moveBall();
			pingPongPanel.moveStick();
			pingPongPanel.repaint();
		}
	}
	
	private static class PingPongListener implements KeyListener {
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP) { // движение на стик 1 
				pingPongPanel.setStickMovement(-1);
				
			} else if(e.getKeyCode() == KeyEvent.VK_DOWN) { // движение на стик 2
				pingPongPanel.setStickMovement(1);	
				
			} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Стартиране на играта
				
				/*
				 * избора на начална посока ще е добре да става с random(); 
				 * възможните посоки са комбинация от следните параметри :
				 * 1.  pingPongPanel.setStepX(-1); 
				 * 2.  pingPongPanel.setStepX(1);
				 * 3.  pingPongPanel.setStepY(-1);
				 * 4.  pingPongPanel.setStepY(1);
				 * ** 0 - (нула все още не включваме като параметър ( освен при пауза ), защото нашият стик 
				 * първоначално няма да има възможността да отклонява топчето при успоредно на Х движение. 
				 */
				
				pingPongPanel.setStepX(1); // зададеното поведение : x = 1 , y = 1 
				pingPongPanel.setStepY(1); // ще стартира движението на допчето към долния десен ъгъл

			}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				//pause
				pingPongPanel.setStepX(0);
				pingPongPanel.setStepY(0);
				
			} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				frame.dispose();  // exit frame
			}
		}
	}
}
