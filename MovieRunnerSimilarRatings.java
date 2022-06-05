import models.*;
import java.util.*;

import Filters.*;

public class MovieRunnerSimilarRatings {
    public static void printAverageRatings(){
        RaterDatabase.initialize("Data/ratings_short.csv");
        System.out.println("number of raters: " + RaterDatabase.size());  
        
        MovieDatabase.initialize("Data/ratedmovies_short.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());

        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingList = fr.getAverageRatings(35);
        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList);

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
        }
        
    }

    public static ArrayList<Rating> getAverageRatingByFilter(int minimalRaters, Filter filterCriteria){
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        System.out.println("Number of movies: " + MovieDatabase.size());

        RaterDatabase.initialize("Data/ratings_short.csv");
        System.out.println("number of raters: " + RaterDatabase.size());  

        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingListFull = fr.getAverageRatings(minimalRaters);
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        
        for (Rating curr: ratingListFull){
            boolean check = false;
            for (String movie_id : movieIDs){
                if(curr.getItem().equals(movie_id)){
                    check = true;
                }
            }
            if(check){
                ratingList.add(curr);
            }
        }
        return ratingList;
    }

    public static void getAverageRatingByYearAFterAndGenre(int minimalRaters, int year, String genre){
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(year));
        filters.addFilter(new GenreFilter(genre));


        ArrayList<Rating> ratingList = getAverageRatingByFilter(minimalRaters, filters);


        System.out.println(ratingList.size() + " Movie(s) matched");
        Collections.sort(ratingList);


        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getYear(curr.getItem()) + " " + MovieDatabase.getTitle(curr.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(curr.getItem()));
        }
    }




    public static void printSimilarRatings(){
        RaterDatabase.initialize("Data/ratings.csv");
        System.out.println("number of raters: " + RaterDatabase.size());  
        
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingList = fr.getSimilarRatings("337", 10, 3);

        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList, Collections.reverseOrder());

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
        }

    }
    
    public static void printSimilarRatingsByGenre(){
        RaterDatabase.initialize("Data/ratings.csv");
        System.out.println("number of raters: " + RaterDatabase.size());  
        
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));

        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList, Collections.reverseOrder());

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
        }
    }

    public static void printSimilarRatingsByDirector(){
        RaterDatabase.initialize("Data/ratings.csv");
        System.out.println("number of raters: " + RaterDatabase.size());  
        
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));

        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList, Collections.reverseOrder());

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
        }
    }

    public static void printSimilarRatingsByGenreAndMinutes(){
        RaterDatabase.initialize("Data/ratings.csv");
        System.out.println("number of raters: " + RaterDatabase.size());  
        
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        FourthRatings fr = new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter("168", 10, 3, filter);

        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList, Collections.reverseOrder());

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
        }
    }

    public static void printSimilarRatingsByYearAfterAndMinutes(){
        RaterDatabase.initialize("Data/ratings.csv");
        System.out.println("number of raters: " + RaterDatabase.size());  
        
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        FourthRatings fr = new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter("314", 10, 5, filter);

        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList, Collections.reverseOrder());

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
        }
    }



}
