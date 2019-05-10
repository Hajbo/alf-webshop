package whyarewestillalive.resourceserver;

	import org.springframework.stereotype.Repository;
	import org.springframework.transaction.annotation.Transactional;

	import whyarewestillalive.resourceserver.Category;
	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	
@Repository
public class CategoryRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Category save(Category c) {
		return em.merge(c);
	}

	public Category findById(long id) {
		return em.find(Category.class, id);
	}

	@Transactional
	public void deleteById(long id) {
		Category c = findById(id);
		em.remove(c);
	}
	
}
