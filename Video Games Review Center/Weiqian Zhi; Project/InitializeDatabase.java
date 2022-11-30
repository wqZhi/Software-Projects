// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is to initialize database.
 */
public class InitializeDatabase {
    protected static HashTableMap<String, Game> tag = new HashTableMap();
    protected static HashTableMap<Integer, Game> rating = new HashTableMap();
    protected static HashTableMap<String, Game> name = new HashTableMap();

    /**
     * This method is add game to hashtables.
     *
     * @param game an object of Game
     */
    public static void addGameToHashTable(Game game) {
        tag.put(game.getTag(), game);
        rating.put(game.getRating(), game);
        name.put(game.getName(), game);
    }

    /**
     * The method is to assemble game information and make it become an object of Game
     *
     * @param gameInfo information store in an array
     */
    public static void assembleGameInfo(String[] gameInfo) {
        String name = gameInfo[0].trim().toUpperCase();
        String tag = gameInfo[1].trim().toUpperCase();
        int rating = Integer.parseInt(gameInfo[2].trim());
        double price = Double.parseDouble(gameInfo[3].trim());
        String description = gameInfo[4].trim();

        addGameToHashTable(new Game(name, tag, rating, price, description));
    }

    /**
     * This method is to read the .txt file.
     */
    public static void readFileIntoDatabase() {
        File file = new File("GamesData.txt");
        try {
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                if (line.equals("")) {
                    break;
                }
                String[] split = line.split(",", 5);
                assembleGameInfo(split);
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to clean up the Hashtables's value.
     */
    public static void clean() {
        tag = new HashTableMap();
        rating = new HashTableMap();
        name = new HashTableMap();
    }

}
