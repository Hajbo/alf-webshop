package whyarewestillalive.resourceserver;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import whyarewestillalive.resourceserver.Item;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ItemRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Item save(Item item) {
		return em.merge(item);
	}

	public List<Item> findAll() {
		return em.createQuery("SELECT t FROM Item t", Item.class).getResultList();
	}

	public Item findById(long id) {
		return em.find(Item.class, id);
	}

	@Transactional
	public void deleteById(long id) {
		Item item = findById(id);
		em.remove(item);
	}
}
