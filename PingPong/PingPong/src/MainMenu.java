import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public final class MainMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static boolean isSinglePlayer = false;
	private static boolean isStarted = false;
	int screenWidth = 500;
	int screenHeight = 200;
	int buttonWidth = 90;
	int buttonHeight = 40;

	JButton Play, Multiplay, Quit;

	public MainMenu() {
		addButtons();
		addActions();
		getContentPane().setLayout(null);
		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth,
				buttonHeight);
		Multiplay.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth,
				buttonHeight);
		Quit.setBounds((screenWidth - buttonWidth) / 2, 95, buttonWidth,
				buttonHeight);

		// Adding buttons
		getContentPane().add(Play); // Add the button to the JFrame
		getContentPane().add(Multiplay); // Add the button to the JFrame
		getContentPane().add(Quit); // Add the button to the JFrame

		// Creating the JFrame
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Ping Pong Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void addButtons() {
		Play = new JButton("Play");
		Multiplay = new JButton("Multiplay");
		Quit = new JButton("Quit");

	}

	private void addActions() {

		Play.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				isSinglePlayer = true;
				isStarted = true;
				new PingPong();
				dispose();
			}
		});

		Multiplay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isSinglePlayer = false;
				isStarted = true;
				new PingPong();
				dispose();
			}
		});

		Quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	
}
