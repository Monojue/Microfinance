package tool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.UQueries;
import entryForm.ClientEntry;
import entryForm.GroupEntry;
import entryForm.GroupRequestForm;
import entryForm.LoanRequestForm;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class Select extends JFrame {

	private JPanel contentPane;
	private JTextField textSearch;
	private JTable table;
	private String FormType = "Test";
	public String ClientID;
	DefaultTableModel dtm = new DefaultTableModel();
	UQueries msql = new UQueries();
	String form;
	private final ButtonGroup radioGroup = new ButtonGroup();
	private JLabel lblError;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Select frame = new Select();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Select() {
		initialize();
		createTable();
	}
	public  Select(String FormType, String form) {
		this.FormType = FormType;
		this.form = form;
		initialize();
		createTable();
	}
	
	public void createTable() {
		if(FormType.equals(MyString.One)) {
			table.setModel(msql.getAllClient());
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(50);
			table.getColumnModel().getColumn(7).setPreferredWidth(100);
			table.getColumnModel().getColumn(7).setPreferredWidth(100);
		}else if (FormType.equals(MyString.Group)) {
			table.setModel(msql.getAllGroup());
			table.getColumnModel().getColumn(0).setPreferredWidth(200);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(200);
			table.getColumnModel().getColumn(4).setPreferredWidth(200);
			table.getColumnModel().getColumn(5).setPreferredWidth(200);
		}
		
	}
	
	public String gettableData(int row) {
		return table.getValueAt(table.getSelectedRow(),row).toString();
	}
	
	public void showError(String error) {
		lblError.setText(error);
		lblError.setVisible(true);
	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(220, 30, 940, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 904, 36);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[96.00px][109px][109px][143px][89px][130.00]", "[23px]"));
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		JRadioButton RadioID = new JRadioButton("ID");
		RadioID.setSelected(true);
		radioGroup.add(RadioID);
		panel.add(RadioID, "cell 1 0,growx,aligny top");
		
		JRadioButton RadioName = new JRadioButton("Name");
		radioGroup.add(RadioName);
		panel.add(RadioName, "cell 2 0,growx,aligny top");
		
		if (FormType.equals(MyString.One)) {
			RadioID.setText("Client ID");
			RadioName.setText("Client Name");
		}else if (FormType.equals(MyString.Group)) {
			RadioID.setText("Group ID");
			RadioName.setText("Leader Name");
		}else {
			RadioID.setText("ID");
			RadioName.setText("Name");
		}
		
		textSearch = new JTextField();
		panel.add(textSearch, "cell 3 0,growx,aligny center");
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if (radioGroup.isSelected(RadioID.getModel())) {
					if (textSearch.getText().equals("")) {
						showError("Please Type Client ID To Search");
					}else {
						table.removeAll();
						table.setModel(msql.getClientFormID("CL-"+textSearch.getText().trim()));;
						table.getColumnModel().getColumn(0).setPreferredWidth(100);
						table.getColumnModel().getColumn(1).setPreferredWidth(150);
						table.getColumnModel().getColumn(2).setPreferredWidth(200);
						table.getColumnModel().getColumn(3).setPreferredWidth(250);
						table.getColumnModel().getColumn(4).setPreferredWidth(100);
						table.getColumnModel().getColumn(5).setPreferredWidth(100);
						table.getColumnModel().getColumn(6).setPreferredWidth(50);
						table.getColumnModel().getColumn(7).setPreferredWidth(100);
						table.getColumnModel().getColumn(7).setPreferredWidth(100);
					}
				}else if (radioGroup.isSelected(RadioName.getModel())) {
					if (textSearch.getText().equals("")) {
						showError("Please Type Client Name To Search");
					}else {
						
						table.getColumnModel().getColumn(0).setPreferredWidth(200);
						table.getColumnModel().getColumn(1).setPreferredWidth(200);
						table.getColumnModel().getColumn(2).setPreferredWidth(200);
						table.getColumnModel().getColumn(3).setPreferredWidth(200);
						table.getColumnModel().getColumn(4).setPreferredWidth(200);
						table.getColumnModel().getColumn(5).setPreferredWidth(200);
					}
				}
			}
		});
		panel.add(btnSearch, "cell 4 0");
		
		lblError = new JLabel("New label");
		lblError.setForeground(Color.RED);
		lblError.setVisible(false);
		panel.add(lblError, "cell 5 0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 58, 800, 534);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (FormType.equals(MyString.One)) {
					ClientEntry clientEntry = new ClientEntry();
					clientEntry.setVisible(true);
				}else if (FormType.equals(MyString.Group)) {
					GroupEntry groupEntry = new GroupEntry();
					groupEntry.setVisible(true);
				}
			}
		});
		btnNew.setBounds(825, 58, 89, 23);
		contentPane.add(btnNew);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MyString.GroupEntry.equals(form)) {
					String id	=	gettableData(0);
					String name	=	gettableData(1);
					GroupEntry.setID(name, id);
				}else if (MyString.GroupRequestForm.equals(form)) {
					String id  = gettableData(0);
					String leader = gettableData(1);
					String mem1 = gettableData(2);
					String mem2 = gettableData(3);
					String mem3 = gettableData(4);
					String mem4 = gettableData(5);
					GroupRequestForm.setGroupData(id,leader,mem1,mem2,mem3,mem4);
				}else if (MyString.LoanRequestForm.equals(form)) {
					String id = gettableData(0);
					String name  = gettableData(1);
					String NRC = gettableData(2);
					String address = gettableData(3);
					String phno = gettableData(4);
					String DOB = gettableData(5);
					String home = gettableData(6);
					String job = gettableData(7);
					String salary = gettableData(8);
					LoanRequestForm.setClientData(id,name,NRC,address,phno,DOB,home,job,salary);
				}
				
				dispose();
				
			}
		});
		btnOK.setBounds(825, 92, 89, 23);
		contentPane.add(btnOK);
	}
}
