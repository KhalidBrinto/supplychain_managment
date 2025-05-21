package backend;

import dev.Util;

import java.io.*;
import java.util.*;

public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String orderId;
	public String getOrderId() {return this.orderId;}
	public void setOrderId(String orderid) {this.orderId = orderid;}
	public String generateOrderId() {Random rnd = new Random(); int n = 10000 + rnd.nextInt(90000); return Integer.toString(n);}
	
	private String userId;
	public String getUserId() {return this.userId;}
	public void setUserId(String userid) {this.userId = userid;}
	
	private List<Product> p;
	public List<Product> getProductList() {return this.p;}
	public void setProductList(List<Product> plist) {this.p = plist;}
	
	private String orderStatus;
	public String getOrderStatus() {return this.orderStatus;}
	public void setOrderStatus(String orderStatus) {this.orderStatus = orderStatus;}
	
	public Order(){}
	
	public Order(String userId, List<Product> plist){
		setOrderId("OD"+generateOrderId());
		setUserId(userId);
		setProductList(plist);
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> loadOrderList(){
		List<Order> od = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("orderlist2.txt"))) {
			if (br.readLine() == null) {
			    System.out.println("File empty");
			    return null;
			}else {
				FileInputStream fileStream = new FileInputStream("orderlist2.txt");
				ObjectInputStream ois = new ObjectInputStream(fileStream);
				od = (List<Order>) ois.readObject();
				ois.close();
				fileStream.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return od;
	}
	
	public void placeOrder(Order o) {
		o.setOrderStatus("Pending");
		List<Order> od = new ArrayList<>();
		try {
			if(loadOrderList()!= null) {
				od = loadOrderList();
				od.add(o);
			}else {
				od.add(o);				
			}
			
			FileOutputStream fileStream = new FileOutputStream("orderlist2.txt");
			ObjectOutputStream ois = new ObjectOutputStream(fileStream);
			ois.writeObject(od);
			
			ois.close();
			fileStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void processOrder(String oid, String dealerid) {
		String filename = "orderlist.txt";
		File f = new File(filename);
		List<Order> od = loadOrderList();
		System.out.println(od.size());
		Dealer d = new Dealer(dealerid);
		int totalXqty = 0;
		int totalYqty = 0;
		
		for(int i=0; i<od.size(); i++) {
			if(od.get(i).getOrderId().equals(oid)) {
				od.get(i).setOrderStatus("Complete");
				for(Product p : od.get(i).getProductList()) {
					totalXqty+= p.getProductQty()*p.getrawX();
					totalYqty+= p.getProductQty()*p.getrawY();
					
				}
				if(d.getRawXQty()>totalXqty && d.getRawYQty()>totalYqty) {
					d.setRawXQty(d.getRawXQty()-totalXqty);
					d.setRawYQty(d.getRawYQty()-totalYqty);
					d.updateDealerInfo(d);
					new Util().throwPopupMessage("Order Processed Successfully");
					
				}else {
					new Util().throwPopupMessage("Cannot Process Order. \nLow Material Quantity");
				}
				
				od.remove(i);
				break;
				
			}
			
		}
		FileOutputStream fileStream;
		try {
			fileStream = new FileOutputStream("orderlist2.txt");
			ObjectOutputStream ois = new ObjectOutputStream(fileStream);
			ois.writeObject(od);
			
			ois.close();
			fileStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		List<Order> olist = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("orderlist2.txt"))) {
			if (br.readLine() == null) {
			    System.out.println("File empty");
			}else {
				FileInputStream fileStream = new FileInputStream("orderlist2.txt");
				ObjectInputStream ois = new ObjectInputStream(fileStream);
				olist = (List<Order>) ois.readObject();
				ois.close();
				fileStream.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(olist.size());
		
		
//		try {
//			olist = loadOrderList();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(olist);
		   
		
		for(Order od: olist) {
			for (Product prod: od.getProductList()) {
				System.out.println(od.getOrderId()+" "+od.getUserId()+" "+prod.getId()+" "+prod.getName()+" "+prod.getProductPrice()+" "+prod.getProductQty());
				
				
			}
			
		}
		
		
	}

}
