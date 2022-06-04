package models;

public class Movie {
    
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private int minutes;
    private String poster;

    //contructor 
    public Movie(String id, String title, 
    int year, String genres, String director, 
    String country, int minutes, String poster) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.director = director;
        this.country = country;
        this.minutes = minutes;
        this.poster = poster;
    }

    //Getters 

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getGenres() {
        return this.genres;
    }

    public String getDirector() {
        return this.director;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public String getCountry() {
        return this.country;
    }

    public String getPoster() {
        return this.poster;
    }


    @Override
    public String toString(){
        return "ID: " + this.id + ", Title: " + this.title + 
        ", Year: " + this.year +
        ", Country: " + this.country +
        ", Genres: " + this.genres +
        ", Directed by: " + this.director +
        ", Duration: " + this.minutes +
        ", Posted at: " + this.poster;
    }

}