import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frmAmbikeyboard;
	private JLabel starStop;
	private JButton btnStart;
	static boolean running = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmAmbikeyboard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAmbikeyboard = new JFrame();
		frmAmbikeyboard.setResizable(false);
		frmAmbikeyboard.setTitle("AmbiKeyboard");
		frmAmbikeyboard.setBounds(100, 100, 263, 100);
		frmAmbikeyboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmAmbikeyboard.getContentPane().setLayout(springLayout);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread LED = new Thread(new AmbiLed());
				if (running) {
					running = false;
					GUI.this.starStop.setText("stopped");
					GUI.this.starStop.setForeground(new Color(255, 0, 0));
					GUI.this.btnStart.setText("Start");

				} else {
					running = true;
					GUI.this.starStop.setText("running");
					GUI.this.starStop.setForeground(new Color(0, 255, 0));
					GUI.this.btnStart.setText("Stop");
					LED.start();

				}

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnStart, 10, SpringLayout.NORTH,
				frmAmbikeyboard.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnStart, 10, SpringLayout.WEST,
				frmAmbikeyboard.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnStart, 237, SpringLayout.WEST,
				frmAmbikeyboard.getContentPane());
		frmAmbikeyboard.getContentPane().add(btnStart);

		starStop = new JLabel("stopped");
		starStop.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, starStop, 6, SpringLayout.SOUTH, btnStart);
		springLayout.putConstraint(SpringLayout.WEST, starStop, 0, SpringLayout.WEST, btnStart);
		springLayout.putConstraint(SpringLayout.EAST, starStop, 0, SpringLayout.EAST, btnStart);
		starStop.setHorizontalAlignment(SwingConstants.CENTER);
		frmAmbikeyboard.getContentPane().add(starStop);
	}
}
