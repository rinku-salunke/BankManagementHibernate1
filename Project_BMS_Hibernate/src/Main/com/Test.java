package Main.com;

import java.util.Scanner;

import com.InvalidAccountNumberException;
import com.InvalidAdharNumberException;
import com.InvalidContactException;
import com.NotEligibleForAccountCreationException;

import SBI.com.*;
import RBI.com.RBI;

public class Test {
public static void main(String[] args) throws Exception {
	RBI k=new SBI();
	try (Scanner sc = new Scanner(System.in)) {
		while(true) {
		System.out.println("Enter Your Choice To Call The Method");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
		{
			try
			{
			k.createAccount();
			}
			catch(InvalidContactException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidAdharNumberException l) {
				System.out.println(l.getMessage());
			}
			catch(NotEligibleForAccountCreationException h) {
				System.out.println(h.getMessage());
			}
			catch(InvalidAccountNumberException j) {
				System.out.println(j.getMessage());
			}
			break;
		}
		case 2:
		{
			k.showdetails();
			break;
		}
		case 3:
		{
			k.checkBalance();
			break;
		}
		case 4:
		{
			k.withdrawAmount();
			break;
		}
		case 5:
		{
			k.depositAmount();
			break;
		}
		default:
		{
			System.out.println("Invalid Data Entry");
		}
		}	
}
}
}
}
