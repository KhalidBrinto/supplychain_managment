package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
	private String userId;
	public void setUserId(String userId) {this.userId = userId;}
	public String getUserId() {return this.userId;}
	
	private String userType;
	public void setUserType(String userType) {this.userType = userType;}
	public String getUserType() {return this.userType;}
	
	private String name;
	public void setName(String name) {this.name = name;}
	public String getName() {return this.name;}
	
	private String username;
	public void setUsername(String username) {this.username = username;}
	public String getUsername() {return this.username;}
	
	private String password;
	public void setPassword(String password) {this.password = password;}
	public String getPassword() {return this.password;}
	
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
	
	public void createUser(String userId, String userType, String name, 
			String username,String password, int age, String gender, 
			String address, String contact) {
		
		try {
		      File file = new File("userdata.txt");
		      if (!file.exists()) {
		        file.createNewFile();
		      } 
		      
		      FileWriter fw = new FileWriter(file,true);
		      String finput = userId+";"+userType+";"+name+";"+username+";"+password+";"+age+";"+gender+";"+address+";"+contact;
		      fw.write(finput);
		      fw.write(System.getProperty( "line.separator" ));
		      fw.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public User getUserbyId(String userId) {
		User user = new User();
		try {
        	String filename = "userdata.txt";
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
              String data = fileReader.nextLine();
              
              String[] userinfo = (data.split(";"));
              
              if(userinfo[0].equals(userId)) {
            	  user.setUserId(userinfo[0]);
            	  user.setUserType(userinfo[1]);
                  user.setName(userinfo[2]);
                  user.setUsername(userinfo[3]);
                  user.setAge(Integer.parseInt(userinfo[5]));
                  user.setGender(userinfo[6]);
                  user.setAddress(userinfo[7]);
                  user.setContact(userinfo[8]);
            	  break;
        	  }
            }

            fileReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
		return user;
		
	}

}

