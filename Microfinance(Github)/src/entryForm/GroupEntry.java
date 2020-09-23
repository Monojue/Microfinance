package entryForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import database.DBConnection;
import database.MyQueries;
import database.UQueries;
import net.miginfocom.swing.MigLayout;
import tool.MyDate;
import tool.Select;
import tool.MyString;

import java.awt.Panel;
import java.awt.Label;
import javax.swing.JSeparator;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GroupEntry extends JFrame {

	private JPanel contentPane;
	private JTextField textGID;
	private JTextField textDate;
	private JPanel panel;
	private  Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Label label_5;
	private Label label_6;
	private static JTextField leadName;
	private static JTextField M1Name;
	private static JTextField M2Name;
	private static JTextField M3Name;
	private static JTextField M4Name;
	private static JTextField leadID;
	private static JTextField M1ID;
	private static JTextField M2ID;
	private static JTextField M3ID;
	private static JTextField M4ID;
	private Button btnled;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private JPanel panel_2;
	private JButton btnSave;
	private JButton button_6;
	DBConnection myDbConnection = new DBConnection();
	MyDate myDate = new MyDate();
	private static JLabel lblAlert;
	public static String selplus = ""; 
	UQueries msql = new UQueries();
	MyQueries msql1 = new MyQueries();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupEntry frame = new GroupEntry(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void AutoID() {
		textGID.setText(myDbConnection.getAutoID("groupID", "clientGroup", "GP-"));
		textDate.setText(myDate.getdate());
	}
	
	public boolean check() {
		if(msql1.CheckClientIsInGroup(leadID.getText())) {
			lblAlert.setText( "Leader is in another Group!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckClientIsInGroup(M1ID.getText())) {
			lblAlert.setText(M1Name.getText() +" is in another Group!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckClientIsInGroup(M2ID.getText())) {
			lblAlert.setText(M2Name.getText() +" is in another Group!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckClientIsInGroup(M3ID.getText())) {
			lblAlert.setText(M3Name.getText() +" is in another Group!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckClientIsInGroup(M4ID.getText())) {
			lblAlert.setText(M4Name.getText() +" is in another Group!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckAvaliable("Individual",leadID.getText())) {
			lblAlert.setText( "Leader is already requested Individual Loan!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckAvaliable("Individual",M1ID.getText())) {
			lblAlert.setText(M1Name.getText()+" is already requested Individual Loan!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckAvaliable("Individual",M2ID.getText())) {
			lblAlert.setText(M2Name.getText()+" is already requested Individual Loan!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckAvaliable("Individual",M3ID.getText())) {
			lblAlert.setText(M3Name.getText()+" is already requested Individual Loan!");
			lblAlert.setVisible(true);
			return false;
		}
		else if(msql1.CheckAvaliable("Individual",M4ID.getText())) {
			lblAlert.setText(M4Name.getText()+" is already requested Individual Loan!");
			lblAlert.setVisible(true);
			return false;
		}
		else {
			return true;
		}
	}
	
	public GroupEntry(String GroupID) {
		initialize();
		if(GroupID == null) {
			AutoID();
			btnSave.setText("Save");
		}
		else {
			String[] GroupDetails = msql1.getGroupDetailsFormID(GroupID);
			textGID.setText(GroupDetails[0]);
			leadID.setText(GroupDetails[1]);
			M1ID.setText(GroupDetails[2]);
			M2ID.setText(GroupDetails[3]);
			M3ID.setText(GroupDetails[4]);
			M4ID.setText(GroupDetails[5]);
			leadName.setText(GroupDetails[6]);
			M1Name.setText(GroupDetails[7]);
			M2Name.setText(GroupDetails[8]);
			M3Name.setText(GroupDetails[9]);
			M4Name.setText(GroupDetails[10]);
			btnSave.setText("Update");
		}
	}
	
	public void chooseClient() {
		lblAlert.setVisible(false);
		new Select(MyString.One,MyString.GroupEntry).setVisible(true);
	}
	
	public static void setID(String name,String ID) {
		if(leadID.getText().equals(ID)||M1ID.getText().equals(ID)||M2ID.getText().equals(ID)||M3ID.getText().equals(ID)||M4ID.getText().equals(ID)) {
			lblAlert.setText("Client Already Exits!");
			lblAlert.setVisible(true);
		}else {
			if(MyString.leader.equals(selplus)) {
				leadName.setText(name);
				leadID.setText(ID);
			}else if (MyString.Mem_1.equals(selplus)) {
				M1Name.setText(name);
				M1ID.setText(ID);
			}else if (MyString.Mem_2.equals(selplus)) {
				M2Name.setText(name);
				M2ID.setText(ID);
			}else if (MyString.Mem_3.equals(selplus)) {
				M3Name.setText(name);
				M3ID.setText(ID);
			}else if (MyString.Mem_4.equals(selplus)) {
				M4Name.setText(name);
				M4ID.setText(ID);
			}
		}
	}
	
	public void Clear() {
		leadID.setText("");
		leadName.setText("");
		M1ID.setText("");
		M1Name.setText("");
		M2ID.setText("");
		M2Name.setText("");
		M3ID.setText("");
		M3Name.setText("");
		M4ID.setText("");
		M4Name.setText("");
	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 458, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[420px]", "[39px][229px][34px]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1, "cell 0 0,growx,aligny top");
		panel_1.setLayout(new MigLayout("", "[46px][93px][130.00][25.00px][103px]", "[20px]"));
		
		JLabel lblNewLabel = new JLabel("Group ID");
		lblNewLabel.setBackground(Color.WHITE);
		panel_1.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		panel_1.add(lblNewLabel_1, "cell 3 0,growx,aligny center");
		
		textGID = new JTextField();
		textGID.setEditable(false);
		panel_1.add(textGID, "cell 1 0,growx,aligny top");
		textGID.setColumns(10);
		
		textDate = new JTextField();
		textDate.setEditable(false);
		panel_1.add(textDate, "cell 4 0,growx,aligny top");
		textDate.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "GROUP INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[62px][158.00px][165.00px][]", "[22px][30][30][30][30][30]"));
		
		label = new Label("Leader");
		label.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(label, "cell 0 1,grow");
		
		btnled = new Button("+");
		btnled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selplus = MyString.leader;
				chooseClient();
			}
		});
		panel.add(btnled, "cell 3 1,alignx center,aligny center");
		
		label_1 = new Label("Member 1");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(label_1, "cell 0 2,grow");
		
		btn1 = new Button("+");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selplus = MyString.Mem_1;
				chooseClient();
			}
		});
		panel.add(btn1, "cell 3 2,growx");
		
		label_2 = new Label("Member 2");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(label_2, "cell 0 3,grow");
		
		btn2 = new Button("+");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selplus = MyString.Mem_2;
				chooseClient();
			}
		});
		panel.add(btn2, "cell 3 3,growx,aligny center");
		
		label_3 = new Label("Member 3");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(label_3, "cell 0 4,grow");
		
		btn3 = new Button("+");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selplus = MyString.Mem_3;
				chooseClient();
			}
		});
		panel.add(btn3, "cell 3 4,alignx center,aligny center");
		
		label_4 = new Label("Member 4");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(label_4, "cell 0 5,grow");
		
		label_5 = new Label("Name");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel.add(label_5, "cell 1 0,alignx center,aligny center");
		
		label_6 = new Label("Client ID");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel.add(label_6, "cell 2 0,alignx center,aligny center");
		
		leadName = new JTextField();
		leadName.setEditable(false);
		panel.add(leadName, "cell 1 1,grow");
		
		M1Name = new JTextField();
		M1Name.setEditable(false);
		panel.add(M1Name, "cell 1 2,grow");
		
		M2Name = new JTextField();
		M2Name.setEditable(false);
		panel.add(M2Name, "cell 1 3,grow");
		
		M3Name = new JTextField();
		M3Name.setEditable(false);
		panel.add(M3Name, "cell 1 4,grow");
		
		M4Name = new JTextField();
		M4Name.setEditable(false);
		panel.add(M4Name, "cell 1 5,grow");
		
		leadID = new JTextField();
		leadID.setEditable(false);
		panel.add(leadID, "cell 2 1,grow");
		
		M1ID = new JTextField();
		M1ID.setEditable(false);
		panel.add(M1ID, "cell 2 2,grow");
		
		M2ID = new JTextField();
		M2ID.setEditable(false);
		panel.add(M2ID, "cell 2 3,grow");
		
		M3ID = new JTextField();
		M3ID.setEditable(false);
		panel.add(M3ID, "cell 2 4,grow");
		
		M4ID = new JTextField();
		M4ID.setEditable(false);
		panel.add(M4ID, "cell 2 5,grow");
		
		btn4 = new Button("+");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selplus = MyString.Mem_4;
				chooseClient();
			}
		});
		panel.add(btn4, "cell 3 5,alignx center,aligny center");
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[294.00][][]", "[]"));
		
		lblAlert = new JLabel("* Please Fill All Member!");
		lblAlert.setForeground(Color.RED);
		lblAlert.setVisible(false);
		panel_2.add(lblAlert, "cell 0 0");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (leadID.getText().isEmpty()||M1ID.getText().isEmpty()||M2ID.getText().isEmpty()||M3ID.getText().isEmpty()||M4ID.getText().isEmpty()) {
					lblAlert.setText("Please Fill All Member!");
					lblAlert.setVisible(true);
				}
				else if(check()) {
					String[] data = new String[11];
					data[0] = textGID.getText();
					data[1] = leadID.getText();
					data[2] = M1ID.getText();
					data[3] = M2ID.getText();
					data[4] = M3ID.getText();
					data[5] = M4ID.getText();
					
					data[6] = leadName.getText();
					data[7] = M1Name.getText();
					data[8] = M2Name.getText();
					data[9] = M3Name.getText();
					data[10] = M4Name.getText();
					
					if(btnSave.getText()=="Save") {
					boolean save = msql.InsertData(MyString.GroupEntry, data);
					if (save) {
						JOptionPane.showMessageDialog(null, "Saved Successfully!","Saved Record",JOptionPane.INFORMATION_MESSAGE);
						Clear();
						AutoID();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to Save new Record!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
						AutoID();
					}
					}
					else if (btnSave.getText()=="Update") {
						boolean update = msql1.UpdateData("clientgroup", data);
						if (update) {
							JOptionPane.showMessageDialog(null, "Updated Successfully!","Updated Record",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to Update new Record!","Cannot Updated",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		panel_2.add(btnSave, "cell 1 0");
		
		button_6 = new JButton("Cancel");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_2.add(button_6, "cell 2 0");
	}
}
