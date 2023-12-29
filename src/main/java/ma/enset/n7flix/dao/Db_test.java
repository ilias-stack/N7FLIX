package ma.enset.n7flix.dao;

import ma.enset.n7flix.dao.entities.Film;
import ma.enset.n7flix.dao.entities.Rating;

public class Db_test {
    public static void main(String[] args) {
        new RatingDaoImpl().setRating(244,7,0.4f);
        System.out.println(new RatingDaoImpl().getRating(244,7));
    }
}
