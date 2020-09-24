package entryForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import database.MyQueries;
import database.UQueries;
import main.LoanPanel;
import net.miginfocom.swing.MigLayout;
import tool.Calculation;
import tool.MyString;
import tool.Select;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Panel;

public class ViewDetails extends JFrame {

	private JPanel contentPane;
	private JTextField textCID;
	private JTextField textPName;
	private JTextField textPNRC;
	private JTextField textPAddress;
	private JTextField textPPhone;
	private JTextField textPDOB;
	private JCheckBox checkPHome;
	private JTextField textPJob;
	private JTextField textPSalary;
	private JTextField txtGroupID;
	private JTextField txtLID;
	private JTextField txtM1ID;
	private JTextField txtM2ID;
	private JTextField txtM3ID;
	private JTextField txtM4ID;
	private JTextField txtM4Name;
	private JTextField txtM3Name;
	private JTextField txtM2Name;
	private JTextField txtM1Name;
	private JTextField txtLName;
	private JPanel CPanel;
	private JPanel GPanel;
	private JTextField textID;
	private JTextField textDate;
	private JTextField textAmount;
	private JLabel lblRate;
	private String Rate;
	private String Fees;
	private JLabel lblFees;
	private JLabel lblKyats;
	private JTextField textDuration;
	private TextArea textRemark;
	private String LoanRequestID, ID, Amount, Duration;
	private JButton btnAccept,btnDecline;
	
