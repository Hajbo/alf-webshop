package whyarewestillalive.resourceserver.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
	
	@Id
	protected Long userid;
	
	@OneToOne
	protected User user;
	
	
	protected List<Item> items;
	
	public Long getUserId() {
		return userid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User u) {
		this.user=u;
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
