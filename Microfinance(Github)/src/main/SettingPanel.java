package main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SettingPanel extends JPanel {
	private JTextField Iduration;
	private JTextField Iinterval;
	private JTextField Irate;
	private JTextField Ifees;
	private JTextField Gduration;
	private JTextField GInterval;
	private JTextField Grate;
	private JTextField Gfees;
	private AbstractButton IEdit;
	private JButton Icancel;
	private JButton GEdit;
	private JButton Gcancel;

	/**
	 * Create the panel.
	 */
	public SettingPanel() {
		initialize();
		fieldDisable();
		
	}
	
	public void fieldDisable() {
		Iduration.setEditable(false);
		Iinterval.setEditable(false);
		Irate.setEditable(false);
		Ifees.setEditable(false);
		Gduration.setEditable(false);
		GInterval.setEditable(false);
		Grate.setEditable(false);
		Gfees.setEditable(false);
		Icancel.setVisible(false);
		Gcancel.setVisible(false);
		IEdit.setText("Edit");
		GEdit.setText("Edit");
	}
	
	public void IndividualfieldEnable() {
		Iduration.setEditable(true);
		Iinterval.setEditable(true);
		Irate.setEditable(true);
		Ifees.setEditable(true);
		IEdit.setText("Save");
		Icancel.setVisible(true);
	}
	
	public void GroupfieldEnable() {
		Gduration.setEditable(true);
		GInterval.setEditable(true);
		Grate.setEditable(true);
		Gfees.setEditable(true);
		GEdit.setText("Save");
		Gcancel.setVisible(true);
	}
	
	public void initialize() {
		setBorder(new LineBorder(Color.ORANGE));
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 1059, 580);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Loan Setting", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 690, 558);
		add(panel);
		panel.setLayout(new MigLayout("", "[][][20][][][][][][][][grow][][20][][][][][][][]", "[10][][][30][30][30][30][30][]"));
		
		JLabel lblNewLabel = new JLabel("Individual");
		panel.add(lblNewLabel, "cell 1 1");
		
		JLabel lblNewLabel_1 = new JLabel("Group");
		panel.add(lblNewLabel_1, "cell 11 1");
		
		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1, "cell 11 2 9 1,growx");
		
		JSeparator separator = new JSeparator();
		panel.add(separator, "cell 1 2 9 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Duration");
		panel.add(lblNewLabel_2, "cell 1 3");
		
		Iduration = new JTextField();
		Iduration.setEditable(false);
		panel.add(Iduration, "cell 3 3 5 1,growx,aligny center");
		Iduration.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Month");
		panel.add(lblNewLabel_6, "cell 8 3");
		
		JLabel label = new JLabel("Duration");
		panel.add(label, "cell 11 3");
		
		Gduration = new JTextField();
		Gduration.setEditable(false);
		Gduration.setColumns(10);
		panel.add(Gduration, "cell 13 3 5 1,growx");
		
		JLabel label_4 = new JLabel("Month");
		panel.add(label_4, "cell 18 3");
		
		JLabel lblNewLabel_5 = new JLabel("Interval");
		panel.add(lblNewLabel_5, "cell 1 4");
		
		Iinterval = new JTextField();
		Iinterval.setEditable(false);
		panel.add(Iinterval, "cell 3 4 5 1,growx");
		Iinterval.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Month");
		panel.add(lblNewLabel_7, "cell 8 4");
		
		JLabel label_1 = new JLabel("Interval");
		panel.add(label_1, "cell 11 4");
		
		GInterval = new JTextField();
		GInterval.setEditable(false);
		GInterval.setColumns(10);
		panel.add(GInterval, "cell 13 4 5 1,growx");
		
		JLabel label_5 = new JLabel("Month");
		panel.add(label_5, "cell 18 4");
		
		JLabel lblNewLabel_3 = new JLabel("Intrest Rate");
		panel.add(lblNewLabel_3, "cell 1 5");
		
		Irate = new JTextField();
		Irate.setEditable(false);
		panel.add(Irate, "cell 3 5 5 1,growx");
		Irate.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("%");
		panel.add(lblNewLabel_8, "cell 8 5");
		
		JLabel label_2 = new JLabel("Intrest Rate");
		panel.add(label_2, "cell 11 5");
		
		Grate = new JTextField();
		Grate.setEditable(false);
		Grate.setColumns(10);
		panel.add(Grate, "cell 13 5 5 1,growx");
		
		JLabel label_6 = new JLabel("%");
		panel.add(label_6, "cell 18 5");
		
		JLabel lblNewLabel_4 = new JLabel("Service Fees");
		panel.add(lblNewLabel_4, "cell 1 6");
		
		Ifees = new JTextField();
		Ifees.setEditable(false);
		panel.add(Ifees, "cell 3 6 5 1,growx");
		Ifees.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("%");
		panel.add(lblNewLabel_9, "cell 8 6");
		
		JLabel label_3 = new JLabel("Service Fees");
		panel.add(label_3, "cell 11 6");
		
		Gfees = new JTextField();
		Gfees.setEditable(false);
		Gfees.setColumns(10);
		panel.add(Gfees, "cell 13 6 5 1,growx");
		
		JLabel label_7 = new JLabel("%");
		panel.add(label_7, "cell 18 6");
		
		IEdit = new JButton("Edit");
		IEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IndividualfieldEnable();
			}
		});
		panel.add(IEdit, "cell 1 8,growx");
		
		Icancel = new JButton("Cancel");
		Icancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldDisable();
			}
		});
		panel.add(Icancel, "cell 2 8 3 1");
		
		GEdit = new JButton("Edit");
		GEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GroupfieldEnable();
			}
		});
		panel.add(GEdit, "cell 11 8,growx");
		
		Gcancel = new JButton("Cancel");
		Gcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldDisable();
			}
		});
		panel.add(Gcancel, "cell 12 8 3 1");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(710, 11, 339, 558);
		add(panel_1);
		panel_1.setLayout(new MigLayout("", "[134.00][grow][]", "[20][][][50,grow][][][][][][][][][]"));
		
		JLabel lblNewLabel_10 = new JLabel("MICROFINANCE");
		lblNewLabel_10.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 32));
		panel_1.add(lblNewLabel_10, "cell 0 1 3 1");
		
		JLabel lblNewLabel_11 = new JLabel("Management Software");
		lblNewLabel_11.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_11, "cell 0 2 3 1");
		
		JLabel lblNewLabel_12 = new JLabel("Developed By");
		lblNewLabel_12.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_12, "cell 2 9,alignx right");
		
		JLabel lblNewLabel_13 = new JLabel("GROUP 12");
		lblNewLabel_13.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_13, "cell 2 10,alignx right");
		
		JLabel lblNewLabel_14 = new JLabel("NYI LINN HTIN");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_14, "cell 2 11,alignx right");
		
		JLabel lblNewLabel_15 = new JLabel("KAUNG MYAT THET");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_15, "cell 2 12,alignx right");
	}
}
