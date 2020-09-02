package entryForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import database.DBConnection;
import net.miginfocom.swing.MigLayout;
import tool.MyDate;
import tool.Select;

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
	private Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Label label_5;
	private Label label_6;
	private JTextField leadName;
	private JTextField M1Name;
	private JTextField M2Name;
	private JTextField M3Name;
	private JTextField M4Name;
	private JTextField leadID;
	private JTextField M1ID;
	private JTextField M2ID;
	private JTextField M3ID;
	private JTextField M4ID;
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private JPanel panel_2;
	private JButton button_5;
	private JButton button_6;
	DBConnection myDbConnection = new DBConnection();
	MyDate myDate = new MyDate();
	private JLabel lblAlert;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupEntry frame = new GroupEntry();
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
	
	public GroupEntry() {
		initialize();
		AutoID();
	}
	
	public void chooseClient() {
		new Select("ONE").setVisible(true);
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
		
		btn0 = new Button("+");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseClient();
			}
		});
		panel.add(btn0, "cell 3 1,alignx center,aligny center");
		
		label_1 = new Label("Member 1");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(label_1, "cell 0 2,grow");
		
		btn1 = new Button("+");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		leadName.setEnabled(false);
		panel.add(leadName, "cell 1 1,grow");
		
		M1Name = new JTextField();
		M1Name.setEditable(false);
		M1Name.setEnabled(false);
		panel.add(M1Name, "cell 1 2,grow");
		
		M2Name = new JTextField();
		M2Name.setEditable(false);
		M2Name.setEnabled(false);
		panel.add(M2Name, "cell 1 3,grow");
		
		M3Name = new JTextField();
		M3Name.setEditable(false);
		M3Name.setEnabled(false);
		panel.add(M3Name, "cell 1 4,grow");
		
		M4Name = new JTextField();
		M4Name.setEditable(false);
		M4Name.setEnabled(false);
		panel.add(M4Name, "cell 1 5,grow");
		
		leadID = new JTextField();
		leadID.setEditable(false);
		leadID.setEnabled(false);
		panel.add(leadID, "cell 2 1,grow");
		
		M1ID = new JTextField();
		M1ID.setEditable(false);
		M1ID.setEnabled(false);
		panel.add(M1ID, "cell 2 2,grow");
		
		M2ID = new JTextField();
		M2ID.setEditable(false);
		M2ID.setEnabled(false);
		panel.add(M2ID, "cell 2 3,grow");
		
		M3ID = new JTextField();
		M3ID.setEditable(false);
		M3ID.setEnabled(false);
		panel.add(M3ID, "cell 2 4,grow");
		
		M4ID = new JTextField();
		M4ID.setEditable(false);
		M4ID.setEnabled(false);
		panel.add(M4ID, "cell 2 5,grow");
		
		btn4 = new Button("+");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		button_5 = new JButton("Save");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (leadID.getText().isEmpty()||M1ID.getText().isEmpty()||M2ID.getText().isEmpty()||M3ID.getText().isEmpty()||M4ID.getText().isEmpty()) {
					lblAlert.setVisible(true);
				}
			}
		});
		panel_2.add(button_5, "cell 1 0");
		
		button_6 = new JButton("Cancel");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_2.add(button_6, "cell 2 0");
	}
}
