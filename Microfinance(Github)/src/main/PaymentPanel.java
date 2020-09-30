package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import database.MyQueries;
import database.UQueries;
import entryForm.RepaymentEntry;

import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import tool.MyString;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class PaymentPanel extends JPanel {
	private static JTable table;
	private JTextField txtSearch;
	private final ButtonGroup radioGroup = new ButtonGroup();
	private final ButtonGroup radioGroup2 = new ButtonGroup();
	private JPanel panel;
	private JRadioButton rdoCID;
	private JRadioButton rdoIndividual,rdoGroup;
	private JScrollPane scrollPane;
	private String ClientID, LoanRequestID, Amount, Duration,GroupID;
	
	static MyQueries msql = new MyQueries();
	static UQueries usql = new UQueries();
	private JButton btnRefresh;
	private JButton btnDetails;
	private JButton btnDelete;
	private JRadioButton rdoDate;
	private JDateChooser dateChooser;
	private JLabel lblPrefix;

	 public void updatePanelSize() {

////	        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
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
	        panel.setBounds(10,11,paddedWidth,37);
	        scrollPane.setBounds(10,59,paddedWidth,paddedHeight);
	        
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
	public PaymentPanel() {
		Initialize();
		createITable("All", null);
	}
	
	public static void createITable(String str, String ID) {
		table.setModel(msql.getIRepaymentTable(str, ID));
	}
	
	public static void createGTable(String str, String ID) {
		table.setModel(msql.getGRepaymentTable(str, ID));
	}
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 1039, 37);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);
		panel.setLayout(new MigLayout("", "[61px][111px][71px][49px][][143.00][67px][grow][73px][55px][grow][69px][65px][73px]", "[23px,grow]"));
		
		JLabel label = new JLabel("Search With");
		panel.add(label, "cell 0 0,alignx left,aligny center");
		
		JRadioButton rdoLRID = new JRadioButton("Loan Request ID");
		rdoLRID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setVisible(false);
				txtSearch.setVisible(true);
				lblPrefix.setText("LR-");
			}
		});
		rdoLRID.setSelected(true);
		radioGroup.add(rdoLRID);
		rdoLRID.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoLRID, "cell 1 0,alignx left,aligny bottom");
		
		rdoCID = new JRadioButton("Client ID");
		rdoCID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setVisible(false);
				txtSearch.setVisible(true);
				lblPrefix.setText("CL-");
			}
		});
		radioGroup.add(rdoCID);
		rdoCID.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoCID, "cell 2 0,alignx left,aligny bottom");
		
		rdoDate = new JRadioButton("Date");
		rdoDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtSearch.setVisible(false);
				dateChooser.setVisible(true);
				lblPrefix.setText("");
			}
		});
		radioGroup.add(rdoDate);
		rdoDate.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoDate, "cell 3 0,alignx left,aligny bottom");
		
		lblPrefix = new JLabel("LR-");
		panel.add(lblPrefix, "cell 4 0");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 5 0,grow");
		panel_1.setLayout(null);
		
