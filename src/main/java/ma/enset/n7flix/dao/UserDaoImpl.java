package ma.enset.n7flix.dao;

import ma.enset.n7flix.dao.entities.User;

import java.sql.*;

public class UserDaoImpl implements UserDao{
    @Override
    public User validCredentials(String identifier, String password) {
        Connection connection = DbSingleton.getConnection();
        try{
            PreparedStatement pst=connection.prepareStatement("select * from users where (email=? or username=?) and password=?");
            pst.setString(1,identifier);
            pst.setString(2,identifier);
            pst.setString(3,password);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                return new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
            }
            return null;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User createUser(User user) throws SQLException {
        Connection connection = DbSingleton.getConnection();
        PreparedStatement pst=connection.prepareStatement("insert into users (username,email,password,birthday) values(?,?,?,?)");
        pst.setString(1,user.getUsername());
        pst.setString(2,user.getEmail());
        pst.setString(3,user.getPassword());
        pst.setString(4,user.getBirthDay());

        pst.executeUpdate();

        return user;
    }
}
