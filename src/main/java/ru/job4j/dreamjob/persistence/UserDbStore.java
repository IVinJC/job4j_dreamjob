package ru.job4j.dreamjob.persistence;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;

import javax.annotation.concurrent.ThreadSafe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@ThreadSafe
@Repository
public class UserDbStore {
    private final BasicDataSource pool;

    public UserDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        Optional<User> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            rsl = Optional.of(user);
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return rsl;
    }

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        Optional<User> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")
        ) {
            ps.setString(2, email);
            ps.setString(3, password);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    User user = new User(it.getInt("id"),
                            it.getString("email"),
                            it.getString("password"));
                    user.setName(it.getString("name"));
                    rsl = Optional.of(user);
                }
            }
        } catch (Exception e) {
            log.error("Can't find user by id", e);
        }
        return rsl;
    }
}
