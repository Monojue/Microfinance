package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import database.MyQueries;
import database.UQueries;
import entryForm.ClientEntry;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;
import tool.MyString;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientPanel extends JPanel {
	private JTextField textField;
	private JTable table;
	private JTextField test;
	UQueries msql = new UQueries();
	/**
	 * Create the panel.
	 */
	
	

	public ClientPanel() {
		Initialize();
		createTable();
	}
	
	public void createTable() {
			table.setModel(msql.getAllClient());
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(50);
			table.getColumnModel().getColumn(7).setPreferredWidth(100);
			table.getColumnModel().getColumn(7).setPreferredWidth(100);
	}
	
	public void Initialize() {
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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Client ID");
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		panel.add(rdbtnNewRadioButton, "cell 1 0");
		
		JRadioButton rdbtnClientName = new JRadioButton("Client Name");
		rdbtnClientName.setBackground(Color.LIGHT_GRAY);
		panel.add(rdbtnClientName, "cell 2 0");
		
		textField = new JTextField();
		panel.add(textField, "cell 4 0,growx");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		panel.add(btnNewButton, "cell 5 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator, "cell 6 0,growy");
		
		JButton btnNewButton_1 = new JButton("New Client");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ClientEntry().setVisible(true);
			}
		});
		panel.add(btnNewButton_1, "cell 7 0,alignx center");
		
		JButton btnNewButton_2 = new JButton("Edit Client");
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
