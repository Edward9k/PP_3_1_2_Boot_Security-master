package ru.kata.spring.boot_security.demo.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Arrays;

@SpringBootApplication
public class TestUserRun {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public TestUserRun(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void initializeUsersAndRoles() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        roleRepository.saveAll(Arrays.asList(userRole, adminRole));

        User user1 = new User("user", "user", 54, "User", passwordEncoder.encode("us6198675517er"));

        if (!userRepository.findByUsername(user1.getUsername()).isPresent()) {
            user1.setRoles(Arrays.asList(userRole));
            userRepository.save(user1);
        } else {
            System.out.println(user1.getUsername() + " уже существует");
        }

        User user2 = new User("admin", "admin", 80, "Admin", passwordEncoder.encode("ad63758267217min"));

        if (!userRepository.findByUsername(user2.getUsername()).isPresent()) {
            user2.setRoles(Arrays.asList(userRole, adminRole));
            userRepository.save(user2);
        } else {
            System.out.println(user2.getUsername() + " уже существует");
        }

    }
}

