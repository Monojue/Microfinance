package entryForm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	private JPanel contentPane;
	 panel panel = new panel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Frame");
        setLayout(new GridBagLayout());

        add(panel);
        initListeners();
	}

    public void initListeners() {

        /** When the window is resized, the panel size is updated. */
        addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {        
                panel.updatePanelSize();
            }

            @Override
            public void componentHidden(ComponentEvent evt) {}

            @Override
            public void componentShown(ComponentEvent evt) {}

            @Override
            public void componentMoved(ComponentEvent evt) {}
        });
    }
}
