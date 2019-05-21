package whyarewestillalive.resourceserver.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
@Entity
public class Cart {
	
	@Id
	@Column
	protected Long userid;

	@OneToOne
	protected User user;
	
	@ManyToMany
	protected List<Item> items=new ArrayList<Item>();
	
	public Long getUserId() {
		return userid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User u) {
		this.user=u;
		this.userid=u.getId();
	}
	public int getPrice() {
		int sum=0;
		for(int i = 0;i<items.size();i++) {
			sum+=items.get(i).getPrice();
		}
		return sum;
	}
	public List<Item> getItems(){
		return items;
	}
	public void addItem(Item added) {
		items.add(added);
		added.getCarts().add(this);
	}
	public void removeItem(Item removed) {
		items.remove(removed);
	}
	public void clear() {
		items.clear();
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (userid != null)
			return userid.equals(cart.getUserId());
		else
			return cart.getUserId() == null;
    }

    @Override
    public int hashCode() {
        return userid != null ? userid.hashCode() : 0;
    }
}
