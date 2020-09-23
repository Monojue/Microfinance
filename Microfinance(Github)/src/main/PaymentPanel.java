package main;

import java.awt.Color;

import javax.swing.JPanel;
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
import java.awt.event.ActionEvent;

public class PaymentPanel extends JPanel {
	private static JTable table;
	private JTextField textField;
	private final ButtonGroup radioGroup = new ButtonGroup();
	private final ButtonGroup radioGroup2 = new ButtonGroup();
	private JPanel panel;
	private JRadioButton rdoType;
	private JRadioButton rdoIndividual,rdoGroup;
	private JScrollPane scrollPane;
	private String ClientID, LoanRequestID, Amount, Duration,GroupID;
	
	static MyQueries msql = new MyQueries();
	static UQueries usql = new UQueries();
	private JButton btnRefresh;
	private JButton btnDetails;
	private JButton btnDelete;

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
		createITable();
	}
	
	public static void createITable() {
		table.setModel(msql.getIRepaymentTable());
	}
	
	public static void createGTable() {
		table.setModel(msql.getGRepaymentTable());
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
		panel.setLayout(new MigLayout("", "[][][][][166.00][][][][][][][][][][][][][][35.00][38.00][]", "[]"));
		
		JLabel label = new JLabel("Search With");
		panel.add(label, "cell 0 0");
		
		JRadioButton radioButton = new JRadioButton("Loan Request ID");
		radioGroup.add(radioButton);
		radioButton.setBackground(Color.LIGHT_GRAY);
		panel.add(radioButton, "cell 1 0");
		
		rdoType = new JRadioButton("Client ID");
		radioGroup.add(rdoType);
		rdoType.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoType, "cell 2 0");
		
//		JRadioButton radioButton_2 = new JRadioButton("Group ID");
//		radioGroup.add(radioButton_2);
//		radioButton_2.setBackground(Color.LIGHT_GRAY);
//		panel.add(radioButton_2, "cell 3 0");
		
		textField = new JTextField();
		panel.add(textField, "cell 4 0,growx");
		textField.setColumns(10);
		
		JButton button = new JButton("Search");
		panel.add(button, "cell 5 0");
		
		rdoIndividual = new JRadioButton("Individual");
		rdoIndividual.setSelected(true);
		rdoIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdoType.setText("Client ID");
				createITable();
			}
		});
		rdoIndividual.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoIndividual, "cell 12 0");
		radioGroup2.add(rdoIndividual);
		
		rdoGroup = new JRadioButton("Group");
		rdoGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdoType.setText("Group ID");
				createGTable();
			}
		});
		rdoGroup.setBackground(Color.LIGHT_GRAY);
		panel.add(rdoGroup, "cell 13 0");
		radioGroup2.add(rdoGroup);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoIndividual.isSelected()) {
					createITable();
				}
				else if(rdoGroup.isSelected()) {
					createGTable();
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
		panel.add(btnDetails, "cell 18 0");
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password  = "";
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Payment to Delete!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String LoanID = (String) table.getValueAt(table.getSelectedRow(),0);
					while(password.isEmpty()){
						password = JOptionPane.showInputDialog(null, "Please Type Password To Delete!", "Warning!", JOptionPane.YES_NO_OPTION);
					}
					if (password.equals("password")) {
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
						
					}					
				}
			}
		});
		panel.add(btnDelete, "cell 19 0");
		panel.add(btnRefresh, "cell 20 0");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
