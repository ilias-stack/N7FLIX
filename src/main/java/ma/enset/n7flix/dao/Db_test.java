package ma.enset.n7flix.dao;

import ma.enset.n7flix.dao.entities.Film;
import ma.enset.n7flix.dao.entities.Rating;

public class Db_test {
    public static void main(String[] args) {
        System.out.println(new FilmDaoImp().getWatchedFilms(24).size());
    }
}
