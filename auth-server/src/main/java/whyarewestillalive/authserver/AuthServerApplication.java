package whyarewestillalive.authserver;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import whyarewestillalive.authserver.entities.User;
import whyarewestillalive.authserver.repositories.UserRepository;

@SpringBootApplication
public class AuthServerApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("demo");
        user.setPassword(passwordEncoder.encode("demo"));
        user.setEnabled(true);
        user.setRoles(List.of("ROLE_USER"));

        User admin = new User();
        admin.setName("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEnabled(true);
        admin.setRoles(List.of("ROLE_ADMIN"));

        repository.saveAll(List.of(user, admin));

        log.info("Auth server started.");
    }
}