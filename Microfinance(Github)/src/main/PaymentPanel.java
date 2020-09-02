package main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PaymentPanel extends JPanel {
	private JTable table;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PaymentPanel() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setBounds(0, 0, 1059, 580);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1039, 37);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);
		panel.setLayout(new MigLayout("", "[][][][][166.00][]", "[]"));
		
		JLabel label = new JLabel("Search With");
		panel.add(label, "cell 0 0");
		
		JRadioButton radioButton = new JRadioButton("Loan Request ID");
		radioButton.setBackground(Color.LIGHT_GRAY);
		panel.add(radioButton, "cell 1 0");
		
		JRadioButton radioButton_1 = new JRadioButton("Client ID");
		radioButton_1.setBackground(Color.LIGHT_GRAY);
		panel.add(radioButton_1, "cell 2 0");
		
		JRadioButton radioButton_2 = new JRadioButton("Group ID");
		radioButton_2.setBackground(Color.LIGHT_GRAY);
		panel.add(radioButton_2, "cell 3 0");
		
		textField = new JTextField();
		panel.add(textField, "cell 4 0,growx");
		textField.setColumns(10);
		
		JButton button = new JButton("Search");
		panel.add(button, "cell 5 0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
