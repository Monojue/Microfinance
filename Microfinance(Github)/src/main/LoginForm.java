package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import tool.Checking;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPassword;
	Checking check = new Checking();
	private JLabel lblError;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean check() {
		if (check.IsNull(txtName.getText().trim())) {
			showError("UserName Requierd!");
		}else if (check.IsNull(txtPassword.getText().trim())) {
			showError("Password Required!");
		}else {
			hideError();
			return true;
		}
		
		return false;
	}
	
	public void showError(String str) {
		lblError.setText(str);
		lblError.setVisible(true);
	}
	
	public void hideError() {
		lblError.setVisible(false);
	}
	public LoginForm() {
		Initialize();
		
	}
	public void Initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 410, 382);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[30][60][60][181px][56.00px][60][30]", "[45.00][43px][20px][51.00][29px][16.00][29px][25.00][21px]"));
		
		JLabel label = new JLabel("MICROFINANCE");
		label.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 32));
		panel.add(label, "cell 2 1 3 1,alignx center,aligny center");
		
		JLabel label_1 = new JLabel("Management Software");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel.add(label_1, "cell 2 2 3 1,alignx center,aligny center");
		
		JLabel lblNewLabel = new JLabel("UserName");
		panel.add(lblNewLabel, "cell 1 4 2 1,alignx center,aligny center");
		
		txtName = new JTextField();
		panel.add(txtName, "cell 3 4 2 1,grow");
		txtName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword, "cell 1 6 2 1,alignx center,aligny center");
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		panel.add(txtPassword, "cell 3 6 2 1,grow");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setVisible(false);
		panel.add(lblError, "cell 1 7 5 1,alignx center,growy");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (check()) {
					
				}
			}
		});
		panel.add(btnLogin, "cell 2 8 3 1,alignx center,aligny top");
	}
}
