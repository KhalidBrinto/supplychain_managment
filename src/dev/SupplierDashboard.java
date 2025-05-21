package dev;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import backend.Dealer;
import backend.Product;
import backend.User;
import backend.Supplier;
import dev.DealerDashboard.MultiLineTableCellRenderer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;

public class SupplierDashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierDashboard frame = new SupplierDashboard("92331");
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
	public SupplierDashboard(String userId) {
		
		User us = new User().getUserbyId(userId);
		Supplier s = new Supplier(userId);
		List<List<String>> rqstList = s.getRequestList();

		Object[][] row = new Object[rqstList.size()][5];
		for(int i =0; i<rqstList.size(); i++) {
			
			row[i][0] = rqstList.get(i).get(0);
			row[i][1] = rqstList.get(i).get(1);
			User u = new User().getUserbyId(rqstList.get(i).get(1));
			row[i][2] = u.getName();
			row[i][3] = u.getContact();
			row[i][4] = rqstList.get(i).get(2);
			System.out.println(row[i][0]+" "+row[i][1]+" "+row[i][2]+" "+row[i][3]);
		}
		

		
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		CardLayout cl = new CardLayout(0, 0);
		
		JPanel viewRequestPanel = new JPanel();
		JPanel processRqstPanel = new JPanel();
		
		JPanel sidebar = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, sidebar, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, sidebar, -5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, sidebar, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, sidebar, 300, SpringLayout.WEST, contentPane);
		sidebar.setBackground(new Color(0, 128, 128));
		contentPane.add(sidebar);
		SpringLayout sl_sidebar = new SpringLayout();
		sidebar.setLayout(sl_sidebar);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		sl_sidebar.putConstraint(SpringLayout.NORTH, lblNewLabel, 24, SpringLayout.NORTH, sidebar);
		sl_sidebar.putConstraint(SpringLayout.WEST, lblNewLabel, 29, SpringLayout.WEST, sidebar);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, lblNewLabel, 79, SpringLayout.NORTH, sidebar);
		sl_sidebar.putConstraint(SpringLayout.EAST, lblNewLabel, -30, SpringLayout.EAST, sidebar);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		sidebar.add(lblNewLabel);		
		
		JLabel lblNewLabel_1 = new JLabel("Welcome "+us.getName().split(" ")[0]);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		sl_sidebar.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_sidebar.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, lblNewLabel);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 40, SpringLayout.SOUTH, lblNewLabel);
		sl_sidebar.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		sidebar.add(lblNewLabel_1);
		
		JTable rqstTable = new JTable();

		rqstTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rqstTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rqstTable.setFillsViewportHeight(false);
		rqstTable.setBorder(null);
		rqstTable.setRowHeight(30);
	    
		rqstTable.setModel(new DefaultTableModel(
		    	row,
		    	new String[] {
		    		"Request ID", "Dealer ID", "Dealer Name", "Dealer Contact", "Quantity"
		    	}
	    ));
		JScrollPane orderspane = new JScrollPane(rqstTable);
		orderspane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		orderspane.setBounds(25, 160, 685, 210);
		orderspane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
