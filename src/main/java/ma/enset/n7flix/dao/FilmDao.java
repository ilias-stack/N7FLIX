package ma.enset.n7flix.dao;


import ma.enset.n7flix.dao.entities.Film;

import java.util.List;


public interface FilmDao {
    List<Film> getAllFilms();
    Film getFilmById(int id);
    List<Film> getFilmsByQuery(String query);
    List<Film> getFilmsByCategories(String[] categories);
    List<Film> getFromTo(int start ,int end);
    List<Film> getWatchedFilms(Integer userId);
}
