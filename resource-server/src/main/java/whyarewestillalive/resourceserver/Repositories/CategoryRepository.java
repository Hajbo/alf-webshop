package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;
	
@Repository
public class CategoryRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Category save(Category c) {
		return em.merge(c);
	}

	//Looks up category based on id
	public Category findById(long id) {
		return em.find(Category.class, id);
	}
	//Looks up category based on name
	public Category findByName(String name) {
		return em.createQuery("SELECT t FROM Category t WHERE t.name LIKE :NAME",Category.class).setParameter("NAME",name).getSingleResult();
	}

	@Transactional
	public void deleteById(long id) {
		Category c = findById(id);
		em.remove(c);
	}
	
}
