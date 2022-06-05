import models.*;
import java.util.*;

import Filters.*;

public class MovieRunnerWithFilters {
    public static void printAverageRatings(){

        ThirdRating tr = new ThirdRating("Data/ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());  
        
        MovieDatabase.initialize("Data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());

        ArrayList<Rating> ratingList = tr.getAverageRatings(35);
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


        ThirdRating tr = new ThirdRating("Data/ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());  

        ArrayList<Rating> ratingListFull = tr.getAverageRatings(minimalRaters);
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

    public static void getAverageRatingByYear(int minimalRaters, int year){
        ArrayList<Rating> ratingList = getAverageRatingByFilter(minimalRaters, new YearAfterFilter(year));
        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList);

        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
        }
    }

    public static void getAverageRatingByGenre(int minimalRaters, String genre){
        ArrayList<Rating> ratingList = getAverageRatingByFilter(minimalRaters, new GenreFilter(genre));
        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList);


        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(curr.getItem()));
        }
    }

    public static void getAverageRatingByMinutes(int minimalRaters, int min, int max){
        ArrayList<Rating> ratingList = getAverageRatingByFilter(minimalRaters, new MinutesFilter(min, max));
        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList);


        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " Time: " + MovieDatabase.getMinutes(curr.getItem()) +" " + MovieDatabase.getTitle(curr.getItem()));
            
        }
    }

    public static void getAverageRatingByDirectors(int minimalRaters, String directors){
        ArrayList<Rating> ratingList = getAverageRatingByFilter(minimalRaters, new DirectorsFilter(directors));
        System.out.println("Found " + ratingList.size() + " Movies");
        Collections.sort(ratingList);
        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(curr.getItem()));

            
        }

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

    public static void getAverageRatingByDirectorsAndMinutes(int minimalRaters, String directors, int min, int max){
        AllFilters filters = new AllFilters();
        filters.addFilter(new DirectorsFilter(directors));
        filters.addFilter(new Filters.MinutesFilter(min, max));

        ArrayList<Rating> ratingList = getAverageRatingByFilter(minimalRaters, filters);


        System.out.println(ratingList.size() + " Movie(s) matched");
        Collections.sort(ratingList);


        for (Rating curr : ratingList){
            System.out.println(curr.getValue() + " Time: " + MovieDatabase.getMinutes(curr.getItem()) +" " + MovieDatabase.getTitle(curr.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(curr.getItem()));
        }
    }


}
