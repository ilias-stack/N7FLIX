package ma.enset.n7flix.dao;


import ma.enset.n7flix.dao.entities.Film;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoImp implements FilmDao{
    @Override
    public List<Film> getAllFilms() {
        List<Film> list = new ArrayList<>();

        Connection connection = DbSingleton.getConnection();

        try{
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM movies");
             ResultSet rst = pst.executeQuery() ;

            while (rst.next()) {
                Film film = new Film();
                film.setId(rst.getInt("id"));
                film.setPosterLink(rst.getString("Poster_Link"));
                film.setSeriesTitle(rst.getString("Series_Title"));
                film.setReleasedYear(rst.getInt("Released_Year"));
                film.setCertificate(rst.getString("Certificate"));
                film.setRuntime(rst.getInt("Runtime"));
                film.setGenre(rst.getString("Genre"));
                film.setImdbRating(rst.getDouble("IMDB_Rating"));
                film.setOverview(rst.getString("Overview"));
                film.setMetaScore(rst.getInt("Meta_score"));
                film.setDirector(rst.getString("Director"));
                film.setStar1(rst.getString("Star1"));
                film.setStar2(rst.getString("Star2"));
                film.setStar3(rst.getString("Star3"));
                film.setStar4(rst.getString("Star4"));
                film.setNoOfVotes(rst.getInt("No_of_Votes"));
                film.setGross(rst.getDouble("Gross"));

                list.add(film);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gère les exceptions de manière appropriée dans un projet réel
        }

        return list;
    }


    @Override
    public Film getFilmById(int id) {
        Film film = new Film();
        Connection connection = DbSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM movies WHERE ID = ?");
             ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                film.setId(rst.getInt("id"));
                film.setPosterLink(rst.getString("Poster_Link"));
                film.setSeriesTitle(rst.getString("Series_Title"));
                film.setReleasedYear(rst.getInt("Released_Year"));
                film.setCertificate(rst.getString("Certificate"));
                film.setRuntime(rst.getInt("Runtime"));
                film.setGenre(rst.getString("Genre"));
                film.setImdbRating(rst.getDouble("IMDB_Rating"));
                film.setOverview(rst.getString("Overview"));
                film.setMetaScore(rst.getInt("Meta_score"));
                film.setDirector(rst.getString("Director"));
                film.setStar1(rst.getString("Star1"));
                film.setStar2(rst.getString("Star2"));
                film.setStar3(rst.getString("Star3"));
                film.setStar4(rst.getString("Star4"));
                film.setNoOfVotes(rst.getDouble("No_of_Votes"));
                film.setGross(rst.getDouble("Gross"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gère les exceptions de manière appropriée dans un projet réel
        }
        return film;
    }


    @Override
    public List<Film> getFromTo(int start , int end ) {
        List<Film> list = new ArrayList<>();
        Connection connection = DbSingleton.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM movies WHERE id BETWEEN ? AND ?");
            pst.setInt(1,start);
            pst.setInt(2,end);
            ResultSet rst = pst.executeQuery() ;

            while (rst.next()) {
                Film film = new Film();
                film.setId(rst.getInt("id"));
                film.setPosterLink(rst.getString("Poster_Link"));
                film.setSeriesTitle(rst.getString("Series_Title"));
                film.setReleasedYear(rst.getInt("Released_Year"));
                film.setCertificate(rst.getString("Certificate"));
                film.setRuntime(rst.getInt("Runtime"));
                film.setGenre(rst.getString("Genre"));
                film.setImdbRating(rst.getDouble("IMDB_Rating"));
                film.setOverview(rst.getString("Overview"));
                film.setMetaScore(rst.getInt("Meta_score"));
                film.setDirector(rst.getString("Director"));
                film.setStar1(rst.getString("Star1"));
                film.setStar2(rst.getString("Star2"));
                film.setStar3(rst.getString("Star3"));
                film.setStar4(rst.getString("Star4"));
                film.setNoOfVotes(rst.getDouble("No_of_Votes"));
                film.setGross(rst.getDouble("Gross"));
                list.add(film);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
