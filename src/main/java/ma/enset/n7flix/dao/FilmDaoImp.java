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
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public Film getFilmById(int id) {
        Film film = new Film();
        Connection connection = DbSingleton.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM movies WHERE ID = ?");
            pst.setInt(1,id);
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
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public List<Film> getFilmsByQuery(String query) {
        List<Film> list = new ArrayList<>();

        Connection connection = DbSingleton.getConnection();
        String upperQuery=query.toUpperCase();
        try{
            PreparedStatement pst = connection.prepareStatement(
                    "SELECT * FROM movies " +
                            "WHERE UPPER(genre) LIKE ? OR " +
                            "UPPER(series_title) LIKE ? OR " +
                            "UPPER(released_year) LIKE ? OR " +
                            "UPPER(director) LIKE ? OR " +
                            "UPPER(star1) LIKE ? OR " +
                            "UPPER(star2) LIKE ? OR " +
                            "UPPER(star3) LIKE ? OR " +
                            "UPPER(star4) LIKE ?");

            for (int i = 1; i <= 8; i++)
                pst.setString(i, "%" + upperQuery + "%");

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
            e.printStackTrace();
            return null;
        }

        return list;
    }

    @Override
    public List<Film> getFilmsByCategories(String[] categories) {
        List<Film> list = new ArrayList<>();

        Connection connection = DbSingleton.getConnection();
        StringBuilder query= new StringBuilder("SELECT * FROM movies where ");
        try{
            for (int i = 0; i < categories.length ; i++) {
                if(i< categories.length-1) query.append("UPPER(genre) like '").append(categories[i].toUpperCase()).append("%' or ");
                else query.append("UPPER(genre) like '").append(categories[i].toUpperCase()).append("%'");
            }
            PreparedStatement pst = connection.prepareStatement(query.toString());

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
            e.printStackTrace();
            return null;
        }

        return list;
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
