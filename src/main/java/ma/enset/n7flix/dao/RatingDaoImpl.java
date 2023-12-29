package ma.enset.n7flix.dao;

import ma.enset.n7flix.dao.entities.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDaoImpl implements RatingDao {
    Connection connection = null;

    public RatingDaoImpl() {
        connection = DbSingleton.getConnection();
    }

    @Override
    public List<Rating> getAllRatingOf(Integer userId) {
        List<Rating> ratings = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ratings where userid=?");
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                ratings.add(new Rating(resultSet.getInt(1), resultSet.getFloat(2),
                        resultSet.getInt(3), resultSet.getInt(4)));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ratings;
    }

    @Override
    public void setRating(Integer movieId, Integer userId, float rate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ratings (movieId, userId, rating) VALUES (?, ?, ?) " +
                                                  "ON DUPLICATE KEY UPDATE rating = VALUES(rating)");
            preparedStatement.setInt(1, movieId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setFloat(3, rate);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float getRating(Integer movieId, Integer userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ratings where userid=? and movieid=?");
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,movieId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getFloat("rating");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
