package whyarewestillalive.resourceserver.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;

@Repository
public interface UserRepository extends JpaRepository<User,long>{

	public User findById(long id);
	public User findByName(String name);
}
