package whyarewestillalive.resourceserver;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import whyarewestillalive.resourceserver.User;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public User save(User user) {
		return em.merge(user);
	}

	public List<User> findAll() {
		return em.createQuery("SELECT t FROM User t", User.class).getResultList();
	}

	public User findById(long id) {
		return em.find(User.class, id);
	}

	@Transactional
	public void deleteById(long id) {
		User user = findById(id);
		em.remove(user);
	}
}
