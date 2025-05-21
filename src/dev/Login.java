package dev;

import java.awt.EventQueue;
import java.io.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.User;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 520);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(409, 5, 572, 473);
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/images/4530381_19368.jpg")));
		lblNewLabel_4.setBounds(0, 0, 469, 473);
		panel_1.add(lblNewLabel_4);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, -36, 409, 529);
		panel.setBackground(new Color(0, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(242, 242, 242));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 54));
		lblNewLabel.setBounds(73, 53, 262, 81);
		panel.add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setForeground(Color.DARK_GRAY);
		usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		usernameField.setBounds(49, 219, 318, 36);
		panel.add(usernameField);
		usernameField.setColumns(12);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.DARK_GRAY);
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(49, 315, 318, 36);
		panel.add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String enteredUsername = usernameField.getText();
		        String enteredPassword = String.valueOf(passwordField.getPassword());
		        
		        String userId="";
		        String userType = "";
		        String username="";
		        String password="";
		        
		        try {
		        	String filename = "userdata.txt";
		            File file = new File(filename);
		            Scanner fileReader = new Scanner(file);
		            boolean loginSuccessful = false;
		            while (fileReader.hasNextLine()) {
		              String data = fileReader.nextLine();
		              
		              String[] userinfo = (data.split(";"));
		              
		              userId = userinfo[0];
		              userType = userinfo[1];
		              username = userinfo[3];
		              password = userinfo[4];
		              
		              if(username.equals(enteredUsername) && password.equals(enteredPassword)) {
		            	  loginSuccessful = true;
		            	  break;
		        	  }
		            }
		            
		            if(loginSuccessful) {
		            	switch(userType) {
		            	case "Client": {
		            		ClientDashboard cl = new ClientDashboard(userId);
			            	cl.setVisible(true); 
			            	dispose();
			            	break;
		            	}
		            	case "Dealer": DealerDashboard dl = new DealerDashboard(userId); dl.setVisible(true);dispose(); break;
		            	case "Supplier": SupplierDashboard sl = new SupplierDashboard(userId); sl.setVisible(true);dispose(); break;
		            	default: break;
		            	}
		            }
		            else {
		            	Util u = new Util();
		            	u.throwPopupMessage("Wrong username or password");
		            	u.throwPopupMessage("login failed");		            	
		            	
		            }
		            fileReader.close();
		          } catch (FileNotFoundException e1) {
		            System.out.println("An error occurred.");
		            e1.printStackTrace();
		          }
			}
		});
		loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(UIManager.getColor("Button.disabledShadow"));
		loginBtn.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		loginBtn.setBounds(140, 392, 119, 36);
		panel.add(loginBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Don't have an accoount? Sign Up");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationGUI rg = new RegistrationGUI();
				rg.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_1.setBounds(96, 453, 221, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setBounds(50, 189, 130, 21);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setForeground(SystemColor.text);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(49, 285, 112, 21);
		panel.add(lblNewLabel_3);
	}
}
