package ma.enset.n7flix.dao.entities;

public class Film {
    private int id;
    private String posterLink;
    private String seriesTitle;
    private int releasedYear;
    private String certificate;
    private int runtime;
    private String genre;
    private double imdbRating;
    private String overview;
    private int metaScore;
    private String director;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private double noOfVotes;
    private double gross;

    public Film() {
    }

    public Film(int id, String posterLink, String seriesTitle, int releasedYear, String certificate, int runtime, String genre, double imdbRating, String overview, int metaScore, String director, String star1, String star2, String star3, String star4, double noOfVotes, double gross) {
        this.id = id;
        this.posterLink = posterLink;
        this.seriesTitle = seriesTitle;
        this.releasedYear = releasedYear;
        this.certificate = certificate;
        this.runtime = runtime;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.overview = overview;
        this.metaScore = metaScore;
        this.director = director;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.noOfVotes = noOfVotes;
        this.gross = gross;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(int metaScore) {
        this.metaScore = metaScore;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStar1() {
        return star1;
    }

    public void setStar1(String star1) {
        this.star1 = star1;
    }

    public String getStar2() {
        return star2;
    }

    public void setStar2(String star2) {
        this.star2 = star2;
    }

    public String getStar3() {
        return star3;
    }

    public void setStar3(String star3) {
        this.star3 = star3;
    }

    public String getStar4() {
        return star4;
    }

    public void setStar4(String star4) {
        this.star4 = star4;
    }

    public double getNoOfVotes() {
        return noOfVotes;
    }

    public void setNoOfVotes(double noOfVotes) {
        this.noOfVotes = noOfVotes;
    }

    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", posterLink='" + posterLink + '\'' +
                ", seriesTitle='" + seriesTitle + '\'' +
                ", releasedYear=" + releasedYear +
                ", certificate='" + certificate + '\'' +
                ", runtime=" + runtime +
                ", genre='" + genre + '\'' +
                ", imdbRating=" + imdbRating +
                ", overview='" + overview + '\'' +
                ", metaScore=" + metaScore +
                ", director='" + director + '\'' +
                ", star1='" + star1 + '\'' +
                ", star2='" + star2 + '\'' +
                ", star3='" + star3 + '\'' +
                ", star4='" + star4 + '\'' +
                ", noOfVotes=" + noOfVotes +
                ", gross=" + gross +
                '}';
    }
}
