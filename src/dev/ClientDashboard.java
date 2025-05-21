package dev;

import java.awt.EventQueue;

import java.util.*;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.User;
import dev.DealerDashboard.MultiLineTableCellRenderer;
import backend.Product;
import backend.Dealer;
import backend.Order;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.TextField;
import net.miginfocom.swing.MigLayout;
import java.awt.ScrollPane;
import java.awt.Point;
import javax.swing.ScrollPaneConstants;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.border.SoftBevelBorder;

public class ClientDashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private String userId;
	private JTable table;
	private List<Product> cart= new ArrayList<>();

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientDashboard frame = new ClientDashboard("26701");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {
		public MultiLineTableCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
		}

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	       setText((value == null)? "":value.toString());
	       setEditable(false);
	       table.setRowHeight(table.getColumnCount()*10);

	        return this;
	    }
	}

	/**
	 * Create the frame.
	 */

	public ClientDashboard(String userId) {

		User us = new User().getUserbyId(userId);
		List<Product> plist = new Product().getProductList();
		List<Product> cart = new ArrayList<>();
		
		Object[][] row = new Object[plist.size()][4];
		for(int i =0; i<plist.size(); i++) {
			row[i][0] = plist.get(i).getId();
			row[i][1] = plist.get(i).getName();
			row[i][2] = plist.get(i).getProductPrice();
			row[i][3] = plist.get(i).getdealerId();
		}
		
		CardLayout cl = new CardLayout(0, 0);
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));


		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
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
		
		JLabel viewProductLabel = new JLabel("View Products");
		viewProductLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sl_sidebar.putConstraint(SpringLayout.WEST, viewProductLabel, 0, SpringLayout.WEST, sidebar);
		sl_sidebar.putConstraint(SpringLayout.EAST, viewProductLabel, 0, SpringLayout.EAST, sidebar);
		viewProductLabel.setBackground(new Color(0, 128, 128));
		viewProductLabel.setBorder(new EmptyBorder(0,50,0,0));
		viewProductLabel.setOpaque(true);
		viewProductLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				viewProductLabel.setBackground(new Color(0, 169, 135));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewProductLabel.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(dashboardpanel, "1");
			}
		});
		sl_sidebar.putConstraint(SpringLayout.NORTH, viewProductLabel, 113, SpringLayout.SOUTH, lblNewLabel_1);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, viewProductLabel, -297, SpringLayout.SOUTH, sidebar);
		viewProductLabel.setForeground(new Color(255, 255, 255));
		viewProductLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sidebar.add(viewProductLabel);
		
		JLabel viewCartLabel = new JLabel("View Cart");
		viewCartLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewCartLabel.setBackground(new Color(0, 128, 128));
		viewCartLabel.setBorder(new EmptyBorder(0,50,0,0));
		sl_sidebar.putConstraint(SpringLayout.NORTH, viewCartLabel, 6, SpringLayout.SOUTH, viewProductLabel);
		sl_sidebar.putConstraint(SpringLayout.WEST, viewCartLabel, 0, SpringLayout.WEST, viewProductLabel);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, viewCartLabel, -191, SpringLayout.NORTH, logoutBtn);
		sl_sidebar.putConstraint(SpringLayout.EAST, viewCartLabel, 0, SpringLayout.EAST, viewProductLabel);
		viewCartLabel.setForeground(Color.WHITE);
		viewCartLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		viewCartLabel.setOpaque(true);

		sidebar.add(viewCartLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, dashboardpanel, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, dashboardpanel, 0, SpringLayout.EAST, contentPane);
		contentPane.add(dashboardpanel);
		
		dashboardpanel.setLayout(cl);
		JPanel viewProductPanel = new JPanel();
		JPanel viewCartPanel = new JPanel();

		viewProductPanel.setBackground(new Color(255, 255, 255));
		viewCartPanel.setBackground(new Color(242, 242, 242));
		
		dashboardpanel.add(viewProductPanel,"1");
		SpringLayout sl_viewProductPanel = new SpringLayout();
		viewProductPanel.setLayout(sl_viewProductPanel);
		
		JPanel headerPanel = new JPanel();
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, headerPanel, 1, SpringLayout.NORTH, viewProductPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.WEST, headerPanel, -1, SpringLayout.WEST, viewProductPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.EAST, headerPanel, 0, SpringLayout.EAST, viewProductPanel);
		Border outline = BorderFactory.createLineBorder(new Color(0, 128, 128));
		headerPanel.setBorder(outline);
		sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, headerPanel, 100, SpringLayout.NORTH, viewProductPanel);
		viewProductPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("USER ID: "+us.getUserId());
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(30, 25, 170, 20);
		headerPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username: "+us.getUsername());
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_3.setBounds(30, 55, 170, 20);
		headerPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Balance: 15000 Tk.");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_4.setForeground(new Color(0, 128, 128));
		lblNewLabel_4.setBounds(425, 15, 300, 60);
		headerPanel.add(lblNewLabel_4);
		

		JPanel productCardpanel = new JPanel();
		productCardpanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		productCardpanel.setBackground(new Color(255, 255, 255));
		SpringLayout sl_productCardpanel = new SpringLayout();
		productCardpanel.setLayout(sl_productCardpanel);
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, productCardpanel, 0, SpringLayout.SOUTH, headerPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.WEST, productCardpanel, 0, SpringLayout.WEST, viewProductPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, productCardpanel, 0, SpringLayout.SOUTH, viewProductPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.EAST, productCardpanel, 0, SpringLayout.EAST, viewProductPanel);
		
	    JTable table = new JTable();
	    table.setFillsViewportHeight(true);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    table.setBorder(null);
	    
	    table.setModel(new DefaultTableModel(
	    	row,
	    	new String[] {
	    		"PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "DEALER ID"
	    	}
	    ));
	    JScrollPane tablespane = new JScrollPane(table);
	    sl_productCardpanel.putConstraint(SpringLayout.NORTH, tablespane, 50, SpringLayout.NORTH, productCardpanel);
	    sl_productCardpanel.putConstraint(SpringLayout.SOUTH, tablespane, -10, SpringLayout.SOUTH, productCardpanel);
	    sl_productCardpanel.putConstraint(SpringLayout.EAST, tablespane, -10, SpringLayout.EAST, productCardpanel);
		tablespane.setBounds(25, 160, 685, 360);
		tablespane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
		table.getColumnModel().getColumn(3).setCellRenderer(renderer);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		
		ListSelectionModel od = table.getSelectionModel();
		od.addListSelectionListener(new ListSelectionListener(){
			@Override 
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!od.isSelectionEmpty() && !e.getValueIsAdjusting()) {
	        		
	        		int selectedRow = od.getMinSelectionIndex();

	        		
	        		boolean update = false;
	        		int index = -1;
		            
		            		        		           		            
		            int input = JOptionPane.showConfirmDialog(null, "Add to Cart?");
		            if(input == 0) {
		            	String pid = (String) table.getValueAt(selectedRow, 0);
		            	Product pcart = new Product(pid);
		            	if(!cart.isEmpty()) {
		            		for(int i =0; i<cart.size();i++) {
		            			if(cart.get(i).getId().equals(pid)) {
		            				update = true;
		            				index = i;
		            				break;
		            			}else {
		            				continue;
		            			}				            	
		            		}
		            		if(update & index!=-1) {
		            			cart.get(index).setProductQty(cart.get(index).getProductQty()+1);
		            		}else {
		            			pcart.setProductQty(pcart.getProductQty()+1);
		            			cart.add(pcart);
		            		}		            		
		            	}else {
		            		pcart.setProductQty(pcart.getProductQty()+1);
		            		cart.add(pcart);
		            	}
		            	pid= "";
		            }
		            table.getSelectionModel().clearSelection();	
		    				    		       		
	        	}
	        }
	    });
		
		

	    table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

	    sl_productCardpanel.putConstraint(SpringLayout.WEST, tablespane, 10, SpringLayout.WEST, productCardpanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.EAST, tablespane, 200, SpringLayout.EAST, productCardpanel);
		
		productCardpanel.add(tablespane);
		
		
		viewProductPanel.add(productCardpanel);
		
		JLabel lblNewLabel_5 = new JLabel("Select Rows to add products to your Cart");
		lblNewLabel_5.setForeground(new Color(0, 128, 128));
		sl_productCardpanel.putConstraint(SpringLayout.EAST, lblNewLabel_5, 286, SpringLayout.WEST, tablespane);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_productCardpanel.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 10, SpringLayout.NORTH, productCardpanel);
		sl_productCardpanel.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0, SpringLayout.WEST, tablespane);
		productCardpanel.add(lblNewLabel_5);

		
		
		SpringLayout sl_viewCartPanel = new SpringLayout();
		viewCartPanel.setLayout(sl_viewCartPanel);
		
		
		
		
		
		JButton confirmBtn = new JButton("Confirm Order");
		sl_viewCartPanel.putConstraint(SpringLayout.NORTH, confirmBtn, 400, SpringLayout.NORTH, viewCartPanel);
		sl_viewCartPanel.putConstraint(SpringLayout.WEST, confirmBtn, 494, SpringLayout.WEST, viewCartPanel);
		sl_viewCartPanel.putConstraint(SpringLayout.SOUTH, confirmBtn, -100, SpringLayout.SOUTH, viewCartPanel);
		sl_viewCartPanel.putConstraint(SpringLayout.EAST, confirmBtn, -53, SpringLayout.EAST, viewCartPanel);
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Place order?");
	            if(input == 0) {
	            	
	            	Order o = new Order(userId, cart);
					o.placeOrder(o);
		            new Util().throwPopupMessage("Order Placed Successfully");
		            dispose();
		            new ClientDashboard(userId).setVisible(true);
	            }
								
			}
		});
		
		JLabel emptycartlabel = new JLabel("No items in Cart");
		sl_viewCartPanel.putConstraint(SpringLayout.NORTH, emptycartlabel, 225, SpringLayout.NORTH, viewCartPanel);
		sl_viewCartPanel.putConstraint(SpringLayout.WEST, emptycartlabel, 195, SpringLayout.WEST, viewCartPanel);
		sl_viewCartPanel.putConstraint(SpringLayout.SOUTH, emptycartlabel, 310, SpringLayout.NORTH, viewCartPanel);
		sl_viewCartPanel.putConstraint(SpringLayout.EAST, emptycartlabel, 523, SpringLayout.WEST, viewCartPanel);
		emptycartlabel.setHorizontalAlignment(SwingConstants.CENTER);
		emptycartlabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmBtn.setForeground(new Color(255, 255, 255));
		confirmBtn.setBackground(new Color(0, 128, 128));
		confirmBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(192, 192, 192), null)); 		
		confirmBtn.setFocusPainted(false);
		confirmBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		
		
		viewCartLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				viewCartLabel.setBackground(new Color(0, 169, 135));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewCartLabel.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(cart);
	            for (Product p: cart) {
	            	System.out.println(p.getName()+" "+p.getProductQty()+" "+p.getProductPrice());
	            }
				if(!cart.isEmpty()) {
					emptycartlabel.setVisible(false);
					float total = 0;
					Object[][] cartrow = new Object[cart.size()+1][5];
					for(int i =0; i<cart.size(); i++) {
						cartrow[i][0] = cart.get(i).getId();
						cartrow[i][1] = cart.get(i).getName();
						cartrow[i][2] = cart.get(i).getProductPrice();
						cartrow[i][3] = cart.get(i).getProductQty();
						cartrow[i][4] = cart.get(i).getProductQty() * cart.get(i).getProductPrice();
						total = total+(cart.get(i).getProductQty() * cart.get(i).getProductPrice());
					}
					cartrow[cart.size()][4-1] = "Total = ";
					cartrow[cart.size()][5-1] = total;
					System.out.println(total);
					
					JTable carttable = new JTable();
					carttable.setFillsViewportHeight(true);
					carttable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					carttable.setBorder(null);
				    
				    carttable.setModel(new DefaultTableModel(
				    	cartrow,
				    	new String[] {
				    		"PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "Quantity", "Price"
				    	}
				    ));
				    JScrollPane cartspane = new JScrollPane(carttable);
				    sl_viewCartPanel.putConstraint(SpringLayout.NORTH, cartspane, 20, SpringLayout.NORTH, viewCartPanel);
				    sl_viewCartPanel.putConstraint(SpringLayout.WEST, cartspane, 20, SpringLayout.WEST, viewCartPanel);
				    sl_viewCartPanel.putConstraint(SpringLayout.SOUTH, cartspane, -250, SpringLayout.SOUTH, viewCartPanel);
				    sl_viewCartPanel.putConstraint(SpringLayout.EAST, cartspane, -20, SpringLayout.EAST, viewCartPanel);
				    cartspane.setBounds(25, 160, 685, 360);
				    cartspane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					carttable.getColumnModel().getColumn(1).setPreferredWidth(220);
					viewCartPanel.add(confirmBtn);
					viewCartPanel.add(cartspane);
				}else {
					viewCartPanel.add(emptycartlabel);
				}
				cl.show(dashboardpanel, "2");
			}
		});
		dashboardpanel.add(viewCartPanel,"2");

		
		
	}
}