//		MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
//		ordertable.setDefaultRenderer(Object.class, renderer);
//		rqstTable.getColumnModel().getColumn(3).setCellRenderer(renderer);
		
		ListSelectionModel od = rqstTable.getSelectionModel();
		od.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!od.isSelectionEmpty() && !e.getValueIsAdjusting()) {
	        		
		            String rqstId = rqstTable.getValueAt(rqstTable.getSelectedRow(), 0).toString();
		            String dealerId = rqstTable.getValueAt(rqstTable.getSelectedRow(), 1).toString();
		            String dealerName = rqstTable.getValueAt(rqstTable.getSelectedRow(), 2).toString();
		            String dealerCont = rqstTable.getValueAt(rqstTable.getSelectedRow(), 3).toString();
		            String qty = rqstTable.getValueAt(rqstTable.getSelectedRow(), 4).toString();
		            
		            
		    		System.out.println(rqstId+" "+dealerId+" "+dealerName+" "+dealerCont+" "+qty);
		    		
		    		int input = JOptionPane.showConfirmDialog(null, "Supply to Dealer?");
		            if(input == 0) {
			            Dealer d = new Dealer(dealerId);
			            if(s.getSupplierType().equals("X")) {
			            	d.setRawXQty(d.getRawXQty()+Integer.parseInt(qty));
			            	d.updateDealerInfo(d);
			            	new Util().removeLines("rawmaterialrqstX.txt", rqstId);
			            }else {
			            	d.setRawYQty(d.getRawYQty()+Integer.parseInt(qty));
			            	d.updateDealerInfo(d);
			            	new Util().removeLines("rawmaterialrqstY.txt", rqstId);
			            }
			            new Util().throwPopupMessage("Materials Supplied");
			            dispose();
			            new SupplierDashboard(userId).setVisible(true);
		            }
		    		
		    		
	        		
	        	}
	        }
	    });
		
		processRqstPanel.add(orderspane);
		
		JPanel dashboardpanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, dashboardpanel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, dashboardpanel, 0, SpringLayout.EAST, sidebar);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		sl_sidebar.putConstraint(SpringLayout.EAST, logoutBtn, -104, SpringLayout.EAST, sidebar);
		logoutBtn.setBackground(new Color(0, 128, 128));
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sl_sidebar.putConstraint(SpringLayout.WEST, logoutBtn, 104, SpringLayout.WEST, sidebar);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, logoutBtn, -35, SpringLayout.SOUTH, sidebar);
		sidebar.add(logoutBtn);
		
		JLabel viewRqstLabel = new JLabel("View Requests");
		viewRqstLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sl_sidebar.putConstraint(SpringLayout.WEST, viewRqstLabel, 0, SpringLayout.WEST, sidebar);
		sl_sidebar.putConstraint(SpringLayout.EAST, viewRqstLabel, 0, SpringLayout.EAST, sidebar);
		viewRqstLabel.setBackground(new Color(0, 128, 128));
		viewRqstLabel.setBorder(new EmptyBorder(0,50,0,0));
		viewRqstLabel.setOpaque(true);
		viewRqstLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				viewRqstLabel.setBackground(new Color(0, 169, 135));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewRqstLabel.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(dashboardpanel, "1");
			}
		});
		sl_sidebar.putConstraint(SpringLayout.NORTH, viewRqstLabel, 113, SpringLayout.SOUTH, lblNewLabel_1);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, viewRqstLabel, -297, SpringLayout.SOUTH, sidebar);
		viewRqstLabel.setForeground(new Color(255, 255, 255));
		viewRqstLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sidebar.add(viewRqstLabel);
		
		JLabel processRqstLabel = new JLabel("Process Requests");
		processRqstLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		processRqstLabel.setBackground(new Color(0, 128, 128));
		processRqstLabel.setBorder(new EmptyBorder(0,50,0,0));
		processRqstLabel.setOpaque(true);
		sl_sidebar.putConstraint(SpringLayout.NORTH, processRqstLabel, 6, SpringLayout.SOUTH, viewRqstLabel);
		sl_sidebar.putConstraint(SpringLayout.WEST, processRqstLabel, 0, SpringLayout.WEST, viewRqstLabel);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, processRqstLabel, -191, SpringLayout.NORTH, logoutBtn);
		sl_sidebar.putConstraint(SpringLayout.EAST, processRqstLabel, 0, SpringLayout.EAST, viewRqstLabel);
		processRqstLabel.setForeground(Color.WHITE);
		processRqstLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		processRqstLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				processRqstLabel.setBackground(new Color(0, 169, 135));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				processRqstLabel.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(dashboardpanel, "2");
			}
		});
		sidebar.add(processRqstLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, dashboardpanel, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, dashboardpanel, 0, SpringLayout.EAST, contentPane);
		contentPane.add(dashboardpanel);
		
		dashboardpanel.setLayout(cl);


		viewRequestPanel.setBackground(new Color(255, 255, 255));
		processRqstPanel.setBackground(new Color(255, 255, 255));
		
		dashboardpanel.add(viewRequestPanel,"1");
		SpringLayout sl_viewProductPanel = new SpringLayout();
		viewRequestPanel.setLayout(sl_viewProductPanel);
		
//		Object[][] rqstrow = new Object[rqstList.size()][rqstList.get(1).size()];
//		for(int i =0; i<rqstList.size(); i++) {
//			row[i][0] = rqstList.get(i).get(0);
//			row[i][1] = rqstList.get(i).get(1);
//			row[i][2] = rqstList.get(i).get(2);
//		}
		
	    JTable table = new JTable();
	    table.setRowSelectionAllowed(false);
	    table.setFillsViewportHeight(true);
	    table.setBorder(null);
	    
	    table.setModel(new DefaultTableModel(
	    	row,
	    	new String[] {
	    		"Request ID", "Dealer ID", "Dealer Name", "Dealer Contact", "Quantity"
	    	}
	    ));
	    
	    table.setRowHeight(30);
	    
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, table, 269, SpringLayout.NORTH, viewRequestPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.WEST, table, 53, SpringLayout.WEST, viewRequestPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, table, -83, SpringLayout.SOUTH, viewRequestPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.EAST, table, -53, SpringLayout.EAST, viewRequestPanel);
	    table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    
	    JScrollPane sp = new JScrollPane(table);
	    sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, sp, -155, SpringLayout.SOUTH, viewRequestPanel);
	    sp.setBounds(25, 160, 685, 360);
	    sp.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
	    sl_viewProductPanel.putConstraint(SpringLayout.WEST, sp, 24, SpringLayout.WEST, viewRequestPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.EAST, sp, -24, SpringLayout.EAST, viewRequestPanel);
	    sp.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		viewRequestPanel.add(sp);
		
		JPanel headerPanel = new JPanel();
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, headerPanel, 1, SpringLayout.NORTH, viewRequestPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.WEST, headerPanel, -1, SpringLayout.WEST, viewRequestPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.EAST, headerPanel, 0, SpringLayout.EAST, viewRequestPanel);
		Border outline = BorderFactory.createLineBorder(new Color(0, 128, 128));
		headerPanel.setBorder(outline);
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, sp, 104, SpringLayout.SOUTH, headerPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, headerPanel, 100, SpringLayout.NORTH, viewRequestPanel);
		viewRequestPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("USER ID: "+us.getUserId());
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(30, 25, 130, 20);
		headerPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username: "+us.getUsername());
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_3.setBounds(30, 55, 170, 20);
		headerPanel.add(lblNewLabel_3);
		
		dashboardpanel.add(processRqstPanel,"2");
		processRqstPanel.setLayout(null);
		
		JPanel headerPanel_2 = new JPanel();
		headerPanel_2.setBorder(new LineBorder(new Color(0, 128, 128)));
		headerPanel_2.setLayout(null);
		headerPanel_2.setBounds(-1, 1, 737, 99);
		processRqstPanel.add(headerPanel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("USER ID: "+us.getUserId());
		lblNewLabel_2_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(30, 25, 130, 20);
		headerPanel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("Username: "+us.getUsername());
		lblNewLabel_3_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_3_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(30, 55, 170, 20);
		headerPanel_2.add(lblNewLabel_3_2);

	}

}
