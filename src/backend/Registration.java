package backend;
import dev.Util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class Registration {
	
	private String userId;
	public void setUserId(String userId) {this.userId = userId;}
	public String getUserId() {return this.userId;}
	
	private String userType;
	public void setUserType(String userType) {this.userType = userType;}
	public String getUserType() {return this.userType;}
	
	private String supplierType;
	public void setSupplierType(String supplierType) {this.supplierType = supplierType;}
	public String getSupplierType() {return this.supplierType;}
	
	private String name;
	public void setName(String name) {this.name = name;}
	public String getName() {return this.name;}
	
	private String username;
	public void setUsername(String username) {this.username = username;}
	public String getUsername() {return this.username;}
	
	private String password;
	public void setPassword(String password) {this.password = password;}
	public String getPassword() {return this.password;}
	
	private String confpass;
	public void setConfpass(String confpass) {this.confpass = confpass;}
	public String getConfpasss() {return this.confpass;}
	
	private int age;
	public void setAge(int age) {this.age = age;}
	public int getAge() {return this.age;}
	
	private String gender;
	public void setGender(String gender) {this.gender = gender;}
	public String getGender() {return this.gender;}
	
	private String contact;
	public void setContact(String contact) {this.contact = contact;}
	public String getContact() {return this.contact;}
	
	private String address;
	public void setAddress(String address) {this.address = address;}
	public String getAddress() {return this.address;}
	
	private String status = "fail";
	public String getStatus() {return this.status;}
	public void setStatus(String st) {this.status = st;}
	

	
	public Registration(){
		
		
	}
	public Registration(String userId,String userType, String name, 
			String username,String password,String confpass, int age, String gender, 
			String address, String contact){
		
		setUserId(userId);
		setUserType(userType);
		setName(name);
		setUsername(username);
		setPassword(password);
		setConfpass(confpass);
		setAge(age);
		setGender(gender);
		setAddress(address);
		setContact(contact);
		}
	
	
	public void registerUser() {
		
		try {
			if(validateRegistration()) {
				User us = new User();
				us.createUser(getUserId(), getUserType(), getName(), getUsername(), getPassword(), getAge(), getGender(), getAddress(), getContact());
				switch(getUserType()) {
				case "Client":{
					try {
					      File file = new File("client.txt");
					      if (!file.exists()) {
					        file.createNewFile();
					      } 
					      
					      FileWriter fw = new FileWriter(file,true);
					      String finput = getUserId()+";"+getName()+";"+getAddress()+";"+getContact();
					      fw.write(finput);
					      fw.write(System.getProperty( "line.separator" ));
					      fw.close();
					    } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
					
					break;}
				
				case "Dealer":{
					try {
					      File file = new File("dealer.txt");
					      if (!file.exists()) {
					        file.createNewFile();
					      } 
					      
					      FileWriter fw = new FileWriter(file,true);
					      String finput = getUserId()+";"+500+";"+500+";"+200000.0;
					      fw.write(finput);
					      fw.write(System.getProperty( "line.separator" ));
					      fw.close();
					    } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
					break;}
				
				case "Supplier":{
					try {
					      File file = new File("supplier.txt");
					      if (!file.exists()) {
					        file.createNewFile();
					      } 
					      
					      FileWriter fw = new FileWriter(file,true);
					      String finput = getUserId()+";"+getSupplierType()+";"+getName()+";"+getContact();
					      fw.write(finput);
					      fw.write(System.getProperty( "line.separator" ));
					      fw.close();
					    } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
					
				break;}
				
				}
				setStatus("success");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util u = new Util();
			u.throwPopupMessage("Registration failed! Try again.");
		}

		
	}
	
	private boolean validateRegistration() throws Exception {
		boolean validity = false;
		Util ut = new Util();
		if(!getUserId().isEmpty() && !getUserType().isEmpty() && !getName().isEmpty() 
				&& !getUsername().isEmpty() && !getPassword().isEmpty() && !getConfpasss().isEmpty()
		&& getAge()!=0 && !getGender().isEmpty()&& !getAddress().isEmpty()&& !getContact().isEmpty()) {
			if(getPassword().equals(getConfpasss())) {
				validity = true;				
			}else {
				validity = false;
				ut.throwPopupMessage("Passwords do not match");
				throw new Exception("Passwords do not match");
			}
		}else {
			validity = false;
			ut.throwPopupMessage("Input fields cannot be empty!");
			throw new Exception("Input fields cannot be empty!");		
		}
		return validity;
	}
	

}

