package entryForm;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.DBConnection;
import database.MyQueries;
import database.UQueries;
import main.LoanPanel;
import net.miginfocom.swing.MigLayout;
import tool.Calculation;
import tool.Checking;
import tool.MyDate;
import tool.MyString;
import tool.Select;

import java.awt.Label;
import java.awt.Choice;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.Font;

public class GroupRequestForm extends JFrame{
	private JTable table;
	private JTextField textID;

	private String Require = "* Required";
	private String Invalid = "* Invalid";
	private JTextField textDate;
	private static JTextField txtGroupID;
	public static JTextField txtLID;
	public static JTextField txtM1ID;
	public static JTextField txtM2ID;
	public static JTextField txtM3ID;
	public static JTextField txtM4ID; 
	LoanRequest loanRequest = new LoanRequest();
	MyDate myDate = new MyDate();
	static UQueries usql = new UQueries();
	private static JTextField txtM3Name;
	private static JTextField txtLName;
	private static JTextField txtM2Name;
	private static JTextField txtM1Name;
	private static JTextField txtM4Name;
	DBConnection myDbConnection = new DBConnection();
	static MyQueries msql = new MyQueries();
	DefaultTableModel dtm = new DefaultTableModel();
	private int MaxAmount,MinAmount,MinDuration,MaxDuration,AmountInterval,DurationInterval;
	private float Rate,Fees;
	private JLabel noteDuration;
	private JLabel noteAmount;
	private static JLabel noteClientError;
	private JLabel lblFees;
	private JLabel lblRate;
	private JTextField textAmount;
	private JTextField textDuration;
	private JButton btnSelect;
	private JSlider sliderAmount;
	private JSlider sliderDuration;
	private JButton btnRequestLoan;
	private JButton btnCancel;
	private String GroupID,RequestedAmount,RequestedDuration;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupRequestForm window = new GroupRequestForm(null,null);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	
	public GroupRequestForm(String LID, String GID) {
		GetILoanSetting();
		initialize();
		
		textID.setText(loanRequest.getAutoID());
		textDate.setText(myDate.getdate());		
		
//		else {
//			String[] GroupDetails = msql.getGroupDetailsFormID(GID);
//			textID.setText(LID);
//			setGroupData(GroupDetails[0],GroupDetails[6],GroupDetails[7],GroupDetails[8],GroupDetails[9],GroupDetails[10]);
//			String[] LoanRequestDetails = msql.GetLoanRequestData(LID);
//			setLoanRequestData(LoanRequestDetails[1],LoanRequestDetails[2],LoanRequestDetails[3]);
//			btnSelect.setEnabled(false);
//			}
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

	public static void setGroupData(String id,String leader,String mem1,String mem2,String mem3,String mem4) {
		txtGroupID.setText(id);
		txtLName.setText(leader);
		txtM1Name.setText(mem1);
		txtM2Name.setText(mem2);
		txtM3Name.setText(mem3);
		txtM4Name.setText(mem4);
		Vector<String> clientID = usql.getClientIDFormGroupID(id);
		txtLID.setText(clientID.get(0));
		txtM1ID.setText(clientID.get(1));
		txtM2ID.setText(clientID.get(2));
		txtM3ID.setText(clientID.get(3));
		txtM4ID.setText(clientID.get(4));
		if(msql.CheckAvaliable("Group",txtGroupID.getText())) {
			noteClientError.setText("* This Group is Already Requested");
			noteClientError.setVisible(true);
		}
	}
	
	public void setLoanRequestData(String Amount1,String Duration1,String Rate1) {
		textAmount.setText(Calculation.addcomma(Amount1));
		textDuration.setText(Duration1);
		lblRate.setText(Rate1+" %");
		lblFees.setText("Paid");
		sliderAmount.setEnabled(false);
		sliderDuration.setEnabled(false);
		btnRequestLoan.setText("Pay");
	}
	
	//Get Individual Loan Setting
	public void GetILoanSetting() {
		try {
			String[] IData = msql.GetGroupLoanSetting();
			MinAmount = Integer.parseInt(IData[0]);
			MaxAmount = Integer.parseInt(IData[1]);
			MinDuration = Integer.parseInt(IData[2]);
			MaxDuration = Integer.parseInt(IData[3]);
			AmountInterval = Integer.parseInt(IData[4]);
			DurationInterval = Integer.parseInt(IData[5]);
			Rate = Float.parseFloat(IData[6]);
			Fees = Float.parseFloat(IData[7]);
			}
			catch(NullPointerException e) {
				String[] data = new String[12];
				data[0] =  myDbConnection.getAutoID("ID", "loansetting", "Ls-");
				data[1] = "100000";
				data[2] = "1000000";
				data[3] = "6";
				data[4] = "24";
				data[5] = "100000";
				data[6] = "3";
				data[7] = "2.33";
				data[8] = "1";
				data[9] = java.time.LocalDate.now().toString();
				data[10] = "Group";
				data[11] = "OF-0000001";
				msql.InsertData("Iloansetting", data);
				GetILoanSetting();
			}
	}
	
	public void createTable() {
		if(Checking.IsNull(textAmount.getText())) {
			noteAmount.setVisible(true);
		}
		else if (Checking.IsNull(textDuration.getText())) {
			noteDuration.setVisible(true);
		}
		else {
		DefaultTableModel dtm = new DefaultTableModel(Integer.parseInt(textDuration.getText())+2,5);
		dtm = Calculation.calculator(Integer.parseInt(Calculation.removecomma(textAmount.getText())),Integer.parseInt(textDuration.getText()),Rate);
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(0).setHeaderValue("No");
		table.getColumnModel().getColumn(1).setHeaderValue("Principal Outstanding");
		table.getColumnModel().getColumn(2).setHeaderValue("Principal");
		table.getColumnModel().getColumn(3).setHeaderValue("Interest");
		table.getColumnModel().getColumn(4).setHeaderValue("Installment");
		}
	}
	
	
	public boolean check() {
		//Client ID
		if (Checking.IsNull(txtGroupID.getText())) {
			noteClientError.setText("* Required");
			noteClientError.setVisible(true);
			return false;	
	    }
		//Check Amount
		if(Checking.IsNull(textAmount.getText())) {
			noteAmount.setVisible(true);
			return false;
		}
		//Check Duration
		if (Checking.IsNull(textDuration.getText())) {
			noteDuration.setVisible(true);
			return false;
		}
		if(msql.CheckAvaliable("Group",txtGroupID.getText())) {
			noteClientError.setText("* This Group is Already Requested");
			noteClientError.setVisible(true);
			return false;	
		}
		else {
			return true;
		}
	}
	
	private void initialize() {
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setTitle("Group Registration Form");
		this.setBounds(200, 30, 1001, 730);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 0, 973, 643);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(new Rectangle(0, 0, 5, 0));
		panel_2.setBounds(10, 11, 960, 40);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[96.00px][120.00px,grow][436.00][40px][118.00px]", "[26px]"));
		
		JLabel lblGroupId = new JLabel("Loan Request ID");
		lblGroupId.setBackground(Color.RED);
		lblGroupId.setForeground(Color.BLACK);
		panel_2.add(lblGroupId, "cell 0 0,alignx trailing,growy");
		
		textID = new JTextField();
		textID.setEditable(false);
		panel_2.add(textID, "cell 1 0,growx");
		textID.setColumns(10);
		
		JLabel label_5 = new JLabel("Date :");
		panel_2.add(label_5, "cell 3 0,alignx trailing,growy");
		
		textDate = new JTextField();
		textDate.setEditable(false);
		panel_2.add(textDate, "cell 4 0,growx");
		textDate.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LOAN INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 368, 394, 265);
		panel.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[72.00][53.00px][62.00px][62.00][94.00px]", "[50][26px][30][10.00][31.00px][30][][][14px][23px][]"));
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		panel_4.add(lblNewLabel_2, "cell 0 2,alignx left,aligny center");
		
