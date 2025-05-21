package backend;
import dev.Util;

import java.io.*;
import java.util.*;

public class Supplier extends User{
	
	private String supplierId;
	public void setsupplierId(String supplierId) {this.supplierId = supplierId;}
	public String getsupplierId() {return this.supplierId;}
	
	private String supplierType;
	public void setSupplierType(String suppliertype) {this.supplierType = suppliertype;}
	public String getSupplierType() {return this.supplierType;}
	
	private String dealerid;
	private String rqstid;
	private int supplyqty;
	public String generateRqstId() {Random rnd = new Random(); int n = 10000 + rnd.nextInt(90000); return Integer.toString(n);}
	
	public Supplier() {}
	public Supplier(String supId) {
		String filename = "supplier.txt";
        File supfile = new File(filename);
        Scanner fileReader;
		try {
			fileReader = new Scanner(supfile);
	        while (fileReader.hasNextLine()) {
	            String data = fileReader.nextLine();
	            
	            String[] dealerinfo = (data.split(";"));
	            
	            if(dealerinfo[0].equals(supId)) {
	          	  setsupplierId(dealerinfo[0]);
	          	  setSupplierType(dealerinfo[1]);;
	          	  break;
	            }
	            
	          }
	          fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void supplyRawMaterial(String supid, String rqstid) {
		if(supid.equals("55")) {
			String filename = "rawmaterialrqstX.txt";
			File f = new File(filename);
			try {
				Scanner fr = new Scanner(f);
				while(fr.hasNextLine()) {
					String data = fr.nextLine();
					String[] rqstinfo = (data.split("&"));
					if(rqstinfo[0].equals(rqstid)) {
						Dealer d = new Dealer(rqstinfo[1]);
						System.out.println("OrderID: "+rqstinfo[0]+" Dealer Id: "+rqstinfo[1]+" Qty: "+rqstinfo[2]);
						d.setRawXQty(d.getRawXQty()+Integer.parseInt(rqstinfo[2]));
						d.updateDealerInfo(d);
					}

				}
				System.out.println("Material Supplied");
				Util u = new Util();
				fr.close();
				u.removeLines(filename, rqstid);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(supid.equals("2")) {
			String filename = "rawmaterialrqstY.txt";
			File f = new File(filename);
			try {
				Scanner fr = new Scanner(f);
				while(fr.hasNextLine()) {
					String data = fr.nextLine();
					String[] rqstinfo = (data.split("&"));
					if(rqstinfo[0].equals(rqstid)) {
						Dealer d = new Dealer(rqstinfo[1]);
						System.out.println("OrderID: "+rqstinfo[0]+" Dealer Id: "+rqstinfo[1]+" Qty: "+rqstinfo[2]);
						d.setRawYQty(d.getRawYQty()+Integer.parseInt(rqstinfo[2]));
						d.updateDealerInfo(d);
					}
				}
				System.out.println("Material Supplied");
				Util u = new Util();
				fr.close();
				u.removeLines(filename, rqstid);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public List<List<String>> getRequestList() {
		String stype = getSupplierType();
		List<List<String>> list = new ArrayList<List<String>>();		
		String filename = null;
		if(stype.equals("X")) {
			filename = "rawmaterialrqstX.txt";
		}else {
			filename = "rawmaterialrqstY.txt";
		}
		File f = new File(filename);
		try {
			Scanner fr = new Scanner(f);
			while(fr.hasNextLine()) {
				List<String> row = new ArrayList<>();
				String data = fr.nextLine();
				String[] rqstinfo = (data.split(";"));
				row.add(rqstinfo[0]);
				row.add(rqstinfo[1]);
				row.add(rqstinfo[2]);
				list.add(row);
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}


