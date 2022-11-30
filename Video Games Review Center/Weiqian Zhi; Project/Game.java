// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

/**
 * This class is a Game class that contains game's information.
 */
public class Game {
    private String name;
    private String description;
    private String tag;
    private int rating;
    private double price;

    /**
     * A constructor that create a new game.
     *
     * @param name game's name
     * @param tag game's tag
     * @param rating game's rating
     * @param price game's price
     * @param description game's description
     */
    public Game(String name, String tag, int rating, double price, String description) {
        this.tag = tag;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
    }

    /**
     * Get game's rating
     * @return game's rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Get game's price
     * @return game's price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Get game's tag
     * @return game's tag
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Get game's name
     * @return game's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get game's description
     * @return game's description
     */
    public String getDescription() {
        return this.description;
    }

}
