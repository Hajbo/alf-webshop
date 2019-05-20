package whyarewestillalive.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("whyarewestillalive.resourceserver.*")
@EntityScan(basePackages = {"whyarewestillalive.resourceserver.Entities"})
@EnableJpaRepositories(basePackages = {"whyarewestillalive.resourceserver.Repositories"})
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }
}
