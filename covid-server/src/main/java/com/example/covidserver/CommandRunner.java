package com.example.covidserver;

import com.example.covidserver.domain.dictionary.Role;
import com.example.covidserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandRunner implements CommandLineRunner {

    private static final String USER_USERNAME = "john";
    private static final String USER_PASSWORD = "user";
    private static final String ADMIN_USERNAME = "mario";
    private static final String ADMIN_PASSWORD = "admin";

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        //Admin user creation if not exists
        userService.saveNewUser(ADMIN_USERNAME, ADMIN_PASSWORD, Role.ADMIN);
        //Plain user creation if not exists
        userService.saveNewUser(USER_USERNAME, USER_PASSWORD, Role.USER);
    }
}
