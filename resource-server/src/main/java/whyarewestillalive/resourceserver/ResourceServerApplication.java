package whyarewestillalive.resourceserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import whyarewestillalive.resourceserver.Entities.Cart;
import whyarewestillalive.resourceserver.Entities.Item;
import whyarewestillalive.resourceserver.Entities.User;
import whyarewestillalive.resourceserver.Repositories.CartRepository;
import whyarewestillalive.resourceserver.Repositories.ItemRepository;
import whyarewestillalive.resourceserver.Repositories.UserRepository;

import java.util.List;

@SpringBootApplication
@ComponentScan("whyarewestillalive.resourceserver.*")
@EntityScan(basePackages = {"whyarewestillalive.resourceserver.Entities"})
@EnableJpaRepositories(basePackages = {"whyarewestillalive.resourceserver.Repositories"})
public class ResourceServerApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository repository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;


    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("tester");

        User admin = new User();
        admin.setName("admin");

        repository.saveAll(List.of(user, admin));

        Cart adminCart = new Cart();
        adminCart.setUser(admin);

        Cart userCart = new Cart();
        userCart.setUser(user);

        cartRepository.saveAll(List.of(userCart, adminCart));

        Item test1 = new Item();
        test1.setPrice(Long.parseLong("123"));
        test1.setCategory("cica");
        test1.setDescription("testitem");
        test1.setUserId(user.getId());
        itemRepository.save(test1);

        adminCart.addItem(test1);
        cartRepository.save(adminCart);


        log.info("Resource server started.");
    }
}
