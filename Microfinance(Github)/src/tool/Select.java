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

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Select extends JFrame {

	private JPanel contentPane;
	private JTextField textSearch;
	private JTable table;
	private String FormType = "Test";
	public String ClientID;
	DefaultTableModel dtm = new DefaultTableModel();
	UQueries msql = new UQueries();
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
	public  Select(String FormType) {
		this.FormType = FormType;
		initialize();
		createTable();
	}
	
	public void createTable() {
		table.setModel(msql.getAllClient());
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
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
		panel.setLayout(new MigLayout("", "[96.00px][109px][109px][143px][89px]", "[23px]"));
		
		JLabel lblNewLabel = new JLabel("Search With");
		panel.add(lblNewLabel, "cell 0 0,growx,aligny center");
		
		JRadioButton RadioID = new JRadioButton("ID");
		panel.add(RadioID, "cell 1 0,growx,aligny top");
		
		JRadioButton RadioName = new JRadioButton("Name");
		panel.add(RadioName, "cell 2 0,growx,aligny top");
		
		if (FormType.equals("ONE")) {
			RadioID.setText("Client ID");
			RadioName.setText("Client Name");
		}else if (FormType.equals("GROUP")) {
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
				textSearch.setText(FormType);
			}
		});
		panel.add(btnSearch, "cell 4 0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 58, 800, 534);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (FormType.equals("ONE")) {
					ClientEntry clientEntry = new ClientEntry();
					clientEntry.setVisible(true);
				}else if (FormType.equals("GROUP")) {
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
				GroupEntry.setID(table.getValueAt(table.getSelectedRow(),1).toString(),table.getValueAt(table.getSelectedRow(),0).toString());
				dispose();
			}
		});
		btnOK.setBounds(825, 92, 89, 23);
		contentPane.add(btnOK);
	}
}
