/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;

import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.RenderCallback;
import uk.co.caprica.vlcj.player.direct.RenderCallbackAdapter;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;
/**
 *
 *
 */
public class JSTPlay {

    /**
     * @param args the command line arguments
     */
    private static final String NATIVE_LIBRARY_SEARCH_PATH = "C:\\Users\\Gautam\\Documents\\vlc";
    private final JFrame frame;
    private final DirectMediaPlayerComponent mediaPlayerComponent;
    private final JButton connectButton;
    private final JButton stopButton;
    private JTextField urlField;
    private final JPanel mediaPanel;
    private final BufferedImage img;

    private static final int width = 640;

    private static final int height = 360;



    public static void main(String[] args) {
        // TODO code application logic here
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);
        System.out.println(LibVlc.INSTANCE.libvlc_get_version());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JSTPlay();
            }
        });
    }
    public JSTPlay() {
        frame = new JFrame("jStreamPlayer");
        frame.setBounds(50, 100, 850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });


        JPanel basePanel = new JPanel();
        mediaPanel = new mediaSurface();
        mediaPanel.setLayout(new BorderLayout());
        basePanel.add(mediaPanel,BorderLayout.NORTH);


        img = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(width, height);
        BufferFormatCallback bufferFormatCallback = new BufferFormatCallback() {
            @Override
            public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
                return new RV32BufferFormat(width, height);
            }
        };

        mediaPlayerComponent = new DirectMediaPlayerComponent(bufferFormatCallback)  {
            @Override
            protected RenderCallback onGetRenderCallback() {
                return new jPlayRenderCallback();
            }
        };

        JPanel connectPanel = new JPanel();
        urlField = new JTextField("http://192.168.0.1:8000/picam.webm",50);

        connectPanel.add(urlField,BorderLayout.WEST);
        connectButton = new JButton("Connect");
        connectPanel.add(connectButton);
        stopButton = new JButton("Stop");
        connectPanel.add(stopButton);
        basePanel.add(connectPanel,BorderLayout.SOUTH);

        connectButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.getMediaPlayer().playMedia(urlField.getText());
            }
        });

        stopButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.getMediaPlayer().stop();
            }
        });
        frame.setContentPane(basePanel);
        frame.setVisible(true);
        //   mediaPlayerComponent.getMediaPlayer().playMedia("http://192.168.0.1:8000/picam.webm");
    }


    private class mediaSurface extends JPanel {

        private mediaSurface() {
            setBackground(Color.black);
            setOpaque(true);
            setPreferredSize(new Dimension(width, height));
            setMinimumSize(new Dimension(width, height));
            setMaximumSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(img, null, 0, 0);
        }
    }
    private class jPlayRenderCallback extends RenderCallbackAdapter {

        private jPlayRenderCallback() {
            super(new int[width * height]);
        }

        @Override
        protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {
            // Simply copy buffer to the image and repaint
            img.setRGB(0, 0, width, height, rgbBuffer, 0, width);
            mediaPanel.repaint();
        }
    }

}
