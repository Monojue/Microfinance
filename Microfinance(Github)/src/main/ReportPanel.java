package main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import database.MyQueries;
import entryForm.ClientEntry;
import entryForm.GroupRequestForm;
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
	private JTable tableGroup;
	private String ClientID, LoanRequestID, Amount, Duration,GroupID;
	
	MyQueries msql = new MyQueries();

	/**
	 * Create the panel.
	 */
	public ReportPanel() {
		Initialize();
		createIndividualTable();
		createGroupTable();
	}
	
	public void createIndividualTable() {
		table.setModel(msql.getIndividualLoanRequest());
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
	
	public void createGroupTable() {
		tableGroup.setModel(msql.getGroupLoanRequest());
		tableGroup.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(1).setMinWidth(0);
		tableGroup.getColumnModel().getColumn(1).setMaxWidth(0);
		tableGroup.getColumnModel().getColumn(1).setWidth(0);
		tableGroup.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(9).setPreferredWidth(100);
	}
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 1059, 580);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Individual Pending Request", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1039, 268);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 1019, 231);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		if(table.getSelectedRow()>=0) {
			createGroupTable();
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Group Pending Request", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 318, 1039, 251);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 1019, 231);
		panel_1.add(scrollPane_1);
		
		tableGroup = new JTable();
		scrollPane_1.setViewportView(tableGroup);
		
		JButton btnNewButton = new JButton("View Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()<0 && tableGroup.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Request to Approve or Delete","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(table.getSelectedRow()>=0) {
					LoanRequestID = (String) table.getValueAt(table.getSelectedRow(),0);
					ClientID = (String) table.getValueAt(table.getSelectedRow(),1);
					Amount = (String) table.getValueAt(table.getSelectedRow(),3);
					Duration = (String) table.getValueAt(table.getSelectedRow(),4);
					new LoanRequestForm(LoanRequestID,ClientID,Amount,Duration).setVisible(true);
				}
				else if(tableGroup.getSelectedRow()>=0) {
					LoanRequestID = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),0);
					GroupID = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),1);
					Amount = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),7);
					Duration = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),8);
					new GroupRequestForm(LoanRequestID,GroupID,Amount,Duration).setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(768, 280, 133, 25);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIndividualTable();
				createGroupTable();
			}
		});
		btnNewButton_1.setBounds(913, 280, 122, 25);
		add(btnNewButton_1);
	}
}
