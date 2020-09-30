package main;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import entryForm.ClientEntry;
import entryForm.GroupEntry;
import net.miginfocom.swing.MigLayout;
import tool.MyString;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.JTableHeader;

import database.UQueries;

import javax.swing.border.BevelBorder;
import javax.swing.ButtonGroup;

public class GroupPanel extends JPanel {

	private JTextField textSearch;
	private JTable table;
	private UQueries usql = new UQueries();
	private final ButtonGroup radioGroup = new ButtonGroup();
	private JLabel lblError;
	private JPanel panel;
	private JScrollPane scrollPane;
	/**
	 * Create the panel.
	 */
	
	

	public GroupPanel() {
		Initialize();
		createTable();
	}
	
	public void createTable() {
		table.setModel(usql.getGroup(null, MyString.All));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
	
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

	        System.out.println("GroupFrame: " + width + "x" + height);
	        System.out.println("GroupChangeSize: " + paddedWidth + "x" + paddedHeight);
	        System.out.println("GroupResut: " + resultWidth + "x" + resultHeight);
//	        System.out.println("Ratio: " + (float)resultWidth / resultHeight);
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
		panel.setLayout(new MigLayout("", "[][][][][][140:151.00:140][][224,grow][][][][][][][][]", "[grow]"));
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel.add(lblNewLabel, "cell 0 0");
		
		JLabel lblPrefix = new JLabel("GP-");
		panel.add(lblPrefix, "cell 4 0,alignx trailing");
		
		JRadioButton RadioID = new JRadioButton("Group ID");
		radioGroup.add(RadioID);
		RadioID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPrefix.setVisible(true);
			}
		});
		RadioID.setSelected(true);
		RadioID.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioID, "cell 1 0");
		
		JRadioButton RadioName = new JRadioButton("Leader Name");
		radioGroup.add(RadioName);
		RadioName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPrefix.setVisible(false);
			}
		});
		RadioName.setBackground(Color.LIGHT_GRAY);
		panel.add(RadioName, "cell 2 0");
		
		
		
		textSearch = new JTextField();
		panel.add(textSearch, "cell 5 0,growx");
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (radioGroup.isSelected(RadioID.getModel())) {
					if (textSearch.getText().equals("")) {
						showError("Please Type Group ID To Search");
					}else {
						table.setModel(usql.getGroup("GP-"+textSearch.getText().trim(),MyString.ID));;
					}
				}else if (radioGroup.isSelected(RadioName.getModel())) {
					if (textSearch.getText().equals("")) {
						showError("Please Type Leader Name To Search");
					}else {
						table.setModel(usql.getGroup(textSearch.getText().trim(),MyString.Name));;
				}
				
			}
				table.getColumnModel().getColumn(0).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(200);
				table.getColumnModel().getColumn(4).setPreferredWidth(200);
				table.getColumnModel().getColumn(5).setPreferredWidth(200);
			}
		});
		panel.add(btnSearch, "cell 6 0");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		panel.add(lblError, "cell 7 0,grow");
		
		JButton btnNewButton_1 = new JButton("New Group");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GroupEntry(null).setVisible(true);
			}
		});
		panel.add(btnNewButton_1, "cell 8 0");
		
		JButton btnNewButton_2 = new JButton("Edit Group");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Group to Edit!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String GroupID = (String) table.getValueAt(table.getSelectedRow(),0);
					new GroupEntry(GroupID).setVisible(true);
				}
			}
		});
		panel.add(btnNewButton_2, "cell 10 0");
		
		JButton btnDeleteGroup = new JButton("Delete Group");
		btnDeleteGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password  = "";
				String message = "This Group is founded in ";
				JPasswordField passwordField = new JPasswordField();
				passwordField.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
				Object[] obj = {"Are you sure want to Delete!"
						+ "\n Please Type Password To Delete!", passwordField};
				Object stringArray[] = {"OK","Cancel"};
				
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Group to Delete!","Error!",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String GroupID = (String) table.getValueAt(table.getSelectedRow(),0);
					System.out.println(usql.getLoanIDfromGroupID(GroupID));
					Vector<String> found = usql.checkBeforeDelete("group", GroupID);
					
					if (found.size()>0) {
						for (int i = 0; i < found.size(); i++) {
							message += "\n"+found.get(i); 
						}
						Object[] objmsg = {message + "\n Please Type Password To Delete!", passwordField};
						while(password.isEmpty()){
							if (JOptionPane.showOptionDialog(null, objmsg, "Warning!",
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
								usql.AutoDelete("group", found.get(j), GroupID);
							}
							
							if (usql.deleteGroupFromGroupID(GroupID)) {
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
								usql.AutoDelete("group", found.get(j), GroupID);
							}
							
							if (usql.deleteGroupFromGroupID(GroupID)) {
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
		panel.add(btnDeleteGroup, "cell 12 0");
		
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 1039, 510);
//		scrollPane.setBounds(10, 59, MyString.panelWidth, MyString.panelHeight);
		add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		header.setForeground(Color.BLACK);
		header.setFont(new Font("SansSerif", Font.BOLD , 14));
		scrollPane.setViewportView(table);
	}
}
