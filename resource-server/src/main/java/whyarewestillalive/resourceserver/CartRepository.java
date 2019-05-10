package whyarewestillalive.resourceserver;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import whyarewestillalive.resourceserver.Cart;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CartRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Cart save(Cart cart) {
		return em.merge(cart);
	}

	public Cart findById(long id) {
		return em.find(Cart.class, id);
	}

	@Transactional
	public void deleteById(long id) {
		Cart cart = findById(id);
		em.remove(cart);
	}
}