	MyQueries msql = new MyQueries();
	static UQueries usql = new UQueries();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDetails frame = new ViewDetails(null,null,null,null,null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param duration 
	 * @param amount 
	 * @param groupID 
	 * @param loanRequestID 
	 * @param Remark 
	 */
	public ViewDetails(String form, String loanRequestID, String groupID, String amount, String duration, String Remark) {
		Initialize();
		if (form.equals("Group")) {
			GPanel.setVisible(true);
			CPanel.setVisible(false);
			LoanRequestID = loanRequestID;
			ID = groupID;
			Amount = amount;
			Duration = duration;
			GroupSetData();
			if(Remark != null) {
				textRemark.setText(Remark);
				textRemark.setEditable(false);
				btnAccept.setText("Inform");
				btnDecline.setText("Cancle");
			}
		}
		
		else if (form.equals("Individual")) {
			GPanel.setVisible(false);
			CPanel.setVisible(true);
			textID.setText(loanRequestID);
			LoanRequestID = loanRequestID;
			ID = groupID;
			Amount = amount;
			Duration = duration;
			ClientSetData();
			if(Remark != null) {
				textRemark.setText(Remark);
				textRemark.setEditable(false);
				btnAccept.setText("Inform");
				btnDecline.setText("Cancle");
			}
		}
		
	}
	
	public boolean check() {
		if(textRemark.getText()=="") {
				JOptionPane.showMessageDialog(null, "Please fill a Remark!","Error!",JOptionPane.INFORMATION_MESSAGE);
				return false;
		}
		else {
			return true;
		}
	}
	
	public void ClientSetData() {
		String[] ClientDetails = msql.getClientDetailsFormID(ID);
		String[] LoanDetails = msql.GetLoanRequestData(LoanRequestID);
		String[] Date = msql.GetLoanRequestedDate("Individual",LoanRequestID);
		textDate.setText(Date[2]);
		textID.setText(LoanRequestID);
		lblRate.setText(LoanDetails[3]+" %");
		textCID.setText(ID);
		textPName.setText(ClientDetails[1]);
		textPNRC.setText(ClientDetails[2]);
		textPAddress.setText(ClientDetails[3]);
		textPPhone.setText(ClientDetails[4]);
		textPDOB.setText(ClientDetails[5]);
		textPJob.setText(ClientDetails[7]);
		textPSalary.setText(ClientDetails[8]);
		String home = ClientDetails[6];
		if (home.equals("1")) {
			checkPHome.setSelected(true);
		}
		else {
			checkPHome.setSelected(false);
		}
		textAmount.setText(Amount);
		textDuration.setText(Duration);;
	}
	
	public void GroupSetData() {
		String[] GroupDetails = msql.getGroupDetailsFormID(ID);
		String[] LoanDetails = msql.GetLoanRequestData(LoanRequestID);
		String[] Date = msql.GetLoanRequestedDate("Group",LoanRequestID);
		textDate.setText(Date[2]);
		textID.setText(LoanRequestID);
		lblRate.setText(LoanDetails[3]+" %");
		txtGroupID.setText(GroupDetails[0]);
		txtLName.setText(GroupDetails[6]);
		txtM1Name.setText(GroupDetails[7]);
		txtM2Name.setText(GroupDetails[8]);
		txtM3Name.setText(GroupDetails[9]);
		txtM4Name.setText(GroupDetails[10]);
		txtLID.setText(GroupDetails[1]);
		txtM1ID.setText(GroupDetails[2]);
		txtM2ID.setText(GroupDetails[3]);
		txtM3ID.setText(GroupDetails[4]);
		txtM4ID.setText(GroupDetails[5]);
		textAmount.setText(Amount);
		textDuration.setText(Duration);
	}

	public void ViewClientDetails(String Ctype){
		if(Ctype == "Leader") {
			new ClientEntry(txtLID.getText(),"View").setVisible(true);
		}
		else if(Ctype == "M1") {
			new ClientEntry(txtM1ID.getText(),"View").setVisible(true);
		}
		else if(Ctype == "M2") {
			new ClientEntry(txtM2ID.getText(),"View").setVisible(true);
		}
		else if(Ctype == "M3") {
			new ClientEntry(txtM3ID.getText(),"View").setVisible(true);
		}
		else if(Ctype == "M4") {
			new ClientEntry(txtM4ID.getText(),"View").setVisible(true);
		}
	}
	
	public void Initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 10, 579, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 0, 1293, 664);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 536, 40);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[70.00px][126.00px][130.00,grow][40px][135.00px]", "[26px]"));
		
		JLabel lblLoanRequestId = new JLabel("Loan Request ID:");
		lblLoanRequestId.setBackground(Color.RED);
		lblLoanRequestId.setForeground(Color.BLACK);
		panel_2.add(lblLoanRequestId, "cell 0 0,alignx trailing,aligny center");
		
		textID = new JTextField();
		textID.setEditable(false);
		panel_2.add(textID, "cell 1 0,growx");
		textID.setColumns(10);
		
		JLabel lblRequestDate = new JLabel("Request Date :");
		panel_2.add(lblRequestDate, "cell 3 0,alignx trailing,growy");
		
		textDate = new JTextField();
		textDate.setEditable(false);
		panel_2.add(textDate, "cell 4 0,growx");
		textDate.setColumns(10);
		
		CPanel = new JPanel();
		CPanel.setBounds(10, 61, 536, 298);
		CPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CLIENT INFROMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(CPanel);
		CPanel.setLayout(new MigLayout("", "[][68px][27.00][40px][4px][4px][4px][50px][8px][42px][8px][31px][8px][97px]", "[][30][20px][20px][20px][50][20px][20][20px][20px][]"));
		
		JLabel lblNewLabel_5 = new JLabel("CLIENT ID");
		CPanel.add(lblNewLabel_5, "cell 1 1,growx,aligny center");
		
		textCID = new JTextField();
		textCID.setEditable(false);
		CPanel.add(textCID, "cell 3 1 6 1,growx,aligny center");
		textCID.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		CPanel.add(lblName, "cell 1 2,growx,aligny center");
		
		textPName = new JTextField();
		textPName.setEditable(false);
		textPName.setColumns(10);
		CPanel.add(textPName, "cell 3 2 6 1,growx,aligny top");
		
		JLabel label_1 = new JLabel("NRC");
		CPanel.add(label_1, "cell 1 3,alignx left,aligny center");
		
		textPNRC = new JTextField();
		textPNRC.setEditable(false);
		textPNRC.setColumns(10);
		CPanel.add(textPNRC, "cell 3 3 6 1,growx,aligny top");
		
		JLabel lblNewLabel = new JLabel("Age");
		CPanel.add(lblNewLabel, "cell 1 4,alignx left,aligny center");
		
		textPDOB = new JTextField();
		textPDOB.setEditable(false);
		CPanel.add(textPDOB, "cell 3 4 4 1,growx,aligny top");
		textPDOB.setColumns(10);
		
		JLabel label = new JLabel("ADDRESS");
		CPanel.add(label, "cell 1 5,alignx leading,aligny top");
		
		textPAddress = new JTextField();
		textPAddress.setEditable(false);
		textPAddress.setColumns(10);
		CPanel.add(textPAddress, "cell 3 5 11 1,grow");
		
		JLabel label_4 = new JLabel("PHONE NO");
		CPanel.add(label_4, "cell 1 6,alignx left,aligny center");
		
		textPPhone = new JTextField();
		textPPhone.setEditable(false);
		textPPhone.setColumns(10);
		CPanel.add(textPPhone, "cell 3 6 7 1,growx,aligny top");
		
		JLabel label_9 = new JLabel("Home");
		CPanel.add(label_9, "cell 1 7,alignx left,aligny center");
		
		checkPHome = new JCheckBox("Owned");
		CPanel.add(checkPHome, "cell 3 7 5 1,alignx left,aligny top");
		checkPHome.setEnabled(false);
		
		JLabel label_10 = new JLabel("Job");
		CPanel.add(label_10, "cell 1 8,alignx left,aligny center");
		
		textPJob = new JTextField();
		textPJob.setEditable(false);
		CPanel.add(textPJob, "cell 3 8 7 1,growx,aligny top");
		textPJob.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Salary");
		CPanel.add(lblNewLabel_1, "cell 1 9,alignx left,aligny center");
		
		textPSalary = new JTextField();
		textPSalary.setEditable(false);
		CPanel.add(textPSalary, "cell 3 9 5 1,alignx left,aligny top");
		textPSalary.setColumns(10);
