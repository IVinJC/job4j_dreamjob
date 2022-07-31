package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.persistence.UserDbStore;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;
@ThreadSafe
@Service
public class UserService {
    private final UserDbStore userDbStore;

    public UserService(UserDbStore userDbStore) {
        this.userDbStore = userDbStore;
    }

    public Optional<User> add(User user) {
        userDbStore.add(user);
        return Optional.of(user);
    }

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        return userDbStore.findUserByEmailAndPwd(email, password);
    }
}
