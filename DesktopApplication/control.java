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

public class control extends JFrame{

	private JPanel contentPane;
	
	private boolean carForward = false;
	private boolean carLeft = false;
	private boolean carRight = false;
	private boolean carBack = false;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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
			
		} else {

		}
	}

}
