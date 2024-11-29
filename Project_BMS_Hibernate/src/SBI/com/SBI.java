package SBI.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.query.Query;

import com.InvalidAccountNumberException;
import com.InvalidAdharNumberException;
import com.InvalidContactException;
import com.NotEligibleForAccountCreationException;
import com.config.HibernateUtil;
import com.mysql.cj.Session;

import Module.com.Account;
import RBI.com.RBI;

public class SBI implements RBI
{
	double amount;
	Account i;
    Scanner sc=new Scanner(System.in);
    
    

	@Override
	public void createAccount() throws InvalidContactException, InvalidAdharNumberException, NotEligibleForAccountCreationException, InvalidAccountNumberException {
	
		
	    org.hibernate.Session session=HibernateUtil.getSessionFactory().openSession();
			
			i=new Account();

			System.out.println("Account Creation Process Start Here");
			System.out.println("Enter Initial Balance In An Account");
		    i.setBalance(0);

			
			System.out.println("Enter Name Of Account Holder");
			
			i.setName(sc.next()+sc.nextLine());

			System.out.println("Enter The Account Number Of Account Holder");
			
			int accNo=sc.nextInt();
			int y=accNo;
			int countDigit=0;
			while(y!=0) {
				y=y/10;
				countDigit++;
			}
			if(countDigit==5) {
				i.setAccNo(accNo);
			}
			else
			{
				throw new InvalidAccountNumberException("Account Number Is an invalid");
			}

			
			System.out.println("Enter Account Holder Mobile Number");
			long mobNo=sc.nextLong();
			long k=mobNo;
			long count =0;
		    while(k!=0) {
		    	k=k/10;
				count++;
			}
			if(count==10) {
			i.setMobNo(mobNo);
			}
			else
			{
				throw new InvalidContactException("Invalid Contact Number");
			}
			
			
			System.out.println("Enter Account Holder's Age Here");
			int age=sc.nextInt();
			if(age>18 && age<60) {
				i.setAge(age);
			}
			else
			{
				throw new NotEligibleForAccountCreationException("User Not Eligible For Creating an account");
			}

			System.out.println("Enter Account Holder Address Here");
			i.setAddress(sc.next());
			i.setAddress(sc.nextLine());
			
			
			System.out.println("Enter Date Of Birth of Account Holder ");
		    i.setDob(sc.next());
		    
		    
		    System.out.println("Enter Account Holder Adhar Number ");
		    long  adharNo=sc.nextLong();
		    long temp=adharNo;
		    long count1=0;
		    while(temp!=0) {
		    	temp=temp/10;
		    	count1++;
		    }
		if(count1==12) {
			i.setAdharNo(adharNo);
			}
		else
			
		{
			throw new InvalidAdharNumberException("Invalid Adhar Number");
		}
		
		    System.out.println("Upload The Photo of Account Holder");
		    System.out.println("Account Created Successfully...!");
			
			   System.out.println( "Data Inserted Successfully...!!!");

	     		 session.save(i);	
		    
		     session.beginTransaction().commit();
	     		
		} 
			
	@Override
	public void showdetails() throws Exception {	
		
	SessionFactory sf=HibernateUtil.getSessionFactory();
	org.hibernate.Session session=sf.openSession();
	
	String hql="from Account";
	
	Query<Account> query=session.createQuery(hql);
	
	List<Account> accounts=query.getResultList();
	for(Account acc: accounts) {
		System.out.println(acc.getAccNo());
		System.out.println(acc.getName());
		System.out.println(acc.getMobNo());
		System.out.println(acc.getAdharNo());
		System.out.println(acc.getAddress());
		System.out.println(acc.getBalance());
		System.out.println(acc.getAge());
		System.out.println(acc.getPincode());
		System.out.println(acc.getDob());
	}
	
	session.beginTransaction().commit();
		
	}
	
	
	
	@Override
	public void checkBalance() throws Exception {
		
		
		SessionFactory sf=HibernateUtil.getSessionFactory();
		org.hibernate.Session session=sf.openSession();
		
		
		System.out.println("Enter account number to assign");
		int ac=sc.nextInt();

		
		 Account s=session.get(Account.class, ac);
		 System.out.println("Balance:" +s.getBalance());
		 
			}

 

	@Override
	public void withdrawAmount() throws Exception{
		
		SessionFactory sf=HibernateUtil.getSessionFactory();
		org.hibernate.Session session=sf.openSession();
			
	    System.out.println("Enter account number for withdrawl process");
	    int ac=sc.nextInt();
	
	
	    Account hi=session.get(Account.class, ac);
	    
	    System.out.println("Enter The Amount for withdrawl Process");
	    long amount=sc.nextLong();
	    
	    if(hi.getBalance()>=amount) {
	    	
	    	long newb=(long) (hi.getBalance()-amount);
	    	hi.setBalance(newb);
	    	session.update(hi);
	    	session.beginTransaction().commit();
	    	System.out.println("Withdrawl Successfully:" +newb);
	    }
	    else
		{
			System.out.println("No Sufficient Money In Account");
		}
		}
	
	
	
	@Override
	public void depositAmount() throws Exception {
		
		
		SessionFactory sf=HibernateUtil.getSessionFactory();
		org.hibernate.Session session=sf.openSession();
		
		System.out.println("Enter acc no for deposit process");
		int ac=sc.nextInt();
		
         System.out.println("Enter The Amount For Deposit Process");
         long amt=sc.nextLong();
         
         Account ac1=session.get(Account.class, ac);
         if(amt>0) {
        	 
        	 long newBalance=(long) (ac1.getBalance()+amt);
        	 ac1.setBalance(newBalance);
        	 session.update(ac1);
        	 session.beginTransaction().commit();
        	 System.out.println("Deposited Successfully:" +newBalance);
         }
         else
         {
        	 System.out.println("No Sufficient Money In Your Account");
         }
}
}



