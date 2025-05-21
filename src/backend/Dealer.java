package backend;

import java.io.*;
import java.util.*;

public class Dealer extends User{
	
	private String dealerId;
	public void setDealerId(String dealerId) {this.dealerId = dealerId;}
	public String getDealerId() {return this.dealerId;}
	
	private int rawmaterialxQty = 0;
	public int getRawXQty() {return this.rawmaterialxQty;}
	public void setRawXQty(int qty) {this.rawmaterialxQty = qty;}
	
	private int rawmaterialyQty = 0;
	public int getRawYQty() {return this.rawmaterialyQty;}
	public void setRawYQty(int qty) {this.rawmaterialyQty=qty;}
	
	private float wallet; 
	public float getWalletBalance() {return this.wallet;}
	public void setWalletBalance(float newbalance) {this.wallet = newbalance;}
	
	public Dealer(){}
	
	public Dealer(String dealerId){
		String filename = "dealer.txt";
        File dealerfile = new File(filename);
        Scanner fileReader;
		try {
			fileReader = new Scanner(dealerfile);
	        while (fileReader.hasNextLine()) {
	            String data = fileReader.nextLine();
	            
	            String[] dealerinfo = (data.split(";"));
	            
	            if(dealerinfo[0].equals(dealerId)) {
	          	  setDealerId(dealerinfo[0]);
	          	  setRawXQty(Integer.parseInt(dealerinfo[1]));;
	          	  setRawYQty(Integer.parseInt(dealerinfo[2]));;
	          	  setWalletBalance(Float.parseFloat(dealerinfo[3]));;
	          	  break;
	            }
	            
	          }
	          fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDealerInfo(Dealer d) {
		File inputFile = new File("dealer.txt");
		File tempFile = new File("dealertemp.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String lineToRemove = this.getDealerId();
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();

			    if(trimmedLine.contains(lineToRemove)) {
			    	String updata = d.getDealerId()+";"+d.getRawXQty()+";"+d.getRawYQty()+";"+d.getWalletBalance();
			    	writer.write(updata + System.getProperty("line.separator"));
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
	
//	public void supplyProduct(Order od) {
//		
//	}
	
	public void requestRawMaterialX(String dealerId, String qty) {
		Random rnd = new Random();
		int n = 10000 + rnd.nextInt(90000); 
		String rqId = "RQ"+Integer.toString(n);
		String filename = "rawmaterialrqstX.txt";
		try {
			
			File f = new File(filename);
            FileWriter fw = new FileWriter(f, true);
            
            String inp = rqId+";"+dealerId+";"+qty+System.getProperty("line.separator");
			fw.write(inp);
			
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void requestRawMaterialY(String dealerId, String qty) {
		Random rnd = new Random();
		int n = 10000 + rnd.nextInt(90000); 
		String rqId = "RQ"+Integer.toString(n);
		String filename = "rawmaterialrqstY.txt";
		try {
			
			File f = new File(filename);
            FileWriter fw = new FileWriter(f, true);
            
            String inp = rqId+";"+dealerId+";"+qty+System.getProperty("line.separator");
			fw.write(inp);
			
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
	
	
	public void dashboard(String userId) {
		System.out.println("Welcome to Dealer Dashboard");
		super.getUserbyId(userId);
		showOptions(userId);
	}
	public void showOptions(String userId) {
//		Scanner scan = new Scanner(System.in);
//		
//        System.out.println("Please Select from the options: ");
//        System.out.println("1. Process Order ");
//        System.out.println("2. Request Raw Material ");
//        System.out.println("3. Show Inventory ");
//        System.out.println("4. Logout ");
//        
//        System.out.print("Input: ");
//        String userInput = scan.nextLine();
//        
//        switch (userInput) {
//		case "1": 
//			Order o = new Order(); o.showOrderList(); 
//			System.out.print("<-- Back(n) | Process order(y)");
//			String cmd = scan.nextLine();
//
//			if(cmd.equals("y")) {
//				System.out.print("Enter the orderId you want to process: ");
//				String oid = scan.nextLine();
//				Order po = new Order();
//				po.processOrder(oid, userId);
//			}
//			if(cmd.equals("n")) {
//				dashboard(userId);
//			}
//			break;
//		case "2": 
//			System.out.println("Which Raw material do you want to request?");  
//			System.out.println("1. Raw material X");  
//			System.out.println("2. Raw material Y"); 
//			String input = scan.nextLine();
//			if(input.equals("1")) {
//				System.out.print("Raw Material X Qty: ");
//				int xQty = Integer.parseInt(scan.nextLine());
//				Supplier s = new Supplier();
//				String rqstid = "rq"+s.generateRqstId();
//				requestRawMaterialX(rqstid,xQty, userId);
//				dashboard(userId);
//			}else if(input.equals("2")) {
//				System.out.print("Raw Material Y Qty: ");
//				int yQty = Integer.parseInt(scan.nextLine());
//				Supplier s = new Supplier();
//				String rqstid = "rq"+s.generateRqstId();
//				requestRawMaterialY(rqstid,yQty, userId);
//				dashboard(userId);
//			}else {
//				System.out.println("Invalid input");
//			}
//			System.out.flush(); break;
//			
//		case "3":
//			Dealer d = new Dealer(userId);
//			System.out.println("Raw material X Qty: "+d.getRawXQty()+" Raw Material Y Qty: "+d.getRawYQty()+" Total Balance: "+d.getWalletBalance());
//			System.out.print("<-- Back(y/n)");
//			String input2 = scan.nextLine();
//			if(input2.equals("y")) {
//				dashboard(userId);
//			}
//
//			break;
//		case "4": 
//			System.out.print("\033[H\033[2J");  
//			System.out.flush();  
//			Start st = new Start(); st.initiate(); break;
//		default:
//			System.out.println("Unexpected value: "+userInput);
//		}
//        
//        scan.close();
		
	}

}

