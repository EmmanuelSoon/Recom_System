package models;

import java.util.*;
import Filters.*;

public class FourthRatings {
    public FourthRatings() {
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
        for (Rater curr : RaterDatabase.getRaters()){
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

    private double dotProduct(Rater me, Rater r){
        ArrayList<String> myRatedList = me.getItemsRated();
        ArrayList<String> otherRatedList = r.getItemsRated();

        ArrayList<String> combinedmovieList = new ArrayList<String>();
        for (String movie_id : myRatedList){
            if(otherRatedList.contains(movie_id)){
                combinedmovieList.add(movie_id);
            }
        }
        double dotProd = 0;

        for (String movie_id : combinedmovieList){
            double myRating = me.getRating(movie_id) -5;
            double otherRating = r.getRating(movie_id) - 5;
            dotProd += myRating*otherRating;
        }

        return dotProd;
    }


    private ArrayList<Rating> getSimilarities(String id){
        Rater rater = RaterDatabase.getRater(id);
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        allRaters.remove(rater);

        for (Rater curr : allRaters){
            double dotProd = dotProduct(rater, curr);
            Rating weighted_rating = new Rating(curr.getID(), dotProd);
            ratingList.add(weighted_rating);
        }
        Collections.sort(ratingList, Collections.reverseOrder());
        return ratingList;
    }


    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        List<Rating> similarity = getSimilarities(id).subList(0,numSimilarRaters);
        ArrayList<Rating> movie_weighted = new ArrayList<Rating>();

        for (String movie_id : MovieDatabase.filterBy(filterCriteria)){
            double total = 0;
            int numRater = 0;
            for (Rating similar : similarity){
                if(similar.getValue() > 0){
                    Rater sim_rater = RaterDatabase.getRater(similar.getItem());
                    if(sim_rater.getRating(movie_id) != -1){
                        total += sim_rater.getRating(movie_id)*similar.getValue();
                        numRater += 1;
                    }
                }
            }
                
                if(numRater >= minimalRaters){
                    Rating new_Rating = new Rating(movie_id, total/numRater);
                    movie_weighted.add(new_Rating);
                }

            }
        
        return movie_weighted;
    }


}
