package com.dev.spring;

import com.dev.spring.config.AppConfig;
import com.dev.spring.model.User;
import com.dev.spring.service.UserService;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        final UserService userService = context.getBean(UserService.class);

        User mykola = new User();
        mykola.setName("mykola");
        mykola.setEmail("myk@mail.com");
        User petro = new User();
        petro.setName("petro");
        petro.setEmail("pet@mail.com");
        User olek = new User();
        olek.setName("olek");
        olek.setEmail("ole@mail.com");
        userService.add(mykola);
        userService.add(petro);
        userService.add(olek);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}
