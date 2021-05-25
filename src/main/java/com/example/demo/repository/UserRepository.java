package com.example.demo.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.example.demo.domain.User;
import com.example.demo.exceptions.UserParameterException;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private static final String TABLE_NAME = "users";
    private final Session session;

    public UserRepository(Session session) {
        this.session = session;
    }

    /**
     * Creates the user table.
     */
    public void createTable() {

        final String query = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME +
                "(" + "id uuid PRIMARY KEY, " +
                "name text," +
                "birthdate timestamp," +
                "email text," +
                "password text);";
        session.execute(query);

    }

    /**
     * Insert a row in the table Users.
     *
     * @param user user
     */
    public void insertUser(User user) {

        final String query = "INSERT INTO " +
                TABLE_NAME +
                "(email, birthdate, name, password) " +
                "VALUES ('" +
                user.getEmail() +
                "', '" +
                Timestamp.from(Instant.ofEpochMilli(user.getBirthdate().getTime())) +
                "', '" +
                user.getName() +
                "', '" +
                user.getPassword() +
                "') IF NOT EXISTS;";
        if (!session.execute(query).wasApplied()) {
            throw new UserParameterException(List.of("Email is already registered"));
        }

    }

    /**
     * Delete user from user table
     * @param email user's email
     */
    public void deleteUser(String email) {

        final String query = "DELETE FROM " +
                TABLE_NAME +
                " WHERE email = '" +
                email + "';";
        if (!session.execute(query).wasApplied()) {
            throw new UserParameterException(List.of("User not found"));
        }
    }

    /**
     * Select user by email
     * @param email user's email
     *
     * @return found uses
     */
    public User selectByEmail(String email) {

        final String query = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE email = '" +
                email + "';";

        ResultSet rs = session.execute(query);

        List<User> users = new ArrayList<>();

        for (Row r : rs) {
            User s = new User(r.getString("name"),
                    r.getTimestamp("birthdate"), r.getString("email"), r.getString("password"));
            users.add(s);
        }

        if (users.isEmpty()) {
            throw new UserParameterException(List.of("User not found"));
        }
        return users.get(0);

    }


    /**
     * Delete table.
     *
     * @param tableName the name of the table to delete.
     */
    public void deleteTable(String tableName) {

        final String query = "DROP TABLE IF EXISTS " + tableName;
        session.execute(query);
    }
}