//////////////////////////
		GPanel = new JPanel();
		GPanel.setBackground(Color.WHITE);
		GPanel.setBorder(new TitledBorder(null, "GROUP INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GPanel.setBounds(10, 61, 536, 297);
		panel.add(GPanel);
		GPanel.setLayout(new MigLayout("", "[20:20:20][61px][166.00px][160.00px][]", "[30][30][30][30][30][30][30][::10][]"));
		
		JLabel lbl = new JLabel("Group ID");
		GPanel.add(lbl, "cell 1 0,alignx left");
		
		txtGroupID = new JTextField();
		txtGroupID.setFont(new Font("Century", Font.PLAIN, 14));
		txtGroupID.setEditable(false);
		GPanel.add(txtGroupID, "cell 2 0,growx,aligny center");
		txtGroupID.setColumns(10);
		
		Label lbl1 = new Label("Leader");
		GPanel.add(lbl1, "cell 1 2,grow");
		
		Label ll2 = new Label("Name");
		GPanel.add(ll2, "cell 2 1,alignx center,aligny top");
		
		txtLID = new JTextField();
		txtLID.setColumns(10);
		txtLID.setForeground(Color.BLACK);
		txtLID.setFont(new Font("Century", Font.PLAIN, 14));
		txtLID.setEditable(false);
		GPanel.add(txtLID, "cell 3 2,grow");
		
		Label label_2 = new Label("Client ID");
		GPanel.add(label_2, "cell 3 1,alignx center,aligny top");
		
		Button button = new Button("View");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewClientDetails("Leader");
			}
		});
		GPanel.add(button, "cell 4 2");
		
		txtM1ID = new JTextField();
		txtM1ID.setColumns(10);
		txtM1ID.setForeground(Color.BLACK);
		txtM1ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM1ID.setEditable(false);
		GPanel.add(txtM1ID, "cell 3 3,grow");
		
		Button button_1 = new Button("View");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewClientDetails("M1");
			}
		});
		GPanel.add(button_1, "cell 4 3");
		
		txtM2ID = new JTextField();
		txtM2ID.setColumns(10);
		txtM2ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM2ID.setEditable(false);
		GPanel.add(txtM2ID, "cell 3 4,grow");
		
		Button button_2 = new Button("View");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewClientDetails("M2");
			}
		});
		GPanel.add(button_2, "cell 4 4");
		
		txtM3ID = new JTextField();
		txtM3ID.setColumns(10);
		txtM3ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM3ID.setEditable(false);
		GPanel.add(txtM3ID, "cell 3 5,grow");
		
		Button button_3 = new Button("View");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewClientDetails("M3");
			}
		});
		GPanel.add(button_3, "cell 4 5");
		
		txtM4ID = new JTextField();
		txtM4ID.setColumns(10);
		txtM4ID.setFont(new Font("Century", Font.PLAIN, 14));
		txtM4ID.setEditable(false);
		GPanel.add(txtM4ID, "cell 3 6,grow");
		
		txtM4Name = new JTextField();
		txtM4Name.setColumns(10);
		txtM4Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM4Name.setEditable(false);
		GPanel.add(txtM4Name, "cell 2 6,grow");
		
		txtM3Name = new JTextField();
		txtM3Name.setColumns(10);
		txtM3Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM3Name.setEditable(false);
		GPanel.add(txtM3Name, "cell 2 5,grow");
		
		txtM2Name = new JTextField();
		txtM2Name.setColumns(10);
		txtM2Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM2Name.setEditable(false);
		GPanel.add(txtM2Name, "cell 2 4,grow");
		
		txtM1Name = new JTextField();
		txtM1Name.setColumns(10);
		txtM1Name.setFont(new Font("Century", Font.PLAIN, 14));
		txtM1Name.setEditable(false);
		GPanel.add(txtM1Name, "cell 2 3,grow");
		
		txtLName = new JTextField();
		txtLName.setColumns(10);
		txtLName.setFont(new Font("Century", Font.PLAIN, 14));
		txtLName.setEditable(false);
		GPanel.add(txtLName, "cell 2 2,grow");
		
		Label lbl3 = new Label("Member 1");
		GPanel.add(lbl3, "cell 1 3,alignx left,growy");
		
		Label label_6 = new Label("Member 2");
		GPanel.add(label_6, "cell 1 4,alignx left,growy");
		
		Label label_7 = new Label("Member 3");
		GPanel.add(label_7, "cell 1 5,alignx left,growy");
		
		Label label_8 = new Label("Member 4");
		GPanel.add(label_8, "cell 1 6,alignx left,growy");
		
		Button button_4 = new Button("View");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ViewClientDetails("M4");
			}
		});
		GPanel.add(button_4, "cell 4 6");
