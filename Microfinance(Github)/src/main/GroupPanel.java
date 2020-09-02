package main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import entryForm.GroupEntry;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class GroupPanel extends JPanel {

	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public GroupPanel() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 1059, 580);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 1039, 37);
		add(panel);
		panel.setLayout(new MigLayout("", "[][][][][151.00][][][][][][][][grow]", "[grow]"));
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel.add(lblNewLabel, "cell 0 0");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Group ID");
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		panel.add(rdbtnNewRadioButton, "cell 1 0");
		
		JRadioButton rdbtnClientName = new JRadioButton("Leader Name");
		rdbtnClientName.setBackground(Color.LIGHT_GRAY);
		panel.add(rdbtnClientName, "cell 2 0");
		
		textField = new JTextField();
		panel.add(textField, "cell 4 0,growx");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		panel.add(btnNewButton, "cell 5 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator, "cell 6 0,grow");
		
		JButton btnNewButton_1 = new JButton("New Group");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GroupEntry().setVisible(true);
			}
		});
		panel.add(btnNewButton_1, "cell 7 0");
		
		JButton btnNewButton_2 = new JButton("Edit Group");
		panel.add(btnNewButton_2, "cell 9 0");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_1, "cell 10 0,grow");
		
		JButton btnNewButton_3 = new JButton("Refresh");
		panel.add(btnNewButton_3, "cell 11 0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
