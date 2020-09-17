package main;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entryForm.ClientEntry;
import entryForm.GroupEntry;
import entryForm.GroupRequestForm;
import entryForm.LoanRequestForm;
import tool.MyString;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JTextField;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private ClientPanel clientPanel;
	private GroupPanel groupPanel;
	private LoanPanel loanPanel;
	private PaymentPanel paymentPanel;
	private ReportPanel reportPanel;
	private SettingPanel settingPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("MICROFINANACE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, MyString.frameWidth, MyString.frameHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, MyString.panelWidth, MyString.frameHeight-50);
		tabbedPane.setMinimumSize(new Dimension(802,708));
		contentPane.add(tabbedPane);
		
		clientPanel = new ClientPanel();
		tabbedPane.addTab("          CLIENT          ", clientPanel);
		
		
		groupPanel = new GroupPanel();
		tabbedPane.addTab("          GROUP          ", groupPanel);
		groupPanel.setLayout(null);
		
		loanPanel = new LoanPanel();
		tabbedPane.addTab("          LOAN          ", loanPanel);
		
		paymentPanel = new PaymentPanel();
		tabbedPane.addTab("          REPAYMENT          ", paymentPanel);
		
		reportPanel = new ReportPanel();
		tabbedPane.addTab("          REPORT          ", reportPanel);
		
		settingPanel = new SettingPanel();
		tabbedPane.addTab("          SETTING          ", settingPanel);
		initListeners();
	}
	
	   public void initListeners() {

	        /** When the window is resized, the panel size is updated. */
	        addComponentListener(new ComponentListener() {

	            @Override
	            public void componentResized(ComponentEvent e) {        
	                updatePanelSize();
	                clientPanel.updatePanelSize();
	                groupPanel.updatePanelSize();
	                loanPanel.updatePanelSize();
	                paymentPanel.updatePanelSize();
	                reportPanel.updatePanelSize();
	                settingPanel.updatePanelSize();
	            }

	            @Override
	            public void componentHidden(ComponentEvent evt) {}

	            @Override
	            public void componentShown(ComponentEvent evt) {}

	            @Override
	            public void componentMoved(ComponentEvent evt) {}
	        });
	    }
	   
	    public void updatePanelSize() {

//	        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
//	                .getDefaultScreenDevice();
//	        float monitorWidth = gd.getDisplayMode().getWidth();
//	        float monitorHeight = gd.getDisplayMode().getHeight();
	//
//	        // Aspect ratio of the monitor in decimal form.
//	        float monitorRatio = monitorWidth / monitorHeight;

	        JComponent parent = (JComponent) getParent();
	        float width = getWidth();
	        float height = getHeight();

//	        width = Math.min(width, height * monitorRatio);
//	        height = width / monitorRatio;

	        // I am subtracting the width and height by their respective aspect ratio...
	        int paddedWidth = (int) width - 36;
	        int paddedHeight = (int) height - 60;
//	        setPreferredSize(new Dimension(paddedWidth, paddedHeight));
	        tabbedPane.setBounds(10,10,paddedWidth,paddedHeight);
	        tabbedPane.setMinimumSize(new Dimension(802,708));
	        int resultWidth = getWidth();
	        int resultHeight = getHeight();
	        if (paddedWidth != resultWidth && paddedHeight != resultHeight) {
	            revalidate(); // preferred dimensions not applied, so force them
	        }

//	        System.out.println("Frame: " + width + "x" + height);
//	        System.out.println("ChangeSize: " + paddedWidth + "x" + paddedHeight);
//	        System.out.println("Resutl: " + resultWidth + "x" + resultHeight);
//	        System.out.println("Ratio: " + (float)resultWidth / resultHeight);
	    }
}
