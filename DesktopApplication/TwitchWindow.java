import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TwitchWindow extends JFrame {

	private JPanel contentPane;
	
	static JLabel forward;
	static JLabel left;
	static JLabel right;
	static JLabel backward;
	static JLabel nothing;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TwitchWindow frame = new TwitchWindow();
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
	public TwitchWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		forward = new JLabel("");
		forward.setVisible(false);
		forward.setIcon(new ImageIcon(TwitchWindow.class.getResource("/resource/forward1.png")));
		forward.setBounds(440, 328, 70, 91);
		contentPane.add(forward);
		
		left = new JLabel("");
		left.setVisible(false);
		left.setIcon(new ImageIcon(TwitchWindow.class.getResource("/resource/left1.png")));
		left.setBounds(251, 285, 147, 91);
		contentPane.add(left);
		
		right = new JLabel("");
		right.setVisible(false);
		right.setIcon(new ImageIcon(TwitchWindow.class.getResource("/resource/right1.png")));
		right.setBounds(260, 409, 159, 108);
		contentPane.add(right);
		
		backward = new JLabel("");
		backward.setVisible(false);
		backward.setIcon(new ImageIcon(TwitchWindow.class.getResource("/resource/back1.png")));
		backward.setBounds(373, 192, 116, 103);
		contentPane.add(backward);
		
		nothing = new JLabel("");
		nothing.setVisible(false);
		nothing.setIcon(new ImageIcon(TwitchWindow.class.getResource("/resource/tryAgain.png")));
		nothing.setBounds(405, 554, 614, 392);
		contentPane.add(nothing);
		
		TwitchCommandSend test = new TwitchCommandSend();
		String twitch = "C:/Users/Ludvig/AppData/Roaming/mIRC/logs/#riffslayerboy.Twitch.tv.log";
		String testis = "C:/Users/Ludvig/Desktop/t/twitch.txt";
		test.sendCommands(testis);
	}
	static void commandStart(DriveLog log){
		if(log.getCmdtype().equals("@forward")){
			forward.setVisible(true);
		} else if (log.getCmdtype().equals("@left")){
			left.setVisible(true);
		} else if(log.getCmdtype().equals("@right")){
			right.setVisible(true);
		} else if(log.getCmdtype().equals("@backward")){
			backward.setVisible(true);
		} else {
			nothing.setVisible(true);
		}
	}
	static void commandEnd(JLabel lbl){
		forward.setVisible(false);
		left.setVisible(false);
		right.setVisible(false);
		backward.setVisible(false);
		nothing.setVisible(false);
	}
}
