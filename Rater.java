import java.util.ArrayList;

public class Rater {
    
    private String myID;
    private ArrayList<Rating> myRatings;


    public Rater(String myID) {
        super();
        this.myID = myID;
        this.myRatings = new ArrayList<Rating>();
    }

    public void addRating(String item, double rating){
        Rating new_rating = new Rating(item, rating);
        this.myRatings.add(new_rating);
    }

    public String getID(){
        return this.myID;
    }

    public double getRating(String item){
        double curr_rating = -1;
        for (Rating curr : this.myRatings){
            if(curr.getItem() == item){
                curr_rating = curr.getValue();
            }
        }
        return curr_rating;
    }

    public int numRatings(){
        return this.myRatings.size();
    }

    public ArrayList<String> getItemsRated(){
        ArrayList<String> currList = new ArrayList<String>();
        for(Rating curr : this.myRatings){
            currList.add(curr.getItem());
        }
        return currList;
    }

}
