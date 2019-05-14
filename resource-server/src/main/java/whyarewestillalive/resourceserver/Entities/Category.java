package whyarewestillalive.resourceserver.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
	@Id
	@GeneratedValue
	protected Long id;
	protected String name;
	
	@OneToMany(mappedBy= "category")
	protected List<Item> items;
	
	public Long getId() {
		return id;
	}
	public void setId(Long i) {
		this.id=i;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		this.name=n;
	}
	
	public List<Item> getItems(){
		return items;
	}
	public void setItems(List<Item> item) {
		this.items=item;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;
        return id != null ? id.equals(category.getId()) : category.getId() == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
