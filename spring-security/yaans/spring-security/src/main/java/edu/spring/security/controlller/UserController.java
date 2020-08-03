package edu.spring.security.controlller;

import edu.spring.security.domain.User;
import edu.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/add")
    public void saveDummyUsers(){
        for (int i = 0; i < 10; i++) {
            User user = new User("user"+i, "userp", "ADMIN");
        }

    }

    @GetMapping("/login")
    public String login(){
        return "login page";
    }
    @GetMapping("/signin")
    public String signIn(){
        return "sign in page";
    }
}

