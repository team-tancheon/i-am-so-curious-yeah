package edu.spring.security.repository;

import edu.spring.security.domain.User;
import edu.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void ttt(){

        for (int i = 0; i < 10; i++) {
            User user = new User("test"+i,"test"+i, "ADMIN");
            User save = userRepository.save(user);
            System.out.println("저장 완료 :"+save.toString());
        }
    }
}
