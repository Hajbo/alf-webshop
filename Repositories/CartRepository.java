package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CartRepository  extends JpaRepository<Cart,long>{
	public Cart findById(long userid);
}
