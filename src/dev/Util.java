package dev;

import java.awt.Component;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;


public class Util {
	

	
	public void removeLines(String filename, String entry) {
		File inputFile = new File(filename);
		File tempFile = new File("temp.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String lineToRemove = entry;
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();

			    if(trimmedLine.contains(lineToRemove)) {
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
	
	public String generateUserId() {Random rnd = new Random(); int n = 10000 + rnd.nextInt(90000); return Integer.toString(n);}
	public String generateProductId() {Random rnd = new Random(); int n = 10000 + rnd.nextInt(90000); return "P"+Integer.toString(n);}
	public String generateOrderId() {Random rnd = new Random(); int n = 10000 + rnd.nextInt(90000); return "OD"+Integer.toString(n);}
	
	public void throwPopupMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		
		
	}

}