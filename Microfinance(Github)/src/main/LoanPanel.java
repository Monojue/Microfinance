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
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class LoanPanel extends JPanel {

	private JTextField textField;
	private JTable table;
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
	/**
	 * Create the panel.
	 */
	
	public LoanPanel() {
		Initialize();
		createITable();
		//createGTable();
	}
	
	public void createITable() {
		tableIndividual.setModel(msql.getApprovedLoanRequest());
		tableIndividual.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIndividual.getColumnModel().getColumn(1).setMinWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setMaxWidth(0);
		tableIndividual.getColumnModel().getColumn(1).setWidth(0);
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
	
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Individual", new ImageIcon(LoanPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeClosed.gif")), panel, null);
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
		panel_2.setLayout(new MigLayout("", "[][][][159.00][][grow][][][][]", "[]"));
		
		JButton btnNewButton = new JButton("New Individual Loan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoanRequestForm(null,null,null,null).setVisible(true);
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
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_2.add(separator, "cell 5 0,grow");
		panel_2.add(btnNewButton, "cell 6 0,growx,aligny center");
		
		JButton button = new JButton("Refresh");
		panel_2.add(button, "cell 8 0,growx,aligny center");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Group", new ImageIcon(LoanPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeClosed.gif")), panel_1, "");
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 56, 1034, 497);
		panel_1.add(scrollPane_1);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(10, 11, 1034, 34);
		panel_1.add(panel_4);
//		panel_4.setLayout(new MigLayout("", "[58px][105px][85px][159px][65px][][129px][][71px]", "[23px]"));
		panel_4.setLayout(new MigLayout("", "[][][][159.00][][grow][][][][]", "[]"));
		
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
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_4.add(separator_1, "cell 5 0,grow");
		
		JButton btnNewGroupLoan = new JButton("New Group Loan");
		btnNewGroupLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GroupRequestForm().setVisible(true);
			}
		});
		panel_4.add(btnNewGroupLoan, "cell 6 0,growx,aligny center");
		
		JButton button_3 = new JButton("Refresh");
		panel_4.add(button_3, "cell 8 0,growx,aligny center");
		

	}
}
