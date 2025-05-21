package dev;

import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.Registration;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegistrationGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField contactField;
	private JTextField addressField;
	private JPasswordField confpassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationGUI frame = new RegistrationGUI();
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
	public RegistrationGUI() {
		
		JSpinner supplierTypeSpinner = new JSpinner();
		setTitle("Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1,1,1,1));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, contentPane);
		panel.setBackground(new Color(0, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration Form");
		lblNewLabel.setBounds(60, 20, 308, 57);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.SOUTH, panel);
		
		JLabel back = new JLabel("");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		back.setFont(new Font("Tahoma", Font.BOLD, 10));
		back.setBounds(22, 27, 39, 50);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		try {
			BufferedImage buff = ImageIO.read(RegistrationGUI.class.getResource("/images/icons8-back-100.png"));
			Image image = buff.getScaledInstance(24, 24, Image.SCALE_DEFAULT);
			ImageIcon backicon = new ImageIcon(image);
			back.setIcon(backicon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panel.add(back);
		
		sl_contentPane.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, contentPane);
		contentPane.add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("User ID (Auto-generated): ");
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 38, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_1, 32, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 65, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_1, 204, SpringLayout.WEST, panel_1);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_1);
		
		Util u = new Util();
		JLabel userIdLabel = new JLabel(u.generateUserId());
		userIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel_1.putConstraint(SpringLayout.NORTH, userIdLabel, 3, SpringLayout.NORTH, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, userIdLabel, 6, SpringLayout.EAST, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, userIdLabel, 68, SpringLayout.EAST, lblNewLabel_1);
		userIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(userIdLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(nameField);
		nameField.setColumns(10);
		
		JRadioButton rdbtnClient = new JRadioButton("Client");
		sl_panel_1.putConstraint(SpringLayout.SOUTH, nameField, 49, SpringLayout.SOUTH, rdbtnClient);
		rdbtnClient.setSelected(true);
		rdbtnClient.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnClient.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbtnClient.setForeground(new Color(0, 128, 128));
		panel_1.add(rdbtnClient);
		
		JLabel lblNewLabel_3 = new JLabel("User Type: ");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnClient, -4, SpringLayout.NORTH, lblNewLabel_3);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnClient, 6, SpringLayout.EAST, lblNewLabel_3);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 20, SpringLayout.SOUTH, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel_1);
		lblNewLabel_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_3);
		
		JRadioButton rdbtnDealer = new JRadioButton("Dealer");
		rdbtnDealer.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnDealer, 0, SpringLayout.NORTH, rdbtnClient);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnDealer, 6, SpringLayout.EAST, rdbtnClient);
		rdbtnDealer.setForeground(new Color(0, 128, 128));
		rdbtnDealer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(rdbtnDealer);
		
		JRadioButton rdbtnSupplier = new JRadioButton("Supplier");
		sl_panel_1.putConstraint(SpringLayout.EAST, nameField, 87, SpringLayout.EAST, rdbtnSupplier);
		rdbtnSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnSupplier, 0, SpringLayout.NORTH, rdbtnClient);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnSupplier, 6, SpringLayout.EAST, rdbtnDealer);
		rdbtnSupplier.setForeground(new Color(0, 128, 128));
		rdbtnSupplier.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(rdbtnSupplier);
		
		ButtonGroup btngrpUserType = new ButtonGroup();
		btngrpUserType.add(rdbtnClient);
		btngrpUserType.add(rdbtnDealer);
		btngrpUserType.add(rdbtnSupplier);
		
		JLabel lblNewLabel_4 = new JLabel("Full Name: ");
		sl_panel_1.putConstraint(SpringLayout.NORTH, nameField, -2, SpringLayout.NORTH, lblNewLabel_4);
		sl_panel_1.putConstraint(SpringLayout.WEST, nameField, 16, SpringLayout.EAST, lblNewLabel_4);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblNewLabel_4, -409, SpringLayout.SOUTH, panel_1);
		lblNewLabel_4.setForeground(new Color(0, 128, 128));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_4, 33, SpringLayout.WEST, panel_1);
		panel_1.add(lblNewLabel_4);
		
		usernameField = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, usernameField, 22, SpringLayout.SOUTH, nameField);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, usernameField, 51, SpringLayout.SOUTH, nameField);
		sl_panel_1.putConstraint(SpringLayout.EAST, usernameField, 0, SpringLayout.EAST, nameField);
		usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		usernameField.setColumns(10);
		panel_1.add(usernameField);
		
		JLabel lblNewLabel_4_1 = new JLabel("Username: ");
		sl_panel_1.putConstraint(SpringLayout.WEST, usernameField, 17, SpringLayout.EAST, lblNewLabel_4_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_4_1, 25, SpringLayout.SOUTH, nameField);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_4_1, 0, SpringLayout.WEST, lblNewLabel_1);
		lblNewLabel_4_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Password:");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_4_1_1, 30, SpringLayout.SOUTH, lblNewLabel_4_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_4_1_1, 0, SpringLayout.WEST, lblNewLabel_1);
		lblNewLabel_4_1_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_4_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_4_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.BOLD, 16));
		passwordField.setEchoChar('*');
		sl_panel_1.putConstraint(SpringLayout.NORTH, passwordField, -2, SpringLayout.NORTH, lblNewLabel_4_1_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, nameField);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, passwordField, 27, SpringLayout.NORTH, lblNewLabel_4_1_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, nameField);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Confirm Password:");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_4_1_1_1, 35, SpringLayout.SOUTH, lblNewLabel_4_1_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_4_1_1_1, 32, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_4_1_1_1, 0, SpringLayout.EAST, rdbtnClient);
		lblNewLabel_4_1_1_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_4_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Gender: ");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_3_1, 27, SpringLayout.SOUTH, lblNewLabel_4_1_1_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_3_1, 0, SpringLayout.WEST, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_3_1, 0, SpringLayout.EAST, lblNewLabel_3);
		lblNewLabel_3_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_3_1);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnMale, -4, SpringLayout.NORTH, lblNewLabel_3_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnMale, 0, SpringLayout.WEST, rdbtnClient);
		rdbtnMale.setSelected(true);
		rdbtnMale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMale.setForeground(new Color(0, 128, 128));
		rdbtnMale.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnFemale, -4, SpringLayout.NORTH, lblNewLabel_3_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, rdbtnFemale, 0, SpringLayout.EAST, rdbtnDealer);
		rdbtnFemale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFemale.setForeground(new Color(0, 128, 128));
		rdbtnFemale.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(rdbtnFemale);
		
		JRadioButton rdbtnOther = new JRadioButton("Others");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnOther, -4, SpringLayout.NORTH, lblNewLabel_3_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnOther, 0, SpringLayout.WEST, rdbtnSupplier);
		rdbtnOther.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOther.setForeground(new Color(0, 128, 128));
		rdbtnOther.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(rdbtnOther);
		
		ButtonGroup btngrpGender = new ButtonGroup();
		btngrpGender.add(rdbtnMale);
		btngrpGender.add(rdbtnFemale);
		btngrpGender.add(rdbtnOther);
		
		JSpinner ageSpinner = new JSpinner();
		sl_panel_1.putConstraint(SpringLayout.NORTH, ageSpinner, 386, SpringLayout.NORTH, panel_1);
		ageSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		ageSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(ageSpinner);
		
		JLabel lblNewLabel_5 = new JLabel("Age: ");
		sl_panel_1.putConstraint(SpringLayout.WEST, ageSpinner, 16, SpringLayout.EAST, lblNewLabel_5);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 3, SpringLayout.NORTH, ageSpinner);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0, SpringLayout.WEST, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_5, -37, SpringLayout.EAST, lblNewLabel_3);
		lblNewLabel_5.setForeground(new Color(0, 128, 128));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Contact:");
		sl_panel_1.putConstraint(SpringLayout.EAST, ageSpinner, -33, SpringLayout.WEST, lblNewLabel_6);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 25, SpringLayout.SOUTH, rdbtnFemale);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_6, 0, SpringLayout.WEST, rdbtnDealer);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_6, -13, SpringLayout.EAST, rdbtnDealer);
		lblNewLabel_6.setForeground(new Color(0, 128, 128));
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_6);
		
		contactField = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, contactField, 22, SpringLayout.SOUTH, rdbtnOther);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, contactField, -149, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, ageSpinner, 0, SpringLayout.SOUTH, contactField);
		sl_panel_1.putConstraint(SpringLayout.WEST, contactField, 0, SpringLayout.WEST, rdbtnSupplier);
		sl_panel_1.putConstraint(SpringLayout.EAST, contactField, 0, SpringLayout.EAST, nameField);
		panel_1.add(contactField);
		contactField.setColumns(10);
		
		addressField = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, addressField, 437, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, addressField, 42, SpringLayout.WEST, ageSpinner);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, addressField, -87, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, addressField, -18, SpringLayout.EAST, panel_1);
		addressField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(addressField);
		addressField.setColumns(10);
		
		JLabel lblNewLabel_5_1 = new JLabel("Address: ");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_5_1, 8, SpringLayout.NORTH, addressField);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_5_1, 0, SpringLayout.WEST, lblNewLabel_1);
		lblNewLabel_5_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_5_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_5_1);
		
		confpassField = new JPasswordField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, confpassField, 21, SpringLayout.SOUTH, passwordField);
		sl_panel_1.putConstraint(SpringLayout.WEST, confpassField, 6, SpringLayout.EAST, lblNewLabel_4_1_1_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, confpassField, 50, SpringLayout.SOUTH, passwordField);
		sl_panel_1.putConstraint(SpringLayout.EAST, confpassField, 0, SpringLayout.EAST, nameField);
		panel_1.add(confpassField);
		
		JButton signupBtn = new JButton("Sign Up");
		signupBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signupBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userId = userIdLabel.getText();

				String usertype;
				if(rdbtnClient.isSelected()) {
					usertype = rdbtnClient.getText();
				}else if(rdbtnDealer.isSelected()) {
					usertype = rdbtnDealer.getText();
				}else {
					usertype = rdbtnSupplier.getText();
				}
				
				String name = nameField.getText();
				String username = usernameField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				String confpass = String.valueOf(confpassField.getPassword());
				
				String gender;
				if(rdbtnMale.isSelected()) {
					gender = rdbtnMale.getText();
				}else if(rdbtnFemale.isSelected()) {
					gender = rdbtnFemale.getText();
				}else {
					gender = rdbtnOther.getText();
				}
				
				int age = (int) ageSpinner.getValue();
				String contact = contactField.getText();
				String address = addressField.getText();
				
				Registration reg = new Registration(userId, usertype, name, username, pass, confpass, age, gender, address, contact);
				reg.setSupplierType((String) supplierTypeSpinner.getValue());
				reg.registerUser();	
				
				if(reg.getStatus().equals("success")) {
					Util u = new Util();
					u.throwPopupMessage("Registration Successful");
					Login l = new Login();
					l.setVisible(true);
					dispose();
				}			
			}
			
		});
		
		sl_panel_1.putConstraint(SpringLayout.NORTH, signupBtn, 26, SpringLayout.SOUTH, addressField);
		sl_panel_1.putConstraint(SpringLayout.WEST, signupBtn, 0, SpringLayout.WEST, rdbtnDealer);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, signupBtn, -30, SpringLayout.SOUTH, panel_1);
		signupBtn.setBackground(new Color(0, 128, 128));
		signupBtn.setForeground(new Color(255, 255, 255));
		signupBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(signupBtn);
		

		sl_panel_1.putConstraint(SpringLayout.NORTH, supplierTypeSpinner, 0, SpringLayout.NORTH, rdbtnClient);
		sl_panel_1.putConstraint(SpringLayout.EAST, supplierTypeSpinner, 0, SpringLayout.EAST, usernameField);
		supplierTypeSpinner.setModel(new SpinnerListModel(new String[] {"X", "Y"}));
		sl_panel_1.putConstraint(SpringLayout.WEST, supplierTypeSpinner, 37, SpringLayout.EAST, rdbtnSupplier);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, supplierTypeSpinner, 0, SpringLayout.SOUTH, rdbtnClient);

		panel_1.add(supplierTypeSpinner);
		
		supplierTypeSpinner.setVisible(false);
		

		
		rdbtnSupplier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print("1");
				supplierTypeSpinner.setVisible(true);
				
			}
		});
		rdbtnClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(supplierTypeSpinner.isVisible()) {
					supplierTypeSpinner.setVisible(false);
				}
				
				
			}
		});
		rdbtnDealer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(supplierTypeSpinner.isVisible()) {
					supplierTypeSpinner.setVisible(false);
				}
				
				
			}
		});
		
		
	}
}
