package whyarewestillalive.resourceserver;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class User {
	@Id
	protected int ID;
	protected String Name;
	protected String Email;
	protected int Balance;
	protected Date Lastlogin;
	
	public int getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public String getEmail() {
		return Email;
	}
	public int getBalance() {
		return Balance;
	}
	public Date getLastLogin() {
		return Lastlogin;
	}
	
	public void setID(int id) {
		this.ID=id;
	}
	public void setName(String name) {
		this.Name=name;
	}
	public void setEmail(String email) {
		this.Email=email;
	}
	public void setBalance(int balance) {
		this.Balance=balance;
	}
	public void setLastLogin(Date login) {
		this.Lastlogin=login;
	}
	public void ChangeBalance(int value) {
		this.Balance+=value;
	}
	
	public boolean CanPay(int value) {
		if(Balance>=value) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean Pay(int value) {
		if(Can_Pay(value)) {
			Balance-=value;
			return true;
		}
		else {
			return false;
		}
	}

}
