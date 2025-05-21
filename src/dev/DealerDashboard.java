package dev;

import java.awt.CardLayout;


import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import backend.Product;
import backend.User;
import backend.Dealer;
import backend.Order;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;



public class DealerDashboard extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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

	private JPanel contentPane;
	private JTextField enteredProductNameField;
	private JTextField enteredProductPriceField;
	private JTextField enteredProductRawXField;
	private JTextField enteredProductRawYField;
	private JTextField xqtyField;
	private JTextField yqtyField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DealerDashboard frame = new DealerDashboard("15374");
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
	public DealerDashboard(String userId) {
		
		User us = new User().getUserbyId(userId);
		Dealer dl = new Dealer(userId);
		List<Product> plist = new Product().getProductsByDealerId(userId);

		Object[][] row = new Object[plist.size()][5];
		for(int i =0; i<plist.size(); i++) {
			row[i][0] = plist.get(i).getId();
			row[i][1] = plist.get(i).getName();
			row[i][2] = plist.get(i).getProductPrice();
			row[i][3] = plist.get(i).getrawX();
			row[i][4] = plist.get(i).getrawY();
		}
		
		CardLayout cl = new CardLayout(0, 0);
		CardLayout editProductCard = new CardLayout(0, 0);
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		JPanel viewProductPanel = new JPanel();
		JPanel viewOrderPanel = new JPanel();
		JPanel editProductPanel = new JPanel();
		JPanel rqstMaterialPanel = new JPanel();


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
		
		JPanel editProductOperationPanel = new JPanel();
		editProductOperationPanel.setBounds(0, 118, 736, 445);
		editProductOperationPanel.setLayout(editProductCard);
		JPanel addProductPanel = new JPanel();
		addProductPanel.setBackground(new Color(255, 255, 255));
		JPanel updateProductPanel = new JPanel();
		updateProductPanel.setBackground(new Color(255, 255, 255));
		updateProductPanel.setLayout(null);
		
		JTable ordertable = new JTable();

		ordertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ordertable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ordertable.setFillsViewportHeight(false);
		ordertable.setBorder(null);
		
		List<Order> olist = new ArrayList<>();
		try {
			olist = new Order().loadOrderList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object[][] orderrow = new Object[olist.size()][7];
		for(int i=0; i<olist.size();i++) {
			String prlist = "";
			String qtlist = "";
			String idlist = "";
			orderrow[i][0] = olist.get(i).getOrderId();
			orderrow[i][1] = olist.get(i).getUserId();
			User u = new User().getUserbyId(olist.get(i).getUserId());
			orderrow[i][2] = u.getContact();
			for(Product p : olist.get(i).getProductList()) {
				prlist = prlist+p.getName()+", ";
				idlist = idlist+p.getId()+", ";
				qtlist = qtlist+p.getProductQty()+", ";
			}
			orderrow[i][3] = prlist;
			orderrow[i][4] = idlist;
			orderrow[i][5] = qtlist;
			orderrow[i][6] = olist.get(i).getOrderStatus();
			
		}
	    
		ordertable.setModel(new DefaultTableModel(
				orderrow,
		    	new String[] {
		    		"ORDER ID", "USER ID", "USER CONTACT", "PRODUCT(s)","Product ID", "QUANTITY", "STATUS"
		    	}
	    ));
		ordertable.getColumnModel().getColumn(1).setPreferredWidth(30);
		ordertable.getColumnModel().getColumn(3).setPreferredWidth(160);
		ordertable.getColumnModel().getColumn(4).setPreferredWidth(30);
		JScrollPane orderspane = new JScrollPane(ordertable);
		orderspane.setBounds(25, 160, 685, 360);
		orderspane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
//		ordertable.setDefaultRenderer(Object.class, renderer);
		ordertable.getColumnModel().getColumn(3).setCellRenderer(renderer);
		ordertable.getColumnModel().getColumn(4).setCellRenderer(renderer);
		
		ListSelectionModel od = ordertable.getSelectionModel();
		od.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!od.isSelectionEmpty() && !e.getValueIsAdjusting()) {
	        		
		            String orderid = ordertable.getValueAt(ordertable.getSelectedRow(), 0).toString();
		            String userid = ordertable.getValueAt(ordertable.getSelectedRow(), 1).toString();
		            String usercontact = ordertable.getValueAt(ordertable.getSelectedRow(), 2).toString();
		            String products = ordertable.getValueAt(ordertable.getSelectedRow(), 3).toString();
		            String productIds = ordertable.getValueAt(ordertable.getSelectedRow(), 4).toString();
		            String qtys = ordertable.getValueAt(ordertable.getSelectedRow(), 5).toString();
		            
		            String[] productlist = products.split(", ");
		            String[] pidlist = productIds.split(", ");
		            String[] qtylist = qtys.split(", ");
		            
		    		System.out.println(orderid+" "+userid+" "+usercontact+" "+productlist+" "+qtylist);
		    		
		    		for(int i=0; i<productlist.length; i++) {
		    			System.out.println(productlist[i]+" = "+qtylist[i]+" = "+pidlist[i]);
		    		}
		            int input = JOptionPane.showConfirmDialog(null, "Process Order?");
		            if(input == 0) {
		            	new Order().processOrder(orderid, userId);
		            }
		            dispose();
		            new DealerDashboard(userId).setVisible(true);
		    		
		    		
	        		
	        	}
	        }
	    });
		
		viewOrderPanel.add(orderspane);
			
		
		JTable editproducttable = new JTable();
		editproducttable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		editproducttable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		editproducttable.setFillsViewportHeight(false);
		editproducttable.setBorder(null);
	    
		editproducttable.setModel(new DefaultTableModel(
				row,
		    	new String[] {
		    		"PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "MATERIAL X", "MATERIAL Y"
		    	}
	    ));
		ListSelectionModel md = editproducttable.getSelectionModel();
		md.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!md.isSelectionEmpty() && !e.getValueIsAdjusting()) {
	        		
		            String pid = editproducttable.getValueAt(editproducttable.getSelectedRow(), 0).toString();
		            String pname = editproducttable.getValueAt(editproducttable.getSelectedRow(), 1).toString();
		            String pPrice = editproducttable.getValueAt(editproducttable.getSelectedRow(), 2).toString();
		            String rawX = editproducttable.getValueAt(editproducttable.getSelectedRow(), 3).toString();
		            String rawY = editproducttable.getValueAt(editproducttable.getSelectedRow(), 3).toString();
		            
		            
		    		JTextField updateProductNameField = new JTextField();
		    		updateProductNameField.setText(pname);
		    		updateProductNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		    		updateProductNameField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Product Name"), updateProductNameField.getBorder()));
		    		updateProductNameField.setBounds(60, 190, 280, 55);
		    		updateProductNameField.setColumns(10);
		    		updateProductPanel.add(updateProductNameField);
		    		
		    		JTextField updateProductPriceField = new JTextField();
		    		updateProductPriceField.setText(pPrice);
		    		updateProductPriceField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		    		updateProductPriceField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Product Price Tk."), updateProductPriceField.getBorder()));
		    		updateProductPriceField.setBounds(370, 190, 280, 55);
		    		updateProductPriceField.setColumns(10);
		    		updateProductPanel.add(updateProductPriceField);
		    		
		    		JTextField updateProductRawXField = new JTextField();
		    		updateProductRawXField.setText(rawX);
		    		updateProductRawXField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		    		updateProductRawXField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Raw Material X Qty"), updateProductRawXField.getBorder()));
		    		updateProductRawXField.setBounds(60, 250, 280, 55);
		    		updateProductRawXField.setColumns(10);
		    		updateProductPanel.add(updateProductRawXField);
		    		
		    		JTextField updateProductRawYField = new JTextField();
		    		updateProductRawYField.setText(rawY);
		    		updateProductRawYField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		    		updateProductRawYField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Raw Material Y Qty"), updateProductRawYField.getBorder()));
		    		updateProductRawYField.setBounds(370, 250, 280, 55);
		    		updateProductRawYField.setColumns(10);
		    		updateProductPanel.add(updateProductRawYField);
		    		
		            JButton updateProductBtn = new JButton("Update Product");
		    		updateProductBtn.setForeground(new Color(255, 255, 255));
		    		updateProductBtn.setBackground(new Color(0, 128, 128));
		    		updateProductBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(192, 192, 192), null)); 		
		    		updateProductBtn.setFocusPainted(false);
		    		updateProductBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		    		updateProductBtn.setBounds(300, 330, 170, 40);
		    		
		    		updateProductBtn.addMouseListener(new MouseAdapter() {
		    			@Override
		    			public void mouseClicked(MouseEvent e) {
		    				new Product().updateProduct(pid, updateProductNameField.getText(), updateProductPriceField.getText(), updateProductRawXField.getText(), updateProductRawYField.getText(), userId);
		    				new Util().throwPopupMessage("Product Updated Successfully");
		    				dispose();
		    				new DealerDashboard(userId).setVisible(true);
		    			}
		    		});
		    		
		    		updateProductPanel.add(updateProductBtn);
		    		
	        		
	        	}
	        }
	    });
		JScrollPane spane = new JScrollPane(editproducttable);
		spane.setBounds(25, 40, 685, 140);
		spane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		updateProductPanel.add(spane);
		
		
		JPanel deleteProductPanel = new JPanel();
		deleteProductPanel.setBackground(new Color(255, 255, 255));
		
		JTable deleteproducttable = new JTable();
		deleteproducttable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deleteproducttable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		deleteproducttable.setFillsViewportHeight(false);
		deleteproducttable.setBorder(null);
	    
		deleteproducttable.setModel(new DefaultTableModel(
				row,
		    	new String[] {
		    		"PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "MATERIAL X", "MATERIAL Y"
		    	}
	    ));
		ListSelectionModel delmd = deleteproducttable.getSelectionModel();
		delmd.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	if(!delmd.isSelectionEmpty() && !e.getValueIsAdjusting()) {
	        		
		            String pid = deleteproducttable.getValueAt(deleteproducttable.getSelectedRow(), 0).toString();
		            int input = JOptionPane.showConfirmDialog(null, "Confirm delete?");
		            if(input == 0) {
			            new Product().deleteProduct(pid);
			            new Util().throwPopupMessage("Product Deleted");
			            dispose();
			            new DealerDashboard(userId).setVisible(true);
		            }

	        	}
	        }
	    });
		deleteProductPanel.setLayout(null);
		
		JScrollPane delspane = new JScrollPane(deleteproducttable);
		delspane.setBounds(25, 40, 685, 140);
		delspane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		deleteProductPanel.add(delspane);
		
		editProductOperationPanel.add(addProductPanel,"1");
		editProductOperationPanel.add(updateProductPanel,"2");
		editProductOperationPanel.add(deleteProductPanel,"3");
		
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
		
		JLabel viewOrderLabel = new JLabel("View Orders");
		viewOrderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewOrderLabel.setBackground(new Color(0, 128, 128));
		viewOrderLabel.setBorder(new EmptyBorder(0,50,0,0));
		viewOrderLabel.setOpaque(true);
		sl_sidebar.putConstraint(SpringLayout.NORTH, viewOrderLabel, 6, SpringLayout.SOUTH, viewProductLabel);
		sl_sidebar.putConstraint(SpringLayout.WEST, viewOrderLabel, 0, SpringLayout.WEST, viewProductLabel);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, viewOrderLabel, -191, SpringLayout.NORTH, logoutBtn);
		sl_sidebar.putConstraint(SpringLayout.EAST, viewOrderLabel, 0, SpringLayout.EAST, viewProductLabel);
		viewOrderLabel.setForeground(Color.WHITE);
		viewOrderLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		viewOrderLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				viewOrderLabel.setBackground(new Color(0, 169, 135));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewOrderLabel.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(dashboardpanel, "2");
			}
		});
		sidebar.add(viewOrderLabel);
		
		JLabel addProductLabel = new JLabel("Edit Products");
		addProductLabel.setBackground(new Color(0, 128, 128));
		addProductLabel.setOpaque(true);
		sl_sidebar.putConstraint(SpringLayout.NORTH, addProductLabel, 6, SpringLayout.SOUTH, viewOrderLabel);
		sl_sidebar.putConstraint(SpringLayout.WEST, addProductLabel, 0, SpringLayout.WEST, viewOrderLabel);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, addProductLabel, 40, SpringLayout.SOUTH, viewOrderLabel);
		sl_sidebar.putConstraint(SpringLayout.EAST, addProductLabel, 0, SpringLayout.EAST, viewOrderLabel);
		addProductLabel.setBorder(new EmptyBorder(0,50,0,0));
		addProductLabel.setForeground(new Color(255, 255, 255));
		addProductLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		addProductLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				addProductLabel.setBackground(new Color(0, 169, 135));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addProductLabel.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(dashboardpanel, "3");
			}
		});
		sidebar.add(addProductLabel);
		
		JLabel rqstMaterialLabel = new JLabel("Request Materials");
		sl_sidebar.putConstraint(SpringLayout.NORTH, rqstMaterialLabel, 6, SpringLayout.SOUTH, addProductLabel);
		sl_sidebar.putConstraint(SpringLayout.WEST, rqstMaterialLabel, 0, SpringLayout.WEST, addProductLabel);
		sl_sidebar.putConstraint(SpringLayout.SOUTH, rqstMaterialLabel, 40, SpringLayout.SOUTH, addProductLabel);
		sl_sidebar.putConstraint(SpringLayout.EAST, rqstMaterialLabel, 0, SpringLayout.EAST, addProductLabel);
		rqstMaterialLabel.setOpaque(true);
		rqstMaterialLabel.setForeground(Color.WHITE);
		rqstMaterialLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rqstMaterialLabel.setBorder(new EmptyBorder(0,50,0,0));
		rqstMaterialLabel.setBackground(new Color(0, 128, 128));
		rqstMaterialLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				rqstMaterialLabel.setBackground(new Color(0, 169, 135));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rqstMaterialLabel.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(dashboardpanel, "4");
			}
		});
		sidebar.add(rqstMaterialLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, dashboardpanel, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, dashboardpanel, 0, SpringLayout.EAST, contentPane);
		contentPane.add(dashboardpanel);
		
		dashboardpanel.setLayout(cl);


		viewProductPanel.setBackground(new Color(255, 255, 255));
		viewOrderPanel.setBackground(new Color(255, 255, 255));
		editProductPanel.setBackground(new Color(255, 255, 255));
		rqstMaterialPanel.setBackground(new Color(255, 255, 255));
		
		dashboardpanel.add(viewProductPanel,"1");
		SpringLayout sl_viewProductPanel = new SpringLayout();
		viewProductPanel.setLayout(sl_viewProductPanel);
		
	    JTable table = new JTable();
	    table.setRowSelectionAllowed(false);
	    table.setFillsViewportHeight(true);
	    table.setBorder(null);
	    
	    table.setModel(new DefaultTableModel(
	    	row,
	    	new String[] {
	    		"PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "MATERIAL X", "MATERIAL Y"
	    	}
	    ));
	    
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, table, 269, SpringLayout.NORTH, viewProductPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.WEST, table, 53, SpringLayout.WEST, viewProductPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, table, -83, SpringLayout.SOUTH, viewProductPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.EAST, table, -53, SpringLayout.EAST, viewProductPanel);
	    table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    JScrollPane sp = new JScrollPane(table);
	    sl_viewProductPanel.putConstraint(SpringLayout.WEST, sp, 24, SpringLayout.WEST, viewProductPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, sp, -140, SpringLayout.SOUTH, viewProductPanel);
	    sl_viewProductPanel.putConstraint(SpringLayout.EAST, sp, -24, SpringLayout.EAST, viewProductPanel);
	    sp.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		viewProductPanel.add(sp);
		
		JPanel headerPanel = new JPanel();
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, headerPanel, 1, SpringLayout.NORTH, viewProductPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.WEST, headerPanel, -1, SpringLayout.WEST, viewProductPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.EAST, headerPanel, 0, SpringLayout.EAST, viewProductPanel);
		Border outline = BorderFactory.createLineBorder(new Color(0, 128, 128));
		headerPanel.setBorder(outline);
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, sp, 104, SpringLayout.SOUTH, headerPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, headerPanel, 100, SpringLayout.NORTH, viewProductPanel);
		viewProductPanel.add(headerPanel);
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
		
		JLabel lblNewLabel_4 = new JLabel("Balance: "+dl.getWalletBalance()+" Tk.");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_4.setForeground(new Color(0, 128, 128));
		lblNewLabel_4.setBounds(440, 15, 280, 60);
		headerPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_10 = new JLabel("Raw Material X Qty: "+dl.getRawXQty());
		lblNewLabel_10.setForeground(new Color(0, 128, 128));
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(210, 25, 170, 20);
		headerPanel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Raw Material Y Qty: "+dl.getRawYQty());
		lblNewLabel_10_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_10_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_10_1.setBounds(210, 55, 170, 20);
		headerPanel.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_11 = new JLabel("My products");
		sl_viewProductPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_11, 33, SpringLayout.SOUTH, headerPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.WEST, lblNewLabel_11, 24, SpringLayout.WEST, viewProductPanel);
		sl_viewProductPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_11, -18, SpringLayout.NORTH, sp);
		sl_viewProductPanel.putConstraint(SpringLayout.EAST, lblNewLabel_11, -501, SpringLayout.EAST, viewProductPanel);
		lblNewLabel_11.setForeground(new Color(0, 128, 128));
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 25));
		viewProductPanel.add(lblNewLabel_11);
		
		dashboardpanel.add(viewOrderPanel,"2");
		viewOrderPanel.setLayout(null);
		
		JPanel headerPanel_2 = new JPanel();
		headerPanel_2.setBorder(new LineBorder(new Color(0, 128, 128)));
		headerPanel_2.setLayout(null);
		headerPanel_2.setBounds(-1, 1, 737, 99);
		viewOrderPanel.add(headerPanel_2);
		
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
		
		JLabel lblNewLabel_4_2 = new JLabel("Balance: "+dl.getWalletBalance()+" Tk.");
		lblNewLabel_4_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_4_2.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_4_2.setBounds(440, 15, 280, 60);
		headerPanel_2.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_10_3 = new JLabel("Raw Material X Qty: "+dl.getRawXQty());
		lblNewLabel_10_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_10_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_10_3.setBounds(210, 25, 170, 20);
		headerPanel_2.add(lblNewLabel_10_3);
		
		JLabel lblNewLabel_10_1_2 = new JLabel("Raw Material Y Qty: "+dl.getRawYQty());
		lblNewLabel_10_1_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_10_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_10_1_2.setBounds(210, 55, 170, 20);
		headerPanel_2.add(lblNewLabel_10_1_2);
		dashboardpanel.add(editProductPanel,"3");
		
		JButton addProductBtn = new JButton("Add Product");
		addProductBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editProductCard.show(editProductOperationPanel, "1");
			}
		});
		addProductBtn.setForeground(new Color(255, 255, 255));
		addProductBtn.setBackground(new Color(0, 128, 128));
		addProductBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(192, 192, 192), null)); 		
		addProductBtn.setFocusPainted(false);
		addProductBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		addProductBtn.setBounds(60, 49, 170, 40);
		editProductPanel.setLayout(null);
		editProductPanel.add(addProductBtn);
		
		JButton updateProductBtn = new JButton("Update Product");
		updateProductBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editProductCard.show(editProductOperationPanel, "2");
			}
		});
		updateProductBtn.setForeground(new Color(255, 255, 255));
		updateProductBtn.setBackground(new Color(0, 128, 128));
		updateProductBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(192, 192, 192), null)); 		
		updateProductBtn.setFocusPainted(false);
		updateProductBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		updateProductBtn.setBounds(250, 49, 170, 40);
		editProductPanel.add(updateProductBtn);
		
		JButton deleteProductBtn = new JButton("Delete Product");
		deleteProductBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editProductCard.show(editProductOperationPanel, "3");
			}
		});
		deleteProductBtn.setForeground(new Color(255, 255, 255));
		deleteProductBtn.setBackground(new Color(0, 128, 128));
		deleteProductBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(192, 192, 192), null)); 		
		deleteProductBtn.setFocusPainted(false);
		deleteProductBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		deleteProductBtn.setBounds(440, 49, 170, 40);
		editProductPanel.add(deleteProductBtn);
		

		
		editProductOperationPanel.add(addProductPanel,"1");
		addProductPanel.setLayout(null);
		String ProductId = new Util().generateProductId();
		JLabel lblNewLabel_5 = new JLabel("PRODUCT ID(Auto-generated): "+ProductId);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(40, 60, 280, 35);
		addProductPanel.add(lblNewLabel_5);
		
		enteredProductNameField = new JTextField();
		enteredProductNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		enteredProductNameField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Product Name"), enteredProductNameField.getBorder()));
		enteredProductNameField.setBounds(40, 105, 280, 55);
		enteredProductNameField.setColumns(10);
		addProductPanel.add(enteredProductNameField);
		
		enteredProductPriceField = new JTextField();
		enteredProductPriceField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		enteredProductPriceField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Product Price Tk."), enteredProductPriceField.getBorder()));
		enteredProductPriceField.setBounds(40, 165, 280, 55);
		enteredProductPriceField.setColumns(10);
		addProductPanel.add(enteredProductPriceField);
		
		enteredProductRawXField = new JTextField();
		enteredProductRawXField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		enteredProductRawXField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Raw Material X Qty"), enteredProductRawXField.getBorder()));
		enteredProductRawXField.setBounds(40, 225, 280, 55);
		enteredProductRawXField.setColumns(10);
		addProductPanel.add(enteredProductRawXField);
		
		enteredProductRawYField = new JTextField();
		enteredProductRawYField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		enteredProductRawYField.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Raw Material Y Qty"), enteredProductRawYField.getBorder()));
		enteredProductRawYField.setBounds(40, 285, 280, 55);
		enteredProductRawYField.setColumns(10);
		addProductPanel.add(enteredProductRawYField);
		
		JButton confirmAddProductBtn = new JButton("ADD");
		confirmAddProductBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!enteredProductNameField.getText().equals("") && !enteredProductPriceField.getText().equals("") && !enteredProductRawXField.getText().equals("") && !enteredProductRawYField.getText().equals("")) {
					new Product().addProduct(ProductId, enteredProductNameField.getText(), enteredProductPriceField.getText(), enteredProductRawXField.getText(), enteredProductRawYField.getText(), userId);
					new Util().throwPopupMessage("Product Added Successfully");
					dispose();
					new DealerDashboard(userId).setVisible(true);
				}else {
					new Util().throwPopupMessage("Input fields cannot be empty");
				}

			}
		});
		confirmAddProductBtn.setForeground(new Color(255, 255, 255));
		confirmAddProductBtn.setBackground(new Color(0, 128, 128));
		confirmAddProductBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(192, 192, 192), null)); 		
		confirmAddProductBtn.setFocusPainted(false);
		confirmAddProductBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		confirmAddProductBtn.setBounds(464, 199, 170, 40);
		addProductPanel.add(confirmAddProductBtn);
		
		editProductOperationPanel.add(updateProductPanel,"2");
		
		JLabel lblNewLabel_7 = new JLabel("Select Product from the Table to Update.");
		lblNewLabel_7.setForeground(new Color(0, 128, 128));
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(30, 10, 280, 20);
		updateProductPanel.add(lblNewLabel_7);
		editProductOperationPanel.add(deleteProductPanel,"3");
		
		JLabel lblNewLabel_6 = new JLabel("Select Product from the Table to Delete.");
		lblNewLabel_6.setForeground(new Color(0, 128, 128));
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(30, 10, 280, 20);
		deleteProductPanel.add(lblNewLabel_6);
		
		editProductPanel.add(editProductOperationPanel);
		dashboardpanel.add(rqstMaterialPanel,"4");
		rqstMaterialPanel.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Material X Quantity");
		lblNewLabel_8.setForeground(new Color(0, 128, 128));
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(130, 200, 150, 20);
		rqstMaterialPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Material Y Quantity");
		lblNewLabel_9.setForeground(new Color(0, 128, 128));
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(475, 200, 150, 20);
		rqstMaterialPanel.add(lblNewLabel_9);
		
		xqtyField = new JTextField();
		xqtyField.setBounds(45, 235, 295, 35);
		rqstMaterialPanel.add(xqtyField);
		xqtyField.setColumns(10);
		
		yqtyField = new JTextField();
		yqtyField.setBounds(395, 235, 295, 35);
		rqstMaterialPanel.add(yqtyField);
		yqtyField.setColumns(10);
		
		JButton rqstBtn = new JButton("Request");
		rqstBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int inp = -1;

				if(xqtyField.getText().equals("") && yqtyField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Input Fields Cannot be Empty","Error", JOptionPane.ERROR_MESSAGE);										
				}else {
					inp = JOptionPane.showConfirmDialog(null, "Confirm Request?");
					
					if((!xqtyField.getText().equals("")&& Integer.parseInt(xqtyField.getText())>0) && yqtyField.getText().equals("")) {
						new Dealer().requestRawMaterialX(userId, xqtyField.getText());						
					}
					if((!yqtyField.getText().equals("")&& Integer.parseInt(yqtyField.getText())>0) && xqtyField.getText().equals("")) {
						new Dealer().requestRawMaterialY(userId, yqtyField.getText());	
					}
					if((!xqtyField.getText().equals("")&& Integer.parseInt(xqtyField.getText())>0) && (!yqtyField.getText().equals("")&& Integer.parseInt(yqtyField.getText())>0)) {
						new Dealer().requestRawMaterialX(userId, xqtyField.getText());
						new Dealer().requestRawMaterialY(userId, yqtyField.getText());
					}
				}

				if(inp == 0) {
					JOptionPane.showMessageDialog(null, "Request Placed Successfully");
					dispose();
					new DealerDashboard(userId).setVisible(true);
				}
			}
		});
		rqstBtn.setForeground(new Color(255, 255, 255));
		rqstBtn.setBackground(new Color(0, 128, 128));
		rqstBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(192, 192, 192), null)); 		
		rqstBtn.setFocusPainted(false);
		rqstBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rqstBtn.setBounds(287, 347, 170, 40);
		rqstMaterialPanel.add(rqstBtn);
		
		JPanel headerPanel_1 = new JPanel();
		headerPanel_1.setBorder(new LineBorder(new Color(0, 128, 128)));
		headerPanel_1.setLayout(null);
		headerPanel_1.setBounds(-1, 1, 737, 99);
		rqstMaterialPanel.add(headerPanel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("USER ID: "+us.getUserId());
		lblNewLabel_2_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(30, 25, 130, 20);
		headerPanel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Username: "+us.getUsername());
		lblNewLabel_3_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(30, 55, 170, 20);
		headerPanel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Balance: "+dl.getWalletBalance()+" Tk.");
		lblNewLabel_4_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_4_1.setBounds(440, 15, 280, 60);
		headerPanel_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_10_2 = new JLabel("Raw Material X Qty: "+dl.getRawXQty());
		lblNewLabel_10_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_10_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_10_2.setBounds(210, 25, 170, 20);
		headerPanel_1.add(lblNewLabel_10_2);
		
		JLabel lblNewLabel_10_1_1 = new JLabel("Raw Material Y Qty: "+dl.getRawYQty());
		lblNewLabel_10_1_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_10_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_10_1_1.setBounds(210, 55, 170, 20);
		headerPanel_1.add(lblNewLabel_10_1_1);
	}
}
