import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class control extends JFrame{

	private JPanel contentPane;
	
	private boolean carForward = false;
	private boolean carLeft = false;
	private boolean carRight = false;
	private boolean carBack = false;
	private boolean prevention = true;
	private boolean Fkey = false;
	
	private int speed = 40;
	private JLabel lblSpeed = new JLabel("Speed: " + speed);
	private JLabel lblPrevention = new JLabel("Prevention: " + prevention);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					control frame = new control();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public control() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 400, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSpeed.setBounds(15, 54, 69, 20);
		contentPane.add(lblSpeed);
		

		lblPrevention.setBounds(15, 89, 102, 20);
		contentPane.add(lblPrevention);
	}
	public void processKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if(!carForward) System.out.println("Car is driving forward");
				carForward = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				if(!carLeft) System.out.println("Car is turning left");
				carLeft = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(!carRight) System.out.println("Car is turning right");
				carRight = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				if(!carBack) System.out.println("Car is going in reverse");
				carBack = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_Z){
				if(speed > 20) speed -= 20;
				lblSpeed.setText("Speed: " + speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_X){
				if (speed < 100) speed += 20;
				lblSpeed.setText("Speed: " + speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_F){
				if(!Fkey) {
					if(prevention) prevention = false;
					else prevention = true;
					lblPrevention.setText("Prevention: " + prevention);
				}
				Fkey = true;
			}

		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("Car stopped driving forward");
				carForward = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("Car stopped turning left");
				carLeft = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("Car stopped turning right");
				carRight = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("Car stopped going in reverse");
				carRight = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_F){
				Fkey = false;
			}
			
		} else {
		}
	}
}
