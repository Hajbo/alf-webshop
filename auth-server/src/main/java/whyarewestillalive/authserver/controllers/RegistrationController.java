package whyarewestillalive.authserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import whyarewestillalive.authserver.entities.User;
import whyarewestillalive.authserver.repositories.UserRepository;
import whyarewestillalive.authserver.responses.JsonResponse;
import whyarewestillalive.authserver.responses.ResponseType;

import java.util.List;
import java.util.Optional;

@RestController
public class RegistrationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
           value = "/register")
    public @ResponseBody
    JsonResponse register(@RequestBody User user) {
        Optional<User> existingUser = repository.findById(user.getName());
        log.debug("POST /register");
        if (!existingUser.isPresent()) {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setEnabled(true);
            newUser.setRoles(List.of("ROLE_USER"));
            repository.save(newUser);
            log.info("User registered successfully: " + user.getName());
            return new JsonResponse(ResponseType.SUCCESS, "User registered successfully.");
        } else {
            log.warn("User already exists: " + user.getName());
            return new JsonResponse(ResponseType.ERROR, "User already exist!");
        }
    }

    @DeleteMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            value = "/delete")
    public JsonResponse delete(@RequestBody User user){
        Optional<User> existingUser = repository.findById(user.getName());
        log.debug("POST /register");
        if (!existingUser.isPresent()) {
            log.info("User do not  exists: " + user.getName());
            return new JsonResponse(ResponseType.ERROR, "User delete is unsuccessful.");
        } else {
            repository.delete(user);
            log.warn("User is deleted: " + user.getName());
            return new JsonResponse(ResponseType.SUCCESS, "User delete is successful!");
        }
    }

}
