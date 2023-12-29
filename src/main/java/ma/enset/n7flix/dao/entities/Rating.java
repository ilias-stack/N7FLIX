package ma.enset.n7flix.dao.entities;

public class Rating {
    Integer id;
    float rating;
    Integer movieId;
    Integer userId;

    public Rating(Integer id,float rate,Integer movieId, Integer userId) {
        this.id = id;
        this.rating=rate;
        this.movieId = movieId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public float getRating() {
        return rating;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", movieId=" + movieId +
                ", userId=" + userId +
                '}';
    }
}
