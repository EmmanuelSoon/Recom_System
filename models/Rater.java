package models;

import java.util.ArrayList;

public interface Rater {
    void addRating(String id, double rating);
    double getRating(String item);
    String getID();
    int numRating();
    ArrayList<String> getItemsRated();

}
