import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main extends JFrame {

	private JPanel contentPane;
	public static Bluetooth bluetooth;
	
	private JLabel lblSearching = new JLabel("");
	private final JLabel lblController = new JLabel("");
	private final JLabel lblTwitch = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main2 frame = new main2();
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
	public main() {
		setTitle("SMLR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bluetooth = new Bluetooth();
		
		lblSearching.setIcon(new ImageIcon(main.class.getResource("/resource/searching.png")));
		lblSearching.setBounds(249, 146, 584, 373);
		contentPane.add(lblSearching);
		
		JLabel lblConnecting = new JLabel("");
		lblConnecting.setVisible(false);
		lblConnecting.setBounds(191, 96, 673, 528);
		lblConnecting.setIcon(new ImageIcon(main.class.getResource("/resource/connecting.png")));
		contentPane.add(lblConnecting);
		
		lblController.setBounds(285, 74, 476, 225);
		lblController.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblController.setIcon(new ImageIcon(main.class.getResource("/resource/controller2.png")));
				lblController.setBounds(260, 29, 521, 319);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblController.setIcon(new ImageIcon(main.class.getResource("/resource/controller.png")));
				lblController.setBounds(285, 74, 476, 225);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				control control = new control();
				control.setVisible(true);
			}
		});
		lblController.setIcon(new ImageIcon(main.class.getResource("/resource/controller.png")));
		contentPane.add(lblController);
		
		
		lblTwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTwitch.setIcon(new ImageIcon(main.class.getResource("/resource/twitch2.png")));
				lblTwitch.setBounds(285, 378, 459, 300);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblTwitch.setBounds(300, 396, 425, 268);
				lblTwitch.setIcon(new ImageIcon(main.class.getResource("/resource/twitch.png")));
			}
		});
		lblTwitch.setBounds(300, 396, 425, 268);
		lblTwitch.setIcon(new ImageIcon(main.class.getResource("/resource/twitch.png")));
		contentPane.add(lblTwitch);
		

		
		
		/**
		try {
			bluetooth.findCar();
			lblSearching.setVisible(false);
		} catch (BluetoothStateException | InterruptedException e) {
			e.printStackTrace();
		}
		if(bluetooth.getCarFound()){
			try {
				lblConnecting.setVisible(true);
				bluetooth.connect();
				lblConnecting.setVisible(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}
}
