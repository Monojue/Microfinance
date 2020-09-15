package main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import tool.MyString;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class PaymentPanel extends JPanel {
	private JTable table;
	private JTextField textField;
	private final ButtonGroup radioGroup = new ButtonGroup();
	private JPanel panel;
	private JScrollPane scrollPane;

	 public void updatePanelSize() {

//	        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
//	                .getDefaultScreenDevice();
//	        float monitorWidth = gd.getDisplayMode().getWidth();
//	        float monitorHeight = gd.getDisplayMode().getHeight();
	//
//	        // Aspect ratio of the monitor in decimal form.
//	        float monitorRatio = monitorWidth / monitorHeight;

//	        JComponent parent = (JComponent) getParent();
	        float width = getWidth();
	        float height = getHeight();

//	        width = Math.min(width, height * monitorRatio);
//	        height = width / monitorRatio;

	        // I am subtracting the width and height by their respective aspect ratio...
	        int paddedWidth = (int) width - 20;
	        int paddedHeight = (int) height - 70;
//	        setPreferredSize(new Dimension(paddedWidth, paddedHeight));
	        setBounds(0,0,paddedWidth,paddedHeight);
	        panel.setBounds(10,11,paddedWidth,37);
	        scrollPane.setBounds(10,59,paddedWidth,paddedHeight);
	        
	        int resultWidth = getWidth();
	        int resultHeight = getHeight();
	        if (paddedWidth != resultWidth && paddedHeight != resultHeight) {
	            revalidate(); // preferred dimensions not applied, so force them
	        }

	        System.out.println("Frame: " + width + "x" + height);
	        System.out.println("ChangeSize: " + paddedWidth + "x" + paddedHeight);
	        System.out.println("Resutl: " + resultWidth + "x" + resultHeight);
//	        System.out.println("Ratio: " + (float)resultWidth / resultHeight);
	    }
	public PaymentPanel() {
		Initialize();
	}
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 1039, 37);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);
		panel.setLayout(new MigLayout("", "[][][][][166.00][]", "[]"));
		
		JLabel label = new JLabel("Search With");
		panel.add(label, "cell 0 0");
		
		JRadioButton radioButton = new JRadioButton("Loan Request ID");
		radioGroup.add(radioButton);
		radioButton.setBackground(Color.LIGHT_GRAY);
		panel.add(radioButton, "cell 1 0");
		
		JRadioButton radioButton_1 = new JRadioButton("Client ID");
		radioGroup.add(radioButton_1);
		radioButton_1.setBackground(Color.LIGHT_GRAY);
		panel.add(radioButton_1, "cell 2 0");
		
		JRadioButton radioButton_2 = new JRadioButton("Group ID");
		radioGroup.add(radioButton_2);
		radioButton_2.setBackground(Color.LIGHT_GRAY);
		panel.add(radioButton_2, "cell 3 0");
		
		textField = new JTextField();
		panel.add(textField, "cell 4 0,growx");
		textField.setColumns(10);
		
		JButton button = new JButton("Search");
		panel.add(button, "cell 5 0");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
