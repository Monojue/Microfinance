package entryForm;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.DBConnection;
import database.MyQueries;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Rectangle;
import net.miginfocom.swing.MigLayout;
import tool.MyDate;
import tool.MyString;
import tool.Select;
import tool.Checking;
import tool.Calculation;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.Choice;

import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.event.ItemEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;


public class LoanRequestForm extends JFrame {

	private static JTextField textPName;
	private static JTextField textPNRC;
	private static JTextField textPDOB;
	private static JTextField textPAddress;
	private static JCheckBox checkPHome;
	private static JTextField textPPhone;
	private static JTextField textPJob;
	private static JTextField textPSalary;
	private JLabel noteClientError;
	private JTextField textGName;
	private JTextField textGNRC;
	private JTextField textGAddress;
	private JTextField textGCity;
	private JTextField textGState;
	private JTextField textGPhone;
	private JTextField textGJob;
	private JTextField textGSalary;
	private JTextField textID;
	private JTextField textDate;
	private JScrollPane scrollPane;
	private static JTextField textCID;
	private JLabel noteGName;
	private Choice boxGNRC1;
	private JLabel noteNRC;
	private Choice boxGNRC2;
	private Choice boxGNRC3;
	private JLabel noteGAddress;
	private JLabel noteGCity;
	private JLabel noteGState;
	private JLabel noteGPhone;
	private JLabel noteGJob;
	private JLabel noteGSalary;
	private JTextField textGRelationship;
	private JLabel noteGRelationship;
	private JLabel noteAmount;
	private JLabel noteDuration;
	
	LoanRequest loanRequest = new LoanRequest();
	DBConnection myDbConnection = new DBConnection();
	MyQueries msql = new MyQueries();
	MyDate myDate = new MyDate();
	DefaultTableModel dtm = new DefaultTableModel();
	private JTextField textDuration;
	private JTextField textAmount;
	private JTable table;
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

	public void SetNRCcodeData() {
		Vector<String> code = new Vector<>();
		code.clear();
		boxGNRC2.removeAll();
		code = msql.getCodefromNo(boxGNRC1.getSelectedItem().toString());
		for (String data : code) {
			boxGNRC2.add(data);
		}
	}
	public LoanRequestForm() {
		initialize();
		textID.setText(loanRequest.getAutoID());
		textDate.setText(myDate.getdate());
		SetNRCcodeData();
	}
	
