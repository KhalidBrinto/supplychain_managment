package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import dev.Util;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productId;
	public String getId() {return this.productId;}
	public void setId(String productId) {this.productId = productId;}
	
	private String productName;
	public String getName() {return this.productName;}
	public void setName(String productName) {this.productName = productName;}
	
	private float productPrice;
	public float getProductPrice() {return this.productPrice;}
	public void setProductPrice(float price) {this.productPrice = price;}
	
	private int rawX, rawY;
	public int getrawX() {return this.rawX;}
	public void setrawX(int x) {this.rawX = x;}
	public int getrawY() {return this.rawY;}
	public void setrawY(int y) {this.rawY=y;}

	
	private String dealerId;
	public String getdealerId() {return this.dealerId;}
	public void setdealerId(String dealerid) {this.dealerId = dealerid;}
	
	private int pqty;
	public void setProductQty(int qty) {this.pqty = qty;}
	public int getProductQty() {return this.pqty;}
	
	public Product(){}
	public Product(String productId){
		
		String filename = "productlist.txt";
        File pfile = new File(filename);
        Scanner fileReader;
		try {
			fileReader = new Scanner(pfile);
	        while (fileReader.hasNextLine()) {
	            String data = fileReader.nextLine();
	            
	            String[] pinfo = (data.split(";"));
	            
	            if(pinfo[0].equals(productId)) {
	          	  this.productId=pinfo[0];
	          	  this.productName=pinfo[1];
	          	  this.productPrice=Float.parseFloat(pinfo[2]);
	          	  this.rawX = Integer.parseInt(pinfo[3]);
	          	  this.rawY = Integer.parseInt(pinfo[4]);
	          	  this.dealerId = pinfo[5];
	          	  break;
	            }
	            
	          }
	          fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initiate(String userId) {
//		System.out.println("----------------------------------------------------------------------------------------------------");
//		System.out.println("----------------------------------------------product list------------------------------------------");
//		System.out.println("----------------------------------------------------------------------------------------------------");
//		showProductList();
//		
//		Scanner scan = new Scanner(System.in);
//		List<Product> productlist = new ArrayList<>();
//		
//		System.out.print("Do you want to buy products?(y/n) ");
//		String input = scan.nextLine();
//		while(input.equals("y")) {
//
//			System.out.print("Write the product Id you want to buy: ");
//			String productid = scan.nextLine();
//			System.out.print("Write the quantity of the product: ");
//			int productqty = scan.nextInt();scan.nextLine();
//			
//			Product pi = getProduct(productid);
//			pi.setProductQty(productqty);
//			
//			
//			productlist.add(pi);
//			System.out.print("Do you want to buy more?(y/n): ");
//			input = scan.nextLine();
//			
//		}
//		
//		float total = 0;
//		
//		for(int i=0; i<productlist.size(); i++) {
//			total = total+(productlist.get(i).getProductPrice()*productlist.get(i).getProductQty());
//			System.out.println(productlist.get(i).getProductQty()+"x "+productlist.get(i).getName()+" -----> $"+(productlist.get(i).getProductPrice()*productlist.get(i).getProductQty()));
//		}
//		System.out.println("---------------------------------------------");		
//		System.out.println("Total: &"+total);
//		System.out.print("Place order?(y/n) ");
//		input = scan.nextLine();
//		scan.close();
//		if(input.equals("y")) {
//			System.out.println("Order Placed");
//			Order dummy = new Order();
//			Order o = new Order("od"+dummy.generateOrderId(),userId, productlist);
//			o.placeOrder();
//		}else {
//			System.out.println("Order cancelled");
//		}
		
	}
	
	public void updateProduct(String pid, String pname, String pPrice, String px, String py, String dId) {
		File inputFile = new File("productlist.txt");
		File tempFile = new File("temp.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String lineToRemove = pid;
			String lineToWrite = pid+";"+pname+";"+pPrice+";"+px+";"+py+";"+dId;
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();

			    if(trimmedLine.contains(lineToRemove)) {
			    	writer.write(lineToWrite + System.getProperty("line.separator"));
			    	continue;
			    }
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			inputFile.delete();
			tempFile.renameTo(inputFile);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public Product getProduct(String productId) {
		return new Product(productId);
	}
	
	public List<Product> getProductList() {
		List<Product> plist = new ArrayList<>();
		String filename = "productlist.txt";
        File pfile = new File(filename);
        Scanner fileReader;
		try {
			fileReader = new Scanner(pfile);
	        while (fileReader.hasNextLine()) {
	            String data = fileReader.nextLine();
	            Product p = new Product();
	            String[] pinfo = (data.split(";"));
	            p.setId(pinfo[0]);
	            p.setName(pinfo[1]);
	            p.setProductPrice(Float.parseFloat(pinfo[2]));
	            p.setrawX(Integer.parseInt(pinfo[3]));
	            p.setrawY(Integer.parseInt(pinfo[4]));
	            p.setdealerId(pinfo[5]);	
	            plist.add(p);
	          }
	          fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return plist;
	}
	
	public java.util.List<Product> getProductsByDealerId(String dealerId) {
		String filename = "productlist.txt";
        File pfile = new File(filename);
        Scanner fileReader;
        List<Product> products = new ArrayList<>();
		try {
			fileReader = new Scanner(pfile);
	        while (fileReader.hasNextLine()) {
	            String data = fileReader.nextLine();
	            
	            String[] pinfo = (data.split(";"));
	            
	            Product p = new Product();
	            
	            if(pinfo[5].equals(dealerId)) {
	          	  p.setId(pinfo[0]);
	          	  p.setName(pinfo[1]);
	          	  p.setProductPrice(Float.parseFloat(pinfo[2]));
	          	  p.setrawX(Integer.parseInt(pinfo[3]));
	          	  p.setrawY(Integer.parseInt(pinfo[4]));
	          	  p.setdealerId(pinfo[5]);
		          products.add(p);
	            }

	            
	          }
	          fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public void addProduct(String productId, String productName, String productPrice, String rawx, String rawy, String dealerId) {
		try {
		      File file = new File("productlist.txt");
		      if (!file.exists()) {
		        file.createNewFile();
		      } 
		      
		      FileWriter fw = new FileWriter(file,true);
		      String finput = productId+";"+productName+";"+productPrice+";"+rawx+";"+rawy+";"+dealerId;
		      fw.write(finput);
		      fw.write(System.getProperty( "line.separator" ));
		      fw.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void deleteProduct(String productId) {
		new Util().removeLines("productlist.txt", productId);
	}
	

}
