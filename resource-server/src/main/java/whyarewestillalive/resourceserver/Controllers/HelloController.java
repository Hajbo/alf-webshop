package whyarewestillalive.resourceserver.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index(@AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt.toString());
        return String.format("Hello, %s!", jwt.getSubject());
    }

    @GetMapping("/message")
    public String message() {
        return "secret message";
    }

}
