package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.MyQueries;

import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import tool.Calculation;

import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentSchedule extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String LID,Name;
	
	MyQueries msql = new MyQueries();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentSchedule frame = new PaymentSchedule(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param Name 
	 * @param Duration 
	 * @param Amount 
	 * @param LID 
	 */
	public PaymentSchedule(String LID,String Name) {
		initialize();
		setTitle(Name.toUpperCase()+ " Loan Payment Time Table");
		createTable(LID,Name);
	}
	
	public void createTable(String LID, String Name) {
		String[] LoanDetails = msql.GetLoanRequestData(LID);
		int Amount = Integer.parseInt(LoanDetails[1]);
		int Duration = Integer.parseInt(LoanDetails[2]);
		float Rate = Float.parseFloat(LoanDetails[3]);
		DefaultTableModel DTableCalculation = Calculation.calculator(Amount, Duration, Rate);
		DefaultTableModel dtm = new DefaultTableModel(Duration,3);
		
		LocalDate today = LocalDate.now();
				
		for(int i = 0; i < Duration; i++) {
			dtm.setValueAt(i+1,i,0);
			dtm.setValueAt(DTableCalculation.getValueAt(i, 4),i,1);
			dtm.setValueAt(today.plusMonths(i+1), i, 2);
		}
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(0).setHeaderValue("No");
		table.getColumnModel().getColumn(1).setHeaderValue("Installment");
		table.getColumnModel().getColumn(2).setHeaderValue("Schedule");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 564, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 526, 473);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(20);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setForeground(Color.BLACK);
		header.setFont(new Font("SansSerif", Font.BOLD , 16));
		scrollPane.setViewportView(table);
		
		JButton btnCancle = new JButton("Cancle");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setBounds(453, 495, 85, 21);
		contentPane.add(btnCancle);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(358, 495, 85, 21);
		contentPane.add(btnPrint);
		
		
	}
}
