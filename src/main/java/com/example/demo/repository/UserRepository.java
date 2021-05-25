package com.example.demo.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.example.demo.domain.User;
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

        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME)
                .append("(").append("id uuid PRIMARY KEY, ")
                .append("name text,")
                .append("birthdate timestamp,")
                .append("email text,")
                .append("password text);");

        final String query = sb.toString();
        session.execute(query);

    }

    /**
     * Insert a row in the table books. 
     * 
     * @param user
     */
    public void insertUser(User user) {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(email, birthdate, name, password) ")
                .append("VALUES ('")
                    .append(user.getEmail())
                    .append("', '")
                    .append(Timestamp.from(Instant.ofEpochMilli(user.getBirthdate().getTime())))
                    .append("', '")
                    .append(user.getName())
                    .append("', '")
                    .append(user.getPassword())
                .append("');");

        final String query = sb.toString();
        session.execute(query);

    }

    public void deleteUser(String email){

        StringBuilder sb = new StringBuilder("DELETE FROM ")
                .append(TABLE_NAME)
                .append(" WHERE id = ")
                .append(email).append(";");
        final String query = sb.toString();
        session.execute(query);
    }

    public User selectByEmail(String email) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE email = '")
                .append(email).append("';");

        final String query = sb.toString();

        ResultSet rs = session.execute(query);

        List<User> users = new ArrayList<>();

        for (Row r : rs) {
            User s = new User( r.getString("name"),
                   r.getTimestamp("birthdate"), r.getString("email"),r.getString("password"));
            users.add(s);
        }
        return users.get(0);

    }


    /**
     * Delete table.
     * 
     * @param tableName the name of the table to delete.
     */
    public void deleteTable(String tableName) {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);

        final String query = sb.toString();
        session.execute(query);
    }
}
