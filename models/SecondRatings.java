package models;
import java.util.ArrayList;


public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {}

    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings FR = new FirstRatings();
        this.myMovies = FR.loadMovies(movieFile);
        this.myRaters = FR.loadRatings(ratingsFile);
    }

    public int getMovieSize(){
        return this.myMovies.size();
    }

    public int getRaterSize(){
        return this.myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        for (Movie movie : myMovies){
            double avgbyID = getAverageByID(movie.getId(), minimalRaters);
            if (avgbyID > 0){
                Rating avgRating = new Rating(movie.getId(), avgbyID);
                ratingList.add(avgRating);
            }
        }
        return ratingList;
    }

    public String getTitle(String id){
        for (Movie movie : myMovies){
            if(movie.getId().equals(id)){
                return movie.getTitle();
            }
        }
        return "ID was not found.";
    }

    private double getAverageByID(String id, int minimalRaters){
        double total = 0.0;
        int numOfraters = 0;
        for (Rater curr : this.myRaters){
            double rating = curr.getRating(id);
            if (rating != -1){
                numOfraters += 1;
                total += rating;
            }
        }
        if (numOfraters >= minimalRaters){
            return total/numOfraters;
        }
        return 0.0;
    }


}
