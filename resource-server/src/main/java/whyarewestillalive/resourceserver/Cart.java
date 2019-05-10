package whyarewestillalive.resourceserver;
import whyarewestillalive.resourceserver.Item;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Cart {
	@Id
	protected int UserID;
	protected List<Item> Items;
	
	public int getUserID() {
		return UserId;
	}
	public int getPrice() {
		int sum=0;
		for(int i = 0;i<Items.size();i++) {
			sum+=Items.get(i).getPrice();
		}
		return sum;
	}
	public List<Item> getItems(){
		return Items;
	}
	public void AddItem(Item added) {
		Items.add(added);
	}
	public void RemoveItem(Item removed) {
		Items.remove(removed);
	}
}
