package ma.enset.n7flix.dao;

import ma.enset.n7flix.dao.entities.Film;

public class Db_test {
    public static void main(String[] args) {
        String[] categs={"action"};
        for(Film film : new FilmDaoImp().getFilmsByCategories(categs)){
            System.out.println(film.getGenre());
        }
    }
}
