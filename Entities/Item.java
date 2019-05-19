package whyarewestillalive.resourceserver.Entities;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Item {
	@Id
	@GeneratedValue
	protected Long id;
	protected Long userid;
	protected Long price;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	protected Category category;
	
	protected String summary;
	protected String description;
	
	protected Date creationdate;
	protected Date expirationdate;

	public Long getId() {
		return id;
	}
	public Long getUserId() {
		return userid;
	}
	public Category getCategory() {
		return category;
	}
	public String getSummary() {
		return summary;
	}
	public String getDescription() {
		return description;
	}
	public Long getPrice() {
		return price;
	}
	public Date getCreationDate() {
		return creationdate;
	}
	public Date getExpirationDate() {
		return expirationdate;
	}
	
	public void setId(Long i) {
		this.id=i;
	}
	public void setUserId(Long i) {
		this.userid=i;
	}
	public void setCategory(Category cat) {
		this.category=cat;
	}
	public void setSummary(String sum) {
		this.summary=sum;
	}
	public void setPrice(Long p) {
		this.price=p;
	}
	public void setCreationDate(Date cd) {
		this.creationdate=cd;
	}
	public void setExpirationDate(Date ed) {
		this.expirationdate=ed;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item i = (Item) o;

        return id != null ? id.equals(i.getId()) : i.getId() == null;
    }
	@Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}