import models.*;
import java.util.*;


public class MovieRunnerAverage {
    
    public static void printAverageRatings(){
        SecondRatings sr = new SecondRatings("Data/ratedmoviesfull.csv","Data/ratings.csv");
        System.out.println("number of movies: " + sr.getMovieSize());
        System.out.println("number of raters: " + sr.getRaterSize());  
        
        ArrayList<Rating> ratingList = sr.getAverageRatings(12);
        Collections.sort(ratingList);

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + sr.getTitle(curr.getItem()));
        }
        
    }

    public static void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("Data/ratedmoviesfull.csv","Data/ratings.csv");
        ArrayList<Rating> ratingList = sr.getAverageRatings(1);
        String movieTitle = "Vacation";

        for(Rating curr : ratingList){
            if (sr.getTitle(curr.getItem()).toLowerCase().equals(movieTitle.toLowerCase())){
                System.out.println("The Rating for " + movieTitle + " is " + curr.getValue());
            }
        }
    }
}