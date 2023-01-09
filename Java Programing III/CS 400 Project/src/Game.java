// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

public class Game {

    private String name;
    private String description;
    private String tag;
    private int rating;
    private double price;

    public Game(String name, String tag, int rating, double price, String description) {
        this.tag = tag;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
    }

    public int getRating() {
        return this.rating;
    }

    public double getPrice() {
        return this.price;
    }

    public String getTag() {
        return this.tag;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

}
