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
	private JTextField Iduration;
	private JTextField Iinterval;
	private JTextField Irate;
	private JTextField Ifees;
	private JTextField Gduration;
	private JTextField Ginterval;
	private JTextField Grate;
	private JTextField Gfees;
	private AbstractButton IEdit;
	private JButton Icancel;
	private JButton GEdit;
	private JButton Gcancel;
	private JLabel noteISetting;
	private JLabel noteGSetting;
	
	MyQueries msql = new MyQueries();
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
		Iduration.setText(IData[0]);
		Iinterval.setText(IData[1]);
		Irate.setText(IData[2]);
		Ifees.setText(IData[3]);
		}
		catch(NullPointerException e) {
			String[] data = new String[1];
			data[0] = java.time.LocalDate.now().toString();
			System.out.println(data[0]);
			msql.InsertData("Iloansetting", data);
			GetIData();
		}
	}
	//Group Loan Setting
	public void GetGData() {
		try {
		String[] IData = msql.GetGroupLoanSetting();
		Gduration.setText(IData[0]);
		Ginterval.setText(IData[1]);
		Grate.setText(IData[2]);
		Gfees.setText(IData[3]);
		}
		catch(NullPointerException e) {
			String[] data = new String[1];
			data[0] = java.time.LocalDate.now().toString();
			System.out.println(data[0]);
			msql.InsertData("Gloansetting", data);
			GetGData();
		}
	}
	
	public boolean Icheck() {
		//Duration
		if(Checking.IsNull(Iduration.getText())) {
			noteISetting.setText("* Duration must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Iduration.getText())) {
			noteISetting.setText("* Duration must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(Iduration.getText())<9 || Integer.parseInt(Iduration.getText())>60) {
			noteISetting.setText("* Duration must be between 9 and 60");
			noteISetting.setVisible(true);
			return false;
		}
		//Interval
		if(Checking.IsNull(Iinterval.getText())) {
			noteISetting.setText("* Interval must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Iinterval.getText())) {
			noteISetting.setText("* Interval must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(Iinterval.getText())<1 || Integer.parseInt(Iinterval.getText())>100) {
			noteISetting.setText("* Interval must be between 1 and 100");
			noteISetting.setVisible(true);
			return false;
		}
		//Interest Rate
		if(Checking.IsNull(Irate.getText())) {
			noteISetting.setText("* Interest Rate must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Irate.getText())) {
			noteISetting.setText("* Interest Rate must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(Irate.getText())<1 || Float.parseFloat(Irate.getText())>30) {
			noteISetting.setText("* Interest Rate must be between 1 and 30");
			noteISetting.setVisible(true);
			return false;
		}
		//Service Fee
		if(Checking.IsNull(Ifees.getText())) {
			noteISetting.setText("* Service Fee must be filled");
			noteISetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Ifees.getText())) {
			noteISetting.setText("* Service Fee must be number only");
			noteISetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(Ifees.getText())<1 || Float.parseFloat(Ifees.getText())>30) {
			noteISetting.setText("* Service Fee must be between 1 and 30");
			noteISetting.setVisible(true);
			return false;
		}
			return true;
	}
	
	public boolean Gcheck() {
		//Duration
		if(Checking.IsNull(Gduration.getText())) {
			noteGSetting.setText("* Duration must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Gduration.getText())) {
			noteGSetting.setText("* Duration must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(Gduration.getText())<9 || Integer.parseInt(Gduration.getText())>60) {
			noteGSetting.setText("* Duration must be between 9 and 60");
			noteGSetting.setVisible(true);
			return false;
		}
		//Interval
		if(Checking.IsNull(Ginterval.getText())) {
			noteGSetting.setText("* Interval must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Ginterval.getText())) {
			noteGSetting.setText("* Interval must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Integer.parseInt(Ginterval.getText())<1 || Integer.parseInt(Ginterval.getText())>100) {
			noteGSetting.setText("* Interval must be between 1 and 100");
			noteGSetting.setVisible(true);
			return false;
		}
		//Interest Rate
		if(Checking.IsNull(Grate.getText())) {
			noteGSetting.setText("* Interest Rate must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Grate.getText())) {
			noteGSetting.setText("* Interest Rate must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(Grate.getText())<1 || Float.parseFloat(Grate.getText())>30) {
			noteGSetting.setText("* Interest Rate must be between 1 and 30");
			noteGSetting.setVisible(true);
			return false;
		}
		//Service Fee
		if(Checking.IsNull(Gfees.getText())) {
			noteGSetting.setText("* Service Fee must be filled");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Checking.IsLetter(Gfees.getText())) {
			noteGSetting.setText("* Service Fee must be number only");
			noteGSetting.setVisible(true);
			return false;
		}
		if(Float.parseFloat(Gfees.getText())<1 || Float.parseFloat(Gfees.getText())>30) {
			noteGSetting.setText("* Service Fee must be between 1 and 30");
			noteGSetting.setVisible(true);
			return false;
		}
			return true;
	}
	
	public void fieldDisable() {
		Iduration.setEditable(false);
		Iinterval.setEditable(false);
		Irate.setEditable(false);
		Ifees.setEditable(false);
		Gduration.setEditable(false);
		Ginterval.setEditable(false);
		Grate.setEditable(false);
		Gfees.setEditable(false);
		Icancel.setVisible(false);
		Gcancel.setVisible(false);
		IEdit.setText("Edit");
		GEdit.setText("Edit");
	}
	
	public void IndividualfieldEnable() {
		Iduration.setEditable(true);
		Iinterval.setEditable(true);
		Irate.setEditable(true);
		Ifees.setEditable(true);
		IEdit.setText("Save");
		Icancel.setVisible(true);
	}
	
	public void GroupfieldEnable() {
		Gduration.setEditable(true);
		Ginterval.setEditable(true);
		Grate.setEditable(true);
		Gfees.setEditable(true);
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
		panel.setLayout(new MigLayout("", "[][][20][][][][][][][][grow][][20][][][][][][][]", "[10][][][30][30][30][30][30][]"));
		
		JLabel lblNewLabel = new JLabel("Individual");
		panel.add(lblNewLabel, "cell 1 1");
		
		JLabel lblNewLabel_1 = new JLabel("Group");
		panel.add(lblNewLabel_1, "cell 11 1");
		
		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1, "cell 11 2 9 1,growx");
		
		JSeparator separator = new JSeparator();
		panel.add(separator, "cell 1 2 9 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Duration");
		panel.add(lblNewLabel_2, "cell 1 3");
		
		Iduration = new JTextField();
		Iduration.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				noteISetting.setVisible(false);
			}
		});
		Iduration.setEditable(false);
		panel.add(Iduration, "cell 3 3 5 1,growx,aligny center");
		Iduration.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Month");
		panel.add(lblNewLabel_6, "cell 8 3");
		
		JLabel label = new JLabel("Duration");
		panel.add(label, "cell 11 3");
		
		Gduration = new JTextField();
		Gduration.setEditable(false);
		Gduration.setColumns(10);
		panel.add(Gduration, "cell 13 3 5 1,growx");
		
		JLabel label_4 = new JLabel("Month");
		panel.add(label_4, "cell 18 3");
		
		JLabel lblNewLabel_5 = new JLabel("Interval");
		panel.add(lblNewLabel_5, "cell 1 4");
		
		Iinterval = new JTextField();
		Iinterval.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteISetting.setVisible(false);
			}
		});
		Iinterval.setEditable(false);
		panel.add(Iinterval, "cell 3 4 5 1,growx");
		Iinterval.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Month");
		panel.add(lblNewLabel_7, "cell 8 4");
		
		JLabel label_1 = new JLabel("Interval");
		panel.add(label_1, "cell 11 4");
		
		Ginterval = new JTextField();
		Ginterval.setEditable(false);
		Ginterval.setColumns(10);
		panel.add(Ginterval, "cell 13 4 5 1,growx");
		
		JLabel label_5 = new JLabel("Month");
		panel.add(label_5, "cell 18 4");
		
		JLabel lblNewLabel_3 = new JLabel("Intrest Rate");
		panel.add(lblNewLabel_3, "cell 1 5");
		
		Irate = new JTextField();
		Irate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteISetting.setVisible(false);
			}
		});
		Irate.setEditable(false);
		panel.add(Irate, "cell 3 5 5 1,growx");
		Irate.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("%");
		panel.add(lblNewLabel_8, "cell 8 5");
		
		JLabel label_2 = new JLabel("Intrest Rate");
		panel.add(label_2, "cell 11 5");
		
		Grate = new JTextField();
		Grate.setEditable(false);
		Grate.setColumns(10);
		panel.add(Grate, "cell 13 5 5 1,growx");
		
		JLabel label_6 = new JLabel("%");
		panel.add(label_6, "cell 18 5");
		
		JLabel lblNewLabel_4 = new JLabel("Service Fees");
		panel.add(lblNewLabel_4, "cell 1 6");
		
		Ifees = new JTextField();
		Ifees.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteISetting.setVisible(false);
			}
		});
		Ifees.setEditable(false);
		panel.add(Ifees, "cell 3 6 5 1,growx");
		Ifees.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("%");
		panel.add(lblNewLabel_9, "cell 8 6");
		
		JLabel label_3 = new JLabel("Service Fees");
		panel.add(label_3, "cell 11 6");
		
		Gfees = new JTextField();
		Gfees.setEditable(false);
		Gfees.setColumns(10);
		panel.add(Gfees, "cell 13 6 5 1,growx");
		
		JLabel label_7 = new JLabel("%");
		panel.add(label_7, "cell 18 6");
		
		IEdit = new JButton("Edit");
		IEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(IEdit.getText()=="Edit") {
				IndividualfieldEnable();
				}				
				else if(IEdit.getText()=="Save") {
					boolean check = Icheck();
					if(check) {
						String[] INewSetting = new String[5];
						INewSetting[0] = Iduration.getText();
						INewSetting[1] = Iinterval.getText();
						INewSetting[2] = Irate.getText();
						INewSetting[3] = Ifees.getText();
						INewSetting[4] = java.time.LocalDate.now().toString();
						boolean update = msql.UpdateData("Iloansetting", INewSetting);
						if(update) {
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
		panel.add(noteISetting, "cell 1 7 7 1");
		noteISetting.setVisible(false);
		
		noteGSetting = new JLabel("* Error");
		noteGSetting.setForeground(Color.RED);
		panel.add(noteGSetting, "cell 11 7 7 1");
		noteGSetting.setVisible(false);
		
		panel.add(IEdit, "cell 1 8,growx");
		
		Icancel = new JButton("Cancel");
		Icancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldDisable();
				GetIData();
			}
		});
		panel.add(Icancel, "cell 2 8 3 1");
		
		GEdit = new JButton("Edit");
		GEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(GEdit.getText()=="Edit") {
				GroupfieldEnable();
				}				
				else if(GEdit.getText()=="Save") {
					boolean check = Gcheck();
					if(check) {
						String[] GNewSetting = new String[5];
						GNewSetting[0] = Gduration.getText();
						GNewSetting[1] = Ginterval.getText();
						GNewSetting[2] = Grate.getText();
						GNewSetting[3] = Gfees.getText();
						GNewSetting[4] = java.time.LocalDate.now().toString();
						boolean update = msql.UpdateData("Gloansetting", GNewSetting);
						if(update) {
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
		panel.add(GEdit, "cell 11 8,growx");
		
		Gcancel = new JButton("Cancel");
		Gcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldDisable();
				GetGData();
			}
		});
		panel.add(Gcancel, "cell 12 8 3 1");
		
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
