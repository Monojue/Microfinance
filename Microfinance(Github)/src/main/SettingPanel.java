package main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import tool.Checking;
import tool.MyDate;
import database.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import database.MyQueries;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SettingPanel extends JPanel {
	private JTextField textIMinDuration;
	private JTextField textIDurationInterval;
	private JTextField textIRate;
	private JTextField textIFees;
	private JTextField textGMinDuration;
	private JTextField textGDurationInterval;
	private JTextField textGRate;
	private JTextField textGFees;
	private AbstractButton IEdit;
	private JButton Icancel;
	private JButton GEdit;
	private JButton Gcancel;
	private JLabel noteISetting;
	private JLabel noteGSetting;
	private JTextField textIMinAmount;
	private JTextField textIMaxAmount;
	private JTextField textIMaxDuration;
	private JTextField textGMinAmount;
	private JTextField textGMaxDuration;
	private JTextField textGMaxAmount;
	private JTextField textIAmountInterval;
	private JTextField textGAmountInterval;
	private String ID;
	
	MyQueries msql = new MyQueries();
	DBConnection myDbConnection = new DBConnection();
	
	/**
	 * Create the panel.
	 */
	public SettingPanel() {
		initialize();
		fieldDisable();
		GetIData();
		GetGData();
	}
	
	
	public void GetIData() {
		try {
		String[] IData = msql.GetIndividualLoanSetting();
		textIMinAmount.setText(IData[0]);
		textIMaxAmount.setText(IData[1]);
		textIMinDuration.setText(IData[2]);
		textIMaxDuration.setText(IData[3]);
		textIAmountInterval.setText(IData[4]);
		textIDurationInterval.setText(IData[5]);
		textIRate.setText(IData[6]);
		textIFees.setText(IData[7]);
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
			data[10] = "Individual";
			data[11] = "1";
			msql.InsertData("Iloansetting", data);
			GetIData();
		}
	}
	//Group Loan Setting
	public void GetGData() {
		try {
			String[] GData = msql.GetGroupLoanSetting();
			textGMinAmount.setText(GData[0]);
			textGMaxAmount.setText(GData[1]);
			textGMinDuration.setText(GData[2]);
			textGMaxDuration.setText(GData[3]);
			textGAmountInterval.setText(GData[4]);
			textGDurationInterval.setText(GData[5]);
			textGRate.setText(GData[6]);
			textGFees.setText(GData[7]);
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
			data[11] = "1";
			msql.InsertData("Gloansetting", data);
			GetIData();
		}
	}
	
	public boolean Icheck() {
		//Amount
		if(Checking.IsNull(textIMinAmount.getText())) {
			noteISetting.setText("* Amount must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsNull(textIMaxAmount.getText())) {
			noteISetting.setText("* Amount must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIMinAmount.getText())) {
			noteISetting.setText("* Amount must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIMaxAmount.getText())) {
			noteISetting.setText("* Amount must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textIMinAmount.getText())< 100000 || Integer.parseInt(textIMaxDuration.getText())>10000000) {
			noteISetting.setText("* Amount must be between 100,000 and 10,000,000");
			noteISetting.setVisible(true);
			return false;
		}
		//Duration
		if(Checking.IsNull(textIMinDuration.getText())) {
			noteISetting.setText("* Duration must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsNull(textIMaxDuration.getText())) {
			noteISetting.setText("* Duration must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIMinDuration.getText())) {
			noteISetting.setText("* Duration must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIMaxDuration.getText())) {
			noteISetting.setText("* Duration must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textIMinDuration.getText())<3 || Integer.parseInt(textIMaxDuration.getText())>60) {
			noteISetting.setText("* Duration must be between 3 and 60");
			noteISetting.setVisible(true);
			return false;
		}
		//Amount Interval
		if(Checking.IsNull(textIDurationInterval.getText())) {
			noteISetting.setText("* Amount Interval must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIAmountInterval.getText())) {
			noteISetting.setText("* Amount Interval must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textIAmountInterval.getText())<50000 || Integer.parseInt(textIAmountInterval.getText())>5000000) {
			noteISetting.setText("* Amount Interval must be between 50,000 and 5,000,000");
			noteISetting.setVisible(true);
			return false;
		}
		//Duration Interval
		if(Checking.IsNull(textIDurationInterval.getText())) {
			noteISetting.setText("* Interval must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIDurationInterval.getText())) {
			noteISetting.setText("* Interval must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textIDurationInterval.getText())<1 || Integer.parseInt(textIDurationInterval.getText())>100) {
			noteISetting.setText("* Interval must be between 1 and 100");
			noteISetting.setVisible(true);
			return false;
		}
		//Interest Rate
		if(Checking.IsNull(textIRate.getText())) {
			noteISetting.setText("* Interest Rate must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIRate.getText())) {
			noteISetting.setText("* Interest Rate must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(textIRate.getText())<1 || Float.parseFloat(textIRate.getText())>30) {
			noteISetting.setText("* Interest Rate must be between 1 and 30");
			noteISetting.setVisible(true);
			return false;
		}
		//Service Fee
		if(Checking.IsNull(textIFees.getText())) {
			noteISetting.setText("* Service Fee must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textIFees.getText())) {
			noteISetting.setText("* Service Fee must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(textIFees.getText())<1 || Float.parseFloat(textIFees.getText())>30) {
			noteISetting.setText("* Service Fee must be between 1 and 30");
			noteISetting.setVisible(true);
			return false;
		}
			return true;
	}
	
	public boolean Gcheck() {
		//Amount
		if(Checking.IsNull(textGMinAmount.getText())) {
			noteGSetting.setText("* Amount must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsNull(textGMaxAmount.getText())) {
			noteGSetting.setText("* Amount must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textGMinAmount.getText())) {
			noteGSetting.setText("* Amount must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textGMaxAmount.getText())) {
			noteGSetting.setText("* Amount must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textGMinAmount.getText())< 100000 || Integer.parseInt(textGMaxDuration.getText())>10000000) {
			noteGSetting.setText("* Amount must be between 100,000 and 10,000,000");
			noteGSetting.setVisible(true);
			return false;
		}
		//Duration
		if(Checking.IsNull(textGMinDuration.getText())) {
			noteGSetting.setText("* Duration must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textGMinDuration.getText())) {
			noteGSetting.setText("* Duration must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textGMinDuration.getText())<3 || Integer.parseInt(textGMaxDuration.getText())>60) {
			noteGSetting.setText("* Duration must be between 3 and 60");
			noteGSetting.setVisible(true);
			return false;
		}
		//Amount Interval
		if(Checking.IsNull(textGAmountInterval.getText())) {
			noteGSetting.setText("* Amount Interval must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textGAmountInterval.getText())) {
			noteGSetting.setText("* Amount Interval must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textGAmountInterval.getText())<50000 || Integer.parseInt(textGAmountInterval.getText())>5000000) {
			noteGSetting.setText("* Amount Interval must be between 50,000 and 5,000,000");
			noteGSetting.setVisible(true);
			return false;
		}
		//Duration Interval
		if(Checking.IsNull(textGDurationInterval.getText())) {
			noteGSetting.setText("* Interval must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textGDurationInterval.getText())) {
			noteGSetting.setText("* Interval must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(textGDurationInterval.getText())<1 || Integer.parseInt(textGDurationInterval.getText())>100) {
			noteGSetting.setText("* Interval must be between 1 and 100");
			noteGSetting.setVisible(true);
			return false;
		}
		//Interest Rate
		if(Checking.IsNull(textGRate.getText())) {
			noteGSetting.setText("* Interest Rate must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textGRate.getText())) {
			noteGSetting.setText("* Interest Rate must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(textGRate.getText())<1 || Float.parseFloat(textGRate.getText())>30) {
			noteGSetting.setText("* Interest Rate must be between 1 and 30");
			noteGSetting.setVisible(true);
			return false;
		}
		//Service Fee
		if(Checking.IsNull(textGFees.getText())) {
			noteGSetting.setText("* Service Fee must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(textGFees.getText())) {
			noteGSetting.setText("* Service Fee must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(textGFees.getText())<1 || Float.parseFloat(textGFees.getText())>30) {
			noteGSetting.setText("* Service Fee must be between 1 and 30");
			noteGSetting.setVisible(true);
			return false;
		}
			return true;
	}
	
	public void fieldDisable() {
		textIMinAmount.setEditable(false);
		textIMaxAmount.setEditable(false);
		textIMinDuration.setEditable(false);
		textIMaxDuration.setEditable(false);
		textIAmountInterval.setEditable(false);
		textIDurationInterval.setEditable(false);
		textIRate.setEditable(false);
		textIFees.setEditable(false);
		textGMinAmount.setEditable(false);
		textGMaxAmount.setEditable(false);
		textGMinDuration.setEditable(false);
		textGMaxDuration.setEditable(false);
		textGAmountInterval.setEditable(false);
		textGDurationInterval.setEditable(false);
		textGRate.setEditable(false);
		textGFees.setEditable(false);
		Icancel.setVisible(false);
		Gcancel.setVisible(false);
		IEdit.setText("Edit");
		GEdit.setText("Edit");
	}
	
	public void IndividualfieldEnable() {
		textIMinAmount.setEditable(true);
		textIMaxAmount.setEditable(true);
		textIMinDuration.setEditable(true);
		textIMaxDuration.setEditable(true);
		textIAmountInterval.setEditable(true);
		textIDurationInterval.setEditable(true);
		textIRate.setEditable(true);
		textIFees.setEditable(true);
		IEdit.setText("Save");
		Icancel.setVisible(true);
	}
	
	public void GroupfieldEnable() {
		textGMinAmount.setEditable(true);
		textGMaxAmount.setEditable(true);
		textGMinDuration.setEditable(true);
		textGMaxDuration.setEditable(true);
		textGAmountInterval.setEditable(true);
		textGDurationInterval.setEditable(true);
		textGRate.setEditable(true);
		textGFees.setEditable(true);
		GEdit.setText("Save");
		Gcancel.setVisible(true);
	}
	
	public void initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 1059, 580);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Loan Setting", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 690, 558);
		add(panel);
		panel.setLayout(new MigLayout("", "[][][20][65.00,grow][][65][][][][][grow][][20][65,grow][][65][][][][]", "[10][][][30][30][][30][30][30][30][]"));
		
		JLabel lblNewLabel = new JLabel("Individual");
		panel.add(lblNewLabel, "cell 1 1");
		
		JLabel lblNewLabel_1 = new JLabel("Group");
		panel.add(lblNewLabel_1, "cell 11 1");
		
		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1, "cell 11 2 9 1,growx");
		
		JSeparator separator = new JSeparator();
		panel.add(separator, "cell 1 2 9 1,grow");
		
		JLabel lblNewLabel_16 = new JLabel("Amount");
		panel.add(lblNewLabel_16, "cell 1 3");
		
		textIMinAmount = new JTextField();
		textIMinAmount.setEditable(false);
		panel.add(textIMinAmount, "cell 3 3,growx");
		textIMinAmount.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("-");
		panel.add(lblNewLabel_17, "cell 4 3");
		
		textIMaxAmount = new JTextField();
		textIMaxAmount.setEditable(false);
		textIMaxAmount.setColumns(10);
		panel.add(textIMaxAmount, "cell 5 3,growx");
		
		JLabel lblKyats = new JLabel("Kyats");
		panel.add(lblKyats, "cell 6 3");
		
		JLabel label_9 = new JLabel("Amount");
		panel.add(label_9, "cell 11 3");
		
		textGMinAmount = new JTextField();
		textGMinAmount.setEditable(false);
		textGMinAmount.setColumns(10);
		panel.add(textGMinAmount, "cell 13 3,growx");
		
		JLabel label_13 = new JLabel("-");
		panel.add(label_13, "cell 14 3,alignx trailing");
		
		textGMaxAmount = new JTextField();
		textGMaxAmount.setEditable(false);
		textGMaxAmount.setColumns(10);
		panel.add(textGMaxAmount, "cell 15 3,growx");
		
		JLabel lblKyats_1 = new JLabel("Kyats");
		panel.add(lblKyats_1, "cell 16 3");
		
		JLabel lblNewLabel_2 = new JLabel("Duration");
		panel.add(lblNewLabel_2, "cell 1 4");
		
		textIMinDuration = new JTextField();
		textIMinDuration.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				noteISetting.setVisible(false);
			}
		});
		textIMinDuration.setEditable(false);
		panel.add(textIMinDuration, "cell 3 4,growx,aligny center");
		textIMinDuration.setColumns(10);
		
		JLabel label_8 = new JLabel("-");
		panel.add(label_8, "cell 4 4");
		
		textIMaxDuration = new JTextField();
		textIMaxDuration.setText("24");
		textIMaxDuration.setEditable(false);
		textIMaxDuration.setColumns(10);
		panel.add(textIMaxDuration, "cell 5 4,growx");
		
		JLabel lblNewLabel_6 = new JLabel("Month");
		panel.add(lblNewLabel_6, "cell 6 4 2 1");
		
		JLabel label = new JLabel("Duration");
		panel.add(label, "cell 11 4");
		
		textGMinDuration = new JTextField();
		textGMinDuration.setEditable(false);
		textGMinDuration.setColumns(10);
		panel.add(textGMinDuration, "cell 13 4,growx");
		
		JLabel label_14 = new JLabel("-");
		panel.add(label_14, "cell 14 4");
		
		textGMaxDuration = new JTextField();
		textGMaxDuration.setText("24");
		textGMaxDuration.setEditable(false);
		textGMaxDuration.setColumns(10);
		panel.add(textGMaxDuration, "cell 15 4,growx");
		
		JLabel label_11 = new JLabel("Month");
		panel.add(label_11, "cell 16 4");
		
		JLabel lblNewLabel_5_1 = new JLabel("Amount Interval");
		panel.add(lblNewLabel_5_1, "cell 1 5");
		
		textIAmountInterval = new JTextField();
		textIAmountInterval.setText((String) null);
		textIAmountInterval.setEditable(false);
		textIAmountInterval.setColumns(10);
		panel.add(textIAmountInterval, "cell 3 5,growx");
		
		JLabel lblNewLabel_7_1 = new JLabel("Kyats");
		panel.add(lblNewLabel_7_1, "cell 5 5");
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Amount Interval");
		panel.add(lblNewLabel_5_1_1, "cell 11 5");
		
		textGAmountInterval = new JTextField();
		textGAmountInterval.setText((String) null);
		textGAmountInterval.setEditable(false);
		textGAmountInterval.setColumns(10);
		panel.add(textGAmountInterval, "cell 13 5,growx");
		
		JLabel lblNewLabel_5 = new JLabel("Duration Interval");
		panel.add(lblNewLabel_5, "cell 1 6");
		
		textIDurationInterval = new JTextField();
		textIDurationInterval.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteISetting.setVisible(false);
			}
		});
		textIDurationInterval.setEditable(false);
		panel.add(textIDurationInterval, "cell 3 6,growx");
		textIDurationInterval.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Month");
		panel.add(lblNewLabel_7, "cell 5 6");
		
		JLabel lblDurationInterval = new JLabel("Duration Interval");
		panel.add(lblDurationInterval, "cell 11 6");
		
		textGDurationInterval = new JTextField();
		textGDurationInterval.setEditable(false);
		textGDurationInterval.setColumns(10);
		panel.add(textGDurationInterval, "cell 13 6,growx");
		
		JLabel label_12 = new JLabel("Month");
		panel.add(label_12, "cell 15 6");
		
		JLabel lblNewLabel_3 = new JLabel("Intrest Rate");
		panel.add(lblNewLabel_3, "cell 1 7");
		
		textIRate = new JTextField();
		textIRate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteISetting.setVisible(false);
			}
		});
		textIRate.setEditable(false);
		panel.add(textIRate, "cell 3 7,growx");
		textIRate.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("%");
		panel.add(lblNewLabel_8, "cell 4 7");
		
		JLabel label_2 = new JLabel("Intrest Rate");
		panel.add(label_2, "cell 11 7");
		
		textGRate = new JTextField();
		textGRate.setEditable(false);
		textGRate.setColumns(10);
		panel.add(textGRate, "cell 13 7,growx");
		
		JLabel label_6 = new JLabel("%");
		panel.add(label_6, "cell 14 7 5 1");
		
		JLabel lblNewLabel_4 = new JLabel("Service Fees");
		panel.add(lblNewLabel_4, "cell 1 8");
		
		textIFees = new JTextField();
		textIFees.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteISetting.setVisible(false);
			}
		});
		textIFees.setEditable(false);
		panel.add(textIFees, "cell 3 8,growx");
		textIFees.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("%");
		panel.add(lblNewLabel_9, "cell 4 8");
		
		JLabel label_3 = new JLabel("Service Fees");
		panel.add(label_3, "cell 11 8");
		
		textGFees = new JTextField();
		textGFees.setEditable(false);
		textGFees.setColumns(10);
		panel.add(textGFees, "cell 13 8,growx");
		
		JLabel label_7 = new JLabel("%");
		panel.add(label_7, "cell 14 8 5 1");
		
		IEdit = new JButton("Edit");
		IEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(IEdit.getText()=="Edit") {
				IndividualfieldEnable();
				}				
				else if(IEdit.getText()=="Save") {
					boolean check = Icheck();
					if(check) {
						String[] INewSetting = new String[12];
						INewSetting[0] =  myDbConnection.getAutoID("ID", "loansetting", "Ls-");
						INewSetting[1] = textIMinAmount.getText();
						INewSetting[2] = textIMaxAmount.getText();
						INewSetting[3] = textIMinDuration.getText();
						INewSetting[4] = textIMaxDuration.getText();
						INewSetting[5] = textIAmountInterval.getText();
						INewSetting[6] = textIDurationInterval.getText();
						INewSetting[7] = textIRate.getText();
						INewSetting[8] = textIFees.getText();
						INewSetting[9] = java.time.LocalDate.now().toString();
						INewSetting[10] = "Individual";
						INewSetting[11] = "1";
						boolean insert = msql.InsertData("Iloansetting", INewSetting);
						if(insert) {
							fieldDisable();
							JOptionPane.showMessageDialog(null, "Individual Loan Setting is Updated!","Save Sucessfully!",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							IndividualfieldEnable();
							JOptionPane.showMessageDialog(null, "Failed to Save Loan New Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(!check) {
						IndividualfieldEnable();
					}
				}
			}
		});
		
		noteISetting = new JLabel("* Error");
		noteISetting.setForeground(Color.RED);
		panel.add(noteISetting, "cell 1 9 7 1");
		noteISetting.setVisible(false);
		
		noteGSetting = new JLabel("* Error");
		noteGSetting.setForeground(Color.RED);
		panel.add(noteGSetting, "cell 11 9 7 1");
		noteGSetting.setVisible(false);
		
		panel.add(IEdit, "cell 1 10,growx");
		
		Icancel = new JButton("Cancel");
		Icancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldDisable();
				GetIData();
			}
		});
		panel.add(Icancel, "cell 2 10 3 1");
		
		GEdit = new JButton("Edit");
		GEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(GEdit.getText()=="Edit") {
				GroupfieldEnable();
				}				
				else if(GEdit.getText()=="Save") {
					boolean check = Gcheck();
					if(check) {
						String[] GNewSetting = new String[12];
						GNewSetting[0] = myDbConnection.getAutoID("ID", "loansetting", "Ls-");
						GNewSetting[1] = textGMinAmount.getText();
						GNewSetting[2] = textGMaxAmount.getText();
						GNewSetting[3] = textGMinDuration.getText();
						GNewSetting[4] = textGMaxDuration.getText();
						GNewSetting[5] = textGAmountInterval.getText();
						GNewSetting[6] = textGDurationInterval.getText();
						GNewSetting[7] = textGRate.getText();
						GNewSetting[8] = textGFees.getText();
						GNewSetting[9] = java.time.LocalDate.now().toString();
						GNewSetting[10] = "Group";
						GNewSetting[11] = "1";
						boolean insert = msql.InsertData("Gloansetting", GNewSetting);
						if(insert) {
							fieldDisable();
							JOptionPane.showMessageDialog(null, "Group Loan Setting is Updated!","Save Sucessfully!",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							GroupfieldEnable();
							JOptionPane.showMessageDialog(null, "Failed to Update new Individual Setting!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(!check) {
						GroupfieldEnable();
					}
				}
			}
		});
		panel.add(GEdit, "cell 11 10,growx");
		
		Gcancel = new JButton("Cancel");
		Gcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldDisable();
				GetGData();
			}
		});
		panel.add(Gcancel, "cell 12 10 3 1");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(710, 11, 339, 558);
		add(panel_1);
		panel_1.setLayout(new MigLayout("", "[134.00][grow][]", "[20][][][50,grow][][][][][][][][][]"));
		
		JLabel lblNewLabel_10 = new JLabel("MICROFINANCE");
		lblNewLabel_10.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 32));
		panel_1.add(lblNewLabel_10, "cell 0 1 3 1");
		
		JLabel lblNewLabel_11 = new JLabel("Management Software");
		lblNewLabel_11.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_11, "cell 0 2 3 1");
		
		JLabel lblNewLabel_12 = new JLabel("Developed By");
		lblNewLabel_12.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_12, "cell 2 9,alignx right");
		
		JLabel lblNewLabel_13 = new JLabel("GROUP 12");
		lblNewLabel_13.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_13, "cell 2 10,alignx right");
		
		JLabel lblNewLabel_14 = new JLabel("NYI LINN HTIN");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_14, "cell 2 11,alignx right");
		
		JLabel lblNewLabel_15 = new JLabel("KAUNG MYAT THET");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_15, "cell 2 12,alignx right");
	}
}
