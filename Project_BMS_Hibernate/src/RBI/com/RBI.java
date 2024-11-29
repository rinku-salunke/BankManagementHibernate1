package RBI.com;

import com.InvalidAccountNumberException;
import com.InvalidAdharNumberException;
import com.InvalidContactException;
import com.NotEligibleForAccountCreationException;

public interface RBI {
	
public void createAccount() throws InvalidContactException, InvalidAdharNumberException, NotEligibleForAccountCreationException, InvalidAccountNumberException, Exception;
public void checkBalance() throws Exception;
public void showdetails() throws Exception;
public void withdrawAmount() throws Exception;
public void depositAmount() throws Exception;



}

