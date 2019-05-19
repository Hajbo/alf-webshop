package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;


@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long>{
	
	public Category findById(long id);
	public Category findByName(String name);

}
