import models.*;
import java.util.*;
import Filters.*;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> listToRate = new ArrayList<String>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random rand = new Random();

        while (listToRate.size() < 10){
            int randIdx = rand.nextInt(movies.size());
            String movie_id = movies.get(randIdx);
            if (!listToRate.contains(movie_id)){
                listToRate.add(movie_id);
            }
        }
        return listToRate;
    };

    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingList = fr.getSimilarRatings(webRaterID, 20, 5);

        ArrayList<String> resultList = getResults(ratingList, webRaterID, 20);

        if(resultList.size() <= 0){
            System.out.println("<p>No recommendation available</p>");
        }
        else{
            System.out.println("<table><tr><th>Title</th><th>Year</th><th>Duration</th></tr>");
            for (String movie_id : resultList){

                System.out.println(
                    "<tr>"+
                    "<td>" + MovieDatabase.getTitle(movie_id) + "</td>"+
                    "<td>" + MovieDatabase.getYear(movie_id) + "</td>"+
                    "<td>" + MovieDatabase.getMinutes(movie_id) + "</td>"+
                    "</tr>"
                );
            }
            System.out.println("</table>");


        }

    };

    private ArrayList<String> getResults(ArrayList<Rating> ratingList, String rater_id, int num){
        ArrayList<String> results = new ArrayList<String>();
        int numToRecom = Math.min(ratingList.size(), num);

        Rater rater = RaterDatabase.getRater(rater_id);

        for (Rating curr : ratingList){
            if(!rater.hasRating(curr.getItem())){
                results.add(curr.getItem());
            }
            if(results.size() >= numToRecom) break;
        }
        return results;
    }

}
