package entryForm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Choice;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

public class OfficerEntry extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblName;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_7;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

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

	/**
	 * Create the frame.
	 */
	public OfficerEntry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 450, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[428.00,grow]", "[31.00][182.00,grow][30.00]"));
		
		JPanel panelhead = new JPanel();
		contentPane.add(panelhead, "cell 0 0,grow");
		panelhead.setLayout(new MigLayout("", "[][]", "[]"));
		
		JLabel lblNewLabel = new JLabel("ID");
		panelhead.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		textField.setEditable(false);
		panelhead.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JPanel panelbody = new JPanel();
		panelbody.setBorder(new TitledBorder(null, "Officer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelbody, "cell 0 1,grow");
		panelbody.setLayout(new MigLayout("", "[][][][51.00][32.00][74.00][50.00][95.00][46.00]", "[][30][28.00][28.00][28.00][28.00][28.00][28.00][28.00]"));
		
		JPanel panelbtn = new JPanel();
		contentPane.add(panelbtn, "cell 0 2,grow");
		panelbtn.setLayout(new MigLayout("", "[grow][][][]", "[]"));
		
		JButton btnAdd = new JButton("Add");
		panelbtn.add(btnAdd, "cell 1 0");
		
		JButton btnNewButton = new JButton("Cancel");
		panelbtn.add(btnNewButton, "cell 3 0");
		
		lblName = new JLabel("Name");
		panelbody.add(lblName, "cell 1 1,alignx center,growy");
		
		textField_1 = new JTextField();
		panelbody.add(textField_1, "cell 3 1 4 1,grow");
		textField_1.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		panelbody.add(lblAddress, "cell 1 2,alignx center,growy");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panelbody.add(textField_2, "cell 3 2 5 1,grow");
		
		JLabel lblPhone = new JLabel("Phone");
		panelbody.add(lblPhone, "cell 1 3,alignx center,growy");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panelbody.add(textField_3, "cell 3 3 4 1,grow");
		
		JLabel lblNrc = new JLabel("NRC");
		panelbody.add(lblNrc, "cell 1 4,alignx center,growy");
		
		Choice choice = new Choice();
		panelbody.add(choice, "cell 3 4,grow");
		
		JLabel lblNewLabel_1 = new JLabel("/");
		panelbody.add(lblNewLabel_1, "cell 4 4,alignx center,growy");
		
		Choice choice_1 = new Choice();
		panelbody.add(choice_1, "cell 5 4,grow");
		
		Choice choice_2 = new Choice();
		panelbody.add(choice_2, "cell 6 4,grow");
		
		textField_7 = new JTextField();
		panelbody.add(textField_7, "cell 7 4,grow");
		textField_7.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		panelbody.add(lblRole, "cell 1 5,alignx center,growy");
		
		Choice choice_3 = new Choice();
		panelbody.add(choice_3, "cell 3 5 2 1,grow");
		
		JLabel lblUsername = new JLabel("UserName");
		panelbody.add(lblUsername, "cell 1 6,alignx center,growy");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panelbody.add(textField_4, "cell 3 6 3 1,grow");
		
		JLabel lblPassword = new JLabel("Password");
		panelbody.add(lblPassword, "cell 1 7,alignx center,growy");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panelbody.add(textField_5, "cell 3 7 3 1,grow");
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		panelbody.add(lblConfirmPassword, "cell 1 8,alignx center,growy");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		panelbody.add(textField_6, "cell 3 8 3 1,grow");
	}

}
