package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import database.MyQueries;
import database.UQueries;
import entryForm.GroupRequestForm;
import entryForm.LoanRequestForm;
import entryForm.ViewDetails;
import net.miginfocom.swing.MigLayout;
import tool.MyString;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

public class LoanPanel extends JPanel {

	private JTextField textField;
	//private JTable table;
	private static JTable tableIndividual;
	private JTextField txtISearch;
	private JTextField txtGSearch;

	static MyQueries msql = new MyQueries();

	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JScrollPane scrollPane_1;
	private JPanel panel_4;
	private static JTable tableGroup;
	private JButton btnIPaid;
	private JButton btnGPaid;
	private JRadioButton rdoIAccept;
	private JRadioButton rdoIRejected;
	private JRadioButton rdoGAccepted;
	private JRadioButton rdoGRejected;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private UQueries usql = new UQueries();
	private final ButtonGroup rdoInGroup = new ButtonGroup();
	private final ButtonGroup rdoGGroup = new ButtonGroup();
	private JLabel lblprefix1;
	private JLabel lblprefix2;

	/**
	 * Create the panel.
	 */
	
	public LoanPanel() {
		Initialize();
		createITable("All", null);
		createGTable("All", null);
	}
	
	public static void createITable(String str, String ID) {
		tableIndividual.setModel(msql.getIndividualApprovedLoanRequest(str, ID));
		tableIndividual.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIndividual.getColumnModel().getColumn(1).setMinWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setMaxWidth(0);
		tableIndividual.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
	
	public static void createRejectedITable(String str, String ID) {
		tableIndividual.setModel(msql.getIndividualRejectedLoanRequest(str, ID));
		tableIndividual.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIndividual.getColumnModel().getColumn(1).setMinWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setMaxWidth(0);
		tableIndividual.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(4).setPreferredWidth(100);
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
	        float height = getHeight();

//	        width = Math.min(width, height * monitorRatio);
//	        height = width / monitorRatio;

	        // I am subtracting the width and height by their respective aspect ratio...
	        int paddedWidth = (int) width - 20;
	        int paddedHeight = (int) height - 70;
//	        setPreferredSize(new Dimension(paddedWidth, paddedHeight));
	        setBounds(0,0,paddedWidth,paddedHeight);
//	        panel.setBounds(10,11,paddedWidth+50,37);
	        panel_2.setBounds(10,11,paddedWidth-20,37);
	        panel_4.setBounds(10,11,paddedWidth-20,37);
	        tabbedPane.setBounds(10,10,paddedWidth,paddedHeight+40);
	        scrollPane.setBounds(10,59,paddedWidth-25,paddedHeight);
	        scrollPane_1.setBounds(10,59,paddedWidth-25,paddedHeight);
	        
	        int resultWidth = getWidth();
	        int resultHeight = getHeight();
	        if (paddedWidth != resultWidth && paddedHeight != resultHeight) {
	            revalidate(); // preferred dimensions not applied, so force them
	        }

	        System.out.println("GroupFrame: " + width + "x" + height);
	        System.out.println("GroupChangeSize: " + paddedWidth + "x" + paddedHeight);
	        System.out.println("GroupResut: " + resultWidth + "x" + resultHeight);
//	        System.out.println("Ratio: " + (float)resultWidth / resultHeight);
	    }

	public static void createGTable(String str, String ID) {
		tableGroup.setModel(msql.getGroupApprovedLoanRequest(str, ID));
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
	public static void createRejectedGTable(String str, String ID) {
		tableGroup.setModel(msql.getGroupRejectedLoanRequest(str, ID));
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
		tableGroup.getColumnModel().getColumn(10).setPreferredWidth(100);
	}
	
	public void PaidDay(String ID,String Amount) {
		String[] PayDay = new String[2];
		PayDay[0] = ID;
		PayDay[1] = java.time.LocalDate.now().toString();
		boolean update = msql.UpdateData("paidday", PayDay);
		
		if (update) {
			JOptionPane.showMessageDialog(null, Amount +" is Paid Sucessfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(!update) {
			JOptionPane.showMessageDialog(null, "Failed to Approve Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Individual", panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 1034, 497);
		panel.add(scrollPane);
		
		tableIndividual = new JTable();
		tableIndividual.setRowHeight(20);
		JTableHeader header = tableIndividual.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setForeground(Color.BLACK);
		header.setFont(new Font("SansSerif", Font.BOLD , 14));
		scrollPane.setViewportView(tableIndividual);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 11, 1034, 34);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][][][][120][][][][][][27.00,grow][][][][][]", "[]"));
		
		JButton btnNewButton = new JButton("New Individual Loan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoanRequestForm(null,null).setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel_2.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		JRadioButton rdoIRID = new JRadioButton("Loan Request ID");
		rdoIRID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblprefix1.setText("LR-");
			}
		});
		rdoIRID.setSelected(true);
		rdoInGroup.add(rdoIRID);
		rdoIRID.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdoIRID, "cell 1 0,growx,aligny center");
		
