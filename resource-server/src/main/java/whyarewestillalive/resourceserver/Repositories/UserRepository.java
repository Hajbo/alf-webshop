package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;

@Repository
public class UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	//Save user
	@Transactional
	public User save(User user) {
		return em.merge(user);
	}

	//Get all user
	public List<User> findAll() {
		return em.createQuery("SELECT t FROM User t", User.class).getResultList();
	}

	//Find user with id of "id"
	public User findById(long id) {
		return em.find(User.class, id);
	}

	//Delete user with id of "id"
	@Transactional
	public void deleteById(long id) {
		User user = findById(id);
		em.remove(user);
	}
}
