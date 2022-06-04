public class Rating {
    
    private String item;
    private double value;

    public Rating(String item, double value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "Movie ID: " + this.item + ", Rating: " + this.value;
    }

    
    public int compareTo(Rating other){
        if (this.value > other.getValue()) return 1;
        else if (this.value < other.getValue()) return -1;
        else return 0;
    }

}
