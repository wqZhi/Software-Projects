// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitializeDatabase {
    protected static HashTableMap<String, Game> tag = new HashTableMap();
    protected static HashTableMap<Integer, Game> rating = new HashTableMap();
    protected static HashTableMap<String, Game> name = new HashTableMap();

    public static void addGameToHashTable(Game game) {
        tag.put(game.getTag(), game);
        rating.put(game.getRating(), game);
        name.put(game.getName(), game);
    }

    public static void assmbleGameInfo(String[] gameInfo) {
        String name = gameInfo[0].trim().toUpperCase();
        String tag = gameInfo[1].trim().toUpperCase();
        int rating = Integer.parseInt(gameInfo[2].trim());
        double price = Double.parseDouble(gameInfo[3].trim());
        String description = gameInfo[4].trim();

        addGameToHashTable(new Game(name, tag, rating, price, description));
    }

    public static void readFileIntoDatabase() {
        File file = new File("GamesData.txt");
        try{
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                if (line.equals(""))  {
                    break;
                }
                String[] split = line.split(",", 5);
                assmbleGameInfo(split);
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void clean() {
        tag = new HashTableMap();
        rating = new HashTableMap();
        name = new HashTableMap();
    }

}
