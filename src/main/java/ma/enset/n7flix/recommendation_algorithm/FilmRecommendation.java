package ma.enset.n7flix.recommendation_algorithm;

import ma.enset.n7flix.dao.entities.Film;

public class FilmRecommendation{
    public Film film;
    public String cause;
    public float score;

    public FilmRecommendation(Film film, String cause, float score) {
        this.film = film;
        this.cause = cause;
        this.score = score;
    }

    public FilmRecommendation() {
        score=-1;
    }

    public void setAll(Film film, String cause, float score) {
        this.film = film;
        this.cause = cause;
        this.score = score;
    }

    public float getScore(){
        return this.score;
    }
    @Override
    public String toString() {
        return "FilmRecommendation{" +
                "film=" + film.getSeriesTitle() +
                ", cause='" + cause + '\'' +
                ", score=" + score +
                '}';
    }
}