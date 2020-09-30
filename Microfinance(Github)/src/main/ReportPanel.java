package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import database.MyQueries;
import entryForm.ClientEntry;
import entryForm.GroupRequestForm;
import entryForm.LoanRequestForm;
import entryForm.ViewDetails;
import tool.MyString;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class ReportPanel extends JPanel {
	private JTable table;
	private JTable tableGroup;
	private String ClientID, LoanRequestID, Amount, Duration,GroupID;
	
	MyQueries msql = new MyQueries();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JPanel panel_2;

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
	        float height = getHeight()/2;

//	        width = Math.min(width, height * monitorRatio);
//	        height = width / monitorRatio;

	        // I am subtracting the width and height by their respective aspect ratio...
	        int paddedWidth = (int) width - 20;
	        int paddedHeight = (int) height -31;
//	        setPreferredSize(new Dimension(paddedWidth, paddedHeight));
	        setBounds(0,0,paddedWidth,paddedHeight);
	        
	        panel.setBounds(10, 51, paddedWidth, paddedHeight);
	        scrollPane.setBounds(10, 26, paddedWidth-20, paddedHeight-40);
	        panel_1.setBounds(10, paddedHeight+61, paddedWidth, paddedHeight);
			scrollPane_1.setBounds(10, 26, paddedWidth-20, paddedHeight-40);
			panel_2.setBounds(10,11,paddedWidth,37);
			
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
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Individual Pending Request", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 57, 1039, 268);

		add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 1019, 231);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setForeground(Color.BLACK);
		header.setFont(new Font("SansSerif", Font.BOLD , 14));
		scrollPane.setViewportView(table);
		if(table.getSelectedRow()>=0) {
			createGroupTable();
		}
		

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Group Pending Request", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 347, 1039, 335);

		add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 1019, 231);
		panel_1.add(scrollPane_1);
		

		
		// tableApproved = new JTable();
		// scrollPane_1.setViewportView(tableApproved);

		tableGroup = new JTable();
		tableGroup.setRowHeight(20);
		JTableHeader header2 = tableGroup.getTableHeader();
		header2.setBackground(Color.WHITE);
		header2.setForeground(Color.BLACK);
		header2.setFont(new Font("SansSerif", Font.BOLD , 14));
		scrollPane_1.setViewportView(tableGroup);

		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 1308, 35);
		add(panel_2);
		panel_2.setLayout(new MigLayout("", "[grow][100px][][100px]", "[]"));
		
		JButton btnNewButton = new JButton("View Details");
		panel_2.add(btnNewButton, "cell 1 0,grow");
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
					new ViewDetails("Individual",LoanRequestID,ClientID,Amount,Duration,null).setVisible(true);
				}
				else if(tableGroup.getSelectedRow()>=0) {
					LoanRequestID = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),0);
					GroupID = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),1);
					Amount = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),7);
					Duration = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),8);
					new ViewDetails("Group",LoanRequestID,GroupID,Amount,Duration,null).setVisible(true);
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Refresh");
		panel_2.add(btnNewButton_1, "cell 3 0,grow");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIndividualTable();
				createGroupTable();
			}
		});
	}
}
