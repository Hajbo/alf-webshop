package whyarewestillalive.resourceserver.Entities;

import javax.persistence.*;

import java.util.Date;


@Entity
public class User {
	@Id
	@GeneratedValue
	protected Long id;
	protected int balance;
	
	protected String name;
	protected String email;
	
	protected Date lastlogin;
	@OneToOne
	protected Cart cart;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getBalance() {
		return balance;
	}
	public Date getLastLogin() {
		return lastlogin;
	}
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart c) {
		this.cart=c;
	}
	public void setId(Long i) {
		this.id=i;
	}
	public void setName(String n) {
		this.name=n;
	}
	public void setEmail(String em) {
		this.email=em;
	}
	public void setBalance(int b) {
		this.balance=b;
	}
	public void setLastLogin(Date login) {
		this.lastlogin=login;
	}
	public void changeBalance(Long value) {
		this.balance+=value;
	}
	
	public boolean canPay(int i) {
		if(balance>=i) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean Pay(int i) {
		if(canPay(i)) {
			balance-=i;
			return true;
		}
		else {
			return false;
		}
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.getId()) : user.getId() == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
