package whyarewestillalive.resourceserver;
import javax.persistence.Entity;


@Entity
public class Category {
	@Id
	@GeneratedValue
	protected int ID;
	protected String Name
	
	public int getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name=name;
	}
}
