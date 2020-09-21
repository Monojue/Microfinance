package main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import database.MyQueries;
import entryForm.GroupRequestForm;
import entryForm.LoanRequestForm;
import net.miginfocom.swing.MigLayout;
import tool.MyString;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

public class LoanPanel extends JPanel {

	private JTextField textField;
	//private JTable table;
	private JTable tableIndividual;
	private JTextField textField_1;
	private JTextField textField_2;

	MyQueries msql = new MyQueries();

	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JScrollPane scrollPane_1;
	private JPanel panel_4;
	private JTable tableGroup;
	private JButton btnIPaid;
	private JButton btnGPaid;
	private JRadioButton rdoIAccept;
	private JRadioButton rdoIRejected;
	private JRadioButton rdoGAccepted;
	private JRadioButton rdoGRejected;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	
	public LoanPanel() {
		Initialize();
		createITable();
		createGTable();
	}
	
	public void createITable() {
		tableIndividual.setModel(msql.getIndividualApprovedLoanRequest());
		tableIndividual.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIndividual.getColumnModel().getColumn(1).setMinWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setMaxWidth(0);
		tableIndividual.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
	
	public void createRejectedITable() {
		tableIndividual.setModel(msql.getIndividualRejectedLoanRequest());
		tableIndividual.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIndividual.getColumnModel().getColumn(1).setMinWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setMaxWidth(0);
		tableIndividual.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableIndividual.getColumnModel().getColumn(4).setPreferredWidth(100);
	}

	

	 public void updatePanelSize() {

//	        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
//	                .getDefaultScreenDevice();
//	        float monitorWidth = gd.getDisplayMode().getWidth();
//	        float monitorHeight = gd.getDisplayMode().getHeight();
	//
//	        // Aspect ratio of the monitor in decimal form.
//	        float monitorRatio = monitorWidth / monitorHeight;

//	        JComponent parent = (JComponent) getParent();
	        float width = getWidth();
	        float height = getHeight();

//	        width = Math.min(width, height * monitorRatio);
//	        height = width / monitorRatio;

	        // I am subtracting the width and height by their respective aspect ratio...
	        int paddedWidth = (int) width - 20;
	        int paddedHeight = (int) height - 70;
//	        setPreferredSize(new Dimension(paddedWidth, paddedHeight));
	        setBounds(0,0,paddedWidth,paddedHeight);
//	        panel.setBounds(10,11,paddedWidth+50,37);
	        panel_2.setBounds(10,11,paddedWidth-20,37);
	        panel_4.setBounds(10,11,paddedWidth-20,37);
	        tabbedPane.setBounds(10,10,paddedWidth,paddedHeight+40);
	        scrollPane.setBounds(10,59,paddedWidth-25,paddedHeight);
	        scrollPane_1.setBounds(10,59,paddedWidth-25,paddedHeight);
	        
	        int resultWidth = getWidth();
	        int resultHeight = getHeight();
	        if (paddedWidth != resultWidth && paddedHeight != resultHeight) {
	            revalidate(); // preferred dimensions not applied, so force them
	        }

	        System.out.println("GroupFrame: " + width + "x" + height);
	        System.out.println("GroupChangeSize: " + paddedWidth + "x" + paddedHeight);
	        System.out.println("GroupResut: " + resultWidth + "x" + resultHeight);
//	        System.out.println("Ratio: " + (float)resultWidth / resultHeight);
	    }

	public void createGTable() {
		tableGroup.setModel(msql.getGroupApprovedLoanRequest());
		tableGroup.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(1).setMinWidth(0);
		tableGroup.getColumnModel().getColumn(1).setMaxWidth(0);
		tableGroup.getColumnModel().getColumn(1).setWidth(0);
		tableGroup.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(9).setPreferredWidth(100);
	}
	public void createRejectedGTable() {
		tableGroup.setModel(msql.getGroupRejectedLoanRequest());
		tableGroup.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(1).setMinWidth(0);
		tableGroup.getColumnModel().getColumn(1).setMaxWidth(0);
		tableGroup.getColumnModel().getColumn(1).setWidth(0);
		tableGroup.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(9).setPreferredWidth(100);
		tableGroup.getColumnModel().getColumn(10).setPreferredWidth(100);
	}
	
	public void PaidDay(String ID,String Amount) {
		String[] PayDay = new String[2];
		PayDay[0] = ID;
		PayDay[1] = java.time.LocalDate.now().toString();
		boolean update = msql.UpdateData("paidday", PayDay);
		
		if (update) {
			JOptionPane.showMessageDialog(null, Amount +" is Paid Sucessfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(!update) {
			JOptionPane.showMessageDialog(null, "Failed to Approve Request!","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Individual", panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 1034, 497);
		panel.add(scrollPane);
		
		tableIndividual = new JTable();
		scrollPane.setViewportView(tableIndividual);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 11, 1034, 34);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][][][159.00][][][][][][27.00,grow][][][][]", "[]"));
		
		JButton btnNewButton = new JButton("New Individual Loan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoanRequestForm().setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel_2.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Loan Request ID");
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdbtnNewRadioButton, "cell 1 0,growx,aligny center");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Customer ID");
		rdbtnNewRadioButton_1.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdbtnNewRadioButton_1, "cell 2 0,growx,aligny center");
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "cell 3 0,growx,aligny center");
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Search");
		panel_2.add(btnNewButton_2, "cell 4 0,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("View Table Of");
		panel_2.add(lblNewLabel_1, "cell 6 0");
		
		rdoIAccept = new JRadioButton("Accepted");
		rdoIAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createITable();
				btnIPaid.setText("Paid");
			}
		});
		buttonGroup.add(rdoIAccept);
		rdoIAccept.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdoIAccept, "cell 7 0");
		
		rdoIRejected = new JRadioButton("Rejected");
		rdoIRejected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRejectedITable();
				btnIPaid.setText("Informed");
			}
		});
		buttonGroup.add(rdoIRejected);
		rdoIRejected.setBackground(Color.LIGHT_GRAY);
		panel_2.add(rdoIRejected, "cell 8 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_2.add(separator, "cell 5 0,grow");
		panel_2.add(btnNewButton, "cell 10 0,growx,aligny center");
		
		btnIPaid = new JButton("Paid");
		btnIPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnIPaid.getText() == "Paid") {
					if(tableIndividual.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Client Request","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						PaidDay((String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),0),(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),3));
						createITable();
					}				
				}
				else if(btnIPaid.getText()=="Informed") {
					if(tableIndividual.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Individual Request to Inform","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						boolean delete = msql.DeleteData("loanrequest1",(String)tableIndividual.getValueAt(tableIndividual.getSelectedRow(),0));
						if(delete) {
							JOptionPane.showMessageDialog(null, "A loan Request is Deleted Sucessfully","Success!",JOptionPane.INFORMATION_MESSAGE);
							createRejectedITable();
						}
						else {
							JOptionPane.showMessageDialog(null, "Cant Delete A Request","Error!",JOptionPane.INFORMATION_MESSAGE);
						}
					}				
				}			
			}
		});
		panel_2.add(btnIPaid, "cell 10 0,growx,aligny center");
		
		JButton button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdoIRejected.isSelected()==true) {
					createRejectedITable();
				}
				else {
					createITable();
				}
			}
		});
		panel_2.add(button, "cell 12 0,growx,aligny center");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Group", panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 56, 1034, 497);
		panel_1.add(scrollPane_1);
		

		panel_4 = new JPanel();

		tableGroup = new JTable();
		scrollPane_1.setColumnHeaderView(tableGroup);
		
		

		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(10, 11, 1034, 34);
		panel_1.add(panel_4);