		JRadioButton rdoCID = new JRadioButton("Customer ID");
		rdoCID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblprefix1.setText("CL-");
			}
		});
		rdoInGroup.add(rdoCID);
		rdoCID.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdoCID, "cell 2 0,growx,aligny center");
		
		lblprefix1 = new JLabel("LR-");
		panel_2.add(lblprefix1, "cell 3 0,alignx trailing");
		
		txtISearch = new JTextField();
		panel_2.add(txtISearch, "cell 4 0,growx,aligny top");
		txtISearch.setColumns(10);
		
		JButton btnISearch = new JButton("Search");
		btnISearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtISearch.getText().equals("")) {
					String searchData = txtISearch.getText();
					if (rdoIRID.isSelected()) {
						if (rdoIAccept.isSelected()) {
							createITable("LID","LR-"+searchData);
						}else if (rdoIRejected.isSelected()) {
							createRejectedITable("LID", "LR-"+searchData);
						}
					}else if (rdoCID.isSelected()) {
						if (rdoIAccept.isSelected()) {
							createITable("CID","CL-"+searchData);
						}else if (rdoIRejected.isSelected()) {
							createRejectedITable("CID","CL-"+searchData);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Please Type To search!");
				}
			}
		});
		panel_2.add(btnISearch, "cell 5 0,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("View Table Of");
		panel_2.add(lblNewLabel_1, "cell 7 0");
		
		rdoIAccept = new JRadioButton("Accepted");
		rdoIAccept.setSelected(true);
		rdoIAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createITable("All", null);
				btnIPaid.setText("To Pay");
			}
		});
		buttonGroup.add(rdoIAccept);
		rdoIAccept.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdoIAccept, "cell 8 0");
		
		rdoIRejected = new JRadioButton("Rejected");
		rdoIRejected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRejectedITable("All", null);
				btnIPaid.setText("To Inform");
			}
		});
		buttonGroup.add(rdoIRejected);
		rdoIRejected.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdoIRejected, "cell 9 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_2.add(separator, "cell 6 0,grow");
		panel_2.add(btnNewButton, "cell 11 0,growx,aligny center");
		
		btnIPaid = new JButton("To Pay");
		btnIPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnIPaid.getText() == "To Pay") {
					if(tableIndividual.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Client Request","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						new ViewDetails("Individual",(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),0),(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),1),
								(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),3),(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),4),
								"Accepted").setVisible(true);
					
					}				
				}
				else if(btnIPaid.getText()=="To Inform") {
					if(tableIndividual.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Individual Request to Inform","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						new ViewDetails("Individual",(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),0),(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),1),
								(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),4),(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),5),
								(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),6)).setVisible(true);
					}				
				}			
			}
		});
		panel_2.add(btnIPaid, "cell 11 0,growx,aligny center");
		
		JButton button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoIRejected.isSelected()==true) {
					createRejectedITable("All", null);
				}
				else {
					createITable("All", null);
				}
			}
		});
		
		JButton btnInDelete = new JButton("Delete");
		btnInDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password  = "";
				String message = "This Client is founded in ";
				JPasswordField passwordField = new JPasswordField();
				passwordField.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
				Object[] obj = {"Are you sure want to Delete!"
						+ "\n Please Type Password To Delete!", passwordField};
				Object stringArray[] = {"OK","Cancel"};
				if(tableIndividual.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Loan Request to Delete!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String LoanID = (String) tableIndividual.getValueAt(tableIndividual.getSelectedRow(),0);
					
					while(password.isEmpty()){
						if (JOptionPane.showOptionDialog(null, obj, "Warning!",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION)
									password = passwordField.getText().toString();
						else {
							password = "";
							return;
						}
					}
					if (usql.CheckPassword(MyString.LoginUser, password)) {
						
						if (usql.deleteclientLoanRequestLoanID(LoanID)) {
							JOptionPane.showMessageDialog(null, "Successfully Deleted!");
						}else {
							JOptionPane.showMessageDialog(null, "Error Occoured!");
						}
					}else {
						JOptionPane.showMessageDialog(null,  "Wrong Password!");
					}
				}
			}
		});
		panel_2.add(btnInDelete, "cell 12 0");
		panel_2.add(button, "cell 14 0,growx,aligny center");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Group", panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 56, 1034, 497);
		panel_1.add(scrollPane_1);
		

		panel_4 = new JPanel();

		tableGroup = new JTable();
		tableGroup.setRowHeight(20);
		JTableHeader header2 = tableGroup.getTableHeader();
		header2.setBackground(Color.WHITE);
		header2.setForeground(Color.BLACK);
		header2.setFont(new Font("SansSerif", Font.BOLD , 14));
		scrollPane_1.setViewportView(tableGroup);
		
		

		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(10, 11, 1034, 34);
		panel_1.add(panel_4);