/////////////////////////////////////////////
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 364, 536, 108);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LOAN INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[21.00][65px][81.00px][20.00px][77px][][][]", "[20px][14px][][]"));

		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		panel_4.add(lblNewLabel_2, "cell 1 0 1 2,alignx left");
		
		textAmount = new JTextField();
		textAmount.setEditable(false);
		textAmount.setForeground(Color.black);
		textAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		textAmount.setColumns(10);
		panel_4.add(textAmount, "cell 2 0 2 2,growx");
		
		lblKyats = new JLabel("Kyats");
		panel_4.add(lblKyats, "cell 4 0 1 2");
		
		label = new JLabel("Duration");
		panel_4.add(label, "cell 5 0 1 2,alignx trailing");
		
		textDuration = new JTextField();
		textDuration.setEditable(false);
		textDuration.setColumns(10);
		panel_4.add(textDuration, "cell 6 0 1 2,growx");
		
		label_1 = new JLabel("Month");
		panel_4.add(label_1, "cell 7 0 1 2");
		
		JLabel lblNewLabel_4 = new JLabel("Interest Rate");
		panel_4.add(lblNewLabel_4, "cell 1 2");
		
		
		lblRate = new JLabel(Rate + "%");
		lblRate.setForeground(Color.BLUE);
		panel_4.add(lblRate, "cell 2 2");
		
		JLabel label_25 = new JLabel("Service Fees");
		panel_4.add(label_25, "cell 1 3");
		
		lblFees = new JLabel(Fees + "%");
		lblFees.setForeground(Color.BLUE);
		panel_4.add(lblFees, "cell 2 3");
		
		textRemark = new TextArea();
		textRemark.setBounds(10, 489, 536, 154);
		panel.add(textRemark);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(10, 649, 533, 33);
		panel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow][][][]", "[]"));
		
		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if(btnAccept.getText()=="Accept") {
				String[] Approve = new String[2];
				Approve[0] = textID.getText();
				Approve[1] = "1";
				boolean update = msql.UpdateData("loanrequest", Approve);
				if (update) {
					JOptionPane.showMessageDialog(null, "New Loan Request is Approved Sucessfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else if(!update) {
					JOptionPane.showMessageDialog(null, "Failed to Approve Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			else if(btnAccept.getText()=="Inform") {
				if(CPanel.isVisible()) {
				boolean delete = msql.DeleteData("loanrequest1",textID.getText());
				if(delete) {
					JOptionPane.showMessageDialog(null, "A loan Request is Deleted Sucessfully","Success!",JOptionPane.INFORMATION_MESSAGE);
						LoanPanel.createRejectedITable();
						dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Cant Delete A Request","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
			}
				else if(GPanel.isVisible()) {
					boolean delete = msql.DeleteData("loanrequest2",textID.getText());
					if(delete) {
						JOptionPane.showMessageDialog(null, "A loan Request is Deleted Sucessfully","Success!",JOptionPane.INFORMATION_MESSAGE);
						LoanPanel.createRejectedGTable();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Cant Delete A Request","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
				}	
			}
			
			}	
		});
		panel_1.add(btnAccept, "cell 1 0");
		
		btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if(btnDecline.getText()=="Decline") {
				if(check()) {
				String[] Decline = new String[3];
				Decline[0] = textID.getText();
				Decline[1] = "2";
				Decline[2] = textRemark.getText();
				boolean update = msql.UpdateData("loanrequestDecline", Decline);
				if (update) {
					JOptionPane.showMessageDialog(null, "New Loan Request is Rejected Sucessfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else if(!update) {
					JOptionPane.showMessageDialog(null, "Failed to Approve Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
				}
			}}
			
			else if(btnDecline.getText()=="Cancle") {
				dispose();
			}
				
				}
		});
		panel_1.add(btnDecline, "cell 3 0");
		
		JLabel lblNewLabel_3 = new JLabel("Remarks:");
		lblNewLabel_3.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 474, 72, 13);
		panel.add(lblNewLabel_3);
		
	}
}