//		panel_4.setLayout(new MigLayout("", "[58px][105px][85px][159px][65px][][129px][][71px]", "[23px]"));
		panel_4.setLayout(new MigLayout("", "[][][][159.00][][][][][][grow][][][][]", "[]"));
		
		JLabel label = new JLabel("Search With");
		panel_4.add(label, "cell 0 0,growx,aligny center");
		
		JRadioButton radioButton = new JRadioButton("Loan Request ID");
		radioButton.setBackground(Color.LIGHT_GRAY);
		panel_4.add(radioButton, "cell 1 0,growx,aligny center");
		
		JRadioButton rdbtnGroupId = new JRadioButton("Group ID");
		rdbtnGroupId.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdbtnGroupId, "cell 2 0,growx,aligny center");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_4.add(textField_2, "cell 3 0,growx,aligny center");
		
		JButton button_1 = new JButton("Search");
		panel_4.add(button_1, "cell 4 0,growx,aligny center");
		
		ButtonGroup BG = new ButtonGroup();
		
		JLabel lblNewLabel_2 = new JLabel("View Table Of");
		panel_4.add(lblNewLabel_2, "cell 6 0");
		
		rdoGAccepted = new JRadioButton("Accepted");
		rdoGAccepted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createGTable();
				btnGPaid.setText("Paid");
			}
		});
		rdoGAccepted.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdoGAccepted, "cell 7 0");
		BG.add(rdoGAccepted);
		
		rdoGRejected = new JRadioButton("Rejected");
		rdoGRejected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRejectedGTable();
				btnGPaid.setText("Informed");
			}
		});
		rdoGRejected.setBackground(Color.LIGHT_GRAY);
		panel_4.add(rdoGRejected, "cell 8 0");
		BG.add(rdoGRejected);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_4.add(separator_1, "cell 5 0,grow");
		
		
		
		JButton btnNewGroupLoan = new JButton("New Group Loan");
		btnNewGroupLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GroupRequestForm().setVisible(true);
			}
		});
		panel_4.add(btnNewGroupLoan, "cell 10 0,growx,aligny center");
		
		btnGPaid = new JButton("Paid");
		btnGPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnGPaid.getText() == "Paid") {
					if(tableGroup.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Group Request","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						PaidDay((String)tableGroup.getValueAt(tableGroup.getSelectedRow(),0),(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),7));
						createGTable();
					}				
				}
				else if(btnGPaid.getText()=="Informed") {
					if(tableGroup.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Group Request to Inform Them","Error!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						boolean delete = msql.DeleteData("loanrequest2",(String)tableGroup.getValueAt(tableGroup.getSelectedRow(),0));
						if(delete) {
							JOptionPane.showMessageDialog(null, "A loan Request is Deleted Sucessfully","Success!",JOptionPane.INFORMATION_MESSAGE);
							createRejectedGTable();
						}
						else {
							JOptionPane.showMessageDialog(null, "Cant Delete A Request","Error!",JOptionPane.INFORMATION_MESSAGE);
						}
					}				
				}
			}
		});
		panel_4.add(btnGPaid, "cell 10 0,growx,aligny center");
		
		JButton button_3 = new JButton("Refresh");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdoGRejected.isSelected()==true) {
					createRejectedGTable();
				}
				else {
					createGTable();
				}
			}
		});
		panel_4.add(button_3, "cell 12 0,growx,aligny center");
		

	}
}
