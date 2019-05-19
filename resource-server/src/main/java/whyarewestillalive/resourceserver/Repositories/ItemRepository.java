package whyarewestillalive.resourceserver.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whyarewestillalive.resourceserver.Entities.*;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	public List<Item> findAll();
	public Item findById(long id);
}
