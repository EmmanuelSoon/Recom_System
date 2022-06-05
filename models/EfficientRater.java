package models;
import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String myID) {
        super();
        this.myID = myID;
        this.myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating){
        Rating new_rating = new Rating(item, rating);
        this.myRatings.put(item, new_rating);
    }

    public String getID(){
        return this.myID;
    }

    public double getRating(String item){
        double curr_rating = -1;
        if(this.myRatings.containsKey(item)){
            curr_rating = this.myRatings.get(item).getValue();
        }
        return curr_rating;
    }

    public int numRating(){
        return this.myRatings.size();
    }

    public ArrayList<String> getItemsRated(){
        ArrayList<String> currList = new ArrayList<String>();
        
        for (String id : myRatings.keySet())
        {
            currList.add(myRatings.get(id).getItem());
        }
        
        return currList;
    }

}
