// --== CS400 File Header Information ==--
// Name: Weiqian Zhi
// Email: wzhi3@wisc.edu
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class contains functions for all commands that can be implemented functionally
 */
public class GamesConsole {

    /**
     * This method is to print out choice for user selection: Yes/No
     */
    public void printChoice() {
        System.out.println("1 - Yes \n0 - No");
        System.out.println("Choose an option (also accept 01 refer to Yes, etc) :");
    }

    /**
     * This method is to print out the command menu for user
     */
    public void printMenu() {
        System.out.print("\nMENU");
        System.out.println("\n1 - List all games" + "\n2 - List games with a given rating number"
                + "\n3 - List games with a given tag" + "\n4 - Multi-Criteria Search"
                + "\n5 - Search for game with its name" + "\n0 - Quit");
        System.out.println("Choose an option (also accept 01 refer to List all games, etc) :");
    }

    /**
     * This method is to print out the game tags option.
     */
    public void printTags() {
        System.out.print("Tags");
        System.out.println("\n1 - ACTION" + "\n2 - ADVENTURE" + "\n3 - SIMULATION" + "\n4 - STRATEGY");
        System.out.println("Choose an option (also accept 01 refer to ACTION, etc) :");
    }

    /**
     * This method calls findAllGamesHelper() to find games in database and print the reorder result
     * it in rating order (High - Low).
     *
     * @return false if the game list is empty. Otherwise, true.
     */
    public boolean findAllGames() {
        ArrayList<Bucket> tempList = findAllGamesHelper();
        if (tempList == null) {
            System.out.println("> None of the games are this rating");
            return false;
        }

        // take the sorted result and print it out.
        print(sort(tempList));
        return true;
    }

    /**
     * This method try to find the games in database.
     *
     * @return a list may contains games.
     */
    public ArrayList<Bucket> findAllGamesHelper() {
        ArrayList<Bucket> tempList = new ArrayList<>();
        int tableCapacity = InitializeDatabase.rating.getCapacity();

        // traverse the rating(hashtable) to find the valid value, which is not null.
        while ((tableCapacity--) > 0) {
            if (InitializeDatabase.rating.getHead(tableCapacity) == null) {
                continue;
            }

            Bucket tableHead = InitializeDatabase.rating.getHead(tableCapacity);
            while (tableHead != null) {
                tempList.add(tableHead);
                tableHead = tableHead.next;
            }
        }

        return tempList;
    }

    /**
     * This method calls findSpecifiedRatingHelper() to find game by given rating and print out.
     *
     * @param rating the rating of the game
     * @return false if no game in the list. Otherwise, true.
     */
    public boolean findSpecifiedRating(int rating) {
        System.out.println("*** Game list ***" + " (rating: " + rating + ")");
        ArrayList<Bucket> bucketList = findSpecifiedRatingHelper(rating);

        // Check if the list is null, return false
        if (bucketList == null) {
            System.out.println("> None of the games are this rating");
            return false;
        }

        print(sort(bucketList));
        return true;
    }

    /**
     * This method uses given rating to find the games in the hashtable
     *
     * @param rating the rating of the game
     * @return null if a table is empty. Otherwise, a arrayList that contains found elements
     */
    public ArrayList<Bucket> findSpecifiedRatingHelper(int rating) {
        Bucket tableHead = InitializeDatabase.rating.getHead(rating);

        // if nothing found, then return null. Otherwise, return a arraylist contains bucket.
        if (tableHead == null) {
            return null;
        } else {
            ArrayList<Bucket> bucketList = addBucketToArrayList(tableHead);
            return bucketList;
        }

    }

    /**
     * This method calls findSpecifiedTagHelper() to find game by given tag and print out.
     *
     * @param tagNumber the tag of the game
     * @return false if no game in the list. Otherwise, true.
     */
    public boolean findSpecifiedTag(int tagNumber) {
        ArrayList<Bucket> bucketList;

        if (tagNumber == 1) {
            bucketList = findSpecifiedTagHelper(1);
            System.out.println("*** Game list ***" + " (tag: ACTION)");

            // if nothing found, return false. Otherwise, print it.
            if (bucketList == null) {
                System.out.println("> None of the games are this rating");
                return false;
            }

            print(sort(bucketList));
        } else if (tagNumber == 2) {
            bucketList = findSpecifiedTagHelper(2);
            System.out.println("*** Game list ***" + " (tag: ADVENTURE)");
            if (bucketList == null) {
                System.out.println("> None of the games are this rating");
                return false;
            }
            print(sort(bucketList));
        } else if (tagNumber == 3) {
            bucketList = findSpecifiedTagHelper(3);
            System.out.println("*** Game list ***" + " (tag: SIMULATION)");
            if (bucketList == null) {
                System.out.println("> None of the games are this rating");
                return false;
            }
            print(sort(bucketList));
        } else if (tagNumber == 4) {
            bucketList = findSpecifiedTagHelper(4);
            System.out.println("*** Game list ***" + " (tag: STRATEGY)");
            if (bucketList == null) {
                System.out.println("> None of the games are this rating");
                return false;
            }
            print(sort(bucketList));;
        }
        return true;
    }

