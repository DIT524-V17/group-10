import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class control extends JFrame{

	private JPanel contentPane;
	
	private boolean keyPress = false;
	private boolean carForward = false;
	private boolean carLeft = false;
	private boolean carRight = false;
	private boolean carBack = false;
	private boolean prevention = true;
	private boolean Fkey = false;
	
	private String[] speeds = new String[3];
	private int speed = 0;
	private JLabel lblSpeed = new JLabel();
	private JLabel lblPrevention = new JLabel("Prevention: " + prevention);
	private JLabel left = new JLabel("");
	private JLabel right = new JLabel("");
	private JLabel forward = new JLabel("");
	private final JLabel back = new JLabel("");
	private final JPanel panel = new JPanel();
	private final JLabel lblVideoFeed = new JLabel("Video feed");


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
		speeds[0] = "Low";
		speeds[1] = "Medium";
		speeds[2] = "High";
		lblSpeed.setText("Speed:  " + speeds[speed]);
		
		setTitle("Controller");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 400, 940, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSpeed.setBounds(15, 54, 69, 20);
		contentPane.add(lblSpeed);
		

		lblPrevention.setBounds(15, 89, 102, 20);
		contentPane.add(lblPrevention);
		

		left.setBounds(15, 203, 50, 50);
		left.setIcon(new ImageIcon(main.class.getResource("/resource/left0.png")));
		contentPane.add(left);
		

		right.setBounds(75, 203, 50, 50);
		right.setIcon(new ImageIcon(main.class.getResource("/resource/right0.png")));
		contentPane.add(right);
		
		forward.setBounds(44, 142, 50, 50);
		forward.setIcon(new ImageIcon(main.class.getResource("/resource/forward0.png")));
		contentPane.add(forward);
		
		back.setBounds(44, 264, 50, 50);
		back.setIcon(new ImageIcon(main.class.getResource("/resource/back0.png")));
		contentPane.add(back);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(165, 54, 749, 497);
		
		contentPane.add(panel);
		panel.setLayout(null);
		lblVideoFeed.setFont(new Font("Tahoma", Font.PLAIN, 43));
		lblVideoFeed.setBounds(281, 185, 348, 101);
		
		panel.add(lblVideoFeed);
	}
	public void processKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			
			if (e.getKeyCode() == KeyEvent.VK_UP && !keyPress) {
				//main.bluetooth.btnPress("");
				System.out.println("Car is driving forward");
				forward.setIcon(new ImageIcon(main.class.getResource("/resource/forward1.png")));
				carForward = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT && !keyPress){
				System.out.println("Car is turning left");
				left.setIcon(new ImageIcon(main.class.getResource("/resource/left1.png")));
				carLeft = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT && !keyPress){
				System.out.println("Car is turning right");
				right.setIcon(new ImageIcon(main.class.getResource("/resource/right1.png")));
				carRight = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN && !keyPress){
				System.out.println("Car is going in reverse");
				back.setIcon(new ImageIcon(main.class.getResource("/resource/back1.png")));
				carBack = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_Z && !keyPress){
				if(speed > 0) speed -= 1;
				lblSpeed.setText("Speed: " + speeds[speed]);
			}
			if (e.getKeyCode() == KeyEvent.VK_X && !keyPress){
				if (speed < 2) speed += 1;
				lblSpeed.setText("Speed: " + speeds[speed]);
			}
			if (e.getKeyCode() == KeyEvent.VK_F && !keyPress){
				if(!Fkey) {
					if(prevention) prevention = false;
					else prevention = true;
					lblPrevention.setText("Prevention: " + prevention);
				}
				Fkey = true;
			}

		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
			if (e.getKeyCode() == KeyEvent.VK_UP && carForward == true) {
				System.out.println("Car stopped driving forward");
				forward.setIcon(new ImageIcon(main.class.getResource("/resource/forward0.png")));
				carForward = false;
				keyPress = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && carLeft == true) {
				System.out.println("Car stopped turning left");
				left.setIcon(new ImageIcon(main.class.getResource("/resource/left0.png")));
				carLeft = false;
				keyPress = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && carRight == true) {
				System.out.println("Car stopped turning right");
				right.setIcon(new ImageIcon(main.class.getResource("/resource/right0.png")));
				carRight = false;
				keyPress = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && carBack == true) {
				System.out.println("Car stopped going in reverse");
				back.setIcon(new ImageIcon(main.class.getResource("/resource/back0.png")));
				carBack = false;
				keyPress = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_F){
				Fkey = false;
				keyPress = false;
			}
			
		} else {
		}
	}
}