//		panel_4.setLayout(new MigLayout("", "[58px][105px][85px][159px][65px][][129px][][71px]", "[23px]"));
		panel_4.setLayout(new MigLayout("", "[][][][][120][][][][][][grow][][][][][]", "[]"));
		
		JLabel label = new JLabel("Search With");
		panel_4.add(label, "cell 0 0,growx,aligny center");
		
		JRadioButton rdoGRID = new JRadioButton("Loan Request ID");
		rdoGRID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblprefix2.setText("LR-");
			}
		});
		rdoGRID.setSelected(true);
		rdoGGroup.add(rdoGRID);
		rdoGRID.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdoGRID, "cell 1 0,growx,aligny center");
		
		JRadioButton rdoGID = new JRadioButton("Group ID");
		rdoGID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblprefix2.setText("GP-");
			}
		});
		rdoGGroup.add(rdoGID);
		rdoGID.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdoGID, "cell 2 0,growx,aligny center");
		
		lblprefix2 = new JLabel("LR-");
		panel_4.add(lblprefix2, "cell 3 0,alignx trailing");
		
		txtGSearch = new JTextField();
		txtGSearch.setColumns(10);
		panel_4.add(txtGSearch, "cell 4 0,growx,aligny center");
		
		JButton btnGSearch = new JButton("Search");
		btnGSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtGSearch.getText().equals("")) {
					String searchData = txtGSearch.getText();
					if (rdoGRID.isSelected()) {
						if (rdoGAccepted.isSelected()) {
							createGTable("LID", "LR-"+searchData);
						}else if (rdoGRejected.isSelected()) {
							createRejectedGTable("LID", "LR-"+searchData);
						}
					}else if (rdoGID.isSelected()) {
						if (rdoGAccepted.isSelected()) {
							createGTable("GID", "GP-"+searchData);
						}else if (rdoGRejected.isSelected()) {
							createRejectedGTable("GID", "GP-"+searchData);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Please Type To search!");
				}
			}
		});
		panel_4.add(btnGSearch, "cell 5 0,growx,aligny center");
		
		ButtonGroup BG = new ButtonGroup();
		
		JLabel lblNewLabel_2 = new JLabel("View Table Of");
		panel_4.add(lblNewLabel_2, "cell 7 0");
		
		rdoGAccepted = new JRadioButton("Accepted");
		rdoGAccepted.setSelected(true);
		rdoGAccepted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createGTable("All", null);
				btnGPaid.setText("To Pay");
			}
		});
		rdoGAccepted.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdoGAccepted, "cell 8 0");
		BG.add(rdoGAccepted);
		
		rdoGRejected = new JRadioButton("Rejected");
		rdoGRejected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRejectedGTable("All", null);
				btnGPaid.setText("To Inform");
			}
		});
		rdoGRejected.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdoGRejected, "cell 9 0");
		BG.add(rdoGRejected);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_4.add(separator_1, "cell 6 0,grow");
		
		
		
		JButton btnNewGroupLoan = new JButton("New Group Loan");
		btnNewGroupLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GroupRequestForm(null,null).setVisible(true);
			}
		});
		panel_4.add(btnNewGroupLoan, "cell 11 0,growx,aligny center");
		
		btnGPaid = new JButton("To Pay");
		btnGPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (btnGPaid.getText() == "To Pay") {
					if(tableGroup.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Group Request","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {									
						new ViewDetails("Group",(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),0),(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),1),
								(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),7),(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),8),
								"Accepted").setVisible(true);
					}
				}
				else if(btnGPaid.getText()=="To Inform") {
					if(tableGroup.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Group Request to Inform Them","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						new ViewDetails("Group",(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),0),(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),1),
								(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),8),(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),9),
								(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),10)).setVisible(true);
					}
					
				}
			}
		});
		panel_4.add(btnGPaid, "cell 11 0,growx,aligny center");
		
		JButton button_3 = new JButton("Refresh");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdoGRejected.isSelected()==true) {
					createRejectedGTable("All", null);
				}
				else {
					createGTable("All", null);
				}
			}
		});
		
		JButton btnGDelete = new JButton("Delete");
		btnGDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password  = "";
				String message = "This Client is founded in ";
				JPasswordField passwordField = new JPasswordField();
				passwordField.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
				Object[] obj = {"Are you sure want to Delete!"
						+ "\n Please Type Password To Delete!", passwordField};
				Object stringArray[] = {"OK","Cancel"};
				
				if(tableGroup.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Loan Request to Delete!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String LoanID = (String) tableGroup.getValueAt(tableGroup.getSelectedRow(),0);
					
					while(password.isEmpty()){
						if (JOptionPane.showOptionDialog(null, obj, "Warning!",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION)
									password = passwordField.getText().toString();
						else {
							password = "";
							return;
						}
					}
					if (usql.CheckPassword(MyString.LoginUser, password)) {
						
						if (usql.deleteGroupLoanRequestLoanID(LoanID)) {
							JOptionPane.showMessageDialog(null, "Successfully Deleted!");
						}else {
							JOptionPane.showMessageDialog(null, "Error Occoured!");
						}
					}else {
						JOptionPane.showMessageDialog(null,  "Wrong Password!");
					}
				}
			}
		});
		panel_4.add(btnGDelete, "cell 12 0");
		panel_4.add(button_3, "cell 14 0,growx,aligny center");
		

	}
}
