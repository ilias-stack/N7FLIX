package ma.enset.n7flix.dao;

import ma.enset.n7flix.dao.entities.User;

import java.sql.SQLException;

public interface UserDao {
    User validCredentials(String identifier, String password);
    User createUser(User user) throws SQLException;
}
