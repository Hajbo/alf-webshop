package whyarewestillalive.resourceserver;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Item {
	@Id
	@GeneratedValue
	protected int ID;
	protected int UserID;
	protected int CategoryID;
	protected String Summary;
	protected String Description;
	protected int Price;
	protected Date CreationDate;
	protected Date ExpirationDate;

	public int getID() {
		return ID;
	}
	public int getUserID() {
		return UserId;
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public String getSummary() {
		return Summary;
	}
	public String getDescription() {
		return Description;
	}
	public int getPrice() {
		return Price;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public Date getExpirationDate() {
		return ExpirationDate;
	}
	
	public void setID(int id) {
		this.ID=id;
	}
	public void setUserID(int id) {
		this.UserID=id;
	}
	public void setCategoryId(int id) {
		this.CategoryID=id;
	}
	public void setSummary(String summary) {
		this.Summary=summary;
	}
	public void setPrice(int price) {
		this.Price=price;
	}
	public void setCreationDate(Date cd) {
		this.CreationDate=cd;
	}
	public void setExpirationDate(Date ed) {
		this.ExpirationDate=ed;
	}
	
}
