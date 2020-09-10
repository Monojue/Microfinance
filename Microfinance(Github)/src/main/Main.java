package main;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entryForm.ClientEntry;
import entryForm.GroupEntry;
import entryForm.GroupRequestForm;
import entryForm.LoanRequestForm;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("MICROFINANACE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 30, 1095, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 1059, 606);
		contentPane.add(tabbedPane);
		
		ClientPanel clientPanel = new ClientPanel();
		tabbedPane.addTab("          CLIENT          ", clientPanel);
		
		
		GroupPanel groupPanel = new GroupPanel();
		tabbedPane.addTab("          GROUP          ", groupPanel);
		groupPanel.setLayout(null);
		
		LoanPanel loanPanel = new LoanPanel();
		tabbedPane.addTab("          LOAN          ", loanPanel);
		
		PaymentPanel paymentPanel = new PaymentPanel();
		tabbedPane.addTab("          REPAYMENT          ", paymentPanel);
		
		ReportPanel reportPanel = new ReportPanel();
		tabbedPane.addTab("          REPORT          ", reportPanel);
		
		SettingPanel settingPanel = new SettingPanel();
		tabbedPane.addTab("          SETTING          ", settingPanel);
	}
}
