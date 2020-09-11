package entryForm;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import database.DBConnection;
import database.MyQueries;
import database.UQueries;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

import net.miginfocom.swing.MigLayout;
import tool.Checking;
import tool.MyDate;
import tool.MyString;
import main.ClientPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Cursor;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.Font;
import java.awt.Cursor;


public class ClientEntry extends JFrame {
	
	//Nyi Linn Htin
	private JTextField helo;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField boxNo;
	private JTextField textAddress;
	private JTextField textCity;
	private JTextField textState;
	private JTextField textPh;
	private JTextField textJob;
	private JTextField textSalary;
	private JTextField textCID;
	private JLabel noteName;
	private JLabel noteAge;
	private JLabel noteAddress;
	private JLabel noteCity;
	private JLabel notePhone;
	private JLabel noteHome;
	private JLabel lblHome;
	private JLabel noteJob;
	private JLabel noteState;
	private JLabel noteSalary;
	private JLabel noteNRC;
	private Choice boxN1;
	private Choice boxN2;
	private Choice boxN3;
	private JCheckBox checkHome;
	DBConnection myDbConnection = new DBConnection();
	MyQueries msql = new MyQueries();
	MyDate myDate = new MyDate();
	private JComboBox boxDay;
	private JComboBox<String> boxMonth;
	private JComboBox boxYear;
	Date date=new Date();
	int TodayYear = Integer.parseInt(String.valueOf(date).substring(24));
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JButton btnSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientEntry frame = new ClientEntry(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void AutoID() {
		 textCID.setText(myDbConnection.getAutoID("clientID", "Client", "CL-"));
	}

	public boolean check() {
		//Check Name
		if (Checking.IsNull(textName.getText())) {
			noteName.setText("* Required");
			noteName.setVisible(true);
			return false;
		}else if (!Checking.IsLetter(textName.getText())) {
			noteName.setText("* Invalid");
			noteName.setVisible(true);
			return false;
		}
		//Check NRC
		if (Checking.IsNull(boxN1.getSelectedItem())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}else if (Checking.IsNull(boxN2.getSelectedItem())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}else if (Checking.IsNull(boxN3.getSelectedItem())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}else if (Checking.IsNull(boxNo.getText())) {
			noteNRC.setText("* Required");
			noteNRC.setVisible(true);
			return false;
		}
		else if (boxNo.getText().length() != 6) {
			noteNRC.setText("* Invalid");
			noteNRC.setVisible(true);
			return false;
		}
		//Check Date of Birth
		if (boxDay.getSelectedIndex()==0 || boxMonth.getSelectedIndex()==0 || boxYear.getSelectedIndex()==0) {
			noteAge.setText("* Required");
			noteAge.setVisible(true);
			return false;
			}
		else if(boxYear.getSelectedIndex()!=0) {
			int age = TodayYear-Integer.parseInt(boxYear.getSelectedItem().toString());
			if(age<18 || age>60) {
			noteAge.setText("* Invalid Age");
			noteAge.setVisible(true);
			return false;}
		}
		//Check Address
		if (Checking.IsNull(textAddress.getText().toString())) {
			noteAddress.setText("* Required");
			noteAddress.setVisible(true);
			return false;
		}
		//Check City
		if (Checking.IsNull(textCity.getText().toString())) {
			noteCity.setText("* Required");
			noteCity.setVisible(true);
			return false;
		}
		//Check State
		if (Checking.IsNull(textState.getText())) {
			noteState.setText("* Required");
			noteState.setVisible(true);
			return false;
		}
		//Check Phone No
		if (Checking.IsNull(textPh.getText())) {
			notePhone.setText("* Required");
			notePhone.setVisible(true);
			return false;
		}
		else if (Checking.IsLetter(textPh.getText())) {
			notePhone.setText("* Invalid");
			notePhone.setVisible(true);
			return false;
		}
		else if(textPh.getText().length()<9 || textPh.getText().length()>15) {
			notePhone.setText("* Invalid");
			notePhone.setVisible(true);
			return false;
		}
		//Check Job
		if (Checking.IsNull(textJob.getText())) {
			noteJob.setText("* Required");
			noteJob.setVisible(true);
			return false;
		}
		//Check Salary
		if (Checking.IsNull(textSalary.getText())) {
			noteSalary.setText("* Required");
			noteSalary.setVisible(true);
			return false;
		}
		else if (Checking.IsLetter(textSalary.getText())) {
			noteSalary.setText("* Invalid");
			noteSalary.setVisible(true);
			return false;
		}
		else if (Integer.parseInt(textSalary.getText())<150000) {
			noteSalary.setText("* Too Low");
			noteSalary.setVisible(true);
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Create the frame.
	 * @param ClientID 
	 */
	public ClientEntry(String ClientID) {
		initialize();
		if(ClientID == null) {		
		AutoID();
		btnSave.setText("Save");
		}
		else {
			String[] ClientDetails = msql.getClientDetailsFormID(ClientID);
			textCID.setText(ClientDetails[0]);
			textName.setText(ClientDetails[1]);
			//textAddress.setText(ClientDetails[4]);
			textPh.setText(ClientDetails[4]);
			if(Integer.parseInt(ClientDetails[6])==1) {
				checkHome.setSelected(true);
			}
			textJob.setText(ClientDetails[7]);
			textSalary.setText(ClientDetails[8]);
			btnSave.setText("Update");
		}
		SetNRCcodeData();
	}
	
	public void SetNRCcodeData() {
		Vector<String> code = new Vector<>();
		code.clear();
		boxN2.removeAll();
		code = msql.getCodefromNo(boxN1.getSelectedItem().toString());
		for (String data : code) {
			boxN2.add(data);
		}
	}
	
	public void Clear() {
		textName.setText("");
		boxN1.select(0);
		boxN2.select(0);
		boxN3.select(0);
		boxNo.setText("");
		boxDay.setSelectedIndex(0);
		boxMonth.setSelectedIndex(0);
		boxYear.setSelectedIndex(0);
		textAddress.setText("");
		textCity.setText("");
		textState.setText("");
		textPh.setText("");
		checkHome.setSelected(false);
		textJob.setText("");
		textSalary.setText("");
	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(450, 100, 681, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CLIENT INFROMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(new MigLayout("", "[52.00][52px][28px,grow][5px][4px][5px][13px][grow][17px][34px][46.00,grow][][5px][57.00px][13.00px][31px][5px][52px][5px][25px][5px][34.00px,left][34.00px,left][62px,left]", "[][30][30][30][30][30][30][30][30][30]"));
		
		JLabel label = new JLabel("NAME");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label, "cell 1 1,grow");
		
		noteName = new JLabel("* Required");
		noteName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteName.setForeground(Color.RED);
		noteName.setBackground(Color.WHITE);
		noteName.setVisible(false);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteName.setVisible(false);
			}
		});
		textName.setColumns(10);
		panel.add(textName, "cell 2 1 16 1,grow");
		panel.add(noteName, "cell 19 1 4 1,alignx left,growy");
		
		JLabel label_1 = new JLabel("NRC");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label_1, "cell 1 2,alignx left,growy");
		
