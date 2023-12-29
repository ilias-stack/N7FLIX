package ma.enset.n7flix.dao;

import ma.enset.n7flix.dao.entities.Rating;

import java.util.List;

public interface RatingDao {
    List<Rating> getAllRatingOf(Integer userId);
    void setRating(Integer movieId,Integer userId,float rate);
    float getRating(Integer movieId,Integer userId);

}