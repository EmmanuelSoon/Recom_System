package models;

import Filters.*;
import java.util.*;

public class ThirdRating {
    private ArrayList<Rater> myRaters;

    public ThirdRating() {
        this("rating.csv");
    }

    public ThirdRating(String ratingsFile) {
        FirstRatings FR = new FirstRatings();
        this.myRaters = FR.loadRatings(ratingsFile);
    }

    public int getRaterSize(){
        return this.myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movie_id : movies){
            double avgbyID = getAverageByID(movie_id, minimalRaters);
            if (avgbyID > 0){
                Rating avgRating = new Rating(movie_id, avgbyID);
                ratingList.add(avgRating);
            }
        }
        return ratingList;
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