		sliderAmount = new JSlider();
		sliderAmount.setBackground(Color.WHITE);
		sliderAmount.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				noteAmount.setVisible(false);
				textAmount.setText(Calculation.addcomma(Integer.toString(sliderAmount.getValue())));
			}
		});
		sliderAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				noteAmount.setVisible(false);
				textAmount.setText(Calculation.addcomma(Integer.toString(sliderAmount.getValue())));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				noteAmount.setVisible(false);
				textAmount.setText(Calculation.addcomma(Integer.toString(sliderAmount.getValue())));
			}
		});
		sliderAmount.setSnapToTicks(true);
		sliderAmount.setPaintTicks(true);
		//sliderAmount.setPaintLabels(true);
		sliderAmount.setMinorTickSpacing(AmountInterval);
		sliderAmount.setMinimum(MinAmount);
		sliderAmount.setMaximum(MaxAmount);
		sliderAmount.setMajorTickSpacing(AmountInterval);
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer(MinAmount), new JLabel(Calculation.addcomma(Integer.toString(MinAmount))));
		labelTable.put( new Integer(MaxAmount), new JLabel(Calculation.addcomma(Integer.toString(MaxAmount))));
		sliderAmount.setLabelTable( labelTable );

		sliderAmount.setPaintLabels(true);
		panel_4.add(sliderAmount, "cell 1 1 3 1,growx,aligny center");
		
		noteAmount = new JLabel("* Require");
		noteAmount.setForeground(Color.RED);
		noteAmount.setVisible(false);
		panel_4.add(noteAmount, "cell 4 1");
		
		textAmount = new JTextField();
		textAmount.setColumns(10);
		panel_4.add(textAmount, "cell 1 2 2 1,growx");
		
		JLabel lblNewLabel_6 = new JLabel("Kyats");
		panel_4.add(lblNewLabel_6, "cell 3 2");
		
		JLabel lblNewLabel_3 = new JLabel("Duration");
		panel_4.add(lblNewLabel_3, "cell 0 5,growx,aligny center");
		
		sliderDuration = new JSlider();
		sliderDuration.setBackground(Color.WHITE);
		sliderDuration.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				noteDuration.setVisible(false);
				textDuration.setText(Integer.toString(sliderDuration.getValue()));
			}
		});
		sliderDuration.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				noteDuration.setVisible(false);
				textDuration.setText(Integer.toString(sliderDuration.getValue()));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				noteDuration.setVisible(false);
				textDuration.setText(Integer.toString(sliderDuration.getValue()));
			}
		});
		sliderDuration.setSnapToTicks(true);
		sliderDuration.setPaintTicks(true);
		sliderDuration.setPaintLabels(true);
		sliderDuration.setMinorTickSpacing(DurationInterval);
		sliderDuration.setMinimum(MinDuration);
		sliderDuration.setMaximum(MaxDuration);
		sliderDuration.setMajorTickSpacing(DurationInterval);
		panel_4.add(sliderDuration, "cell 1 4 3 1,growx,aligny center");
		
		noteDuration = new JLabel("* Require");
		noteDuration.setForeground(Color.RED);
		noteDuration.setVisible(false);
		panel_4.add(noteDuration, "cell 4 4");
		
		textDuration = new JTextField();
		textDuration.setColumns(10);
		panel_4.add(textDuration, "cell 1 5 2 1,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Month");
		panel_4.add(lblNewLabel_1, "cell 3 5");
		
		JLabel lblNewLabel_5 = new JLabel("Interest Rate");
		panel_4.add(lblNewLabel_5, "cell 0 6");
		
		lblRate = new JLabel(Rate + "%");
		lblRate.setForeground(Color.BLUE);
		panel_4.add(lblRate, "cell 1 6");
		
		JLabel lblNewLabel_4 = new JLabel("Service Fees");
		panel_4.add(lblNewLabel_4, "cell 0 8,alignx left,aligny top");
		
		lblFees = new JLabel(Fees + "%");
		lblFees.setForeground(Color.BLUE);
		panel_4.add(lblFees, "cell 1 8,alignx left,aligny top");
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTable();
			}
		});
		panel_4.add(btnCalculate, "cell 3 10 2 1,alignx right,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(414, 60, 556, 573);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "GROUP INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 61, 394, 297);
		panel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[61px][151px,grow][157px]", "[30][30][30][30][30][30][30][::10][]"));
		
		JLabel lblNewLabel = new JLabel("Group ID");
		panel_1.add(lblNewLabel, "cell 0 0,alignx left");
		
		txtGroupID = new JTextField();
		txtGroupID.setFont(new Font("Century", Font.PLAIN, 14));
		txtGroupID.setEditable(false);
		panel_1.add(txtGroupID, "cell 1 0,growx,aligny center");
		txtGroupID.setColumns(10);
		
		Label label = new Label("Leader");
		panel_1.add(label, "cell 0 2,grow");
		
		Label label_1 = new Label("Name");
		panel_1.add(label_1, "cell 1 1,alignx center,aligny top");
		
		txtLID = new JTextField();
		txtLID.setColumns(10);
		txtLID.setForeground(Color.BLACK);
		txtLID.setFont(new Font("Century", Font.PLAIN, 14));
		txtLID.setEditable(false);
		panel_1.add(txtLID, "cell 2 2,grow");
		
		Label label_2 = new Label("Client ID");
		panel_1.add(label_2, "cell 2 1,alignx center,aligny top");
		
		txtM1ID = new JTextField();
		txtM1ID.setColumns(10);
		txtM1ID.setForeground(Color.BLACK);
		txtM1ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM1ID.setEditable(false);
		panel_1.add(txtM1ID, "cell 2 3,grow");
		
		txtM2ID = new JTextField();
		txtM2ID.setColumns(10);
		txtM2ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM2ID.setEditable(false);
		panel_1.add(txtM2ID, "cell 2 4,grow");
		
		txtM3ID = new JTextField();
		txtM3ID.setColumns(10);
		txtM3ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM3ID.setEditable(false);
		panel_1.add(txtM3ID, "cell 2 5,grow");
		
		txtM4ID = new JTextField();
		txtM4ID.setColumns(10);
		txtM4ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM4ID.setEditable(false);
		panel_1.add(txtM4ID, "cell 2 6,grow");
		
		txtM4Name = new JTextField();
		txtM4Name.setColumns(10);
		txtM4Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM4Name.setEditable(false);
		panel_1.add(txtM4Name, "cell 1 6,grow");
		
		txtM3Name = new JTextField();
		txtM3Name.setColumns(10);
		txtM3Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM3Name.setEditable(false);
		panel_1.add(txtM3Name, "cell 1 5,grow");
		
		txtM2Name = new JTextField();
		txtM2Name.setColumns(10);
		txtM2Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM2Name.setEditable(false);
		panel_1.add(txtM2Name, "cell 1 4,grow");
		
		txtM1Name = new JTextField();
		txtM1Name.setColumns(10);
		txtM1Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM1Name.setEditable(false);
		panel_1.add(txtM1Name, "cell 1 3,grow");
		
		txtLName = new JTextField();
		txtLName.setColumns(10);
		txtLName.setFont(new Font("Century", Font.PLAIN, 14));
		txtLName.setEditable(false);
		panel_1.add(txtLName, "cell 1 2,grow");
		
		Label label_4 = new Label("Member 1");
		panel_1.add(label_4, "cell 0 3,alignx left,growy");
		
		Label label_6 = new Label("Member 2");
		panel_1.add(label_6, "cell 0 4,alignx left,growy");
		
		Label label_7 = new Label("Member 3");
		panel_1.add(label_7, "cell 0 5,alignx left,growy");
		
		Label label_8 = new Label("Member 4");
		panel_1.add(label_8, "cell 0 6,alignx left,growy");
		
		btnSelect = new JButton("Select Group");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Select select = new Select(MyString.Group, MyString.GroupRequestForm);
				select.setVisible(true);
				noteClientError.setVisible(false);
			}
		});
		
		noteClientError = new JLabel("New label");
		noteClientError.setForeground(Color.RED);
		noteClientError.setVisible(false);
		panel_1.add(noteClientError, "cell 0 8 2 1");
		panel_1.add(btnSelect, "cell 2 8,alignx right");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnCancel.getText() == "Cancel") {
					dispose();
					}
					if(btnCancel.getText() == "Reject") {
						
					}
			}
		});
		btnCancel.setBounds(894, 653, 89, 23);
		this.getContentPane().add(btnCancel);
		
		btnRequestLoan = new JButton("Request Loan");
		btnRequestLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if(btnRequestLoan.getText()=="Request Loan") {
				boolean check = check();
				if(check) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure want to Request a Group Loan!", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						String[] LoanRequest = new String[5];
						LoanRequest[0] = textID.getText();
						LoanRequest[1] = "Group";
						LoanRequest[2] = Calculation.removecomma(textAmount.getText());
						LoanRequest[3] = textDuration.getText();
						LoanRequest[4] = lblRate.getText().replace("%", "");
						boolean insert = msql.InsertData("loanrequest", LoanRequest);
						
						String[] GroupDetails = new String[3];
						GroupDetails[0] = txtGroupID.getText();
						GroupDetails[1] = textID.getText();
						GroupDetails[2] = textDate.getText();
						boolean insert2 = msql.InsertData("groupdetails", GroupDetails);
						if (insert && insert2) {
							JOptionPane.showMessageDialog(null, "Saved Successfully!","New Loan Request Saved",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						else if (!insert){
							JOptionPane.showMessageDialog(null, "Failed to Save Loan New Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
						}
						else if (!insert2){
							JOptionPane.showMessageDialog(null, "Failed to Save Loan New Request2!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
//			else if(btnRequestLoan.getText()=="Pay") {
//				if (JOptionPane.showConfirmDialog(null, "Are you sure want to Pay!", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
//					PaidDay(textID.getText(),textAmount.getText());
//					LoanPanel.createGTable();
//					dispose();
//				}
//			}
				}
		});
		btnRequestLoan.setBounds(762, 653, 122, 23);
		this.getContentPane().add(btnRequestLoan);
	}
}
