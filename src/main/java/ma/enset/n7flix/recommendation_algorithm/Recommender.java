package ma.enset.n7flix.recommendation_algorithm;

import ma.enset.n7flix.dao.FilmDaoImp;
import ma.enset.n7flix.dao.RatingDaoImpl;
import ma.enset.n7flix.dao.entities.Film;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Recommender {
    final Integer userId;
    final FilmDaoImp films;
    final RatingDaoImpl ratings;

    private HashMap<Integer,Double> filmScore=new HashMap<>();
    public Recommender(Integer id){
        this.userId = id;
        films = new FilmDaoImp();
        ratings = new RatingDaoImpl();
    }

    public List<Film> getLikedFilms(){
        var positiveRates = ratings.getPositiveRatingsOf(userId);
        List<Film> likedFilms = new ArrayList<>();

        for (var rating:positiveRates)
            likedFilms.add(films.getFilmById(rating.getMovieId()));

        return likedFilms;
    }

    public byte[] filmGenreToByteArray(String genre1,String genre2){
        String[] genre1Array = genre1.split(", ");
        String[] genre2Array = genre2.split(", ");
        byte[] byteArray = new byte[Math.max(genre1Array.length,genre2Array.length)];
        Arrays.fill(byteArray, (byte) 0);
        for (int i=0;i<genre1Array.length;i++)
            for (int j = 0; j < genre2Array.length; j++) {
                if(genre2Array[j].equals(genre1Array[i])) {
                    byteArray[i] = 1;
                    break;
                }
            }

        System.out.println(Arrays.toString(byteArray));
        return byteArray;
    }
}