		boxN1 = new Choice();
		boxN1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				SetNRCcodeData();
			}
		});

		boxN1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxN1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteNRC.setVisible(false);
			}
		});
		for (int i = 1; i <= 12; i++) {
			boxN1.addItem(String.valueOf(i));
		}
		
		panel.add(boxN1, "cell 2 2,alignx left,aligny top");
		
		JLabel label_2 = new JLabel("/");
		panel.add(label_2, "cell 4 2,alignx left,growy");
		
		boxN2 = new Choice();
		boxN2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxN2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteNRC.setVisible(false);
			}
		});
		panel.add(boxN2, "cell 6 2 4 1,growx,aligny top");
		
		boxN3 = new Choice();
		boxN3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxN3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteNRC.setVisible(false);
			}
		});
		boxN3.addItem("N");
		panel.add(boxN3, "cell 10 2,growx,aligny top");
		
		boxNo = new JTextField();
		boxNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteNRC.setVisible(false);
			}
		});
		boxNo.setColumns(10);
		panel.add(boxNo, "cell 11 2 7 1,grow");
		
		noteNRC = new JLabel("* Invalid");
		noteNRC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteNRC.setForeground(Color.RED);
		noteNRC.setBackground(Color.WHITE);
		noteNRC.setVisible(false);
		panel.add(noteNRC, "cell 18 2 5 1,alignx left,growy");
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblDateOfBirth, "cell 1 3,alignx left,growy");
		
		noteAge = new JLabel("* Required");
		noteAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteAge.setForeground(Color.RED);
		noteAge.setBackground(Color.WHITE);
		noteAge.setVisible(false);
		
		boxDay = new JComboBox();
		boxDay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxDay.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				noteAge.setVisible(false);
			}
		});
		panel.add(boxDay, "cell 2 3 4 1,growx");
		boxDay.addItem("");
		for(int i=1;i<=31;i++) {
			boxDay.addItem(i);
		}
		
		boxMonth = new JComboBox();
		boxMonth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxMonth.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteAge.setVisible(false);
			}
		});
		panel.add(boxMonth, "cell 6 3 4 1,growx,aligny bottom");
		boxMonth.addItem("");
		boxMonth.addItem("JANUARY");
		boxMonth.addItem("FEBUARY");
		boxMonth.addItem("MARCH");
		boxMonth.addItem("APRIL");
		boxMonth.addItem("MAY");
		boxMonth.addItem("JUNE");
		boxMonth.addItem("JULY");
		boxMonth.addItem("AUGUST");
		boxMonth.addItem("SEPTEMBER");
		boxMonth.addItem("OCTOBER");
		boxMonth.addItem("NOVEMBER");
		boxMonth.addItem("DECEMBER");
		
		boxYear = new JComboBox();
		boxYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxYear.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteAge.setVisible(false);
			}
		});
		panel.add(boxYear, "cell 10 3 3 1,growx");
		panel.add(noteAge, "cell 20 3 3 1,alignx left,growy");
		boxYear.addItem("");
		for(int i=TodayYear-70;i<=TodayYear;i++) {
			boxYear.addItem(i);
		}
		
		JLabel label_4 = new JLabel("ADDRESS");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label_4, "cell 1 4,alignx left,growy");
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteAddress.setVisible(false);
			}
		});
		textAddress.setColumns(10);
		panel.add(textAddress, "cell 2 4 18 1,grow");
		
		noteAddress = new JLabel("* Required");
		noteAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteAddress.setForeground(Color.RED);
		noteAddress.setBackground(Color.WHITE);
		noteAddress.setVisible(false);
		panel.add(noteAddress, "cell 21 4 2 1,alignx center,growy");
		
		JLabel label_7 = new JLabel("CITY");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label_7, "cell 1 5,alignx left,growy");
		
		textCity = new JTextField();
		textCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteCity.setVisible(false);
			}
		});
		textCity.setColumns(10);
		panel.add(textCity, "cell 2 5 6 1,grow");
		
		noteCity = new JLabel("* Required");
		noteCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteCity.setForeground(Color.RED);
		noteCity.setBackground(Color.WHITE);
		noteCity.setVisible(false);
		panel.add(noteCity, "cell 10 5 2 1,alignx center,growy");
		
		JLabel label_5 = new JLabel("STATE");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label_5, "cell 13 5 3 1,alignx left,aligny center");
		
		textState = new JTextField();
		textState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textState.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteState.setVisible(false);
			}
		});
		textState.setColumns(10);
		panel.add(textState, "cell 16 5 6 1,grow");
		
		noteState = new JLabel("* Required");
		noteState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteState.setForeground(Color.RED);
		noteState.setBackground(Color.WHITE);
		noteState.setVisible(false);
		panel.add(noteState, "cell 22 5,grow");
		
		JLabel label_6 = new JLabel("PHONE NO");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label_6, "cell 1 6,alignx left,growy");
		
		textPh = new JTextField();
		textPh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				notePhone.setVisible(false);
			}
		});
		textPh.setColumns(10);
		panel.add(textPh, "cell 2 6 8 1,grow");
		
		notePhone = new JLabel("* Required");
		notePhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		notePhone.setForeground(Color.RED);
		notePhone.setBackground(Color.WHITE);
		notePhone.setVisible(false);
		panel.add(notePhone, "cell 10 6 2 1,alignx left,growy");
		
		lblHome = new JLabel("HOME");
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblHome, "cell 1 7,alignx left,growy");
		
		noteHome = new JLabel("* Required");
		noteHome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteHome.setForeground(Color.RED);
		noteHome.setBackground(Color.WHITE);
		noteHome.setVisible(false);
		
		checkHome = new JCheckBox("Owned");
		checkHome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkHome.setBackground(Color.WHITE);
		checkHome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteHome.setVisible(false);
			}
		});
		panel.add(checkHome, "cell 2 7 5 1");
		panel.add(noteHome, "cell 7 7,alignx left,growy");
		
		JLabel lblJob = new JLabel("JOB");
		lblJob.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblJob, "cell 1 8,alignx left,growy");
		
		textJob = new JTextField();
		textJob.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textJob.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteJob.setVisible(false);
			}
		});
		textJob.setColumns(10);
		panel.add(textJob, "cell 2 8 14 1,grow");
		
		noteJob = new JLabel("* Required");
		noteJob.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteJob.setForeground(Color.RED);
		noteJob.setBackground(Color.WHITE);
		noteJob.setVisible(false);
		panel.add(noteJob, "cell 17 8,alignx left,growy");
		
		JLabel lblSalary = new JLabel("SALARY");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblSalary, "cell 1 9,alignx left,growy");
		
		textSalary = new JTextField();
		textSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSalary.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				noteSalary.setVisible(false);
			}
		});
		textSalary.setColumns(10);
		panel.add(textSalary, "cell 2 9 8 1,grow");
		contentPane.setLayout(new MigLayout("", "[524px]", "[36px][297px][60.00px]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(new MigLayout("", "[46px][118.00px][171.00][24.00px][103px][][][][][grow]", "[20px]"));
		
		JLabel lblNewLabel = new JLabel("Client ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBackground(Color.WHITE);
		panel_1.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		textCID = new JTextField();
		textCID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCID.setEditable(false);
		panel_1.add(textCID, "cell 1 0,growx,aligny top");
		textCID.setColumns(10);
		contentPane.add(panel_1, "cell 0 0,grow");
		
		lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_1, "cell 7 0");
		
		textField = new JTextField();
		textField.setText("2020-Sep-08");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		panel_1.add(textField, "cell 9 0,growx");
		contentPane.add(panel, "cell 0 1,grow");
		
		noteSalary = new JLabel("* Required");
		noteSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noteSalary.setForeground(Color.RED);
		noteSalary.setBackground(Color.WHITE);
		noteSalary.setVisible(false);
		panel.add(noteSalary, "cell 10 9 2 1,alignx left,growy");
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[496.00][][]", "[77.00]"));
		
		btnSave = new JButton("Save");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setForeground(Color.BLACK);
		btnSave.setBackground(Color.WHITE);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = check();
				if(check) {
					
					String NRC = boxN1.getSelectedItem().toString()+"/"+boxN2.getSelectedItem().toString()
								 +"("+ boxN3.getSelectedItem().toString()+")" + boxNo.getText();
					String DateOfBirth = boxDay.getSelectedItem().toString()+"-"+boxMonth.getSelectedItem().toString()+"-"+boxYear.getSelectedItem().toString();
					String home = "0";
					String Address = textAddress.getText()+","+ textCity.getText() + "," + textState.getText();
					if(checkHome.isSelected()) {
						home = "1";
					}
					String[] st = new String[1];
					st[0] = NRC;
					boolean dup = msql.IsDuplicate(MyString.ClientEntry, st);
					if(dup) {
						noteNRC.setText("* Already Existed");
						noteNRC.setVisible(true);
					}
					else {
						String[] data = new String[9];
						data[0] = textCID.getText();
						data[1] = textName.getText();
						data[2] = NRC;
						data[3] = Address;
						data[4] = textPh.getText();
						data[5] = DateOfBirth;
						data[6] = home;
						data[7] = textJob.getText();
						data[8] = textSalary.getText();
						if(btnSave.getText()=="Save") {
						boolean save = msql.InsertData(MyString.ClientEntry, data);
						if (save) {
							JOptionPane.showMessageDialog(null, "Saved Successfully!","Saved Record",JOptionPane.INFORMATION_MESSAGE);
							Clear();
							AutoID();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to Save new Record!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
							AutoID();
						}
						}
						
						else if(btnSave.getText()=="Update") {
							boolean update = msql.UpdateData("client", data);
							if (update) {
								JOptionPane.showMessageDialog(null, "Updated Successfully!","Saved Record",JOptionPane.INFORMATION_MESSAGE);
								Clear();
								AutoID();
							} else {
								JOptionPane.showMessageDialog(null, "Failed to Update new Data!","Cannot Updated",JOptionPane.INFORMATION_MESSAGE);
								AutoID();
							}
						}
					}
			}
		}});
		panel_2.add(btnSave, "cell 1 0");
		
		JButton button = new JButton("Cancel");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBackground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_2.add(button, "cell 2 0");
	}
}



