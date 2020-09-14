package main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import database.MyQueries;
import entryForm.ClientEntry;
import entryForm.LoanRequestForm;
import tool.MyString;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportPanel extends JPanel {
	private JTable table;
	private JTable table_1;
	private String ClientID, LoanRequestID, Amount, Duration;
	
	MyQueries msql = new MyQueries();

	/**
	 * Create the panel.
	 */
	public ReportPanel() {
		Initialize();
		createTable();
	}
	
	public void createTable() {
		table.setModel(msql.getLoanRequest());
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
}
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 1059, 580);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pending Request", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1039, 268);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 1019, 231);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Approved Request", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 318, 1039, 251);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 1019, 231);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("View Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Request to Approve or Delete","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					LoanRequestID = (String) table.getValueAt(table.getSelectedRow(),0);
					ClientID = (String) table.getValueAt(table.getSelectedRow(),2);
					Amount = (String) table.getValueAt(table.getSelectedRow(),4);
					Duration = (String) table.getValueAt(table.getSelectedRow(),5);
					new LoanRequestForm(LoanRequestID,ClientID,Amount,Duration).setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(768, 280, 133, 25);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTable();
			}
		});
		btnNewButton_1.setBounds(913, 280, 122, 25);
		add(btnNewButton_1);
	}
}