    /**
     * This method uses given tag to find the games in the hashtable
     *
     * @param tagNumber the tag of the game
     * @return null if the table is empty. Otherwise, a arrayList that contains found elements
     */
    public ArrayList<Bucket> findSpecifiedTagHelper(int tagNumber) {
        Bucket tableHead;

        if (tagNumber == 1) {
            tableHead = InitializeDatabase.tag.getHead("ACTION");
            // check does the table contains value or not.
            return tableHead == null ? null : addBucketToArrayList(tableHead);
        } else if (tagNumber == 2) {
            tableHead = InitializeDatabase.tag.getHead("ADVENTURE");
            return tableHead == null ? null : addBucketToArrayList(tableHead);
        } else if (tagNumber == 3) {
            tableHead = InitializeDatabase.tag.getHead("SIMULATION");
            return tableHead == null ? null : addBucketToArrayList(tableHead);
        } else if (tagNumber == 4) {
            tableHead = InitializeDatabase.tag.getHead("STRATEGY");
            return tableHead == null ? null : addBucketToArrayList(tableHead);
        }

        return null;
    }

    /**
     * This method calls findSpecifiedNameHelper() to find a game by given name and print out.
     *
     * @param name the name of the game
     * @return false if there is no game found. Otherwise, true.
     * @throws NoSuchElementException when there is this searched game in the game list
     */
    public boolean findSpecifiedName(String name) {
        Game game = findSpecifiedNameHelper(name);
        // if there is no game found, return false. Otherwise, print the game information.
        if (game == null) {
            System.out.println("> No such game found in the Game list");
            return false;
        }
        System.out.println("> The game you are looking for is found in the Game List!");
        printGameInfo(game);
        return true;
    }

    /**
     * This method is to find a given name and search it in the list by using hashtable function,
     * which is get();
     *
     * @param name the name of the game
     * @return a Game object that stores game information
     */
    public Game findSpecifiedNameHelper(String name) {
        try {
            Game game = InitializeDatabase.name.get(name);
            return game;
            // if no game found, throws exception.
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * This method calls multiCriteriaSearchHelper() to find a game by given rating and tag, print
     * out.
     *
     * @param rating the rating of the game
     * @param tag    the tag of the game
     * @return false if the no game matched. Otherwise, true;
     */
    public boolean multiCriteriaSearch(int rating, String tag) {
        ArrayList<Game> gamesMatch = multiCriteriaSearchHelper(rating, tag);
        System.out.println("*** Game list *** (rating: " + rating + ", Tag: " + tag + ")");

        // if no game found, return false
        if (gamesMatch.isEmpty()) {
            System.out.println("> No such games found in the game list");
            return false;
        }

        print(gamesMatch);
        return true;
    }

    /**
     * This method is for dealing with multi-criteria search. Take two user's inputs, which is a
     * rating and a tag. Then find games with these characteristics and print out.
     *
     * @param rating the rating of game
     * @param tag    the tag of game
     * @return a arrayList that contains found elements
     */
    public ArrayList<Game> multiCriteriaSearchHelper(int rating, String tag) {
        ArrayList<Bucket> ratingListBucket = findSpecifiedRatingHelper(rating);
        ArrayList<Game> gamesMatch = new ArrayList<>();
        ArrayList<Game> ratingListGame = new ArrayList<>();

        // check if the game has correct tag. if so, store it into gamesMatch arraylist.
        for (int i = 0; i < ratingListBucket.size(); i++) {
            ratingListGame.add((Game) ratingListBucket.get(i).val);
            if (ratingListGame.get(i).getTag().equals(tag)) {
                gamesMatch.add(ratingListGame.get(i));
            }
        }

        return gamesMatch;
    }

    /**
     * This method is to add all bucket (in a singly linked list bucket) to a arraylist.
     *
     * @param tableHead a singly linked list bucket head.
     * @return a arraylist with Bucket type.
     */
    public ArrayList<Bucket> addBucketToArrayList(Bucket tableHead) {
        ArrayList<Bucket> bucketList = new ArrayList<>();

        while (tableHead != null) {
            bucketList.add(tableHead);
            tableHead = tableHead.next;
        }
        return bucketList;
    }

    /**
     * This method will help sort the rating list from (high to low). If the two games have the same
     * rating number, it will sort base on the its alphabet (A-Z) order.
     *
     * @param tempHead an arraylist contains list value in Bucket type
     * @return a list that contains sorted list.
     */
    public ArrayList<Game> sort(ArrayList<Bucket> tempHead) {
        ArrayList<Game> tempList = new ArrayList<>();

        // get an new arraylist with Game type.
        for (int i = 0; i < tempHead.size(); i++) {
            if (tempHead != null) {
                tempList.add((Game) tempHead.get(i).val);
            }
        }

        // compare and sort the games in a Descending Order by its rating number
        Collections.sort(tempList, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                if (o2.getRating() == o1.getRating()) {
                    return o1.getName().compareTo(o2.getName());
                }
                return o2.getRating() - o1.getRating();
            }
        });

        return tempList;
    }

    /**
     * This method is to print out the games by given list.
     *
     * @param tempList an arraylist contains list value in Game type
     */
    public void print(ArrayList<Game> tempList) {
        int sequence = 1;
        for (Game game : tempList) {
            System.out.println(sequence++ + ". " + game.getName() + " --->  rating: " + game.getRating());
        }
        System.out.println("*****************");
    }

    /**
     * This list will print out a game information.
     *
     * @param game a game object contains its information
     */
    public void printGameInfo(Game game) {
        String gameInfo = "Game Name: " + game.getName() + " --> Tag: " + game.getTag()
                + " --> Rating: " + game.getRating() + " --> Price: $" + game.getPrice()
                + " --> Description: " + game.getDescription();
        System.out.println(gameInfo);
    }

}
