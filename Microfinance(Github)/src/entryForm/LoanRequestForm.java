package entryForm;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Rectangle;
import net.miginfocom.swing.MigLayout;
import tool.MyDate;
import tool.MyString;
import tool.Select;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.Choice;

public class LoanRequestForm extends JFrame {

	private static JTextField textPName;
	private static JTextField textPNRC;
	private static JTextField textPDOB;
	private static JTextField textPAddress;
	private static JCheckBox checkPHome;
	private static JTextField textPPhone;
	private static JTextField textPJob;
	private static JTextField textPSalary;
	private JTextField textGName;
	private JTextField textGNRC;
	private JTextField textGAddress;
	private JTextField textGCity;
	private JTextField textGState;
	private JTextField textGPhone;
	private JTextField textGJob;
	private JTextField textGSalary;
	private JTextField LoanAmount;
	private JTextField LoanDuration;
	private JTable table;
	private JTextField textID;
	private JTextField textDate;
	private static JTextField textCID;
	LoanRequest loanRequest = new LoanRequest();
	MyDate myDate = new MyDate();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanRequestForm window = new LoanRequestForm();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoanRequestForm() {
		initialize();
		textID.setText(loanRequest.getAutoID());
		textDate.setText(myDate.getdate());
	}

	public static void setClientData(String id,String name,String NRC,String address,String phno,String DOB,String home,String job,String salary) {
		textCID.setText(id);
		textPName.setText(name);
		textPNRC.setText(NRC);
		textPAddress.setText(address);
		textPPhone.setText(phno);
		textPDOB.setText(DOB);
		textPJob.setText(job);
		textPSalary.setText(salary);
		if (home.equals("1")) {
			checkPHome.setSelected(true);
		}
		else {
			checkPHome.setSelected(false);
		}
		
	}
	private void initialize() {
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setTitle("Loan Request Form");
		this.setBounds(260, 30, 857, 683);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 0, 821, 606);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PERSONAL INFROMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 56, 394, 316);
		panel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[][68px][27.00][40px,grow][4px][4px][4px][50px][8px][42px][8px][31px][8px][97px]", "[][30][20px][20px][20px][50][20px][20][20px][20px][]"));
		
		JLabel lblNewLabel_5 = new JLabel("CUSTOMER ID");
		panel_1.add(lblNewLabel_5, "cell 1 1,growx,aligny center");
		
		textCID = new JTextField();
		textCID.setEditable(false);
		panel_1.add(textCID, "cell 3 1 8 1,growx,aligny top");
		textCID.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		panel_1.add(lblName, "cell 1 2,growx,aligny center");
		
		textPName = new JTextField();
		textPName.setEditable(false);
		textPName.setColumns(10);
		panel_1.add(textPName, "cell 3 2 9 1,growx,aligny top");
		
		JLabel label_1 = new JLabel("NRC");
		panel_1.add(label_1, "cell 1 3,alignx left,aligny center");
		
		textPNRC = new JTextField();
		textPNRC.setEditable(false);
		textPNRC.setColumns(10);
		panel_1.add(textPNRC, "cell 3 3 7 1,growx,aligny top");
		
		JLabel lblNewLabel = new JLabel("Date Of Birth");
		panel_1.add(lblNewLabel, "cell 1 4,alignx left,aligny center");
		
		textPDOB = new JTextField();
		textPDOB.setEditable(false);
		panel_1.add(textPDOB, "cell 3 4 7 1,growx,aligny top");
		textPDOB.setColumns(10);
		
		JLabel label = new JLabel("ADDRESS");
		panel_1.add(label, "cell 1 5,alignx leading,aligny top");
		
		textPAddress = new JTextField();
		textPAddress.setEditable(false);
		textPAddress.setColumns(10);
		panel_1.add(textPAddress, "cell 3 5 11 1,grow");
		
		JLabel label_4 = new JLabel("PHONE NO");
		panel_1.add(label_4, "cell 1 6,alignx left,aligny center");
		
		textPPhone = new JTextField();
		textPPhone.setEditable(false);
		textPPhone.setColumns(10);
		panel_1.add(textPPhone, "cell 3 6 7 1,growx,aligny top");
		
		JLabel label_9 = new JLabel("Home");
		panel_1.add(label_9, "cell 1 7,alignx left,aligny center");
		
		checkPHome = new JCheckBox("Owned");
		panel_1.add(checkPHome, "cell 3 7 5 1,alignx left,aligny top");
		
		JLabel label_10 = new JLabel("Job");
		panel_1.add(label_10, "cell 1 8,alignx left,aligny center");
		
		textPJob = new JTextField();
		textPJob.setEditable(false);
		panel_1.add(textPJob, "cell 3 8 7 1,growx,aligny top");
		textPJob.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Salary");
		panel_1.add(lblNewLabel_1, "cell 1 9,alignx left,aligny center");
		
		textPSalary = new JTextField();
		textPSalary.setEditable(false);
		panel_1.add(textPSalary, "cell 3 9 5 1,alignx left,aligny top");
		textPSalary.setColumns(10);
		
		JButton btnNewButton = new JButton("Select Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Select select = new Select(MyString.One,MyString.LoanRequestForm);
				select.setVisible(true);
			}
		});
		panel_1.add(btnNewButton, "cell 13 10");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(new Rectangle(0, 0, 5, 0));
		panel_2.setBounds(10, 11, 811, 40);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[70.00px][113.00px,grow][464.00][40px][135.00px]", "[26px]"));
		
		JLabel lblLoanRequestId = new JLabel("Loan Request ID:");
		lblLoanRequestId.setBackground(Color.RED);
		lblLoanRequestId.setForeground(Color.BLACK);
		panel_2.add(lblLoanRequestId, "cell 0 0,alignx trailing,aligny center");
		
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "GUARANTOR INFROMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 383, 394, 215);
		panel.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[68px][40px][4px][4px][4px][50px][8px][42px][8px][31px][8px][97px][97px]", "[30,fill][30,fill][30,fill][30,fill][30,fill][30,fill][30,fill]"));
		
		JLabel label_11 = new JLabel("NAME");
		panel_3.add(label_11, "cell 0 0,growx,aligny center");
		
		textGName = new JTextField();
		textGName.setColumns(10);
		panel_3.add(textGName, "cell 1 0 9 1,grow");
		
		JLabel lblNewLabel_6 = new JLabel("* Require");
		lblNewLabel_6.setForeground(Color.RED);
		panel_3.add(lblNewLabel_6, "cell 11 0 2 1");
		
		JLabel label_12 = new JLabel("NRC");
		panel_3.add(label_12, "cell 0 1,alignx left,aligny center");
		
		JLabel label_13 = new JLabel("/");
		panel_3.add(label_13, "cell 3 1,alignx left,growy");
		
		Choice boxGNRC2 = new Choice();
		panel_3.add(boxGNRC2, "cell 5 1 2 1,growx,aligny top");
		
		textGNRC = new JTextField();
		textGNRC.setColumns(10);
		panel_3.add(textGNRC, "flowx,cell 9 1 4 1,alignx left,aligny top");
		
		JLabel label_15 = new JLabel("ADDRESS");
		panel_3.add(label_15, "cell 0 2,alignx left,aligny center");
		
		textGAddress = new JTextField();
		textGAddress.setColumns(10);
		panel_3.add(textGAddress, "cell 1 2 11 1,growx,aligny top");
		
		JLabel label_19 = new JLabel("* Require");
		label_19.setForeground(Color.RED);
		panel_3.add(label_19, "cell 12 2");
		
		textGCity = new JTextField();
		textGCity.setColumns(10);
		panel_3.add(textGCity, "cell 1 3 5 1,growx,aligny top");
		
		JLabel label_22 = new JLabel("* Require");
		label_22.setForeground(Color.RED);
		panel_3.add(label_22, "cell 7 3");
		
		JLabel label_16 = new JLabel("STATE");
		panel_3.add(label_16, "cell 9 3,alignx left,aligny center");
		
		textGState = new JTextField();
		textGState.setColumns(10);
		panel_3.add(textGState, "cell 11 3,growx,aligny top");
		
		JLabel label_14 = new JLabel("* Require");
		label_14.setForeground(Color.RED);
		panel_3.add(label_14, "cell 12 3");
		
		JLabel label_17 = new JLabel("PHONE NO");
		panel_3.add(label_17, "cell 0 4,alignx left,aligny center");
		
		textGPhone = new JTextField();
		textGPhone.setColumns(10);
		panel_3.add(textGPhone, "cell 1 4 7 1,growx,aligny top");
		
		JLabel label_18 = new JLabel("CITY");
		panel_3.add(label_18, "cell 0 3,alignx left,aligny center");
		
		JLabel label_2 = new JLabel("* Require");
		label_2.setForeground(Color.RED);
		panel_3.add(label_2, "cell 9 4");
		
		JLabel label_20 = new JLabel("Job");
		panel_3.add(label_20, "cell 0 5,alignx left,aligny center");
		
		textGJob = new JTextField();
		textGJob.setColumns(10);
		panel_3.add(textGJob, "cell 1 5 7 1,growx,aligny top");
		
		JLabel label_6 = new JLabel("* Require");
		label_6.setForeground(Color.RED);
		panel_3.add(label_6, "cell 9 5");
		
		JLabel label_21 = new JLabel("Salary");
		panel_3.add(label_21, "cell 0 6,alignx left,aligny center");
		
		textGSalary = new JTextField();
		textGSalary.setColumns(10);
		panel_3.add(textGSalary, "cell 1 6 5 1,alignx left,aligny top");
		
		Choice boxGNRC1 = new Choice();
		panel_3.add(boxGNRC1, "cell 1 1,growx,aligny top");
		
		Choice boxGNRC3 = new Choice();
		panel_3.add(boxGNRC3, "cell 7 1,growx,aligny top");
		
		JLabel label_7 = new JLabel("* Require");
		label_7.setForeground(Color.RED);
		panel_3.add(label_7, "cell 7 6");
		
		JLabel label_8 = new JLabel("* Require");
		label_8.setForeground(Color.RED);
		panel_3.add(label_8, "cell 12 1");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LOAN INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(414, 56, 407, 194);
		panel.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[65px][81.00px][159px][77px]", "[26px][20px][14px][][23px]"));
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		panel_4.add(lblNewLabel_2, "cell 0 0,alignx left,aligny center");
		
		LoanAmount = new JTextField();
		panel_4.add(LoanAmount, "cell 1 0,growx,aligny center");
		LoanAmount.setColumns(10);
		
		JSlider slider_1 = new JSlider();
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setMinorTickSpacing(3);
		slider_1.setMinimum(6);
		slider_1.setMaximum(24);
		slider_1.setMajorTickSpacing(3);
		slider_1.setSnapToTicks(true);
		panel_4.add(slider_1, "cell 2 0,alignx center,aligny center");
		
		JLabel label_23 = new JLabel("* Require");
		label_23.setForeground(Color.RED);
		panel_4.add(label_23, "cell 3 0");
		
		JLabel lblNewLabel_3 = new JLabel("Duration");
		panel_4.add(lblNewLabel_3, "cell 0 1,growx,aligny center");
		
		LoanDuration = new JTextField();
		panel_4.add(LoanDuration, "cell 1 1,growx,aligny center");
		LoanDuration.setColumns(10);
		
		JSlider slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(3);
		slider.setMinimum(6);
		slider.setMaximum(24);
		slider.setMajorTickSpacing(3);
		slider.setMinorTickSpacing(3);
		slider.setSnapToTicks(true);
		panel_4.add(slider, "cell 2 1,alignx center,aligny center");
		
		JLabel label_24 = new JLabel("* Require");
		label_24.setForeground(Color.RED);
		panel_4.add(label_24, "cell 3 1");
		
		JLabel lblNewLabel_4 = new JLabel("Interest Rate");
		panel_4.add(lblNewLabel_4, "cell 0 2,alignx left,aligny top");
		
		JLabel lblRate = new JLabel("Rate%");
		panel_4.add(lblRate, "cell 1 2,alignx left,aligny top");
		
		JLabel label_25 = new JLabel("Service Fees");
		panel_4.add(label_25, "cell 0 3");
		
		JLabel label_26 = new JLabel("Rate%");
		panel_4.add(label_26, "cell 1 3");
		
		JButton btnCalculate = new JButton("Calculate");
		panel_4.add(btnCalculate, "cell 3 4,alignx left,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(414, 261, 407, 337);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(742, 617, 89, 23);
		this.getContentPane().add(btnCancel);
		
		JButton btnRequestLoan = new JButton("Request Loan");
		btnRequestLoan.setBounds(610, 617, 122, 23);
		this.getContentPane().add(btnRequestLoan);
	}
}
