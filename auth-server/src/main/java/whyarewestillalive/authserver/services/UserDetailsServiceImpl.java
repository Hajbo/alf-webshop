package whyarewestillalive.authserver.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import whyarewestillalive.authserver.entities.User;
import whyarewestillalive.authserver.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findById(username);
        log.debug("FindById " + username);
        if (!user.isPresent()) {
            log.warn("UsernameNotFound: " + username);
            throw new UsernameNotFoundException(username + " is an invalid username");
        } else {
            log.debug("Username was found: " + username);
            return new UserDetailsImpl(user.get());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("Return passwordEncoder");
        return new BCryptPasswordEncoder();
    }
}
