package whyarewestillalive.resourceserver.Repositories;

import org.springframework.stereotype.Repository;

import whyarewestillalive.resourceserver.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long>{
	public Cart findByUserid(long userid);
}