	public void createTable() {
		if(Checking.IsNull(textAmount.getText())) {
			noteAmount.setVisible(true);
		}
		else if (Checking.IsNull(textDuration.getText())) {
			noteDuration.setVisible(true);
		}
		else {
		DefaultTableModel dtm = new DefaultTableModel(25,5);
		dtm = Calculation.calculator(Integer.parseInt(textAmount.getText()),Integer.parseInt(textDuration.getText()),2.33);
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
	
	public boolean check() {
		//Client ID
		if (Checking.IsNull(textCID.getText())) {
			noteClientError.setText("* Required");
			noteClientError.setVisible(true);
			return false;	
	    }if (Checking.IsNull(textGName.getText())) {
			noteGName.setText("* Required");
			noteGName.setVisible(true);
			return false;
		//Check G Name
		}if (Checking.IsNull(textGName.getText())) {
			noteGName.setText("* Required");
			noteGName.setVisible(true);
			return false;
		}else if (!Checking.IsLetter(textGName.getText())) {
			noteGName.setText("* Invalid");
			noteGName.setVisible(true);
			return false;
		}
		//Check G NRC
		if (Checking.IsNull(boxGNRC1.getSelectedItem())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}else if (Checking.IsNull(boxGNRC2.getSelectedItem())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}else if (Checking.IsNull(boxGNRC3.getSelectedItem())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}else if (Checking.IsNull(textGNRC.getText())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}
		else if (textGNRC.getText().length() != 6) {
			noteNRC.setText("* Invalid");
			noteNRC.setVisible(true);
			return false;
		}
		//Check Address
		if (Checking.IsNull(textGAddress.getText().toString())) {
			noteGAddress.setText("* Required");
			noteGAddress.setVisible(true);
			return false;
		}
		//Check City
		if (Checking.IsNull(textGCity.getText().toString())) {
			noteGCity.setText("* Required");
			noteGCity.setVisible(true);
			return false;
		}
		//Check State
		if (Checking.IsNull(textGState.getText())) {
			noteGState.setText("* Required");
			noteGState.setVisible(true);
			return false;
		}
		//Check Phone No
		if (Checking.IsNull(textGPhone.getText())) {
			noteGPhone.setText("* Required");
			noteGPhone.setVisible(true);
			return false;
		}
		else if (Checking.IsLetter(textGPhone.getText())) {
			noteGPhone.setText("* Invalid");
			noteGPhone.setVisible(true);
			return false;
		}
		else if(textGPhone.getText().length()<9 || textGPhone.getText().length()>15) {
			noteGPhone.setText("* Invalid");
			noteGPhone.setVisible(true);
			return false;
		}
		//Check Job
		if (Checking.IsNull(textGJob.getText())) {
			noteGJob.setText("* Required");
			noteGJob.setVisible(true);
			return false;
		}
		//Check Salary
		if (Checking.IsNull(textGSalary.getText())) {
			noteGSalary.setText("* Required");
			noteGSalary.setVisible(true);
			return false;
		}
		else if (Checking.IsLetter(textGSalary.getText())) {
			noteGSalary.setText("* Invalid");
			noteGSalary.setVisible(true);
			return false;
		}
		else if (Integer.parseInt(textGSalary.getText())<150000) {
			noteGSalary.setText("* Too Low");
			noteGSalary.setVisible(true);
			return false;
		}
		//Check Relationship
		if (Checking.IsNull(textGRelationship.getText())) {
			noteGSalary.setText("* Required");
			noteGSalary.setVisible(true);
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
		else {
			return true;
		}
	}
	private void initialize() {		
	this.getContentPane().setBackground(Color.LIGHT_GRAY);
	this.setTitle("Loan Request Form");
	this.setBounds(20, 10, 1316, 748);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.getContentPane().setLayout(null);
	this.setResizable(false);
	
	JPanel panel = new JPanel();
	panel.setBackground(Color.LIGHT_GRAY);
	panel.setBounds(10, 0, 1293, 664);
	this.getContentPane().add(panel);
	panel.setLayout(null);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(10, 56, 399, 316);
	panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PERSONAL INFROMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
	
	JLabel lblNewLabel = new JLabel("Age");
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
	checkPHome.setEnabled(false);
	
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
			noteClientError.setVisible(false);
		}
	});
	
	noteClientError = new JLabel("* Choose Client");
	noteClientError.setForeground(Color.RED);
	panel_1.add(noteClientError, "cell 1 10 6 1");
	panel_1.add(btnNewButton, "cell 13 10");
	noteClientError.setVisible(false);
	
	JPanel panel_2 = new JPanel();
	panel_2.setBounds(10, 11, 1271, 40);
	panel.add(panel_2);
	panel_2.setLayout(new MigLayout("", "[70.00px][158.00px][464.00,grow][40px][135.00px]", "[26px]"));
	
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
	panel_3.setBounds(10, 383, 798, 268);
	panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "GUARANTOR INFROMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	panel.add(panel_3);
	panel_3.setLayout(new MigLayout("", "[68px][40px][4px][4px][4px][50px][8px][42px][8px][69.00px][8px][97px][97px]", "[30,fill][30,fill][30,fill][30,fill][30,fill][30,fill][][30,grow,fill]"));
	
	JLabel lblName_1 = new JLabel("NAME(*)");
	panel_3.add(lblName_1, "cell 0 0,growx,aligny center");
	
	textGName = new JTextField();
	textGName.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent arg0) {
			noteGName.setVisible(false);
		}
	});
	textGName.setColumns(10);
	panel_3.add(textGName, "cell 1 0 9 1,grow");
	
	noteGName = new JLabel("* Require");
	noteGName.setForeground(Color.RED);
	panel_3.add(noteGName, "cell 11 0 2 1");
	noteGName.setVisible(false);
	
	JLabel lblNrc = new JLabel("NRC(*)");
	panel_3.add(lblNrc, "cell 0 1,alignx left,aligny center");
	
	JLabel label_13 = new JLabel("/");
	panel_3.add(label_13, "cell 3 1,alignx left,growy");
	
	boxGNRC2 = new Choice();
	panel_3.add(boxGNRC2, "cell 5 1 2 1,growx,aligny top");
			
	textGNRC = new JTextField();
	textGNRC.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteNRC.setVisible(false);
		}
	});
	textGNRC.setColumns(10);
	panel_3.add(textGNRC, "flowx,cell 9 1 4 1,alignx left,aligny top");
	
	JLabel lblAddress = new JLabel("ADDRESS(*)");
	panel_3.add(lblAddress, "cell 0 2,alignx left,aligny center");
	
	textGAddress = new JTextField();
	textGAddress.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteGAddress.setVisible(false);
		}
	});
	textGAddress.setColumns(10);
	panel_3.add(textGAddress, "cell 1 2 11 1,growx,aligny top");
	
	noteGAddress = new JLabel("* Require");
	noteGAddress.setForeground(Color.RED);
	panel_3.add(noteGAddress, "cell 12 2,growx");
	noteGAddress.setVisible(false);
	
	textGCity = new JTextField();
	textGCity.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteGCity.setVisible(false);
		}
	});
	textGCity.setColumns(10);
	panel_3.add(textGCity, "cell 1 3 5 1,growx,aligny top");
	
	noteGCity = new JLabel("* Require");
	noteGCity.setForeground(Color.RED);
	panel_3.add(noteGCity, "cell 7 3");
	noteGCity.setVisible(false);
	
	JLabel label_16 = new JLabel("STATE");
	panel_3.add(label_16, "cell 9 3,alignx left,aligny center");
	
	textGState = new JTextField();
	textGState.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteGState.setVisible(false);
		}
	});
	textGState.setColumns(10);
	panel_3.add(textGState, "cell 11 3,growx,aligny top");
	
	noteGState = new JLabel("* Require");
	noteGState.setForeground(Color.RED);
	panel_3.add(noteGState, "cell 12 3,growx");
	noteGState.setVisible(false);
	
	JLabel lblPhoneNo = new JLabel("PHONE NO(*)");
	panel_3.add(lblPhoneNo, "cell 0 4,alignx left,aligny center");
	
	textGPhone = new JTextField();
	textGPhone.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteGPhone.setVisible(false);
		}
	});
	textGPhone.setColumns(10);
	panel_3.add(textGPhone, "cell 1 4 7 1,growx,aligny top");
	
	JLabel lblCity = new JLabel("CITY(*)");
	panel_3.add(lblCity, "cell 0 3,alignx left,aligny center");
	
	noteGPhone = new JLabel("* Require");
	noteGPhone.setForeground(Color.RED);
	panel_3.add(noteGPhone, "cell 9 4");
	noteGPhone.setVisible(false);
	
	JLabel lblJob = new JLabel("Job(*)");
	panel_3.add(lblJob, "cell 0 5,alignx left,aligny center");
	
	textGJob = new JTextField();
	textGJob.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteGJob.setVisible(false);
		}
	});
	textGJob.setColumns(10);
	panel_3.add(textGJob, "cell 1 5 7 1,growx,aligny top");
	
	noteGJob = new JLabel("* Require");
	noteGJob.setForeground(Color.RED);
	panel_3.add(noteGJob, "cell 9 5");
	noteGJob.setVisible(false);
	
	JLabel lblSalary = new JLabel("Salary(*)");
	lblSalary.setHorizontalAlignment(SwingConstants.LEFT);
	panel_3.add(lblSalary, "cell 0 6,alignx left");
	
	textGSalary = new JTextField();
	textGSalary.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteGSalary.setVisible(false);
		}
	});
	textGSalary.setColumns(10);
	panel_3.add(textGSalary, "cell 1 6 7 1,growx");
	
	boxGNRC1 = new Choice();
	boxGNRC1.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			SetNRCcodeData();
		}
	});
	for (int i = 1; i <= 12; i++) {
		boxGNRC1.addItem(String.valueOf(i));
	}
	panel_3.add(boxGNRC1, "cell 1 1,growx,aligny top");
	
	boxGNRC3 = new Choice();
	panel_3.add(boxGNRC3, "cell 7 1,growx,aligny top");
	boxGNRC3.addItem("N");
	
	noteNRC = new JLabel("* Require");
	noteNRC.setForeground(Color.RED);
	panel_3.add(noteNRC, "cell 12 1,growx");
	noteNRC.setVisible(false);
	
	noteGSalary = new JLabel("* Require");
	noteGSalary.setForeground(Color.RED);
	panel_3.add(noteGSalary, "cell 9 6");
	noteGSalary.setVisible(false);
	
	JLabel lblRelation = new JLabel("Relation(*)");
	lblRelation.setHorizontalAlignment(SwingConstants.LEFT);
	panel_3.add(lblRelation, "cell 0 7,alignx left");
	
	textGRelationship = new JTextField();
	textGRelationship.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			noteGRelationship.setVisible(false);
		}
	});
	textGRelationship.setColumns(10);
	panel_3.add(textGRelationship, "cell 1 7 7 1,growx");
	
	noteGRelationship = new JLabel("* Require");
	noteGRelationship.setForeground(Color.RED);
	panel_3.add(noteGRelationship, "cell 9 7");
	noteGRelationship.setVisible(false);
	
	JPanel panel_4 = new JPanel();
	panel_4.setBounds(414, 56, 394, 316);
	panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LOAN INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	panel.add(panel_4);
	panel_4.setLayout(new MigLayout("", "[65px][81.00px,grow][159px][77px]", "[26px][20px][14px][][38.00][][][][23px]"));
	
	JSlider sliderAmount = new JSlider();
	sliderAmount.addMouseMotionListener(new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) {
			noteAmount.setVisible(false);
			textAmount.setText(Integer.toString(sliderAmount.getValue()));
		}
	});
	sliderAmount.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			noteAmount.setVisible(false);
			textAmount.setText(Integer.toString(sliderAmount.getValue()));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			noteAmount.setVisible(false);
			textAmount.setText(Integer.toString(sliderAmount.getValue()));
		}
	});
	sliderAmount.setSnapToTicks(true);
	sliderAmount.setPaintTicks(true);
	//sliderAmount.setPaintLabels(true);
	sliderAmount.setMinorTickSpacing(100000);
	sliderAmount.setMinimum(100000);
	sliderAmount.setMaximum(1000000);
	sliderAmount.setMajorTickSpacing(100000);
	panel_4.add(sliderAmount, "cell 0 0 3 1");
	
	noteAmount = new JLabel("* Require");
	noteAmount.setForeground(Color.RED);
	panel_4.add(noteAmount, "cell 3 0");
	noteAmount.setVisible(false);
	

	JLabel lblNewLabel_2 = new JLabel("Amount(*)");
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
	
	JLabel lblNewLabel_3 = new JLabel("Duration(*)");
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

	JLabel lblNewLabel_2 = new JLabel("Amount");
	panel_4.add(lblNewLabel_2, "cell 0 1 1 2,alignx left");
	
	textAmount = new JTextField();
	textAmount.setEditable(false);
	textAmount.setForeground(Color.black);
	textAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
	textAmount.setColumns(10);
	panel_4.add(textAmount, "cell 1 1 2 2,growx");
	
	JSlider sliderDuration = new JSlider();
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
	sliderDuration.setMinorTickSpacing(3);
	sliderDuration.setMinimum(6);
	sliderDuration.setMaximum(24);
	sliderDuration.setMajorTickSpacing(3);
	panel_4.add(sliderDuration, "cell 0 3 3 1");
	
	noteDuration = new JLabel("* Require");
	noteDuration.setForeground(Color.RED);
	panel_4.add(noteDuration, "cell 3 3");
	noteDuration.setVisible(false);
	
	JLabel lblNewLabel_3 = new JLabel("Duration");
	panel_4.add(lblNewLabel_3, "cell 0 4,alignx left");
	
	textDuration = new JTextField();
	textDuration.setEditable(false);
	textDuration.setColumns(10);
	panel_4.add(textDuration, "cell 1 4,growx");

	
	JLabel lblNewLabel_4 = new JLabel("Interest Rate");
	panel_4.add(lblNewLabel_4, "cell 0 6");
	
	JLabel lblRate = new JLabel("Rate%");
	panel_4.add(lblRate, "cell 1 6");
	
	JLabel label_25 = new JLabel("Service Fees");
	panel_4.add(label_25, "cell 0 7");
	
	JLabel label_26 = new JLabel("Rate%");
	panel_4.add(label_26, "cell 1 7");
	
	JButton btnCalculate = new JButton("Calculate");
	btnCalculate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) { 
			createTable();
		}
	});
	panel_4.add(btnCalculate, "cell 3 8,alignx left,aligny top");
	
	scrollPane = new JScrollPane();
	scrollPane.setBounds(814, 56, 469, 595);
	panel.add(scrollPane);
	
	table = new JTable();
	scrollPane.setColumnHeaderView(table);
	scrollPane.setViewportView(table);
	
	JButton btnCancel = new JButton("Cancel");
	btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	});
	btnCancel.setBounds(1209, 674, 89, 23);
	this.getContentPane().add(btnCancel);
	
	JButton btnRequestLoan = new JButton("Request Loan");
	btnRequestLoan.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			boolean check = check();
			if(check) {
				
				String NRC = boxGNRC1.getSelectedItem().toString()+"\\"+boxGNRC2.getSelectedItem().toString()
							 +"("+ boxGNRC3.getSelectedItem().toString()+")" + textGNRC.getText();
				String Address = textGAddress.getText()+","+textGCity.getText()+","+textGState.getText();
				
					String[] GDetails = new String[8];
					GDetails[0] = textCID.getText();
					GDetails[1] = textGName.getText();
					GDetails[2] = textGJob.getText();
					GDetails[3] = textGSalary.getText();
					GDetails[4] = textGRelationship.getText();
					GDetails[5] = Address;
					GDetails[6] = textGPhone.getText();
					GDetails[7] = NRC;
					boolean update = msql.UpdateData("guarantor", GDetails);
					
					String[] LoanRequest = new String[5];
					LoanRequest[0] = textID.getText();
					LoanRequest[1] = "Individual";
					LoanRequest[2] = textAmount.getText();
					LoanRequest[3] = textDuration.getText();
					LoanRequest[4] = "2.33";
					boolean insert = msql.InsertData("loanrequest", LoanRequest);
					if (update && insert) {
						JOptionPane.showMessageDialog(null, "Saved Successfully!","New Loan Request Saved",JOptionPane.INFORMATION_MESSAGE);
					}
					else if(!update) {
						JOptionPane.showMessageDialog(null, "Failed to Save Guarantor Information Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
					}
					else if (!insert){
						JOptionPane.showMessageDialog(null, "Failed to Save Loan New Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
					}
				}
		}
	});
	btnRequestLoan.setBounds(1078, 674, 122, 23);
	this.getContentPane().add(btnRequestLoan);
	}
}
