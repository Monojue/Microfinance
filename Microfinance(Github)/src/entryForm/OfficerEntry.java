package entryForm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import tool.Checking;
import tool.MyString;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Choice;
import javax.swing.border.TitledBorder;

import database.DBConnection;
import database.MyQueries;
import database.UQueries;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class OfficerEntry extends JFrame {

	private JPanel contentPane;
	private JTextField lblID;
	private JLabel lblName;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField boxNo;
	private JTextField txtPhone;
	private JTextField txtUserName;
	private JTextField txtPass;
	private JTextField txtCPass;
	private DBConnection myDbConnection =  new DBConnection();
	private Checking check = new Checking();
	private MyQueries msql = new MyQueries();
	private UQueries usql = new UQueries();
	private JLabel lblError;
	private Choice boxN1;
	private Choice boxN2;
	private Choice boxN3;
	private Choice boxRole;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficerEntry frame = new OfficerEntry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean SetNRCcodeData() {
		Vector<String> code = new Vector<>();
		code.clear();
		boxN2.removeAll();
		code = msql.getCodefromNo(boxN1.getSelectedItem().toString());
		for (String data : code) {
			boxN2.add(data);
		}
		return true;
	}

	public boolean check() {
		//Check Name
		if (Checking.IsNull(txtName.getText())) {
			showError("Name is Empty!");
		}else if (Checking.IsNull(txtAddress.getText())) {
			showError("Address is Empty!");
		}
		else if (Checking.IsNull(txtPhone.getText())) {
			showError("Phone is Empty!");
		}
		else if (!Checking.IsAllDigit(txtPhone.getText()) || txtPhone.getText().length()<9 || txtPhone.getText().length()>15) {
			showError("Phone is Invalid!");
		}
		//Check NRC
		else if (Checking.IsNull(boxN1.getSelectedItem())) {
			showError("NRC is Empty!");
		}else if (Checking.IsNull(boxN2.getSelectedItem())) {
			showError("NRC is Empty!");
		}else if (Checking.IsNull(boxN3.getSelectedItem())) {
			showError("NRC is Empty!");
		}else if (Checking.IsNull(boxNo.getText())) {
			showError("NRC is Empty!");
		}
		else if (boxNo.getText().length() != 6 || !Checking.IsAllDigit(boxNo.getText())) {
			showError("NRC is Invalid!");
		}
		else if(Checking.IsNull(boxRole.getSelectedItem())) {
			showError("Role is Empty!");
		}
		else if (Checking.IsNull(txtUserName.getText())) {
			showError("User Name is Empty!");
		}
		else if(Checking.IsNull(txtPass.getText())) {
			showError("Password is Empty!");
		}
		else if(Checking.IsNull(txtCPass.getText())) {
			showError("Confirm Password is Empty!");
		}
		else if (!txtPass.getText().equals(txtCPass.getText())) {
			showError("Password doesn't match!");
		}
		else {
			lblError.setVisible(false);
			return true;
		}
		return false;
	}
	
	public void showError(String error) {
		lblError.setText(error);
		lblError.setVisible(true);
	}
	
	public void AutoID() {
		lblID.setText(myDbConnection.getAutoID("OfficerID", "Officer", "OF-"));
	}
	
	public OfficerEntry() {
		Initialize();
		AutoID();
	}
	public void Clear() {
		txtName.setText("");
		boxN1.select(0);
		boxN2.select(0);
		boxN3.select(0);
		boxNo.setText("");
		boxRole.select(0);
		txtAddress.setText("");
		txtUserName.setText("");
		txtPass.setText("");
		txtCPass.setText("");
		txtPhone.setText("");
	}
	
	public void Initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(450, 150, 514, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[428.00,grow]", "[31.00][182.00,grow][30.00]"));
		this.setResizable(false);
		
		JPanel panelhead = new JPanel();
		contentPane.add(panelhead, "cell 0 0,grow");
		panelhead.setLayout(new MigLayout("", "[][]", "[]"));
		
		JLabel lblNewLabel = new JLabel("ID");
		panelhead.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		lblID = new JTextField();
		lblID.setEditable(false);
		panelhead.add(lblID, "cell 1 0,growx");
		lblID.setColumns(10);
		
		JPanel panelbody = new JPanel();
		panelbody.setBorder(new TitledBorder(null, "Officer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelbody, "cell 0 1,grow");
		panelbody.setLayout(new MigLayout("", "[][][][51.00][32.00][96.00][50.00][95.00][46.00]", "[][40][40][40][40][40][40][40][40]"));
		
		JPanel panelbtn = new JPanel();
		contentPane.add(panelbtn, "cell 0 2,grow");
		panelbtn.setLayout(new MigLayout("", "[grow][][][]", "[]"));
		
		lblError = new JLabel("New label");
		lblError.setForeground(Color.RED);
		lblError.setVisible(false);
		panelbtn.add(lblError, "cell 0 0");
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (check()) {
					String NRC = boxN1.getSelectedItem().toString()+"/-"+boxN2.getSelectedItem().toString()
							 +"-"+ boxN3.getSelectedItem().toString()+"-" + boxNo.getText();
					String[] st = new String[1];
					st[0] = NRC;
					boolean dup = msql.IsDuplicate("officer", st);
					if(dup) {
						showError("NRC Already Existed");
					}else {
						String[] data = new String[8];
						data[0] = lblID.getText();
						data[1] = txtName.getText();
						data[2] = txtAddress.getText();
						data[3] = txtPhone.getText();
						data[4] = NRC;
						data[5] = boxRole.getSelectedItem();
						data[6] = txtUserName.getText();
						data[7] = txtPass.getText();
						boolean save = usql.InsertData("Officer", data);
						if (save) {
							JOptionPane.showMessageDialog(null, "Saved Successfully!","Saved Record",JOptionPane.INFORMATION_MESSAGE);
							Clear();
							AutoID();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to Save new Record!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
							AutoID();
						}
					}
				}
				
			}
		});
		panelbtn.add(btnAdd, "cell 1 0");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelbtn.add(btnCancel, "cell 3 0");
		
		lblName = new JLabel("Name");
		panelbody.add(lblName, "cell 1 1,alignx center,growy");
		
		txtName = new JTextField();
		panelbody.add(txtName, "cell 3 1 4 1,grow");
		txtName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		panelbody.add(lblAddress, "cell 1 2,alignx center,growy");
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		panelbody.add(txtAddress, "cell 3 2 5 1,grow");
		
		JLabel lblPhone = new JLabel("Phone");
		panelbody.add(lblPhone, "cell 1 3,alignx center,growy");
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		panelbody.add(txtPhone, "cell 3 3 4 1,grow");
		
		JLabel lblNrc = new JLabel("NRC");
		panelbody.add(lblNrc, "cell 1 4,alignx center,growy");
		
		boxN1 = new Choice();
		boxN1.addItem("");
		boxN1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				SetNRCcodeData();
			}
		});
		for (int i = 1; i <= 12; i++) {
			boxN1.addItem(String.valueOf(i));
		}
		panelbody.add(boxN1, "cell 3 4,grow");
		
		JLabel lblNewLabel_1 = new JLabel("/");
		panelbody.add(lblNewLabel_1, "cell 4 4,alignx center,growy");
		
		boxN2 = new Choice();
		panelbody.add(boxN2, "cell 5 4,grow");
		
		boxN3 = new Choice();
		boxN3.addItem("");
		boxN3.addItem("(N)");
		panelbody.add(boxN3, "cell 6 4,grow");
		
		boxNo = new JTextField();
		panelbody.add(boxNo, "cell 7 4,grow");
		boxNo.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		panelbody.add(lblRole, "cell 1 5,alignx center,growy");
		
		boxRole = new Choice();
		boxRole.addItem("");
		boxRole.addItem("Manager");
		boxRole.addItem("Staff");
		panelbody.add(boxRole, "cell 3 5 2 1,growx,aligny center");
		
		JLabel lblUsername = new JLabel("UserName");
		panelbody.add(lblUsername, "cell 1 6,alignx center,growy");
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		panelbody.add(txtUserName, "cell 3 6 3 1,grow");
		
		JLabel lblPassword = new JLabel("Password");
		panelbody.add(lblPassword, "cell 1 7,alignx center,growy");
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		panelbody.add(txtPass, "cell 3 7 3 1,grow");
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		panelbody.add(lblConfirmPassword, "cell 1 8,alignx center,growy");
		
		txtCPass = new JTextField();
		txtCPass.setColumns(10);
		panelbody.add(txtCPass, "cell 3 8 3 1,grow");
	}

}
