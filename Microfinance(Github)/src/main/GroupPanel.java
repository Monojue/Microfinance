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
import tool.MyString;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;

import database.UQueries;

import javax.swing.border.BevelBorder;
import javax.swing.ButtonGroup;

public class GroupPanel extends JPanel {

	private JTextField textSearch;
	private JTable table;
	private UQueries msql = new UQueries();
	private final ButtonGroup radioGroup = new ButtonGroup();
	private JLabel lblError;
	/**
	 * Create the panel.
	 */
	
	

	public GroupPanel() {
		Initialize();
		createTable();
	}
	
	public void createTable() {
		table.setModel(msql.getGroup(null, MyString.All));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
	
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
		panel.setLayout(new MigLayout("", "[][][][][][151.00][][224][][][][][][grow]", "[grow]"));
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel.add(lblNewLabel, "cell 0 0");
		
		JLabel lblPrefix = new JLabel("GP-");
		panel.add(lblPrefix, "cell 4 0,alignx trailing");
		
		JRadioButton RadioID = new JRadioButton("Group ID");
		radioGroup.add(RadioID);
		RadioID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPrefix.setVisible(true);
			}
		});
		RadioID.setSelected(true);
		RadioID.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioID, "cell 1 0");
		
		JRadioButton RadioName = new JRadioButton("Leader Name");
		radioGroup.add(RadioName);
		RadioName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPrefix.setVisible(false);
			}
		});
		RadioName.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioName, "cell 2 0");
		
		
		
		textSearch = new JTextField();
		panel.add(textSearch, "cell 5 0,growx");
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (radioGroup.isSelected(RadioID.getModel())) {
					if (textSearch.getText().equals("")) {
						showError("Please Type Group ID To Search");
					}else {
						table.setModel(msql.getGroup("GP-"+textSearch.getText().trim(),MyString.ID));;
					}
				}else if (radioGroup.isSelected(RadioName.getModel())) {
					if (textSearch.getText().equals("")) {
						showError("Please Type Leader Name To Search");
					}else {
						table.setModel(msql.getGroup(textSearch.getText().trim(),MyString.Name));;
				}
				
			}
				table.getColumnModel().getColumn(0).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(200);
				table.getColumnModel().getColumn(4).setPreferredWidth(200);
				table.getColumnModel().getColumn(5).setPreferredWidth(200);
			}
		});
		panel.add(btnSearch, "cell 6 0");
		
		JButton btnNewButton_1 = new JButton("New Group");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GroupEntry().setVisible(true);
			}
		});
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		panel.add(lblError, "cell 7 0,grow");
		panel.add(btnNewButton_1, "cell 8 0");
		
		JButton btnNewButton_2 = new JButton("Edit Group");
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
