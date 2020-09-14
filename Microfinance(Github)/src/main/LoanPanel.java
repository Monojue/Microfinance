package main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import database.MyQueries;
import entryForm.GroupRequestForm;
import entryForm.LoanRequestForm;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class LoanPanel extends JPanel {

	private JTextField textField;
	private JTable table;
	private JTable tableIndividual;
	private JTextField textField_1;
	private JTextField textField_2;

	MyQueries msql = new MyQueries();
	/**
	 * Create the panel.
	 */
	
	public LoanPanel() {
		Initialize();
		createITable();
		//createGTable();
	}
	
	public void createITable() {
		tableIndividual.setModel(msql.getApprovedLoanRequest());
		tableIndividual.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIndividual.getColumnModel().getColumn(1).setMinWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setMaxWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setWidth(0);
		tableIndividual.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 1059, 580);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1059, 596);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Individual", new ImageIcon(LoanPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeClosed.gif")), panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 1034, 497);
		panel.add(scrollPane);
		
		tableIndividual = new JTable();
		scrollPane.setViewportView(tableIndividual);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 11, 1034, 34);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][][][159.00][][][][][][][]", "[]"));
		
		JButton btnNewButton = new JButton("New Individual Loan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoanRequestForm(null,null,null,null).setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel_2.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Loan Request ID");
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdbtnNewRadioButton, "cell 1 0,growx,aligny center");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Customer ID");
		rdbtnNewRadioButton_1.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdbtnNewRadioButton_1, "cell 2 0,growx,aligny center");
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "cell 3 0,growx,aligny center");
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Search");
		panel_2.add(btnNewButton_2, "cell 4 0,growx,aligny center");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_2.add(separator, "cell 5 0,grow");
		panel_2.add(btnNewButton, "cell 6 0,growx,aligny center");
		
		JButton button = new JButton("Refresh");
		panel_2.add(button, "cell 8 0,growx,aligny center");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Group", new ImageIcon(LoanPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeClosed.gif")), panel_1, "");
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 56, 1034, 497);
		panel_1.add(scrollPane_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(10, 11, 1034, 34);
		panel_1.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[58px][105px][85px][159px][65px][][129px][][71px]", "[23px]"));
		
		JLabel label = new JLabel("Search With");
		panel_4.add(label, "cell 0 0,growx,aligny center");
		
		JRadioButton radioButton = new JRadioButton("Loan Request ID");
		radioButton.setBackground(Color.LIGHT_GRAY);
		panel_4.add(radioButton, "cell 1 0,growx,aligny center");
		
		JRadioButton rdbtnGroupId = new JRadioButton("Group ID");
		rdbtnGroupId.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdbtnGroupId, "cell 2 0,growx,aligny center");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_4.add(textField_2, "cell 3 0,growx,aligny center");
		
		JButton button_1 = new JButton("Search");
		panel_4.add(button_1, "cell 4 0,growx,aligny center");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_4.add(separator_1, "cell 5 0,grow");
		
		JButton btnNewGroupLoan = new JButton("New Group Loan");
		btnNewGroupLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GroupRequestForm().setVisible(true);
			}
		});
		panel_4.add(btnNewGroupLoan, "cell 6 0,growx,aligny center");
		
		JButton button_3 = new JButton("Refresh");
		panel_4.add(button_3, "cell 8 0,growx,aligny center");
		

	}
}
