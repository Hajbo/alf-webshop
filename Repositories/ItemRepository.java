package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import whyarewestillalive.resourceserver.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepository extends JpaRepository<Item,long>{
	public List<Item> findAll();
	public User findById(long id);
}
