package collectionsframework;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;





public class CustomerBo {
	
		

	public static List<Customer> getAllCustomers() throws IOException, ParseException
	{
		File f1=new File("E:\\customerFile.txt");
		FileReader fin=new FileReader(f1);
		BufferedReader bf=new BufferedReader(fin);
		List<Customer> l1=new ArrayList();
		String data;
		while((data=bf.readLine())!=null) {
			String str[]=data.split(",");
			Customer c=new Customer();
			c.setCustId(Integer.parseInt(str[0]));
			c.setCustName(str[1]);
			c.setEmail(str[2]);
			c.setMobile(str[3]);
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			String date=str[4];
			Date d = null;
			try {
				d = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.setDob(d);
			Address a=new Address();
			a.setCity(str[5]);
			a.setState(str[6]);
			a.setCountry(str[7]);
			c.setAddress(a);
			l1.add(c);
		}
			return l1;	
		
	}
	public static void displayall() throws IOException, ParseException {
		List<Customer> list=getAllCustomers();
		for(Customer c1:list) {
			System.out.println(c1);
		}
		
	}
	public static int calculateage(Date dob) throws IOException {
		int age=2020-(dob.getYear()+1900);
		return age;
	}
	public static void displaybyage(int age) throws IOException, ParseException{
		List<Customer> list=getAllCustomers();
		List<Customer> l2=new ArrayList();
		for(Customer cus:list) {
			int age1=calculateage(cus.getDob());
			if(age1<age) {
				l2.add(cus);
			}
			
		}
		
		for(Customer c:l2) {
			System.out.println(c);
		}
		
	}
	public static void displaybycity(String name) throws IOException, ParseException {
		List<Customer> list=getAllCustomers();
		
		
		for(Customer cus:list) {
			if(cus.getAddress().getCity().equalsIgnoreCase(name)) {
				System.out.println(cus);
		}
			
		}
	}
	public static void main(String args[]) throws IOException, ParseException {
		while(true) {
		System.out.println("Enter your choice");
		System.out.println("1:Display All Customers\t2:Display by age\t3:Display by city\t4:Transfer\t5:Replace\t6:Exit");
		Scanner sc=new Scanner(System.in);	
		int choice=Integer.parseInt(sc.next());
		switch(choice) {
		case 1:
			displayall();
			break;
		case 2:
			System.out.println("Enter age");
			int age=Integer.parseInt(sc.next());
			displaybyage(age);
			break;
		case 3:
			System.out.println("Enter city name");
			String name=sc.next();
			displaybycity(name);
			break;
		case 4:
			System.out.println("Enter old and new cities");
			String oldcity=sc.next();
			String newcity=sc.next();
			List<Customer> list=getAllCustomers();
			int i=0;
			for(Customer c:list) {
				Address a1=c.getAddress();
				if(a1.getCity().equalsIgnoreCase(oldcity)) {
					a1.setCity(newcity);
					list.get(i).setAddress(a1);
				}
				i++;
			}
			
			for(Customer c1:list)
			{
				System.out.println(c1);
			}
			break;
		case 5:
			System.out.println("Enter id");
			int id=Integer.parseInt(sc.next());
			List<Customer> list2=getAllCustomers();
			
			CopyOnWriteArrayList<Customer> list1 = new CopyOnWriteArrayList<Customer>(list2);
			
			int index=0;
			Customer cus=new Customer();
			cus.setCustId(51);
			cus.setCustName("bhavya");
			
			Iterator<Customer> l1=list1.iterator();
			while(l1.hasNext()) {
				if(l1.next().getCustId()==id) {
								}
					index++;
				}
				
			
			/*for(Customer c1:list1) {
				if(c1.getId()==id) {
				list1.set(index, cus);
				}
				index++;
			}*/
			
			for(Customer cus1:list1) {
				System.out.println(cus1);
			}
			break;
			
			
			
			// New Customer
			
		case 6:
			System.exit(0);
		}
		
	}
	}

}
