package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;

@Repository
public class ItemRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	//Creates item,and sets userid
	public void create(String owner,Item item) {
		//Find user
		User u=findOwner(owner);
		//Set owner
		item.setUserId(u.getId());
		//Save
	    em.merge(item);
	}
	///Updates item if owner is valid
	public boolean update(String owner,Item item) {
		//Find owner by name
		User u=findOwner(owner);
		//If user is the owner of the item
		if(u.getId()==item.getUserId()) {
			//Update
			em.merge(item);
			return true;
		}
		else return false;
	}
	//Saves item
	@Transactional
	public Item save(Item item) {
	    return em.merge(item);
	}
	
	//Returns every item
	public List<Item> findAll() {
		return em.createQuery("SELECT t FROM Item t", Item.class).getResultList();
	}
	//Finds the user with the name of "name"
	public User findOwner(String name) {
		return em.createQuery("SELECT t FROM User t WHERE t.name LIKE :NAME",User.class).setParameter("NAME",name).getSingleResult();
	}
	//Deletes item with id of "id" if the owner of item is valid
	public boolean deleteItem(String owner,long id) {
		//Find owner and item
		User u=findOwner(owner);
		Item i=findById(id);
		//Userid=id,transaction is valid
		if(i.getUserId()==u.getId()) {
			//Remove item
			em.remove(i);
			return true;
		}
		else return false;
	}
	//Finds item with id of "id"
	public Item findById(long id) {
		return em.find(Item.class, id);
	}

}
