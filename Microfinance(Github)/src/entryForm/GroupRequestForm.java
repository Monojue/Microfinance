package entryForm;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import database.DBConnection;
import net.miginfocom.swing.MigLayout;
import tool.MyDate;
import tool.Select;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Choice;
import javax.swing.JList;
import java.awt.Button;

public class GroupRequestForm extends JFrame{

	private JTextField LoanAmount;
	private JTextField LoanDuration;
	private JTable table;
	private JTextField textID;
	private JTextField textField_11;
	private String Require = "* Required";
	private String Invalid = "* Invalid";
	private JTextField textDate;
	LoanRequest loanRequest = new LoanRequest();
	MyDate myDate = new MyDate();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupRequestForm window = new GroupRequestForm();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	
	public GroupRequestForm() {
		initialize();
		textID.setText(loanRequest.getAutoID());
		textDate.setText(myDate.getdate());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setTitle("Group Registration Form");
		this.setBounds(260, 30, 857, 649);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 0, 821, 563);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(new Rectangle(0, 0, 5, 0));
		panel_2.setBounds(10, 11, 811, 40);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[96.00px][120.00px,grow][436.00][40px][118.00px,grow]", "[26px]"));
		
		JLabel lblGroupId = new JLabel("Loan Request ID");
		lblGroupId.setBackground(Color.RED);
		lblGroupId.setForeground(Color.BLACK);
		panel_2.add(lblGroupId, "cell 0 0,alignx trailing,growy");
		
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
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LOAN INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 368, 394, 185);
		panel.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[65px][56px][159px][77px]", "[26px][20px][][][14px][23px][]"));
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		panel_4.add(lblNewLabel_2, "cell 0 0,alignx left,aligny center");
		
		LoanAmount = new JTextField();
		panel_4.add(LoanAmount, "cell 1 0,growx,aligny center");
		LoanAmount.setColumns(10);
		
		JSlider slider_1 = new JSlider();
		slider_1.setBackground(Color.WHITE);
		slider_1.setToolTipText("");
		slider_1.setPaintTicks(true);
		slider_1.setSnapToTicks(true);
		slider_1.setMinimum(100000);
		slider_1.setMaximum(1000000);
		slider_1.setMajorTickSpacing(50000);
		panel_4.add(slider_1, "cell 2 0,growx,aligny center");
		
		JLabel label_9 = new JLabel("* Require");
		label_9.setForeground(Color.RED);
		panel_4.add(label_9, "cell 3 0");
		
		JLabel lblNewLabel_3 = new JLabel("Duration");
		panel_4.add(lblNewLabel_3, "cell 0 1,growx,aligny center");
		
		LoanDuration = new JTextField();
		panel_4.add(LoanDuration, "cell 1 1,growx,aligny center");
		LoanDuration.setColumns(10);
		
		JSlider slider = new JSlider();
		slider.setBackground(Color.WHITE);
		slider.setMajorTickSpacing(3);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setMinimum(6);
		slider.setMaximum(24);
		slider.setMinorTickSpacing(3);
		panel_4.add(slider, "cell 2 1,growx,aligny center");
		
		JLabel label_10 = new JLabel("* Require");
		label_10.setForeground(Color.RED);
		panel_4.add(label_10, "cell 3 1");
		
		JLabel lblNewLabel_5 = new JLabel("Service Fees");
		panel_4.add(lblNewLabel_5, "cell 0 2");
		
		JLabel label_3 = new JLabel("Rate%");
		panel_4.add(label_3, "cell 1 2");
		
		JLabel lblNewLabel_4 = new JLabel("Interest Rate");
		panel_4.add(lblNewLabel_4, "cell 0 4,alignx left,aligny top");
		
		JLabel lblRate = new JLabel("Rate%");
		panel_4.add(lblRate, "cell 1 4,alignx left,aligny top");
		
		JButton btnCalculate = new JButton("Calculate");
		panel_4.add(btnCalculate, "cell 3 6,alignx left,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(414, 60, 407, 493);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "GROUP INFORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 61, 394, 297);
		panel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[61px][151px,grow][157px]", "[30][30][30][30][30][30][30][::10][]"));
		
		JLabel lblNewLabel = new JLabel("Group ID");
		panel_1.add(lblNewLabel, "cell 0 0,alignx left");
		
		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setEditable(false);
		panel_1.add(textField_11, "cell 1 0,growx,aligny center");
		textField_11.setColumns(10);
		
		Label label = new Label("Leader");
		panel_1.add(label, "cell 0 2,grow");
		
		Label label_1 = new Label("Name");
		panel_1.add(label_1, "cell 1 1,alignx center,aligny top");
		
		TextField textField = new TextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		panel_1.add(textField, "cell 2 2,grow");
		
		Label label_2 = new Label("Client ID");
		panel_1.add(label_2, "cell 2 1,alignx center,aligny top");
		
		TextField textField_1 = new TextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		panel_1.add(textField_1, "cell 2 3,grow");
		
		TextField textField_2 = new TextField();
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		panel_1.add(textField_2, "cell 2 4,grow");
		
		TextField textField_3 = new TextField();
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		panel_1.add(textField_3, "cell 2 5,grow");
		
		TextField textField_4 = new TextField();
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		panel_1.add(textField_4, "cell 2 6,grow");
		
		TextField textField_5 = new TextField();
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		panel_1.add(textField_5, "cell 1 6,grow");
		
		TextField textField_6 = new TextField();
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		panel_1.add(textField_6, "cell 1 5,grow");
		
		TextField textField_7 = new TextField();
		textField_7.setEnabled(false);
		textField_7.setEditable(false);
		panel_1.add(textField_7, "cell 1 4,grow");
		
		TextField textField_8 = new TextField();
		textField_8.setEnabled(false);
		textField_8.setEditable(false);
		panel_1.add(textField_8, "cell 1 3,grow");
		
		TextField textField_9 = new TextField();
		textField_9.setEnabled(false);
		textField_9.setEditable(false);
		panel_1.add(textField_9, "cell 1 2,grow");
		
		Label label_4 = new Label("Member 1");
		panel_1.add(label_4, "cell 0 3,alignx left,growy");
		
		Label label_6 = new Label("Member 2");
		panel_1.add(label_6, "cell 0 4,alignx left,growy");
		
		Label label_7 = new Label("Member 3");
		panel_1.add(label_7, "cell 0 5,alignx left,growy");
		
		Label label_8 = new Label("Member 4");
		panel_1.add(label_8, "cell 0 6,alignx left,growy");
		
		JButton btnNewButton = new JButton("Select Group");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Select select = new Select("GROUP");
				select.setVisible(true);
			}
		});
		panel_1.add(btnNewButton, "cell 2 8,alignx right");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(742, 574, 89, 23);
		this.getContentPane().add(btnCancel);
		
		JButton btnRequestLoan = new JButton("Request Loan");
		btnRequestLoan.setBounds(610, 574, 122, 23);
		this.getContentPane().add(btnRequestLoan);
	}
}
