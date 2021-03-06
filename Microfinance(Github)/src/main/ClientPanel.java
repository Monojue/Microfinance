package main;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import database.MyQueries;
import database.UQueries;
import entryForm.ClientEntry;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;
import tool.MyString;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class ClientPanel extends JPanel {
	private JTextField textSearch;
	private JTable table;
	private JTextField test;
	UQueries usql = new UQueries();
	public static final Color VERY_LIGHT_Grey = new Color(230,230,230);
	MyQueries msql = new MyQueries();
	private ButtonGroup radioGroup = new ButtonGroup();
	private JLabel lblPrefix;
	private JLabel lblError;
	public String ClientID;
	private JPanel panel;
	private JScrollPane scrollPane;
	/**
	 * Create the panel.
	 */
	

	public ClientPanel() {
		Initialize();
		createTable();
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
	        panel.setBounds(10,11,paddedWidth,37);
	        scrollPane.setBounds(10,59,paddedWidth,paddedHeight);
	        
	        int resultWidth = getWidth();
	        int resultHeight = getHeight();
	        if (paddedWidth != resultWidth && paddedHeight != resultHeight) {
	            revalidate(); // preferred dimensions not applied, so force them
	        }

//	        System.out.println("Frame: " + width + "x" + height);
//	        System.out.println("ChangeSize: " + paddedWidth + "x" + paddedHeight);
//	        System.out.println("Resutl: " + resultWidth + "x" + resultHeight);
//	        System.out.println("Ratio: " + (float)resultWidth / resultHeight);
	    }
	
	public void createTable() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			table.setModel(usql.getClient(null, MyString.All));
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(50);
			table.getColumnModel().getColumn(7).setPreferredWidth(100);
			table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
	}
	public void showError(String error) {
		lblError.setText(error);
		lblError.setVisible(true);
	}
	public void Initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, MyString.panelWidth, MyString.panelHeight);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, MyString.panelWidth, 37);
		add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][140:151.00:140][][224.00,grow][][][][][][][][6.00]", "[grow]"));
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel.add(lblNewLabel, "cell 0 0");
		
		JRadioButton RadioID = new JRadioButton("Client ID");
		radioGroup.add(RadioID);
		RadioID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPrefix.setVisible(true);
			}
		});
		RadioID.setSelected(true);
		RadioID.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioID, "cell 1 0");
		
		JRadioButton RadioName = new JRadioButton("Client Name");
		radioGroup.add(RadioName);
		RadioName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPrefix.setVisible(false);
			}
		});
		RadioName.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioName, "cell 2 0");
		
		lblPrefix = new JLabel("CL-");
		panel.add(lblPrefix, "cell 4 0,alignx trailing");
		
		textSearch = new JTextField();
		panel.add(textSearch, "cell 5 0,growx");
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					if (radioGroup.isSelected(RadioID.getModel())) {
						if (textSearch.getText().equals("")) {
							showError("Please Type Client ID To Search");
						}else {
							table.setModel(usql.getClient("CL-"+textSearch.getText().trim(),MyString.ID));;
						}
					}else if (radioGroup.isSelected(RadioName.getModel())) {
						if (textSearch.getText().equals("")) {
							showError("Please Type Client Name To Search");
						}else {
							table.setModel(usql.getClient(textSearch.getText().trim(),MyString.Name));;
						}
					}
					DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
					rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
					table.getColumnModel().getColumn(0).setPreferredWidth(100);
					table.getColumnModel().getColumn(1).setPreferredWidth(150);
					table.getColumnModel().getColumn(2).setPreferredWidth(200);
					table.getColumnModel().getColumn(3).setPreferredWidth(250);
					table.getColumnModel().getColumn(4).setPreferredWidth(100);
					table.getColumnModel().getColumn(5).setPreferredWidth(100);
					table.getColumnModel().getColumn(6).setPreferredWidth(50);
					table.getColumnModel().getColumn(7).setPreferredWidth(100);
					table.getColumnModel().getColumn(7).setPreferredWidth(100);
					table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
			}
		});
		panel.add(btnSearch, "cell 6 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator, "flowx,cell 7 0,growy");
		
		JButton btnNewButton_1 = new JButton("New Client");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ClientEntry(null,null).setVisible(true);
			}
		});
		panel.add(btnNewButton_1, "cell 8 0,alignx center");
		
		JButton btnNewButton_2 = new JButton("Edit Client");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Client to Edit!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					ClientID = (String) table.getValueAt(table.getSelectedRow(),0);
					new ClientEntry(ClientID,null).setVisible(true);
				}
			}
		});
		panel.add(btnNewButton_2, "cell 10 0");
		
		JButton btnDeleteClient = new JButton("Delete Client");
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password  = "";
				String message = "This Client is founded in ";
				JPasswordField passwordField = new JPasswordField();
				passwordField.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
				Object[] obj = {"Are you sure want to Delete!"
						+ "\n Please Type Password To Delete!", passwordField};
				Object stringArray[] = {"OK","Cancel"};
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Client to Delete!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					ClientID = (String) table.getValueAt(table.getSelectedRow(),0);
					Vector<String> found = usql.checkBeforeDelete("client", ClientID);
					
					if (found.size()>0) {
						
						for (int i = 0; i < found.size(); i++) {
							message += "\n"+found.get(i); 
						}
						Object[] objmsg = {message + "\n Please Type Password To Delete!", passwordField};
						while(password.isEmpty()){
							if (JOptionPane.showOptionDialog(null, objmsg, "Warning!",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, objmsg) == JOptionPane.YES_OPTION)
										password = passwordField.getText().toString();
							else {
								password = "";
								return;
							}
						}
						if (usql.CheckPassword(MyString.LoginUser, password)) {
							for (int j = found.size()-1; j >= 0; j--) {
								System.out.println(found.get(j));
								usql.AutoDelete("client", found.get(j), ClientID);
							}
							
							if (usql.deleteClient(ClientID)) {
								JOptionPane.showMessageDialog(null, "Successfully Deleted!");
							}else {
								JOptionPane.showMessageDialog(null, "Error Occoured!");
							}
						}else {
							JOptionPane.showMessageDialog(null,  "Wrong Password!");
						}
					}else {
						while(password.isEmpty()){
							if (JOptionPane.showOptionDialog(null, obj, "Warning!",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION)
										password = passwordField.getText().toString();
							else {
								password = "";
								return;
							}
						}
						if (usql.CheckPassword(MyString.LoginUser, password)) {
							for (int j = found.size()-1; j >= 0; j--) {
								System.out.println(found.get(j));
								usql.AutoDelete("client", found.get(j), ClientID);
							}
							
							if (usql.deleteClient(ClientID)) {
								JOptionPane.showMessageDialog(null, "Successfully Deleted!");
							}else {
								JOptionPane.showMessageDialog(null, "Error Occoured!");
							}
						}else {
							JOptionPane.showMessageDialog(null,  "Wrong Password!");
						}
					}
				}
			}
		});
		panel.add(btnDeleteClient, "cell 12 0");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_1, "cell 13 0,grow");
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable();
			}
		});
		panel.add(btnNewButton_3, "cell 14 0");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		panel.add(lblError, "cell 7 0,grow");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
		add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setForeground(Color.BLACK);
		header.setFont(new Font("SansSerif", Font.BOLD , 14));
		scrollPane.setViewportView(table);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		defaults.putIfAbsent("Table.alternateRowColor", VERY_LIGHT_Grey);
	}
	

}