//		JRadioButton radioButton_2 = new JRadioButton("Group ID");
//		radioGroup.add(radioButton_2);
//		radioButton_2.setBackground(Color.LIGHT_GRAY);
//		panel.add(radioButton_2, "cell 3 0");
		
		txtSearch = new JTextField();
		txtSearch.setBounds(0, 0, 143, 23);
		panel_1.add(txtSearch);
		txtSearch.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(0, 0, 143, 23);
		panel_1.add(dateChooser);
		dateChooser.setVisible(false);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtSearch.getText().equals("") && !rdoDate.isSelected()) {
					String searchdata = txtSearch.getText();
					if (rdoLRID.isSelected()) {
						if (rdoIndividual.isSelected()) {
							createITable("LID", "LR-"+ searchdata);
						}else if (rdoGroup.isSelected()) {
							createGTable("LID", "LR-"+ searchdata);
						}
					}else if (rdoCID.isSelected()) {
						if (rdoIndividual.isSelected()) {
							createITable("CID", "CL-"+ searchdata);
						}else if (rdoGroup.isSelected()) {
							createGTable("GID", "GP-"+searchdata);
						}
					}
				}else if (rdoDate.isSelected()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
					String searchdate="";
					try {
						searchdate = sdf.format(dateChooser.getDate());
						System.out.println(searchdate);
						if (rdoIndividual.isSelected()) {
							createITable("Date", searchdate);
						}else if (rdoGroup.isSelected()) {
							createGTable("Date", searchdate);
						}
					} catch (NullPointerException ne) {
						JOptionPane.showMessageDialog(null, "Please choose Date To search!");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Please Type To search!");
				}
			}
		});
		panel.add(btnSearch, "cell 6 0,alignx left,aligny bottom");
		
		rdoIndividual = new JRadioButton("Individual");
		rdoIndividual.setSelected(true);
		rdoIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdoCID.setText("Client ID");
				createITable("All", null);
			}
		});
		
		JLabel lblNewLabel = new JLabel("View Table Of :");
		panel.add(lblNewLabel, "cell 7 0,alignx right");
		rdoIndividual.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoIndividual, "cell 8 0,alignx left,aligny bottom");
		radioGroup2.add(rdoIndividual);
		
		rdoGroup = new JRadioButton("Group");
		rdoGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdoCID.setText("Group ID");
				createGTable("All", null);
			}
		});
		rdoGroup.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoGroup, "cell 9 0,alignx left,aligny bottom");
		radioGroup2.add(rdoGroup);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoIndividual.isSelected()) {
					createITable("All", null);
				}
				else if(rdoGroup.isSelected()) {
					createGTable("All", null);
				}
			}
		});
		
		btnDetails = new JButton("Details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoIndividual.isSelected()) {
					if(table.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Loan to Pay!","Error",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						LoanRequestID = (String) table.getValueAt(table.getSelectedRow(),0);
						ClientID = (String) table.getValueAt(table.getSelectedRow(),1);
						Amount = (String) table.getValueAt(table.getSelectedRow(),4);
						Duration = (String) table.getValueAt(table.getSelectedRow(),7);
						new RepaymentEntry("Individual",LoanRequestID,ClientID,Amount,Duration).setVisible(true);
					}
				}
				
				else {
					if(table.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Loan to Pay!","Error",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						LoanRequestID = (String) table.getValueAt(table.getSelectedRow(),0);
						ClientID = (String) table.getValueAt(table.getSelectedRow(),1);
						Amount = (String) table.getValueAt(table.getSelectedRow(),4);
						Duration = (String) table.getValueAt(table.getSelectedRow(),7);
						new RepaymentEntry("Group",LoanRequestID,ClientID,Amount,Duration).setVisible(true);
					}
				}
			}
		});
		panel.add(btnDetails, "cell 11 0,alignx left,aligny bottom");
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password  = "";
				String message = "This Client is founded in ";
				JPasswordField passwordField = new JPasswordField();
				passwordField.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
				Object[] obj = {"Are you sure want to Delete!"
						+ "\n Please Type Password To Delete!", passwordField};
				Object stringArray[] = {"OK","Cancel"};
				
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Payment to Delete!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String LoanID = (String) table.getValueAt(table.getSelectedRow(),0);
					
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
						
						if(rdoIndividual.isSelected()) {
							if (usql.deletePayment(LoanID) && usql.deleteclientLoanRequestLoanID(LoanID)) {
								JOptionPane.showMessageDialog(null, "Successfully Deleted!");
							}else {
								JOptionPane.showMessageDialog(null, "Error Occoured!");
							}
						}
						else if(rdoGroup.isSelected()) {
							if (usql.deletePayment(LoanID) && usql.deleteGroupLoanRequestLoanID(LoanID)) {
								JOptionPane.showMessageDialog(null, "Successfully Deleted!");
							}else {
								JOptionPane.showMessageDialog(null, "Error Occoured!");
							}
						}
						
					}else {
						JOptionPane.showMessageDialog(null,  "Wrong Password!");
					}
				}
			}
		});
		panel.add(btnDelete, "cell 12 0,alignx left,aligny bottom");
		panel.add(btnRefresh, "cell 13 0,alignx left,aligny bottom");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
