import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;


public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        FileResource fr = new FileResource(filename);
        for (CSVRecord record : fr.getCSVParser()){
            Movie new_mov = new Movie(
                Integer.parseInt(record.get("id")), 
                record.get("title"),
                Integer.parseInt(record.get("year")),
                record.get("genre"), 
                record.get("director"), 
                record.get("country"), 
                Integer.parseInt(record.get("minutes")), 
                record.get("poster")
                );

            movieList.add(new_mov);
        }
        return movieList;
    }

    public ArrayList<Rater> loadRatings(String filename){
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord record : fr.getCSVParser()){
            
            String item = record.get("movie_id");
            Double rating = Double.parseDouble(record.get("rating"));
            String raterID = record.get("rater_id");

            boolean check = false;
            for(Rater curr : raterList){
                if(curr.getID().equals(raterID)){
                    check = true;
                }
            }

            if(!check){
                Rater new_Rater = new Rater(raterID);
                raterList.add(new_Rater);
            }
            
            for(Rater currRater : raterList){
                if(currRater.getID().equals(raterID)){
                    currRater.addRating(item, rating);
                }
            }
        }
        return raterList;
    }

    public void testLoadMovies(){
        ArrayList<Movie> movieList = loadMovies("Data/ratedmoviesfull.csv");
        //Print out all the movies
        // movieList.forEach(System.out::println);

        //Print out the number of movies in the genre comedy
        System.out.println("The number of movies that had comedy: " + numMovies("comedy", movieList));

        //Print out the number of movies more than 150 mins.
        System.out.println("Movies longer than 150 mins: " + movieDuration(150, movieList));

        //Print out list of director names
        ArrayList<String> directList = maxDirectors(movieList);
        System.out.println("Directors who has the max count: " + directList);

    }

    public void testLoadRaters(){
        ArrayList<Rater> raterList = loadRatings("Data/ratings.csv");

        // for (Rater curr : raterList){
        //     System.out.println("Rater ID:" + curr.getID() + ", number of Ratings: " + curr.getItemsRated().size());
        //     curr.getItemsRated().forEach(System.out::println);
        // }
        
        // Print out number of rating by particular rater 
        System.out.println("The number of ratings by ID: " + getNumberOfRatings("193", raterList));
        
        // Print out the max number of ratings and the raters
        maxNumRating(raterList);
        
        // Print out the number of raters for a certain movie
        System.out.println("The number of raters that rated this movie: " + movieRater(raterList, "1798709"));

        //Print out the movie ids that were rated 
        System.out.println("The number of unique movies rated: " + ratedMovies(raterList));
    }


    private int ratedMovies(ArrayList<Rater> raterList){
        Map<String,Integer> movieMap = new HashMap<String, Integer>();
        for (Rater curr : raterList){
            for(String movie : curr.getItemsRated()){
                if(movieMap.containsKey(movie)){
                    movieMap.put(movie, movieMap.get(movie) + 1);
                }
                else {
                    movieMap.put(movie, 1);
                }
            }
        }
        return movieMap.keySet().size();
    }

    private int movieRater(ArrayList<Rater> raterList, String movie_id){
        Map<String,Integer> movieMap = new HashMap<String, Integer>();
        for (Rater curr : raterList){
            for(String movie : curr.getItemsRated()){
                if(movieMap.containsKey(movie)){
                    movieMap.put(movie, movieMap.get(movie) + 1);
                }
                else {
                    movieMap.put(movie, 1);
                }
            }
        }

        return movieMap.get(movie_id);
    }

    private void maxNumRating(ArrayList<Rater> raterList){
        int maxCount = 0;        
        for (Rater curr : raterList){
            if(maxCount < curr.getItemsRated().size()){
                maxCount = curr.getItemsRated().size();
            }
        }
        System.out.println("The max number of ratings by any rater is " + maxCount);

        System.out.println("The raters that had the max number of rating is ");
        for (Rater curr : raterList){
            if(curr.getItemsRated().size() == maxCount){
                System.out.println(curr.getID());
            }
        }
    }

    private int getNumberOfRatings(String rater_ID, ArrayList<Rater> raterList){
        int count = -1;
        for(Rater curr : raterList){
            if (curr.getID().equals(rater_ID)){
                count = curr.getItemsRated().size();
            }
        }

        return count;
    }

    private int numMovies(String genre, ArrayList<Movie> movieList){
        int count = 0;
        for (Movie curr : movieList){
            if(curr.getGenres().toLowerCase().contains(genre)){
                count +=1;
            }
        }
        return count;
    }

    private int movieDuration(int duration, ArrayList<Movie> movieList){
        int count = 0;
        for (Movie curr : movieList){
            if(curr.getMinutes() > duration){
                count +=1;
            }
        }
        return count;
    }

    private ArrayList<String> maxDirectors(ArrayList<Movie> movieList){
        Map<String, Integer> directorDict = new HashMap<>();
        for (Movie movie : movieList){
            String[] directorNames = movie.getDirector().split(",");
            for(String name : directorNames){
                if(directorDict.containsKey(name)) {
                    directorDict.put(name, directorDict.get(name)+1);
                }
                else {
                    directorDict.put(name,1);
                }
            }
        }

        int maxCount = 0;

        for(Map.Entry<String, Integer> kvp : directorDict.entrySet()){
            if(maxCount < kvp.getValue()){
                maxCount = kvp.getValue();
            }
        }

        ArrayList<String> maxDirecterList = new ArrayList<String>();
        for(Map.Entry<String, Integer> kvp : directorDict.entrySet()){
            if(kvp.getValue() == maxCount){
                maxDirecterList.add(kvp.getKey());
            }
        }

        System.out.println("Max count is " + maxCount);
        return maxDirecterList;
    }



}
