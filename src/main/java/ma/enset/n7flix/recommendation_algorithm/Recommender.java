package ma.enset.n7flix.recommendation_algorithm;

import ma.enset.n7flix.dao.FilmDaoImp;
import ma.enset.n7flix.dao.RatingDaoImpl;
import ma.enset.n7flix.dao.entities.Film;

import java.util.*;

public class Recommender {
    final Integer userId;
    final FilmDaoImp films;
    final RatingDaoImpl ratings;

    public Recommender(Integer id){
        this.userId = id;
        films = new FilmDaoImp();
        ratings = new RatingDaoImpl();
    }

    private List<Film> getLikedFilms(){
        var positiveRates = ratings.getPositiveRatingsOf(userId);
        List<Film> likedFilms = new ArrayList<>();

        for (var rating:positiveRates)
            likedFilms.add(films.getFilmById(rating.getMovieId()));

        return likedFilms;
    }

    private byte[] filmGenreToByteArray(String genre1,String genre2){
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

        return byteArray;
    }

    public List<FilmRecommendation> getRecommendedFilms(){
        List<FilmRecommendation> filmRecommendationList=new ArrayList<>();
        final String sentence = "This film is similar to ";
        var onesByteArray = new byte[]{1,1,1};
        for (var unwatchedFilm:films.getUnwatchedFilms(userId)){
            var filmRecommendation = new FilmRecommendation();
            for (var thisRanking : ratings.getPositiveRatingsOf(userId)){
                Film likedFilm = films.getFilmById(thisRanking.getMovieId());

                var byteArray = filmGenreToByteArray(likedFilm.getGenre(),unwatchedFilm.getGenre());
                var onesArray = Arrays.copyOfRange(onesByteArray, 0, byteArray.length);
                double similarity = new CosineSimilarity(onesArray,byteArray).getResult();

                if(similarity>.7){
                    if(filmRecommendation.score < similarity) filmRecommendation.setAll(unwatchedFilm,sentence+likedFilm.getSeriesTitle(), (float) similarity);
                }
            }
            if(filmRecommendation.score!=-1) filmRecommendationList.add(filmRecommendation);
        }
        filmRecommendationList.sort(Comparator.comparing(FilmRecommendation::getScore).reversed());

        return filmRecommendationList;
    }

}
