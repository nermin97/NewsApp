package com.softraysolutions.news.util;

import com.softraysolutions.news.model.User;
import com.softraysolutions.news.model.enumeration.Enumerations;
import com.softraysolutions.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserSeed {

    @Value("${seed.admin-email}")
    private String email;

    @Value("${seed.admin-password}")
    private String password;

    @Bean
    public CommandLineRunner loadUserData(UserRepository repository) {
        return (args -> {
            if (repository.findAll().size() == 0) {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(Enumerations.UserType.SuperAdmin);
                repository.save(user);
            }
        });
    }
}
