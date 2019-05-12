package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;

@Repository
public class CartRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	//Merges cart back into DB
	@Transactional
	public Cart save(Cart cart) {
		return em.merge(cart);
	}

	//Finds the cart of the owner with the name of "name"
	public Cart findByOwner(String name) {
		//Find owner id
		Long id=findOwner(name).getId();
		//Return cart where the userid==id
		return em.createQuery("SELECT t FROM Cart t WHERE t.userid LIKE :ID", Cart.class).setParameter("ID",id).getSingleResult();
	}
	
	//Finds the user with the name of "name"
	public User findOwner(String name) {
		return em.createQuery("SELECT t FROM User t WHERE t.name LIKE :NAME",User.class).setParameter("NAME",name).getSingleResult();
	}
	
	//Add the item with id of "id" to the user with the name of "name"'s cart
	public boolean addItem(String name,long id) {
		//Find cart of the user
		Cart c=findByOwner(name);
		//Find item
		Item added=em.createQuery("SELECT t FROM Item t WHERE t.id LIKE :ID",Item.class).setParameter("ID",id).getSingleResult();
		//Item and cart found
		if(c!=null && added!=null) {
			//Add item to cart and save
			c.addItem(added);
			save(c);
			return true;
		}
		return false;
	}
	
	//Removes the item with id of "id" from the user with the name of "name"'s cart
	public boolean removeItem(String name,long id) {
		//Find cart of the user
		Cart c=findByOwner(name);
		//Find item 
		Item removed=em.createQuery("SELECT t FROM Item t WHERE t.id LIKE :ID",Item.class).setParameter("ID",id).getSingleResult();
		//If they exist,remove from cart the item
		if(c!=null && removed!=null) {
			//Remove item from cart and save
			c.removeItem(removed);
			save(c);
			return true;
		}
		return false;
	}
	
}
