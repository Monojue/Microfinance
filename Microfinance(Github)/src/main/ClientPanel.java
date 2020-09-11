package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;

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
import javax.swing.ButtonGroup;

public class ClientPanel extends JPanel {
	private JTextField textSearch;
	private JTable table;
	private JTextField test;
	UQueries msql = new UQueries();
	MyQueries msql1 = new MyQueries();
	private ButtonGroup radioGroup = new ButtonGroup();
	private JLabel lblPrefix;
	private JLabel lblError;
	public String ClientID;
	/**
	 * Create the panel.
	 */
	

	public ClientPanel() {
		Initialize();
		createTable();
	}
	
	public String[] ImportClientID() {
		String[] ClientData = msql1.getClientDetailsFormID(ClientID);
		return ClientData;
	}
	
	public void createTable() {
			table.setModel(msql.getClient(null, MyString.All));
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
	public void showError(String error) {
		lblError.setText(error);
		lblError.setVisible(true);
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
		panel.setLayout(new MigLayout("", "[][][][][][151.00][][224.00][][][][][][6.00]", "[grow]"));
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel.add(lblNewLabel, "cell 0 0");
		
		JRadioButton RadioID = new JRadioButton("Client ID");
		radioGroup.add(RadioID);
		RadioID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPrefix.setVisible(true);
			}
		});
		RadioID.setSelected(true);
		RadioID.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioID, "cell 1 0");
		
		JRadioButton RadioName = new JRadioButton("Client Name");
		radioGroup.add(RadioName);
		RadioName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPrefix.setVisible(false);
			}
		});
		RadioName.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioName, "cell 2 0");
		
		lblPrefix = new JLabel("CL-");
		panel.add(lblPrefix, "cell 4 0,alignx trailing");
		
		textSearch = new JTextField();
		panel.add(textSearch, "cell 5 0,growx");
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					if (radioGroup.isSelected(RadioID.getModel())) {
						if (textSearch.getText().equals("")) {
							showError("Please Type Client ID To Search");
						}else {
							table.setModel(msql.getClient("CL-"+textSearch.getText().trim(),MyString.ID));;
						}
					}else if (radioGroup.isSelected(RadioName.getModel())) {
						if (textSearch.getText().equals("")) {
							showError("Please Type Client Name To Search");
						}else {
							table.setModel(msql.getClient(textSearch.getText().trim(),MyString.Name));;
						}
					}
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
		});
		panel.add(btnSearch, "cell 6 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator, "flowx,cell 7 0,growy");
		
		JButton btnNewButton_1 = new JButton("New Client");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ClientEntry(null).setVisible(true);
			}
		});
		panel.add(btnNewButton_1, "cell 8 0,alignx center");
		
		JButton btnNewButton_2 = new JButton("Edit Client");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Client to Edit!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					ClientID = (String) table.getValueAt(table.getSelectedRow(),0);
					ImportClientID();
					new ClientEntry(ClientID).setVisible(true);
				}
			}
		});
		panel.add(btnNewButton_2, "cell 10 0");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_1, "cell 11 0,grow");
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable();
			}
		});
		panel.add(btnNewButton_3, "cell 12 0");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		panel.add(lblError, "cell 7 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}
}
